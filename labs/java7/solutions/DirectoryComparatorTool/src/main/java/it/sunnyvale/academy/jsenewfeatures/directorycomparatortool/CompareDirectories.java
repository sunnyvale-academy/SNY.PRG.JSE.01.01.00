/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sunnyvale.academy.jsenewfeatures.directorycomparatortool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author denismaggiorotto
 */
public class CompareDirectories {

    public static List<Path>[] compareDirectories(Path oldDir, Path newDir) throws IOException {
        MyFileVisitor oldFileWalker = new MyFileVisitor(oldDir, newDir);
        Files.walkFileTree(oldDir, oldFileWalker);
        List<Path> oldFiles = oldFileWalker.files;

        MyFileVisitor newFileWalker = new MyFileVisitor(newDir, oldDir);
        Files.walkFileTree(newDir, newFileWalker);
        List<Path> newFiles = newFileWalker.files;

        ListIterator<Path> iter = oldFiles.listIterator();
        while (iter.hasNext()) {
            Path oldFile = iter.next();
            if (newFiles.contains(oldFile)) {
                iter.remove();
                newFiles.remove(oldFile);
            }
        }
        return new List[]{oldFiles, newFiles};
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: CompareDirectories [old dir] [new dir]");
        }
        File oldDir = new File(args[0]);
        File newDir = new File(args[1]);

        if (!oldDir.exists() || !oldDir.isDirectory()) {
            System.out.println("Directory not found: " + oldDir.getPath());
        }
        if (!newDir.exists() || !newDir.isDirectory()) {
            System.out.println("Directory not found: " + newDir.getPath());
        }

        List[] results = compareDirectories(oldDir.toPath(), newDir.toPath());
        List<Path> oldFiles = results[0];
        List<Path> newFiles = results[1];
        System.out.println("Files removed:");
        for (Path file : oldFiles) {
            System.out.println(file);
        }
        System.out.println("\nFiles added:");
        for (Path file : newFiles) {
            System.out.println(file);
        }
    }

}
