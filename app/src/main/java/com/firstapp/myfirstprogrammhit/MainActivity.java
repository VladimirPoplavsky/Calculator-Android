package com.firstapp.myfirstprogrammhit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView result;
    String operator = null;
    double num1 = 0, num2 = 0, res = 0;

    boolean calculates = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textView_res);
    }

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

//                    case "%":
//                        res *= num2;
//                        str = Double.toString(res).replaceAll((String)"\\.0$", "");
//                        result.setText("");
//                        break;
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

    public double parseCurrentNumber(TextView result) {
        double num = Double.parseDouble(result.getText().toString());
        return num;
    }
}