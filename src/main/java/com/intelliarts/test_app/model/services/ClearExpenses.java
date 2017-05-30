package com.intelliarts.test_app.model.services;

import com.intelliarts.test_app.dao.DateDAO;
import com.intelliarts.test_app.dao.impl.HibernateDateDAO;
import com.intelliarts.test_app.model.exceptions.NoDatesFoundException;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ClearExpenses {
    private static final Logger logger = Logger.getLogger(ClearExpenses.class);
    private static final String SUBMIT_MESSAGE = "Expenses deleted.";
    private static final String ERROR_MESSAGE = "No date found.";

    private DateDAO dateDAO = new HibernateDateDAO();

    public ClearExpenses(){}

    public ClearExpenses(DateDAO dateDAO) {
        this.dateDAO = dateDAO;
    }

    public void execute(String dateStrFromUser) {
        Date date = convertToDateObj(dateStrFromUser);

        try {
            com.intelliarts.test_app.entity.Date dateToRemove = dateDAO.readUsingDate(date);
            if (dateToRemove == null) {
                throw new NoDatesFoundException();
            }

            dateDAO.deleteUsingDate(date);
            System.out.println("\n" + SUBMIT_MESSAGE);
        } catch (NoDatesFoundException ne) {
            logger.info(ne);
            System.out.println(ERROR_MESSAGE);
        }

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
