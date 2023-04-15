// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.autonomous.scoreDoubleReverse;
import frc.robot.commands.autonomous.scoreReverse;
import frc.robot.commands.autonomous.scoreReverseMid;
import frc.robot.commands.drivetrain.AutoBalanceCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExtenderSubsystem;
import frc.robot.subsystems.PinchMotorSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.PinchSubsystem;

public class RobotContainer {

  private final ExtenderSubsystem m_extenderSubsystem = new ExtenderSubsystem();
  private final SendableChooser<Command> autonChooser = new SendableChooser<>();
  private final static CommandXboxController m_drController = new CommandXboxController(0);
  private final static CommandXboxController m_opController = new CommandXboxController(1);
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final ArmSubsystem m_armSubsystem = new ArmSubsystem();
  private final PinchSubsystem m_pinchSubsystem = new PinchSubsystem();
  private final PinchMotorSubsystem m_pinchMotorSubsystem = new PinchMotorSubsystem();
  private final AutoBalance m_autoBalance = new AutoBalance();

  private final scoreDoubleReverse m_ScoreDoubleReverse = new scoreDoubleReverse(
    m_armSubsystem, m_driveSubsystem, m_pinchSubsystem, m_drController, .7, 4.7, .3, 1, 2.9);
  private final scoreReverseMid m_ScoreReverseMid = new scoreReverseMid(
    m_driveSubsystem, m_pinchSubsystem, m_armSubsystem, m_extenderSubsystem, m_drController, .7, 1, .3, 3);
  private final scoreReverse m_ScoreReverseCS = new scoreReverse(
    m_driveSubsystem, m_pinchSubsystem, m_armSubsystem, m_drController, .7, .3, 4);
  private final scoreReverse m_ScoreReverseOff = new scoreReverse(
    m_driveSubsystem, m_pinchSubsystem, m_armSubsystem, m_drController, .7, .3, 5);
  private final scoreReverse m_ScoreReverse = new scoreReverse(
    m_driveSubsystem, m_pinchSubsystem, m_armSubsystem, m_drController, .7, .3, 3);
  private final scoreReverse m_scoreOnly = new scoreReverse(
    m_driveSubsystem, m_pinchSubsystem, m_armSubsystem, m_drController, 0, .3, 0);
  private final AutoBalanceCommand m_Balance = new AutoBalanceCommand(
    m_driveSubsystem, m_autoBalance, 1);

  public RobotContainer() {
    SmartDashboard.putData("Autonomous Chooser", autonChooser);

    CameraServer.startAutomaticCapture().setResolution(360, 360);

    autonChooser.addOption("Score leave then balance", m_ScoreDoubleReverse);
    autonChooser.addOption("Score mid then reverse", m_ScoreReverseMid);
    autonChooser.addOption("Score then balance", m_ScoreReverseCS);
    autonChooser.addOption("Score then leave over chargestation", m_ScoreReverseOff);
    autonChooser.setDefaultOption("Score then leave", m_ScoreReverse);
    autonChooser.addOption("Score only", m_scoreOnly);
    autonChooser.addOption("Balance", m_Balance);
    autonChooser.addOption("Do Nothing", null);

    configureBindings();
  }

  private void configureBindings() {
   // m_opController.a().whileTrue(Commands.startEnd(m_extenderSubsystem::retractArm, m_extenderSubsystem::stopExtender, m_extenderSubsystem));
   // m_opController.y().whileTrue(Commands.startEnd(m_extenderSubsystem::extendArm, m_extenderSubsystem::stopExtender, m_extenderSubsystem));
    // Intake pinch control
    m_opController.rightTrigger().toggleOnTrue(Commands.startEnd(m_pinchSubsystem::pinchTrue, m_pinchSubsystem::pinchFalse, m_pinchSubsystem));
    // WIP Lift setpoint subsystem command mapping
    m_opController.povUp().whileTrue(Commands.startEnd(() -> m_armSubsystem.lowerArm(.7), m_armSubsystem::stopLift, m_armSubsystem));
    m_opController.povDown().whileTrue(Commands.startEnd(() -> m_armSubsystem.raiseArm(.7), m_armSubsystem::stopLift, m_armSubsystem));

    m_opController.leftBumper().whileTrue(Commands.startEnd(m_pinchMotorSubsystem::IntakeIn, m_pinchMotorSubsystem::stopMotors, m_pinchMotorSubsystem));
    m_opController.rightBumper().whileTrue(Commands.startEnd(m_pinchMotorSubsystem::IntakeOut, m_pinchMotorSubsystem::stopMotors, m_pinchMotorSubsystem));
    
    m_opController.start().toggleOnTrue(Commands.startEnd(m_extenderSubsystem::highSoft, m_extenderSubsystem::midSoft, m_extenderSubsystem));
  }

  public Command getAutonomousCommand() {
    return autonChooser.getSelected();
  }

  public static CommandXboxController drController() {
    return m_drController;
  }

  public static CommandXboxController opController() {
    return m_opController;
  }

  public void periodic() {

  }
}
