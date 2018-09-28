/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author felahi
 */
public class Rename {

    public static File reNameFile(File inputFile, File tcfFile) throws IOException {
        String oldFileName = tcfFile.getAbsolutePath();
        String newFileName = tcfFile.getAbsolutePath().replace(FilenameUtils.getBaseName(tcfFile.getName()), FilenameUtils.getBaseName(inputFile.getName()));
        Path oldPath = Paths.get(oldFileName);
        Path newPath = Paths.get(newFileName);
        Files.move(Paths.get(tcfFile.getAbsolutePath()), Paths.get(newFileName), StandardCopyOption.REPLACE_EXISTING);
        return newPath.toFile();
    }

}
