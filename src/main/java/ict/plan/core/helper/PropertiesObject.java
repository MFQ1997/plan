package ict.plan.core.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class PropertiesObject implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("加载PropertiesObject类");
    }

    @Value("${plan.manage.db}")
    private Integer dbID;

    @Value("${plan.manage.driver}")
    private String driverName;

    @Value("${plan.manage.url}")
    private String url;

    @Value("${plan.manage.user}")
    private String user;

    @Value("${plan.manage.password}")
    private String password;

    public Integer getDbID() {
        return dbID;
    }

    public void setDbID(Integer dbID) {
        this.dbID = dbID;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
