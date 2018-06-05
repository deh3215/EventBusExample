package tw.jim.eventbusexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import tw.jim.eventbusexample.event.MessageEvent;

public class MainActivity extends Activity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv);

        Intent intent = new Intent(this, MyService.class);
        startService(intent);

        EventBus.getDefault().register(this);
        //post 前先確認有EventBus有register

        //EventBus.getDefault().post(new MessageEvent("VR46"));//Publisher  from activity

    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onStart() {


        super.onStart();
    }

    //Thread mode 有 ASYN, MAIN, POSTING, BACKGROUND 三種選擇，主線程才能修改UI，因此這裡選 MAIN
    //這 onEvent 方法，當有人呼叫 EventBus.getDefault().post(Object event)方法時，就會觸發，並把附帶的資料傳入

    @Subscribe(threadMode = ThreadMode.MAIN)
   public void onEvent(MessageEvent event) {//Subscriber
        //
        textView.setText(event.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //一定要記得取消註冊釋放資源
        EventBus.getDefault().unregister(this);
    }


}
