package com.example.basiclogintoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentData;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.time.Duration;
import java.util.ArrayList;
public class Payment extends AppCompatActivity {
    CardView send;
    TextView send2;
    final int UPI_PAYMENT = 0;

    ImageView i1,i2,i3,i4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        int a=0; // use like case to open gpay,payth etc;
        send2 = findViewById(R.id.send2);
        send = findViewById(R.id.send);
        i1=findViewById(R.id.img1);
        i2=findViewById(R.id.img2);
        i3=findViewById(R.id.img3);
        i4=findViewById(R.id.img4);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payUsingUpi(1);
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payUsingUpi(2);
            }
        });

        send2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payUsingUpi(a);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payUsingUpi(a);
            }
        });
    }
    public void changeCardColor(View view) {
        CardView cardView = findViewById(R.id.send);
        cardView.setCardBackgroundColor(Color.parseColor("#339A38"));
        // Apply a scale animation
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(cardView, "scaleX", 0.9f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(cardView, "scaleY", 0.9f);
        scaleDownX.setDuration(200);
        scaleDownY.setDuration(200);

        // Play the scale down animation
        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);
        scaleDown.start();
    }

    void payUsingUpi(int a) {
        String amount = "1.00"; // You can dynamically set this amount
        Uri uri = new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", "slaydesequeira03@okhdfcbank") // your personal VPA
                .appendQueryParameter("pn", "Slayde Sequeira") // your name
                .appendQueryParameter("am", amount) // amount
                .appendQueryParameter("cu", "INR") // currency
                .appendQueryParameter("tn", "For personal use") // transaction note
                .build();
        String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
        int GOOGLE_PAY_REQUEST_CODE = 123;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
        if(a==1)
        {
            Intent paytmIntent = getPackageManager().getLaunchIntentForPackage("net.one97.paytm");
            if (paytmIntent != null) {
                startActivity(paytmIntent);
            } else {
                // Paytm app is not installed, handle this case accordingly
                // For example, redirect the user to download the Paytm app from the Play Store
            }
        }
        if(a==2) {
            startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);          //use if u only wanna show google pay
        }
        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
        if (null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(Payment.this, "No UPI app found, please install one to continue", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        // Handle the payment response here
                    } else {
                        // Handle case when return data is null
                    }
                } else {
                    // Handle case when user simply back without payment
                }
                break;
        }
    }
}
