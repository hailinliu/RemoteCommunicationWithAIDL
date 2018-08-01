package com.ccb.admin.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ccb.admin.xiaodemo.IService;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


private Button bt;
private IService iService;
    private static final String TAG = "MainActivity";
private ServiceConnection connection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iService = IService.Stub.asInterface(service);
        try {
            iService.sayHello();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.i(TAG, "onServiceDisconnected: "+name);
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.bt);
        Button bt1 = findViewById(R.id.bt1);
        bt1.setOnClickListener(this);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.bt:
            Intent intent = new Intent();
            intent.setAction("com.ccb.admin.xiaodemo.IService");
            intent.setPackage("com.ccb.admin.xiaodemo");
            bindService(intent,connection,BIND_AUTO_CREATE);
            //startService(intent);
        case R.id.bt1:
            Intent intent1 = new Intent();
            intent1.setAction("com.ccb.admin.xiaodemo.IService");
            intent1.setPackage("com.ccb.admin.xiaodemo");
            startService(intent1);
            break;
    }
    }
}
