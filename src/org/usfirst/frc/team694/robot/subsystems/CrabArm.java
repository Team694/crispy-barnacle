package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CrabArm extends Subsystem {
    public Solenoid crabArmSolenoid;
    public WPI_VictorSPX leftCrabArmMotor;
    public WPI_VictorSPX rightCrabArmMotor;
    public SpeedControllerGroup crabArmMotors;

    public CrabArm() {
        crabArmSolenoid = new Solenoid(RobotMap.CRAB_ARM_SOLENOID_PORT);
        leftCrabArmMotor = new WPI_VictorSPX(RobotMap.CRAB_ARM_LEFT_MOTOR_PORT);
        rightCrabArmMotor = new WPI_VictorSPX(RobotMap.CRAB_ARM_RIGHT_MOTOR_PORT);
        leftCrabArmMotor.setInverted(true);
        crabArmMotors = new SpeedControllerGroup(leftCrabArmMotor, rightCrabArmMotor);
    }

    public void release() {
        crabArmSolenoid.set(true);
    }

    public void acquire() {
        crabArmMotors.set(1);
    }

    public void deacquire() {
        crabArmMotors.set(-1);
    }

    public void initDefaultCommand() {
    }
}