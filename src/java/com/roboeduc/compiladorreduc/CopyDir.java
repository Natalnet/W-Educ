/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboeduc.compiladorreduc;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
 
/**
 * This program copies a whole directory (including its sub files and
 * sub directories) to another, using the Java NIO API.
 *
 * @author www.codejava.net
 */
public class CopyDir extends SimpleFileVisitor<Path> {
    private Path sourceDir;
    private Path targetDir;
 
    public CopyDir(Path sourceDir, Path targetDir) {
        this.sourceDir = sourceDir;
        this.targetDir = targetDir;
    }
 
    @Override
    public FileVisitResult visitFile(Path file,
            BasicFileAttributes attributes) {
 
        try {
            Path targetFile = targetDir.resolve(sourceDir.relativize(file));
            Files.copy(file, targetFile);
        } catch (IOException ex) {
            System.err.println(ex);
        }
 
        return FileVisitResult.CONTINUE;
    }
 
    @Override
    public FileVisitResult preVisitDirectory(Path dir,
            BasicFileAttributes attributes) {
        try {
            Path newDir = targetDir.resolve(sourceDir.relativize(dir));
            Files.createDirectory(newDir);
        } catch (IOException ex) {
            System.err.println(ex);
        }
 
        return FileVisitResult.CONTINUE;
    }
    
    public void copy() throws IOException {
        Files.walkFileTree(this.sourceDir, this);
    }
 
    public static void main(String[] args) throws IOException {
        Path sourceDir = Paths.get(args[0]);
        Path targetDir = Paths.get(args[1]);
 
        Files.walkFileTree(sourceDir, new CopyDir(sourceDir, targetDir));
    }
}