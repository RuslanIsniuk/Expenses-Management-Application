package com.expenditures.model.services;

import com.expenditures.dao.DateDAO;
import com.expenditures.dao.impl.HibernateDateDAO;
import com.expenditures.model.exceptions.NoDatesFoundException;
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
            com.expenditures.entity.Date dateToRemove = dateDAO.readUsingDate(date);
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
            dateFromUser = new Date(utilDate.getTime());
        } catch (ParseException e) {
            logger.error(e);
        }

        return dateFromUser;
    }

}
