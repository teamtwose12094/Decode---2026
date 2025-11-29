package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

/*
Connect to roboto  wifi
open command prompt and type "adb connect 192.168.43.1"
 */

@TeleOp(name="DriverControl", group="Linear OpMode")
public class DriverControl extends LinearOpMode {

    // Declare motors and servos
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor Launcher = null;
    private DcMotor intake = null;
    private Servo PushyThing = null;
    private ColorSensor colorSensor;
    private double ticksPerRotation;


    Config config = new Config();

    //Drive Motor Parameters
    double driveSpeedMultiplier = config.driveSpeedMultiplier;
    double pivotSpeedMultiplier = config.pivotSpeedMultiplier;

    //For Slow Mode
    private boolean isSlowMode = false;
    private boolean lastRightBumperState = false;
    private ColorSensor ColorSensor;
    private DistanceSensor distanceSensor;

    // Tracks the current state (true = out/1, false = in/0)
    private boolean pushyThingIsOut = false;

    // Tracks the state of the button from the previous loop iteration
    private boolean wasButtonPressed = false;

    private int direction = 0;




    @Override
    public void runOpMode() {

        //Hardware Initialized
        frontLeftDrive = hardwareMap.get(DcMotor.class,"frontLeftDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class,"backLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class,"frontRightDrive");
        backRightDrive = hardwareMap.get(DcMotor.class,"backRightDrive");

        Launcher = hardwareMap.get(DcMotor.class,"Launcher");
        intake = hardwareMap.get(DcMotor.class,"intake");
        PushyThing = hardwareMap.get(Servo.class, "PushyThing");
        ColorSensor = hardwareMap.get(ColorSensor.class, "ColorSensor");


        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE); //Test to make sure is right direction
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE); //Test to make sure is right direction
        frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE); //Test to make sure is right direction
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD); //Test to make sure is right direction

        Launcher.setDirection(DcMotorSimple.Direction.REVERSE); //Test to make sure is right direction
        intake.setDirection(DcMotorSimple.Direction.REVERSE);




        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            //Driving


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

            double max = 0.0;
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
            backLeftDrive.setPower(backLeftPower);
            frontLeftDrive.setPower(frontLeftPower);
            backRightDrive.setPower(backRightPower);
            frontRightDrive.setPower(frontRightPower);

            //Slow Mode

            boolean currentRightBumperState = gamepad1.right_bumper;


            if (currentRightBumperState && !lastRightBumperState) {
                // Toggle the slow mode state
                isSlowMode = !isSlowMode;
            }


            if (isSlowMode) {
                // SLOW MODE ACTIVE
                driveSpeedMultiplier = config.slowDriveSpeedMultiplier;
                pivotSpeedMultiplier = config.slowPivotSpeedMultiplier;
            } else {
                // NORMAL MODE ACTIVE
                driveSpeedMultiplier = config.driveSpeedMultiplier;
                pivotSpeedMultiplier = config.pivotSpeedMultiplier;
            }


            //Launcher
            double LeftTriggerValue = gamepad2.left_trigger;
            if (LeftTriggerValue > 0.1) {
                // If the trigger is fully pressed (1.0), the motor runs at full power.
                Launcher.setPower(0.75);
                telemetry.addData("Launch Motor", "Running @ " + LeftTriggerValue);
            } else {
                // If the trigger is released (value <= 0.1), stop the motor.
                Launcher.setPower(0.0);
                telemetry.addData("Launch Motor", "Stopped");
            }




            //Intake

            double RightTriggerValue = gamepad2.right_trigger;
            if (RightTriggerValue > 0.1) {
                intake.setPower(RightTriggerValue);
                telemetry.addData("Intake Motor","Running @" + RightTriggerValue);
            } else {
                intake.setPower(0.0);
                telemetry.addData("Intake Motor","Stopped");
            }


            //PushyThing

            if (gamepad2.a) {
                direction++;
                }

            if (gamepad2.b) {
                direction = 0;
            }

            if (direction > 0) {
                // If direction is > 0, set the servo to one of the two end positions (1.0 or 0.0)
                if (direction % 2 == 0) {
                    PushyThing.setPosition(0.5); // E.g., Move one way

                } else { // direction % 2 != 0

                    PushyThing.setPosition(1.0); // Set the servo position to the middle value
                }
            } else {
                // This block executes ONLY when direction is 0 (set by gamepad2.b)
                PushyThing.setPosition(0.0); // E.g., Move the other way
            }

            //Color Sensor


            /*String detectedColor = "NONE";
            int red = colorSensor.red();
            int green = colorSensor.green();
            int blue = colorSensor.blue();

           

            // Simple GREEN Detection: Green is high, and much higher than Red and Blue
            if (green > 100 && green > (red * 2) && green > (blue * 2)) {
                detectedColor = "GREEN";
                telemetry.addData("Colour Detected", "Green");
            }
            // Simple PURPLE Detection: Red and Blue are high and balanced, Green is low
            else if (red > 100 && blue > 100 && (red + blue) > (green * 2)) {
                detectedColor = "PURPLE";
                telemetry.addData("Colour Detected", "Purple");
            }


             */

            //Velocity



            //Telemetry
            telemetry.addData("Wheel", frontLeftDrive.getCurrentPosition());
            telemetry.addData("Servo", PushyThing.getPosition());
            telemetry.addData("Speed", frontLeftPower);


            telemetry.addData("Player 1 Controls", "");
            telemetry.addData("Right Bumper", "Slow Mode");
            telemetry.addData("Left Stick Up", "Drive Forwards");
            telemetry.addData("Left Stick Down", "Drive Backwards");
            telemetry.addData("Left Stick Left", "Strafe Left");
            telemetry.addData("Left Stick Right", "Strafe Right");
            telemetry.addData("Right Stick Left", "Turn Left");
            telemetry.addData("Right Stick Right", "Turn Right");
            telemetry.addData("", "");
            telemetry.addData("Player 2 Controls", "");
            telemetry.addData("Left Trigger", "Launch Artifacts");
            telemetry.addData("Right Trigger", "Intake");
            telemetry.addData("D-Pad Left", "Start Lever");
            telemetry.addData("D-Pad Right", "Stop Lever");
            telemetry.addData("X Button", "Turn On Pushy Thing");
            telemetry.addData("O Button", "Turn Off Pushy Thing");
            telemetry.update();
        }
        }


}
