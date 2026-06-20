package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="BigTriangleLEFT", group ="Robot")

public class BigTriangleLEFT extends LinearOpMode {

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





        ticksPerRotation = frontLeftDrive.getMotorType().getTicksPerRev();
        ticksPerRotation = backLeftDrive.getMotorType().getTicksPerRev();
        ticksPerRotation = frontRightDrive.getMotorType().getTicksPerRev();
        ticksPerRotation = backRightDrive.getMotorType().getTicksPerRev();



        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();
        runtime.reset(); // Resets the timer once the OpMode start

        //CODE GOES HERE
        driveForward(30,0.5);
        Shoot(1000);
        fireSequence(1300,0.0,1.0);
        Intake(100);
        sleep(4000);
        GateClose(300,0.5);
        StrafeLeft(20,0.5);
        stop();





    }

    public void driveForward(double inches, double speed) {
        // Calculate how many ticks are in those inches
        // Replace 45.0 with your specific Ticks Per Inch calculation
        int target = (int)(inches * 60.37);

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
        int target = (int) (inches * 60.37);

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
        int target = (int) (inches * 60.37);

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
        int target = (int) (inches * 60.37);

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


    public void fireSequence(double launcherVel, double doorPos, double carouselPos) {



        //Start the UltraPlanetary motors
        Launcher.setVelocity(launcherVel);


        // Open the door
        Door.setPosition(doorPos);
        // Small pause to ensure the door is out of the way

        // Move the Carousel servo to push/feed
        Carousel.setPosition(carouselPos);

        // Keep everything running while the piece is launched
        sleep(4000);

    }

    public void collectArtifacts(double distanceInches, double driveSpeed) {
        // Start the Intake
        // 1200 is a good starting Ticks-Per-Second for an intake
        intake.setVelocity(1200);



        // Drive forward while the intake is spinning
        driveForward(24, 2);

        // Once the drive is intake.setVelocity(0);
        Door.setPosition(0.1); // Adjust to your 'Closed' position

    }
    public void turnLeft(int ticks, double speed) {
        //Reset encoders so we start at 0
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set the target: Left goes back (-), Right goes forward (+)
        frontLeftDrive.setTargetPosition(-ticks);
        frontRightDrive.setTargetPosition(ticks);
        backLeftDrive.setTargetPosition(-ticks);
        backRightDrive.setTargetPosition(ticks);

        //Set to Run to Position
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Give it power
        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);

        // Wait until it reaches the spot
        while (opModeIsActive() && (frontLeftDrive.isBusy() && frontRightDrive.isBusy() && backLeftDrive.isBusy() && backRightDrive.isBusy())) {
            telemetry.addData("Status", "Turning Left...");
            telemetry.update();
        }

        // Stop
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void turnRight(int ticks, double speed) {
        //Reset encoders so we start at 0
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set the target: Left goes back (-), Right goes forward (+)
        frontLeftDrive.setTargetPosition(ticks);
        frontRightDrive.setTargetPosition(-ticks);
        backLeftDrive.setTargetPosition(ticks);
        backRightDrive.setTargetPosition(-ticks);

        //Set to Run to Position
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Give it power
        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);

        // Wait until it reaches the spot
        while (opModeIsActive() && (frontLeftDrive.isBusy() && frontRightDrive.isBusy() && backLeftDrive.isBusy() && backRightDrive.isBusy())) {
            telemetry.addData("Status", "Turning Right...");
            telemetry.update();
        }

        // Stop
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void Shoot (double launcherVel) {

        Launcher.setVelocity(launcherVel);

        sleep(500);


    }

    public void GateOpen(int ticks, double speed) {
        Door.setPosition(1.0);
        sleep(100);
    }
    public void Intake(double  intakeVel) {
        intake.setVelocity(intakeVel);
        sleep(400);
    }

    public void GateClose(int ticks, double speed) {
        Door.setPosition(1.0);
        sleep(100);
    }


}

