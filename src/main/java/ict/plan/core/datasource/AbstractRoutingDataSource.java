package ict.plan.core.datasource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractRoutingDataSource extends AbstractDataSource implements InitializingBean {
    //对象锁，防止并发新增数据源
    public static Object objHelper = new Object();

    //存储所有数据源
    protected Map<Object, DataSource> resolvedDataSources ;

    @Override
    public Connection getConnection() throws SQLException {
        return determineTargetDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return determineTargetDataSource().getConnection(username, password);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        this.resolvedDataSources = new ConcurrentHashMap<Object, DataSource>();
    }

    public boolean isContainDataSource(Integer dbID) {
        synchronized (objHelper) {
            return this.resolvedDataSources.containsKey(dbID);
        }
    }

    protected boolean addDataSource(Integer dbID, DataSource ds){
        if(!this.resolvedDataSources.containsKey(dbID)&& !ObjectUtils.isEmpty(ds)){
            this.resolvedDataSources.put(dbID, ds);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 从缓存获取数据源
     * @throws SQLException
     */
    protected DataSource determineTargetDataSource() throws SQLException {
        Assert.notNull(this.resolvedDataSources, "DataSource router not initialized");
        Object lookupKey = determineCurrentLookupKey();
        DataSource dataSource = this.resolvedDataSources.get(lookupKey);

        if (dataSource == null) {
            throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
        }

        return dataSource;

    }

    /**
     * Determine the current lookup key. This will typically be
     * implemented to check a thread-bound transaction context.
     * <p>Allows for arbitrary keys. The returned key needs
     * to match the stored lookup key type, as resolved by the
     * {@link #resolveSpecifiedLookupKey} method.
     */
    protected abstract Object determineCurrentLookupKey();

}