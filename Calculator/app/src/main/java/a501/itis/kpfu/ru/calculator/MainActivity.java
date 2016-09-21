package a501.itis.kpfu.ru.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnAdd;
    Button btnSub;
    Button btnMult;
    Button btnDiv;
    Button btnRes;
    Button btnClear;
    Button btnBack;
    Button btnBL;
    Button btnBR;
    TextView screen;

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
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMult = (Button) findViewById(R.id.btnMult);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnRes = (Button) findViewById(R.id.btnRes);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBL = (Button) findViewById(R.id.btnBL);
        btnBR = (Button) findViewById(R.id.btnBR);

        screen = (TextView) findViewById(R.id.screen);

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
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnRes.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnBL.setOnClickListener(this);
        btnBR.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn0:
                screen.setText(screen.getText().toString() + "0");
                break;
            case R.id.btn1:
                screen.setText(screen.getText().toString() + "1");
                break;
            case R.id.btn2:
                screen.setText(screen.getText().toString() + "2");
                break;
            case R.id.btn3:
                screen.setText(screen.getText().toString() + "3");
                break;
            case R.id.btn4:
                screen.setText(screen.getText().toString() + "4");
                break;
            case R.id.btn5:
                screen.setText(screen.getText().toString() + "5");
                break;
            case R.id.btn6:
                screen.setText(screen.getText().toString() + "6");
                break;
            case R.id.btn7:
                screen.setText(screen.getText().toString() + "7");
                break;
            case R.id.btn8:
                screen.setText(screen.getText().toString() + "8");
                break;
            case R.id.btn9:
                screen.setText(screen.getText().toString() + "9");
                break;
            case R.id.btnAdd:
                if (!TextUtils.isEmpty(screen.getText().toString()))
                    screen.setText(screen.getText().toString() + " + ");
                break;
            case R.id.btnSub:
                if (!TextUtils.isEmpty(screen.getText().toString()))
                    screen.setText(screen.getText().toString() + " - ");
                break;
            case R.id.btnMult:
                if (!TextUtils.isEmpty(screen.getText().toString()))
                    screen.setText(screen.getText().toString() + " * ");
                break;
            case R.id.btnDiv:
                if (!TextUtils.isEmpty(screen.getText().toString()))
                    screen.setText(screen.getText().toString() + " / ");
                break;
            case R.id.btnClear:
                screen.setText("");
                break;
            case R.id.btnBack:
                if (!TextUtils.isEmpty(screen.getText().toString())) {
                    StringBuilder sb = new StringBuilder(screen.getText().toString());
                    if(sb.charAt(screen.getText().toString().length() - 1) != ' ')
                        sb.deleteCharAt(screen.getText().toString().length() - 1);
                    else
                        try{
                            sb.delete(screen.getText().toString().length() - 3, screen.getText().toString().length());
                        } catch (Exception e) {
                            sb.delete(screen.getText().toString().length() - 2, screen.getText().toString().length());
                        }
                    screen.setText(sb.toString());
                }
                break;
            case R.id.btnBL:
                screen.setText(screen.getText().toString() + "( ");
                break;
            case R.id.btnBR:
                screen.setText(screen.getText().toString() + " )");
                break;
            case R.id.btnRes:
                try {
                    result(toPostfix());
                } catch (Exception e) {
                    Toast.makeText(this, "Неправильный ввод", Toast.LENGTH_SHORT).show();
                    screen.setText("");
                }
                break;
        }
    }

    public int getPriority(String s) {
        switch (s) {
            case "(" :
                return 1;
            case "+" :
                return 2;
            case "-" :
                return 2;
            case "*" :
                return 3;
            case "/" :
                return 3;
        }
        return 0;
    }
    public String toPostfix() {
        Stack<String> ls = new Stack();
        String s = screen.getText().toString();
        if(s.charAt(0) == ' ')
            s = s.substring(1);
        if(s.charAt(s.length() - 1) == ' ')
            s = s.substring(0, s.length()-1);
        String[] pr = s.split(" ");
        String out = "";

        for(int i = 0; i < pr.length; i++) {
            System.out.println(pr[i]);
            if(!pr[i].equals("+") && !pr[i].equals("-") && !pr[i].equals("*") && !pr[i].equals("/") && !pr[i].equals("(") && !pr[i].equals(")")) {
                out += pr[i] + " ";
            }
            else {
                if(pr[i].equals("(")) {
                    ls.push(pr[i]);
                }
                else {
                    if(pr[i].equals(")")) {
                        String x = ls.pop();
                        while (!x.equals("(")) {
                            out += x + " ";
                            x = ls.pop();
                        }

                    }
                    else {
                        while(!ls.isEmpty() && (getPriority(ls.peek()) >= getPriority(pr[i])))
                            out += ls.pop() + " ";
                        ls.push(pr[i]);
                    }
                }
            }
        }

        while (!ls.isEmpty())
            out += ls.pop() + " ";
        return out;
    }

    public void result(String out) {
        Stack<Double> ls = new Stack();
        String s = out;

        String[] pr = s.split(" ");
            for (String aPr : pr) {
                if (aPr.charAt(0) == '+' || aPr.charAt(0) == '-' || aPr.charAt(0) == '*' || aPr.charAt(0) == '/') {
                    double x = ls.pop();
                    double y = ls.pop();
                    switch (aPr.charAt(0)) {
                        case '+':
                            ls.push(x + y);
                            break;
                        case '-':
                            ls.push(y - x);
                            break;
                        case '*':
                            ls.push(x * y);
                            break;
                        case '/':
                            ls.push(y / x);
                            break;
                    }
                } else {
                    ls.push(Double.parseDouble(aPr));
                }
            }
        if ((ls.peek() % 1) == 0 && !Double.isInfinite(ls.peek()))
            screen.setText(Integer.toString(ls.pop().intValue()));
        else
            if(!Double.isInfinite(ls.peek()))
                screen.setText(String.valueOf(ls.pop()));
            else {
                screen.setText("");
                Toast.makeText(MainActivity.this, "Деление на ноль запрещено", Toast.LENGTH_SHORT).show();
            }
    }
}