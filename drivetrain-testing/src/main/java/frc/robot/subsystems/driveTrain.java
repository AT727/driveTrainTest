// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class driveTrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public driveTrain() {
    CANSparkMax frontRight = new CANSparkMax(port0);
    CANSparkMax frontLeft = new CANSparkMax(port1);
    CANSparkMax backRight = new CANSparkMax(port2);
    CANSparkMax backLeft = new CANSparkMax(port3);

    MotorControllerGroup m_left = new MotorControllerGroup(frontLeft, backLeft);
    MotorControllerGroup m_right = new MotorControllerGroup(frontRight, backRight);
    DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
