package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.RobotController;

/**
 *
 */
public class DrivetrainDriveSystemCommand extends DefaultCommand {

    // When we start our auto low gear shift
    private static final double BROWNOUT_PROTECTION_LOWER_BOUND_VOLTAGE = 8;

    // After auto low gear shifting, when do we shift back up
    private static final double BROWNOUT_PROTECTION_UPPER_BOUND_VOLTAGE = 9;

    private boolean isBrownOutProtectionOn;

    private boolean tankDrive;
    private boolean driveModeToggleButtonWasPressed;

    public DrivetrainDriveSystemCommand() {
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        super.initialize();
        tankDrive = false;
        driveModeToggleButtonWasPressed = false;
    }

    @Override
    protected void defaultExecute() {
        //Preliminary Values from the triggers used for Curvature Drive
        double rightTrigger = Robot.oi.driverGamepad.getRawRightTriggerAxis();
        double leftTrigger = Robot.oi.driverGamepad.getRawLeftTriggerAxis();

        //Values Used for Curvature Drive
        double rightTriggerSquared = rightTrigger * Math.abs(rightTrigger);
        double leftTriggerSquared = leftTrigger * Math.abs(leftTrigger);
        double leftJoystickX = Robot.oi.driverGamepad.getLeftX();

        //Values used for Tank Drive
        double rightJoystickY = Robot.oi.driverGamepad.getRightY();
        double leftJoystickY = Robot.oi.driverGamepad.getLeftY();

        if (Robot.oi.driverGamepad.getRawOptionButton() && !driveModeToggleButtonWasPressed) {
            tankDrive = !tankDrive;
        }
        driveModeToggleButtonWasPressed = Robot.oi.driverGamepad.getRawOptionButton();

        // AUTO GEAR SHIFTING to reduce brownouts
        if (!isBrownOutProtectionOn) {
            // If we're already manual gear shifting, ignore this change.
            if (!Robot.oi.driverGamepad.getRawBottomButton() 
              && RobotController.getBatteryVoltage() < BROWNOUT_PROTECTION_LOWER_BOUND_VOLTAGE) {
                isBrownOutProtectionOn = true;
                Robot.drivetrain.enableBrownOutProtection();
                //Robot.drivetrain.lowGearShift();
            }
        } else {
            if (RobotController.getBatteryVoltage() > BROWNOUT_PROTECTION_UPPER_BOUND_VOLTAGE) {
                isBrownOutProtectionOn = false;
                // If we're already manual gear shifting, don't override the driver's decision
                if (!Robot.oi.driverGamepad.getRawBottomButton()) {
                    Robot.drivetrain.disableBrownOutProtection();
                    //Robot.drivetrain.highGearShift();
                }
            }
        }


        if (!tankDrive) {
            if (Math.abs(rightTrigger + leftTrigger) > 0.05) {
                Robot.drivetrain.curvatureDrive(rightTriggerSquared - leftTriggerSquared, leftJoystickX, false);
            } else {
                Robot.drivetrain.curvatureDrive(rightTriggerSquared - leftTriggerSquared, leftJoystickX, true);
            }
        } else {
            Robot.drivetrain.tankDrive(leftJoystickY, rightJoystickY);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        
    }
}
