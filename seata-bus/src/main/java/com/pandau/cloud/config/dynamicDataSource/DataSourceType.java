package com.pandau.cloud.config.dynamicDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceType {
    public enum DataBaseType {
        MASTER, SLAVE
    }
    // 使用ThreadLocal保证线程安全
    private static final ThreadLocal<DataBaseType> TYPE = new ThreadLocal<DataBaseType>();

    // 往当前线程里设置数据源类型
    public static void setDataBaseType(DataBaseType dataBaseType) {
        if (dataBaseType == null) {
            throw new NullPointerException();
        }
        log.info("[将当前数据源改为]：" + dataBaseType);
        TYPE.set(dataBaseType);
    }

    // 获取数据源类型
    public static DataBaseType getDataBaseType() {
        DataBaseType dataBaseType = TYPE.get() == null ? DataBaseType.MASTER : TYPE.get();
        log.info("[获取当前数据源的类型为]：" + dataBaseType);
        return dataBaseType;
    }

    // 清空数据类型
    public static void clearDataBaseType() {
        TYPE.remove();
    }
}
