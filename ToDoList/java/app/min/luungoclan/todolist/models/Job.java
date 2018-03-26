package app.min.luungoclan.todolist.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Luu Ngoc Lan on 04-Mar-18.
 */

public class Job {
    private String mName;
    private String mDescription;
    private Date mDate;
    private Date mTime;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public Job(String mName, String mDescription, Date mDate, Date mTime) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mDate = mDate;
        this.mTime = mTime;
    }
    public String getDateFormat(Date d)
    {
        SimpleDateFormat dft=new
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(d);
    }

    public String getHourFormat(Date d)
    {
        SimpleDateFormat dft=new
                SimpleDateFormat("hh:mm a", Locale.getDefault());
        return dft.format(d);
    }
    @Override
    public String toString() {
        return this.mName+"-"+
                getDateFormat(this.mDate)+"-"+
                getHourFormat(this.mTime);
    }
}
