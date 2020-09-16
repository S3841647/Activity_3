package com.albert.week05_activity2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.liang.coffeeapp2.MESSAGE";

    int quantity = 2; //. Both quantity and name are global variables
    String name;
    String message;
    int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //called when the + button is clicked
    public void increment (View view){
        if (quantity == 100){  // .(quantity) this part is only local variable
            return;
        }
        quantity ++; //equal as saying quantity plus plus
        displayQuantity(quantity);

    }

    //called when the - button is clicked
    public void decrement (View view){
        if (quantity == 0){ //. (quantity) this part is only local variable
            return;
        }
        quantity --; //equal as saying quantity minus minus
        displayQuantity(quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Get user's name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        name = nameEditable.toString();

        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox)
                findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox)
                findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Calculate the price
        totalPrice = calculatePrice(hasWhippedCream, hasChocolate);

        // Display the order summary on the screen
        String message = createOrderSummary(name, totalPrice,
                hasWhippedCream, hasChocolate);

        // start the new intent here
        Intent intent = new Intent(this, DisplayOrderDetails.class);
        // intent.putExtra(EXTRA_MESSAGE, message);
        //  intent.putExtra(EXTRA_MESSAGE,name);
        intent.putExtra("name", name);
        intent.putExtra("message", message);
        intent.putExtra("totalPrice", Integer. toString(totalPrice));
        startActivity(intent);
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // First calculate the price of one cup of coffee
        int basePrice = 5;

        // If the user wants whipped cream, add $1 per cup
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        // If the user wants chocolate, add $2 per cup
        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        // Calculate the total order price by multiplying by the quantity
        return quantity * basePrice;
    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream,
                                      boolean addChocolate) {
        //. setting up output (grab the name and assign it to message)
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate, addChocolate);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n" + getString(R.string.order_summary_price,
                NumberFormat.getCurrencyInstance().format(price));
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

} // end of app