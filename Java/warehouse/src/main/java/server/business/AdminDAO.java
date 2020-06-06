package server.business;

import client.presentation.model.ItemRequest;
import client.presentation.model.User;
import server.data_source.AdminActions;

public class AdminDAO {

    public static String insertUserDAO(User u){ return AdminActions.insertUser(u); }

    public static String updateUserDAO(User u){
        return AdminActions.updateUser(u);
    }

    public static String deleteUserDAO(User u){
        return AdminActions.deleteUser(u);
    }

    public static String viewUserDAO(User u){
        return AdminActions.viewUser(u);
    }

    public static String makeReportDAO(int i,String type){ return AdminActions.makeReportDS(i,type);}

    public static String viewRequestsDAO(){return AdminActions.viewRequests();}

    public static String grantRequestDAO(ItemRequest i){return AdminActions.grantRequest(i);}
}
