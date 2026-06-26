package org.firstinspires.ftc.teamcode.src.main.java.org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp
public class C13_ArraysOpMode extends OpMode{
    String[] words = {"There once there was a ship", "that took to sea.", "The name of the ship",
            "was the Billy O Tea,"};
    int wordIndex;
    double DELAY_SECS = 2;
    double nextTime = 2;

    @Overide
    public void init() {
        wordIndex = 0;
    }

    @Overide
    public void loop() {
        if (nextTime < getRuntime()) {
            wordIndex++;
            if (wordIndex >= words.length) {
                wordIndex = words.length - 4;
            }
            nextTime = getRuntime() + DELAY_SECS;
        }
        telemetry.addLine(words[wordIndex]);
    }
}
