package com.goldit.managerinfo.fragment.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by baonguyen on 12/28/17.
 */

public class Update {


    /**
     * success : true
     * code : 210
     * data : {"success":"Update Customer done id = 2"}
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

    public static class DataBean {
        /**
         * success : Update Customer done id = 2
         */

        @SerializedName("success")
        private String success;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }
    }
}
