package org.usfirst.frc.team818.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a good example of how to use commands. It is necessary that each of
 * these commands extends CommandBase, as this we are otherwise unable to
 * reference the required subsystem "drive".
 *
 */
public class AutoAlignCommand extends CommandBase {

	double speed, calcTarget, targetAngle, target;

	//TODO: Create a test run mode that writes a file to the roboRIO for calibration settings
	//TODO: Read those settings into ViableVision
	
	public AutoAlignCommand() {
		requires(drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		drive.setBoth(0);
		drive.resetGyro();

		speed = 0.3;
		
		camera.resetTarget();
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
		
		if (!(calcTarget > 310 && calcTarget < 330)) {
			if (calcTarget > 320)
				target = calcTarget - 320;
			else
				//target = camera.getTarget();
				target = -(320 - calcTarget);
			
			//targetAngle = target/240;
			targetAngle = (target/320) * 30;
		} else {
			targetAngle = drive.getAngle();
		}
		SmartDashboard.putNumber("Number1", targetAngle);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		if (targetAngle < -2.5) {
			drive.setBoth(-speed, speed);
		} else if (targetAngle > 2.5) {
			drive.setBoth(speed, -speed);
		} else {
			end();
		}
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return  (drive.getAngle() > targetAngle-2.5 && drive.getAngle() < targetAngle+2.5);
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
