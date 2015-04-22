package com.example.handlerdemo;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
public class MainActivityHandler extends Activity {
    private ProgressBar progress_bar = null;
    private Button start = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress_bar = (ProgressBar)findViewById(R.id.progress_bar);
        start = (Button)findViewById(R.id.start);

        start.setOnClickListener(new StartOnClickListenr());
    }

    private class StartOnClickListenr implements OnClickListener
    {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //�ý�������ʾ����
            progress_bar.setVisibility(View.VISIBLE);
            //���̼߳��뵽handler���̶߳�����
            update_progress_bar.post(update_thread);

        }
    }
    //����һ��handler���ڲ���ɴ�����Ϣ����
    Handler update_progress_bar = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            //super.handleMessage(msg);
            //��ʾ������
            progress_bar.setProgress(msg.arg1);
            //���°ѽ��̼��뵽���̶�����
            update_progress_bar.post(update_thread);
        }       
    };//��������ֺ������Զ���Ӵ���

    Runnable update_thread = new Runnable()
    {
        int i = 0;
        public void run() {
            // TODO Auto-generated method stub
            i += 10;
            //���Ȼ��һ����Ϣ�ṹ
            Message msg = update_progress_bar.obtainMessage();
            //����Ϣ�ṹ��arg1������ֵ
            msg.arg1 = i;
            //��ʱ1s��java�е�try+catch�����Ŵ���
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            //����Ϣ���͵���Ϣ������
            update_progress_bar.sendMessage(msg);
            if(i == 100)
                //���̴߳��̶߳������Ƴ�
                update_progress_bar.removeCallbacks(update_thread);
        }       
    };

   
}