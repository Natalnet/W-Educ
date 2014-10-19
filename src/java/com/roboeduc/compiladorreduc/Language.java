/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboeduc.compiladorreduc;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author carlafernandes
 */
public class Language implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String name;
    
    private String robot;
    
    private String description;
    
    private String footnote;
    
    private String header;
    
    private String mainFunction;
    
    private String otherFunctions;
    
    private String callFunction;
    
    private String compileCode;
    
    private String sendCode;
    
    private String compilerFile;
    
    private String sentExtension;
    
    private String extension;
    
    private Types types;
    
    private Operators operators;
    
    private List<LFunction> functions;
    
    private ControlFlow controlFlow;

    public String getRobot() {
        return robot;
    }

    public void setRobot(String robot) {
        this.robot = robot;
    }

    public String getSentExtension() {
        return sentExtension;
    }

    public void setSentExtension(String sentExtension) {
        this.sentExtension = sentExtension;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getCallFunction() {
        return callFunction;
    }

    public void setCallFunction(String callFunction) {
        this.callFunction = callFunction;
    }

    public String getCompileCode() {
        return compileCode;
    }

    public void setCompileCode(String compileCode) {
        this.compileCode = compileCode;
    }

    public String getSendCode() {
        return sendCode;
    }

    public void setSendCode(String sendCode) {
        this.sendCode = sendCode;
    }

    public String getCompilerFile() {
        return compilerFile;
    }

    public void setCompilerFile(String compilerFile) {
        this.compilerFile = compilerFile;
    }

    public String getFootnote() {
        return footnote;
    }

    public void setFootnote(String footnote) {
        this.footnote = footnote;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMainFunction() {
        return mainFunction;
    }

    public void setMainFunction(String mainFunction) {
        this.mainFunction = mainFunction;
    }

    public String getOtherFunctions() {
        return otherFunctions;
    }

    public void setOtherFunctions(String otherFunctions) {
        this.otherFunctions = otherFunctions;
    }

    public ControlFlow getControlFlow() {
        return controlFlow;
    }

    public void setControlFlow(ControlFlow controlFlow) {
        this.controlFlow = controlFlow;
    }

    public List<LFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(List<LFunction> functions) {
        this.functions = functions;
    }

    public Operators getOperators() {
        return operators;
    }

    public void setOperators(Operators operators) {
        this.operators = operators;
    }

    public Types getTypes() {
        return types;
    }

    public void setTypes(Types types) {
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Language() {
    }

    public Language(String name, String robot, String description) {
        this.name = name;
        this.robot = robot;
        this.description  = description;
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
        if (!(object instanceof Language)) {
            return false;
        }
        Language other = (Language) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.entity.Language[ id=" + id + " ]";
    }
    
}
