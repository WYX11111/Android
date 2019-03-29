package com.example.love;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {
    private TextView textview;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView) findViewById(R.id.tv);
        SystemClock.sleep(15000);
        handler = new Handler() {
            public void handleMessage(Message msg) {
                textview.setText((String) msg.obj);
            }
        };
        Threads thread = new Threads();
        thread.start();
    }

    class Threads extends Thread {
        public int checkYear(int year){
            if ((year%4==0) && (year%100!=0) || year%400==0)
                return 366;
            else
                return 365;
        }

        public int checkMonth(int year, int month){
            if ((year%4==0) && (year%100!=0) || year%400==0) {
                if (month == 2)
                    return 29;
                else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 )
                    return 31;
                else
                    return 30;
            }
            else{
                if (month == 2)
                    return 28;
                else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 )
                    return 31;
                else
                    return 30;
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
                    String str = sdf.format(new Date());
                    char[ ] arr = str.toCharArray();
                    char[] year = new char[4];
                    for(int i = 0; i < 4; i++){
                        year[i] = arr[i];
                    }

                    String year1 = String.valueOf(year);
                    int year2 = Integer.parseInt(year1);
                    //s=String.valueOf(i);

                    double ysum = 0;
                    for(int i = 2018; i < year2; i++)
                        ysum = ysum + checkYear(i);

                    ysum = ysum * 24 * 3600;

                    char[] month = new char[2];
                    for(int i = 5; i < 7; i++){
                        month[i - 5] = arr[i];
                    }

                    String month1 = String.valueOf(month);
                    int month2 = Integer.parseInt(month1);
                    double dsum = 0;
                    for(int i = 1; i < month2; i++)
                        dsum = dsum + checkMonth(year2,i);

                    char[] day = new char[2];
                    for(int i = 8; i < 10; i++){
                        day[i - 8] = arr[i];
                    }

                    String day1 = String.valueOf(day);
                    int day2 = Integer.parseInt(day1);
                    dsum += day2;

                    dsum = dsum * 24 * 3600;

                    dsum += ysum;

                    char[] hour = new char[2];
                    for(int i = 14; i < 16; i++){
                        hour[i - 14] = arr[i];
                    }
                    String hour1 = String.valueOf(hour);
                    int hour2 = Integer.parseInt(hour1);
                    dsum += hour2 * 3600;

                    char[] min = new char[2];
                    for(int i = 17; i < 19; i++){
                        min[i - 17] = arr[i];
                    }
                    String min1 = String.valueOf(min);
                    int min2 = Integer.parseInt(min1);

                    dsum += min2 * 60;
                    char[] sec = new char[2];
                    for(int i = 20; i < 22; i++){
                        sec[i - 20] = arr[i];
                    }
                    String sec1 = String.valueOf(sec);
                    int sec2 = Integer.parseInt(sec1);
                    dsum += sec2;

                    double oysum = 0;
                    for(int i = 2018; i < 2019; i++)
                        oysum = oysum + checkYear(i);

                    oysum = oysum * 24 * 3600;

                    double odsum = 0;
                    for(int i = 1; i < 3; i++)
                        odsum = odsum + checkMonth(2019,i);
                    odsum += 21;
                    odsum = odsum * 24 * 3600;
                    odsum += oysum;

                    int ohour = 23;
                    odsum += ohour * 3600;
                    int omin = 41;
                    odsum += omin * 60;

                    int d = (int)(dsum - odsum) / 3600 / 24;
                    String da = String.valueOf(d);

                    int h = (int)(dsum - odsum - d * 24 * 3600) / 3600;
                    String ho = String.valueOf(h);

                    int m = (int)(dsum - odsum - d * 24 * 3600 - h * 3600) / 60;
                    String mi = String.valueOf(m);

                    int s = (int)(dsum - odsum - d * 24 * 3600 - h * 3600 - m * 60) ;
                    String se = String.valueOf(s);
                    handler.sendMessage(handler.obtainMessage(100,da + "天" + ho + "小时" + mi + "分钟" + se + "秒"));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}