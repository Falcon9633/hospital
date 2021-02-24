package ua.com.dao.impl;

import ua.com.bean.EmployeeAssignmentBean;
import ua.com.bean.EmployeeMedicamentBean;
import ua.com.bean.EmployeeProcedureBean;
import ua.com.bean.EmployeeSurgeryBean;
import ua.com.constant.MySQLFields;

import java.sql.ResultSet;
import java.sql.SQLException;

class ObjectMapper {

    static EmployeeMedicamentBean mapEmployeeMedicamentBean (ResultSet rs) throws SQLException{
        return (EmployeeMedicamentBean) mapEmployeeAssigmentBean(rs, new EmployeeMedicamentBean());
    }

    static EmployeeSurgeryBean mapEmployeeSurgeryBean(ResultSet rs) throws SQLException{
        return (EmployeeSurgeryBean) mapEmployeeAssigmentBean(rs, new EmployeeSurgeryBean());
    }

    public static EmployeeProcedureBean mapEmployeeProcedureBean(ResultSet rs) throws SQLException {
        return (EmployeeProcedureBean) mapEmployeeAssigmentBean(rs, new EmployeeProcedureBean());
    }

    private static EmployeeAssignmentBean mapEmployeeAssigmentBean (ResultSet rs, EmployeeAssignmentBean bean) throws SQLException{
        bean.setId(rs.getLong(MySQLFields.ID));
        bean.setNameEN(rs.getString(MySQLFields.NAME_EN));
        bean.setNameUA(rs.getString(MySQLFields.NAME_UA));
        bean.setDescriptionEN(rs.getString(MySQLFields.DESCRIPTION_EN));
        bean.setDescriptionUA(rs.getString(MySQLFields.DESCRIPTION_UA));
        bean.setEnd(rs.getBoolean(MySQLFields.END));
        bean.setCreateTime(rs.getTimestamp(MySQLFields.CREATE_TIME).toLocalDateTime());
        bean.setCreatedBy(rs.getLong(MySQLFields.CREATED_BY));
        bean.setServedBy(rs.getLong(MySQLFields.SERVED_BY));
        bean.setMedicalCardId(rs.getLong(MySQLFields.MEDICAL_CARD_ID));
        bean.setPatientBirthday(rs.getDate(MySQLFields.PATIENT_BIRTHDAY).toLocalDate());
        bean.setPatientNameEN(rs.getString(MySQLFields.PATIENT_NAME_EN));
        bean.setPatientSurnameEN(rs.getString(MySQLFields.PATIENT_SURNAME_EN));
        bean.setPatientNameUA(rs.getString(MySQLFields.PATIENT_NAME_UA));
        bean.setPatientSurnameUA(rs.getString(MySQLFields.PATIENT_SURNAME_UA));
        bean.setCreatedByNameEN(rs.getString(MySQLFields.CREATED_BY_NAME_EN));
        bean.setCreatedBySurnameEN(rs.getString(MySQLFields.CREATED_BY_SURNAME_EN));
        bean.setCreatedByNameUA(rs.getString(MySQLFields.CREATED_BY_NAME_UA));
        bean.setCreatedBySurnameUA(rs.getString(MySQLFields.CREATED_BY_SURNAME_UA));
        return bean;
    }
}
