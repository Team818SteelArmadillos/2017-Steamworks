package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpticalReceptors extends Command {

	public OpticalReceptors() {

	}

	protected void initialize() {
		try {

			RobotLog.putMessage("   __________       __________     ");
			RobotLog.putMessage("  / ____     \\     / ____     \\   ");
			RobotLog.putMessage(" / /    \\     \\   / /    \\     \\  ");
			RobotLog.putMessage(" | | O  |     |   | | O  |     | ");
			RobotLog.putMessage(" \\ \\    /     /   \\ \\    /     /  ");
			RobotLog.putMessage("  \\ ̅ ̅ ̅ ̅      /     \\ ̅ ̅ ̅ ̅      /   ");
			RobotLog.putMessage("   ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅        ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅     ");
			
/*
 		RobotLog.putMessage("   __________       __________     ");
		RobotLog.putMessage("  / ____     \     / ____     \   ");
		RobotLog.putMessage(" / /    \     \   / /    \     \  ");
		RobotLog.putMessage(" | | O  |     |   | | O  |     | ");
		RobotLog.putMessage(" \ \    /     /   \ \    /     /  ");
		RobotLog.putMessage("  \ ̅ ̅ ̅ ̅      /     \ ̅ ̅ ̅ ̅      /   ");
		RobotLog.putMessage("   ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅        ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅ ̅     ");
	
 */
			
		} catch (Exception e) {
			RobotLog.putMessage("Taif is a bad programmer.");
		}
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
