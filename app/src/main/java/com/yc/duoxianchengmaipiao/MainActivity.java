package com.yc.duoxianchengmaipiao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvKaishi;
    int ticket = 50;//一个窗口有100张票
    //步骤1:创建线程类,继承自Thread类
    //因为这里需要有两个操作:一个窗口卖票速度是1s/张,一个窗口是3s/张
    //所以需要创建两个Thread的子类

    //第一个Thread子类实现一个窗口卖票速度是1s/张
    private class MyThread1 extends Thread{

        //private int ticket = 100;//一个窗口有100张票
        private String name; //窗口名, 也即是线程的名字

        public MyThread1(String name){
            this.name=name;
        }

        //在run方法里复写需要进行的操作:卖票速度是1s/张
        @Override
        public void run(){
            while (ticket>0){
                ticket--;
                //System.out.println(name + "卖掉了1张票，剩余票数为:"+ticket);
                Log.i("====",name + "卖掉了1张票，剩余票数为:"+ticket);
                try {
                    Thread.sleep(1000);//卖票速度是1s一张
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //第二个Thread子类实现一个窗口卖票速度是3s/张
    private class MyThread2 extends Thread{

        //private int ticket = 100;//一个窗口有100张票
        private String name; //窗口名, 也即是线程的名字

        public MyThread2(String name){
            this.name=name;
        }

        //在run方法里复写需要进行的操作:卖票速度是3s/张
        @Override
        public void run(){
            while (ticket>0){
                ticket--;
                //System.out.println(name + "卖掉了1张票，剩余票数为:"+ticket);
                Log.e("====",name + "卖掉了1张票，剩余票数为:"+ticket);
                try {
                    Thread.sleep(3000);//卖票速度是1s一张
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvKaishi = findViewById(R.id.tvKsmp);
        tvKaishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //步骤2:创建线程类的实例
                //创建二个线程，模拟二个窗口卖票
                MyThread1 mt1 = new MyThread1("窗口1");
                MyThread2 mt2 = new MyThread2("窗口2");

                //步骤3：调用start()方法开启线程
                //启动二个线程，也即是窗口，开始卖票
                mt1.start();
                mt2.start();
            }
        });
    }
}