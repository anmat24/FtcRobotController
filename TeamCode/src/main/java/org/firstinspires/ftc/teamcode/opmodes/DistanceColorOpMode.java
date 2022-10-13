package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard7;

@TeleOp()
public class DistanceColorOpMode extends OpMode {
    ProgrammingBoard7 board = new ProgrammingBoard7();
    @Override
    public void init() {
        board.init(hardwareMap);
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
        else currentcolor = "N/A";


        telemetry.addData("COLOR: ", currentcolor);
    }
}