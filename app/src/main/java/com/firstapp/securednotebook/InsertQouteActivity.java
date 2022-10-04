package com.firstapp.securednotebook;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class InsertQouteActivity extends AppCompatActivity {
    EditText insertQout,insertWriter,insertType;
    Button canclebtn,insertbtn;
    ImageButton button;
    private int lastdateselectedy;
    private int lastdateselectedm;
    private int lastdateselectedd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_qoute);
        this.setTitle("ማስታውሻ ደብተር");
        button = findViewById(R.id.button);
        insertbtn = findViewById(R.id.insertbtn);
        canclebtn = findViewById(R.id.canclebtn);
        insertQout = findViewById(R.id.editTextQoute);
        insertWriter = findViewById(R.id.editTextQouteWriter);
        insertType = findViewById(R.id.editTextQouteType);
        final Calendar calendar = Calendar.getInstance();
        this.lastdateselectedy= calendar.get(Calendar.YEAR);
        this.lastdateselectedm= calendar.get(Calendar.MONTH);
        this.lastdateselectedd= calendar.get(Calendar.DAY_OF_MONTH);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepickerbutton();
            }
        });
    }

    public void InsertQouteOnclick(View view) {
        Qoute qoute = new Qoute(insertQout.getText().toString(),
                insertWriter.getText().toString(),
                insertType.getText().toString());
        InsertAsycnTask insertAsycnTask = new InsertAsycnTask();
        insertAsycnTask.execute(qoute);
    }

    public void CancleQouteOnclick(View view) {
        insertQout.setText("");
        insertWriter.setText("");
        insertType.setText("");
    }
    private void datepickerbutton() {
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                insertType.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                lastdateselectedy=year;
                lastdateselectedm=month;
                lastdateselectedd=dayOfMonth;
            }
        };
        DatePickerDialog ddl=null;
        ddl=new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog_NoActionBar,dateSetListener,lastdateselectedy,
                lastdateselectedm,lastdateselectedd);
        ddl.show();

    }
    private class InsertAsycnTask extends AsyncTask<Qoute,Void,Void>{

        @Override
        protected Void doInBackground(Qoute... qoutes) {
            QouteRoomDatabase.getInstance(getApplicationContext())
                    .qouteDao().insertQoute(qoutes[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(InsertQouteActivity.this, "Qoute Inserted", Toast.LENGTH_SHORT).show();
        }
    }
}