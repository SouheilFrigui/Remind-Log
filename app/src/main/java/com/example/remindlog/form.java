package com.example.remindlog;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.xml.transform.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

import static com.example.remindlog.Call_Receiver.savedNumber;

public class form extends Activity implements DatePickerDialog.OnDateSetListener{
    private EditText C_name;
    private EditText C_function;
    private EditText C_residence;
    private EditText C_appartement;
    private EditText C_visit_date;
    private EditText C_budget;
    private EditText C_description;
    private Button C_button;
    private Button C_date_picker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    C_name= findViewById(R.id.CName);
    C_function= findViewById(R.id.CFunction);
    C_residence= findViewById(R.id.CResidence);
    C_appartement= findViewById(R.id.CAppartement);
    C_visit_date= findViewById(R.id.CVisitDate);
    C_budget= findViewById(R.id.CBudget);
    C_description= findViewById(R.id.CDescription);
    C_date_picker=findViewById(R.id.Date_picker);

        C_date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });


    C_button= findViewById(R.id.CButton);
    C_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomeRegistration();
        }
    });
    }
    public void CustomeRegistration(){
        String name=C_name.getText().toString();
        String phone=savedNumber;
        String function=C_function.getText().toString();
        String residence=C_residence.getText().toString();
        String appartement=C_appartement.getText().toString();
        String visitDate=C_visit_date.getText().toString();
        String budget=C_budget.getText().toString();
        String description=C_description.getText().toString();
        String commercial= MainActivity.prefConfig.readName().toString();

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy ");
        String dateString = sdf.format(date);
        String sysDate=dateString;

        if(TextUtils.isEmpty(name)) {
            C_name.setError("Vous avez oublié le nom");
            return;
        }
        if(TextUtils.isEmpty(function)) {
            C_function.setError("Vous avez oublié la fonction");
            return;
        }

        if(TextUtils.isEmpty(residence)) {
            C_residence.setError("Vous avez oublié la résidence");
            return;
        }

        if(TextUtils.isEmpty(appartement)) {
            C_appartement.setError("Vous avez oublié l'appartement");
            return;
        }
        if(TextUtils.isEmpty(visitDate)) {
            C_visit_date.setError("Vous avez oublié la date de visite");
            return;
        }

        if(TextUtils.isEmpty(budget)) {
            C_budget.setError("Vous avez oublié le budget");
            return;
        }

        if(TextUtils.isEmpty(description)) {
            C_description.setError("Vous avez oublié la fonction");
            return;
        }



        Call<Customer> call=MainActivity.api_interface.performCustomerRegistration(name,phone,function,residence,appartement,visitDate,budget,description
                ,sysDate,commercial);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                assert response.body()!=null;
                switch (response.body().getResult()){
                    case "ok":
                        MainActivity.prefConfig.displayToast("Client inscrit avec succèes !");
                        break;
                    case "error":
                        MainActivity.prefConfig.displayToast("Il ya une erreur !");
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });
        C_name.setText("");
        C_function.setText("");
        C_residence.setText("");
        C_appartement.setText("");
        C_visit_date.setText("");
        C_budget.setText("");
        C_description.setText("");


    }



    private void showDatePickerDialog() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this, this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        }


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String date= day + " - " + month + " - " + year;
        C_visit_date.setText(date);

    }
}