package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ControlOperator extends Command {

    public ControlOperator() {
       
    }

    protected void initialize() {
    
    	RobotLog.putMessage("******Operator Controls**********");
    	RobotLog.putMessage("Climber backDrive = X/1 (whileHeld)");
    	RobotLog.putMessage("Door close = A/2 (whenPressed)");
    	RobotLog.putMessage("Operator Controls = B/3");
    	RobotLog.putMessage("Door open = Y/4 (whenPressed)");
    	RobotLog.putMessage("Intake in = Left Bumper/5 (whileHeld)");
    	RobotLog.putMessage("Intake out = Right Bumper/6 (whileHeld)");
    	RobotLog.putMessage("Arms closed = Left Trigger/7 (whenPressed)");
    	RobotLog.putMessage("Arms open = Right Trigger/8 (whenPressed)");
    	RobotLog.putMessage("Drop Climber = Back/9 (whenPressed)");
    	RobotLog.putMessage("Spin Climber = Start/10 (whileHeld)");
    	
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
