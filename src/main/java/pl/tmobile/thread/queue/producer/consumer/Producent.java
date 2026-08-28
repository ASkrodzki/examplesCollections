package pl.tmobile.thread.queue.producer.consumer;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producent implements Runnable {

    private Path folder;
    private BlockingQueue<Path> blockingQueue;
    private final AtomicInteger activeProducer;


    public Producent(Path folder, BlockingQueue<Path> blockingQueue, AtomicInteger activeProducer) {
        this.folder = folder;
        this.blockingQueue = blockingQueue;
        this.activeProducer = activeProducer;
    }

    @Override
    public void run() {
        try {
            findJavaFiles(folder.toFile());
        } finally {
            activeProducer.decrementAndGet();
        }
       /* try {
            Files.walk(folder)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(path -> {
                        try {
                            blockingQueue.put(path);
                            System.out.println("Producer found: " + path);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        */
    }

    private void findJavaFiles(File directory) {
        File[] files = directory.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                findJavaFiles(file);
            } else if (file.isFile() && file.getName().endsWith(".java")) {
                try {
                    blockingQueue.put(file.toPath());
                    System.out.println("Producer found: " + file.toPath());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return; // przerwanie pętli w przpyadku gdy
                    // wątek dostaje interrupt
                }
            }
        }
    }

    public AtomicInteger getActiveProducer() {
        return activeProducer;
    }

    public Path getFolder() {
        return folder;
    }

    public void setFolder(Path folder) {
        this.folder = folder;
    }

    public BlockingQueue<Path> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(BlockingQueue<Path> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
}
