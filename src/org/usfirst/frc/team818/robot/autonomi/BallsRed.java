package org.usfirst.frc.team818.robot.autonomi;

import org.usfirst.frc.team818.robot.commands.DoorCloseAuton;
import org.usfirst.frc.team818.robot.commands.DoorOpenAuton;
import org.usfirst.frc.team818.robot.commands.Drive4Distance;
import org.usfirst.frc.team818.robot.commands.PiRotate;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class BallsRed extends CommandGroup {

 /*   public RightGear() {
    	
    	addSequential(new Drive4Distance(0.4, 61));
    	addSequential(new TurnAngleCommand(-60));
    	addSequential(new AutoAlignCommand());
    	addSequential(new Drive4Distance(0.4, CommandBase.camera.getDistance()));
    	addSequential(new GearOpenCommand4Time(1.0));
    	addSequential(new Drive4Distance(0.4, -10));

    }
   */
	private static final double distanceForward = 24; // CHANGE THIS
	private static final double distanceToBoiler = 23; // CHANGE THIS
	private static final double distanceBack = -18; // CHANGE THIS
	private static final double distanceToBaseline = -93; // CHANGE THIS TOO

	private static final double speed = .4; // CHANGE THIS TOO

	private static final double angle = 130; // CHANGE THIS TOO
	private static final double angleForBaseline = 35; // CHANGE THIS TOO

	public BallsRed() {
		addSequential(new Drive4Distance(speed, distanceForward));
		addSequential(new WaitCommand(1.0));
		addSequential(new PiRotate(angle));
		addSequential(new WaitCommand(1.0));
		addSequential(new Drive4Distance(speed, distanceToBoiler));
		addSequential(new WaitCommand(1.0));
		addSequential(new DoorOpenAuton());
		addSequential(new WaitCommand(3.0));
		addSequential(new DoorCloseAuton());
		addSequential(new WaitCommand(2.0));
		addSequential(new DoorOpenAuton());
		addSequential(new WaitCommand(1.0));
		addSequential(new DoorCloseAuton());
		addSequential(new Drive4Distance(speed, distanceBack));
		addSequential(new PiRotate(angleForBaseline));
		addSequential(new Drive4Distance(speed, distanceToBaseline));
	}
}
