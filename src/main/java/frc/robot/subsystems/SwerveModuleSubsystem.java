package frc.robot.subsystems;

import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SwerveModuleSubsystem extends SubsystemBase {
    private final int turningMotorID;
    private final int driveMotorID;
    private final int canCoderID;

    private final boolean driveMotorReverse;
    private final boolean turningMotorReverse;
    private final double absoluteEncoderOffset;

    private final TalonFX driveMotor;
    private final TalonFX turnMotor;
    private final CANcoder turningEncoder;

    public SwerveModuleSubsystem(int turningMotorID, int driveMotorID, int canCoderID, boolean driveMotorReverse, boolean turningMotorReverse, double absoluteEncoderOffset) {
        this.turningMotorID  = turningMotorID;;
        this.driveMotorID=driveMotorID;
        this.canCoderID=canCoderID;
        this.driveMotorReverse=driveMotorReverse;
        this.turningMotorReverse=turningMotorReverse;
        this.absoluteEncoderOffset=absoluteEncoderOffset;

        driveMotor=new TalonFX(driveMotorID, "Patrice the Pineapple");
        turnMotor= new TalonFX(turningMotorID, "Patrice the Pineapple");
        
        driveMotor.setInverted(driveMotorReverse);
        turnMotor.setInverted(turningMotorReverse);

        turningEncoder=new CANcoder(canCoderID, "Patrice the Pineapple");
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(driveMotor.getVelocity().getValueAsDouble(), new Rotation2d(turningEncoder.getAbsolutePosition().getValueAsDouble()-absoluteEncoderOffset));
    }



    public void setState(SwerveModuleState desiredState) {
        desiredState = SwerveModuleState.optimize(getState(), getState().angle);
        driveMotor.setControl(new VelocityVoltage(desiredState.speedMetersPerSecond));
        turnMotor.setControl(new PositionVoltage(desiredState.angle.getRadians()));
    }

    public SwerveModulePosition getModulePosition() {
        return new SwerveModulePosition((driveMotor.getPosition().getValueAsDouble()-absoluteEncoderOffset) *0.3912, getState().angle);
    }
}
