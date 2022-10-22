package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard7;

@Autonomous()
public class DistanceColorOpMode extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    ProgrammingBoard7 board = new ProgrammingBoard7();
    private double[][] colorDataTable = new double[4][3];
    @Override
    public void init() {
        board.init(hardwareMap);
    }
    public void start(){
        double red = board.getAmountRed();
        double green = board.getAmountGreen();
        double blue = board.getAmountBlue();

        double sum = blue + red + green;

        int pred = (int) (100 * red / sum);
        int pgreen = (int) (100 * green / sum);
        double pblue = (int) (100 * blue / sum);

        telemetry.addData("Percent green", pgreen);
        telemetry.addData("Percent blue", pblue);
        telemetry.addData("Percent red", pred);

        String currentcolor;


    }
    @Override
    public void loop() {

        telemetry.addData("Distance (cm)", board.getDistance(DistanceUnit.CM));
        telemetry.addData("Distance (in)", board.getDistance(DistanceUnit.INCH));

        double red = board.getAmountRed();
        double green = board.getAmountGreen();
        double blue = board.getAmountBlue();

        telemetry.addData("Amount green", green);
        telemetry.addData("Amount blue", blue);
        telemetry.addData("Amount red", red);

        double sum = blue + red + green;

        int pred = (int) (100 * red / sum);
        int pgreen = (int) (100 * green / sum);
        double pblue = (int) (100 * blue / sum);

        telemetry.addData("Percent green", pgreen);
        telemetry.addData("Percent blue", pblue);
        telemetry.addData("Percent red", pred);

        String currentcolor;

        if (pred > pgreen && pred > pblue) {
            currentcolor = "RED";
        } else if (pgreen > pblue + 0.1 && pgreen > pred) {
            currentcolor = "GREEN";
        } else if (pblue > pred && pblue > pgreen)
            currentcolor = "BLUE";
        else {
            currentcolor = "N/A";
        }

        telemetry.addData("COLOR: ", currentcolor);
    }
}