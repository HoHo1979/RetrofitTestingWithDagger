package com.iotarch.retrofittesting;

import javax.inject.Singleton;


/**
 * Created by JamesHo on 2018/1/27.
 */

public class User {

    String name="ABC";
    String age="17";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
