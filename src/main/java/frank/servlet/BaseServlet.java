package frank.servlet;

import frank.exception.BaseException;
import frank.model.Article;
import frank.model.Result;
import frank.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

abstract public class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // 返回json数据格式
        resp.setContentType("application/json; charset=UTF-8");

        Result result = new Result();
        try {
            // 调用servlet子类的process方法，实现具体业务
            Object data = process(req, resp);

            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("操作成功");
            result.setData(data);
        } catch (Exception e) {
            if (e instanceof BaseException) {
                BaseException ex = (BaseException) e;
                result.setCode(ex.getCode());
                result.setMessage(ex.getMessage());
            } else {
                result.setCode("500");
                result.setMessage("服务器出错了");
            }
            e.printStackTrace();
        }
        PrintWriter pw = resp.getWriter();
        pw.println(JSONUtil.serialize(result));
        pw.flush();

    }

    public abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
