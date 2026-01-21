package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name="Small_Triangle", group ="Robot")

public class Auto_Small_Triangle extends LinearOpMode {

    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotorEx Launcher = null;
    private DcMotorEx intake = null;
    private Servo Door = null;
    private Servo Carousel;
    private final ElapsedTime runtime = new ElapsedTime();
    private DigitalChannel touchsensor;
    private double ticksPerRotation;

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initializing Hardware..."); // Add telemetry to see progress
        telemetry.update();

        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        Launcher = hardwareMap.get(DcMotorEx.class, "Launcher");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        Door = hardwareMap.get(Servo.class, "Door");
        Carousel = hardwareMap.get(Servo.class, "Carousel");


        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE); //Test to make sure is right direction
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE); //Test to make sure is right direction
        frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE); //Test to make sure is right direction
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD); //Test to make sure is right direction
        Launcher.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setDirection(DcMotorEx.Direction.REVERSE);

        Launcher.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        ticksPerRotation = frontLeftDrive.getMotorType().getTicksPerRev();
        ticksPerRotation = backLeftDrive.getMotorType().getTicksPerRev();
        ticksPerRotation = frontRightDrive.getMotorType().getTicksPerRev();
        ticksPerRotation = backRightDrive.getMotorType().getTicksPerRev();


        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();
        runtime.reset(); // Resets the timer once the OpMode start

        //CODE GOES HERE

    }



    public void driveForward(double inches, double speed) {
        // Calculate how many ticks are in those inches
        // Replace 45.0 with your specific Ticks Per Inch calculation
        int target = (int)(inches * 45.0);

        //  Set the target relative to where the motor is now
        frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + target);
        frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() + target);
        backLeftDrive.setTargetPosition(backLeftDrive.getCurrentPosition() + target);
        backRightDrive.setTargetPosition(backRightDrive.getCurrentPosition() + target);

        // Switch to RUN_TO_POSITION
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //  Set power (this acts as the 'speed limit')
        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);

        //  Wait until the motors reach the destination
        while (opModeIsActive() && (frontLeftDrive.isBusy() || frontRightDrive.isBusy() || backLeftDrive.isBusy() || backRightDrive.isBusy())) {
            telemetry.addData("Status", "Driving to target...");
            telemetry.update();
        }

        //  Stop the motors
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void driveBackwards(double inches, double speed) {
        // Calculate how many ticks are in those inches
        // Replace 45.0 with your specific Ticks Per Inch calculation
        int target = (int) (inches * 45.0);

        //  Set the target relative to where the motor is now
        frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() - target);
        frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() - target);
        backLeftDrive.setTargetPosition(backLeftDrive.getCurrentPosition() - target);
        backRightDrive.setTargetPosition(backRightDrive.getCurrentPosition() - target);

        // Switch to RUN_TO_POSITION
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //  Set power (this acts as the 'speed limit')
        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);

        //  Wait until the motors reach the destination
        while (opModeIsActive() && (frontLeftDrive.isBusy() || frontRightDrive.isBusy() || backLeftDrive.isBusy() || backRightDrive.isBusy())) {
            telemetry.addData("Status", "Driving to target...");
            telemetry.update();
        }

        //  Stop the motors
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void StrafeLeft(double inches, double speed) {
        // Calculate how many ticks are in those inches
        // Replace 45.0 with your specific Ticks Per Inch calculation
        int target = (int) (inches * 45.0);

        //  Set the target relative to where the motor is now
        frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() - target);
        frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() + target);
        backLeftDrive.setTargetPosition(backLeftDrive.getCurrentPosition() + target);
        backRightDrive.setTargetPosition(backRightDrive.getCurrentPosition() - target);

        // Switch to RUN_TO_POSITION
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //  Set power (this acts as the 'speed limit')
        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);

        //  Wait until the motors reach the destination
        while (opModeIsActive() && (frontLeftDrive.isBusy() || frontRightDrive.isBusy() || backLeftDrive.isBusy() || backRightDrive.isBusy())) {
            telemetry.addData("Status", "Driving to target...");
            telemetry.update();
        }

        //  Stop the motors
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void StrafeRight(double inches, double speed) {
        // Calculate how many ticks are in those inches
        // Replace 45.0 with your specific Ticks Per Inch calculation
        int target = (int) (inches * 45.0);

        //  Set the target relative to where the motor is now
        frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + target);
        frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() - target);
        backLeftDrive.setTargetPosition(backLeftDrive.getCurrentPosition() - target);
        backRightDrive.setTargetPosition(backRightDrive.getCurrentPosition() + target);

        // Switch to RUN_TO_POSITION
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //  Set power (this acts as the 'speed limit')
        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);

        //  Wait until the motors reach the destination
        while (opModeIsActive() && (frontLeftDrive.isBusy() || frontRightDrive.isBusy() || backLeftDrive.isBusy() || backRightDrive.isBusy())) {
            telemetry.addData("Status", "Driving to target...");
            telemetry.update();
        }

        //  Stop the motors
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }


    public void fireSequence(double launcherVel, double intakeVel, double doorPos, double carouselPos) {
        //Start the UltraPlanetary motors
        Launcher.setVelocity(launcherVel);
        intake.setVelocity(intakeVel);


        sleep(1000);

        // Open the door
        Door.setPosition(doorPos);
        sleep(300); // Small pause to ensure the door is out of the way

        // Move the Carousel servo to push/feed
        Carousel.setPosition(carouselPos);

        // Keep everything running while the piece is launched
        sleep(2000);

        // SHUT DOWN / RESET
        Launcher.setVelocity(0);
        intake.setVelocity(0);
        Door.setPosition(0.0);      // Close door
        Carousel.setPosition(0.0);  // Reset carousel
    }

    public void collectArtifacts(double distanceInches, double driveSpeed) {
        // Start the Intake
        // 1200 is a good starting Ticks-Per-Second for an intake
        intake.setVelocity(1200);



        // Drive forward while the intake is spinning
        driveForward(24, 2);

        // Once the drive is done, stop the intake and close the door
        intake.setVelocity(0);
        Door.setPosition(0.1); // Adjust to your 'Closed' position

    }


}

