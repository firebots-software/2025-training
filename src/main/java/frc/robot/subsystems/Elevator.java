package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ElevatorConstants;

public class Elevator implements AutoCloseable {
    private final DCMotor m_elevatorGearbox = DCMotor.getKrakenX60(2);

    private final ProfiledPIDController m_controller =
        new ProfiledPIDController(
            ElevatorConstants.kElevatorKp,
            ElevatorConstants.kElevatorKi,
            ElevatorConstants.kElevatorKd,
            new TrapezoidProfile.Constraints(2.45, 2.45));
    
    private final ElevatorFeedforward m_feedforward =
        new ElevatorFeedforward(
            ElevatorConstants.kElevatorkS,
            ElevatorConstants.kElevatorkG,
            ElevatorConstants.kElevatorkV,
            ElevatorConstants.kElevatorkA);

    private final ElevatorSim m_elevatorSim =
        new ElevatorSim(
            m_elevatorGearbox,
            ElevatorConstants.kElevatorGearing,
            ElevatorConstants.kCarriageMass,
            ElevatorConstants.kElevatorDrumRadius,
            ElevatorConstants.kMinElevatorHeightMeters,
            ElevatorConstants.kMaxElevatorHeightMeters,
            true,
            0);

    private final TalonFX m_leftMotor = new TalonFX(ElevatorConstants.kLeftElevatorMotorID);
    private final TalonFXSimState m_leftMotorSim = m_leftMotor.getSimState();

    private final Mechanism2d m_mech2d = new Mechanism2d(20, 50);
    private final MechanismRoot2d m_mech2dRoot = m_mech2d.getRoot("Elevator Root", 10, 0);
    private final MechanismLigament2d m_elevatorMech2d =
        m_mech2dRoot.append(
            new MechanismLigament2d("Elevator", m_elevatorSim.getPositionMeters(), 90));

    public Elevator() {
        configureMotor(m_leftMotor);

        SmartDashboard.putData("Elevator Sim", m_mech2d);
    }

    private void configureMotor(TalonFX motor) {
        motor.setNeutralMode(NeutralModeValue.Brake);
        motor.setInverted(false);  // Adjust as needed
    }

    public void setSpeed(double speed) {
      // Clamp the speed to be between -1.0 and 1.0
      speed = Math.max(-1.0, Math.min(1.0, speed));

      // Check if we're at the limits of the elevator
      if ((speed > 0 && getPosition() >= ElevatorConstants.kMaxElevatorHeightMeters) ||
          (speed < 0 && getPosition() <= ElevatorConstants.kMinElevatorHeightMeters)) {
          stop();
          return;
      }

      // Calculate the voltage to apply based on the desired speed
      double voltage = speed * RobotController.getBatteryVoltage();

      // Apply the calculated voltage to the motor
      m_leftMotor.setVoltage(voltage);
  }

    public void simulationPeriodic() {
        double supplyVoltage = RobotController.getBatteryVoltage();
        m_leftMotorSim.setSupplyVoltage(supplyVoltage);

        // In simulation, we'll assume both motors contribute equally
        double totalMotorVoltage = m_leftMotorSim.getMotorVoltage();
        m_elevatorSim.setInput(totalMotorVoltage);
        m_elevatorSim.update(0.020);

        double positionRotations = m_elevatorSim.getPositionMeters() / ElevatorConstants.kElevatorMetersPerRotation;
        double velocityRotationsPerSecond = m_elevatorSim.getVelocityMetersPerSecond() / ElevatorConstants.kElevatorMetersPerRotation;

        m_leftMotorSim.setRawRotorPosition(positionRotations);
        m_leftMotorSim.setRotorVelocity(velocityRotationsPerSecond);

        RoboRioSim.setVInVoltage(
            BatterySim.calculateDefaultBatteryLoadedVoltage(m_elevatorSim.getCurrentDrawAmps()));

        SmartDashboard.putNumber("Elevator Position", getPosition());
        SmartDashboard.putNumber("Elevator Velocity", getVelocity());
    }

    public void reachGoal(double goal) {
        m_controller.setGoal(goal);

        double pidOutput = m_controller.calculate(getPosition());
        double feedforwardOutput = m_feedforward.calculate(m_controller.getSetpoint().velocity);
        double outputVoltage = pidOutput + feedforwardOutput;
        
        m_leftMotor.setVoltage(outputVoltage);
        // The right motor will follow the left motor, so we don't need to set it explicitly
    }

    public void stop() {
        m_controller.setGoal(getPosition());
        m_leftMotor.set(0.0);
        // The right motor will follow the left motor and also stop
    }

    public void updateTelemetry() {
        m_elevatorMech2d.setLength(getPosition());
    }

    public double getPosition() {
        return m_leftMotor.getPosition().getValueAsDouble() * ElevatorConstants.kElevatorMetersPerRotation;
    }

    public double getVelocity() {
        return m_leftMotor.getVelocity().getValueAsDouble() * ElevatorConstants.kElevatorMetersPerRotation;
    }

    @Override
    public void close() {
        m_leftMotor.close();
        m_mech2d.close();
    }
}