package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ControlDriver extends Command {

    public ControlDriver() {
       
    }

    protected void initialize() {
    	
    	RobotLog.putMessage("******Driver Controls**********");
    	RobotLog.putMessage("Ramping override = Left stick, 1 (whenPressed)");
    	RobotLog.putMessage("Dynamic braking = Left stick, 2 (whileHeld)");
    	RobotLog.putMessage("Align = Left stick, 3 (whenPressed)");
    	RobotLog.putMessage("Jog back = Left stick, 4 (whileHeld)");
    	RobotLog.putMessage("Drive straight/Swerve drive = Left stick, 5 (whileHeld)");
    	RobotLog.putMessage("Climber override = Left stick, 6 & 7 (whileActive)");
    	RobotLog.putMessage("Dom command = Right stick 6 & 7, Left stick 8 & 9 (whileActive)");
    	
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
