package cpu;

import java.util.LinkedList;
import java.util.Queue;

import processmanagement.Process;
import processmanagement.Task;

public class CPU2 extends Thread
{
	Process process = new Process();
	private Queue CPU2 = new LinkedList<Process>();
	
	public CPU2()
	{
		//process = getProcess(process);
	}
	public Queue getCPU2() {
		return CPU2;
	}
	public void setCPU2(Queue cPU1) {
		CPU2 = cPU1;
	}
	
	public void setProcess(Process process)
	{
		this.process = process;
	}
	public Process getProcess()
	{
		return process;
	}
	
	public void run()
	{  
		boolean exit = false;
		//do
		//{
			System.out.println("Running process " +  process.getPid() + " on CPU1" );
			assignToCPU();	
			currentThread().stop();
			CPU2.remove(process);
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
			try
			{
				int burstTime = process.getBurstTime();
				burstTime = burstTime * 1000;
				CPU2.add(process);
				taskNum = process.getTaskNum();
				task.runTask(taskNum);
				Thread.sleep(0);
				process.setStartTime(process.updateTime());
			}
			catch (InterruptedException e) 
			{
		         System.out.println("Process " +  process.getPid() + "has been interrupted.");
		     }
	        System.out.println("Process " + process.getPid() + " exiting....");
			process.setEndTime(process.updateTime());
			exit = true;
	//	}while(exit !=true);	
		return CPU2;
	}
}
