package com.firstapp.securednotebook;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    public static final int INSERT_ACTIVITY_RESULT=3333;
    public static final int UPDATE_ACTIVITY_RESULT=4444;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.setTitle("ማስታውሻ ደብተር");
        floatingActionButton=findViewById(R.id.floatingActionButton);

        final Handler handler =new Handler();
        Thread thread = new Thread(new Runnable() {
            List<Qoute> qouteList;

            @Override
            public void run() {
                qouteList = QouteRoomDatabase.getInstance(getApplicationContext()).qouteDao().getAllQoute();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView = findViewById(R.id.recyclerview);
                        recyclerViewAdapter = new RecyclerViewAdapter(HomeActivity.this, qouteList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                        recyclerView.setAdapter(recyclerViewAdapter);


                        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(final int position) {

                            }

                            @Override
                            public void onDeleteClick(final int position) {
                                final Handler handler1=new Handler();
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Qoute q=QouteRoomDatabase.getInstance(HomeActivity.this).qouteDao().findQouteByQoute(qouteList.get(position).getQoute());
                                        QouteRoomDatabase.getInstance(HomeActivity.this).qouteDao().deleteQoute(q);
                                        qouteList.remove(position);
                                        handler1.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                recyclerViewAdapter.notifyItemRemoved(position);
                                            }
                                        });


                                    }
                                }).start();
                            }

                            @Override
                            public void onEditClick(final int position) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Qoute q = QouteRoomDatabase.getInstance(HomeActivity.this)
                                                .qouteDao()
                                                .findQouteByQoute(qouteList.get(position).getQoute());
                                        Intent intent = new Intent(HomeActivity.this, UpdateActivity.class);
                                        Gson gson = new Gson();
                                        String s = gson.toJson(q);
                                        intent.putExtra("qoute_data",s);
                                        startActivityForResult(intent,UPDATE_ACTIVITY_RESULT);
                                    }
                                }).start();
                            }
                        });












                    }
                });


            }});

        thread.start();
    }

    public void insertQoutfloatingactionbutton(View view) {
        Intent intent = new Intent(this,InsertQouteActivity.class);
        startActivityForResult(intent,INSERT_ACTIVITY_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == INSERT_ACTIVITY_RESULT || requestCode == UPDATE_ACTIVITY_RESULT){
            recreate();
        }
    }
}
