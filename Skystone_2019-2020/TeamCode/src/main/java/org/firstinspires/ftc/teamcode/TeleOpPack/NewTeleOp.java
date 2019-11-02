package org.firstinspires.ftc.teamcode.TeleOpPack;

import android.database.sqlite.SQLiteDiskIOException;
import android.transition.Slide;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Autonomous.Init.HardwareClass;

@TeleOp(name= "NewTeleOp", group= "Pushbot")
public class NewTeleOp extends OpMode {
        //Making the slower arm and elbow toggle (driver 2)
        private ElapsedTime openClaw = new ElapsedTime();
        private boolean switchedS = false;
        private boolean usedRecently = false;
        private double controlSpeedE = 1, controlSpeedS = 1;

        //Making the slower robot toggle (driver 1)
        private ElapsedTime rmove = new ElapsedTime();
        private ElapsedTime latching = new ElapsedTime();

        private boolean latchReset = false;
        private boolean robotCSpeed = false; // the boolean for the robot's speed to be able to slow it down
        private boolean robotUsedRecent = false;
        private double robotControlSpeed = 0.7;

        private double upArmSpeed = 0, downArmSpeed = 0;
        private double turnspeed = 0;
        private double speed = 0;
        private double ElbowSpeed = 0;

        private double LElbowSpeed = 0;

        CRServo Clamp;


        //Set the speed of the motors when the Left or Right sticks are not idle

        HardwareClass robot = new HardwareClass();

        public void init() {
            robot.init(hardwareMap);


            //DriveL.setDirection(DcMotor.Direction.REVERSE);
        }

        @Override
        public void loop() {
            upArmSpeed = (gamepad2.right_trigger * 0.7) * controlSpeedS;
            downArmSpeed = (gamepad2.left_trigger * 0.7) * controlSpeedS;
            turnspeed = gamepad1.left_stick_x * robotControlSpeed;
            speed = gamepad1.left_stick_y * robotControlSpeed;



        /*
        -----------------------------
        |                           |
        |                           |
        |         Gamepad 2         |
        |                           |
        |                           |
        -----------------------------
         */
            //The Shoulder

            //Controlling Shoulder arm speed
            if (gamepad2.a && !usedRecently) {
                if (switchedS) {
                    controlSpeedS = 1;
                    controlSpeedE = 1;
                    switchedS = false;
                } else if (!switchedS) {
                    controlSpeedS = 0.7;
                    controlSpeedE = 0.7;
                    switchedS = true;
                }
                usedRecently = true;
                robot.runtime.reset();
            }

            //The LClamp
            if (gamepad1.right_bumper) {
                robot.Clamp.setPower(1);
                openClaw.reset();
                //Wait 250 milliseconds before stopping the movement of the clamp

            }else if (gamepad1.left_bumper) {
                robot.Clamp.setPower(0);
            }else{
                robot.Clamp.setPower(0.5);
            }



        /*
        -----------------------------
        |                           |
        |                           |
        |         Gamepad 1         |
        |                           |
        |                           |
        -----------------------------
         */
            //Resetting Latch
            //Turning
            if (gamepad1.left_stick_x >= 0.1 || gamepad1.left_stick_x <= -0.1) {
                robot.Left.setPower(-turnspeed);
                robot.Right.setPower(turnspeed);
            }

            //Moving
            if (gamepad1.left_stick_y >= 0.1 || gamepad1.left_stick_y <= -0.1) {
                robot.Left.setPower(-speed);
                robot.Right.setPower(-speed);
            }
            if (gamepad1.right_stick_y >= 0.1) {
                robot.Arm.setPower(-1);
            }
            if (gamepad1.right_stick_y <= -0.1) {
                robot.Arm.setPower(1);
            }
            if (gamepad1.right_stick_y >= -0.1 && gamepad1.right_stick_y <= 0.1) {
                robot.Arm.setPower(0);
            }
            //Making the robot stop when it's set to 0
            if (gamepad1.left_stick_y == 0 && gamepad1.right_stick_x == 0) {
                robot.Left.setPower(0);
                robot.Right.setPower(0);
            }

            //Tilting and resetting back our marker servo platform
            //The winch

            if (gamepad1.x && !robotUsedRecent) {
                if (robotCSpeed) {
                    robotControlSpeed = 0.7;
                    robotCSpeed = false;
                } else if (!robotCSpeed) {
                    robotControlSpeed = 0.4;
                    robotCSpeed = true;
                }
                robotUsedRecent = true;
                rmove.reset();
            }



            // Setting the used recently boolean to true after 200
            // milliseconds after the a button was pressed
            if (robot.runtime.milliseconds() > 200) {
                usedRecently = false;
            }

            if (rmove.milliseconds() > 200) {
                robotUsedRecent = false;
            }
            // Setting the LClamp power to 0.5 after the open claw is greater than 250 milliseconds

        }

    }

