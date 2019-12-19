package org.firstinspires.ftc.teamcode.Hermes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.teamcode.Hermes.Autonomous.Init.HermesAggregated;

@Autonomous(name = "HermesAInterval", group = "Hermes")
public class HermesAInterval extends HermesAggregated {
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        //init_vuforia();

        waitForStart();
        pidTurn(1.22, 0.5, 0.1, 90, 0, 10000);
        //pidTurn(1.97, 0.5, 0.1, 0, 0, 10000);
        /*mecanumMove(-0.3, 90, 17, 3);

        sleep(10000);
        encoderDrives(0.3, -12, -12, 1.5);
        sleep(1500);
        encoderDrives(0.3, -12, -12, 1.5);
        sleep(1500);
        encoderDrives(0.3, -12, -12, 1.5);*/
    }
}