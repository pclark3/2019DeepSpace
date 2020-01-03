/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EncoderTurnCommand extends Command {

    private double targetAngle;

  public EncoderTurnCommand(double targetAngle, double seconds) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    requires(Robot.m_driveSubsystem);
    this.targetAngle = targetAngle;
    setTimeout(seconds);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    //Zero encoders
    Robot.m_driveSubsystem.zeroEncoders();

    //Set target
    Robot.m_driveSubsystem.setupOnTarget(500, 20);

    //Set turn angle
    Robot.m_driveSubsystem.setSetPointTurn(targetAngle);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_driveSubsystem.onTarget() || isTimedOut(); 
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_driveSubsystem.drive(0, 0);
    Robot.m_driveSubsystem.zeroEncoders();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
