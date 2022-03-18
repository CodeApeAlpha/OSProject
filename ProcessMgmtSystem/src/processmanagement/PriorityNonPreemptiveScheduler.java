package processmanagement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import helper.ArrivalTimeSort;
import helper.PrioritySort;

public class PriorityNonPreemptiveScheduler 
{
	private ArrayList<Process> processList;
	private Queue readyQueue =  new LinkedList<Process>();
	
	public Queue<Process> populateReadyQueue()
	{
		Process process = new Process();
		Task task = new Task();

		processList = process.getProcesses();
		
		processList.sort(new PrioritySort());
        processList.sort(new ArrivalTimeSort());

        for(Process pro : processList)
        {
            readyQueue.add(pro);
        }
        return readyQueue;
	}
	
	public void viewReadyQueue(Queue<Process> readyQueue)
	{
		for (Process process : readyQueue) 
		{    
			System.out.println(process);  
		}   
	}
	public void removeFromQueue(Queue<Process> readyQueue, int pid)
	{
		readyQueue.remove(pid);
		for (Process process : readyQueue) 
		{    
			System.out.println(process);  
		}  
	}
}
