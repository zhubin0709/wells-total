package com.wells.pojo;

import java.io.Serializable;

public class SysPermission implements Serializable {
    private Integer privilegeId;

    private String subject;

    private Integer subjectValue;

    private String access;

    private Integer accessValue;

    private Integer operation;

    private static final long serialVersionUID = 1L;

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Integer getSubjectValue() {
        return subjectValue;
    }

    public void setSubjectValue(Integer subjectValue) {
        this.subjectValue = subjectValue;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access == null ? null : access.trim();
    }

    public Integer getAccessValue() {
        return accessValue;
    }

    public void setAccessValue(Integer accessValue) {
        this.accessValue = accessValue;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", privilegeId=").append(privilegeId);
        sb.append(", subject=").append(subject);
        sb.append(", subjectValue=").append(subjectValue);
        sb.append(", access=").append(access);
        sb.append(", accessValue=").append(accessValue);
        sb.append(", operation=").append(operation);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}