package com.example.mysangeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mysangeet.databinding.ActivityMobileNumberLoginBinding;

import java.util.Objects;

public class Mobile_number_login extends AppCompatActivity {
    private ActivityMobileNumberLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_number_login);
        binding =ActivityMobileNumberLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.getOtp.setOnClickListener(new View.OnClickListener() {

//            String number= Objects.requireNonNull(binding.mobile.getText()).toString();
            @Override
            public void onClick(View view) {
                Intent iHome=new Intent(Mobile_number_login.this,otp_verification.class);
                startActivity(iHome);
//                if(TextUtils.isEmpty(number))
//                {
//                    Toast.makeText(Mobile_number_login.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
//                    binding.mobile.setError("Mobile number is required");
//                    binding.mobile.requestFocus();
//                }
//                else if(number.length()!=10)
//                {
//                    Toast.makeText(Mobile_number_login.this, "Please re-enter mobile number", Toast.LENGTH_SHORT).show();
//                    binding.mobile.setError("Mobile number must be of 10 digits");
//                    binding.mobile.requestFocus();
//                }
//                else{
//                    Toast.makeText(Mobile_number_login.this, "Get OTP", Toast.LENGTH_SHORT).show();
//                    Intent iHome=new Intent(Mobile_number_login.this,otp_verification.class);
//                    iHome.putExtra("mobile ",number);
//                    startActivity(iHome);
//                }
            }
        });
    }
}