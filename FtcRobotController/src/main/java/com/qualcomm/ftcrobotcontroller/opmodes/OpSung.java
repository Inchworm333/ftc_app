package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class OpSung extends OpMode {
    /*DC Motor*/
    DcMotor leftMotor1;
    DcMotor leftMotor2;
    DcMotor rightMotor1;
    DcMotor rightMotor2;

    @Override
    public void init() {
        //defining motors
        leftMotor1 = hardwareMap.dcMotor.get("motor_1");
        leftMotor2 = hardwareMap.dcMotor.get("motor_2");
        rightMotor1 = hardwareMap.dcMotor.get("motor_3");
        rightMotor2 = hardwareMap.dcMotor.get("motor_4");

        //reverse the left motors
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        rightMotor1.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {
        //values from gamepad
        double frontleft = -gamepad1.left_stick_y *0.7;
        double frontright = -gamepad1.right_stick_y *0.7;
        double backleft = frontleft * 0.7;
        double backright = frontright * 0.7;


        //set motor power with gamepad
        leftMotor1.setPower(frontleft);
        rightMotor1.setPower(frontright);

        rightMotor2.setPower(backright);
        leftMotor2.setPower(backleft);
    }
}