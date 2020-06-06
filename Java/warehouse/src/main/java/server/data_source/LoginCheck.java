package server.data_source;

import client.presentation.model.User;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class LoginCheck {


    public static String checkLogin(String name, String pass) {
        String str = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User p where p.name='" + name
                + "' and p.pass='" + pass + "'");
        List<User> user1 = query.list();
        if (user1.isEmpty()) {
            str = ("loginCheck,Username or password incorrect");
        }
        StringBuilder msg = new StringBuilder("");
        for (User j : user1) {
            msg.append(j.toString()).append("\n");
            System.out.println(j.toString());
            str = ("loginCheck," + j.getType()+","+j.getLocation()+","+j.getName());

            if (msg.length() == 0)
                str = ("loginCheck,Username or password incorrect");

            session.getTransaction().commit();
            session.close();
        }
        return str;
    }
    public static void main(String[] args)  {
        //checkLogin("john","123");
    }
}
