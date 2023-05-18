package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "bestilCarportServlet", value = "/bestilcarportservlet")
public class bestilCarportServlet extends HttpServlet {
    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        int userid = Integer.parseInt(request.getParameter("userid"));
        try {
            UserFacade.createOrder(length, width, userid, connectionPool);

        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("WEB-INF/ordrebekræftelse.jsp").forward(request,response);

    }
}