package osproject_3.cpu;

import osproject_3.processmanagement.Process;
import osproject_3.processmanagement.Task;

public class CPU2 extends Thread
{
	int threadNum;
	Process process = new Process();
	
	public CPU2()
	{
		threadNum = 0;
	}
	public CPU2(int threadNum)
	{
		this.threadNum = threadNum;
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

			System.out.println("Running process " +  process.getPid() + " on CPU2" );
			Task task = new Task();
			int taskNum = 0;
			try
			{
				int burstTime = process.getBurstTime();
				burstTime = burstTime * 1000;
				taskNum = process.getTaskNum();
				task.runTask(taskNum, process);

				process.setStartTime(process.updateTime());
				Thread.sleep(burstTime);
				process.setEndTime(process.updateTime());
			}
			catch (InterruptedException e) 
			{
		         System.out.println("Process " +  process.getPid() + "has been interrupted.");
		     }
			System.out.println("Process " + process.getPid() + " exiting....");
		}
}
