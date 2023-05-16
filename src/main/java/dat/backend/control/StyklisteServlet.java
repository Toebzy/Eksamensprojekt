package dat.backend.control;

import dat.backend.model.entities.Beregner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "stykliste", value = "/stykliste")
public class StyklisteServlet extends HttpServlet {
    Beregner beregner = new Beregner();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width =Integer.parseInt(request.getParameter("width"));
        int length =Integer.parseInt(request.getParameter("length"));
        System.out.println(beregner.carportBeregner(width, length));
    }
}
