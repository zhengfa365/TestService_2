package com.example.lyf.testservice_1;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.btn_start)
    Button btn_start;
    @InjectView(R.id.btn_stop)
    Button btn_stop;
    @InjectView(R.id.btn_bind)
    Button btn_bind;
    @InjectView(R.id.btn_unbind)
    Button btn_unbind;
    private TestService.MyBinder myBinder;
    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder=(TestService.MyBinder)service;
            myBinder.method1();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.btn_start)
    void startService(){
        Toast.makeText(this,"startService",Toast.LENGTH_LONG).show();
        Intent serviceIntent=new Intent(this,TestService.class);
        startService(serviceIntent);
    }
    @OnClick(R.id.btn_stop)
    void stopService(){
        Intent serviceIntent=new Intent(this,TestService.class);
        stopService(serviceIntent);
    }
    @OnClick(R.id.btn_bind)
    void bindService(){
        Intent serviceIntent=new Intent(this,TestService.class);
        bindService(serviceIntent,conn,BIND_AUTO_CREATE);
    }
    @OnClick(R.id.btn_unbind)
    void unbindService(){
        unbindService(conn);
    }
}
