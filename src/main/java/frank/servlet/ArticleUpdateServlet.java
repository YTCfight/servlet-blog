package frank.servlet;

import frank.dao.ArticleDAO;
import frank.exception.BusinessException;
import frank.model.Article;
import frank.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Article article = JSONUtil.deserialize(req.getInputStream(), Article.class);
        if (!ArticleDAO.update(article)) {
            throw new BusinessException("aupdate001", "修改文章操作执行数量等于0");
        }
        return null;
    }
}
