package OnlineTutuorial;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();

        ReentrantLock bufferLock = new ReentrantLock();

        Thread producerThread = new Thread(new Producer(buffer, bufferLock));
        producerThread.setName("producerThread");

        Thread consumerThread1 = new Thread(new Consumer(buffer, bufferLock));
        consumerThread1.setName("consumerThread1");

        Thread consumerThread2 = new Thread(new Consumer(buffer, bufferLock));
        consumerThread2.setName("consumerThread2");

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}