/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import oi.limelightvision.limelight.frc.ControlMode.CamMode;


public class NewLimelightAimRangeCommand extends Command {
   //private double kpDistance = 0.05;
   //private double kpAim = 0.05;
   private double m_moveValue = 0.0;
   private double m_rotateValue = 0.0;
   private boolean m_limeLightHasValidTarget = false; 

// These numbers must be tuned for your Robot!  Be careful!
    final double STEER_K = 0.03;                    // how hard to turn toward the target
    final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
    final double DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
    final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast

  public NewLimelightAimRangeCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.m_driveSubsystem.gLimeLight().setCamMode(CamMode.kvision);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double ty = Robot.m_limeLightSubsystem.getTY();
    double tx = Robot.m_limeLightSubsystem.getTX();
    double tv = Robot.m_limeLightSubsystem.getTV();
    double ta = Robot.m_limeLightSubsystem.getTA();

    //boolean targetFound = Robot.m_limeLightSubsystem.getIsTargetFound();

    if(tv < 1.0){
      m_limeLightHasValidTarget = false;
      m_moveValue = 0.0;
      m_rotateValue = 0.0;
      return;
    }
      m_limeLightHasValidTarget = true;
    

      // Start with proportional steering
      double steer_cmd = tx * STEER_K;
      m_rotateValue = steer_cmd;

      // try to drive forward until the target area reaches our desired area
      double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

      // don't let the robot drive too fast into the goal
      if (drive_cmd > MAX_DRIVE)
      {
        drive_cmd = MAX_DRIVE;
      }
      m_moveValue = drive_cmd;

      if (m_limeLightHasValidTarget){
        Robot.m_driveSubsystem.drive(m_moveValue, m_rotateValue);
      } else {
        Robot.m_driveSubsystem.drive(0.0, 0.0);
      }
    
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
