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

@WebServlet(name = "ordercarportservlet", value = "/ordercarportservlet")
public class OrderCarportServlet extends HttpServlet {


    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String type = request.getParameter("button");
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        int height = Integer.parseInt(request.getParameter("height"));
        int userid = Integer.parseInt(request.getParameter("userid"));
       if(type.equals("Bestil Carport"))
       {
           try
           {
               OrderFacade.createOrder(length, width, height, userid, connectionPool);

           } catch (DatabaseException | SQLException e)
           {
               request.setAttribute("errormessage", e.getMessage());
               request.getRequestDispatcher("error.jsp").forward(request, response);
           }

           request.getRequestDispatcher("WEB-INF/orderconfirmation.jsp").forward(request, response);
       }
       if(type.equals("Se pris"))
       {
           try
           {
               Calculator calc = new Calculator(length, width, height, connectionPool);
               ModelGenerator model = new ModelGenerator(calc);
               model.create3DModel();

               float price = calc.getTotalPrice();


               request.setAttribute("msg", "Pris for carport med længde: " + length + "cm. Bredde: " + width + "cm. Højde: "+height+"cm. Price ="+price + "kr");
               request.setAttribute("length", length);
               request.setAttribute("width", width);
               request.setAttribute("height",height);
               request.getRequestDispatcher("ordercarport.jsp").forward(request,response);
           } catch (DatabaseException | SQLException e)
           {
               e.printStackTrace();
           }

       }
    }
}
