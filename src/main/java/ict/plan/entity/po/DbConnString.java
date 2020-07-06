package ict.plan.entity.po;

/*
* @Name:DbConnString
* @Description:这个是从库数据源的对应的实体类
* @Date:2019
* */
public class DbConnString {
    //从库数据库的i编号
    private int dbID;
    //从库数据库的用户
    private String dbUser;
    //从库数据库的用户的密码
    private String dbPwd;
    //从库数据库的驱动名称
    private String dbDriverName;
    //从库数据库的url
    private String dbUrl;
    //从库数据库的状态标致
    private int flag;

    public int getDbID() {
        return dbID;
    }

    public void setDbID(int dbID) {
        this.dbID = dbID;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public String getDbDriverName() {
        return dbDriverName;
    }

    public void setDbDriverName(String dbDriverName) {
        this.dbDriverName = dbDriverName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}
