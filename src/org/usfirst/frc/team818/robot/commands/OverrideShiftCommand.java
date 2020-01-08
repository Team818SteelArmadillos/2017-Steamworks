package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OverrideShiftCommand extends CommandBase {
    
    public OverrideShiftCommand() {
       	requires(drive);
    }

    protected void initialize() {
    	drive.setBoth(0);
    	SmartDashboard.putBoolean("Check1", false);
    	RobotLog.putMessage("Ramping OFF");
    }

    protected void execute() {
        drive.setBoth(oi.getLeftY(), oi.getRightY());
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
