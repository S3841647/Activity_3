package com.albert.week05_activity2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayOrderDetails extends AppCompatActivity {
    String message;
    String name;
    String totalPrice;
    CoffeeDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order_details);
        dbHandler = new CoffeeDBHandler(this, null, null, 1);
        // Get the Intent that started this activity and extract the strings
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        message = intent.getStringExtra("message");
        totalPrice = intent.getStringExtra("totalPrice");

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView2);
        textView.setText(message); //. displaying message details
        //
    }

    public void addButtonClicked(View view) {
        //start the new intent here
        Order order = new Order(name,Integer.parseInt(totalPrice));
        dbHandler.addOrder(order);
        Toast.makeText(getApplicationContext(), "Data Saved!", Toast.LENGTH_SHORT).show();
    }

    //method to send the report details
    public void salesReport(View view) {
        //read the details from the database to produce the report
        String dbString = dbHandler.databaseToString();
        //start the new intent here
        Intent salesIntent = new Intent(this, DisplaySalesDetails.class);
        salesIntent.putExtra("db", dbString);
        startActivity(salesIntent);
    }

    public void sendEmail(View view) {
        // Use an intent (Common) to launch an email app.
        // Send the order summary in the email body.
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        // only email apps should handle this
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                "Coffee order for "+name);
        emailIntent.putExtra(Intent.EXTRA_TEXT,message);

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }




}// end of app