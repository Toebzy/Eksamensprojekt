package dat.backend.control;

import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OpretBruger", value = "/opretbruger")
public class OpretBrugerServlet extends HttpServlet {
    ConnectionPool connectionPool = new ConnectionPool();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gentagkodeord = request.getParameter("gentagkodeord");
        try {
            if(UserFacade.checkUser(email, connectionPool))
            {
                request.setAttribute("msg", "Denne mail findes allerede i vores system");
                request.getRequestDispatcher("opretbruger.jsp").forward(request,response);
            }
            if(!email.contains("@") || !email.contains("."))
            {
                request.setAttribute("msg", "Ugyldig email");
                request.getRequestDispatcher("opretbruger.jsp").forward(request,response);
            }
            if(!password.equals(gentagkodeord))
            {
                request.setAttribute("msg", "De to kodeord matcher ikke");
                request.getRequestDispatcher("opretbruger.jsp").forward(request,response);
            }
            if(email.contains("@") && email.contains(".") && password.equals(gentagkodeord))
            {
                UserFacade.createUser(request.getParameter("email"),request.getParameter("password"),request.getParameter("zipcode"),request.getParameter("address"),request.getParameter("name"),request.getParameter("phonenumber"), connectionPool);

                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

    }
}
