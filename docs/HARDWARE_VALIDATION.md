# Hardware/Config Verification Against Official Docs

Date: 2026-02-14

## Sources
- YAGSL Device Configuration: https://docs.yagsl.com/configuring-yagsl/configuration/device-configuration
- YAGSL Absolute Encoders: https://docs.yagsl.com/devices/absolute-encoders
- YAGSL Physical Properties Configuration: https://docs.yagsl.com/configuring-yagsl/configuration/physical-properties-configuration
- YAGSL Swerve Drive Configuration: https://docs.yagsl.com/configuring-yagsl/configuration/swerve-drive-configuration
- YAGSL NavX Gyro Page: https://docs.yagsl.com/devices/gyroscope/navx
- REV Hardware Client (SPARK Flex update): https://docs.revrobotics.com/rev-hardware-client/ion/spark-flex/updating-a-spark-flex
- REV Hardware Client 2 Swerve Calibration: https://docs.revrobotics.com/rev-hardware-client-2/guides/swerve-calibration
- WPILib Setup (2026 compatibility note): https://docs.wpilib.org/en/latest/docs/zero-to-robot/step-2/wpilib-setup.html
- WPILib roboRIO 2 imaging notes: https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-3/roborio2-imaging.html

## Verification Results

1. `encoder.type = "none"` in module JSON
- Status: Valid option in YAGSL docs (not an unknown type).
- Evidence: YAGSL Absolute Encoders list includes `none` as a possible type.
- Practical note: This means no external absolute encoder. For competition robustness, absolute steering reference is still strongly recommended.

2. `physicalproperties.json` conversion factors with zeros
- Status: Confirmed issue.
- Evidence: YAGSL physical properties docs define drive diameter + drive gear ratio + angle gear ratio as required composition inputs; zero placeholders are not valid tuned drivetrain values.
- Impact: Incorrect speed/odometry/closed-loop behavior even if robot starts.

3. IMU setting `navx_mxp_serial`
- Status: Supported by YAGSL.
- Evidence: YAGSL NavX page includes `navx_mxp_serial` as a supported connection type.
- Practical note: YAGSL indicates SPI (`navx`/`navx_spi`) is generally preferred when available.

4. Module ordering in `swervedrive.json`
- Status: Correct format.
- Evidence: YAGSL swerve drive config requires clockwise order starting from front-left; your file uses `frontleft`, `frontright`, `backleft`, `backright`.

5. Spark Flex firmware baseline
- Status: Process verified, exact "latest" version number not asserted by docs.
- Evidence: REV docs instruct updating to latest firmware via REV Hardware Client and indicate REV Hardware Client 2 is required for REVLib 2026+ environments.
- Local baseline recorded: all SPARK Flex on 26.1.2 (from your hardware client screenshots).

6. roboRIO imaging/software compatibility
- Status: Your current image line is compatible with 2026 toolchain context.
- Evidence: WPILib setup notes 2026 updates are compatible with kickoff 2026 roboRIO image; your screenshot shows `FRC_roboRIO_2026_v1.2`.
- Additional note: roboRIO 2.0 has specific microSD imaging guidance in WPILib docs.

## Conclusion
- Confirmed high-priority config issue: zeroed conversion factors in `src/main/deploy/swerve/modules/physicalproperties.json`.
- Previous claim that `encoder.type: none` is inherently invalid is not supported by YAGSL documentation.
- Current hardware baseline has been renamed to `docs/hardware.md`.

7. Vivid-Hosting VH-109 radio
- Status: Hardware choice is aligned with current FRC documentation.
- Evidence: WPILib radio programming page explicitly documents VH-109 for at-home control and links to Vivid-Hosting docs.
- Firmware note: Vivid-Hosting documentation states radios must be updated to at least 1.3.0 for official 2025 events; keep radio firmware current before events.

## Additional Radio Sources
- WPILib Radio Programming (VH-109): https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-3/radio-programming.html
- Vivid-Hosting VH-109 docs: https://frc-radio.vivid-hosting.net/
- Vivid-Hosting firmware upgrade guide: https://frc-radio.vivid-hosting.net/overview/upgrading-firmware
