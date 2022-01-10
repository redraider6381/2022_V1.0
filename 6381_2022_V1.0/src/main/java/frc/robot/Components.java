package frc.robot;
// NO TOUCHY Imports
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
//import edu.wpi.cscore.UsbCamera;// Idk if we are even using one 
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C.*;
import edu.wpi.first.wpilibj.Ultrasonic;
// stuff for CAN
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.cameraserver.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Counter;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Components {

        // NAVX SUPER Gyro (Idk if we are using a gyro)
        //public static AHRS OGgyro = new AHRS(SPI.Port.kMXP);
        public static ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
        //public static AnalogGyro gyro = new AnalogGyro(0);
    
        // CAN Motors (THIS NEEDS TO BE MODIFIED WHEN WE KNOW WHERE ALL THE MOTERS ARE !!!!!!!!!!!!!!!!!IMPOTTANT!!!!!!!!!!!!!!!!!!!!!)
        public static CANSparkMax CANFrontLeft    = new CANSparkMax(2, MotorType.kBrushless);
        public static CANSparkMax CANFrontRight   = new CANSparkMax(4, MotorType.kBrushless);
        public static CANSparkMax CANBackLeft     = new CANSparkMax(1, MotorType.kBrushless);
        public static CANSparkMax CANBackRight    = new CANSparkMax(3, MotorType.kBrushless);
        //Flywheel
        public static CANSparkMax CANShooter  = new CANSparkMax(5, MotorType.kBrushless);
        //might have 2 motors
        //public static CANSparkMax CANShooter2  = new CANSparkMax(7, MotorType.kBrushless);
        public static Servo ExampleServo  = new Servo(1);



        // Joystick
        public static Joystick happyStick = new Joystick(1);
    
        // X-Box controller
        public static XboxController XBController= new XboxController(0);
    
        //Sparks, replace 1 with it's port
        public static Spark ExampleSpark        = new Spark(1);
    
        //lazer 
        public static I2C lidar;
        //public static Counter m_LIDAR = new Counter(0);

        // Encoders

        public static RelativeEncoder backLeftEncoder            = CANBackLeft.getEncoder();
        public static RelativeEncoder backRightEncoder           = CANBackRight.getEncoder();
        public static RelativeEncoder frontLeftEncoder           = CANFrontLeft.getEncoder();
        public static RelativeEncoder frontRightEncoder          = CANFrontRight.getEncoder();
        
        // PID
        //public static PIDController PID = new PIDController(.5, 0, 0);

        // limelight
    
        public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    }
