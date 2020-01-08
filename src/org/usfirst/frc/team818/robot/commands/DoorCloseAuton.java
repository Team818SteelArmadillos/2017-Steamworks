package org.usfirst.frc.team818.robot.commands;

public class DoorCloseAuton extends CommandBase {

	public DoorCloseAuton() {
		requires(door);
	}

	protected void initialize() {
		door.close();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {

	}

	protected void interrupted() {

	}
}
