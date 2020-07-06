package ict.plan.core.datasource;

import ict.plan.core.cache.RequestCacheDataItem;
import ict.plan.core.util.DBConnStrUtil;

public class DataSourceContextHolder {
    //设置数据源
    public static void setDataSource(Integer key) {
        RequestCacheDataItem.DB_ID.set(key);
    }

    //获取数据源
    public static Integer getDataSource() {
        Integer dbID = RequestCacheDataItem.DB_ID.get();
        if (dbID !=null) {
            return dbID;
        }
        // 如果没有设置数据源
        Integer companyID = RequestCacheDataItem.COMPANY_ID.get();
        if (companyID != null) {
            try {
                DBConnStrUtil.setDataSourceByCompanyID(companyID);
                dbID = RequestCacheDataItem.DB_ID.get();// 重新获取
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dbID;
    }
}
