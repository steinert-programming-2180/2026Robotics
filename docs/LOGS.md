# Runtime Log List and Analysis

Date: 2026-02-14
Source: Driver Station Log Viewer screenshots provided during debugging.

## Captured Log Lines

1. REV SPARK Flex error
- `ERROR 11 [Spark Flex] IDs: 2, Invalid parameter value, parameter id (113) Velocity Conversion Factor: Factor to convert encoder counts or other velocity units to the desired velocity units, invalid value is nan`

2. Robot startup failure
- `ERROR 1 The robot program quit unexpectedly. This is usually due to a code error.`
- `... at frc.robot.Main.main(Main.java:23) ...`
- `Error at edu.wpi.first.wpilibj.RobotBase.runRobot(RobotBase.java:406): Could not instantiate robot frc.robot.subsystems.SwerveSubsystem!`

3. Context lines around startup
- `Instantiating navX-Sensor on roboRIO Serial Port 1.`
- `NavX: Opening TTL UART serial port ...`

## Interpretation

1. Primary fault path
- SPARK Flex reports a NaN Velocity Conversion Factor on CAN ID 2.
- This indicates invalid drivetrain conversion configuration at startup.

2. Likely config cause in this repo
- `src/main/deploy/swerve/modules/physicalproperties.json` currently contains zeroed conversion inputs:
  - `conversionFactors.angle.gearRatio = 0`
  - `conversionFactors.drive.diameter = 0`
  - `conversionFactors.drive.gearRatio = 0`
- Zero/placeholder values can produce invalid math for conversion setup, which aligns with the NaN conversion-factor error.

3. Why the robot app exits
- `SwerveSubsystem` construction fails during swerve initialization, then WPILib reports:
  - robot program quit unexpectedly
  - could not instantiate robot `frc.robot.subsystems.SwerveSubsystem`

## Correlated Code/Config Locations
- `src/main/java/frc/robot/subsystems/SwerveSubsystem.java`
- `src/main/deploy/swerve/modules/physicalproperties.json`
- `src/main/deploy/swerve/modules/frontleft.json`
- `src/main/deploy/swerve/modules/frontright.json`
- `src/main/deploy/swerve/modules/backleft.json`
- `src/main/deploy/swerve/modules/backright.json`

## Next Validation Logs to Capture After Fix
- Confirm SPARK Flex error 11 no longer appears.
- Confirm robot reaches enabled state without `Could not instantiate robot`.
- Capture one clean startup log and store date/time + git commit.

## Log Update (2026-02-14, REV_20260214_190842)

### Additional Captured Lines
- `java.lang.RuntimeException: Failed to create swerve drive: Invalid parameter value, parameter id`
- `at frc.robot.subsystems.SwerveSubsystem.<init>(SwerveSubsystem.java:30)`
- `at frc.robot.RobotContainer.<init>(RobotContainer.java:21)`
- `at frc.robot.Robot.<init>(Robot.java:27)`
- `ERROR 11 [Spark Flex] IDs: 2, Invalid parameter value, parameter id (113) Velocity Conversion Factor ... invalid value is nan`
- `ERROR 1 Could not instantiate robot frc.robot.subsystems.SwerveSubsystem!`

### Analysis Update
- This run confirms the startup failure happens while configuring a SPARK Flex (CAN ID 2) with an invalid velocity conversion factor (`nan`).
- The exception thrown in `SwerveSubsystem` is a downstream result of that motor-config failure.
- Most probable root cause remains invalid conversion inputs in `src/main/deploy/swerve/modules/physicalproperties.json`.

### Non-blocking Warnings Seen
- `StatusLogger: It is not recommended to log to RoboRIO internal storage. Plug in a flash drive.`
- `StatusLogger: Logging REVLOG to /home/lvuser/logs/...`
