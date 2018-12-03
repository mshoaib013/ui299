package com.example.darkshadow.qskip;

import java.security.PrivateKey;

public class SignupControllerOrg {
    private String name;//name
    private String email;//email or phone
    private String pass;//pass
    private Integer totalInQue;//Total service que
    private Integer runningSerial;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getTotalInQue() {
        return totalInQue;
    }

    public void setTotalInQue(Integer totalInQue) {
        this.totalInQue = totalInQue;
    }

    public Integer getRunningSerial() {
        return runningSerial;
    }

    public void setRunningSerial(Integer runningSerial) {
        this.runningSerial = runningSerial;
    }

    public SignupControllerOrg() {

    }

    public SignupControllerOrg(String name, String email, String pass, Integer totalInQue, Integer runningSerial) {

        this.name = name;
        this.email = email;
        this.pass = pass;
        this.totalInQue = totalInQue;
        this.runningSerial = runningSerial;
    }
}
