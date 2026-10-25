package com.example.quickserve.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quickserve.R;

public class UpiPaymentActivity extends AppCompatActivity {

    private EditText editTextUpiId, editTextAmount;
    private Button buttonPay;

    ImageView imgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upi_payment);

        editTextUpiId = findViewById(R.id.editTextUpiId);
        editTextAmount = findViewById(R.id.editTextAmount);
        buttonPay = findViewById(R.id.buttonPay);
        imgButton =(ImageView) findViewById(R.id.backBtn);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PayPage.class);
                startActivity(intent);
                finish();
            }
        });


        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processPayment();
            }
        });

        imgButton =(ImageView) findViewById(R.id.btnPayQ);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void processPayment() {
        String upiId = editTextUpiId.getText().toString().trim();
        String amount = editTextAmount.getText().toString().trim();

        // Perform validation here
        if (upiId.isEmpty() || amount.isEmpty()) {
            showToast("Please fill in all fields.");
        } else {
            // Perform payment processing logic here
            showToast("Payment of " + amount + " to UPI ID " + upiId + " processed successfully.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}