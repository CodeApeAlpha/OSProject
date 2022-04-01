package osproject_2;

import osproject_2.helpers.arrivalTimeSort;
import osproject_2.helpers.prioritySort;

import java.sql.Time;
import java.util.*;

public class PriorityNonPreemptiveScheduling {

    private ArrayList<Process> listOfProcess=new ArrayList<>();
    private  Queue readyQueue=  new LinkedList<Process>();

//  Generate a list of Random processes
    private void generateRandomTask(){
        for(int x=0; x<20;x++){
            listOfProcess.add(new Process(generateRandomNumber(),generateRandomTaskID(),generateRandomPriority(),generateRandomArrival(),generateRandomBurstTime()));
        }
    }
    private int generateRandomTaskID(){return (int)Math.floor(Math.random()*(4-1+1)+1);}
    private int generateRandomPriority(){return (int)Math.floor(Math.random()*(5-1+1)+1);}
    private int generateRandomArrival(){return (int)Math.floor(Math.random()*(10));}
    private int generateRandomBurstTime(){return (int)Math.floor(Math.random()*(10)+1);}
    private int generateRandomNumber(){
        return (int)Math.floor(Math.random()*Integer.MAX_VALUE);
    }


//  Populate the ready Queue after sorting
    public Queue<Process> readyQueueLoader(){
        generateRandomTask();
        listOfProcess.sort(new prioritySort());
        listOfProcess.sort(new arrivalTimeSort());
        for(Process process:listOfProcess){
            readyQueue.add(process);
        }
        return readyQueue;
    }
    public void viewReadyQueue()
    {
        for(Process pro : listOfProcess)
        {
            System.out.println("\t"+pro.getpID()+"\t\t"+pro.getTaskID()+"\t\t\t"+pro.getArrivalTime()+"\t\t\t"+pro.getPriority()+"\t\t\t"+pro.getBurstTime());
        }
    }

}
