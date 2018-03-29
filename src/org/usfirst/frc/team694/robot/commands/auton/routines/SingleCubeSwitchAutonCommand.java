package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SingleCubeSwitchAutonCommand extends CommandGroup {

    public SingleCubeSwitchAutonCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        
        addParallel(new LiftMoveToHeightCommand(0.0)); //TODO: Find a height for the lift to move to.
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.6, 10.0));
        //addSequential(new ArcCommand(90.0)); TODO: Use an arc command here when we get to making one.
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.6, 42.0));
        //addSequential(new ArcCommand(0.0));
        addSequential(new DriveStraightPIDCommand(72.0, 0.6));
        addSequential(new DeacquireCommand();)
        
        
    }
}