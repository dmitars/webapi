package com.task.lab3_example.data;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {

    @SerializedName("startDate")
    String startDate;

    @SerializedName("endDate")
    String endDate;

    @SerializedName("function")
    String function;

    public Payment(String dateStart, String dateEnd,String function)throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
        Date startDate,endDate;
        try {
            startDate = dateFormat.parse(dateStart);
            endDate = dateFormat.parse(dateEnd);
        } catch (ParseException e) {
            throw new Exception("Некорректная дата: dd.mm.yyyy");
        }
        if(endDate.before(startDate) && endDate.getTime()!=startDate.getTime())
            throw new Exception("Некорректный промежуток времени!");
        this.startDate = dateStart;
        this.endDate = dateEnd;
        this.function = function;
    }
}
