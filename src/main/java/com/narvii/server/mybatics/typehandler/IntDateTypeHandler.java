package com.narvii.server.mybatics.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@MappedJdbcTypes({JdbcType.INTEGER})
@MappedTypes({Date.class})
public class IntDateTypeHandler extends BaseTypeHandler<Date> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, (int) (parameter.getTime() / 1000));
    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new Date(1000L * rs.getInt(columnName));
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new Date(1000L * rs.getInt(columnIndex));
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new Date(1000L * cs.getInt(columnIndex));
    }
}
