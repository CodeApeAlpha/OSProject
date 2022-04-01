package OnlineTutuorial;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

class Consumer implements Runnable {
    private List<String> buffer;
    private ReentrantLock bufferLock;

    public Consumer(List<String> buffer, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {

        while (true) {
            bufferLock.lock();
            try {
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.get(0).equals(Main.EOF)) {
                    System.out.println(Thread.currentThread().getName() + " exiting.");
                    break;
                } else {
                    System.out.println(Thread.currentThread().getName() + " removed " + buffer.remove(0));
                    try {
                        Random random = new Random();
                        Thread.sleep(random.nextInt(2000));
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " interrupted.");
                    }
                }
            } finally {
                bufferLock.unlock();
            }
        }
    }
}