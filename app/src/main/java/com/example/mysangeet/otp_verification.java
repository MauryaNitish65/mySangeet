package com.example.mysangeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.mysangeet.databinding.ActivityOtpVerificationBinding;

import java.util.Objects;

public class otp_verification extends AppCompatActivity {
    private ActivityOtpVerificationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        binding=ActivityOtpVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textMobile.setText(String.format("+91-",getIntent().getStringExtra("mobile ")));
        String st1= Objects.requireNonNull(binding.input1.getText()).toString();
        String st2= Objects.requireNonNull(binding.input2.getText()).toString();
        String st3= Objects.requireNonNull(binding.input3.getText()).toString();
        String st4= Objects.requireNonNull(binding.input4.getText()).toString();
        String st5= Objects.requireNonNull(binding.input5.getText()).toString();
        String st6= Objects.requireNonNull(binding.input6.getText()).toString();
        binding.verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!st1.trim().isEmpty() && !st2.trim().isEmpty() && !st3.trim().isEmpty() && !st4.trim().isEmpty() && !st5.trim().isEmpty() && !st6.trim().isEmpty())
                {
                    Toast.makeText(otp_verification.this, "OTP Verified", Toast.LENGTH_SHORT).show();
                    Intent iHome=new Intent(otp_verification.this,MainActivity.class);
                    startActivity(iHome);
                }
                else{
                    Toast.makeText(otp_verification.this, "Please enter otp", Toast.LENGTH_SHORT).show();
                    binding.input1.setError("OTP is required");
                    binding.input1.requestFocus();
                }
            }
        });
        numberOtpMove();
    }

    private void numberOtpMove() {
        binding.input1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    binding.input2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

        });
        binding.input2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    binding.input3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
        binding.input3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    binding.input4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
        binding.input4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    binding.input5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
        binding.input5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    binding.input6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

    }
}