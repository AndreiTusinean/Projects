package server.data_source;

import java.util.ArrayList;
import java.util.List;

public class StandardDiscount extends EmployeeActions.MakeBill {

    public static List<Object> makeDiscountPrice(double discount, int amount, int price) {
        int finalPrice = 0;
        List<Object> l = new ArrayList<>();
        //50+ items -> 2% discount, 100+ items -> 4% discount
        if (discount == 0.0) {
            if (amount < 50) {
                finalPrice = price;
            }
            if (amount >= 50 && amount < 100) {
                finalPrice = (int) (price * 0.98);
                discount = 0.98;
            }
            if (amount >= 100) {
                finalPrice = (int) (price * 0.96);
                discount = 0.96;
            }
        }
        l.add(finalPrice);
        l.add(discount);
        return l;
    }
}
