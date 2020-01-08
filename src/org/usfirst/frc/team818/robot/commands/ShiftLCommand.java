package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShiftLCommand extends CommandBase {

    public ShiftLCommand() {
       	requires(shifter);
    }

    protected void initialize() {
    	
    	SmartDashboard.putBoolean("Check4", false);
    	RobotLog.putMessage("LOW GEAR");
    	if (Math.abs(oi.getRightY()) >= 0.1 && Math.abs(oi.getLeftY()) >= 0.1)
    		shifter.highGear();
    	drive.rampFactor = 0.02;
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	shifter.offGear();
    }

    protected void interrupted() {
    	shifter.offGear();
    }
}
