package frank.dao;

import frank.exception.SystemException;
import frank.model.User;
import frank.util.Constant;
import frank.util.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public static User queryByName(String name) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "select id, name, create_time from user where name = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(name);
                user.setCreateTime(new Date(rs.getTimestamp("create_time").getTime()));
                return user;
            }
            return null;
        } catch (Exception e) {
            throw new SystemException(Constant.QUERY_USER_ERROR_CODE, "查询用户信息出错", e);
        } finally {
            DBUtil.close(connection, ps, rs);
        }
    }
}
