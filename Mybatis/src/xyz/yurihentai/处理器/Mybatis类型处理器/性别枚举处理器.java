package xyz.yurihentai.处理器.Mybatis类型处理器;

import xyz.yurihentai.枚举.性别枚举;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class 性别枚举处理器 implements TypeHandler<性别枚举> {

    // 插入数据库时将枚举映射为数据库类型
    @Override
    public void setParameter(PreparedStatement ps, int i, 性别枚举 性别枚举, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, 性别枚举.性别());
    }

    // 查到结果后将数据映射为枚举类型（通过列名
    @Override
    public 性别枚举 getResult(ResultSet rs, String columnName) throws SQLException {
        if (!rs.wasNull()) {
            Integer result = rs.getInt(columnName);
            if (性别枚举.男.性别().equals(result)) {
                return 性别枚举.男;
            } else if (性别枚举.女.性别().equals(result)) {
                return 性别枚举.女;
            } else {
                return null;
            }
        }
        return null;
    }

    // 查到结果后将数据映射为枚举类型（通过数组下标
    @Override
    public 性别枚举 getResult(ResultSet rs, int columnIndex) throws SQLException {
        if (!rs.wasNull()) {
            Integer result = rs.getInt(columnIndex);
            if (性别枚举.男.性别().equals(result)) {
                return 性别枚举.男;
            } else if (性别枚举.女.性别().equals(result)) {
                return 性别枚举.女;
            } else {
                return null;
            }
        }
        return null;
    }

    // 查到结果后将数据映射为枚举类型（通过数组下标
    @Override
    public 性别枚举 getResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (!cs.wasNull()) {
            Integer result = cs.getInt(columnIndex);
            if (性别枚举.男.性别().equals(result)) {
                return 性别枚举.男;
            } else if (性别枚举.女.性别().equals(result)) {
                return 性别枚举.女;
            } else {
                return null;
            }
        }
        return null;
    }

}
