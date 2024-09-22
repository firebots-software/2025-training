// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {
    public static final class ElevatorConstants {
        public static final int kLeftElevatorMotorID = 1; // CAN ID for the left TalonFX
        public static final int kRightElevatorMotorID = 2; // CAN ID for the right TalonFX

        // Elevator Physical Constants
        public static final double kElevatorGearing = 10.0; // Example gear ratio
        public static final double kElevatorDrumRadius = Units.inchesToMeters(2.0); // Example drum radius
        public static final double kCarriageMass = 4.0; // kg
        public static final double kMinElevatorHeightMeters = 0.0;
        public static final double kMaxElevatorHeightMeters = 2.0; // Example max height

        // Conversion factor
        public static final double kElevatorMetersPerRotation = 2 * Math.PI * kElevatorDrumRadius / kElevatorGearing;

        // PID Constants
        public static final double kElevatorKp = 5;
        public static final double kElevatorKi = 0.0;
        public static final double kElevatorKd = 0.0;

        // Feedforward Constants
        public static final double kElevatorkS = 0.0; // Static friction
        public static final double kElevatorkG = 0.5; // Gravity compensation
        public static final double kElevatorkV = 0.0; // Velocity feedforward
        public static final double kElevatorkA = 0.0; // Acceleration feedforward
    }

    public static final class SimulationConstants {
        // Simulation update rate
        public static final double kSimUpdateRate = 0.020; // 20ms update rate
    }

    // Add other subsystem constants here as needed
}