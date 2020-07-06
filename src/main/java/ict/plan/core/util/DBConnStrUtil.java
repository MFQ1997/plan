package ict.plan.core.util;

import ict.plan.core.cache.RequestCacheDataItem;
import ict.plan.core.datasource.DataSourceContextHolder;
import ict.plan.core.datasource.MultiDataSourceBean;

public class DBConnStrUtil {

    /**
     * 根据数据库id设置数据源
     */
    public static void setDataSourceByDbID(Integer dbID) throws Exception{
        try{
            MultiDataSourceBean multiSource = SpringContextHolder.getBean("getDataSource", MultiDataSourceBean.class);
            if(!multiSource.isContainDataSource(dbID)){
                multiSource.addDataSource(dbID);
            }
        }catch(Exception ex){
            //throw new Exception("dbID初始化数据源有误", "011");
        }
        if(!"0".equals(dbID)){
            DataSourceContextHolder.setDataSource(dbID);
        } else {
            //throw new Exception("根据dbID设置数据源有误", "012");
        }
    }

    /**
     * 根据id切换数据源
     */
    public static void setDataSourceByCompanyID(Integer companyID) throws Exception{
        RequestCacheDataItem.COMPANY_ID.set(companyID);
        int dbID = -1;
        /*
         *这里调用逻辑处理通过公司id获取当前数据源id，设置数据源
         */
        if(companyID == 1) dbID=1;
        setDataSourceByDbID(dbID);
    }
}