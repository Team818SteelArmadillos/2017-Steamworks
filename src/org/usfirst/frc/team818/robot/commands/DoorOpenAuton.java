package org.usfirst.frc.team818.robot.commands;

public class DoorOpenAuton extends CommandBase {

	public DoorOpenAuton() {
		requires(door);
	}

	protected void initialize() {
		door.open();
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
