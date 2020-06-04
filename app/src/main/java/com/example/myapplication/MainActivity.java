package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
    //http://10.115.3.150:8189/c/d?p=
    private String url = "http://flight.gomeplus.com/flight?";
    private String imageUrl = "http://pic5.zhongsou.com/img?id=522f8c6e488097cef53!sy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
//        setContentView(R.layout.activity_main);
//
//
//        BitmapFactory.Options options=new BitmapFactory.Options();

        testOkhttp(""+(10022));

        /**    new Thread() {
         public void run() {
         testOkhttp();
         //                  Map<String,String>  map=new HashMap<String, String>();
         //                  map.put("slotId","10014");
         //                  map.put("requestType","2");
         //                  getPostData(map);
         }
         }.start();*/
        java.util.Base64.Encoder encoder= java.util.Base64.getEncoder();

        Log.i("xxxx","android base64:"+ Base64.encodeToString("test1111111".getBytes(),Base64.DEFAULT));
        Log.i("xxxx","java base64:"+encoder.encodeToString("test1111111".getBytes()));
    }

    private void testOkhttp(String slotId) {
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.readTimeout(3000, TimeUnit.SECONDS);
        final OkHttpClient httpClient = builder.build();


        //type=1 同步get 2异步get 3同步post 4异步post
        //同步时不能在主线程直接调用
        int type = 1;
        try {
            if (type == 1) {
                new Thread(){
                    public void run(){
                        try {
                            final Request request = new Request.Builder().url(url + "slotId=10022&requestType=2")
                                    .build();
                            final Call call = httpClient.newCall(request);
                            Response response = call.execute();
                            Timer timer=new Timer();
                            TimerTask timerTask=new TimerTask() {
                                @Override
                                public void run() {
                                    call.cancel();
                                    Log.i("xxx", "get sync response timeout cancel");
                                }
                            };
                            timer.schedule(timerTask,10);

                            if (response.isSuccessful()) {
                                Log.i("xxx", "get sync response:" + response.body().string());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        setTitle("sync");
                                    }
                                });
                            } else {
                                Log.i("xxx", "except" );
                                throw new IOException("Unexpected code " + response);

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            } else if (type == 2) {
                final Request request = new Request.Builder().url(url + "slotId=10022&requestType=2")
                        .build();
                Call call = httpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }


                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String json = response.body().string();
                        Log.i("xxx", "get async response:" + json);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setTitle(json);
                            }
                        });
                    }
                });

            }
            /**else if (type == 3) {
                new Thread(){
                    public void run(){
                        try {
                            RequestBody requestBody = new FormEncodingBuilder()
                                    .add("slotId", "10022")
                                    .add("requestType", "2")
                                    .build();
                            Request request = new Request.Builder().url(url)
                                    .post(requestBody)
                                    .build();
                            Response response = httpClient.newCall(request).execute();
                            if (response.isSuccessful()) {
                                Log.i("xxx", "post sync response:" + response.body().string());
                            } else {
                                throw new IOException("Unexpected code " + response);
                            }
                        }catch (Exception e){

                        }
                    }
                }.start();
            } else if (type == 4) {
                //flag=1;键值对 flag=2;json格式
                int flag = 2;
                RequestBody requestBody = null;
                if (flag == 1) {
                    requestBody = new FormEncodingBuilder()
                            .add("slotId", ""+slotId)
                            .add("requestType", "2")
                            .build();
                } else if (flag == 2) {

                    JSONObject object = new JSONObject();
                    object.put("slotId", ""+slotId);
                    object.put("requestType", "2");
                    requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), object.toString());
                }

                final Request request = new Request.Builder().url(url)
                        .post(requestBody)
                        .build();
                final long start=System.currentTimeMillis();
                Log.i("xxx", "start:" + start);
                Call call = httpClient.newCall(request);
                call.enqueue(new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("xxx", "error:" + (System.currentTimeMillis()-start));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.i("xxx", "end:" + (System.currentTimeMillis()-start));
//                        Log.i("xxx", "post async response:" + response.body().string() + "\n" + response.headers());
                    }

                });
            }*/
        } catch (Exception e) {
            Log.i("xxx","error:"+e.toString());
        }
    }

    //定义activity的进入和退出动画
    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }


    @Override
    public void onStart() {
        super.onStart();



    }

    @Override
    public void onStop() {
        super.onStop();


    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//    }
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
//    }
}