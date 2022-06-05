package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button clear, backspace, left_over, division, btn7, btn8, btn9, btn6, btn5, btn4,
            btn1, btn2, btn3, btnDot, btn0, multiply, minus, plus, equal;
    TextView result, operation;
    double x, y;
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnDot.setOnClickListener(this);
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

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operation.setText("0");
                result.setText("0");
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperation("+");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperation("-");
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperation("×");
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperation("÷");
            }
        });

        left_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperation("%");
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = operation.getText().toString();
                if (str.length() > 1) {
                    str = str.substring(0, str.length() - 1);
                    operation.setText(str);
                } else if(str.length() == 1) {
                    operation.setText("0");
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doIt();
            }
        });
    }

    private void init() {

        clear = findViewById(R.id.clear);
        backspace = findViewById(R.id.backspace);
        left_over = findViewById(R.id.left_over);
        division = findViewById(R.id.division);
        multiply = findViewById(R.id.multiply);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        equal = findViewById(R.id.equal);
        result = findViewById(R.id.result);
        operation = findViewById(R.id.operation);
        btnDot = findViewById(R.id.btn_dot);
        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
    }

    @Override
    public void onClick(View view) {
        setOperationText(((Button) view).getText());
    }

    private void setOperationText(CharSequence text) {

        if (operation.getText().toString().trim().equals("0") && text.equals(".")) {
            operation.setText("0.");
            if (operation.getText().toString().trim().equals("0.")) {
                operation.setText(text);
            }
        } else if (operation.getText().toString().trim().equals("0")) {
            operation.setText(text);
        } else {
            operation.setText(String.format("%s%s", operation.getText().toString(), text));
        }

        operation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnDot.setEnabled(!charSequence.toString().contains("."));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void setOperation(String s) {

        if (Double.parseDouble(operation.getText().toString()) != 0
                && Double.parseDouble(result.getText().toString()) != 0
                && !operator.isEmpty()) {
            doIt();

        }

        x = Double.parseDouble(operation.getText().toString());
        result.setText(operation.getText().toString());
        operator = s;
        operation.setText("0");
    }

    private void doIt() {

        if (!operator.isEmpty()) {

            y = Double.parseDouble(operation.getText().toString());
            switch (operator.charAt(0)) {
                case '+':
                    operation.setText(String.format("%s", x + y));
                    break;
                case '-':
                    if (y == 0) {
                        Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                        operation.setText(String.format("%s", "ERROR"));
                    } else {
                        operation.setText(String.format("%s", x - y));
                    }
                    break;
                case '×':
                    operation.setText(String.format("%s", x * y));
                    break;
                case '÷':
                    operation.setText(String.format("%s", x / y));
                    break;
                case '%':
                    operation.setText(String.format("%s", x % y));
                    break;
                case '⌫':
                    operation.setText(operation.getText().toString().substring(0, operation.length() - 1));
                default:
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        }

        result.setText("0");
        operator = "";
    }

}