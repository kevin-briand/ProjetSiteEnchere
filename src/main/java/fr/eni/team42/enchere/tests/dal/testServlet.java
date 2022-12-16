package fr.eni.team42.enchere.tests.dal;

import fr.eni.team42.enchere.BusinessException;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "testDAL", value = "/dal/test")
public class testServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            testDAL.init();
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}