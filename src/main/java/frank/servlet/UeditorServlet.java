package frank.servlet;

import frank.util.MyActionEnter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ueditor")
public class UeditorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rootPath = getClass().getClassLoader().getResource("config.json").getPath();
        MyActionEnter actionEnter = new MyActionEnter(req, rootPath);
        String exe = actionEnter.exec();
        PrintWriter pw = resp.getWriter();
        pw.println(exe);
        pw.flush();
    }
}
