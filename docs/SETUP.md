# Setup Guide (Windows, Java, WPILib, GradleRIO)

## Purpose
Standardize local setup so every programmer can build, simulate, and deploy reliably.

## Baseline Requirements
- Windows 10/11
- Git
- VS Code
- FRC Game Tools + Driver Station (for driver station laptop)
- WPILib 2026 tools (includes GradleRIO support and recommended JDK)
- Java 17 configured system-wide

## 1) Install Software
1. Install WPILib 2026 from official WPILib releases.
2. Install Git for Windows.
3. Install VS Code (if not already installed).
4. Install FRC Game Tools on the DS machine.

## 2) Configure Java 17
1. Verify Java:
```powershell
java -version
```
2. If Java is missing, install a JDK 17 distribution.
3. Set `JAVA_HOME` to JDK 17 path.
4. Ensure `%JAVA_HOME%\bin` is in `PATH`.
5. Open a new terminal and verify:
```powershell
echo $env:JAVA_HOME
java -version
```

## 3) Clone and Open Project
```powershell
git clone <repo-url>
cd 2026Robotics
code .
```

## 4) Validate Build and Test
Run these from repo root:
```powershell
.\gradlew.bat build
.\gradlew.bat test
```

## 5) Validate Simulation
```powershell
.\gradlew.bat simulateJava
```

## 6) Deploy to Robot
1. Confirm team number in `.wpilib/wpilib_preferences.json`.
2. Connect to robot network.
3. Deploy:
```powershell
.\gradlew.bat deploy
```

## Quick Troubleshooting
- `JAVA_HOME is not set`: set environment variable and reopen terminal.
- Vendor dependency resolve errors: recheck files in `vendordeps/` and internet connectivity.
- Deploy fails with team mismatch: confirm team in `.wpilib/wpilib_preferences.json`.
- Robot starts but drive does not respond: verify controller port, bindings, and swerve JSON config.

## Team Readiness Check (Every Programmer)
- [ ] Can run `.\gradlew.bat build` without errors.
- [ ] Can run `.\gradlew.bat test` without errors.
- [ ] Can run `.\gradlew.bat simulateJava`.
- [ ] Can explain where to update team number and controller port.

