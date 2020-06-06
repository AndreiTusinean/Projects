package server.business;

import server.data_source.LoginCheck;

public class LoginDAO {
    public static String checkLoginDAO(String name,String pass){ return LoginCheck.checkLogin(name, pass); }
    public static void main(String[] args){ }
}
