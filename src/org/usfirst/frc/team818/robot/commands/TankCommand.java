package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.MathUtil;
import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TankCommand extends CommandBase {
    
    double leftSpeed = 0.0;
    double rightSpeed = 0.0;
    double ramp = 0.03;
    
    public TankCommand() {
       	requires(drive);
    }

    protected void initialize() {
    	drive.setBoth(0);
    	SmartDashboard.putBoolean("Check1", true);
    	RobotLog.putMessage("Ramping ON");
    }

    protected void execute() {
        if (oi.getLeftY() > leftSpeed) leftSpeed = MathUtil.limitMax(leftSpeed + ramp, oi.getLeftY());
        if (oi.getLeftY() < leftSpeed) leftSpeed = MathUtil.limitMin(leftSpeed - ramp, oi.getLeftY());
        if (oi.getRightY() > rightSpeed) rightSpeed = MathUtil.limitMax(rightSpeed + ramp, oi.getRightY());
        if (oi.getRightY() < rightSpeed) rightSpeed = MathUtil.limitMin(rightSpeed - ramp, oi.getRightY());
        /*if (drive.isSlipping()) {
        	  
        }*/
        drive.setBoth(leftSpeed, rightSpeed);
        System.out.println("leftSpeed: " + leftSpeed + "  voltageR: " + drive.pdp.getCurrent(0) + "  rightSpeed: " + rightSpeed + "  voltageR: " + drive.pdp.getCurrent(15));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	drive.setBoth(0);
    }

    protected void interrupted() {
    	drive.setBoth(0);
    }
}
