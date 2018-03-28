package app.min.luungoclan.sqllite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import app.min.luungoclan.sqllite.models.Contact;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtName, edtPhone, edtAddress;
    private RadioGroup rgGender;
    private RadioButton rbGender;
    private Button btnAdd, btnCancel;
    private MyDatabase db;
    private Bundle bundle;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setTitle("Add Contact");
        init();
        bundle = getIntent().getExtras();
        if(bundle!=null){
            contact.setmId(bundle.getInt("ID"));
            contact = db.getContact(contact.getmId());
            setData();
        }
        addEvent();
    }

    private void setData() {
        getSupportActionBar().setTitle("Update Contact");
        btnAdd.setText("UPDATE");
        edtName.setText(contact.getmName());
        edtAddress.setText(contact.getmAddress());
        edtPhone.setText(contact.getmPhone());
        if (contact.getmGender().equals("Male")) {
            rgGender.check(R.id.rbMale);
        } else rgGender.check(R.id.rbFemale);
    }

    private void init() {
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        db = new MyDatabase(this);
        contact = new Contact();
    }


    private void addEvent() {
        btnCancel.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                if(checkData()){
                    addContactAndPutDataToReturn();
                    finish();
                } else return;
                Toast.makeText(this,"add succesfull!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCancel:
                Toast.makeText(this,"Cancel!",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    private void addContactAndPutDataToReturn() {
        contact.setmName(edtName.getText().toString());
        contact.setmAddress(edtAddress.getText().toString());
        contact.setmPhone(edtPhone.getText().toString());
        long day  =System.currentTimeMillis();
        String date =  new SimpleDateFormat("dd/MM/yyyy").format( new Date(day));
        contact.setmDate(date);
        String time =  new SimpleDateFormat("hh:mm:ss").format(new Date(day));
        contact.setmTime(time);
        int idItemRadio = rgGender.getCheckedRadioButtonId();
        if(idItemRadio==R.id.rbFemale){
            contact.setmGender("Female");
        } else
            contact.setmGender("Male");

        if(bundle!=null){
            db.updateContact(contact);
        } else {
            contact.setmId(db.addContact(contact));
        }
        db.close();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("RESULT",contact);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
    }

    private boolean checkData() {
        if(edtName.getText().toString().equals("")||edtPhone.getText().toString().equals("")){
            Toast.makeText(this,"You have not enter full data!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
