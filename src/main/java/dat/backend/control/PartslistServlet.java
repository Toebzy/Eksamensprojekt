package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Calculator;

import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Partslist", value = "/partslist")
public class PartslistServlet extends HttpServlet {
    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    Calculator calc;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("partslist", calc);
        request.getRequestDispatcher("WEB-INF/partslist.jsp").forward(request,response);
    }
}
