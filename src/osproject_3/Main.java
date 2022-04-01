package osproject_3;


import osproject_3.cpu.CPU1;
import osproject_3.cpu.CPU2;
import osproject_3.processmanagement.PriorityNonPreemptiveScheduler;
import osproject_3.processmanagement.Process;

import java.util.LinkedList;
import java.util.Queue;

public class Main
{
	public static void main(String[] args) 
	{
		boolean cpu1isBusy = false;
		boolean cpu2isBusy = false;

		PriorityNonPreemptiveScheduler scheduler = new PriorityNonPreemptiveScheduler();
		Queue<Process> readyQueue =  new LinkedList<Process>();
		Queue <Process> executedQueue = new LinkedList<Process>();
		
		readyQueue = scheduler.populateReadyQueue();
	
		for(int i =0; i < 10; i++)
		{
			CPU1 cpu1 = new CPU1(i);
			CPU2 cpu2 = new CPU2(i);
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\t\t\t\tREADY QUEUE OF PROCESSES");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.print("--------------------------------------------------------------------\n");
			System.out.print(" PID\t| Task\t| Arrival Time\t| Priority\t| Burst Time\n");
			System.out.print("--------------------------------------------------------------------\n");

			scheduler.viewReadyQueue(readyQueue);
			
			System.out.println("\n\n-------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\t\t\tRUNNING PROCESSES");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
			
			if(cpu1isBusy == false)
			{
				try
				{
					cpu1.setProcess(readyQueue.peek());
					cpu1.start();
					executedQueue.add(readyQueue.peek());
					readyQueue.remove(readyQueue.peek());
					
					if(cpu1.getState()==Thread.State.RUNNABLE)
					{
						cpu1isBusy = true;
					}
				}
				catch(IllegalThreadStateException e)
				{
					System.out.println("Attempting to restart dead thread");
				}
			}
			if(cpu2isBusy == false)
			{
				try
				{
					cpu2.setProcess(readyQueue.peek());
					cpu2.start();
					executedQueue.add(readyQueue.peek());
					readyQueue.remove(readyQueue.peek());
					
					if(cpu2.getState()==Thread.State.RUNNABLE)
					{
						cpu2isBusy = true;
					}
				}
				catch(IllegalThreadStateException e)
				{
					System.out.println("Attempting to restart dead thread");
				}
				if(cpu2.getState()==Thread.State.TERMINATED)
				{
					cpu2isBusy = false;
				}
			}
			if(cpu1.getState()==Thread.State.NEW)
			{
				cpu1isBusy = false;
			}
			if(cpu2.getState()==Thread.State.NEW)
			{
				cpu2isBusy = false;
			}
//			if(cpu1isBusy == true && cpu2hasProcess == true)
//			{
//				System.out.println("No available CPU");
//			}
			try 
			{
				Thread.sleep(3500);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			if(readyQueue.isEmpty())
			{
				break;
			}
		}
		Thread.yield();
		System.out.println("______________________________________________________________________________________________________________________");
		System.out.println("\t\t\t\t\t\t\t\t\tEXECUTED PROCESSES");
		System.out.println("______________________________________________________________________________________________________________________");
		System.out.print("---------------------------------------------------------------------------------------------------------------------------------------------------\n");		
		System.out.print("PID \t   |  Task\t |  Arrival Time\t |  Priority    | Burst Time  | \tStart Time\t\t    |  \t\t\tEnd Time\t\t\t   |  \tBlocked Time\n");
		System.out.print("---------------------------------------------------------------------------------------------------------------------------------------------------\n");	
		for (Process pro :  executedQueue)
		{
			 System.out.println(pro.getPid() + " \t\t" + pro.getTaskNum() + " \t\t" + pro.getArrivalTime() +" \t\t\t"+ pro.getPriority() +" \t\t" + pro.getBurstTime() +" \t\t " + pro.getStartTime() + " \t\t" + pro.getEndTime() +" \t\t\t\t" + pro.getBlockedTime());
		}
	}
}
