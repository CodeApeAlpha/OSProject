import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import cpu.CPU1;
import cpu.CPU2;
import processmanagement.PriorityNonPreemptiveScheduler;
import processmanagement.Process;
import processmanagement.Task;

public class Main
{
	public static void main(String[] args) 
	{
		int taskNo = 0;
		Task task = new Task();
		PriorityNonPreemptiveScheduler scheduler = new PriorityNonPreemptiveScheduler();
		HashMap<Integer, Integer> sharedResource;
		ArrayList<Process> executedList = new ArrayList<Process>();
		Queue <Process> readyQueue =  new LinkedList<Process>();
		CPU1 cpu = new CPU1();
		CPU2 cpu2 = new CPU2();
		Queue <Process> qCPU1 =  new LinkedList<Process>();
		Queue <Process> qCPU2 =  new LinkedList<Process>();

		readyQueue = scheduler.populateReadyQueue();
		
		while (true)
		{
			System.out.println("_____________________________________________________________________________________________________________");
			System.out.println("\t\t\t\t\t\t\t\t\t\tREADY QUEUE OF PROCESSES");
			System.out.println("_____________________________________________________________________________________________________________");
			scheduler.viewReadyQueue(readyQueue);
			
			System.out.println("_____________________________________________________________________________________________________________");
			System.out.println("\t\t\t\t\t\t\t\t\tRUNNING PROCESSES");
			System.out.println("_____________________________________________________________________________________________________________");
			
			System.out.println(readyQueue.peek());
						
			if(qCPU1.isEmpty()) //if CPU1 is empty, put process there
			{
				qCPU1.add(readyQueue.peek());
				cpu.setProcess(readyQueue.peek());
				cpu.start();
				readyQueue.remove(readyQueue.peek());
				qCPU1.remove(readyQueue.peek());
			}
			else if(qCPU2.isEmpty())
			{
				qCPU2.add(readyQueue.peek());
				cpu2.setProcess(readyQueue.peek());
				cpu2.start();
				readyQueue.remove(readyQueue.peek());
				qCPU2.remove(readyQueue.peek());
			}
			else
			{
				System.out.println("No available CPU");
			}
			//cpu.getCPU1().remove(readyQueue.peek());
			readyQueue.remove(readyQueue.peek()); 
			//cpu.getCPU1().clear();
			
			if(readyQueue.isEmpty())
			{
				System.exit(0);
			}
		}
//		System.out.println("______________________________________________________________________________________________________________________");
//		System.out.println("\t\t\t\t\t\t\t\t\tEXECUTED PROCESSES");
//		System.out.println("______________________________________________________________________________________________________________________");
//		
//		for (Process pList :  executedList)
//		{
//            //System.out.println(pList);
//		}
	}
}
