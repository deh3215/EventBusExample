package tw.jim.eventbusexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import tw.jim.eventbusexample.event.MessageEvent;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.v("Jimmy", "onCreate");
        EventBus.getDefault().register(this);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("Jimmy", "onStartCommand");

        EventBus.getDefault().post(new MessageEvent("VR46"));//Publisher  from service

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");

        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {//Subscriber
        //
        Log.v("Jimmy", "onEvent");
    }
}
