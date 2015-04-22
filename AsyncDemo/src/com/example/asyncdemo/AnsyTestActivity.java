package com.example.asyncdemo;

import android.app.Activity;
 import android.os.AsyncTask;
 import android.os.Bundle;
 import android.view.View;
 import android.view.View.OnClickListener;
 import android.widget.Button;
 import android.widget.TextView;

public class AnsyTestActivity extends Activity {
     /** Called when the activity is first created. */
    
     TextView text =null;
     Button button=null;
     String str=null;
     AnsyTry anys=null;
     double result=0;
    
     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         text=(TextView) findViewById(R.id.text);
         button=(Button) findViewById(R.id.button);
         str="wei";
         button.setOnClickListener(new OnClickListener() {
            
             @Override
             public void onClick(View v) {
                   // TODO Auto-generated method stub
               anys=new AnsyTry(text);
                 anys.execute(str);
                
             }
         });
     }
    
    
     class AnsyTry extends AsyncTask<String, TextView, Double>{
        
         TextView te=null;
        
         public AnsyTry(TextView te) {
             super();
             this.te = te;
         }

        @Override
         protected Double doInBackground(String... params) {
             // TODO Auto-generated method stub
             double dou=0;
             if(params[0].equals("wei")){
                 System.out.println(Thread.currentThread().getName()+"  recive wei");
                 dou=100;
             }
             publishProgress(te);
             return dou;
         }

        @Override
         protected void onPostExecute(Double result) {
             // TODO Auto-generated method stub
             super.onPostExecute(result);
             System.out.println("postExecute---double---"+result);
             text.setText(result+"");
         }

        @Override
         protected void onPreExecute() {
             // TODO Auto-generated method stub\
             System.out.println("pretExecute------");
             super.onPreExecute();
         }

        @Override
         protected void onProgressUpdate(TextView... values) {
             // TODO Auto-generated method stub
             values[0].setText(values[0].getText()+"1");
             super.onProgressUpdate(values);
         }
        
     }
 }



