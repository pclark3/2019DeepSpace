/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EncoderDriveCommand extends Command {

  private double inches;

  public EncoderDriveCommand(double inches, double seconds) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    requires(Robot.m_driveSubsystem);
    this.inches = inches;
    setTimeout(seconds);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Zero encoders
    Robot.m_driveSubsystem.zeroEncoders();

    //Set distance
    Robot.m_driveSubsystem.setSetPointDrive(inches);

    //Set target
    Robot.m_driveSubsystem.setupOnTarget(300, 10);

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
