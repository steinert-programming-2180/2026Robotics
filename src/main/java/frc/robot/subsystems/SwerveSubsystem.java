package frc.robot.subsystems;

import java.io.File;
import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;
import static edu.wpi.first.units.Units.Meter;

public class SwerveSubsystem extends SubsystemBase {
    final File swerveJsonDir = new File(Filesystem.getDeployDirectory(), "swerve");

    SwerveDrive swerveDrive;

    /** Creates a new ExampleSubsystem. */
    public SwerveSubsystem() {
        try {
            // Robot Facing Towards the Red Alliance
            swerveDrive = new SwerveParser(swerveJsonDir).createSwerveDrive(Constants.MAX_SPEED,
                    new Pose2d(new Translation2d(Meter.of(1), Meter.of(4)), Rotation2d.fromDegrees(0)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public SwerveDrive getSwerveDrive() {
        return swerveDrive;
    }

    public void driveFieldOriented(ChassisSpeeds velocity) {
        swerveDrive.driveFieldOriented(velocity);
    }

    public Command driveFieldOriented(Supplier<ChassisSpeeds> velocity) {
        return run(() -> {
            swerveDrive.driveFieldOriented(velocity.get());
        });
    }

    /**
     * Example command factory method.
     *
     * @return a command
    
    public Command exampleMethodCommand() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
                () -> {
                    /* one-time action goes here
                });
    }

    /**
     * An example method querying a boolean state of the subsystem (for example, a
     * digital sensor).
     *
     * @return value of some boolean subsystem state, such as a digital sensor.
     
    public boolean exampleCondition() {
        // Query some boolean state, such as a digital sensor.
        return false;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
   */
}
