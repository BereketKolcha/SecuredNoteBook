package com.firstapp.securednotebook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class UpdateActivity extends AppCompatActivity {
    EditText editQout,editWriter,editType;
    Qoute q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        this.setTitle("ማስታውሻ ደብተር");
        editQout = findViewById(R.id.editTextQoute);
        editWriter = findViewById(R.id.editTextQouteWriter);
        editType = findViewById(R.id.editTextQouteType);

        Gson gson= new Gson();
        q = gson.fromJson(getIntent().getStringExtra("qoute_data"),Qoute.class);
        if (q != null){
            editQout.setText(q.getQoute());
            editWriter.setText(q.getQoutewriter());
            editType.setText(q.getQouteType());
        }
    }

    public void UpdatetQouteOnclick(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(UpdateActivity.this);
        alert.setTitle("Qoute is going to be changed")
                .setMessage("areyou shure to change")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new Runnable() {
                            final Handler handler=new Handler();
                            @Override
                            public void run() {
                                if(q != null){
                                    Qoute qupdate = QouteRoomDatabase.getInstance(UpdateActivity.this)
                                            .qouteDao().findQouteById(q.getUid());
                                    qupdate.setQoute(editQout.getText().toString());
                                    qupdate.setQoutewriter(editWriter.getText().toString());
                                    qupdate.setQouteType(editType.getText().toString());
                                    QouteRoomDatabase.getInstance(UpdateActivity.this)
                                            .qouteDao().updateQoute(qupdate);
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(UpdateActivity.this,"successfully update",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }).start();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(UpdateActivity.this,"update cancle",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog= alert.create();
        dialog.show();
    }

    public void CancleQouteOnclick(View view) {
        finish();
    }
}