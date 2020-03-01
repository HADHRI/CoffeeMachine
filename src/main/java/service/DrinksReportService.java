package service;

import collection.OrderList;
import exceptions.UnableToCreateReportException;


public interface DrinksReportService {
    public String doReport(OrderList orderList) throws UnableToCreateReportException;
}
