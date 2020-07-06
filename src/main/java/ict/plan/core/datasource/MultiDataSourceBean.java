package ict.plan.core.datasource;

import ict.plan.core.util.LoggerUtil;
import ict.plan.core.util.SpringContextHolder;
import ict.plan.entity.po.DbConnString;
import ict.plan.service.DbConnStrService;

import javax.sql.DataSource;
import java.util.Properties;

public class MultiDataSourceBean extends AbstractRoutingDataSource {

    //初始化数据源
    private DataSource initDataSource(String userName, String password, String url, String driver){
        System.out.println("开始初始化数据源"+url);
        Properties properties = new Properties();
        properties.setProperty("driverClassName", driver);
        properties.setProperty("url", url);
        properties.setProperty("username", userName);
        properties.setProperty("password", password);

        //优化参数配置
        properties.setProperty("initialSize","5");
        properties.setProperty("minIdle", "5");
        properties.setProperty("maxIdle", "15");
        properties.setProperty("maxActive", "15");
        properties.setProperty("testOnBorrow", "false");
        properties.setProperty("testOnReturn", "false");
        properties.setProperty("testWhileIdle", "true");
        properties.setProperty("validationQuery", "SELECT 1");
        properties.setProperty("timeBetweenEvictionRunsMillis", "360000");
        properties.setProperty("numTestsPerEvictionRun", "15");
        properties.setProperty("minEvictableIdleTimeMillis", "1800000");

        DataSource dataSource = null;
        try {
            dataSource = org.apache.commons.dbcp.BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            LoggerUtil.error("数据源创建失败！ driverName="+driver, e);
        }
        return dataSource;
    }

    //添加数据源
    public void addDataSource(Integer dbID, String driverName, String userID, String password, String url)throws  Exception{
        synchronized (objHelper) {
            try {
                //获取用户名称、密码、连接数据库的url、驱动的名字用来初始化数据源
                DataSource dataSource = initDataSource(userID, password, url, driverName);
                super.addDataSource(dbID, dataSource);
            } catch (Exception e) {
                LoggerUtil.error("加载数据源失败，dbID="+dbID, e);
            }
        }
    }

    //添加数据源
    public void addDataSource(Integer dbID)throws  Exception{
        synchronized (objHelper) {
            try {
                DbConnStrService dbConnStrService = SpringContextHolder.getBean("dbConnStrService", DbConnStrService.class);
                DbConnString dbConnStr = dbConnStrService.getDbConnStringByDbID(dbID);
                //获取用户名称、密码、连接数据库的url、驱动的名字用来初始化数据源
                DataSource dataSource = initDataSource(dbConnStr.getDbUser(), dbConnStr.getDbPwd(),
                        dbConnStr.getDbUrl(), dbConnStr.getDbDriverName());
                super.addDataSource(dbID, dataSource);
            } catch (Exception e) {
                LoggerUtil.error("加载数据源失败，dbID="+dbID, e);
            }
        }
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Integer dataSourceType = DataSourceContextHolder.getDataSource();
        return dataSourceType;
    }
}