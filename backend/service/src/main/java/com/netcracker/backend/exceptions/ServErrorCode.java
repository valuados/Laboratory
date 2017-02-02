package com.netcracker.backend.exceptions;

/**
 * Enuming message error on service layer.
 * @author Hromenkov Ilya
 * @version 1.0
 */
public enum ServErrorCode {

    NC_SERV_000("Cannot get all students"),
    NC_SERV_001("Cannot get all groups"),
    NC_SERV_002("Cannot delete student"),
    NC_SERV_003("Cannot save student"),
    NC_SERV_004("Cannot get student by id"),
    NC_SERV_005("Cannot get group by id"),
    NC_SERV_006("Cannot update student"),
    NC_SERV_007("Cannot find students");


    private final String value;

    private ServErrorCode(String s) {
        value = s;
    }

    public boolean equalsValue(String value2) {
        return (value2 != null) && value.equals(value2);
    }

    public String toString() {
        return value;
    }
}