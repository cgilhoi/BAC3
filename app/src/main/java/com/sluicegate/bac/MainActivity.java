package com.sluicegate.bac;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.icu.text.DecimalFormat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompatApi23;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.neno0o.ubersdk.Activites.Authentication;
import com.neno0o.ubersdk.Uber;
import com.neno0o.ubersdk.Widgets.UberButton;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final int UBER_AUTHENTICATION = 1;



    public void uber (View view) {

        WebView webView = (WebView) findViewById(R.id.uberWeb);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://uber.com");

        webView.animate().alpha(1).setDuration(2000);
    }

    public void calculate(View view) {

        EditText genderField = (EditText) findViewById(R.id.genderEdit);

        Double genderConstant;

        String genderString = genderField.toString();

        if (genderString == "Male") {

            genderConstant = 0.68;

        } else

            genderConstant = 0.55;


        Log.i("gender constant:", genderConstant.toString());

        EditText weight = (EditText) findViewById(R.id.bodyEdit);

        Integer weightInt = Integer.parseInt(weight.getText().toString());

        EditText drinks = (EditText) findViewById(R.id.drinksEdit);

        Double drinksDub = Double.parseDouble(drinks.getText().toString());

        Double bac;

        EditText time = (EditText) findViewById(R.id.timeEdit);

        Double timeDub = Double.parseDouble(time.getText().toString());

        bac = (((drinksDub * 17)/(weightInt * 453.6 * genderConstant))*100)-0.015*timeDub;

        String numFin = String.format("%.2f", bac);

        if (bac > .08) {

            String soberJudge = ".  Call a cab or ride with a friend";

            Toast.makeText(getApplicationContext(), "Your BAC is:" + numFin + soberJudge, Toast.LENGTH_LONG).show();

            Button uberField = (Button) findViewById(R.id.uberBut);

            uberField.animate().alpha(1).setDuration(2000);

        } else {

            String soberJudge = ".  You seem OK to drive";

            Toast.makeText(getApplicationContext(), "Your BAC is:" + numFin + soberJudge, Toast.LENGTH_LONG).show();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uber.getInstance().init("kfkHwHbdVBKV2DW1CGq9BpkZniasgVcX",
                "YHTK-UM0qiQJJwpkr6aeDJjiKagiPLlUM1EAIcWL",
                "sivweccNqQ990hL_inb7F0lyIHA4Q6FuEaiJnGGd",
                "https://thesluicegate.auth0.com/login/callback");

       UberButton uberButton = (UberButton) findViewById(R.id.uberBtn);
        uberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Authentication.class);




                startActivityForResult(intent, UBER_AUTHENTICATION);





                Log.i("the code is:", String.valueOf(UBER_AUTHENTICATION));


            }


        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("gr8Scott", String.valueOf(requestCode));

        if (requestCode == UBER_AUTHENTICATION) {
            // you have now access token
            // you can access resources on behalf of an Uber use



            Log.i("gr8Scott", String.valueOf(requestCode));
        }
    }
}


