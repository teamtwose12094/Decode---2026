package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
Connect to roboto  wifi
open comamd prtompt and type "adb connect 192.168.43.1"
 */

@TeleOp(name="Basic: Omni Linear OpMode OLD", group="Linear OpMode")
public class BasicOmniOpMode_LinearOLD extends LinearOpMode {

    // Declare motors and servos
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor Launcher = null;



    Config config = new Config();

    //Drive Motor Paramaters
    double driveSpeedMultiplier = config.driveSpeedMultiplier;
    double pivotSpeedMultiplier = config.pivotSpeedMultiplier;

    private boolean isSlowMode = false;
    private boolean lastRightBumperState = false;


    @Override
    public void runOpMode() {

        // Hardware initialized
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        Launcher = hardwareMap.get(DcMotor.class, "Launcher");


        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        Launcher.setDirection(DcMotor.Direction.FORWARD);


        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Slow mode
            if (gamepad1.right_trigger > 0.1){
                driveSpeedMultiplier = config.slowDriveSpeedMultiplier;
                pivotSpeedMultiplier = config.slowPivotSpeedMultiplier;
            } else {
                driveSpeedMultiplier = config.driveSpeedMultiplier;
                pivotSpeedMultiplier = config.pivotSpeedMultiplier;

            }
            double max = 0.0;

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial = -gamepad1.left_stick_y * driveSpeedMultiplier;  // Note: pushing stick forward gives negative value
            double lateral = gamepad1.left_stick_x * driveSpeedMultiplier;
            double yaw = gamepad1.right_stick_x * pivotSpeedMultiplier;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.

            // Slow mode

            double frontLeftPower = (axial + lateral + yaw);
            double frontRightPower = axial - lateral - yaw;
            double backLeftPower = axial - lateral + yaw;
            double backRightPower = axial + lateral - yaw;


            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(backRightPower));

            if (max > 1.0) {
                frontLeftPower /= max;
                frontRightPower /= max;
                backLeftPower /= max;
                backRightPower /= max;
            }


            // Send calculated power to wheels
            frontLeftDrive.setPower(frontLeftPower);
            frontRightDrive.setPower(frontRightPower);
            backLeftDrive.setPower(backLeftPower);
            backRightDrive.setPower(backRightPower);

            //Launcher
            double triggerValue = gamepad2.left_trigger;
            if (triggerValue > 0.1) {
                // If the trigger is fully pressed (1.0), the motor runs at full power.
                Launcher.setPower(triggerValue);
                telemetry.addData("Launch Motor", "Running @ " + triggerValue);
            } else {
                // If the trigger is released (value <= 0.1), stop the motor.
                Launcher.setPower(0.0);
                telemetry.addData("Launch Motor", "Stopped");
            }



            // telemetry

            telemetry.addData("wheel", frontLeftDrive.getCurrentPosition());



            telemetry.addData("gamepad1 controls", "");
            telemetry.addData("Right trigger", "Slow mode");
            telemetry.addData("Left stick up", "move forwards");
            telemetry.addData("Left stick back", "move backwards");
            telemetry.addData("right stick left", "turn left");
            telemetry.addData("right stick right", "turn right");
            telemetry.addData("left stick left", "strafe left");
            telemetry.addData("left stick right", "strafe right");
            telemetry.addData("bucket up", "left_bumper");
            telemetry.addData("bucket down", "right_bumper");
            telemetry.addData("gamepad2 controls", "");
            telemetry.addData("cross", "gripper clamp");
            telemetry.addData("circle", "gripper let go");
            telemetry.addData("d pad up", "arm up");
            telemetry.addData("d pad down", "arm down");
            telemetry.addData("d pad left", "rotator right");
            telemetry.addData("d pad right", "rotator left");
            telemetry.addData("left trigger", "slide to low basket");
            telemetry.addData("Left joystick button", "slide to high basket");
            telemetry.addData("right trigger", "slide to 0");
            telemetry.addData("Left trigger", "slide to specimen");
            telemetry.addData("right trigger", "slide high bar");

            telemetry.update();

        }
    }
}