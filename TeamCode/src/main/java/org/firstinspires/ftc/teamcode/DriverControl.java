package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="DriverControl", group="Linear OpMode")
public class DriverControl extends LinearOpMode {

    // Declare motors and servos
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor Launcher = null;

    Config config = new Config();

    //Drive Motor Parameters
    double driveSpeedMultiplier = config.driveSpeedMultiplier;
    double pivotSpeedMultiplier = config.pivotSpeedMultiplier;

    //For Slow Mode
    private boolean isSlowMode = false;
    private boolean lastRightBumperState = false;



    @Override
    public void runOpMode() {

        //Hardware Initialized
        frontLeftDrive = hardwareMap.get(DcMotor.class,"frontLeftDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class,"backLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class,"frontRightDrive");
        backRightDrive = hardwareMap.get(DcMotor.class,"backRightDrive");

        Launcher = hardwareMap.get(DcMotor.class,"Launcher");

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE); //Test to make sure is right direction
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE); //Test to make sure is right direction
        frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD); //Test to make sure is right direction
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD); //Test to make sure is right direction

        Launcher.setDirection(DcMotorSimple.Direction.FORWARD); //Test to make sure is right direction

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            //Driving
            //Use left joystick to go forward & strafe, right joystick to turn/rotate
            double axial = -gamepad1.left_stick_y * driveSpeedMultiplier;  // Note: pushing stick forward gives negative value
            double lateral = gamepad1.left_stick_x * driveSpeedMultiplier;
            double yaw = gamepad1.right_stick_x * pivotSpeedMultiplier;

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


            double max = 0.0;

            //Telemetry
            telemetry.addData("Wheel", frontLeftDrive.getCurrentPosition());


            telemetry.addData("Player 1 Controls", "");
            telemetry.addData("Right Bumper", "Slow Mode");
            telemetry.addData("Left Stick Up", "Drive Forwards");
            telemetry.addData("Left Stick Down", "Drive Backwards");
            telemetry.addData("Left Stick Left", "Strafe Left");
            telemetry.addData("Left Stick Right", "Strafe Right");
            telemetry.addData("Right Stick Left", "Turn Left");
            telemetry.addData("Right Stick Right", "Turn Right");

            telemetry.addData("Player 2 Controls", "");
            telemetry.addData("Left Trigger", "Launch Artifacts");

        }
        }


}
