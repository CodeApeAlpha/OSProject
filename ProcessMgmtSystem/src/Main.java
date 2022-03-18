import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import processmanagement.PriorityNonPreemptiveScheduler;
import processmanagement.Process;
import processmanagement.Task;

public class Main 
{
	public static void main(String[] args) 
	{
		int taskNo = 0;
		Process process = new Process();
		Task task = new Task();
		PriorityNonPreemptiveScheduler scheduler = new PriorityNonPreemptiveScheduler();
		ArrayList<Process> processList = new ArrayList<Process>();
		ArrayList<Process> executedList = new ArrayList<Process>();
		Queue <Process> readyQueue =  new LinkedList<Process>();
		
		task.initializeResourceList();
		readyQueue = scheduler.populateReadyQueue();
		
		while (true)
		{
			System.out.println("__________________________________________________________________________________________________________");
			System.out.println("\t\t\t\t\t\t\t\t\t\tREADY QUEUE OF PROCESSES");
			System.out.println("__________________________________________________________________________________________________________");
			scheduler.viewReadyQueue(readyQueue);
			
			System.out.println("_____________________________________________________________________________________________________________");
			System.out.println("\t\t\t\t\t\t\t\t\tRUNNING PROCESSES");
			System.out.println("_____________________________________________________________________________________________________________");
			
			readyQueue.peek().setStartTime(process.updateTime());//update start time of process running
			System.out.println(readyQueue.peek());
			taskNo = readyQueue.peek().getTaskNum();
			task.runTask(taskNo); 
			readyQueue.peek().setEndTime(process.updateTime());//update end time of process running
			executedList.add(readyQueue.peek());
			
			//remove from ready queue
			readyQueue.remove(readyQueue.peek()); 
			
			if(readyQueue.isEmpty())
			{
				break;
			}
		}
		System.out.println("______________________________________________________________________________________________________________________");
		System.out.println("\t\t\t\t\t\t\t\t\tEXECUTED PROCESSES");
		System.out.println("______________________________________________________________________________________________________________________");
		
		for (Process pList :  executedList)
		{
            System.out.println(pList);
		}
	}
}
