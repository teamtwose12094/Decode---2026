
package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

/*
Connect to roboto  wifi
open command prompt and type "adb connect 192.168.43.1"
 */

@TeleOp(name="ControlHubTest", group="Linear OpMode")
public class ControlHubTest extends LinearOpMode {

    // Declare motors and servos
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor Potatoe = null;







    @Override
    public void runOpMode() {



        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            Potatoe = hardwareMap.get(DcMotor.class, "Potatoe");
            Potatoe.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Potatoe.setDirection(DcMotorSimple.Direction.REVERSE);

            double LeftTriggerValue = gamepad2.left_trigger;
            if (LeftTriggerValue > 0.1) {
                // If the trigger is fully pressed (1.0), the motor runs at full power.
                Potatoe.setPower(0.75);
                telemetry.addData("Motor", "Running @ " + LeftTriggerValue);
            } else {
                // If the trigger is released (value <= 0.1), stop the motor.
                Potatoe.setPower(0.0);
                telemetry.addData("Motor", "Stopped");
            }
        }
    }


}
