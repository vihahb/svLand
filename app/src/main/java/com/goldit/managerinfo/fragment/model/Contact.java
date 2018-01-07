package com.goldit.managerinfo.fragment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by baonguyen on 12/26/17.
 */

public class Contact {

    /**
     * success : true
     * code : 205
     * data : [[{"id":2,"name":"Trọng Linh","msisdn":"01675042123","email":"tronglinh@gmail.com","project_id":"4"}],[{"id":7,"name":"Nga Lê","msisdn":"01292701193","email":"ngale@gmail.com","project_id":"3"}],[{"id":10,"name":"Xuân Sang","msisdn":"0966547123","email":"xuansang@gmail.com","project_id":"4"}]]
     */

    @SerializedName("success")
    private boolean success;
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private List<User> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public static class User implements Parcelable {
        /**
         * id : 2
         * name : Trọng Linh
         * msisdn : 01675042123
         * email : tronglinh@gmail.com
         * project_id : 4
         */
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("msisdn")
        private String msisdn;
        @SerializedName("email")
        private String email;

        @SerializedName("project_id")
        private String project_id;

        @SerializedName("status")
        private String status;
        @SerializedName("note")
        private String note;

        public void setStatus(String status) {
            this.status = status;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getNote() {
            return note;
        }

        public String getStatus() {
            return status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMsisdn() {
            return msisdn;
        }

        public void setMsisdn(String msisdn) {
            this.msisdn = msisdn;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getProject_id() {
            return project_id;
        }

        public void setProject_id(String project_id) {
            this.project_id = project_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeString(this.msisdn);
            dest.writeString(this.email);
            dest.writeString(this.project_id);
            dest.writeString(this.status);
        }

        public User() {
        }

        protected User(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.msisdn = in.readString();
            this.email = in.readString();
            this.project_id = in.readString();
            this.status = in.readString();
        }

        public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
            @Override
            public User createFromParcel(Parcel source) {
                return new User(source);
            }

            @Override
            public User[] newArray(int size) {
                return new User[size];
            }
        };
    }
}
