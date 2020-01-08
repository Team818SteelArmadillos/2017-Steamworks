package org.usfirst.frc.team818.robot.commands;

public class PiDrive extends CommandBase {
	double speed, pLeft, pRight;
	
	public PiDrive() {
		requires(drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		pLeft = 0;
		pRight = 0;
		drive.resetBothEncoders();	
		drive.resetGyro();
		drive.enablePID("rotate");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		speed = oi.getLeftY();
		pLeft = speed;
		pRight = speed;
		pRight -= drive.getPIDOutputRotate();
		drive.setBoth(pLeft, pRight);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		drive.setBoth(0);
		drive.disablePID();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		drive.setBoth(0);
	
		drive.disablePID();
	}
}
