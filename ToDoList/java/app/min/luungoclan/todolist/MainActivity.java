package app.min.luungoclan.todolist;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import app.min.luungoclan.todolist.models.Job;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     private Button btnDate, btnTime, btnAdd;
     private EditText edtCongviec, edtNoidung;
     private TextView tvDate, tvTime;
     private ListView lvCongviec;
     private ArrayList<Job> mJob;
     private ArrayAdapter<Job> arrayAdapter;
     private Date dateFinish, timeFinish;
     private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setDefault();
        addEvent();
    }

    public void setDefault(){
        calendar = Calendar.getInstance();
        SimpleDateFormat dft = null;
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = dft.format(calendar.getTime());
        tvDate.setText(strDate);
        dft = new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String strTime = dft.format(calendar.getTime());
        tvTime.setText(strTime);

        dateFinish = calendar.getTime();
        timeFinish = calendar.getTime();
    }

    public void init(){
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDate = (Button)findViewById(R.id.btnDate);
        btnTime = (Button)findViewById(R.id.btnTime);
        tvDate = (TextView)findViewById(R.id.tvDate);
        tvTime = (TextView)findViewById(R.id.tvTime);
        edtCongviec = (EditText)findViewById(R.id.edtCongviec);
        edtNoidung = (EditText)findViewById(R.id.edtNoidung);
        lvCongviec = (ListView) findViewById(R.id.lvCongviec);
        mJob = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<Job>(this,android.R.layout.simple_list_item_1,mJob);
        lvCongviec.setAdapter(arrayAdapter);
    }

    public void addEvent(){
        btnTime.setOnClickListener(this);
        btnDate.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        lvCongviec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setMessage(mJob.get(position).getmDescription());
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        lvCongviec.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setMessage("Bạn có muốn xóa công việc này?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mJob.remove(pos);
                        arrayAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return false;
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                addJob();
                break;
            case R.id.btnDate:
                chooseDate();
                break;
            case R.id.btnTime:
                chooseTime();
                break;
        }
    }

    private void addJob() {
        if(checkContent()){
            String congviec = edtCongviec.getText()+"";
            String noidung = edtNoidung.getText()+"";
            Job job = new Job(congviec,noidung,dateFinish,timeFinish);
            mJob.add(job);
            arrayAdapter.notifyDataSetChanged();
            resetData();
        } else return;;

    }

    public void resetData(){
        edtNoidung.setText("");
        edtCongviec.setText("");
    }

    private void chooseTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        tvTime.setText(hour+":"+minute);
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay>12){
                    tvTime.setText(hourOfDay+":"+minute+" PM");
                } else tvTime.setText(hourOfDay+":"+minute+" AM");

            }
        },hour,minute,false);
        timePickerDialog.setTitle("Chọn giờ hoàn thành");
        timePickerDialog.show();
    }

    private void chooseDate() {
        Calendar calendar = Calendar.getInstance();
        final int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        //month++;
        tvDate.setText(date+"/"+(month+1)+"/"+year);
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                tvDate.setText(dayOfMonth+"/"+month+"/"+year);
            }
        },year,month,date);
        datePickerDialog.setTitle("Chọn ngày hoàn thành");
        datePickerDialog.show();
    }

    public boolean checkContent(){
        if(edtCongviec.getText().toString().equals("") ||edtNoidung.getText().toString().equals("")){
            Toast.makeText(this,"Bạn chưa nhập đầy đủ nội dung!!!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }
}
