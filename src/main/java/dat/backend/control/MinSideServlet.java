package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MinSideServlet", value = "/minside")
public class MinSideServlet extends HttpServlet {
    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList;
        try {
            orderList = UserFacade.orderList(connectionPool);
            request.setAttribute("userOrderList", orderList);
            request.getRequestDispatcher("WEB-INF/minside.jsp").forward(request,response);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}