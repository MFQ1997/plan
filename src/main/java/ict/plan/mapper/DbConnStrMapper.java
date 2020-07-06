package ict.plan.mapper;

import ict.plan.entity.po.DbConnString;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DbConnStrMapper {
    List<DbConnString> getDbConnStrings();

    DbConnString getDbConnStringByDbID(@Param("dbID") int dbID);
}
