package com.pretenders.monish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class OpenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final TextView textView = (TextView) findViewById(R.id.textView2);



        final Button start = (Button) findViewById(R.id.button);
        final Button go = (Button) findViewById(R.id.button2);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setBackgroundResource(android.R.color.darker_gray);
                go.setVisibility(View.VISIBLE);
                go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(OpenActivity.this, MapsActivity.class);
                        //EditText editText = (EditText) findViewById(R.id.edit_message);
                        //String message = editText.getText().toString();
                        //intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                    }
                });

                progressBar.setVisibility(View.VISIBLE);
                // Start long running operation in a background thread
                new Thread(new Runnable() {
                    private int progressStatus = 0;
                    private Handler handler = new Handler();

                    public void run() {
                        while (progressStatus < 11) {
                            progressStatus += 1;
                            // Update the progress bar and display the
                            //current value in the text view
                            handler.post(new Runnable() {
                                public void run() {
                                    progressBar.setProgress(progressStatus);
                                    textView.setText(String.valueOf(progressStatus));
                                }
                            });
                            try {
                                // Sleep for 200 milliseconds.
                                //Just to display the progress slowly
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}
