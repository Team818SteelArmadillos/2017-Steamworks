package org.usfirst.frc.team818.robot.subsystems;

import org.usfirst.frc.team818.robot.commands.DoorCloseCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DoorSubsystem extends Subsystem {
	
	private DoubleSolenoid window;
	private boolean doorEnabled;

	/*private static final double[] ROTATE_PID_VALUES = { 0.05, 0, 0.1 };
	private static final double[] ROTATE_PID_RANGE = { -0.4, 0.4 };
	
	private static final double ROTATE_PID_TOLERANCE = 1;
	
	private PIDController rotateController;
	private DoublePIDOutput pidOutput;*/
	
	public DoorSubsystem(int[] doorMotorPorts, int doorPCMPort, boolean doorEnabled){
		
		this.doorEnabled = doorEnabled;
		
		if(doorEnabled) {
			window = new DoubleSolenoid(doorPCMPort, doorMotorPorts[0], doorMotorPorts[1]);
		}
		
		/*rotateController = new PIDController(ROTATE_PID_VALUES[0], ROTATE_PID_VALUES[1], ROTATE_PID_VALUES[2], doorGyro, pidOutput);
		rotateController.setOutputRange(ROTATE_PID_RANGE[0], ROTATE_PID_RANGE[1]);
		rotateController.setAbsoluteTolerance(ROTATE_PID_TOLERANCE);
		rotateController.setContinuous();*/
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new DoorCloseCommand());
    }
    
    public void open() {
    	if(doorEnabled) {
    		window.set(DoubleSolenoid.Value.kReverse);	
    	}
    }
    
    public void close() {
    	if(doorEnabled) {
    		window.set(DoubleSolenoid.Value.kForward);		
    	}
    }
    
}	