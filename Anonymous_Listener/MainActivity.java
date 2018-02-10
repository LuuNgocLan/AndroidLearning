package app.min.luungoclan.event;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCalc = (Button) findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtNum_a = (EditText) findViewById(R.id.edtNumberFirst);
                EditText edtNum_b = (EditText) findViewById(R.id.edtNumberSecond);

                int numFirst = Integer.parseInt(edtNum_a.getText()+"");
                int numSecond = Integer.parseInt(edtNum_b.getText()+"");

                TextView tvResult = (TextView) findViewById(R.id.tvResult);
                tvResult.setText(String.valueOf(numFirst+numSecond));
            }
        });
    }
    /*public void onClickFindSum(View view){
        EditText edtNum_a = (EditText) findViewById(R.id.edtNumberFirst);
        EditText edtNum_b = (EditText) findViewById(R.id.edtNumberSecond);

        int numFirst = Integer.parseInt(edtNum_a.getText()+"");
        int numSecond = Integer.parseInt(edtNum_b.getText()+"");

        TextView tvResult = (TextView) findViewById(R.id.tvResult);
        tvResult.setText(String.valueOf(numFirst+numSecond));
    }*/

}
