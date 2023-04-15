// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveForTime extends ParallelDeadlineGroup {
  /** Creates a new DriveForTime. */
  public DriveForTime(DriveSubsystem driveSubsystem, CommandXboxController controller, double speed, double time, CommandXboxController drController) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    // Drives the robot at "speed" for "time"
    super(new WaitCommand(time));
    addCommands(
      new ArcadeDriveCommand(driveSubsystem, speed, 0)
    );
  }
}
