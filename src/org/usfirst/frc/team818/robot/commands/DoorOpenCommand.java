package org.usfirst.frc.team818.robot.commands;

public class DoorOpenCommand extends CommandBase {

	public DoorOpenCommand() {
		requires(door);
	}

	protected void initialize() {
		door.open();
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
