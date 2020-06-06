package server.data_source;

import client.presentation.model.Bill;
import client.presentation.model.Item;
import client.presentation.model.Ranking;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmployeeActions {

    public static String insertItem(Item i) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(i);
        session.getTransaction().commit();
        session.close();
        return "addItem," + "Inserted item: " + i.toString();
    }

    public static String updateItem(Item i) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println(i.toString());
        session.update(i);
        session.getTransaction().commit();
        session.close();
        return "updateItem," + "Updated item " + i.getId() + " to " + i.toString();
    }

    public static String deleteItem(Item i) {
        String str;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item p where p.id='" + i.getId() + "'");
        List<Item> item1 = query.list();
        if (item1.isEmpty())
            str = "removeItem," + "No item found with id " + i.getId();
        else
            str = "removeItem," + "Deleted item " + i.getId();
        for (Item j : item1) {
            if (j.getId() == i.getId()) {
                session.delete(j);
                session.getTransaction().commit();
            }
        }
        session.close();
        return str;
    }

    public static String viewItem(Item i) {
        String str;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item p where p.id='" + i.getId() + "'");
        List<Item> item1 = query.list();
        if (item1.isEmpty()) {
            str = "viewItem," + "No item found with id " + i.getId();
        }
        StringBuilder msg = new StringBuilder("");
        for (Item j : item1) {
            if (j.getId() == i.getId()) {
                msg.append(j.toString()).append("\n");
            }
        }
        if (msg.length() == 0)
            str = "viewItem," + "No item found with id " + i.getId();
        else
            str = "viewItem," + msg.toString();
        session.getTransaction().commit();
        session.close();
        return str;
    }

    public static void sendMail(String subject, String content) {
        String to = "andrei.tusinean@gmail.com";
        String from = "andrei.tusinean@gmail.com";
        String host = "smtp.gmail.com";//""localhost";
        String mail = "andrei.tusinean@gmail.com";
                                                                                                        String pass = "paddingtonmarmalade,./";

        // Get system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.user", mail);
        properties.put("mail.smtp.password", pass);

        javax.mail.Session session = javax.mail.Session.getInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message, mail, pass);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void addRanking(String name, int amount) {
        int orders = 0;
        int id = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Ranking p where p.name='" + name + "'");
        List<Ranking> rank1 = query.list();
        for (Ranking j : rank1) {
            if (j.getName().equals(name)) {
                id = j.getId();
                orders = j.getOrders() + 1;
                amount += j.getTotalamount();
            }
        }
        session.getTransaction().commit();
        session.close();
        Ranking r = new Ranking();
        r.setId(id);
        r.setName(name);
        r.setOrders(orders);
        r.setTotalamount(amount);

        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(r);
        s.getTransaction().commit();
        s.close();
    }

    public abstract static class MakeBill {

        static public String addBill(Bill b, double discount, String emplName) {
            String str = "";
            Item i = new Item();
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Item p where p.name='" + b.getName() + "'");
            List<Item> item1 = query.list();
            StringBuilder msg = new StringBuilder("");
            boolean enough = true;
            int itemPrice = 0;
            for (Item j : item1) {
                if (j.getName().equals(b.getName())) {
                    i.setId(j.getId());
                    i.setName(j.getName());
                    i.setLocation(j.getLocation());
                    i.setPrice(j.getPrice());
                    b.setLocation(j.getLocation());
                    b.setPrice(j.getPrice());
                    itemPrice = j.getPrice();
                    msg.append(j.toString()).append("\n");
                    if (b.getAmount() > j.getAmount()) {
                        enough = false;
                        i.setAmount(j.getAmount());
                    } else
                        i.setAmount(j.getAmount() - b.getAmount());
                }
            }
            session.getTransaction().commit();
            if (enough) {
                b.setPrice(b.getPrice() * b.getAmount());
                if (msg.length() == 0)
                    str = "addBill," + "No item found with name " + b.getName();
                else
                    str = "addBill," + "Created bill: " + b.toString();

                int price = 1;

                List<Object> l;
                if (discount == 0.0)
                    l = StandardDiscount.makeDiscountPrice(discount, b.getAmount(), b.getPrice());
                else
                    l = CustomDiscount.makeDiscountPrice(discount, b.getAmount(), b.getPrice());
                price = (int) l.get(0);
                discount = (double) l.get(1);

                addRanking(emplName, price);
                b.setPrice(price);

                session.beginTransaction();
                session.save(b);
                session.getTransaction().commit();
                session.close();
                String disc;
                if (discount == 0.0)
                    disc = "";
                else
                    disc = " and a discount of " + (100 - discount * 100) + "%";
                String message = "You have purchased " + b.getAmount() + " " + b.getName() +
                        " from warehouse " + b.getLocation() + " with the total value of " +
                        b.getPrice() + "$.Price per Item: " + itemPrice + disc;
                sendMail("Bill from warehouse ", message);
                updateItem(i);

            } else
                str = "addBill,Not enough items";

            return str;
        }

        public static List<Object> makeDiscountPrice(double discount, int amount, int price) {
            List<Object> l = new ArrayList<Object>();
            price=10;
            l.add(price);
            l.add(discount);
            return l;
        }
    }


}
