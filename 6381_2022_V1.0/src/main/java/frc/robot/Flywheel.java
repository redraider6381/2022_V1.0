package frc.robot;


public class Flywheel {
    
    public void SetSpeed(double Speed){
        Flywheel.setVoltage(pid.calculate(Flywheel.get(), Speed) + feedforward);
//use https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/controller/ProfiledPIDController.html to make a PID, 
    }

}
