/******Driver Controls**********
Driving: Left and Right Joystick
Ramping override = Left stick, 1 (toggleWhenPressed)
Dynamic braking = Left stick, 2 (whileHeld)
Align = Left stick, 3 (whenPressed)
Jog back = Left stick, 4 (whileHeld)
Drive straight/Swerve drive = Right stick, 2 (whileHeld)
Climber override = Left stick, 6 & 7 (whileActive)
Dom command = Right stick 6 & 7, Left stick 8 & 9 (whileActive)

******Operator Controls**********
Climber backDrive = 1 (whileHeld)
Door close = 2 (whenPressed)
UNUSED BUTTON = 3
Door open = 4 (whenPressed)
Intake in = 5 (whileHeld)
Intake out = 6 (whileHeld)
Arms closed = 7 (whenPressed)
Arms open = 8 (whenPressed)
Drop Climber = 9 (whenPressed)
Spin Climber = 10 (whileHeld)
*/

package org.usfirst.frc.team818.robot;

import org.usfirst.frc.team818.robot.commands.AutoAlignCommand;
import org.usfirst.frc.team818.robot.commands.ClimberSpinCommand;
import org.usfirst.frc.team818.robot.commands.DomCommand;
import org.usfirst.frc.team818.robot.commands.DoorCloseCommand;
import org.usfirst.frc.team818.robot.commands.DoorOpenCommand;
import org.usfirst.frc.team818.robot.commands.DropCommand;
import org.usfirst.frc.team818.robot.commands.DynamicBraking;
import org.usfirst.frc.team818.robot.commands.GearCloseCommand;
import org.usfirst.frc.team818.robot.commands.GearOpenCommand;
import org.usfirst.frc.team818.robot.commands.IntakeInCommand;
import org.usfirst.frc.team818.robot.commands.IntakeOutCommand;
import org.usfirst.frc.team818.robot.commands.OverrideShiftCommand;
import org.usfirst.frc.team818.robot.commands.ShiftLCommand;
import org.usfirst.frc.team818.robot.commands.SlowReverseCommand;
import org.usfirst.frc.team818.robot.commands.SwerveDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class OI {

	private Joystick leftStick;
	private Joystick rightStick;
	private Joystick gamepad;
	private JoystickButton buttonOpen;
	private JoystickButton buttonClose;
	private JoystickButton buttonGearClose;
	private JoystickButton buttonGearOpen;
	private JoystickButton driveStright;
	private JoystickButton jogBack;
	private JoystickButton buttonClimber;
	private JoystickButton buttonDropClimber;
	private Trigger buttonClimberOverride;
	private Trigger buttonDom;
	private JoystickButton buttonShiftGear;
	private JoystickButton buttonRampingOverride;
	private JoystickButton buttonDynamicBrake;
	private JoystickButton buttonAlign;
//	private JoystickButton backDrive;
	private JoystickButton intakeIn;
	private JoystickButton intakeOut;
//	private JoystickButton opticalReceptor;
//	private JoystickButton controlsDriver;
//	private JoystickButton controlsOperator;
	public static double k = 5450;

	public OI() {

		leftStick = new Joystick(Constants.leftJoystickPort);
		rightStick = new Joystick(Constants.rightJoystickPort);
		gamepad = new Joystick(Constants.gamepadPort);
		buttonOpen = new JoystickButton(gamepad, 4);
		buttonClose = new JoystickButton(gamepad, 2);
		buttonGearClose = new JoystickButton(gamepad, 7);
		buttonGearOpen = new JoystickButton(gamepad, 8);
		driveStright = new JoystickButton(rightStick, 2);
		jogBack = new JoystickButton(leftStick, 4);
		buttonShiftGear = new JoystickButton(rightStick, 1);
		buttonDynamicBrake = new JoystickButton(leftStick, 2);
		buttonAlign = new JoystickButton(leftStick, 3);
		buttonRampingOverride = new JoystickButton(leftStick, 1);
//		backDrive = new JoystickButton(gamepad, 1);
		intakeIn = new JoystickButton(gamepad, 5);
		intakeOut = new JoystickButton(gamepad, 6);
//		opticalReceptor = new JoystickButton(rightStick, 11);
//		controlsDriver = new JoystickButton(rightStick, 10);
//		controlsOperator = new JoystickButton(gamepad, 3);
		
		buttonDropClimber = new JoystickButton(gamepad, 9);
		buttonClimber = new JoystickButton(gamepad, 10);
		
		
		buttonClimberOverride = new Trigger() {

			public boolean get() {
				return leftStick.getRawButton(6) && leftStick.getRawButton(7);
			}

		};
		
		buttonDom = new Trigger() {

			public boolean get() {
				return rightStick.getRawButton(6) && rightStick.getRawButton(7) && leftStick.getRawButton(8) && leftStick.getRawButton(9);
			}

		};

		// buttons
		buttonDynamicBrake.whileHeld(new DynamicBraking());
		buttonShiftGear.toggleWhenPressed(new ShiftLCommand());
	//	buttonShiftGear.whenReleased(new ShiftHCommand());
		buttonOpen.whenPressed(new DoorOpenCommand());
		buttonClose.whenPressed(new DoorCloseCommand());
		buttonGearOpen.whenPressed(new GearOpenCommand());
		buttonGearClose.whenPressed(new GearCloseCommand());
		buttonAlign.whenPressed(new AutoAlignCommand());
		buttonRampingOverride.toggleWhenPressed(new OverrideShiftCommand());
		driveStright.whileHeld(new SwerveDrive());
		jogBack.whileHeld(new SlowReverseCommand());
//		backDrive.whileHeld(new ClimberSpinReverseCommand());
		IntakeInCommand incmd = new IntakeInCommand();
		IntakeOutCommand outcmd = new IntakeOutCommand();
		intakeIn.toggleWhenPressed(incmd);
		intakeOut.toggleWhenPressed(outcmd);
		// triggers
		ClimberSpinCommand spin = new ClimberSpinCommand();
		buttonClimber.whileHeld(spin);
		buttonDropClimber.whenPressed(new DropCommand());
		buttonClimberOverride.whileActive(new ClimberSpinCommand(true));
		buttonDom.whenActive(new DomCommand());
		
//		opticalReceptor.whenPressed(new OpticalReceptors());
//		controlsDriver.whenPressed(new ControlDriver());
//		controlsOperator.whenPressed(new ControlOperator());
	}

	public double getLeftY() {

		return (Math.abs(leftStick.getY()) > 0.1) ? -leftStick.getY() : 0;

	}

	public double getRightY() {

		return (Math.abs(rightStick.getY()) > 0.1) ? -rightStick.getY() : 0;

	}

	public double getLeftX() {

		return (Math.abs(leftStick.getX()) > 0.1) ? -leftStick.getX() : 0;

	}

	public double getRightX() {

		return (Math.abs(rightStick.getX()) > 0.1) ? -rightStick.getX() : 0;

	}	
	
	public double getGamepadLeftY() {

		return (Math.abs(gamepad.getRawAxis(1)) > 0.1) ? -gamepad.getRawAxis(1) : 0;
	}

	public double getGamepadRightY() {

		return (Math.abs(gamepad.getRawAxis(3)) > 0.1) ? -gamepad.getRawAxis(3) : 0;

	}

}
