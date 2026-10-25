package com.example.quickserve.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.quickserve.R;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickserve.R;

public class CardMat extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_mat);

        // Assume you have TextViews to display card details
        TextView cardNumberTextView = findViewById(R.id.textViewCardNumber);
        TextView expiryDateTextView = findViewById(R.id.editTextCardNumber);
        TextView cvvTextView = findViewById(R.id.textViewCVV);

        // Fetch card details from intent or any other source
        String cardNumber = "1234 5678 9012 3456";
        String expiryDate = "12/24";
        String cvv = "123";

        // Display card details in TextViews
        cardNumberTextView.setText(cardNumber);
        expiryDateTextView.setText(expiryDate);
        cvvTextView.setText(cvv);

        // Simulate payment process
        simulatePaymentProcess();
    }

    private void simulatePaymentProcess() {
        // Simulate payment processing, e.g., connecting to a payment gateway
        // For demonstration, let's just display a toast message indicating successful payment
        Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show();
    }
}