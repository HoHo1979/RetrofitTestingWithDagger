package com.iotarch.retrofittesting.entity;

import android.os.SystemClock;
import android.util.Log;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static com.iotarch.retrofittesting.MainActivity.byteArrayToHexString;

/**
 * Created by JamesHo on 2018/1/31.
 */

public class BitStampAuth {

    String apiKey;

    String signature;

    Integer nonce;

    private String apiSecrect;

    String bitStampID;

    public BitStampAuth(String bitStampID, String apikey, String apiSecrect){
        this.bitStampID=bitStampID;
        this.apiKey=apikey;
        this.apiSecrect=apiSecrect;
    }

    public BitStampAuth(){

    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }


    public void setApiSecrect(String apiSecrect) {
        this.apiSecrect = apiSecrect;
    }

    public String getBitStampID() {
        return bitStampID;
    }

    public void setBitStampID(String bitStampID) {
        this.bitStampID = bitStampID;
    }

    public String getSignature() {


        nonce = Integer.parseInt(String.valueOf(new Date().getTime()/1000));

        String message = nonce+ bitStampID+ apiKey;

        String signature="";

        try {
            Mac hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(apiSecrect.getBytes(),"HmacSHA256"));
            byte[] hash = hasher.doFinal(message.getBytes());

            signature=byteArrayToHexString(hash);
            signature = signature.toUpperCase();


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getNonce() {
        return nonce;
    }

    public void setNonce(Integer nonce) {
        this.nonce = nonce;
    }
}
