package com.expenditures.model.services;

import com.expenditures.dao.DateDAO;
import com.expenditures.entity.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GetListSortedByDateTest {
    private GetListSortedByDate getListSortedByDate;

    @Mock
    DateDAO dateDAO;
    @Mock
    List<Date> dateList;
    @Mock
    Date date;

    @Before
    public void setUp() {
        getListSortedByDate = new GetListSortedByDate(dateDAO);
        Iterator<Date> mockIterator = mock(Iterator.class);
        when(dateDAO.readAll()).thenReturn(dateList);
        when(dateList.iterator()).thenReturn(mockIterator);
        when(mockIterator.next()).thenReturn(date);
        when(date.toString()).thenReturn("date toString method");
    }

    @Test
    public void executeDefault(){
        when(dateList.size()).thenReturn(3);
        getListSortedByDate.execute();
        ArgumentCaptor<DateComparator> argument = ArgumentCaptor.forClass(DateComparator.class);
        verify(dateList).sort(argument.capture());
    }

    @Test
    public void executeWithNoDatesFoundException(){
        when(dateList.size()).thenReturn(0);
        getListSortedByDate.execute();
        ArgumentCaptor<DateComparator> argument = ArgumentCaptor.forClass(DateComparator.class);
        verify(dateList,never()).sort(argument.capture());
    }

}