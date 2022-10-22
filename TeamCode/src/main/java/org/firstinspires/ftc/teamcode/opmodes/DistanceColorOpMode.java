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

    private void justWait(int miliseconds){

        double currTime = getRuntime();
        double waitUntil = currTime + (double)(miliseconds/1000);
        while (getRuntime() < waitUntil){
        }

    }

    public void start(){

        String currentcolor = "";

        for (int i = 0; i < 3; i = i){
            //restarting the clock for all three times code runs
            resetRuntime();
            double red = board.getAmountRed();
            double green = board.getAmountGreen();
            double blue = board.getAmountBlue();

            double sum = blue + red + green;

            //calculating the percentages of what each color is
            double pred = (int) (100 * red / sum);
            double pgreen = (int) (100 * green / sum);
            double pblue = (int) (100 * blue / sum);
            double distance = board.getDistance(DistanceUnit.CM);

            telemetry.addData("Percent green", pgreen);
            telemetry.addData("Percent blue", pblue);
            telemetry.addData("Percent red", pred);

            colorDataTable[i][0] = pred;
            colorDataTable[i][1] = pgreen;
            colorDataTable[i][2] = pblue;
            colorDataTable[i][3] = distance;

            //forcing program to wait
            justWait(500);
        }


        int shortestLoc = 0;
        double shortestVal = colorDataTable[0][3];
        for (int i = 0; i < 3; i++){
            if (colorDataTable[shortestLoc][3] > colorDataTable[i][3]){
                shortestVal = colorDataTable[i][3];
                shortestLoc = i;
            }
        }

        int farthestLoc = 0;
        double farthestVal = colorDataTable[0][3];
        for (int i = 0; i < 3; i++){
            if (colorDataTable[farthestLoc][3] < colorDataTable[i][3]){
                farthestVal = colorDataTable[i][3];
                farthestLoc = i;
            }
        }


        if (colorDataTable[shortestLoc][0] > colorDataTable[farthestLoc][0]){
            currentcolor = "RED";
        }
        else if (colorDataTable[shortestLoc][1] > colorDataTable[farthestLoc][1]){
            currentcolor = "GREEN";
        }
        else if (colorDataTable[shortestLoc][2] > colorDataTable[farthestLoc][2]){
            currentcolor = "BLUE";
        }
        else {
            currentcolor = "NULL";
        }

        telemetry.addData("The color is", currentcolor);

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