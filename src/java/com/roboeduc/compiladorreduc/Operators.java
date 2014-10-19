package com.roboeduc.compiladorreduc;

import java.io.Serializable;

/**
 *
 * @author carlafernandes
 */
public class Operators implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String name;
    
    private String equalTo;
    
    private String notEqualTo;
    
    private String greaterThan;
    
    private String lessThan;
    
    private String greaterThanOrEqualTo;
    
    private String lessThanOrEqualTo;
    
    private String logicalNot;
    
    private String logicalAnd;
    
    private String logicalOr;

    public Operators() {
    }

    public Operators(String equalTo, String notEqualTo, String greaterThan,
            String lessThan, String greaterThanOrEqualTo, String lessThanOrEqualTo, String logicalNot,
            String logicalAnd, String logicalOr) {
        this.equalTo = equalTo;
        this.notEqualTo = notEqualTo;
        this.greaterThan = greaterThan;
        this.lessThan = lessThan;
        this.greaterThanOrEqualTo = greaterThanOrEqualTo;
        this.lessThanOrEqualTo = lessThanOrEqualTo;
        this.logicalNot = logicalNot;
        this.logicalAnd = logicalAnd;
        this.logicalOr = logicalOr;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEqualTo() {
        return equalTo;
    }

    public void setEqualTo(String equalTo) {
        this.equalTo = equalTo;
    }

    public String getNotEqualTo() {
        return notEqualTo;
    }

    public void setNotEqualTo(String notEqualTo) {
        this.notEqualTo = notEqualTo;
    }

    public String getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(String greaterThan) {
        this.greaterThan = greaterThan;
    }

    public String getLessThan() {
        return lessThan;
    }

    public void setLessThan(String lessThan) {
        this.lessThan = lessThan;
    }

    public String getGreaterThanOrEqualTo() {
        return greaterThanOrEqualTo;
    }

    public void setGreaterThanOrEqualTo(String greaterThanOrEqualTo) {
        this.greaterThanOrEqualTo = greaterThanOrEqualTo;
    }

    public String getLessThanOrEqualTo() {
        return lessThanOrEqualTo;
    }

    public void setLessThanOrEqualTo(String lessThanOrEqualTo) {
        this.lessThanOrEqualTo = lessThanOrEqualTo;
    }

    public String getLogicalNot() {
        return logicalNot;
    }

    public void setLogicalNot(String logicalNot) {
        this.logicalNot = logicalNot;
    }

    public String getLogicalAnd() {
        return logicalAnd;
    }

    public void setLogicalAnd(String logicalAnd) {
        this.logicalAnd = logicalAnd;
    }

    public String getLogicalOr() {
        return logicalOr;
    }

    public void setLogicalOr(String logicalOr) {
        this.logicalOr = logicalOr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operators)) {
            return false;
        }
        Operators other = (Operators) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.entity.Operators[ id=" + id + " ]";
    }
    
}
