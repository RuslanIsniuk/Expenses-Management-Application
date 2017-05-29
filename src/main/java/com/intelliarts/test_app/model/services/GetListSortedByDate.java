package com.intelliarts.test_app.model.services;

import com.intelliarts.test_app.dao.DateDAO;
import com.intelliarts.test_app.dao.impl.HibernateDateDAO;
import com.intelliarts.test_app.entity.Date;
import com.intelliarts.test_app.model.exceptions.NoDatesFoundException;
import org.apache.log4j.Logger;

import java.util.List;

public class GetListSortedByDate {
    private static final Logger logger = Logger.getLogger(GetListSortedByDate.class);
    private static final String ERROR_MESSAGE = "No expenses found.";

    private DateDAO dateDAO = new HibernateDateDAO();
    private DateComparator dateComparator = new DateComparator();

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
