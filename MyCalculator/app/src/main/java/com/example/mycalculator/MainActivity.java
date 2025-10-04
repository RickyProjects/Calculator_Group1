package com.example.mycalculator;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btn_plus, btn_minus, btn_multiply, btn_divide, btn_clear, btn_dot, btn_equal;
    TextView text_display;

    double val1, val2;
    double rawResult;
    boolean add, sub, mul, div;

    // This is to evaluate the math expression

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_dot = (Button) findViewById(R.id.btn_dot);
        text_display = (TextView) findViewById(R.id.textview_input_display);

        setClickListeners();
    }

    private void setClickListeners() {

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                addNumber("0");
                break;
            case R.id.btn1:
                addNumber("1");
                break;
            case R.id.btn2:
                addNumber("2");
                break;
            case R.id.btn3:
                addNumber("3");
                break;
            case R.id.btn4:
                addNumber("4");
                break;
            case R.id.btn5:
                addNumber("5");
                break;
            case R.id.btn6:
                addNumber("6");
                break;
            case R.id.btn7:
                addNumber("7");
                break;
            case R.id.btn8:
                addNumber("8");
                break;
            case R.id.btn9:
                addNumber("9");
                break;
            case R.id.btn_plus:
                addNumber("+");
                break;
            case R.id.btn_minus:
                addNumber("-");
                break;
            case R.id.btn_multiply:
                addNumber("x");
                break;
            case R.id.btn_divide:
                addNumber("/");
                break;
            case R.id.btn_dot:
                addNumber(".");
                break;
            /*case R.id.btn_equal:
                addNumber("=");
                break;*/
            case R.id.btn_equal:
                String result = null;
                try {
                    result = evaluate(text_display.getText().toString());
                    text_display.setText(result);
                } catch (Exception e) {
                    text_display.setText("Error");
                }
                break;
            case R.id.btn_clear:
                clear_display();
                break;
        }
    }

    private String evaluate(String expression) throws Exception {
        String result = "";
        char tempVal;
        boolean operate = false;
        String tempVal1 = "";
        String tempVal2 = "";
        for (int i = 0; i < expression.length(); i++) {
            tempVal = expression.charAt(i);
            if (!operate) {
                if (tempVal == '+') {
                    add = true;
                    operate = true;
                } else if (tempVal == '-') {
                    if (tempVal1 == "") {
                        tempVal1 += "-";
                    } else {
                        sub = true;
                        operate = true;
                    }
                } else if (tempVal == 'x') {
                    mul = true;
                    operate = true;
                } else if (tempVal == '/') {
                    div = true;
                    operate = true;
                } else {
                    tempVal1 += tempVal;
                }
            } else {
                tempVal2 += tempVal;
            }

        }
        val1 = Double.parseDouble(tempVal1);

        double tempResult;

        if (!operate) {
            rawResult = val1;
        } else {
            val2 = Double.parseDouble(tempVal2);
            if (add) {
                rawResult = add(val1, val2);
            } else if (sub) {
                rawResult = sub(val1, val2);
            } else if (mul) {
                rawResult = mul(val1, val2);
            } else if (div) {
                rawResult = div(val1, val2);
            }
        }

        operate = false;
        add = false;
        sub = false;
        mul = false;
        div = false;

        if (rawResult == (long) rawResult) {
            return String.valueOf((long) rawResult);
        } else {
            // Round to 2 decimals only if needed
            BigDecimal decimal = new BigDecimal(rawResult);
            return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
        }
    }

    private double add(double val1, double val2){
        return val1 + val2;
    }
    private double sub(double val1, double val2){
        return val1 - val2;
    }
    private double mul(double val1, double val2){
        return val1 * val2;
    }
    private double div(double val1, double val2) {
        return val1 / val2;
    }

    private void addNumber(String number) {
        text_display.setText(text_display.getText() + number);
    }

    private void clear_display() {
        text_display.setText("");
    }
}
