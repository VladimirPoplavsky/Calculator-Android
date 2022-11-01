package com.firstapp.myfirstprogrammhit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView result;
    String operator = "";
    double num1 = 0, num2 = 0, res = 0;

    boolean calculates = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textView_res);
    }

    // parse arithmetic operator from the text input (+, -, *, /)
    public void setOperator(View view)
    {
        if(view instanceof Button)
        {
            Button b = (Button) view;
            String str = b.getText().toString();
            operator = str;

            num1 = parseCurrentNumber(result);
            res = num1;
            result.setText("");
        }
    }

    public void buttonFunctionNumber(View view) {
        if (view instanceof Button) {
            Button b = (Button) view;
            String str = b.getText().toString();


            if (str.equals("="))
            {
                if(!calculates)
                {
                    num2 = parseCurrentNumber(result);
                    calculates = true;
                }

                switch (operator)
                {
                    case "+":
                        res += num2;
                        str = Double.toString(res).replaceAll((String)"\\.0$", "");
                        result.setText("");
                        break;

                    case "-":
                        res -= num2;
                        str = Double.toString(res).replaceAll((String)"\\.0$", "");
                        result.setText("");
                        break;

                    case "X":
                        res *= num2;
                        str = Double.toString(res).replaceAll((String)"\\.0$", "");
                        result.setText("");
                        break;

                    case "/":
                        if(num2 == 0)
                        {
                            result.setText("Error");
                            str = "";
                        }
                        else
                        {
                            res /= num2;
                            str = Double.toString(res).replaceAll((String)"\\.0$", "");
                            result.setText("");
                        }
                        break;
                    default:
                        result.setText("Something went wrong, try again");
                }
            }

            //clean all from memory ('C' button)
            if (str.equals("c"))
            {
                calculates = false;
                str = "";
                num1 = 0;
                num2 = 0;
                res = 0;
                result.setText("");
            }

            // backspace
            if (str.equals("del"))
            {
                try
                {
                    str = result.getText().toString();
                    result.setText(str.substring(0,str.length()-1));
                    str = "";
                }catch (StringIndexOutOfBoundsException e)
                {
                    str = "";
                }

            }
            result.append(str);
        }
    }

    // as button "%" on the regular calculator
    public void percentage(View view)
    {
        if(view instanceof Button)
        {
            if(operator.equals("+") || operator.equals("-"))
            {
                num2 = parseCurrentNumber(result);
                num2 = (num1 * (num2 / 100));
                result.setText(Double.toString(num2).replaceAll((String)"\\.0$", ""));
            }
            else if(operator.equals(""))
            {
                num1 = parseCurrentNumber(result);
                num1 = num1/100;
                result.setText(Double.toString(num1).replaceAll((String)"\\.0$", ""));
            }
            else
            {
                num2 = parseCurrentNumber(result);
                num2 = num2/100;
                result.setText(Double.toString(num2).replaceAll((String)"\\.0$", ""));
            }

        }
    }

    public double parseCurrentNumber(TextView result) {
        try {
            double num = Double.parseDouble(result.getText().toString());
            return num;
        }catch (NumberFormatException e)
        {
            result.setText("");
            return 0;
        }
    }

    // button +/-
    public void swapPosNeg(View view)
    {
        if(view instanceof Button)
        {
            if(operator.equals(""))
            {
                num1 = parseCurrentNumber(result);
                num1 = num1 * (-1);
                result.setText(Double.toString(num1).replaceAll((String)"\\.0$", ""));
            }
            else
            {
                num2 = parseCurrentNumber(result);
                num2 = num2 * (-1);
                result.setText(Double.toString(num2).replaceAll((String)"\\.0$", ""));
            }

        }
    }
}