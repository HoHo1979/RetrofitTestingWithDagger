package com.iotarch.retrofittesting;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends DaggerAppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() ;

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

            if(btcprice!=null)
            tx_name.setText(btcprice);

        }

        @Override
        protected Void doInBackground(Void... voids) {



            Call<BitStampBtcUsd> pairs = retrofit.create(RestApi.class).getBtcUsd();
            pairs.enqueue(new retrofit2.Callback<BitStampBtcUsd>() {
                @Override
                public void onResponse(Call<BitStampBtcUsd> call, retrofit2.Response<BitStampBtcUsd> response) {
                    btcprice = response.body().getLast();
                }

                @Override
                public void onFailure(Call<BitStampBtcUsd> call, Throwable t) {
                    Log.d(TAG, "onFailure: "+t.getMessage());
                }
            });


//            Request request = new Request.Builder()
//                    .url("http://www.vogella.com/index.html")
//                    .build();
//
//
//            try {
//                Response response = client.newCall(request).execute();
//                Log.d(TAG, "doInBackground: "+response.body().string());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            
            return null;
        }
    }


}
