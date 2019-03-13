package com.di0nys1s.factorial;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;


public class MainActivity extends AppCompatActivity {
    EditText etvfrom;
    EditText etvto;
    Button btn_calculate;
    String numberfrom;
    String numberto;
    TextView tv;
    TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.txt4);
        tv3 = (TextView) findViewById(R.id.txt3);
        tv3.setMovementMethod(new ScrollingMovementMethod());
        btn_calculate = (Button) findViewById(R.id.btn_calculate);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etvfrom = (EditText) findViewById(R.id.etxtfrom);
                etvto = (EditText) findViewById(R.id.etxtto);
                numberfrom = etvfrom.getText().toString();
                numberto = etvto.getText().toString();
                tv3.setText("Calculating Factorial from " + numberfrom + "! to " + numberto + "!");
                tv.setText("");
                calculate();
            }
        });
    }

    public void calculate(){
        Factorial f = new Factorial();
        f.execute(new String[] {
                numberfrom, numberto
        });
    }

    private class Factorial extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
        int from = Integer.parseInt(params[0]);
        int to = Integer.parseInt(params[1]); String result = "";
        for(int i = from;i<= to;i++){
            result = i + "! = " + calculatingFactorial(i) +
                    "\n --------------------------------------------------- \n" + result;
        }
        return result;
    }
        @Override
        protected void onPostExecute(String result) {
            tv.setText(result);
        }
    }

    public BigInteger calculatingFactorial(int n){
        BigInteger result;
        BigInteger bign = new BigInteger(n + "");
        if(n != 0){
            result = new BigInteger(bign.multiply(calculatingFactorial(n-1)) + "");
        }else {
            result = new BigInteger("1");
        }
        return result; }
}