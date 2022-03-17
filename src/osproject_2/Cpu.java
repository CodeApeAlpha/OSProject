package osproject_2;


import java.util.HashMap;
import java.util.Queue;


public class Cpu {

    private static HashMap<Integer,Integer> listOfSharedResources;
    private static Processor processor_1;
    private static Processor processor_2;

    private Queue<Process> readyQueue;

    public Cpu(){
        readyQueue=new PriorityNonPreemptiveScheduling().readyQueueLoader();
    }

    public  Queue<Process> getReadyQueue() {
        return readyQueue;
    }

    public static void main(String [] args){
        System.out.println(new Cpu().getReadyQueue());
    }

}
