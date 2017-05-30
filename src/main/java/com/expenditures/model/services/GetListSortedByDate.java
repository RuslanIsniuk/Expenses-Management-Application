package com.expenditures.model.services;

import com.expenditures.dao.DateDAO;
import com.expenditures.dao.impl.HibernateDateDAO;
import com.expenditures.entity.Date;
import com.expenditures.model.exceptions.NoDatesFoundException;
import org.apache.log4j.Logger;

import java.util.List;

public class GetListSortedByDate {
    private static final Logger logger = Logger.getLogger(GetListSortedByDate.class);
    private static final String ERROR_MESSAGE = "No expenses found.";

    private DateDAO dateDAO = new HibernateDateDAO();
    private DateComparator dateComparator = new DateComparator();

    public GetListSortedByDate() {
    }

    public GetListSortedByDate(DateDAO dateDAO) {
        this.dateDAO = dateDAO;
    }

    public void execute() {
        List<Date> dateList = dateDAO.readAll();
        try {
            if (dateList.size() == 0) {
                throw new NoDatesFoundException(ERROR_MESSAGE);
            }
            dateList.sort(dateComparator);

            for (Date dateFromList : dateList) {
                System.out.println("\n" + dateFromList.toString() + "\n");
            }
        } catch (NoDatesFoundException ne) {
            logger.info(ne);
            System.out.println(ERROR_MESSAGE);
        }
    }

}
