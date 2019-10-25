package com.example.itemmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_add;
    RecyclerView recyclerView;
    AppDatabase db;
    RecyclerView.LayoutManager layoutManager;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.recycler_view);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddItemScreen();
            }
        });

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter();
        recyclerView.setAdapter(itemAdapter);
    }

    void openAddItemScreen() {
        Intent intent = new Intent(MainActivity.this, AddItem.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMemory();
    }

    @SuppressLint("StaticFieldLeak")
    void getMemory() {
        new AsyncTask<Void, Void, List<Item>>() {
            @Override
            protected List<Item> doInBackground(Void... voids) {
                db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database-name").build();

                List<Item> items = db.itemDao().getAll();
                return items;
            }

            @Override
            protected void onPostExecute(List<Item> items) {
                super.onPostExecute(items);
                itemAdapter.items = items;
                itemAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
