package server.business;

import client.presentation.model.ItemRequest;
import server.data_source.ManagerActions;

public class ManagerDAO {

    public static String updateItemRequestDAO(ItemRequest i){ return ManagerActions.updateItemRequest(i); }

    public static String getRankingsDAO(String orderType){ return ManagerActions.getRankings(orderType);}
}
