package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/adminservlet")
public class AdminServlet extends HttpServlet {
    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            List<List> infoList = UserFacade.infoList(connectionPool);
            List<String> userid = infoList.get(0);
            List<String> email = infoList.get(1);
            List<String> password = infoList.get(2);
            List<String> name = infoList.get(6);
            List<String> balance = infoList.get(3);
            List<String> zipcode = infoList.get(4);
            List<String> address = infoList.get(5);
            List<String> phonenumber = infoList.get(7);


            request.setAttribute("userid", userid);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("name", name);
            request.setAttribute("balance", balance);
            request.setAttribute("zipcode", zipcode);
            request.setAttribute("address", address);
            request.setAttribute("phonenumber", phonenumber);
            request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request,response);

        } catch (DatabaseException e)
        {
            e.printStackTrace();
        }
    }
}
