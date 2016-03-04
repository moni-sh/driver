package com.pretenders.monish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Spinner lines = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> lineAdapter = ArrayAdapter.createFromResource(
                this, R.array.line_array, R.layout.spinner_item);

        lineAdapter.setDropDownViewResource(R.layout.spinner_item);
        lines.setAdapter(lineAdapter);

        // Open the Spinner...
        //lines.performClick();

        final Button dst = (Button) findViewById(R.id.src);
        final Button src = (Button) findViewById(R.id.dst);
        Button line = (Button) findViewById(R.id.line1);
        final ImageView img = (ImageView) findViewById(R.id.imageView);
        /*
        lines.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    dst.setVisibility(View.VISIBLE);
                    src.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        */
        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lines.performClick();
                lines.setVisibility(View.VISIBLE);
                dst.setVisibility(View.VISIBLE);
                src.setVisibility(View.VISIBLE);
                img.setVisibility(View.GONE);

            }
        });

        src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, OpenActivity.class);
                //EditText editText = (EditText) findViewById(R.id.edit_message);
                //String message = editText.getText().toString();
                //intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });

        dst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, OpenActivity.class);
                //EditText editText = (EditText) findViewById(R.id.edit_message);
                //String message = editText.getText().toString();
                //intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
    }
}
