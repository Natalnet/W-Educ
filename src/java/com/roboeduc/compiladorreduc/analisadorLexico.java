/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboeduc.compiladorreduc;

import java.io.File;     
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 
 *
 * @author carlafernandes
 */
public class analisadorLexico{
    private class fileListStruct {
        public int line;
        public String name;
    }
    private List<fileListStruct> fileList = new ArrayList();

    private String usedStructures= "";
        
    public analisadorLexico() {
    }
    
    public void readFile(String fileName) {
        try {
//            try (Scanner input = new Scanner(new File(fileName))) {
                Scanner input = new Scanner(new File(fileName));
                Scanner inputLine = new Scanner(new File(fileName));
                int cLine = 0;
                int cWord = 0;
                while (inputLine.hasNext()) {
                    readLine(inputLine.nextLine(), cLine);
                    cLine++;
                }

                while(input.hasNext()) { 
                    String word = input.next();
                    fileList.get(cWord).name = word;
                    cWord++;
                    
                }
                
//                Escrever linha a linha
//                for (int i = 0; i < fileList.size(); i++) {
//                    System.out.println("Dado: linha: " + fileList.get(i).line + " nome: " + fileList.get(i).name);
//                }
                
                lookForSpaces();
//            }
        } catch (IOException e) {
        }
//        Escrever nome a nome
//        for (int i = 0; i< fileList.size(); i++) {
//            System.out.println("ta: " + fileList.get(i).name);
//        }
    }
    
    private void readLine(String name, int line) {
        //System.out.println("reasline: " + name + " line: " + line);
        int i = 0;
        boolean add = false;
        if (name.substring(i, i).equals(" ")) {
            while (name.substring(i, i+1).equals(" ")) {
                i++;
            }
            //coloquei agora
            i++;
        }
        while (i < name.length()) {
            while (!name.substring(i, i+1).equals(" ")) {
                i++;
                add = true;
                if (i == name.length()) {
                    break;
                }
            }
            if(add == true){
            fileListStruct auxStruct = new fileListStruct();
            auxStruct.line = line;
            auxStruct.name = " ";
            fileList.add(auxStruct);
            add = false;
            }
            if (i < name.length()) {
                while (name.substring(i, i+1).equals(" ")) {
                    i++;
                    if (i == name.length()) {
                        break;
                    }
                }
            }
        }
        
    }
    
    private void lookForSpaces() {
        for (int i = 0; i < fileList.size(); i++) {
            if (fileList.get(i).name.length() == 1) {}
            else if (fileList.get(i).name.contains(")") || fileList.get(i).name.contains("(") ||
                    fileList.get(i).name.contains("{") || fileList.get(i).name.contains("}") ||
                    fileList.get(i).name.contains("<") || fileList.get(i).name.contains(">") ||
                    fileList.get(i).name.contains("=") || fileList.get(i).name.contains(",") ||
                    fileList.get(i).name.contains("\"") || fileList.get(i).name.contains(":") || 
                    fileList.get(i).name.contains("!") || fileList.get(i).name.contains("+") || 
                    fileList.get(i).name.contains("-") || fileList.get(i).name.contains("*") || 
                    fileList.get(i).name.contains("/")) {
                int index = fileList.get(i).name.indexOf(")");
                if (index == -1) {
                    index = fileList.get(i).name.indexOf("(");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf("{");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf("}");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf("<");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf(">");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf("!");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf("=");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf("(");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf(",");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf("\"");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf(":");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf("+");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf("-");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf("*");
                }
                if (index == -1) {
                    index = fileList.get(i).name.indexOf("/");
                }
                String firstName;
                if (index != 0 ) {
                    firstName = fileList.get(i).name.substring(0, index);
                    fileList.get(i).name = fileList.get(i).name.substring(index);
                }
                else {
                    firstName = fileList.get(i).name.substring(0, index+1);
                    fileList.get(i).name = fileList.get(i).name.substring(index+1);
                }
                fileListStruct auxStruct = new fileListStruct();
                auxStruct.line = fileList.get(i).line;
                auxStruct.name = firstName;
                fileList.add(i, auxStruct);
                i--;
            }
        }
    }

    public void defineUsedStructures(analisadorSintatico sintatico) {
        if (fileListContains("texto") || fileListContains("numero") || fileListContains("logico")) {
            usedStructures = usedStructures+"1";
        }
        for (int i = 0; i < fileList.size(); i++) {
            if (sintatico.getMapeamento().getFunctionsVoid().contains(fileList.get(i)) ||
                sintatico.getMapeamento().getFunctionsNumber().contains(fileList.get(i)) || 
                sintatico.getMapeamento().getFunctionsName().contains(fileList.get(i)) ) {
                usedStructures = usedStructures+"2";
                break;
            }
        }
        if (fileListContains("tarefa")) {
            usedStructures = usedStructures+"3";
        }
        if (fileListContains("se") || fileListContains("enquanto") || fileListContains("farei")) {
            usedStructures = usedStructures+"4";
        }
        if (fileListContains("se") || fileListContains("teste")) {
            usedStructures = usedStructures+"5";
        }
        if (fileListContains("repita") || fileListContains("enquanto") || fileListContains("farei") || fileListContains("para")) {
            usedStructures = usedStructures+"6";
        }
        usedStructures=usedStructures+"78";
    }

    private boolean fileListContains (String text) {
        for (int i = 0; i < fileList.size(); i++) {
            if (fileList.get(i).name.equals(text)) {
                return true;
            }
        }
        return false;
    }

    public int getFileListLine(int index) {
        return fileList.get(index).line;
    }
    public String getFileListName(int index) {
        return fileList.get(index).name;
    }
    public List<fileListStruct> getFileList() {
        return fileList;
    }

    public boolean hasText(String name) {
        return fileList.contains(name);
    }
    
    public String getUsedStructures() {
        return usedStructures;
    }
    
}
