package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "paycarport", value = "/paycarport")
public class PayCarportServlet extends HttpServlet
{
    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String currentBalance = request.getParameter("balance");
        String price = request.getParameter("price");
        String userid = request.getParameter("userid");
        String idorder = request.getParameter("idorder");
        float newBalance = Float.parseFloat(currentBalance) - Float.parseFloat(price);
        String newBalanceString = Float.toString(newBalance);
        if(newBalance >= 0)
        {
            try
            {
                UserFacade.balanceChange(newBalanceString, userid, connectionPool);
                User user = (User) request.getSession().getAttribute("user");
                user.setBalance(newBalanceString);
                OrderFacade.updatePaid(idorder, true, connectionPool);
                request.getRequestDispatcher("mypage").forward(request,response);
            } catch (DatabaseException e)
            {
                e.printStackTrace();
            }
        }
        else if(newBalance < 0)
        {
            request.setAttribute("msg","Der er ikke nok penge på din konto");
            request.getRequestDispatcher("mypage").forward(request,response);
        }
    }
}
