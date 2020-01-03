/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class LimeLightSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  //NetworkTableEntry tx = table.getEntry("tx");
  //NetworkTableEntry ty = table.getEntry("ty");
  //NetworkTableEntry ta = table.getEntry("ta");

  /**
     * tx Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
     * @return
     */
    public double getTX() {
      NetworkTableEntry tx = table.getEntry("tx");
      double x = tx.getDouble(0);
      return x;
  }
  public double getTA() {
    NetworkTableEntry ta = table.getEntry("ta");
    double a = ta.getDouble(0);
    return a;
}
  /**
   * ty Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
   * @return 
   */
  public double getTY() {
      NetworkTableEntry ty = table.getEntry("ty");
      double y = ty.getDouble(0);
      return y;
  }
  /**
     * tv   Whether the limelight has any valid targets (0 or 1)
     * @return
     */
    /*public boolean getTV() {
      NetworkTableEntry tv = table.getEntry("tv");
      double v = tv.getDouble(0);
      if (v == 0.0f){
          return false;
      }else {
          return true;
      }
  }*/

  public double getTV() {
    NetworkTableEntry tv = table.getEntry("tv");
    double v = tv.getDouble(0);
    return v;
}
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
