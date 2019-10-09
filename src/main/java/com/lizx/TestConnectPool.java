package com.lizx;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.beans.PropertyVetoException;

/**
 * @author com.mhout.lizx
 * @version 1.0.0
 * @ClassName: 测试连接池的性能
 * @Description:
 * @date 2019/9/26
 */
public class TestConnectPool {
    // 驱动
    static String driver = "com.mysql.jdbc.Driver";

    // 数据库连接地址
    static String jdbcUrl = "jdbc:mysql://localhost:3306/t_lizx?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";

    // 数据库用户名
    static String user = "root";

    // 数据库密码
    static String passwd = "root123";

    // 连接池初始化大小
    static int initialSize = 5;

    // 连接池最小空闲
    static int minPoolSize = 10;

    // 连接池最大连接数量
    static int maxPoolSize = 1000;

    // 等待连接池分配连接的最大时长，30秒
    static int connectionTimeout = 30000;

    // 最小逐出时间，100秒
    static int maxIdleTime = 100000;

    // 连接失败重试次数
    static int retryAttempts = 10;

    // 当连接池连接耗尽时获取连接数
    static int acquireIncrement = 5;

    // c3p0数据源
    static ComboPooledDataSource c3p0DataSource = getC3p0DataSource();

    // Druid数据源
    static DruidDataSource druidDataSource = getDruidDataSource();


    // Tomcat Jdbc Pool数据源
    static DataSource tomcatDataSource = getTomcatDataSource();

    // Hikari数据源
    static HikariDataSource hikariDataSource = getHikariDataSource();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("start==============>" + start);
        int times = 2;
        TestConnectPool tcp = new TestConnectPool();
        tcp.testPoolLoop(times, hikariDataSource, druidDataSource, c3p0DataSource);

    }

    /**
     *
     * 三种数据源在单线程或者线程数不多的情况下性能差不多
     *
     * 线程数如果是在成千上万的情况下性能由高到低hikari>druid>c3p0
     *
     * times跟loop用来控制查询次数与线程数
     *
     *
     */

    private void testPoolLoop (int times, HikariDataSource hikariDataSource, DruidDataSource druidDataSource, ComboPooledDataSource c3p0DataSource) {
        TestDAO testDAO = new TestDAO();
        long start = System.currentTimeMillis();
        int loop = 10000;
        // hikariDataSource 测试
        for (int j = 0; j < loop; j++) {
            int finalJ = j;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < times; i++) {
                            testDAO.query(hikariDataSource.getConnection());
                        }
                        if (finalJ == loop - 1) {
                            long end = System.currentTimeMillis();
                            long usedTime = (end - start) / 1000;
                            System.out.println("hikariDataSource==============>" + usedTime);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        // druidDataSource测试
        for (int j = 0; j < loop; j++) {
            int finalJ = j;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < times; i++) {
                            testDAO.query(druidDataSource.getConnection());
                        }
                        if (finalJ == loop - 1) {
                            long end = System.currentTimeMillis();
                            long usedTime = (end - start) / 1000;
                            System.out.println("druidDataSource==============>" + usedTime);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        // c3p0DataSource 测试
        for (int j = 0; j < loop; j++) {
            int finalJ = j;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < times; i++) {
                            testDAO.query(c3p0DataSource.getConnection());
                        }
                        if (finalJ == loop - 1) {
                            long end = System.currentTimeMillis();
                            long usedTime = (end - start) / 1000;
                            System.out.println("c3p0DataSource==============>" + usedTime);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }

    /**
     * 获取c3p0数据源
     *
     * @throws PropertyVetoException
     */
    public static ComboPooledDataSource getC3p0DataSource() {
        // 设置参数
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(driver);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(jdbcUrl);
        cpds.setUser(user);
        cpds.setPassword(passwd);
        cpds.setInitialPoolSize(initialSize);
        cpds.setMinPoolSize(minPoolSize);
        cpds.setMaxPoolSize(maxPoolSize);
        cpds.setMaxIdleTime(maxIdleTime);
        cpds.setAcquireRetryAttempts(retryAttempts);
        cpds.setAcquireIncrement(acquireIncrement);
        cpds.setTestConnectionOnCheckin(false);
        cpds.setTestConnectionOnCheckout(false);
        return cpds;
    }

    /**
     * 获取Druid数据源
     *
     * @return
     */
    public static DruidDataSource getDruidDataSource() {
        DruidDataSource dds = new DruidDataSource();
        dds.setUsername(user);
        dds.setUrl(jdbcUrl);
        dds.setPassword(passwd);
        dds.setDriverClassName(driver);
        dds.setInitialSize(initialSize);
        dds.setMaxActive(maxPoolSize);
        dds.setMaxWait(maxIdleTime);
        dds.setTestWhileIdle(false);
        dds.setTestOnReturn(false);
        dds.setTestOnBorrow(false);
        return dds;
    }

    /**
     * 获取tomcat jdbc数据源
     *
     * @return
     */
    public static DataSource getTomcatDataSource() {
        DataSource ds = new DataSource();
        ds.setUrl(jdbcUrl);
        ds.setUsername(user);
        ds.setPassword(passwd);
        ds.setDriverClassName(driver);
        ds.setInitialSize(initialSize);
        ds.setMaxIdle(minPoolSize);
        ds.setMaxActive(maxPoolSize);
        ds.setTestWhileIdle(false);
        ds.setTestOnBorrow(false);
        ds.setTestOnConnect(false);
        ds.setTestOnReturn(false);
        return ds;
    }

    public static HikariDataSource getHikariDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(user);
        dataSource.setPassword(passwd);
        dataSource.setDriverClassName(driver);
        dataSource.setConnectionTimeout(connectionTimeout);
        dataSource.setIdleTimeout(maxIdleTime);
        dataSource.setMaxLifetime(connectionTimeout);
        dataSource.setMaximumPoolSize(maxPoolSize);
        dataSource.setMinimumIdle(minPoolSize);
        dataSource.setReadOnly(false);
        return dataSource;
    }


}
