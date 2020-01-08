package org.usfirst.frc.team818.robot.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * This is a good example of how to use commands. It is necessary that each of
 * these commands extends CommandBase, as this we are otherwise unable to
 * reference the required subsystem "drive".
 *
 */
public class AutoAlignDriveCommand extends CommandBase {

	Timer timer;
	
	double speed, calcTarget, targetAngle, target, leftSpeed, rightSpeed, time;

	//TODO: Create a test run mode that writes a file to the roboRIO for calibration settings
	//TODO: Read those settings into ViableVision
	
	public AutoAlignDriveCommand(double speed, double time) {
		requires(drive);
		this.speed = speed;
		this.time = time;
		timer = new Timer();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		drive.setBoth(0);
		drive.resetGyro();
		timer.start();
		
		calcTarget = camera.getTarget();
		/*
		 * This could be a problem, possibly getting the calculated target angle multiple times.
		if (!(camera.getTarget() > 300 && camera.getTarget() < 340)) {
			if (camera.getTarget() > 320)
				target = camera.getTarget() - 320;
			else
				//target = camera.getTarget();
				target = -(320 - camera.getTarget());
			
			//targetAngle = target/240;
			targetAngle = (target/320) * 30;
		} else {
			targetAngle = drive.getAngle();
		}
		*/
		
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		leftSpeed = 0.7 * speed;
		rightSpeed = 0.7 * speed;
		
		if (camera.getTarget() > 340) {
			leftSpeed += 0.1;
		} else if (camera.getTarget() < 300) {
			rightSpeed += 0.1;
		}
		
		drive.setBoth(leftSpeed, rightSpeed);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return  timer.hasPeriodPassed(time);
	}

	// Called once after isFinished returns true
	protected void end() {
		drive.setBoth(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		drive.setBoth(0);
	}
}
