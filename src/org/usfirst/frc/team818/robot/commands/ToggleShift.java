package org.usfirst.frc.team818.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ToggleShift extends CommandGroup {
	
	boolean flip = true;

	public ToggleShift() {
		if (flip) {
			addSequential(new ShiftLCommand());
			flip = !flip;
		} else {
			addSequential(new ShiftHCommand());
			flip = !flip;
		}
	}
}
