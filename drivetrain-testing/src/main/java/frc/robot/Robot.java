// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Joystick;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;  
  private RobotContainer m_robotContainer;

    CANSparkMax frontLeft = new CANSparkMax(Constants.motor1Port,  MotorType.kBrushless);
    CANSparkMax middleLeft = new CANSparkMax(Constants.motor2Port,  MotorType.kBrushless);
    CANSparkMax backLeft = new CANSparkMax(Constants.motor3Port,  MotorType.kBrushless);
    CANSparkMax frontRight = new CANSparkMax(Constants.motor4Port,  MotorType.kBrushless);
    CANSparkMax middleRight = new CANSparkMax(Constants.motor5Port,  MotorType.kBrushless);
    CANSparkMax backRight = new CANSparkMax(Constants.motor6Port,  MotorType.kBrushless);

    SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeft, middleLeft, backLeft);
    SpeedControllerGroup m_right = new SpeedControllerGroup(frontRight, middleRight, backRight);
  
    //reverse the motors if needed
    m_left.setInverted(false);
    m_right.setInverted(false);
  

    DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

    Joystick controller = new Joystick (0);
    double yAxis = controller.getRawAxis(1);
    double xAxis = controller.getRawAxis(5);

  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    m_drive.tankDrive(yAxis, xAxis);


    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
