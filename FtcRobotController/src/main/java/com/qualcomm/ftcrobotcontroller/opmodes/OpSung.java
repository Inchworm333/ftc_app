package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class OpSung extends OpMode {
    //DC Motors//
    DcMotor leftMotor1;
    DcMotor leftMotor2;
    DcMotor rightMotor1;
    DcMotor rightMotor2;
    DcMotor sliderLeft;
    DcMotor sliderRight;
    DcMotor sliderCenter;

    //Servos//
    Servo grabberLeft;
    Servo grabberRight;
    Servo grabberCenter;

    //grabber increment
    double topgrabberadd = 0.1;
    double bottomgrabberadd = 0.1;

    //top grabber position
    double topgrabber;
    double bottomgrabber;

    @Override
    public void init() {
        //driving motors//
        leftMotor1 = hardwareMap.dcMotor.get("left_1");
        leftMotor2 = hardwareMap.dcMotor.get("left_2");
        rightMotor1 = hardwareMap.dcMotor.get("right_1");
        rightMotor2 = hardwareMap.dcMotor.get("right_2");

        //slider motors//
        sliderLeft = hardwareMap.dcMotor.get("slider_left");
        sliderRight = hardwareMap.dcMotor.get("slider_right");
        sliderCenter = hardwareMap.dcMotor.get("slider_center");

        //slider servos//
        grabberLeft = hardwareMap.servo.get("grabber_left");
        grabberRight = hardwareMap.servo.get("grabber_right");
        grabberCenter = hardwareMap.servo.get("grabber_center");

        //reverse the left motors//
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        rightMotor1.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {
        //values from gamepad1 driving//
        double front_left = -gamepad1.left_stick_y * 0.7;
        double front_right = -gamepad1.right_stick_y * 0.7;
        double back_left = front_left * 0.7;
        double back_right = front_right * 0.7;

        //values from gamepad2 sliding//
        double slider_left = gamepad2.left_stick_y;
        double slider_right = gamepad2.left_stick_y;
        double slider_center = gamepad2.right_stick_y;

        //values for gamepad2 grabbing//
        double grabber_left_right = gamepad2.left_trigger;
        double grabber_center = gamepad2.right_trigger;
        boolean grabber_left_right_reverse = gamepad2.left_bumper;
        boolean grabber_center_reverse = gamepad2.right_bumper;


        //set motor power with gamepad 1 for driving//
        leftMotor1.setPower(front_left);
        rightMotor1.setPower(front_right);

        leftMotor2.setPower(back_left);
        rightMotor2.setPower(back_right);

        //set motor power with gamepad 2 for sliding//
        sliderLeft.setPower(slider_left);
        sliderRight.setPower(slider_right);
        sliderCenter.setPower(slider_center);
        sliderLeft.setDirection(DcMotor.Direction.REVERSE);

        //set servo power with gamepad 2 for grabbing//
        while (grabber_left_right > 0) {
            topgrabber += topgrabberadd;
        }
        while (grabber_center > 0) {
            bottomgrabber += bottomgrabberadd;
        }
        while (grabber_left_right_reverse) {
            topgrabber -= topgrabberadd;
        }
        while (grabber_center_reverse) {
            bottomgrabber -= bottomgrabberadd;
        }

        //send where grabber is to servo
        grabberLeft.setPosition(topgrabber);
        grabberRight.setPosition(-topgrabber);
        grabberCenter.setPosition(bottomgrabber);
    }
}