// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.drivetrain.Brakes;
import frc.robot.commands.drivetrain.speedcontrol.Boost;
import frc.robot.commands.drivetrain.speedcontrol.Slow;
import frc.robot.subsystems.DriveTrain;

public class RobotContainer {

  public static XboxController xboxController = new XboxController(OIConstants.xboxController);
  public static DriveTrain m_drivetrain = new DriveTrain();
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    
    m_drivetrain.setDefaultCommand(new ArcadeDrive());
    new JoystickButton(xboxController, 5).whileTrue(new Slow());
    new JoystickButton(xboxController, 6).whileTrue(new Boost());
    new JoystickButton(xboxController, 1).toggleOnTrue(new Brakes());

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  public void teleopPeriodic() {
  }
  

}
