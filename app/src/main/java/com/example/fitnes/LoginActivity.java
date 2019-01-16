package com.example.fitnes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button btn;
    private EditText times;
    private EditText height;
    private EditText weight;

    private List<Users> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("MYLOG", "Login");
        times = findViewById(R.id.times);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        btn = findViewById(R.id.btn_next);

        if(savedInstanceState == null) {
            data = DBHelper.getInstance(getApplicationContext()).getUsers();
        }
        final ArrayAdapter<Users> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, data);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   Users user = new Users(Integer.parseInt(times.getText().toString()),
                            Integer.parseInt(height.getText().toString()),
                            Integer.parseInt(weight.getText().toString()));
                    DBHelper.getInstance(getApplicationContext()).addUsers(user);
                    data.add(user);
                    adapter.notifyDataSetChanged();
            }
        });

    }
}
