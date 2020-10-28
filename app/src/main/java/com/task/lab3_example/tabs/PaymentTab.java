package com.task.lab3_example.tabs;

import android.content.Context;
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
import com.task.lab3_example.data.PaymentManager;

import java.text.SimpleDateFormat;
import java.util.Date;

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

        paymentManager = PaymentManager.getInstance();

        return root;
    }


    private void pay(){
        String dateStart = etStartDate.getText().toString();
        String dateEnd = etEndDate.getText().toString();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
            Date startDate = dateFormat.parse(dateStart);
            Date endDate = dateFormat.parse(dateEnd);
            if(endDate.before(startDate) && endDate.getTime()!=startDate.getTime())
                throw new Exception("Некорректный промежуток времени!");
            String answer = paymentManager.sentPayment(dateStart,dateEnd,spinner.getSelectedItem().toString());
            Toast.makeText(mainContext,answer,Toast.LENGTH_SHORT).show();
            Toast.makeText(mainContext,"date is correct!",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(mainContext,"Некорректная дата (dd-mm-yyyy)",Toast.LENGTH_SHORT).show();
        }
    }
}
