package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.Partslist;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.List;

public class OrderFacade {
    public static List<User> infoList(ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.infoList(connectionPool);
    }
    public static List<Order> orderList(ConnectionPool connectionpool) throws DatabaseException{
        return OrderMapper.orderList(connectionpool);
    }
    public static void createOrder(int length, int width,int height, int userid, ConnectionPool connectionPool) throws DatabaseException, SQLException {
        OrderMapper.createOrder(length,width,height,userid,connectionPool);
    }
    public static void updateStatus(String status, String idorder, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.updateStatus(status, idorder, connectionPool);
    }
    public static void updatePaid(String idorder, boolean b, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.updatePaid(idorder, b, connectionPool);
    }
    public static int[] getDimensions( String idorder, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getDimensions(idorder,connectionPool);
    }
    public static List<Partslist> createPartsList(String idorder, ConnectionPool connectionpool) throws DatabaseException {
        return OrderMapper.createPartsList(idorder,connectionpool);

    }
}