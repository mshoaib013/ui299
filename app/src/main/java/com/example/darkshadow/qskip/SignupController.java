package com.example.darkshadow.qskip;

public class SignupController {
    private String name;//name
    private String email;//email or phone
    private String pass;//pass
    private Integer myPosition;

    public SignupController(String name, String email, String pass, Integer myPosition) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.myPosition = myPosition;
    }

    public SignupController() {
    }

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

    public Integer getmyPosition() {
        return myPosition;
    }

    public void setmyPosition(Integer myPosition) {
        this.myPosition = myPosition;
    }
}
