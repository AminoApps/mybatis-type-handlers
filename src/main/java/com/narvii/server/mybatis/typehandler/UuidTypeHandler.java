package com.narvii.server.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@MappedJdbcTypes(JdbcType.BINARY)
@MappedTypes({ UUID.class })
public class UuidTypeHandler extends BaseTypeHandler<UUID> {

    InputStream getInputStream(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return new ByteArrayInputStream(bb.array());
    }

    UUID getUuid(InputStream inputStream) throws IOException {
        byte[] data = new byte[16];
        int length = inputStream.read(data, 0, 16);
        assert length == 16;
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        Long high = byteBuffer.getLong();
        Long low = byteBuffer.getLong();
        return new UUID(high, low);
    }

    UUID getUuid(byte[] data) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        Long high = byteBuffer.getLong();
        Long low = byteBuffer.getLong();
        return new UUID(high, low);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
        ps.setBinaryStream(i, getInputStream(parameter));
    }

    @Override
    public UUID getNullableResult(ResultSet rs, String columnName) throws SQLException {
        try {
            return getUuid(rs.getBinaryStream(columnName));
        } catch (IOException exp) {
            throw new SQLException(exp);
        }
    }

    @Override
    public UUID getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        try {
            return getUuid(rs.getBinaryStream(columnIndex));
        } catch (IOException exp) {
            throw new SQLException(exp);
        }
    }

    @Override
    public UUID getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getUuid(cs.getBytes(columnIndex));
    }
}
