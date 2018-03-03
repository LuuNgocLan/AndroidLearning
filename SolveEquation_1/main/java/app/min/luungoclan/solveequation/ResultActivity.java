package app.min.luungoclan.solveequation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ResultActivity extends AppCompatActivity {
    Button btnBack;
    EditText edtResult;
    Float a, b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        edtResult = (EditText)findViewById(R.id.edtResult);

        Intent callerIntent = getIntent();
        Bundle packageBundle = callerIntent.getBundleExtra("MyPackage");
        a = packageBundle.getFloat("numbera");
        b = packageBundle.getFloat("numberb");

        sovleEquation(a,b);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putDataToSharePreference(a,b);
                finish();
            }
        });
    }

    private void sovleEquation(Float a, Float b) {
        if(a == 0){
            if (b == 0){
                edtResult.setText("Phương trình có vô số nghiệm");
            }else {
                edtResult.setText("Phương trình vô nghiêm");
            }
        }else {
            edtResult.setText(String.valueOf(-b/a));
        }
    }

    private void putDataToSharePreference(Float a, Float b){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("Numbera",a);
        editor.putFloat("Numberb",b);
        editor.commit();
    }
}
