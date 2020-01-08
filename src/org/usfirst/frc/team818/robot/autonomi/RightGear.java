package org.usfirst.frc.team818.robot.autonomi;

import org.usfirst.frc.team818.robot.commands.Drive4Distance;
import org.usfirst.frc.team818.robot.commands.GearOpenCommand4Time;
import org.usfirst.frc.team818.robot.commands.PiRotate;
import org.usfirst.frc.team818.robot.commands.WaitingCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightGear extends CommandGroup {
	
	public RightGear() {
		addSequential(new Drive4Distance(0.3, 96));// 4 seconds (?)
		addSequential(new WaitingCommand(1.5));
		addSequential(new PiRotate(-60, 0.3));// 3 seconds (?)
		addSequential(new WaitingCommand(0.5));
		addSequential(new Drive4Distance(0.3, 87));// 1 second (?)
		addSequential(new WaitingCommand(0.5));
//		addSequential(new GearOpenCommand4Time(0.5));
//		addSequential(new Drive4Distance(0.3, -30));
//		addSequential(new PiRotate(60, 0.3));

	}

}
