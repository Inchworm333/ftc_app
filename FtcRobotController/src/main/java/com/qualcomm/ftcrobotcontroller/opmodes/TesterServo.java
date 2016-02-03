package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;


public class TesterServo extends OpMode {

    //test servo
    Servo MrServo;

    @Override
    public void init(){
        MrServo = hardwareMap.servo.get("Mr Servo");
    }

    @Override
    public void loop(){

        //always true statment to run servo
        if (1<2){
            MrServo.setPosition(1);
        }

    }
}
