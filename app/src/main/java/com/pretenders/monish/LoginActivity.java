package com.pretenders.monish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Spinner lines = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> lineAdapter = ArrayAdapter.createFromResource(
                this, R.array.line_array, R.layout.spinner_item);

        lineAdapter.setDropDownViewResource(R.layout.spinner_item);
        lines.setAdapter(lineAdapter);

        // Open the Spinner...
        //lines.performClick();

        Button dst = (Button) findViewById(R.id.src);
        Button src = (Button) findViewById(R.id.dst);

        src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                //EditText editText = (EditText) findViewById(R.id.edit_message);
                //String message = editText.getText().toString();
                //intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });

        dst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                //EditText editText = (EditText) findViewById(R.id.edit_message);
                //String message = editText.getText().toString();
                //intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
    }
}
