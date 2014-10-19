package com.roboeduc.compiladorreduc;

import java.io.Serializable;

/**
 *
 * @author carlafernandes
 */
public class ControlFlow implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String languageName;
    
    private String ifCode;
    
    private String whileCode;
    
    private String switchCode;
    
    private String repeatCode;
    
    private String forCode;
    
    private String breakCode;
    
    private String doCode;
    
    public ControlFlow() {
    }

    public ControlFlow(String languageName, String ifCode, String whileCode, String repeatCode,
            String switchCode, String forCode, String doCode, String breakCode) {
        this.ifCode = ifCode;
        this.switchCode = switchCode;
        this.repeatCode = repeatCode;
        this.whileCode = whileCode;
        this.breakCode = breakCode;
        this.forCode = forCode;
        this.doCode = doCode;
        this.languageName = languageName;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getIfCode() {
        return ifCode;
    }

    public void setIfCode(String ifCode) {
        this.ifCode = ifCode;
    }

    public String getWhileCode() {
        return whileCode;
    }

    public void setWhileCode(String whileCode) {
        this.whileCode = whileCode;
    }

    public String getSwitchCode() {
        return switchCode;
    }

    public void setSwitchCode(String switchCode) {
        this.switchCode = switchCode;
    }

    public String getRepeatCode() {
        return repeatCode;
    }

    public void setRepeatCode(String repeatCode) {
        this.repeatCode = repeatCode;
    }

    public String getForCode() {
        return forCode;
    }

    public void setForCode(String forCode) {
        this.forCode = forCode;
    }

    public String getBreakCode() {
        return breakCode;
    }

    public void setBreakCode(String breakCode) {
        this.breakCode = breakCode;
    }

    public String getDoCode() {
        return doCode;
    }

    public void setDoCode(String doCode) {
        this.doCode = doCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof ControlFlow)) {
            return false;
        }
        ControlFlow other = (ControlFlow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.entity.ControlFlow[ id=" + id + " ]";
    }
    
}
