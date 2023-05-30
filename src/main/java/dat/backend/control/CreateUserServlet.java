package dat.backend.control;

import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateUser", value = "/createuser")
public class CreateUserServlet extends HttpServlet {
    ConnectionPool connectionPool = new ConnectionPool();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gentagkodeord = request.getParameter("gentagkodeord");
        String zipcode = request.getParameter("zipcode");
        try {
            if(UserFacade.checkEmail(email, connectionPool))
            {
                System.out.println("Email already exists");
                request.setAttribute("msg", "Denne mail findes allerede i vores system");
                request.getRequestDispatcher("createuser.jsp").forward(request,response);
            }
            else if(!email.contains("@") || !email.contains("."))
            {
                request.setAttribute("msg", "Ugyldig email");
                request.getRequestDispatcher("createuser.jsp").forward(request,response);
            }
            else if(!password.equals(gentagkodeord))
            {
                request.setAttribute("msg", "De to kodeord matcher ikke");
                request.getRequestDispatcher("createuser.jsp").forward(request,response);
            }
            else if(zipcode.length()!=4 || !UserFacade.checkZip(zipcode, connectionPool)){
                request.setAttribute("msg", "Vi leverer desværre ikke til dette postnummer");
                request.getRequestDispatcher("createuser.jsp").forward(request,response);
            }

            else
            {
                UserFacade.createUser(request.getParameter("email"),request.getParameter("password"),request.getParameter("zipcode"),request.getParameter("address"),request.getParameter("name"),request.getParameter("phonenumber"), connectionPool);
                request.getRequestDispatcher("login.jsp").forward(request, response);

            }
        } catch (DatabaseException e)
        {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
