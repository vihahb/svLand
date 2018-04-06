package com.goldit.managerinfo.fragment.model;

/**
 * Created by Vi on 2/22/2018.
 */

public class Action {
    public int id;
    public String msisdn;

    public Action() {
    }

    public Action(int id, String msisdn) {
        this.id = id;
        this.msisdn = msisdn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", msisdn=" + msisdn +
                '}';
    }
}
