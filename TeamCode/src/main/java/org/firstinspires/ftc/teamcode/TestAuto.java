package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name="TestAuto", group ="Robot")

public class TestAuto extends LinearOpMode {

    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor Launcher = null;
    private ElapsedTime runtime = new ElapsedTime();
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
        Launcher = hardwareMap.get(DcMotor.class, "Launcher");



        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE); //Test to make sure is right direction
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE); //Test to make sure is right direction
        frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE); //Test to make sure is right direction
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD); //Test to make sure is right direction
        Launcher.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        ticksPerRotation = frontLeftDrive.getMotorType().getTicksPerRev();
        ticksPerRotation = backLeftDrive.getMotorType().getTicksPerRev();
        ticksPerRotation = frontRightDrive.getMotorType().getTicksPerRev();
        ticksPerRotation = backRightDrive.getMotorType().getTicksPerRev();

        /*public double getMotorRotations() {
            frontLeftDrive.setPower() / ticksPerRotation;
        }
        public void loop(){

        }

        */

        waitForStart();
        runtime.reset(); // Resets the timer once the OpMode start

        //Seqeunce Starts Here (1 inch = 63 px)
        moveForward(1,5364); // move off launch line & onto next launch line
        turnRight(1,135);


    }

    public void stopMotors() {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);

    }

    public void moveForward(double power, long time){
        frontLeftDrive.setPower(power);
        frontRightDrive.setPower(power);
        backLeftDrive.setPower(power);
        backRightDrive.setPower(power);
        sleep(time);
        stopMotors();
    }

    public void moveBackward(double power, long time){
        frontLeftDrive.setPower(-power);
        frontRightDrive.setPower(-power);
        backLeftDrive.setPower(-power);
        backRightDrive.setPower(-power);
        sleep(time);
        stopMotors();
    }

    public void turnRight(double power, long time){
        frontLeftDrive.setPower(power);
        frontRightDrive.setPower(-power);
        backLeftDrive.setPower(power);
        backRightDrive.setPower(-power);
        sleep(time);
        stopMotors();
    }

    public void turnLeft(double power, long time){
        frontLeftDrive.setPower(-power);
        frontRightDrive.setPower(power);
        backLeftDrive.setPower(-power);
        backRightDrive.setPower(power);
        sleep(time);
        stopMotors();
    }

    public void strafeRight(double power, long time){
        frontLeftDrive.setPower(-power);
        frontRightDrive.setPower(power);
        backLeftDrive.setPower(power);
        backRightDrive.setPower(-power);
        sleep(time);
        stopMotors();
    }

    public void strafeLeft(double power, long time){
        frontLeftDrive.setPower(power);
        frontRightDrive.setPower(-power);
        backLeftDrive.setPower(-power);
        backRightDrive.setPower(power);
        sleep(time);
        stopMotors();
    }

    public void Launch(double power, long time){
        Launcher.setPower(power);

    }



}


