/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import oi.limelightvision.limelight.frc.ControlMode.CamMode;


public class LimelightAimCommand extends Command {
   private double kpAim = 0.035;
   private double m_moveValue;
   private double m_rotateValue;

  public LimelightAimCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_driveSubsystem.gLimeLight().setCamMode(CamMode.kvision);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double tx = Robot.m_driveSubsystem.gLimeLight().getdegRotationToTarget();
    boolean targetFound = Robot.m_driveSubsystem.gLimeLight().getIsTargetFound();

    if(targetFound){
      m_moveValue = 0;
      m_rotateValue = tx * kpAim;
    }else{
      m_moveValue = 0;
      m_rotateValue = 0;
    }
    Robot.m_driveSubsystem.drive(m_moveValue, m_rotateValue);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_driveSubsystem.drive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
