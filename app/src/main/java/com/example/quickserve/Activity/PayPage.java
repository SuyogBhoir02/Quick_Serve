package com.example.quickserve.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.quickserve.R;
import com.example.quickserve.databinding.ActivityCartBinding;

public class PayPage extends AppCompatActivity {
    private CartActivity binding;


    RadioGroup radioGroup;
    RadioButton radioButton;
    ImageView imgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_page);

        radioGroup = findViewById(R.id.radioGroup);
        imgButton =(ImageView) findViewById(R.id.backBtn);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imgButton =(ImageView) findViewById(R.id.btnGpay);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpiPaymentActivity.class);
                startActivity(intent);
                finish();
            }
        });


        imgButton =(ImageView) findViewById(R.id.btnVisa);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CardMat.class);
                startActivity(intent);
                finish();
            }
        });



        Button buttonApply = findViewById(R.id.button_apply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

               if (selectedRadioButtonId == R.id.CreditCard) {
                    Intent intent = new Intent(getApplicationContext(), CardMat.class);
                    startActivity(intent);
                    finish();


                } else if (selectedRadioButtonId == R.id.Upi) {
                    Intent intent = new Intent(getApplicationContext(), UpiPaymentActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });



    }

    public void checkButton(View v){
        int radioId= radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected Radio Button:" +radioButton.getText(),Toast.LENGTH_SHORT).show();
    }

}

