# Hardware Map

## Purpose
Single source of truth for electrical IDs, ports, firmware, and subsystem ownership.

## Robot Overview
- Team: 2180
- Drivebase: Swerve (YAGSL config in `src/main/deploy/swerve`)
- IMU type configured: `navx_mxp_serial` (`src/main/deploy/swerve/swervedrive.json`)

## CAN Devices
Fill this out and keep it current before each event.

| Device | Subsystem | CAN ID | Bus | Vendor | Firmware | Notes |
|---|---|---:|---|---|---|---|
| Front Left Drive Motor | Drivetrain |  |  |  |  |  |
| Front Left Angle Motor | Drivetrain |  |  |  |  |  |
| Front Left Encoder | Drivetrain |  |  |  |  |  |
| Front Right Drive Motor | Drivetrain |  |  |  |  |  |
| Front Right Angle Motor | Drivetrain |  |  |  |  |  |
| Front Right Encoder | Drivetrain |  |  |  |  |  |
| Back Left Drive Motor | Drivetrain |  |  |  |  |  |
| Back Left Angle Motor | Drivetrain |  |  |  |  |  |
| Back Left Encoder | Drivetrain |  |  |  |  |  |
| Back Right Drive Motor | Drivetrain |  |  |  |  |  |
| Back Right Angle Motor | Drivetrain |  |  |  |  |  |
| Back Right Encoder | Drivetrain |  |  |  |  |  |
| IMU | Drivetrain | 0 | MXP/Serial | Studica/navX |  | from `swervedrive.json` |

## PWM / DIO / Analog / Relay
| Device | Subsystem | Port | Type | Notes |
|---|---:|---|---|---|
|  |  |  |  |  |

## Pneumatics
| Device | Module Type | Module ID | Channel | Notes |
|---|---|---:|---:|---|
|  |  |  |  |  |

## Controller Mapping
| Driver Station USB Port | Device | Purpose |
|---:|---|---|
| 0 | Xbox Controller | Driver primary control (see `Constants.OperatorConstants`) |
| 1 |  |  |

## Software-to-Hardware Links
- Swerve config root: `src/main/deploy/swerve`
- Main config: `src/main/deploy/swerve/swervedrive.json`
- Module configs:
  - `src/main/deploy/swerve/modules/frontleft.json`
  - `src/main/deploy/swerve/modules/frontright.json`
  - `src/main/deploy/swerve/modules/backleft.json`
  - `src/main/deploy/swerve/modules/backright.json`

## Bring-Up Checklist
- [ ] CAN IDs match this document.
- [ ] Firmware versions are validated and recorded.
- [ ] No duplicate CAN IDs.
- [ ] IMU orientation and zero behavior verified.
- [ ] Swerve modules spin/steer in correct direction.
- [ ] Brownout or CAN utilization issues checked in logs.

## Event-Day Validation
- [ ] Power cycle and re-check CAN health.
- [ ] Confirm IMU reports expected heading at startup.
- [ ] Confirm module absolute encoder alignment.
- [ ] Verify forward stick produces expected robot movement.
- [ ] Verify field-relative behavior with alliance switch.

