# FRC Software Skills Matrix (2026Robotics)

## Purpose
Define the software capabilities the team needs to build, test, and operate this robot reliably through the 2026 season.

## Core Roles and Skills

### 1) Robot Architecture
- Command-based architecture design (subsystems, commands, triggers, default commands)
- Clean separation of hardware IO, control logic, and operator interface
- State-machine and mode-transition design for teleop/auto/test

### 2) Drivetrain (Swerve + YAGSL)
- YAGSL configuration, parser usage, and runtime command integration
- Field-relative and robot-relative control behaviors
- Gyro alignment, heading behavior, odometry verification
- Swerve tuning workflow (max speed, deadband, scaling, driver feel)

### 3) Controls and Driver Interface
- Xbox input shaping (deadband, slew, scaling)
- Reliable and explicit button bindings
- Driver/co-driver control map versioning and testing

### 4) Autonomous Systems
- Building command sequences/parallel groups with deterministic timing
- Trajectory/path integration strategy
- Auto startup assumptions and failure handling

### 5) Hardware/Software Integration
- CAN bus planning and diagnostics
- Vendor library lifecycle management (CTRE/REV/Redux/Studica/etc.)
- Sensor validation and fail-safe behaviors

### 6) Toolchain and DevOps
- Java 17 + GradleRIO build pipeline
- WPILib simulation workflow
- Git branching, reviews, and release tagging
- Match-readiness validation scripts/checklists

### 7) Quality and Reliability
- Unit/smoke testing of command logic
- Structured debug logging and telemetry dashboards
- Incident postmortems and corrective action tracking

## Skill Checklist
- [ ] Every programmer can run `build`, `test`, and `simulateJava` locally.
- [ ] At least two programmers can own and debug drivetrain code end-to-end.
- [ ] At least two programmers can modify autonomous safely before events.
- [ ] Control mappings are documented and versioned.
- [ ] CAN IDs and subsystem ownership are documented and reviewed.
- [ ] Vendor dependencies are audited at each major update.
- [ ] Pre-event software readiness checklist is used every competition day.
- [ ] Code review is required for drivetrain and autonomous changes.

## Immediate Team Focus (Based on Current Repo)
- [ ] Toolchain readiness: configure Java 17 and `JAVA_HOME` on all dev machines.
- [ ] Remove template `Example*` command/subsystem scaffolding.
- [ ] Finalize and document drivetrain command modes and operator controls.
- [ ] Replace sample autonomous with competition-ready routines.
- [ ] Add baseline tests and a pre-match software verification process.
