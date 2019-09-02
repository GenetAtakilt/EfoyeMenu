package com.example.yilma.efoymenu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    private Context context;
    public TextView mTitle,name,description,price;
    public Button finishOrder;
    public ImageView orderedImage;
    public EditText name_et, location_et,phone_et;
    public Spinner time;
    String nametxt,phonetxt, locationtxt,timetxt;
    Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Get access to the custom title view
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/orange.ttf");
        mTitle.setTypeface(custom_font);

        name = (TextView)findViewById(R.id.orderdname);
        description = (TextView)findViewById(R.id.ordereddescription);
        price = (TextView)findViewById(R.id.orderdprice);
        orderedImage = (ImageView)findViewById(R.id.orderedimg);
        name_et = (EditText)findViewById(R.id.name_et);
        location_et = (EditText)findViewById(R.id.location_et);
        phone_et = (EditText)findViewById(R.id.phone_et);
        time = (Spinner)findViewById(R.id.spinner);

        extras = getIntent().getExtras();
        name.setText(extras.getString("name"));
        description.setText(extras.getString("description"));
        price.setText(extras.getString("price"));
        orderedImage.setImageResource(extras.getInt("ordimage"));

          phonetxt = phone_et.getText().toString();
          nametxt = name_et.getText().toString();
          locationtxt = location_et.getText().toString();
          timetxt = time.getSelectedItem().toString();


        finishOrder = (Button)findViewById(R.id.finishorder_button);
        finishOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (( phone_et.getText().toString()).length()<=1||( name_et.getText().toString()).length()<=1||( location_et.getText().toString()).length()<=1){
                    Vibrator vibrator;
                    vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(400);
                    final AlertDialog alertDialog = new AlertDialog.Builder(OrderActivity.this).create();
                    alertDialog.setTitle("Error Making an Order");
                    alertDialog.setMessage("Please fill all the  necessary fields");
                    alertDialog.setIcon(R.drawable.error);

                    alertDialog.setButton("Go Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                    return;

                }
               else if (( phone_et.getText().toString()).length()>=1||( name_et.getText().toString()).length()>=1||( location_et.getText().toString()).length()>=1){

                    AlertDialog alertDialog = new AlertDialog.Builder(OrderActivity.this).create();
                    alertDialog.setTitle("Successfully Made an Order");
                    alertDialog.setMessage("You Have Successfully Made an Order");
                    alertDialog.setIcon(R.drawable.tick);
                    alertDialog.setButton("Finish Order", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String number = "0916587883";
                            String msg = "New order customer info Customer Name:"+ name_et.getText().toString()+" Phone: "+ phone_et.getText().toString()+" Location: "+ location_et.getText().toString()+"Orderd Item : "+extras.getString("name")+"Price"+extras.getString("price");
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(number,null,msg,null,null);
                            Intent intent = new Intent(OrderActivity.this, Category.class);
                            startActivity(intent);
                        }
                    });
                    alertDialog.show();
                    return;



                }
            }
        });

    }

}
