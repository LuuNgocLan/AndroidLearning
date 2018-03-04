package app.min.luungoclan.solveequation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNumbera,edtNumberb;
    Button btnResult;
    Float a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNumbera =(EditText) findViewById(R.id.edtNumbera);
        edtNumberb =(EditText) findViewById(R.id.edtNumberb);
        btnResult = (Button) findViewById(R.id.btnResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkError()){
                    if(checkValidationData()){
                        Intent myIntent = new Intent(MainActivity.this, ResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("numbera", a);
                        bundle.putFloat("numberb", b);
                        myIntent.putExtra("MyPackage", bundle);
                        startActivity(myIntent);
                    }
                }
            }
        });
    }

    @Override
    protected void onRestart( ){
        super.onRestart();
        getThenShowDataFromSharePreference();
        setDefaultData();
    }

    private boolean checkError(){
        if(edtNumbera.getText()+""== "" || edtNumberb.getText()+"" == ""){
            Toast.makeText(MainActivity.this, "Bạn chưa nhập đầy đủ dữ liệu!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean checkValidationData(){
        try{
            a = Float.parseFloat(edtNumbera.getText() + "");
            b = Float.parseFloat(edtNumberb.getText() + "");
        }catch (NumberFormatException e){
            Toast.makeText(this,"Dữ liệu nhập vào không hợp lệ! Bạn vui lòng nhập lại!",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void getThenShowDataFromSharePreference(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData",MODE_PRIVATE);
        Float numbera = sharedPreferences.getFloat("Numbera",0);
        Float numberb = sharedPreferences.getFloat("Numberb",0);
        String message = "Save data successfull! Your last edit text: a="+numbera+" b="+numberb;
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    private void setDefaultData(){
        edtNumbera.setText("0");
        edtNumberb.setText("0");
    }
}
