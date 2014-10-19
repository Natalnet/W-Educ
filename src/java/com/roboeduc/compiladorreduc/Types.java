package com.roboeduc.compiladorreduc;

import java.io.Serializable;

/**
 *
 * @author carlafernandes
 */
public class Types implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String name;
    
    private String declareString;
    
    private String declareBoolean;
    
    private String declareFloat;
    
    private String declareTrue;
    
    private String declareFalse;
    
    public Types() {
    }

    public Types(String declareString, String declareBoolean, String declareFloat,
            String declareTrue, String declareFalse) {
        this.declareString = declareString;
        this.declareBoolean = declareBoolean;
        this.declareFloat = declareFloat;
        this.declareTrue = declareTrue;
        this.declareFalse = declareFalse;
    }

    public String getDeclareTrue() {
        return declareTrue;
    }

    public void setDeclareTrue(String declareTrue) {
        this.declareTrue = declareTrue;
    }

    public String getDeclareFalse() {
        return declareFalse;
    }

    public void setDeclareFalse(String declareFalse) {
        this.declareFalse = declareFalse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeclareString() {
        return declareString;
    }

    public void setDeclareString(String declareString) {
        this.declareString = declareString;
    }

    public String getDeclareBoolean() {
        return declareBoolean;
    }

    public void setDeclareBoolean(String declareBoolean) {
        this.declareBoolean = declareBoolean;
    }

    public String getDeclareFloat() {
        return declareFloat;
    }

    public void setDeclareFloat(String declareFloat) {
        this.declareFloat = declareFloat;
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
        if (!(object instanceof Types)) {
            return false;
        }
        Types other = (Types) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.entity.Types[ id=" + id + " ]";
    }
    
}
