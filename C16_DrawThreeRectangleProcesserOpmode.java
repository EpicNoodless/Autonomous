package org.firstinspires.ftc.teamcode.src.main.java.org.firstinspires.ftc.teamcode;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

@Autonomous
public class C16_DrawThreeRectangleProcesserOpmode extends OpMode {
    private Limelight3A limelight;
    private VisionPortal visionPortal;
    private ThreeRectangleProcesser drawThreeRectangleProcesser;
    @Override
    public void init() {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        //limelight = hardwareMap.get(Limelight3A.class,"limelight");
        drawThreeRectangleProcesser = new ThreeRectangleProcesser();
        visionPortal = VisionPortal.easyCreateWithDefaults(webcamName, drawThreeRectangleProcesser);
    }
    @Override
    public void init_loop() {

    }
    @Override
    public void start() {
        visionPortal.stopStreaming();
    }
    @Override
    public void loop() {
    }
}

class ThreeRectangleProcesser implements VisionProcessor {
    public Rect rectLeft = new Rect (110, 42, 40, 40);
    public Rect rectMiddle = new Rect (160, 42, 40, 40);
    public Rect rectRight = new Rect (210, 42, 40, 40);
    Selected selection = Selected.NONE;

    @Override
    public void init(int width, int hight, CameraCalibration calibration) {
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        return null;
    }

    private  android.graphics.Rect makeGraphicsRect(Rect rect, float scaleBmpPxToCanvasPx) {
        int left = Math.round(rect.x * scaleBmpPxToCanvasPx);
        int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
        int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);
        int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);

        return new android.graphics.Rect(left, top, right, bottom);
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight,
                            float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        Paint selectedPaint = new Paint();
        selectedPaint.setColor(Color.RED);
        selectedPaint.setStyle(Paint.Style.STROKE);
        selectedPaint.setStrokeWidth(scaleCanvasDensity * 4);

        Paint nonSelectedPaint = new Paint(selectedPaint);
        nonSelectedPaint.setColor(Color.GREEN);

        android.graphics.Rect drawRectangleLeft = makeGraphicsRect(rectLeft, scaleBmpPxToCanvasPx);
        android.graphics.Rect drawRectangleMiddle = makeGraphicsRect(rectMiddle, scaleBmpPxToCanvasPx);
        android.graphics.Rect drawRectangleRight = makeGraphicsRect(rectRight, scaleBmpPxToCanvasPx);

        switch (selection) {
            case LEFT :
                canvas.drawRect(drawRectangleLeft, selectedPaint);
                canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
                canvas.drawRect(drawRectangleRight, nonSelectedPaint);
                break;
            case MIDDLE :
                canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
                canvas.drawRect(drawRectangleMiddle, selectedPaint);
                canvas.drawRect(drawRectangleRight, nonSelectedPaint);
                break;
            case RIGHT :
                canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
                canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
                canvas.drawRect(drawRectangleRight, selectedPaint);
                break;
            case NONE :
                canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
                canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
                canvas.drawRect(drawRectangleRight, nonSelectedPaint);
                break;
        }

    }

    public enum Selected {
        NONE,
        LEFT,
        MIDDLE,
        RIGHT
    }
}
