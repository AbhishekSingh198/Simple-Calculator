package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.faendir.rhino_android.RhinoAndroidHelper;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4,btn0,btn5,btn6,btn7,btn8,btn9,btnDot,btnMultiply,btnDivision,btnEquals,btnMinus,btnPlus,btnPercent,btnBracket,btnClear,btnBackspace;
    TextView tvInput,tvOutput;
    String Process;
    boolean checkBracket=false;
    private RhinoAndroidHelper rhinoAndroidHelper;
    private Context context;
    String finalResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);
        btnBracket=findViewById(R.id.btnBracket);
        btnClear=findViewById(R.id.btnClear);
        btnPercent=findViewById(R.id.btnPercent);
        btnPlus=findViewById(R.id.btnPlus);
        btnEquals=findViewById(R.id.btnEquals);
        btnMinus=findViewById(R.id.btnMinus);
        btnDivision=findViewById(R.id.btnDivision);
        btnDot=findViewById(R.id.btnDot);
        btnMultiply=findViewById(R.id.btnMultiply);
        tvInput=findViewById(R.id.tvInput);
        tvOutput=findViewById(R.id.tvOutput);
        btnBackspace= findViewById(R.id.btnBackspace);
        btnBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=tvInput.getText().toString();
                if (str.length()>=1){
                    str= str.substring(0,str.length()-1);
                    tvInput.setText(str);
                };
                if (str.length()<1){
                    tvInput.setText("");
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText("");
                tvOutput.setText("");
                checkBracket=false;
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"9");
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"-");
            }
        });
        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"/");
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"+");
            }
        });
        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"%");
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+"x");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process= tvInput.getText().toString();
                tvInput.setText(Process+".");
            }
        });
        btnBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBracket){
                    Process= tvInput.getText().toString();
                    tvInput.setText(Process+")");
                    checkBracket = false;
                }
                else {
                    Process= tvInput.getText().toString();
                    tvInput.setText(Process+"(");
                    checkBracket = true;
                }
            }
        });
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process=tvInput.getText().toString();
                if(Process== ""){
                    tvOutput.setText("");
                    return;
                }
                Process =Process.replaceAll("x","*");
                Process =Process.replaceAll("%","/100");
                Process =Process.replaceAll("/","/");
                rhinoAndroidHelper = new RhinoAndroidHelper(MainActivity.this);
                context= rhinoAndroidHelper.enterContext();
                context.setOptimizationLevel(-1);
                try {
                    Scriptable scriptable =context.initStandardObjects();
                    finalResult = context.evaluateString(scriptable, Process, "javascript", 1, null).toString();

                }
                catch (Exception e){
                    finalResult= "Error";
                }
                tvOutput.setText(finalResult);

            }
        });

    }
}