package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Turn off the brake and move the lift up
 */
public class LiftUpCommand extends Command {

    public LiftUpCommand() {
        requires(Robot.lift);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.lift.setBrakeOff();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.lift.goUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.lift.setBrakeOn();
    }
}
