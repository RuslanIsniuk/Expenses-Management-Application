package com.expenditures.model.services;


import com.expenditures.entity.Date;

import java.util.Comparator;

public class DateComparator implements Comparator<Date> {
    @Override
    public int compare(Date d1, Date d2) {
        java.sql.Date date1 = d1.getDate();
        java.sql.Date date2 = d2.getDate();

        return date1.compareTo(date2);
    }
}
