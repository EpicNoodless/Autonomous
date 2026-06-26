package org.firstinspires.ftc.teamcode.src.main.java.org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.ArrayList;


@TeleOp
public class C13_ArrayListOpMode extends OpMode{
    ArrayList<String> words = new ArrayList<String>();
    int wordIndex;
    double DELAY_SECS = 2;
    double nextTime = 2;

    @Overide
    public void init() {
        wordIndex = 0;
        words.add("There once there was a ship");
        words.add("that took to sea.");
        words.add("The name of the ship");
        words.add("was the Billy O Tea");

    }

    @Overide
    public void loop() {
        if (nextTime < getRuntime()) {
            wordIndex++;
            if (wordIndex >= words.size()) {
                wordIndex = words.size() - 4;
            }
            nextTime = getRuntime() + DELAY_SECS;
        }
        telemetry.addLine(words.get(wordIndex));
    }
}
