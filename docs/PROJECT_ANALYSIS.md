# 2026Robotics Project Analysis

## Scope
This project is a Java command-based FRC robot codebase for the 2026 season, configured around a YAGSL swerve drivetrain.

## What This Software Is For
- Run the robot lifecycle (`disabled`, `auto`, `teleop`, `test`) through `TimedRobot`.
- Define robot structure and controls in command-based style (`RobotContainer`).
- Drive a field-oriented swerve drivetrain using controller inputs and YAGSL config files in `src/main/deploy/swerve`.
- Support desktop simulation through WPILib/GradleRIO sim tasks.

## Current Architecture Snapshot
- Entry point: `src/main/java/frc/robot/Main.java`
- Robot lifecycle: `src/main/java/frc/robot/Robot.java`
- Command wiring and controls: `src/main/java/frc/robot/RobotContainer.java`
- Drivetrain subsystem: `src/main/java/frc/robot/subsystems/SwerveSubsytem.java`
- Constants: `src/main/java/frc/robot/Constants.java`
- Deploy-time swerve config: `src/main/deploy/swerve/*.json`

## Project Software Inventory

### Build / Language
- Java 17 (`build.gradle`)
- GradleRIO plugin `2026.2.1` (`build.gradle`)
- JUnit Jupiter `5.10.1` (`build.gradle`)

### WPILib and command framework
- WPILib 2026 stack via GradleRIO (`build.gradle`)
- WPILib New Commands vendordep (`vendordeps/WPILibNewCommands.json`)

### Vendor dependencies in repo
- YAGSL `2026.2.3.1` (`vendordeps/yagsl-2026.2.3.1.json`)
- CTRE Phoenix 6 `26.1.1` (`vendordeps/Phoenix6-26.1.1.json`)
- CTRE Phoenix 5 `5.36.0` (`vendordeps/Phoenix5-5.36.0.json`)
- REVLib `2026.0.1` (`vendordeps/REVLib.json`)
- ReduxLib `2026.1.1` (`vendordeps/ReduxLib-2026.1.1.json`)
- ThriftyLib `2026.0.1` (`vendordeps/ThriftyLib-2026.json`)
- Studica `2026.0.0` (`vendordeps/Studica.json`)
- MapleSim `0.4.0-beta` (`vendordeps/maple-sim-0.4.0-beta.json`)

## What Needs Updating (No Code Changes Applied)

### Critical blockers
- Java toolchain is not configured on this machine.
  - Build command `.\\gradlew.bat build` fails with: `JAVA_HOME is not set and no 'java' command could be found in your PATH.`
  - Update needed: install/configure JDK 17 and set `JAVA_HOME` before any compile/test/deploy validation.
- `SwerveSubsytem` has incomplete template comment blocks and typo naming that indicate unfinished cleanup.
  - File: `src/main/java/frc/robot/subsystems/SwerveSubsytem.java:21`
  - Risks: hidden dead code, confusing docs, potential future merge/maintenance errors.

### High-priority project readiness updates
- Replace all template/example command pieces with real robot behavior.
  - `ExampleSubsystem`, `ExampleCommand`, and `Autos.exampleAuto` are still scaffold code.
  - Files: `src/main/java/frc/robot/subsystems/ExampleSubsystem.java`, `src/main/java/frc/robot/commands/ExampleCommand.java`, `src/main/java/frc/robot/commands/Autos.java`
- Autonomous currently returns only sample sequence tied to template subsystem.
  - File: `src/main/java/frc/robot/RobotContainer.java:80`
- Driver controls are partially wired for swerve, but direct-angle command is defined and never selected by trigger/mode logic.
  - File: `src/main/java/frc/robot/RobotContainer.java:47`

### Medium-priority technical debt
- Class naming typo: `SwerveSubsytem` should be normalized to `SwerveSubsystem` for consistency.
  - File: `src/main/java/frc/robot/subsystems/SwerveSubsytem.java:21`
- `Constants.MAX_SPEED` is set to `4.5 ft/s` (about `1.37 m/s`), which is unusually low for most FRC swerve drivetrains; likely placeholder/tuning value.
  - File: `src/main/java/frc/robot/Constants.java:23`
- Dependency rationalization needed:
  - Both Phoenix 5 and Phoenix 6 vendordeps are present; keep only what hardware uses.
  - MapleSim beta is present; validate whether simulation dependency is intentionally pinned to beta.

### Validation gaps
- No evidence of subsystem/command unit tests beyond framework default dependencies.
- No project-level docs for subsystem ownership, CAN IDs, control maps, and autonomous strategy.

## Checklist

### Environment and build
- [ ] Install JDK 17 on dev machines.
- [ ] Set `JAVA_HOME` and confirm `java -version`.
- [ ] Run `.\gradlew.bat build` successfully.
- [ ] Run `.\gradlew.bat test`.
- [ ] Run `.\gradlew.bat simulateJava`.

### Codebase cleanup and architecture
- [ ] Remove/replace all `Example*` template classes and references.
- [ ] Define production autonomous command(s) and chooser strategy.
- [ ] Clean up `SwerveSubsytem` comments and naming.
- [ ] Confirm swerve control modes (angular velocity vs direct angle) and bind explicit driver controls.
- [ ] Review `Constants.MAX_SPEED` against physical drivetrain capability and safety limits.

### Dependency and configuration management
- [ ] Verify each vendordep maps to actual robot hardware/software usage.
- [ ] Remove unused vendordeps (especially duplicate motor-controller stacks if unused).
- [ ] Pin/update vendordep versions intentionally after hardware bring-up testing.
- [ ] Validate `src/main/deploy/swerve` JSON values against measured robot geometry and module IDs.

### Quality and operations
- [ ] Add smoke tests or command-level unit tests for key command logic.
- [ ] Add pre-event validation checklist (CAN status, gyro health, odometry sanity, controller mapping).
- [ ] Add coding standards and code-review criteria for the team.

## Suggested Documentation Expansion
- `docs/ARCHITECTURE.md`: subsystem boundaries, command scheduling model.
- `docs/CONTROLS.md`: controller mapping and driver modes.
- `docs/HARDWARE_MAP.md`: CAN IDs, ports, sensors, firmware versions.
- `docs/AUTON.md`: auto routines, assumptions, tuning process.
- `docs/SETUP.md`: local dev setup (JDK, WPILib, VS Code, simulation).
