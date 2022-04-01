package osproject_2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;



public class Cpu {

    public static HashMap<Integer,Integer> listOfSharedResources=new HashMap<Integer,Integer>();
    private PriorityNonPreemptiveScheduling priorityNonPreemptiveScheduling=new PriorityNonPreemptiveScheduling();
    private Queue<Process> readyQueue;
    public static ArrayList<Process> listOfProcess= new ArrayList<Process>();
    final private Processor runner=new Processor();

    public Cpu(){
//      Get list of processes
        readyQueue=priorityNonPreemptiveScheduling.readyQueueLoader();
//      Set the list shared resources to initial state
        initializeResourceList();
    }

    public void viewReadyQueue(Queue<osproject_3.processmanagement.Process> processList){
        for(osproject_3.processmanagement.Process pro : processList)
        {
            System.out.println("\t"+pro.getPid()+"\t\t"+pro.getTaskNum()+"\t\t\t"+pro.getArrivalTime()+"\t\t\t"+pro.getPriority()+"\t\t\t"+pro.getBurstTime());
        }
    }

    public void run(){
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\t\t\t\tREADY QUEUE OF PROCESSES");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("--------------------------------------------------------------------\n");
        System.out.print("\t PID\t\t| Task\t| Arrival Time\t| Priority\t| Burst Time\n");
        System.out.print("--------------------------------------------------------------------\n");
        priorityNonPreemptiveScheduling.viewReadyQueue();
        Thread t1;
        Thread t2;

        t1=new Thread(new Runnable() {
            @Override
            public void run() {
                while (readyQueue.peek()!=null){
                    runner.coreOne(readyQueue.remove());
                }
            }
        });
        t2=new Thread(new Runnable() {
            @Override
            public void run() {
                while (readyQueue.peek()!=null){
                    runner.coreTwo(readyQueue.remove());
                }
            }
        });
        t2.start();
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n");
        System.out.print("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.print("\t PID\t\t| Task\t| Arrival Time\t| Priority\t| Burst Time |\t Start Time\t\t\t|\t\tEnd Time\t\t| Blocked Time\n ");
        System.out.print("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        for(Process pro:listOfProcess){
            System.out.println("\t"+pro.getpID()+"\t\t"+pro.getTaskID()+"\t\t\t"+pro.getArrivalTime()+"\t\t\t"+pro.getPriority()+"\t\t\t"+pro.getBurstTime()+"\t\t\t"+pro.getStartTime()+"\t\t\t"+pro.getEndTime()+"\t\t\t"+pro.getBlockedTime());
        }
}

    private   Queue<Process> getReadyQueue() {
        return readyQueue;
    }
    private void initializeResourceList(){
        int i;
        for(i = 1; i<=20; i++)
        {
            listOfSharedResources.put(i, 0);
        }
    }
    public static void main(String [] args)  {
        new Cpu().run();
    }

}
