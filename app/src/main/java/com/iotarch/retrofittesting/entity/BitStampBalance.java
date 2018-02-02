package com.iotarch.retrofittesting.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by JamesHo on 2018/1/31.
 */

public class BitStampBalance {

    @SerializedName("xrp_balance")
    @Expose
    private Double xrp_balance;

    @SerializedName("usd_balance")
    @Expose
    private Double usd_balance;

    @SerializedName("btc_balance")
    @Expose
    private Double btc_balance;

    @SerializedName("eur_balance")
    @Expose
    private Double eur_balance;

    public Double getXrp_balance() {
        return xrp_balance;
    }

    public void setXrp_balance(Double xrp_balance) {
        this.xrp_balance = xrp_balance;
    }


    public Double getUsd_balance() {
        return usd_balance;
    }

    public void setUsd_balance(Double usd_balance) {
        this.usd_balance = usd_balance;
    }

    public Double getBtc_balance() {
        return btc_balance;
    }

    public void setBtc_balance(Double btc_balance) {
        this.btc_balance = btc_balance;
    }

    public Double getEur_balance() {
        return eur_balance;
    }

    public void setEur_balance(Double eur_balance) {
        this.eur_balance = eur_balance;
    }
}
