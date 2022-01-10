package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import java.util.TimerTask;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C.Port;

import java.util.List;
import java.util.Arrays;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import java.lang.Math;




public class Autonomous {


    public static void DriveDistance(double x, double y, double speed) // Simpler Drive
    {

    }

 
    public static void TurnAngle(double deg, double speed, boolean direction)//Yes means turn right, no means turn left
    {

    }
    public static void resetComponents(){
        Components.gyro.reset();
        Components.frontLeftEncoder.setPosition(0);
        Components.frontRightEncoder.setPosition(0);
        Components.backLeftEncoder.setPosition(0);
        Components.backRightEncoder.setPosition(0);
    }
    public static void StopMotors(){
        Components.gyro.reset();
        Components.CANFrontLeft.set(0);
        Components.CANFrontRight.set(0);
        Components.CANBackLeft.set(0);
        Components.CANBackRight.set(0);
    }
}