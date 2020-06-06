package server.data_source;

import client.presentation.model.ItemRequest;
import client.presentation.model.Ranking;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ManagerActions {

    public static String updateItemRequest(ItemRequest i) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(i);
        session.getTransaction().commit();
        session.close();
        return "updateItemRequest," + "Inserted item: " + i.toString();
    }

    public static String getRankings(String orderType) {
        StringBuilder str = new StringBuilder();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Ranking p");
        List<Ranking> rank1 = query.list();
        if (orderType.equals("orders")) {
            rank1.sort(Comparator.comparing(Ranking::getOrders));
        }
        if (orderType.equals("amount")) {
            rank1.sort(Comparator.comparing(Ranking::getTotalamount));
        }
        Collections.reverse(rank1);
        str.append("getRanking,");
        for (Ranking j : rank1) {
            str.append(j.toString()).append("\n");
        }
        System.out.println(rank1.toString());
        session.getTransaction().commit();
        session.close();
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(getRankings("amount"));
    }
}
