package com.example.itemmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends AppCompatActivity {
    EditText edt_name, edt_quantity;
    Button btn_save;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_iterm);
        edt_name = findViewById(R.id.edt_name);
        edt_quantity = findViewById(R.id.edt_quantity);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItemToDatabase();
                finish();
            }
        });


    }

    void saveItemToDatabase() {
        String name = edt_name.getText().toString();
        String quantity = edt_quantity.getText().toString();
        final Item item = new Item(name, quantity);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database-name").build();
                db.itemDao().insert(item);
                return null;
            }
        }.execute();


    }

}
