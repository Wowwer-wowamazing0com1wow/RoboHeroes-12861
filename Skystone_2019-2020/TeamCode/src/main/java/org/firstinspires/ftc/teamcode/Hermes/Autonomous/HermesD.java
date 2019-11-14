package org.firstinspires.ftc.teamcode.Hermes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Hermes.Autonomous.Init.HermesAggregated;
import org.firstinspires.ftc.teamcode.PID;

@Autonomous(name = "HermesD", group = "Autonomous")
@Disabled
public class HermesD extends HermesAggregated {
    private double speed = 0.1, pidOutput = 0;
    private PID pid = new PID(0.5, 0.5, 0, 0);
    private double locationV = -1000;
    public boolean VuforiaFound = false;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();


        encoderDrives(0.3, 22, 22, 2);
        /*pid.setParams(1.45, 0.5, 0, 90);
        run_pid(0, 5500, pid, true);

        vuforia();
        locationV = translation.get(2);
        if (locationV != 0 && locationV != -1000) {
            run_pid(0, 1500, false, 0.5, 0.3, 0, 0);
        }

        pid.setParams(1.52, 0.5, 0, 0);
        run_pid(0, 5500, pid, true);*/
        encoderDrives(0.3, 10, 10,2);
        sleep(250);
        encoderDrives(0.3, -10, -10,1);
        encoderDrives(0.2, 14, -14,1);
        encoderDrives(0.5, 103,103,4);
        sleep(750);
        encoderDrives(0.5, -60, -60,3.5);
    }
}