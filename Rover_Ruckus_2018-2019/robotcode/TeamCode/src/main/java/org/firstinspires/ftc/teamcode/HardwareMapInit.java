package org.firstinspires.ftc.teamcode;

import android.view.View;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;

public class HardwareMapInit {

    public NormalizedColorSensor Color_Sensor;
    public NormalizedColorSensor Color_Sensor2;
    public DcMotor Right;
    public DcMotor Left;
    public Servo Marker;
    public View relativeLayout;
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        Color_Sensor = hwMap.get(NormalizedColorSensor.class, "ColorSensor");
        Color_Sensor2 = hwMap.get(NormalizedColorSensor.class, "ColorSensor2");

        Right = hwMap.get(DcMotor.class, "Right");
        Left = hwMap.get(DcMotor.class, "Left");
        Right.setDirection(DcMotorSimple.Direction.REVERSE);

        Marker = hwMap.get(Servo.class, "Marker");

        // Set them to 0
        Right.setPower(0);
        Left.setPower(0);

        // Make them not use encoders by default but you can set it to use encoders in the program
        // manually with "RUN_USING_ENCODER" and "STOP_AND_RESET_ENCODER"
        Right.setMode(DcMotor.RunMode. RUN_WITHOUT_ENCODER);
        Left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}




