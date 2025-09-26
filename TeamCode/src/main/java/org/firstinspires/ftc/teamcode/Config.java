package org.firstinspires.ftc.teamcode;

public class Config {
    Hardware hardware = new Hardware();

    //Constructor
    public Config() {}

    //Drivertrain
    double wheelCPMM = (((hardware.hdHexCPR * hardware.ultraFiveToOne * hardware.ultraFourToOne) / hardware.mecanumWheelCircumfrence));
    double wheelBaseWidth = 385.0; //mm
    double wheelBaseLength = 305.0; //mm
    double wheelBaseDiameter = Math.sqrt(Math.pow(wheelBaseWidth, 2.0) + Math.pow(wheelBaseLength, 2.0)); //mm
    double wheelBaseCircumference = Math.PI * wheelBaseDiameter; //mm

    //General
    double stickDeadzone = 0.1;
    long loopTime = 20; //ms
    long loopsPerSecond = 1000/loopTime;

    //Drive
    double driveSpeedMultiplier = 2;
    double slowDriveSpeedMultiplier = 0.2;
    double acceleration = 0.05;

    //Pivot
    double pivotSpeedMultiplier = 1;
    double slowPivotSpeedMultiplier = 0.5;
}