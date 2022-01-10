/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import java.io.Console;
import java.lang.Math;
import edu.wpi.first.wpilibj.AnalogGyro; 
import edu.wpi.first.wpilibj.Counter;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANError;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {




  
  double wheelDiameter = 6; //inches
  double gearRatio = 10.7; //1/10.7
  int autoCount = 0;


  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private static final String TestAuto = "Test Auto";

  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private static final double targetHeight = 98.25; // inches
  private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private static NetworkTableEntry tx = table.getEntry("tx");
  private static NetworkTableEntry ty = table.getEntry("ty");
  private static NetworkTableEntry tv = table.getEntry("tv");
  private static double limeX = 0.0;
  private static double limeV = 0.0;
  private static double limeY = 0.0;  
  private static double limeArea = 0.0;
  private NetworkTable limeTable = NetworkTableInstance.getDefault().getTable("limelight");

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    Components.gyro.calibrate();
    Components.gyro.reset();
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    m_chooser.addOption("Test Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

// //plug the lidar into PWM 0
//     Components.m_LIDAR.setMaxPeriod(1.00); //set the max period that can be measured
//     Components.m_LIDAR.setSemiPeriodMode(true); //Set the counter to period measurement
//     Components.m_LIDAR.reset();

    Components.backLeftEncoder.setPosition(0);
    Components.backRightEncoder.setPosition(0);
    Components.frontLeftEncoder.setPosition(0);
    Components.frontRightEncoder.setPosition(0);

     //Motor set up:
  //If we have to invert any motors do it here

  // SPARK MAX Mode Settings
  Components.CANBackLeft.setIdleMode(IdleMode.kBrake);
  Components.CANFrontLeft.setIdleMode(IdleMode.kBrake);
  Components.CANBackRight.setIdleMode(IdleMode.kBrake);
  Components.CANBackRight.setIdleMode(IdleMode.kBrake);
  Components.CANShooter.setIdleMode(IdleMode.kCoast);
  Components.CANBackLeft.setInverted(true);
  Components.CANFrontLeft.setInverted(true);
  Components.CANBackRight.setInverted(false);
  Components.CANBackRight.setInverted(false);

  //Encoder set up
  Components.backLeftEncoder.setPositionConversionFactor((wheelDiameter*Math.PI)/gearRatio);//FIND GEAR RATIO = 10.7:1 
  Components.backRightEncoder.setPositionConversionFactor((wheelDiameter*Math.PI)/gearRatio);//FIND GEAR RATIO)
  Components.frontLeftEncoder.setPositionConversionFactor((wheelDiameter*Math.PI)/gearRatio);
  Components.frontRightEncoder.setPositionConversionFactor((wheelDiameter*Math.PI)/gearRatio);


  

  //GYRO
  //Components.gyro.zeroYaw();

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  }

 
  @Override
  public void robotPeriodic() {
  }
  public void updateLimelightDashboard() {
    String limelightState;

    int limeStateNum = limeTable.getEntry("ledMode").getNumber(0).intValue();

    if(limeStateNum == 1)
      limelightState = "OFF";
    else if(limeStateNum == 3)
      limelightState = "ON";
    else
      limelightState = "UNKNOWN";

    SmartDashboard.putString("LimelightState", limelightState);
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = TestAuto;
    //m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    limeTable.getEntry("ledMode").setNumber(3);
    updateLimelightDashboard();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case TestAuto:
     // Autos.TestAuto();
      break;
      case kCustomAuto:
      // customAuto();//customAuto doesnt exist rn
      break;
    }
  }
  @Override
  public void teleopInit() {
    // Turn off limelight LEDs
    limeTable.getEntry("ledMode").setNumber(1);
    updateLimelightDashboard();
    
  }
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
    double leftYAxis = Components.XBController.getRawAxis(1);
    double rightYAxis = Components.XBController.getRawAxis(5);
    double leftXAxis = Components.XBController.getRawAxis(0);
    double rightXAxis = Components.XBController.getRawAxis(4);
    double deadzone = 0.05;
    // Components.CANFrontLeft.set(leftYAxis);
    // Components.CANFrontRight.set(leftYAxis);
    // Components.CANBackLeft.set(leftYAxis);
    // Components.CANBackRight.set(leftYAxis);

    setDriveForMecanum(Mecanum.joystickToMotion(
      -leftXAxis, leftYAxis,
      -rightXAxis, rightYAxis));

    if((Math.abs(leftYAxis) <= deadzone) && (Math.abs(leftXAxis) <= deadzone) && (Math.abs(rightXAxis) <= deadzone) && (Math.abs(rightYAxis) <= deadzone))
    {
      Components.CANFrontLeft.set(0);
      Components.CANFrontRight.set(0);
      Components.CANBackLeft.set(0);
      Components.CANBackRight.set(0);
    }

        // Turn on limelight LEDs when secondary btn 12 is pressed.
        if(Components.happyStick.getRawButtonPressed(12) || Components.XBController.getBButtonPressed()) {
          limeTable.getEntry("ledMode").setNumber(3);
          updateLimelightDashboard();
        }
    
        // Turn off limelight LEDs when secondary btn 10 is pressed
        if(Components.happyStick.getRawButtonPressed(10)) {
          limeTable.getEntry("ledMode").setNumber(1);
          updateLimelightDashboard();
        }
    // double drivePower = 0.5;

    // boolean B = Components.XBController.getBButton();
    // boolean X = Components.XBController.getXButton();

    // if(B)
    // {
    //     drivePower = 0.9;
    // }
    // else if(X)
    // {
    //     drivePower = 0.5;
    // }
     
    //  MechanumDrive.setDriveForMecanum(Mecanum.joystickToMotion(
    //      -leftXAxis, leftYAxis,
    //      rightXAxis, rightYAxis));


    //BallControl.periodic();
    //Climber.periodic();
    //In theory we would have a Spinner.periodic too
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
        // Turn on limelight LEDs when secondary btn 12 is pressed.
        if(Components.happyStick.getRawButtonPressed(12) || Components.XBController.getBButtonPressed()) {
          limeTable.getEntry("ledMode").setNumber(3);
          updateLimelightDashboard();
        }
    
        // Turn off limelight LEDs when secondary btn 10 is pressed
        if(Components.happyStick.getRawButtonPressed(10)) {
          limeTable.getEntry("ledMode").setNumber(1);
          updateLimelightDashboard();
        }
    
    limeY = ty.getDouble(0.0);
    System.out.println(limeY);
    double mountedHeight = 15.75;
    double tapeHeight = 81.25;//6 feet 9 1/4 inchess
    double differnceinHeights = mountedHeight-tapeHeight;
    double mountingAngle = 26.61; //116.61-90
    double distance = (differnceinHeights) / Math.tan(limeY + mountingAngle);
    //System.out.println(distance);


    // Components.lidar.read(registerAddress, count, buffer)
    // Components.lidar
    
    
    // byte[] data = new byte[2];

    // for (Components.lidar c : Components.lidar.values())
    // {
    // System.out.println(c);

    //     System.out.println((int)Integer.toUnsignedLong(data[0] << 8) + Byte.toUnsignedInt(data[1]));

    //     Timer.delay(1);
    // }
  
  }
  public static void setDriveForMecanum(Mecanum.Motion motion) {
    double defaultSpeed = 0.2;
    Mecanum.Wheels wheels = Mecanum.motionToWheels(motion);
    Components.CANFrontLeft.set(defaultSpeed*(wheels.frontLeft));
    Components.CANFrontRight.set(defaultSpeed*(wheels.frontRight));
    Components.CANBackLeft.set(defaultSpeed*(wheels.backLeft));
    Components.CANBackRight.set(defaultSpeed*(wheels.backRight));
}
}
