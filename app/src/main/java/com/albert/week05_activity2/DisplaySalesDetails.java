package com.albert.week05_activity2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class DisplaySalesDetails extends AppCompatActivity {
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sales_details);

        Intent intent = getIntent();
        message = intent.getStringExtra("db");
        //Capture the Layout's TextView and set the string as its text
        TextView salesView = (TextView) findViewById(R.id.displayTextView);
        salesView.setText(message);
    }
    public void reorder(View view) {
        final Intent mainAcitivity = new Intent(this, MainActivity.class);
        mainAcitivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mainAcitivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainAcitivity);
    }
}