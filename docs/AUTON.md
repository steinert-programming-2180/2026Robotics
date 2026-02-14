# Autonomous Plan and Validation

## Purpose
Define autonomous strategy, command ownership, assumptions, and test criteria.

## Current Status (From Codebase)
- `RobotContainer.getAutonomousCommand()` returns `Autos.exampleAuto(...)`.
- `Autos.exampleAuto(...)` still uses template/example commands.
- No competition-ready autonomous routines are documented yet.

## Auto Architecture Standard
- `RobotContainer` owns routine selection.
- `Autos` contains autonomous factory methods.
- Subsystems expose clear command factories for reusable actions.
- Each auto routine must define:
  - Start pose
  - Expected game-piece actions
  - End pose / handoff to teleop
  - Failure behavior

## Routine Catalog
Keep this table updated as routines are added.

| Routine Name | Start Position | Primary Goal | Time Budget (s) | Owner | Status |
|---|---|---|---:|---|---|
| Example Auto | Unknown | Template only |  |  | Not Competition Ready |
|  |  |  |  |  |  |

## Required Inputs Per Routine
| Input | Source | Verified |
|---|---|---|
| Start heading | IMU | [ ] |
| Start XY pose | Pose estimator/odometry | [ ] |
| Alliance selection | DS/WPILib | [ ] |
| Field-relative orientation | Drivetrain config | [ ] |

## Autonomous Acceptance Criteria
- [ ] Robot executes full routine without command interruptions.
- [ ] Path/pose error remains within team tolerance.
- [ ] Auto completes under 15 seconds with margin.
- [ ] Teleop handoff is stable (no sudden heading or speed jump).
- [ ] Behavior is repeatable across at least 5 consecutive runs.

## Failure Mode Checklist
- [ ] IMU not ready at init.
- [ ] Pose reset incorrect or missing.
- [ ] Command requirements conflict causes cancellation.
- [ ] Timing dependencies cause incomplete actions.
- [ ] Driver enable/disable transitions produce unsafe outputs.

## Test Procedure (Practice Field)
1. Record firmware versions and code commit hash.
2. Validate battery condition and drivetrain health.
3. Run each routine 5 times from marked start.
4. Log completion time and final pose error.
5. Note any command cancellations or watchdog warnings.
6. Approve only routines that pass all acceptance criteria.

## Data to Record Per Test Session
| Date | Build/Commit | Routine | Avg Time | Max Pose Error | Pass/Fail | Notes |
|---|---|---|---:|---:|---|---|
|  |  |  |  |  |  |  |

## Pre-Match Auto Selection Checklist
- [ ] Correct routine selected on DS/dashboard.
- [ ] Correct alliance color confirmed.
- [ ] Robot physically aligned to expected start mark.
- [ ] IMU heading and odometry reset confirmed.
- [ ] Driver and operator agree on teleop handoff plan.

