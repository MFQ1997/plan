package ict.plan.service.Impl;

import ict.plan.entity.po.DbConnString;
import ict.plan.mapper.DbConnStrMapper;
import ict.plan.service.DbConnStrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbConnStrServiceImpl implements DbConnStrService {

    @Autowired
    private DbConnStrMapper mapper;

    @Override
    public List<DbConnString> getDbConnStrings() {
        return mapper.getDbConnStrings();
    }

    @Override
    public DbConnString getDbConnStringByDbID(int dbID) {
        return mapper.getDbConnStringByDbID(dbID);
    }
}
