# Parts and Firmware Baseline

## Purpose
Single reference for current robot hardware and firmware versions used in this codebase.

## Confirmed Hardware
- Team: 2180
- roboRIO: roboRIO 2.0 (NI-roboRIO-0306ae2b)
- Radio: Vivid-Hosting FRC Radio
- IMU: navX (configured in code as `navx_mxp_serial`)
- Power Distribution: REV Power Distribution Hub (PDH), CAN ID 9

## CAN Devices (Current)
- SPARK Flex controllers detected on CAN IDs: 1, 2, 3, 4, 5, 6, 7, 8
- PDH CAN ID: 9

## Firmware / Software Versions
- SPARK Flex firmware: 26.1.2 (all controllers)
- roboRIO image: FRC_roboRIO_2026_v1.2
- roboRIO firmware version: 25.5.0f112
- Driver Station observed robot IP: 10.21.80.2 (team subnet)

## Codebase Expectations
- GradleRIO plugin: 2026.2.1 (`build.gradle`)
- YAGSL vendordep: 2026.2.3.1 (`vendordeps/yagsl-2026.2.3.1.json`)

## Notes
- If hardware changes (new CAN IDs, sensor swaps, firmware updates), update this file and `docs/HARDWARE_MAP.md` in the same commit.
- This file is intended as a quick baseline; use `docs/HARDWARE_MAP.md` for detailed per-mechanism mapping.
