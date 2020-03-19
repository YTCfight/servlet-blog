package frank.servlet;

import frank.dao.ArticleDAO;
import frank.exception.BusinessException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDelete")
public class ArticleDeleteServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] idsStr = req.getParameter("ids").split(",");
        int[] ids = new int[idsStr.length];
        for (int i = 0; i < idsStr.length; i++) {
            ids[i] = Integer.parseInt(idsStr[i]);
        }
        if (!ArticleDAO.delete(ids)) {
            throw new BusinessException("adelete001", "文章删除数据等于0");
        }
        return null;
    }
}
