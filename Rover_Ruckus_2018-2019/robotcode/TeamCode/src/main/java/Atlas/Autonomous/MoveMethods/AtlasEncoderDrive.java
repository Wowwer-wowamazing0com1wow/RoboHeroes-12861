package Atlas.Autonomous.MoveMethods;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Hardware;

import Atlas.Autonomous.AtlasAuto;
import Atlas.HardwareAtlas;

public class AtlasEncoderDrive extends LinearOpMode {
    HardwareAtlas robot = new HardwareAtlas();

    public void encoderDrive(double speed, double linch, double rinch,
                             double timeoutS) {

        double countsPerRot = 2240; // The counts per rotation
        double gearBoxRatio = 0.025; // The gear box ratio for the motors
        double wheelDiamInch = 4; // The diameter of the Atlas wheels for finding the circumference
        double countsPerInch = (countsPerRot * gearBoxRatio) / (wheelDiamInch * 3.1415);

        int newLeftTarget, newRightTarget;

        if(opModeIsActive()) {
            //Get new targets for the wheels based on the wheel's countsPerInch
            //and how far you still need to go off the motors' current position
            newLeftTarget = robot.Left.getCurrentPosition() + (int)(linch * countsPerInch);
            newRightTarget = robot.Right.getCurrentPosition() + (int)(rinch * countsPerInch);
            robot.Left.setTargetPosition(newLeftTarget);
            robot.Right.setTargetPosition(newRightTarget);

            //Set the mode of the encoders to "RUN_TO_POSITION" mode
            robot.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            //Resetting timer
            robot.runtime.reset();

            //Set the speed of the motors to the speed specified
            robot.Left.setPower(Math.abs(speed));
            robot.Right.setPower(Math.abs(speed));

            //While the program is still running, the runtime seconds is less than
            //the given time in seconds, and both the left and right motors are busy, then
            //continuously display the first 4 digits (%4d) of the new target
            //and the current position of the motors to the phone/drivers
            while(opModeIsActive() && (robot.runtime.seconds() < timeoutS) &&
                    (robot.Left.isBusy() && robot.Right.isBusy())) {
                telemetry.addData("Path 1", "Running to %4d :%4d",
                        newLeftTarget, newRightTarget);
                telemetry.addData("Path 2", "Running to %4d :%4d",
                        robot.Left.getCurrentPosition(), robot.Right.getCurrentPosition());
                telemetry.update();
            }

            //Stop all motion when done
            robot.Left.setPower(0);
            robot.Right.setPower(0);

            robot.Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
    }
}