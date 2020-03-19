package frank.servlet;

import frank.dao.ArticleDAO;
import frank.dao.UserDAO;
import frank.exception.BusinessException;
import frank.model.Article;
import frank.model.Result;
import frank.model.User;
import frank.util.Constant;
import frank.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/articleAdd")
public class ArticleAddServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        InputStream is = req.getInputStream();
        Article article = JSONUtil.deserialize(is, Article.class);
        // 根据传入数据 userAccount
        User user = UserDAO.queryByName(article.getUserAccout());
        if (user == null) {
            throw new BusinessException(Constant.USER_NULL_ERROR_CODE, "该用户不存在，无法发表文章");
        }
        // 如果用户存在就插入文章数据
        article.setUserId(user.getId());
        if (!ArticleDAO.insert(article)) {
            throw new BusinessException(Constant.INSERT_ARTICLE_ERROR_CODE, "文章插入0条数据");
        }
        return null;
    }
}
