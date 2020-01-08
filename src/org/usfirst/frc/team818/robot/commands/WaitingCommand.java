package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitingCommand extends Command {
	
	Timer timer = new Timer();
	double time;

    public WaitingCommand(double time) {
		this.time = time;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotLog.putMessage("Waiting...");
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.hasPeriodPassed(time);
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
		RobotLog.putMessage("Stopped waiting and waited for " + timer.get() + " seconds.");

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	timer.stop();
		RobotLog.putMessage("Interrupted and waited for " + timer.get() + " seconds.");
    }
}
