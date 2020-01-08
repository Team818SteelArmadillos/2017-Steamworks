package org.usfirst.frc.team818.robot.subsystems;

import org.usfirst.frc.team818.robot.commands.TankCommand;
import org.usfirst.frc.team818.robot.utilities.DoublePIDOutput;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

public class DriveSubsystem extends Subsystem {
	
	public PowerDistributionPanel pdp;

	Talon[] leftMotors;
	Talon[] rightMotors;

	AnalogGyro driveGyro;
	
	BuiltInAccelerometer accel;

	Encoder leftEncoder, rightEncoder;
	
	public double rampFactor;
	
	private static final double[] STRAIGHT_PID_VALUES = { 0.01, 0.001, 0 };
	private static final double[] STRAIGHT_PID_RANGE = { -1, 1 };
	private static final double[] ROTATE_PID_VALUES = { 0.05, 0, 0.1 };
	private static final double[] ROTATE_PID_RANGE = { -0.4, 0.4 };
	
	private static final double ROTATE_PID_TOLERANCE = 1;
	
	private PIDController straightControllerLeft, straightControllerRight, rotateController;
	private DoublePIDOutput pidOutputRotate, pidOutputRight, pidOutputLeft;

	private boolean driveEnabled;
//	private double PIDOutput;
	
	public DriveSubsystem(int[] leftMotorPorts, int[] rightMotorPorts, int gyroPort, int[] leftEncoderPorts,
			int[] rightEncoderPorts, boolean driveEnabled) {
		
//		super(0.1, 0.001, 0); //if extends PIDSubsystem
		
		this.driveEnabled = driveEnabled;

		if (driveEnabled) {
			leftMotors = new Talon[leftMotorPorts.length];
			rightMotors = new Talon[rightMotorPorts.length];

			leftEncoder = new Encoder(leftEncoderPorts[0], leftEncoderPorts[1]);
			rightEncoder = new Encoder(rightEncoderPorts[0], rightEncoderPorts[1]);

			for (int i = 0; i < leftMotorPorts.length; i++)
				leftMotors[i] = new Talon(leftMotorPorts[i]);

			for (int i = 0; i < rightMotorPorts.length; i++)
				rightMotors[i] = new Talon(rightMotorPorts[i]);
			
			pdp = new PowerDistributionPanel();
			driveGyro = new AnalogGyro(gyroPort);
			accel = new BuiltInAccelerometer();
			rampFactor = 0.03;
		}
		
		pidOutputRight = new DoublePIDOutput();
		pidOutputLeft = new DoublePIDOutput();
		pidOutputRotate = new DoublePIDOutput();
		
		//right side 
		straightControllerRight = new PIDController(STRAIGHT_PID_VALUES[0], STRAIGHT_PID_VALUES[1], STRAIGHT_PID_VALUES[2], rightEncoder, pidOutputRight);
		straightControllerRight.setOutputRange(STRAIGHT_PID_RANGE[0], STRAIGHT_PID_RANGE[1]);
		straightControllerRight.setSetpoint(0);
		straightControllerRight.setContinuous(false);
		
		//left side
		straightControllerLeft = new PIDController(STRAIGHT_PID_VALUES[0], STRAIGHT_PID_VALUES[1], STRAIGHT_PID_VALUES[2], leftEncoder, pidOutputLeft);
		straightControllerLeft.setOutputRange(STRAIGHT_PID_RANGE[0], STRAIGHT_PID_RANGE[1]);
		straightControllerLeft.setSetpoint(0);
		straightControllerLeft.setContinuous(false);
		
		rotateController = new PIDController(ROTATE_PID_VALUES[0], ROTATE_PID_VALUES[1], ROTATE_PID_VALUES[2], driveGyro, pidOutputRotate);
		rotateController.setOutputRange(ROTATE_PID_RANGE[0], ROTATE_PID_RANGE[1]);
		rotateController.setAbsoluteTolerance(ROTATE_PID_TOLERANCE);
		rotateController.setContinuous();
		rotateController.setSetpoint(0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankCommand());
	}

	public void setLeft(double speed) {
		if (driveEnabled) {
			for (int i = 0; i < leftMotors.length; i++)
				leftMotors[i].set(-speed);
		}
	}

	public void setRight(double speed) {
		if (driveEnabled) {
			for (int i = 0; i < rightMotors.length; i++)
				rightMotors[i].set(speed);
		}
	}

	public void setBoth(double speedLeft, double speedRight) {
		if (driveEnabled) {
			setLeft(speedLeft);
			setRight(speedRight);
		}
	}

	public void setBoth(double speed) {
		if (driveEnabled) {
			setLeft(speed);
			setRight(speed);
		}
	}

	public void resetGyro() {
		if (driveEnabled) {
			driveGyro.reset();
		}
	}

	public double getAngle() {
		if (driveEnabled) {
			return driveGyro.getAngle() % 360;
		} else
			return 0;
	}

	public int getLeftRotation() {
		if (driveEnabled) {
			return leftEncoder.get();
		} else
			return 0;
	}

	public int getRightRotation() {
		if (driveEnabled) {
			return rightEncoder.get();
		} else
			return 0;
	}

	public void resetBothEncoders() {
		if (driveEnabled) {
			rightEncoder.reset();
			leftEncoder.reset();
		}
	}

	public boolean getLeftDirection() {
		if (driveEnabled) {
			return leftEncoder.getDirection();
		} else
			return false;

	}

	public boolean getRightDirection() {
		if (driveEnabled) {
			return rightEncoder.getDirection();
		} else
			return false;

	}
	
	/*public boolean isSlipping() {
		if (pdp.getCurrent(15) < slipVal) 
			return true;
		else 
			return false;
	}*/
	
	//************************** PID STUFF STARTS RIGHT HERE ****************************
	
	//this chunk is for StraightController
	
	public void enablePID(String pidType) {
		if (driveEnabled) {
			if (pidType.equals("straight")) {
				if (rotateController.isEnabled())
					rotateController.disable();
				if (!straightControllerRight.isEnabled())
					straightControllerRight.enable();
				if(!straightControllerLeft.isEnabled())
					straightControllerLeft.enable();
			} else if (pidType.equals("rotate")) {
				if (straightControllerRight.isEnabled())
					straightControllerRight.disable();
				if (straightControllerLeft.isEnabled())
					straightControllerLeft.disable();
				if (!rotateController.isEnabled())
					rotateController.enable();
			}
		}
	}
	
	public void disablePID() {
		if (driveEnabled) {
			if (rotateController.isEnabled())
				rotateController.disable();
			if (straightControllerRight.isEnabled())
				straightControllerRight.disable();
			if (straightControllerLeft.isEnabled())
				straightControllerLeft.disable();			
		}
	}
	
	public void setRotatePoint(double angle) {
																																																																																																												if (driveEnabled)
			rotateController.setSetpoint(angle);
	}
	
	public boolean rotateOnTarget() {
		return (driveEnabled) ? rotateController.onTarget() : true;
	}
	
	public double getPIDOutputRotate() {
		return (driveEnabled) ? pidOutputRotate.get() : 0;
	}
	
	public double getPIDOutputLeft() {
		return (driveEnabled) ? pidOutputLeft.get() : 0;
	}
	
	public double getPIDOutputRight() {
		return (driveEnabled) ? pidOutputRight.get() : 0;
	}
	
	public void setRotatePID(double p, double i, double d) {
		rotateController.setPID(p, i, d);
	}

//	****************if PIDSubsystem is extended****************************************
	
//	@Override
//	protected double returnPIDInput() {
//		return (driveEnabled) ? getLeftRotation()-getRightRotation() : 0;
//	}
//	
//	@Override
//	protected void usePIDOutput(double output) {
//		PIDOutput = output;
//	}
}