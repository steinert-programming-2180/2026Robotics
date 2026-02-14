# Code Review Findings

> Update (2026-02-14): See `docs/HARDWARE_VALIDATION.md` for documentation-backed verification.
> The prior claim that `encoder.type: none` is inherently invalid has been revised.
> Runtime evidence: see `docs/LOGS.md` for extracted Driver Station log lines and analysis.

Date: 2026-02-14
Scope: Current project state, no code changes applied.

## Findings (Highest Severity First)

1. Startup crash is now directly tied to SPARK Flex velocity conversion factor NaN at boot.
- Evidence (latest logs):
  - `ERROR 11 [Spark Flex] IDs: 2 ... Velocity Conversion Factor ... invalid value is nan`
  - `Failed to create swerve drive: Invalid parameter value, parameter id`
  - `Could not instantiate robot frc.robot.subsystems.SwerveSubsystem`
- Likely source config:
  - `src/main/deploy/swerve/modules/physicalproperties.json` (zero conversion/gear/diameter values)

2. Drivetrain conversion factors are zeroed (major control/odometry risk).
- File:
  - `src/main/deploy/swerve/modules/physicalproperties.json`
- Fields with zero values:
  - `conversionFactors.angle.gearRatio`
  - `conversionFactors.angle.factor`
  - `conversionFactors.drive.diameter`
  - `conversionFactors.drive.gearRatio`
  - `conversionFactors.drive.factor`

3. Swerve init exception handling drops stack trace context.
- File:
  - `src/main/java/frc/robot/subsystems/SwerveSubsystem.java:30`
- Current behavior wraps only message text, reducing debuggability.

4. Operator control mode is partially wired.
- File:
  - `src/main/java/frc/robot/RobotContainer.java`
- `driveFieldOrientedDirectAngle` is created but not bound/scheduled.

5. Autonomous currently performs no action.
- File:
  - `src/main/java/frc/robot/Robot.java:55`
- `autonomousInit()` does not schedule an autonomous command.

6. Test coverage gap.
- `build.gradle` includes JUnit dependencies, but no test sources currently exist (`:test NO-SOURCE` in build output).

## Open Questions
- Is running without external module absolute encoders intentional (temporary fallback) or should they be configured now?
- What are the validated wheel diameter and drive/angle gear ratios for `physicalproperties.json`?

## Notes
- Build/deploy pipeline is functioning.
- Main blockers are swerve JSON correctness and drivetrain calibration values.






