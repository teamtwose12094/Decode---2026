package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="DriverControl", group="Linear OpMode")
public class DriverControl extends LinearOpMode {

    // Declare motors and servos
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;

    Config config = new Config();

    //Drive Motor Paramaters
    double driveSpeedMultiplier = config.driveSpeedMultiplier;
    double pivotSpeedMultiplier = config.pivotSpeedMultiplier;



    @Override
    public void runOpMode() throws InterruptedException {

        //Hardware Initialized
        frontLeftDrive = hardwareMap.get(DcMotor.class,"frontLeftDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class,"backLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class,"frontRightDrive");
        backRightDrive = hardwareMap.get(DcMotor.class,"backRightDrive");

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        //Run until end of match (Driver Press Stop)


        //Use left joystick to go forward & strafe, right joystick to turn/rotate
        double axial = -gamepad1.left_stick_y * driveSpeedMultiplier;  // Note: pushing stick forward gives negative value
        double lateral = gamepad1.left_stick_x * driveSpeedMultiplier;
        double yaw = gamepad1.right_stick_x * pivotSpeedMultiplier;


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



    }
}
