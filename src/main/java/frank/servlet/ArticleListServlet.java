package frank.servlet;

import frank.dao.ArticleDAO;
import frank.model.Article;
import frank.model.Result;
import frank.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/articleList")
public class ArticleListServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) {
        // 解析请求数据 id=1
        Integer id = Integer.parseInt(req.getParameter("id"));
        // 数据库查询 id=1 的用户，该用户发表的所有文章数据，返回给客户端
        return ArticleDAO.queryByUserId(id);
    }
}
