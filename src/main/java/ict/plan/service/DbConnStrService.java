package ict.plan.service;

import ict.plan.entity.po.DbConnString;

import java.util.List;

public interface DbConnStrService {
    List<DbConnString> getDbConnStrings();

    DbConnString getDbConnStringByDbID(int dbID);
}
