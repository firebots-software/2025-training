// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  public static class SwerveCostants {
    public static final int pigeonID = 40;

    
    public static final double maxDriveAcceleration = 4;
    public static final double maxAngularAcceleration = 4;
    

    public static final double moveCOMY = 0.046007;
    public static final double moveCOMX = 3.36044;

    public static final boolean REVERSE_STEER_MOTOR=true;
    public static final boolean INVERT_LEFT_SIDE = false;
    public static final boolean INVERT_RIGHT_SIDE = true;

    // Front Left
    public static final int FRONT_LEFT_STEER_MOTOR_ID = 3;
    public static final int FRONT_LEFT_DRIVE_MOTOR_ID = 4;
    public static final int FRONT_LEFT_ENCODER_ID = 21;
    public static final double FRONT_LEFT_ENCODER_OFFSET = -0.46337890625;//-0.464599609375;//-0.466552734375;

    public static final double FRONT_LEFT_X_POS_INCHES = 11.26 - moveCOMX;
    public static final double FRONT_LEFT_Y_POS_INCHES = 11.417 - moveCOMY;

    // Front Right
    public static final int FRONT_RIGHT_STEER_MOTOR_ID = 5;
    public static final int FRONT_RIGHT_DRIVE_MOTOR_ID = 6;
    public static final int FRONT_RIGHT_ENCODER_ID = 22;
    public static final double FRONT_RIGHT_ENCODER_OFFSET = -0.437744140625;//-0.44775390625;//-0.436767578125;

    public static final double FRONT_RIGHT_X_POS_INCHES = 11.26 - moveCOMX;
    public static final double FRONT_RIGHT_Y_POS_INCHES = -11.417 - moveCOMY;

    // Back Left
    public static final int BACK_LEFT_STEER_MOTOR_ID = 1;
    public static final int BACK_LEFT_DRIVE_MOTOR_ID = 2;
    public static final int BACK_LEFT_ENCODER_ID = 20;
    public static final double BACK_LEFT_ENCODER_OFFSET = -0.1796875;//-0.18886484375;//-0.165283203125;

    public static final double BACK_LEFT_X_POS_INCHES = -11.26 - moveCOMX;
    public static final double BACK_LEFT_Y_POS_INCHES = 11.417 - moveCOMY;

    // Back Right
    public static final int BACK_RIGHT_STEER_MOTOR_ID = 7;
    public static final int BACK_RIGHT_DRIVE_MOTOR_ID = 8;
    public static final int BACK_RIGHT_ENCODER_ID = 23;
    public static final double BACK_RIGHT_ENCODER_OFFSET = -0.345703125;//-0.33544921875;//-0.336181640625;

    public static final double BACK_RIGHT_X_POS_INCHES = -11.26 - moveCOMX;
    public static final double BACK_RIGHT_Y_POS_INCHES = -11.417 - moveCOMY;

    // Locations for the swerve drive modules relative to the robot center.
    public static Translation2d m_frontLeftLocation = new Translation2d(0.286004, 0.2899918);
    public static Translation2d m_frontRightLocation = new Translation2d(0.286004,-0.2899918);
    public static Translation2d m_backLeftLocation = new Translation2d(-0.286004,0.2899918);
    public static Translation2d m_backRightLocation = new Translation2d(-0.286004,-0.2899918);

    public static SwerveDriveKinematics m_Kinematics = new SwerveDriveKinematics(m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation);
  }
}
