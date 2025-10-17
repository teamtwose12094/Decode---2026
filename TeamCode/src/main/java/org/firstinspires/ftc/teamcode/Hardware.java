package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    private LinearOpMode opMode = null;

    //Motor Variables
    double hdHexCPR = 28.0;
    double coreHexCPR = 288.0;

    double ultraFiveToOne = 5.23;
    double ultraFourToOne = 3.61;
    double spurFourtyToOne = 40.0;

    //Wheel Variables
    double mecanumWheelDiameter = 80.0; //mm
    double mecanumWheelCircumfrence = Math.PI * mecanumWheelDiameter;

    //Motors

    public DcMotor frontLeftDrive = null;

    public DcMotor backLeftDrive = null;

    public DcMotor frontRightDrive = null;

    public DcMotor backRightDrive = null;


    public static final double MID_SERVO = 0.5;

    //Constructor
    public Hardware() {
    }

    public Hardware(LinearOpMode opMde) {
        opMode = opMde;
    }

    public void init() { /* Initialize standard Hardware interfaces */
        //Define and Initialize Motors and servo

        backLeftDrive = opMode.hardwareMap.dcMotor.get("backLeftDrive");
        frontLeftDrive = opMode.hardwareMap.dcMotor.get("frontLeftDrive");

        backRightDrive = opMode.hardwareMap.dcMotor.get("backRightDrive");
        frontRightDrive = opMode.hardwareMap.dcMotor.get("frontRightDrive");


        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        //Set all motors to run using encoders.
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

}
