package dat.backend.control;

import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderStatusServlet", value = "/orderstatusservlet")
public class OrderStatusServlet extends HttpServlet {
    ConnectionPool connectionPool = new ConnectionPool();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String status = request.getParameter("status");
       String idorder = request.getParameter("idorder");

       if (status==null){
           request.getRequestDispatcher("orderlist").forward(request,response);
       }
        try {
            OrderFacade.updateStatus(status, idorder, connectionPool);
        } catch (DatabaseException e)
        {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.getRequestDispatcher("orderlist").forward(request,response);
    }
}
