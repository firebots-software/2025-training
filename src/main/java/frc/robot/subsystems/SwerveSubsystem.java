package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SwerveSubsystem extends SubsystemBase {

    private static SwerveSubsystem instance;

    private final SwerveModuleSubsystem frontLeft = new SwerveModuleSubsystem(
        Constants.SwerveCostants.FRONT_LEFT_STEER_MOTOR_ID,
        Constants.SwerveCostants.FRONT_LEFT_DRIVE_MOTOR_ID,
        Constants.SwerveCostants.FRONT_LEFT_ENCODER_ID,
        Constants.SwerveCostants.INVERT_LEFT_SIDE,
        Constants.SwerveCostants.REVERSE_STEER_MOTOR,
        Constants.SwerveCostants.FRONT_LEFT_ENCODER_OFFSET
    );

    private final SwerveModuleSubsystem frontRight = new SwerveModuleSubsystem(
        Constants.SwerveCostants.FRONT_RIGHT_STEER_MOTOR_ID,
        Constants.SwerveCostants.FRONT_RIGHT_DRIVE_MOTOR_ID,
        Constants.SwerveCostants.FRONT_RIGHT_ENCODER_ID,
        Constants.SwerveCostants.INVERT_RIGHT_SIDE,
        Constants.SwerveCostants.REVERSE_STEER_MOTOR,
        Constants.SwerveCostants.FRONT_RIGHT_ENCODER_OFFSET
    );

    private final SwerveModuleSubsystem backLeft = new SwerveModuleSubsystem(
        Constants.SwerveCostants.BACK_LEFT_STEER_MOTOR_ID,
        Constants.SwerveCostants.BACK_LEFT_DRIVE_MOTOR_ID,
        Constants.SwerveCostants.BACK_LEFT_ENCODER_ID,
        Constants.SwerveCostants.INVERT_LEFT_SIDE,
        Constants.SwerveCostants.REVERSE_STEER_MOTOR,
        Constants.SwerveCostants.BACK_LEFT_ENCODER_OFFSET
    );

    private final SwerveModuleSubsystem backRight = new SwerveModuleSubsystem(
        Constants.SwerveCostants.BACK_RIGHT_STEER_MOTOR_ID,
        Constants.SwerveCostants.BACK_RIGHT_DRIVE_MOTOR_ID,
        Constants.SwerveCostants.BACK_RIGHT_ENCODER_ID,
        Constants.SwerveCostants.INVERT_RIGHT_SIDE,
        Constants.SwerveCostants.REVERSE_STEER_MOTOR,
        Constants.SwerveCostants.BACK_RIGHT_ENCODER_OFFSET
    );

    private final Pigeon2 gyro = new Pigeon2(Constants.SwerveCostants.pigeonID);
    


// Creating my odometry object from the kinematics object and the initial wheel positions.
// Here, our starting pose is 5 meters along the long end of the field and in the
// center of the field along the short end, facing the opposing alliance wall.
    SwerveDriveOdometry m_odometry = new SwerveDriveOdometry(
    Constants.SwerveCostants.m_Kinematics, gyro.getRotation2d(),
    new SwerveModulePosition[] {
        frontLeft.getModulePosition(),
        frontRight.getModulePosition(),
        backLeft.getModulePosition(),
        backRight.getModulePosition(),
    }, new Pose2d(0, 0, new Rotation2d()));

    public void setModuleStates(SwerveModuleState[] desiredStates) {
        frontLeft.setState(desiredStates[0]);
        frontRight.setState(desiredStates[1]);
        backLeft.setState(desiredStates[2]);
        backRight.setState(desiredStates[3]);
    }

    public static SwerveSubsystem getInstance() {
        if (instance==null) {
            instance = new SwerveSubsystem();
        }
        return instance;
    }

    public double getHeading() {
        return Math.IEEEremainder(gyro.getYaw().getValueAsDouble(), 360);
    }
    // 

    public edu.wpi.first.math.geometry.Rotation2d getRotation2d() {
        return edu.wpi.first.math.geometry.Rotation2d.fromDegrees(getHeading());
    }

    public SwerveModulePosition[] getModulePositions() {
        return new SwerveModulePosition[]{frontLeft.getModulePosition(), frontRight.getModulePosition(), backLeft.getModulePosition(), backRight.getModulePosition()};
    }

    public edu.wpi.first.math.geometry.Pose2d getPose() {
        return m_odometry.getPoseMeters();
    }

    @Override
    public void periodic() {
        m_odometry.update(getRotation2d(), getModulePositions());
    }
}