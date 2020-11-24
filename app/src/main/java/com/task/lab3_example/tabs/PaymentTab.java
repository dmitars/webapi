package com.task.lab3_example.tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.task.lab3_example.R;
import com.task.lab3_example.data.Payment;
import com.task.lab3_example.managers.PaymentManager;

public class PaymentTab extends Fragment {
    private final Context mainContext;
    private Spinner spinner;

    private EditText etStartDate;
    private EditText etEndDate;
    Button btnPay;
    PaymentManager paymentManager;

    public PaymentTab(Context context){
        this.mainContext = context;
    }

    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_payment, container, false);

        spinner = root.findViewById(R.id.spFunctions);
        etStartDate = root.findViewById(R.id.etStartDate);
        etEndDate = root.findViewById(R.id.etEndDate);
        btnPay = root.findViewById(R.id.btnPay);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mainContext,
                R.array.functions, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setSelection(0);

        btnPay.setOnClickListener(view->pay());

        paymentManager = PaymentManager.getInstance(this);

        return root;
    }


    private void pay(){
        String dateStart = etStartDate.getText().toString();
        String dateEnd = etEndDate.getText().toString();
        try{
            String functionName="";
            switch (spinner.getSelectedItemPosition()){
                case 0:
                    functionName = "OrderBook";
                    break;
                case 1:
                    functionName = "AddBook";
                    break;
                case 2:
                    functionName = "UpdateBook";
                    break;
                case 3:
                    functionName = "DeleteBook";
                    break;
            }
            Payment payment = new Payment(dateStart,dateEnd,functionName);
            paymentManager.sentPayment(payment);
        } catch (Exception e) {
            Toast.makeText(mainContext,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void setToken(String function,String token){
        SharedPreferences sharedPreferences = mainContext.getSharedPreferences("tokens",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(function,token);
        editor.apply();
    }
}
