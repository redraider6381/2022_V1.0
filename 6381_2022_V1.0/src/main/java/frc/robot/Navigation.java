package frc.robot;

import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
//import edu.wpi.first.wpilibj.geometry.Translation2d;


public class Navigation {
//     @Override
// public void periodic() {
//   // Get my wheel speeds
//   var wheelSpeeds = new MecanumDriveWheelSpeeds(
//       m_frontLeftEncoder.getRate(), m_frontRightEncoder.getRate(),
//       m_backLeftEncoder.getRate(), m_backRightEncoder.getRate());

//   // Get my gyro angle. We are negating the value because gyros return positive
//   // values as the robot turns clockwise. This is not standard convention that is
//   // used by the WPILib classes.
//   var gyroAngle = Rotation2d.fromDegrees(-m_gyro.getAngle());

//   // Update the pose
//   m_pose = m_odometry.update(gyroAngle, wheelSpeeds);


//     // Locations of the wheels relative to the robot center.
// Translation2d m_frontLeftLocation = new Translation2d(0.381, 0.381);
// Translation2d m_frontRightLocation = new Translation2d(0.381, -0.381);
// Translation2d m_backLeftLocation = new Translation2d(-0.381, 0.381);
// Translation2d m_backRightLocation = new Translation2d(-0.381, -0.381);

// // Creating my kinematics object using the wheel locations.
// MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(
//   m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation
// );

// // Creating my odometry object from the kinematics object. Here,
// // our starting pose is 5 meters along the long end of the field and in the
// // center of the field along the short end, facing forward.
// MecanumDriveOdometry m_odometry = new MecanumDriveOdometry(m_kinematics,
//   getGyroHeading(), new Pose2d(5.0, 13.5, new Rotation2d()));


//   //Mecanum Code:
//   // Example chassis speeds: 1 meter per second forward, 3 meters
// // per second to the left, and rotation at 1.5 radians per second
// // counterclockwise.
// ChassisSpeeds speeds = new ChassisSpeeds(1.0, 3.0, 1.5);

// // Convert to wheel speeds
// MecanumDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(speeds);

// // Get the individual wheel speeds
// double frontLeft = wheelSpeeds.frontLeftMetersPerSecond;
// double frontRight = wheelSpeeds.frontRightMetersPerSecond;
// double backLeft = wheelSpeeds.rearLeftMetersPerSecond;
// double backRight = wheelSpeeds.rearRightMetersPerSecond;

// // Field Relative Drive:
// // The desired field relative speed here is 2 meters per second
// // toward the opponent's alliance station wall, and 2 meters per
// // second toward the left field boundary. The desired rotation
// // is a quarter of a rotation per second counterclockwise. The current
// // robot angle is 45 degrees.
// ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
//   2.0, 2.0, Math.PI / 2.0, Rotation2d.fromDegrees(45.0));

// // Now use this in our kinematics
// MecanumDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(speeds);

// // Covnerting wheel speeds to chassis speeds
// // Example wheel speeds
// var wheelSpeeds = new MecanumDriveWheelSpeeds(-17.67, 20.51, -13.44, 16.26);

// // Convert to chassis speeds
// ChassisSpeeds chassisSpeeds = m_kinematics.toChassisSpeeds(wheelSpeeds);

// // Getting individual speeds
// double forward = chassisSpeeds.vxMetersPerSecond;
// double sideways = chassisSpeeds.vyMetersPerSecond;
// double angular = chassisSpeeds.omegaRadiansPerSecond;
  
// }
}
