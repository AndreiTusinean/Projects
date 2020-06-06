package server.business;

import client.presentation.model.Bill;
import client.presentation.model.Item;
import server.data_source.CustomDiscount;
import server.data_source.EmployeeActions;
import server.data_source.StandardDiscount;

public class EmployeeDAO {

    public static String insertItemDAO(Item i) {
        return EmployeeActions.insertItem(i);
    }

    public static String updateItemDAO(Item i) {
        return EmployeeActions.updateItem(i);
    }

    public static String deleteItemDAO(Item i) {
        return EmployeeActions.deleteItem(i);
    }

    public static String viewItemDAO(Item i) {
        return EmployeeActions.viewItem(i);
    }

    //public static String addBillDAO(Bill b, double d, String emplName) { return EmployeeActions.MakeBill.addBill(b, d, emplName); }

    public static String addCustomDiscountBillDAO(Bill b, double d, String emplName) { return CustomDiscount.addBill(b, d, emplName); }

    public static String addStandardDiscountBillDAO(Bill b, double d, String emplName) { return StandardDiscount.addBill(b, d, emplName); }


}
