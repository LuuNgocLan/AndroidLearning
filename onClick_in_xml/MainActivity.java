package app.min.luungoclan.event;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickFindSum(View view){
        EditText edtNum_a = (EditText) findViewById(R.id.edtNum_a);
        EditText edtNum_b = (EditText) findViewById(R.id.edtNum_b);

        int numFirst = Integer.parseInt(edtNum_a.getText()+"");
        int numSecond = Integer.parseInt(edtNum_b.getText()+"");

        TextView tvResult = (TextView) findViewById(R.id.tvResult);
        tvResult.setText(String.valueOf(numFirst+numSecond));
    }
}
