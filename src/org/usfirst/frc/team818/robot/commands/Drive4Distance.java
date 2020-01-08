package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.Constants;
import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive4Distance extends CommandBase {

	double d, speed, targetSpeed;
	int leftTicks, rightTicks;
	double pLeft, pRight;
	Timer timee;

	public Drive4Distance(double speed, double distance) {
		requires(drive);
		timee = new Timer();
		d = distance;
		this.speed = speed;
		// speed = 0;
		if (d < 0) {
			this.speed = -speed;
		}
	}

	protected void initialize() {
		RobotLog.putMessage("Running Drive4Distance.");
		drive.setBoth(0);
		drive.resetBothEncoders();
		drive.resetGyro();
		timee.start();
		
		pLeft = 0;
		pRight = 0;

		drive.enablePID("rotate");
		drive.setRotatePoint(0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	
		leftTicks = Math.abs(drive.getLeftRotation());
		rightTicks = Math.abs(drive.getRightRotation());
		
		SmartDashboard.putNumber("Data1", leftTicks);
		SmartDashboard.putNumber("Data2", rightTicks);
		SmartDashboard.putNumber("Number1", drive.getAngle());

//		****Old drivestraight*******
//		if (leftTicks == 0 || rightTicks == 0)
//			drive.setBoth(speed/* * direct*/);
//		else
//			drive.setBoth(MathUtil.setLimits((rightTicks / leftTicks) * speed/* * direct*/, -1.0, 1.0),
//					MathUtil.setLimits((leftTicks / rightTicks) * speed/* * direct*/, -1.0, 1.0));
		pLeft = speed;
		pRight = speed;
		pRight -= drive.getPIDOutputRotate();
		drive.setBoth(pLeft, pRight);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (timee.hasPeriodPassed(5)
				|| (Math.abs(Constants.cycleDistance * ((leftTicks + rightTicks / 2) / 360)) >= Math.abs(d))) {
			return true;
		} else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotLog.putMessage("Stopped driving.");
		drive.setBoth(0);
		drive.disablePID();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		RobotLog.putMessage("Interrupted driving.");
		drive.setBoth(0);
		drive.disablePID();
	}
}
