package com.roboeduc.compiladorreduc;
import java.io.Serializable;

/**
 *
 * @author carlafernandes and Victor Torres
 */
public class LFunction implements Serializable {
    private static final long serialVersionUID = 1L;
   
    private Long id;
    
    private String name;
    
    private String type;
    
    private String returnType;
    
    private String qntParameters;
    
    private String code;
    
//    @Column(nullable=false)
    private String description;
    
    private String typeAliases;
    private String imageURL;
    
    public LFunction() {
    }

    public LFunction(String name, String type, String returnType, String qntParameters, String code, String typeAliases, String imageURL) {
        this.name = name;
        this.type = type;
        this.returnType = returnType;
        this.qntParameters = qntParameters;
        this.code = code;
        this.typeAliases = typeAliases;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getQntParameters() {
        return qntParameters;
    }

    public void setQntParameters(String qntParameters) {
        this.qntParameters = qntParameters;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setTypeAliases(String it) {
        this.typeAliases = it;
    }
    
    public String getTypeAliases() {
        return typeAliases;
    }
    
    public void setImageURL(String it) {
        this.imageURL = it;
    }
    
    public String getImageURL() {
        return imageURL;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String it) {
        this.description = it;
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
        if (!(object instanceof LFunction)) {
            return false;
        }
        LFunction other = (LFunction) object;
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
