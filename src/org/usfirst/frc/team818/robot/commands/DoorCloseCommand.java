package org.usfirst.frc.team818.robot.commands;

public class DoorCloseCommand extends CommandBase {

	public DoorCloseCommand() {
		requires(door);
	}

	protected void initialize() {
		door.close();
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
