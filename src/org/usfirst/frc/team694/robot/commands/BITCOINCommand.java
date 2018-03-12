    package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BITCOINCommand extends CommandGroup {

    // Once we get the cube in the grabber, 
//    private static final double SECONDS_TO_MOVE_CUBE_UP_AFTER_GRABBING = 0.6;
    private boolean initialBITCOINAutomation;

    public BITCOINCommand() {

        setInterruptible(false);

        // Get the cube in the grabber
        addSequential(new LiftMoveToBottomCommand());
        addSequential(new QuisitorOpenCommand());

        // TODO: Fix Magic Numbers
        addParallel(new QuisitorMoveSpeedCommand(0.25), RobotMap.PRE_FLIP_WAIT_TIME + RobotMap.POST_FLIP_WAIT_TIME + 0.75);
        addSequential(new FlapAndFlipUpCommand());

//        addSequential(new SpatulaFlipUpCommand());

        //addSequential(new WaitCommand(0.25));

        //addSequential(new GrabberCloseCommand());

        //addSequential(new WaitCommand(0.5));

        // Move the cube up a little to get it out of the spatula (not supposed to be used anymore)
        //addParallel(new SpatulaMoveSpeedCommand(-0.4 / 5.0), SECONDS_TO_MOVE_CUBE_UP_AFTER_GRABBING);
        //addSequential(new LiftMoveSpeedCommand(0.4), SECONDS_TO_MOVE_CUBE_UP_AFTER_GRABBING);
    }

    @Override
    protected void initialize() {
        initialBITCOINAutomation = Robot.quisitor.isBITCOINAutomation;
    }

    @Override
    protected boolean isFinished() {
        return Robot.quisitor.isBITCOINAutomation != initialBITCOINAutomation ||
                super.isFinished();
    }

    @Override
    public void interrupted() {
        System.out.println("[BITCOINCommand] interrupted (NANI???!!)");
    }

    @Override
    public void end() {
        System.out.println("[BITCOINCommand] end");
    }

    @Override
    public void cancel() {
        System.out.println("[BITCOINCommand] cancel");
    }
}