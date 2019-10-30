package org.firstinspires.ftc.teamcode.Autonomous.Init;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@SuppressWarnings({"WeakerAccess"})
public class HardwareClass {
    //Sensors
    public NormalizedColorSensor ColorSensor;
    //public DistanceSensor DistanceSensor;

    //Right motors
    public DcMotor Right;

    //Left motors
    public DcMotor Left;

    //Arm
    public DcMotor Arm;

    WebcamName webcamName = null;

    public CRServo Clamp;
    //IMU sensor
    public BNO055IMU imu;

    // The elapsed time
    public ElapsedTime runtime = new ElapsedTime();


    
    public void init(HardwareMap ahwMap) {
        ColorSensor = ahwMap.get(NormalizedColorSensor.class, "ColorSensor");
        //DistanceSensor = ahwMap.get(DistanceSensor.class, "DistanceSensor");

        webcamName = ahwMap.get(WebcamName .class, "Webcam 1");

        Clamp = ahwMap.crservo.get("Clamp");

        //IMU sensor
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;
        imu = ahwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        Right = ahwMap.get(DcMotor.class, "Right");
        Right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Right.setDirection(DcMotorSimple.Direction.REVERSE);

        Left = ahwMap.get(DcMotor.class, "Left");
        Left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Arm = ahwMap.get(DcMotor.class, "Arm");

        // Make the motors not use encoders by default but you can set it to use encoders in the
        // program manually with "RUN_USING_ENCODER" and "STOP_AND_RESET_ENCODER"
        Right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
