package com.example.handlerdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends Activity {
    private TextView text_view = null;
    private Button start = null;
    private Button end = null;

    
    //ʹ��handlerʱ����Ҫ����һ��handler
    Handler handler = new Handler();
    //Ҫ��handler��������߳̿���ʹ��runnable�ӿڣ������ȶ���ýӿ�
    //�߳������иýӿڵ�run����
    Runnable update_thread = new Runnable()
    {
        public void run()
        {
            //�߳�ÿ��ִ��ʱ���"UpdateThread..."����,���Զ�����
            //textview��append���ܺ�Qt�е�append���ƣ����Ḳ��ǰ��
            //�����ݣ�ֻ��Qt�е�appendĬ�����Զ�����ģʽ
            text_view.append("\nUpdateThread...");
            //��ʱ1s���ֽ��̼߳��뵽�̶߳�����
            handler.postDelayed(update_thread, 1000);

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_view = (TextView)findViewById(R.id.text_view);
        start = (Button)findViewById(R.id.start);
        start.setOnClickListener(new StartClickListener());
        end = (Button)findViewById(R.id.end);
        end.setOnClickListener(new EndClickListener());

    }
    private class StartClickListener implements OnClickListener
    {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //���߳̽ӿ������͵��̶߳�����
            handler.post(update_thread);
        }                
    }

    private class EndClickListener implements OnClickListener
    {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //���ӿڴ��̶߳������Ƴ�
            handler.removeCallbacks(update_thread);
        }

    }

    
}
