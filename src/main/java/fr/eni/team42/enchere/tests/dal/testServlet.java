package fr.eni.team42.enchere.tests.dal;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "testDAL", value = "/dal/test")
public class testServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        testDAL.init();
    }

    public void destroy() {
    }
}