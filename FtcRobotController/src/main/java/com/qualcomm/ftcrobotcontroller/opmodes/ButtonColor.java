package com.qualcomm.ftcrobotcontroller.opmodes;


import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;


public class ButtonColor extends OpMode {

    //DcMotors for driving
    DcMotor leftMotor1;
    DcMotor leftMotor2;
    DcMotor rightMotor1;
    DcMotor rightMotor2;

    //Servo for Turning
    Servo buttonpusher;

    //Color Sensors
    ColorSensor leftsensor;
    ColorSensor rightsensor;

    //Touch Sensor Button
    TouchSensor bigbutton;

    //Strings for Colors
    public String ColorOption;
    public String LeftColor;
    public String RightColor;

    @Override
    public void init() {

        //logging color sensor hopefully
        hardwareMap.logDevices();

        //defining motors
        leftMotor1 = hardwareMap.dcMotor.get("motor_1");
        leftMotor2 = hardwareMap.dcMotor.get("motor_2");
        rightMotor1 = hardwareMap.dcMotor.get("motor_3");
        rightMotor2 = hardwareMap.dcMotor.get("motor_4");

        //getting reference for color sensor (object)
        leftsensor = hardwareMap.colorSensor.get("leftsensor");
        rightsensor = hardwareMap.colorSensor.get("rightsensor");

        //getting reference for button
        bigbutton = hardwareMap.touchSensor.get("bigbutton");

        //Set default team red
        if (ColorOption == ""){
            ColorOption = "red";
        }

        //Choose team color from button
        if (bigbutton.isPressed() && ColorOption == "red") {
            ColorOption = "blue";
        }
        else if (bigbutton.isPressed() && ColorOption == "blue"){
            ColorOption = "red";
        }

        // send the info back to driver station using telemetry function.
        telemetry.addData("Clear", ColorOption == "");
        telemetry.addData("Red  ", ColorOption == "red");
        telemetry.addData("Blue ", ColorOption == "blue");

        // change the background color to match the color chosen by bigbutton.
        // pass a reference to the hue, saturation, and value array as an argument
        // to the HSVToColor method.
        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
            }
        });

    }

    @Override
    public void loop() {
        //Enable/Disable Color Sensor Led
        leftsensor.enableLed(false);
        rightsensor.enableLed(false);

        //LeftSensor Color
        while (leftsensor.red() > leftsensor.blue()) {
            LeftColor = "red";
        }
        while (leftsensor.blue() > leftsensor.red()) {
            LeftColor = "blue";
        }

        //RightSensor Color
        while (rightsensor.red() > rightsensor.blue()) {
            RightColor = "red";
        }
        while (rightsensor.blue() > rightsensor.red()) {
            RightColor = "blue";
        }

        //Select Correct Color Side
        if (ColorOption == LeftColor) {
            buttonpusher.setPosition(0.45);
        } else if (ColorOption == RightColor) {
            buttonpusher.setPosition(0.75);
        }
    }


        /////////////////////////////
        //CHANGING BACKGROUND COLOR//
        /////////////////////////////

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0,0,0};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);
    }

