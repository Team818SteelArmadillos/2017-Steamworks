package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.Timer;

public class PiRotate extends CommandBase {

	public double angle;
	private double speed = 0.3;
	private Timer timer;

	public PiRotate(double angle, double speedTurn) {
		requires(drive);
		this.angle = angle;
		speed = speedTurn;
		timer = new Timer();
	}
	
	public PiRotate(double angle) {
		requires(drive);
		this.angle = angle;
		timer = new Timer();

	}

	protected void initialize() {
		RobotLog.putMessage("Running PiRotate");
		drive.resetGyro();
		drive.setRotatePoint(angle);
		drive.enablePID("rotate");
		timer.start();
	}

	protected void execute() {
		
		speed = drive.getPIDOutputRotate();
		drive.setBoth(speed, -speed); //could be wrong turning

	}

	protected boolean isFinished() {

//		if (drive.getAngle() < angle + 2 && drive.getAngle() > angle - 2) {
//			return true;
//		} else
//			return false;
		
		return drive.rotateOnTarget() || timer.hasPeriodPassed(3);

	}

	protected void end() {
		
		timer.stop();
		RobotLog.putMessage("PiRotate was ended and ran for " + timer.get() + " seconds.");
		drive.disablePID();
		drive.setBoth(0, 0);

	}

	protected void interrupted() {
		
		timer.stop();
		RobotLog.putMessage("PiRotate was interrupted and ran for " + timer.get() + " seconds.");
		drive.disablePID();
		drive.setBoth(0, 0);
	}
}
