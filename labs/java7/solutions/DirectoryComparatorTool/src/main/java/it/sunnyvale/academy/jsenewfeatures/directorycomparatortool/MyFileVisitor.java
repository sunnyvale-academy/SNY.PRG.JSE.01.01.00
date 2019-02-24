/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sunnyvale.academy.jsenewfeatures.directorycomparatortool;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author denismaggiorotto
 */
public class MyFileVisitor extends SimpleFileVisitor<Path> {

        public final List<Path> files = new ArrayList<>();

        private final Path thisPath;
        private final Path ignorePath;

        public MyFileVisitor(Path thisPath, Path ignorePath) {
            this.thisPath = thisPath;
            this.ignorePath = ignorePath;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            files.add(thisPath.relativize(file));
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            if (dir.equals(ignorePath)) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            return FileVisitResult.CONTINUE;
        }
    }
