package com.lizx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author com.mhout.lizx
 * @version 1.0.0
 * @ClassName:
 * @Description:
 * @date 2019/9/24
 */
public class TestDAO {
    private final static String sql = "SELECT * FROM teacher t WHERE t.Tno='1'";

    public void query(Connection conn) {
        try {
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(sql);
            result.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
