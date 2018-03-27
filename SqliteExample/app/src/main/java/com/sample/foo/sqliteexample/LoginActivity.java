package com.sample.foo.sqliteexample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button b = (Button)findViewById(R.id.login);
        final EditText e1=(EditText)findViewById(R.id.username);
        final EditText e2=(EditText)findViewById(R.id.password);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().toString().equals("root")&&e2.getText().toString().equals("root"))
                {
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }

            }
        });
    }
}
