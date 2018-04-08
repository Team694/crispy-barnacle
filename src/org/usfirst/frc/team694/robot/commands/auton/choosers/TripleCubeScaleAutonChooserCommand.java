package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.TripleCubeScaleAutonCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class TripleCubeScaleAutonChooserCommand extends ConditionalCommand {

    public TripleCubeScaleAutonChooserCommand() {
        super(new TripleCubeScaleAutonCommand(true), new TripleCubeScaleAutonCommand(false));
    }

    @Override
    public boolean condition() {
        return Robot.isRobotStartingOnRight();
    }
}