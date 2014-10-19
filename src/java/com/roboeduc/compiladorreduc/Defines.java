package com.roboeduc.compiladorreduc;
import java.io.Serializable;

/**
 *
 * @author carlafernandes and Victor Torres
 */
public class Defines implements Serializable {
    private static final long serialVersionUID = 1L;
   
    private Long id;
    
    String name;
    boolean alreadyExists;
    String text;
    String type;
    
    public Defines() {
    }

    public Defines(String name, boolean alreadyExists, String text, String type) {
        this.name = name;
        this.type = type;
        this.alreadyExists = alreadyExists;
        this.text = text;
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

    public boolean isAlreadyExists() {
        return alreadyExists;
    }

    public void setAlreadyExists(boolean alreadyExists) {
        this.alreadyExists = alreadyExists;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof Defines)) {
            return false;
        }
        Defines other = (Defines) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.entity.Function[ id=" + id + " ]";
    }
    
}
