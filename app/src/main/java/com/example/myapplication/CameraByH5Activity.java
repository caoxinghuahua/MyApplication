package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;


public class CameraByH5Activity extends AppCompatActivity {
    //切记打开读写权限
    private WebView webView;
    String cameraPermission= Manifest.permission.CAMERA;
    String audioPermission=Manifest.permission.RECORD_AUDIO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_by_h5);
        webView=findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setGeolocationEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        Log.i("xxxx","webview:"+webView.getX5WebViewExtension());
        permission();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if(checkSelfPermission(cameraPermission)== PackageManager.PERMISSION_GRANTED){
//
//
////                webView.loadUrl("http://debugtbs.qq.com");
//            }else {
//                requestPermissions(new String[]{cameraPermission},100);
//            }
//        }

    }
    public void permission() {
        final RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.READ_PHONE_STATE,cameraPermission,audioPermission)
                .subscribe(result -> {
                    Log.i("camera","permission result:" + result);
                    if (result) {
//                        webView.loadUrl("http://debugtbs.qq.com");

                        webView.loadUrl("file:///android_asset/camera.html");
                    } else {
                        Toast.makeText(CameraByH5Activity.this, "请授权后使用...",Toast.LENGTH_SHORT);
                    }
                });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {

            if (grantResults != null && grantResults.length > 0) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {

                        webView.loadUrl("file:///android_asset/camera.html");
//                        webView.loadUrl("http://debugtbs.qq.com");

                    }
                }
            }
        }
    }
}
