package com.example.calculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvScreen;
    private List<Calculate> calculates = new ArrayList<Calculate>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScreen = findViewById(R.id.tvScreen);

        findViewById(R.id.but0).setOnClickListener(this);
        findViewById(R.id.but1).setOnClickListener(this);
        findViewById(R.id.but2).setOnClickListener(this);
        findViewById(R.id.but3).setOnClickListener(this);
        findViewById(R.id.but4).setOnClickListener(this);
        findViewById(R.id.but5).setOnClickListener(this);
        findViewById(R.id.but6).setOnClickListener(this);
        findViewById(R.id.but7).setOnClickListener(this);
        findViewById(R.id.but8).setOnClickListener(this);
        findViewById(R.id.but9).setOnClickListener(this);
        findViewById(R.id.butAdd).setOnClickListener(this);
        findViewById(R.id.butSub).setOnClickListener(this);
        findViewById(R.id.butX).setOnClickListener(this);
        findViewById(R.id.butDiv).setOnClickListener(this);
        findViewById(R.id.butResult).setOnClickListener(this);
        findViewById(R.id.butClean).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.but0:
                tvScreen.append("0");
                break;

            case R.id.but1:
                tvScreen.append("1");
                break;

            case R.id.but2:
                tvScreen.append("2");
                break;

            case R.id.but3:
                tvScreen.append("3");
                break;

            case R.id.but4:
                tvScreen.append("4");
                break;

            case R.id.but5:
                tvScreen.append("5");
                break;

            case R.id.but6:
                tvScreen.append("6");
                break;

            case R.id.but7:
                tvScreen.append("7");
                break;

            case R.id.but8:
                tvScreen.append("8");
                break;

            case R.id.but9:
                tvScreen.append("9");
                break;

            case R.id.butAdd:
                calculates.add(new Calculate(Double.parseDouble(tvScreen.getText().toString()),Type.NUM));
                checkAndCompute();
                calculates.add(new Calculate(0,Type.ADD));
                tvScreen.setText("");
                break;

            case R.id.butSub:
                calculates.add(new Calculate(Double.parseDouble(tvScreen.getText().toString()),Type.NUM));
                checkAndCompute();
                calculates.add(new Calculate(0,Type.SUB));
                tvScreen.setText("");
                break;

            case R.id.butX:
                calculates.add(new Calculate(Double.parseDouble(tvScreen.getText().toString()),Type.NUM));
                checkAndCompute();
                calculates.add(new Calculate(0,Type.X));
                tvScreen.setText("");
                break;

            case R.id.butDiv:
                calculates.add(new Calculate(Double.parseDouble(tvScreen.getText().toString()),Type.NUM));
                checkAndCompute();
                calculates.add(new Calculate(0,Type.DIV));
                tvScreen.setText("");
                break;

            case R.id.butClean:
                tvScreen.setText("");
                calculates.clear();
                break;

            case R.id.butResult:
                calculates.add(new Calculate(Double.parseDouble(tvScreen.getText().toString()),Type.NUM));
                checkAndCompute();
                tvScreen.setText(calculates.get(0).a+"");   //转换字符串类型
                calculates.clear();
                break;
        }

    }

    public void checkAndCompute(){
        if(calculates.size()>=3){

            double x = calculates.get(0).a;   //得到数字
            double y = calculates.get(2).a;
            int z = calculates.get(1).b;

            calculates.clear();

            switch (z){    //第二项 判断符号
                case Type.ADD:
                    calculates.add(new Calculate((x+y),Type.NUM));
                    break;

                case Type.SUB:
                    calculates.add(new Calculate((x-y),Type.NUM));
                    break;

                case Type.X:
                    calculates.add(new Calculate((x*y),Type.NUM));
                    break;

                case Type.DIV:
                    calculates.add(new Calculate((x/y),Type.NUM));
                    break;
            }
        }
    }

}