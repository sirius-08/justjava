

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int pricePerCup = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox WhippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean addWhippedCream = WhippedCream.isChecked();
        CheckBox hasChocolate =(CheckBox) findViewById(R.id.chocolate);
        boolean addChocolate = hasChocolate.isChecked();
        int price = calculatePrice();
        EditText nameText = (EditText) findViewById(R.id.name);
        String name = nameText.getText().toString();
        String orderSummary = createOrderSummary(price,addWhippedCream,addChocolate,name);
        //displayMessage(orderSummary);

//        Intent intent= new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:29.397846,76.970304"));
        String addresses[]={"jain.dheerain@gmail.com","kalra.tanish8@gmail.com"};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Yout order summary");
        intent.putExtra(Intent.EXTRA_TEXT,orderSummary);
        //intent.putExtra(Intent.EXTRA_EMAIL,addresses);
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    public void addWhippedCream(View view)
    {
        boolean hasWhippedCream = ((CheckBox) view).isChecked();
        if(hasWhippedCream)
            pricePerCup += 1;
        else
            pricePerCup -= 1;
        displayPrice(calculatePrice());
    }

    public void addChocolate(View view)
    {
        boolean hasChocolate = ((CheckBox) view).isChecked();
        if(hasChocolate)
            pricePerCup += 2;
        else
            pricePerCup -=2;
        displayPrice(calculatePrice());

    }

    public void increment(View view)
    {
        if(quantity == 100)
        {
            String text = "You cannot order more than 100 coffees";
            int duration = Toast.LENGTH_SHORT;
            //this gives the context
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
        displayPrice(quantity*pricePerCup);
    }

    public void decrement(View view)
    {
        if(quantity == 1)
        {
            Context context = getApplicationContext();
            String text = "You cannot order less than 1 coffee";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
        displayPrice(quantity*pricePerCup);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */

    public int calculatePrice()
    {
        return quantity*pricePerCup;
    }

    public String createOrderSummary(int price,boolean addWhippedCream,boolean addChocolate,String name)
    {
        String orderSummary = "Name : " + name;
        orderSummary += "\nQuantity : "+quantity;
        orderSummary += "\nAdd Whipped Cream? "+addWhippedCream;
        orderSummary += "\nAdd Choclate? "+addChocolate;
        orderSummary += "\nTotal : $" + price +"\n";
        orderSummary += "Thank You!";
        return orderSummary;
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}