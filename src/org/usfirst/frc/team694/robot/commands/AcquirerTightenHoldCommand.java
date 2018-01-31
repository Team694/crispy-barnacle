package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class AcquirerTightenHoldCommand extends InstantCommand {

    public AcquirerTightenHoldCommand() {
        super();
        requires(Robot.acquirer);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.acquirer.holdCube();
    }
}