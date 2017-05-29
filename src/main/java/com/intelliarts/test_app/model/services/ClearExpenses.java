package com.intelliarts.test_app.model.services;

import com.intelliarts.test_app.dao.DateDAO;
import com.intelliarts.test_app.dao.impl.HibernateDateDAO;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ClearExpenses {
    private static final Logger logger = Logger.getLogger(GetListSortedByDate.class);
    private static final String SUBMIT_MESSAGE = "Expenses deleted.";

    private DateDAO dateDAO = new HibernateDateDAO();

    public void execute(String dateStrFromUser) {
        Date date = convertToDateObj(dateStrFromUser);

        dateDAO.deleteUsingDate(date);
        System.out.println(SUBMIT_MESSAGE);
    }

    private Date convertToDateObj(String dateStrFromUser) {
        Date dateFromUser = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {

            java.util.Date utilDate = formatter.parse(dateStrFromUser);
            java.sql.Date mySQLDate = new java.sql.Date(utilDate.getTime());

            dateFromUser = mySQLDate;
        } catch (ParseException e) {
            logger.error(e);
        }

        return dateFromUser;
    }

}
