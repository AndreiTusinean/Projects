package server.data_source;

import java.util.ArrayList;
import java.util.List;

public class CustomDiscount extends EmployeeActions.MakeBill {

    public static List<Object> makeDiscountPrice(double discount, int amount, int price) {
        int finalPrice = 0;
        List<Object> l = new ArrayList<>();
        finalPrice = (int) (price * discount);
        l.add(finalPrice);
        l.add(discount);
        return l;
    }
}
