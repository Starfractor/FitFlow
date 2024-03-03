package com.example.fitflow.Water_Food_Exercise_Data;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
TBD. Stores information like weight, height, etc.
 */
public class userInfo implements Serializable {

    public int height;
    public int weight;
    public String sex;
    public String bodyType;
    public userInfo(int height, int weight, String sex, String bodyType){
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.bodyType = bodyType;
    }
    public void saveInfo(Context context) {
        File file = new File(context.getFilesDir(), "saved_data/user/userInfo.ser");
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            stream.writeObject(this);
            System.out.println("Saved userInfo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
