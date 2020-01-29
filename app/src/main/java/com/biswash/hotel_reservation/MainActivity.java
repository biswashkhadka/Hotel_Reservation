package com.biswash.hotel_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private Spinner sproomtype;
    EditText etadult,etchild,etroom;
    Button btncalculate;
    TextView tvchecking,tvcheckout,tvtotalcost,tvvat,tvgrandtotal;
    int year1,year2,month1,month2,day1,day2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding
        sproomtype=findViewById(R.id.sproomtype);
        tvchecking=findViewById(R.id.tvchecking);
        tvcheckout=findViewById(R.id.tvcheckout);
        etadult=findViewById(R.id.etadult);
        etchild=findViewById(R.id.etchild);
        etroom=findViewById(R.id.etroom);
        btncalculate=findViewById(R.id.btncalculate);
        tvtotalcost=findViewById(R.id.totalcost);
        tvvat=findViewById(R.id.tvvat);
        tvgrandtotal=findViewById(R.id.tvgrandtotal);


        String roomtype[]={"-- Select Room Type --", "Deluxe","Presidential", "Premium"};
        ArrayAdapter adapter1=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,roomtype
        );
        sproomtype.setAdapter(adapter1);



        tvchecking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadDatePicker();
            }
        });

        tvcheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker1();
            }
        });



        btncalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(tvchecking.getText())) {
                    tvchecking.setText("Please enter checkin date ");
                    return;
                }
                else if (TextUtils.isEmpty(tvcheckout.getText())) {
                    tvcheckout.setText("Please enter checkedout date ");
                    return;
                }


                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.set(year1, month1, day1);
                cal2.set(year2, month2, day2);
                long millis1 = cal1.getTimeInMillis();
                long millis2 = cal2.getTimeInMillis();
                long diff = millis2 - millis1;
                long diffDays = (diff / (86400000));

                int  numRoom = Integer.parseInt(etroom.getText().toString());

                double roomprice,totalprice,vat,grandtotal;
                String roomtype=sproomtype.getSelectedItem().toString();

                if(roomtype=="Deluxe"){
                    roomprice=2000;
                    totalprice=roomprice*numRoom*diffDays;
                    vat=(0.13*totalprice) + totalprice;
                    grandtotal= vat;

                    tvtotalcost.setText("Total cost is: "+totalprice);
                    tvvat.setText("Price after VAT is: "+vat);
                    tvgrandtotal.setText("Grand Total is: "+grandtotal);
                }

                else if(roomtype=="Presidential"){
                    roomprice=5000;
                    totalprice=roomprice*numRoom*diffDays;
                    vat=(0.13*totalprice) + totalprice;
                    grandtotal= vat;

                    tvtotalcost.setText("Total cost is: "+totalprice);
                    tvvat.setText("Price after VAT is: "+vat);
                    tvgrandtotal.setText("Grand Total is: "+grandtotal);
                }

                else if(roomtype=="Premium"){
                    roomprice=4000;
                    totalprice=roomprice*numRoom*diffDays;
                    vat=(0.13*totalprice) + totalprice;
                    grandtotal= vat;

                    tvtotalcost.setText("Total cost is: "+totalprice);
                    tvvat.setText("Price after VAT is: "+vat);
                    tvgrandtotal.setText("Grand Total is: "+grandtotal);
                }

            }
        });

    }

    private  void loadDatePicker(){
        final Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "Checking Date : " +month +"/"+dayOfMonth+"/"+year;
                year1=year;
                month1=month;
                day1=dayOfMonth;

                tvchecking.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }


    private  void loadDatePicker1(){
        final Calendar ca=Calendar.getInstance();
        int year=ca.get(Calendar.YEAR);
        int month=ca.get(Calendar.MONTH);
        int day=ca.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "Checkout Date : " +month +"/"+dayOfMonth+"/"+year;

                year2=year;
                month2=month;
                day2=dayOfMonth;

                tvcheckout.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }


}