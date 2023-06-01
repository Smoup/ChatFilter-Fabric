package org.smod;

import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Scanner;

public class ModConfig {
    private static File configDirectory;
    private static File configFile;
    public static void loadConfig() {
        configDirectory = new File(MinecraftClient.getInstance().runDirectory.getPath() + "/mods/config");
        configFile = new File(configDirectory + "/ChatFilter.cfg");
        if (!configDirectory.exists()) {
            createDirectory();
        }

        if (!configFile.exists()) {
            createFile();
        }

        MsgHandler.setBadWords(new HashSet<>());

        try {
            Scanner scanner = new Scanner(configFile);

            while (scanner.hasNextLine()) {
                MsgHandler.getBadWords().add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void createDirectory() {
        try {
            Files.createDirectories(configDirectory.toPath());
        } catch (IOException ignored) {

        }
    }

    private static void createFile() {
        try {
            PrintWriter w = new PrintWriter(configFile, StandardCharsets.UTF_8);
            w.print("SMod");
            w.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}