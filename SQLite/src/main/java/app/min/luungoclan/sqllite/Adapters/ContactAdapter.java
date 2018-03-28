package app.min.luungoclan.sqllite.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.min.luungoclan.sqllite.R;
import app.min.luungoclan.sqllite.models.Contact;

/**
 * Created by Luu Ngoc Lan on 25-Mar-18.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private List<Contact> contacts;
    private Context mContext;
    private LayoutInflater mInflater;

    public ContactAdapter(List<Contact> contacts, Context context){
        this.mContext = context;
        this.contacts = contacts;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_list_contact, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.mTvName.setText(contact.getmName());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTvName;

        public MyViewHolder(View itemView){
            super(itemView);
            itemView.setClickable(true);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }
}