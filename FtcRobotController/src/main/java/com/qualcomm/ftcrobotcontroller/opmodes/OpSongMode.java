package com.qualcomm.ftcrobotcontroller.opmodes;

import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;

public class OpSongMode extends OpMode {
    /*DC Motor*/
    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void init() {
        //defining motors
        leftMotor = hardwareMap.dcMotor.get("motor_1");
        rightMotor = hardwareMap.dcMotor.get("motor_2");
       //mediaPlayer  = MediaPlayer.create(this, R.raw.digdug);

        //reverse the left motor
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        //values from gamepad
        float left = -gamepad1.left_stick_y;
        float right = -gamepad1.right_stick_y;


        //set motor power with gamepad
        leftMotor.setPower(left);
        rightMotor.setPower(right);

    }
}
