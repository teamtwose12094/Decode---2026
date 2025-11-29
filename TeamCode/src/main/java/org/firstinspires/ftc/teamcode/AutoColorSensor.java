package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;



@Autonomous(name="AutoColorSensor", group="Auto")
public class AutoColorSensor extends LinearOpMode {

    private com.qualcomm.robotcore.hardware.ColorSensor ColorSensor;
    private DistanceSensor distanceSensor;
    private ColorSensor CoolSensor;

    @Override
    public void runOpMode() {
        waitForStart();
        while (opModeIsActive()) {

            CoolSensor = hardwareMap.get(ColorSensor.class, "ColorSensor");

            String detectedColor = "NONE";
            int red = CoolSensor.red();
            int green = CoolSensor.green();
            int blue = CoolSensor.blue();


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
            telemetry.addData("ColorSensor", red);
            telemetry.addData("ColorSensor", blue);
            telemetry.addData("ColorSensor", green);
            telemetry.update();

        }
    }
}
