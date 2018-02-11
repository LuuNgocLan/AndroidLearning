package app.min.luungoclan.event;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnCalc;
    EditText edtNumberFirst;
    EditText edtNumberSecond;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(this);
        edtNumberFirst = (EditText) findViewById(R.id.edtNumberFirst);
        edtNumberSecond = (EditText) findViewById(R.id.edtNumberSecond);
        tvResult = (TextView) findViewById(R.id.tvResult);
    }
    @Override
    public void onClick(View view) {

        double numberFirst = Double.parseDouble(edtNumberFirst.getText()+"");
        double numberSecond = Double.parseDouble(edtNumberSecond.getText()+"");
        tvResult.setText(String.valueOf(numberFirst+numberSecond));
    }
}
