package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.Timer;

public class GearOpenCommand4Time extends CommandBase {
	
	Timer timer;
	
	double time;

    public GearOpenCommand4Time(double time) {
       	requires(gear);
       	timer = new Timer();
       	this.time = time;
    }

    protected void initialize() {
    	RobotLog.putMessage("Running GearOpenCommand4Time");
    	gear.open();
    	timer.start();
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return timer.hasPeriodPassed(time);
    }

    protected void end() {
    	gear.off();
    	timer.stop();
		RobotLog.putMessage("GearOpenCommand4Time finished in " + timer.get() + " seconds.");
    }

    protected void interrupted() {
    	gear.off();
    	timer.stop();
		RobotLog.putMessage("GearCommandOpen4Time was interrupted and ran for " + timer.get() + " seconds.");
    }
}
