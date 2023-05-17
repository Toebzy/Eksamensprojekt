package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Beregner;
import dat.backend.model.entities.Calculator;
import dat.backend.model.entities.Materiale;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "stykliste", value = "/stykliste")
public class StyklisteServlet extends HttpServlet {
    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    Calculator calc;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        List<Materiale> materialList;
        materialList=beregner.carportBeregner(width,length);

         */
        int width =Integer.parseInt(request.getParameter("width"));
        int length =Integer.parseInt(request.getParameter("length"));
        try {
            Calculator calc = new Calculator(width,length,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        request.setAttribute("stykliste", calc);
        request.getRequestDispatcher("WEB-INF/stykliste.jsp").forward(request,response);
    }
}
