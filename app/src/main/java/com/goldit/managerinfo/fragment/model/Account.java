package com.goldit.managerinfo.fragment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by baonguyen on 12/23/17.
 */

public class Account implements Parcelable {


    /**
     * success : true
     * code : 201
     * data : {"success":"login fail"}
     */
    @SerializedName("success")
    private boolean success;
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * success : login fail
         */

        @SerializedName("success")
        private String success;

        @SerializedName("token")
        private String token;

        @SerializedName("type")
        private String type;

        @SerializedName("user_id")
        private int user_id;

        public String getToken() {
            return token;
        }

        public String getType() {
            return type;
        }

        public int getUser_id() {
            return user_id;
        }

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.success);
            dest.writeString(this.token);
            dest.writeString(this.type);
            dest.writeInt(this.user_id);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.success = in.readString();
            this.token = in.readString();
            this.type = in.readString();
            this.user_id = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeInt(this.code);
        dest.writeParcelable(this.data, flags);
    }

    public Account() {
    }

    protected Account(Parcel in) {
        this.success = in.readByte() != 0;
        this.code = in.readInt();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel source) {
            return new Account(source);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
}
