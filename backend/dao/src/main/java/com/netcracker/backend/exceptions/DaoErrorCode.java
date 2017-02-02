package com.netcracker.backend.exceptions;

/**
 * Created by ilkh0715 on 30.10.2016.
 */
public enum DaoErrorCode {

    NC_DAO_000("Cannot get by id"),
    NC_DAO_001("Cannot get list of object"),
    NC_DAO_002("Cannot add object"),
    NC_DAO_003("Cannot update object"),
    NC_DAO_004("Cannot delete object"),
    NC_DAO_005("Cannot create hql");


    private final String value;

    private DaoErrorCode(String s) {
        value = s;
    }

    public boolean equalsValue(String value2) {
        return (value2 != null) && value.equals(value2);
    }

    public String toString() {
        return value;
    }
}
