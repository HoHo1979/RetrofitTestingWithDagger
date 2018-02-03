package com.iotarch.retrofittesting;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.iotarch.retrofittesting.entity.BitStampAuth;
import com.iotarch.retrofittesting.entity.BitStampBalance;
import com.iotarch.retrofittesting.entity.BitStampBtcUsd;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends DaggerAppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() ;


    // Put your Securet Key,API kew below and Bitstamp ID Below to create Signature.

    static String secret="Your Secret Key";
    static String apiKey="Your API Key";
    static Integer nonce = Integer.parseInt(String.valueOf(new Date().getTime()/1000));
    static String bitStampID= "Your BitStamp ID";


    @Inject
    User user;

    @Inject
    Retrofit retrofit;

    @Inject
    OkHttpClient client;

    private static TextView tx_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tx_name=findViewById(R.id.tx_name);
        tx_name.setText(user.getName());

        new MyAsync(client,retrofit).execute();

    }

    static class MyAsync extends AsyncTask<Void,Void,Void>{

        OkHttpClient client;
        
        Retrofit retrofit;

        String btcprice;

        public MyAsync(OkHttpClient client, Retrofit retrofit){
         this.client=client;
         this.retrofit=retrofit;
         if(client!=null)
             Log.d(TAG, "MyAsync: client is not null");
        }

        @Override
        protected void onPostExecute(Void aVoid) {


        }

        @Override
        protected Void doInBackground(Void... voids) {



            Call<BitStampBtcUsd> pairs = retrofit.create(RestApi.class).getBtcUsd();
            pairs.enqueue(new retrofit2.Callback<BitStampBtcUsd>() {
                @Override
                public void onResponse(Call<BitStampBtcUsd> call, retrofit2.Response<BitStampBtcUsd> response) {
                    btcprice = response.body().getLast();
                    tx_name.setText(btcprice);
                }

                @Override
                public void onFailure(Call<BitStampBtcUsd> call, Throwable t) {
                    Log.d(TAG, "onFailure: "+t.getMessage());
                }
            });


            String signature = getSignature();

     //       BitStampAuth auth = new BitStampAuth(apiKey,signature,nonce);
     //       Call<BitStampBalance> balance = retrofit.create(RestApi.class).getBalance(auth.getKey(),auth.getSignature(),auth.getNonce());

            Call<BitStampBalance> balance = retrofit.create(RestApi.class).getBalance(apiKey,signature,nonce);

            balance.enqueue(new retrofit2.Callback<BitStampBalance>() {
                @Override
                public void onResponse(Call<BitStampBalance> call, retrofit2.Response<BitStampBalance> response) {

                    Log.d(TAG, "1onResponse:"+response.body().getXrp_balance());
                    Log.d(TAG, "2onResponse:"+response.body().getUsd_balance());
                    Log.d(TAG, "3onResponse:"+response.body().getBtc_balance());

                }

                @Override
                public void onFailure(Call<BitStampBalance> call, Throwable t) {
                    Log.d(TAG, "onFailure: "+t.getMessage());
                }
            });

            
            return null;
        }
    }

    @NonNull
    private static String getSignature() {
        String message = nonce+ bitStampID + apiKey;
        String signature="";

        try {
            Mac hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(secret.getBytes(),"HmacSHA256"));
            byte[] hash = hasher.doFinal(message.getBytes());

            signature=byteArrayToHexString(hash);
            signature = signature.toUpperCase();
            Log.d(TAG, "doInBackground: "+signature);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return signature;
    }


    public static String byteArrayToHexString(byte[] array) {
        StringBuffer hexString = new StringBuffer();
        for (byte b : array) {
            int intVal = b & 0xff;
            if (intVal < 0x10)
                hexString.append("0");
            hexString.append(Integer.toHexString(intVal));
        }
        return hexString.toString();
    }

}
