package frc.robot;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Mecanum;
public class MechanumDrive{
    private static double drivePower = .2;

    /**
     * X-Box controller drives robot
     */
public static void driveRobot(){
   

    //Joysticks:
    double leftYAxis = Components.XBController.getRawAxis(1);
    double rightYAxis = Components.XBController.getRawAxis(5);
    double leftXAxis = Components.XBController.getRawAxis(0);
    double rightXAxis = Components.XBController.getRawAxis(4);
    
    //Mecanum Set-up:
    setDriveForMecanum(Mecanum.joystickToMotion(
        leftXAxis, leftYAxis,
        rightXAxis, rightYAxis));
 }

    //Mecanum Power:
    public static void setDriveForMecanum(Mecanum.Motion motion) {
        Mecanum.Wheels wheels = Mecanum.motionToWheels(motion);
        /*runtime.reset();
        robot.FL.setPower(Range.clip(Math.abs(wheels.frontLeft*DrivePower*(runtime.seconds()/0.75)), -1.0, 1.0));
        robot.FR.setPower(Range.clip(Math.abs(wheels.frontRight*DrivePower*(runtime.seconds()/0.75)), -1.0, 1.0));
        robot.BL.setPower(Range.clip(Math.abs(wheels.backLeft*DrivePower*(runtime.seconds()/0.75)), -1.0, 1.0));
        robot.BR.setPower(Range.clip(Math.abs(wheels.backRight*DrivePower*(runtime.seconds()/0.75)), -1.0, 1.0));*/
        Components.CANFrontLeft.set(drivePower*wheels.frontLeft);
        Components.CANFrontRight.set(drivePower*wheels.frontRight);
        Components.CANBackLeft.set(drivePower*wheels.backLeft);
        Components.CANBackRight.set(drivePower*wheels.backRight);
    }
}