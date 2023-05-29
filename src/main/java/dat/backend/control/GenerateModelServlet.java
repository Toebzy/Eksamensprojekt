package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Calculator;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.model3d.ModelGenerator;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GenerateModelServlet", value = "/GenerateModelServlet")
public class GenerateModelServlet extends HttpServlet {

    private final ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idorder = request.getParameter("idorder");
            int[] dimensions = OrderFacade.getDimensions(idorder, connectionPool);
            int width = dimensions[0];
            int length = dimensions[1];
            int height = dimensions[2] ;
            Calculator calc = new Calculator(length, width, height, connectionPool);
            ModelGenerator model = new ModelGenerator(calc);
            model.create3DModel();
            request.getRequestDispatcher("partslist").forward(request,response);
        } catch (DatabaseException | SQLException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
