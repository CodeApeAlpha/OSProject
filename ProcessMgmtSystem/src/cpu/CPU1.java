package cpu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import processmanagement.PriorityNonPreemptiveScheduler;
import processmanagement.Process;
import processmanagement.Task;

public class CPU1 extends Thread
{
	Process process = new Process();
	private Queue CPU1 = new LinkedList<Process>();
	
	public CPU1()
	{
		//process = getProcess(process);
	}
	public Queue getCPU1() {
		return CPU1;
	}
	public void setCPU1(Queue cPU1) {
		CPU1 = cPU1;
	}
	
	public void setProcess(Process process)
	{
		this.process = process;
	}
	public Process getProcess()
	{
		//System.out.println(process.getPid());
		return process;
	}
	
	public void run()
	{  
		boolean exit = false;
		//do
		//{
			System.out.println("Running process " +  process.getPid() + " on CPU1" );
			int taskNum = 0;
			Task task = new Task();
//			do
//			{
				//try
				//{
					int burstTime = process.getBurstTime();
					burstTime = burstTime * 1000;
					taskNum = process.getTaskNum();
					task.runTask(taskNum);
					//Thread.sleep(0);
					process.setStartTime(process.updateTime());
			//	}
//				catch (InterruptedException e) 
//				{
//			         System.out.println("Process " +  process.getPid() + "has been interrupted.");
//			     }
		        System.out.println("Process " + process.getPid() + " exiting....");
				process.setEndTime(process.updateTime());
				//exit = true;
			//assignToCPU();	
			currentThread().stop();
			//CPU1.remove(process);
			//CPU1.clear();
	//	}while(exit !=true);

		//assignToCPU(process);
		//Thread thread = new Thread();
		//System.out.println( thread.getState());
	}
	public Queue assignToCPU()
	{
		boolean exit = false;
		int taskNum = 0;
		Task task = new Task();
//		do
//		{
			//try
			//{
				int burstTime = process.getBurstTime();
				burstTime = burstTime * 1000;
				CPU1.add(process);
				taskNum = process.getTaskNum();
				task.runTask(taskNum);
				//Thread.sleep(0);
				process.setStartTime(process.updateTime());
		//	}
//			catch (InterruptedException e) 
//			{
//		         System.out.println("Process " +  process.getPid() + "has been interrupted.");
//		     }
	        System.out.println("Process " + process.getPid() + " exiting....");
			process.setEndTime(process.updateTime());
			exit = true;
	//	}while(exit !=true);	
		return CPU1;
	}
}
