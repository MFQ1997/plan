package ict.plan.core.helper;

import ict.plan.core.cache.RequestCacheData;
import ict.plan.core.datasource.DataSourceContextHolder;
import ict.plan.core.datasource.MultiDataSourceBean;
import ict.plan.core.util.SpringContextHolder;
import ict.plan.entity.po.DbConnString;
import ict.plan.service.DbConnStrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* @Name:DataSourceHelper
* @Description:在项目启动的时候将主库里面存放的所有的从库数据源连接信息读取出来放到线程副本里面
* @Date:2019
* */
@Component
@Order(2)
public class DataSourceHelper implements CommandLineRunner {

    @Autowired
    PropertiesObject po;

    @Override
    public void run(String... args) throws Exception {
        try{
            RequestCacheData.getInstance().setCache();
            System.out.println("加载DataSourceHelper类");
            //获取实例
            MultiDataSourceBean multiDataSource = SpringContextHolder.getBean("getDataSource", MultiDataSourceBean.class);
            //添加主库数据源
            multiDataSource.addDataSource(po.getDbID(), po.getDriverName(),
                    po.getUser(), po.getPassword(), po.getUrl());
            //设置主库数据源
            DataSourceContextHolder.setDataSource(po.getDbID());
            //获取实例
            DbConnStrService dbConnStrService = SpringContextHolder.getBean("dbConnStrServiceImpl", DbConnStrService.class);
            //获取所有数据源数据
            List<DbConnString> dbConnStrings = dbConnStrService.getDbConnStrings();
            //将读取出来的数据源全部添加到线程副本里面
            for(DbConnString db : dbConnStrings){
                //添加从库数据源
                multiDataSource.addDataSource(db.getDbID(), db.getDbDriverName(),
                        db.getDbUser(), db.getDbPwd(), db.getDbUrl());
                System.out.println("初始化的数据源有："+db.getDbUrl());
            }
            System.out.println("初始化数据源成功");
        }catch(Exception ex){
            System.out.println(ex);
            System.out.println("初始化数据源失败");
        }finally {
            RequestCacheData.getInstance().removeCache();
        }
    }
}
