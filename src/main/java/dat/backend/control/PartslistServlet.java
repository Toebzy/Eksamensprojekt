package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Calculator;

import dat.backend.model.entities.Partslist;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

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
        String idorder = request.getParameter("idorder");
        List<Partslist> partslist = null;
        try {
            partslist = OrderFacade.createPartsList(idorder,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        request.setAttribute("partslist", partslist);
        request.getRequestDispatcher("WEB-INF/partslist.jsp").forward(request,response);
    }
}
