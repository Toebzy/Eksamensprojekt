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
        try {
            UserFacade.createUser(request.getParameter("email"),request.getParameter("password"),request.getParameter("zipcode"),request.getParameter("address"),request.getParameter("name"),request.getParameter("phonenumber"), connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
