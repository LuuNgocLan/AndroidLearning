package app.min.luungoclan.sqllite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.min.luungoclan.sqllite.Adapters.ContactAdapter;
import app.min.luungoclan.sqllite.models.Contact;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvContacts;
    private ContactAdapter mAdapter;
    private List<Contact> mData;
    private MyDatabase db;

    private  int mCurentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Contact");
        db = new MyDatabase(this);
        mData =  new ArrayList<>();
        getData();
        handle();
    }

    private void getData() {
        //mData.clear();
        mData =  db.getAllContacts();
        if(mData!=null) {
            //Contact contact = mData.get(0);

            Toast.makeText(this,"hello",Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void handle(){
        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        rvContacts.setHasFixedSize(true);

        mAdapter = new ContactAdapter(mData,MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rvContacts.setLayoutManager(layoutManager);
        rvContacts.setAdapter(mAdapter);
        rvContacts.addOnItemTouchListener(new RecycleTouchListener(this, rvContacts,
                new RecycleTouchListener.OnItemClickListener(){
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                        intent.putExtra("ID",mData.get(position).getmId());
                        startActivityForResult(intent, 2000);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                }));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.addContact:
                Intent intent =  new Intent(getApplicationContext(),AddActivity.class);
                startActivityForResult(intent,1000);
                break;
            case R.id.deleteAll:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("DROP ALL DATA");
                builder.setMessage("This action will delete all contact and can't restore. Are you sure?");
                builder.setCancelable(false);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAllContacts();
                    }
                })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAllContacts() {
        db.deleteAllContact();
        mData.clear();
        mAdapter.notifyDataSetChanged();
        db.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1000){
            if(resultCode == RESULT_OK){
                Contact contact = (Contact) data.getSerializableExtra("RESULT");
                mData.add(contact);
                mAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 2000 && resultCode == RESULT_OK){
            String contactID = data.getStringExtra("ID");
            int id = Integer.parseInt(contactID);
            int pos = 0;
            for (int i=0;i<mData.size();i++){
                if (mData.get(i).getmId()==id) {
                    pos = i;
                    break;
                }
            }
            mData.remove(pos);
            mAdapter.notifyDataSetChanged();
        }
        if (requestCode == 2000 && resultCode == 1){
            String editID = data.getStringExtra("ID");
            int id = Integer.parseInt(editID);
            int pos=0;
            for (int i=0;i<mData.size();i++){
                if (mData.get(i).getmId()==id) {
                    pos = i;
                    break;
                }
            }
            mData.get(pos).setmName(db.getContact(id).getmName());
            db.close();
            mAdapter.notifyDataSetChanged();
        }

    }
}
