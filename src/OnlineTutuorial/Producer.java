package OnlineTutuorial;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

class Producer implements Runnable {
    private List<String> buffer;
    private ReentrantLock bufferLock;

    public Producer(List<String> buffer, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.bufferLock = bufferLock;
    }


    @Override
    public void run() {
        String numbers[] = {"1", "2", "3"};
        for (String number : numbers) {
            bufferLock.lock();
            try {
                buffer.add(number);
            } finally {
                bufferLock.unlock();
            }

            try {
                Random random = new Random();
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted.");
            }
            System.out.println(Thread.currentThread().getName() + " added " + number);
        }
        System.out.println(Thread.currentThread().getName() + " added " + Main.EOF);
        bufferLock.lock();
        try {
            buffer.add(Main.EOF);
        } finally {
            bufferLock.unlock();
        }


    }
}