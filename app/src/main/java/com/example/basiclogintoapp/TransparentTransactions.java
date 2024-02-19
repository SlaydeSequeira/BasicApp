package com.example.basiclogintoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class TransparentTransactions extends AppCompatActivity {

    // Declare variables to store the entered data
    private String receiverName;
    private String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent_transactions);

        // Access the EditText fields and Button
        EditText editTextReceiverName = findViewById(R.id.edittext1);
        EditText editTextAmount = findViewById(R.id.edittext2);
        Button btnSubmitTransaction = findViewById(R.id.loginbtn);

        // Set a click listener for the button
        btnSubmitTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve data from EditText fields
                receiverName = editTextReceiverName.getText().toString();
                amount = editTextAmount.getText().toString();

                Intent i = new Intent(TransparentTransactions.this,Payment.class);
                int amountValue = Integer.parseInt(amount);
                i.putExtra("Cost", amountValue);
                startActivity(i);
            }
        });
    }
}
