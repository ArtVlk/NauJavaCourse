package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileSyncTask implements Task {
    private final Path sourceDir;
    private final Path targetDir;
    private ScheduledExecutorService scheduler;
    private volatile boolean running;

    public FileSyncTask(){
        this.sourceDir = Paths.get("default/source/dir");
        this.targetDir = Paths.get("default/target/dir");
        this.running = false;
    }

    public FileSyncTask(Path sourceDir, Path targetDir) {
        this.sourceDir = sourceDir;
        this.targetDir = targetDir;
        this.running = false;
    }

    @Override
    public void start() {
        if (running){
            System.out.println("Задача уже запущена.");
            return;
        }
        running = true;
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::syncFiles, 0, 5, TimeUnit.SECONDS);
        System.out.println("Задача запущена.");
    }

    @Override
    public void stop() {
        if (!running){
            System.out.println("Задача не запущена.");
            return;
        }
        running = false;
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(10, TimeUnit.SECONDS)){
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e){
            scheduler.shutdownNow();
        }
        System.out.println("Задача остановлена.");
    }

    private void syncFiles() {
        try {
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path relativePath = sourceDir.relativize(file);
                    Path targetFile = targetDir.resolve(relativePath);
                    Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Синхронизированный файл: " + targetFile);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path relativePath = sourceDir.relativize(dir);
                    Path targetSubDir = targetDir.resolve(relativePath);
                    if (!Files.exists(targetSubDir)) {
                        Files.createDirectory(targetSubDir);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        System.out.println('\n' + "№5");
        Path sourceDir = Paths.get("D:\\test1");
        Path targetDir = Paths.get("D:\\test2");
        FileSyncTask task = new FileSyncTask(sourceDir, targetDir);
        task.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task.stop();
    }
}

