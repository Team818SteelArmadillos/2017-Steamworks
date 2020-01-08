package org.usfirst.frc.team818.robot.autonomi;

import org.usfirst.frc.team818.robot.commands.Drive4Distance;
import org.usfirst.frc.team818.robot.commands.GearOpenCommand4Time;
import org.usfirst.frc.team818.robot.commands.WaitingCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleGear extends CommandGroup {
    
    public MiddleGear() {
        addSequential(new Drive4Distance(0.3, 90));
        addSequential(new WaitingCommand(1.0));
        addSequential(new GearOpenCommand4Time(1.0));
        addSequential(new WaitingCommand(1.0));
        addSequential(new Drive4Distance(0.3, -10));
        
        
    }
}
