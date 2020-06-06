package server.data_source;

import client.presentation.model.Item;
import client.presentation.model.ItemRequest;
import client.presentation.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import server.business.ReportInterface;
import server.business.ReportPdf;
import server.business.ReportTxt;

import java.io.File;
import java.util.List;

public class AdminActions {

    public static String insertUser(User u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(u);
        session.getTransaction().commit();
        session.close();
        return "addUser," + "Inserted user: " + u.toString();
    }

    public static String updateUser(User u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(u);
        //AdminUI.showMessage("Updated user " + u.getId() + " to " + u.toString());
        session.getTransaction().commit();
        session.close();
        return "updateUser," + "Updated user " + u.getId() + " to " + u.toString();
    }

    public static String deleteUser(User u) {
        String str = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User p where p.id='" + u.getId() + "'");
        List<User> user1 = query.list();
        if (user1.isEmpty())
            str = "removeUser," + "No user found with id " + u.getId();
        else
            str = "removeUser," + "Deleted user " + u.getId();
        for (User j : user1) {
            if (j.getId() == u.getId()) {
                session.delete(j);
                session.getTransaction().commit();
            }
        }
        session.close();
        return str;
    }

    public static String viewUser(User u) {
        String str = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User p where p.id='" + u.getId() + "'");
        List<User> user1 = query.list();
        if (user1.isEmpty()) {
            str = "viewUser," + "No user found with id " + u.getId();
        }
        StringBuilder msg = new StringBuilder("");
        for (User j : user1) {
            if (j.getId() == u.getId()) {
                msg.append(j.toString()).append("\n");
            }
        }
        if (msg.length() == 0)
            str = "viewUser," + "No user found with id " + u.getId();
        else
            str = "viewUser," + msg.toString();
        session.getTransaction().commit();
        session.close();
        return str;
    }

    public static String makeReportDS(int i, String type) {
        String str = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item p where p.location='" + i + "'");
        List<Item> item1 = query.list();
        if (item1.isEmpty()) {
            str = "report," + "No items found in warehouse " + i;
        }
        StringBuilder msg = new StringBuilder("");
        for (Item j : item1) {
            if (j.getLocation() == i) {
                msg.append(j.toString()).append("\n");
            }
        }
        if (msg.length() == 0)
            str = "report," + "No items found in warehouse " + i;
        else {
            if (type.equals("txt")) {
                File f = ReportInterface.getLocation();
                System.out.println("You selected the directory: " + f.toString());
                System.out.println("..........................TXT......................................");
                ReportInterface ri = new ReportTxt();
                ri.makeReport(msg.toString(), f);
                str = "report," + msg.toString();
            } else {
                File f = ReportInterface.getLocation();
                System.out.println("You selected the directory: " + f.toString());
                System.out.println("..........................PDF......................................");
                ReportInterface ri = new ReportPdf();
                ri.makeReport(msg.toString(), f);
                str = "report," + msg.toString();
            }

        }
        session.getTransaction().commit();
        session.close();
        return str;
    }

    public static String viewRequests() {
        String str = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ItemRequest");
        List<ItemRequest> item1 = query.list();
        if (item1.isEmpty()) {
            str = "viewRequests,No requests found";
        }
        StringBuilder msg = new StringBuilder("");
        for (ItemRequest j : item1) {
            msg.append(j.toString()).append("\n");
        }
        if (msg.length() == 0)
            str = "viewRequests,No requests found";
        else
            str = "viewRequests," + msg.toString();
        session.getTransaction().commit();
        session.close();
        return str;
    }

    public static String deleteRequest(ItemRequest i) {
        String str = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ItemRequest p where p.id='" + i.getId() + "'");
        List<ItemRequest> item1 = query.list();
        if (item1.isEmpty())
            str = "No item found with id " + i.getId();
        else
            str = "Deleted ItemRequest " + i.getId();
        for (ItemRequest j : item1) {
            if (j.getId() == i.getId()) {
                session.delete(j);
                session.getTransaction().commit();
            }
        }
        session.close();
        return str;
    }

    public static String grantRequest(ItemRequest i) {
        String str = "";
        Item it = new Item();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ItemRequest p where p.id='" + i.getId() + "'");
        List<ItemRequest> item1 = query.list();
        if (item1.isEmpty()) {
            str="adminGrantRequest,"+"No ItemRequest found with id " + i.getId();
        }
        StringBuilder msg = new StringBuilder("");
        for (ItemRequest j : item1) {
            if (j.getId() == i.getId()) {
                msg.append(j.toString()).append("\n");
                it.setId(j.getId());
                it.setAmount(j.getAmount());
                it.setLocation(j.getLocation());
                it.setName(j.getName());
                it.setPrice(j.getPrice());
            }
        }
        if (msg.length() == 0)
            str="adminGrantRequest,"+"No ItemRequest found with id " + i.getId();
        else
            str="adminGrantRequest,"+msg.toString();
        session.getTransaction().commit();
        session.close();

        EmployeeActions.updateItem(it);
        str+=deleteRequest(i);
        return str;
    }

}
