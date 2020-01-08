package org.usfirst.frc.team818.robot.commands;

public class TurnRightSide extends CommandBase {

	public double angle;
	private double speed = 0.2;

	public TurnRightSide(double angle, double speedTurn) {
		requires(drive);
		this.angle = angle;
		speed = speedTurn;
	}
	
	public TurnRightSide(double angle) {
		requires(drive);
		this.angle = angle;

	}

	protected void initialize() {
		drive.resetGyro();
	}

	protected void execute() {
		
		drive.setRight(speed);

	}

	protected boolean isFinished() {

		if (drive.getAngle() < angle + 1 && drive.getAngle() > angle - 1) {
			return true;
		} else
			return false;

	}

	protected void end() {

		drive.setBoth(0, 0);

	}

	protected void interrupted() {

		drive.setBoth(0, 0);
	}
}
