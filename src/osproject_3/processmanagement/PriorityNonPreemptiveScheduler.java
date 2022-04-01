package osproject_3.processmanagement;
import osproject_3.helper.ArrivalTimeSort;
import osproject_3.helper.PrioritySort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;


public class PriorityNonPreemptiveScheduler 
{
	private ArrayList<Process> processList;
	HashMap<Integer, Integer> sharedResource;
	private Queue<Process> readyQueue =  new LinkedList<Process>();
	private Queue CPU1 = new LinkedList<Process>();
	private Queue CPU2 = new LinkedList<Process>();
	private Task task = new Task();
		
	public Queue getCPU1() {
		return CPU1;
	}
	public void setCPU1(Queue CPU1) {
		CPU1 = CPU1;
	}
	public Queue getCPU2() {
		return CPU2;
	}
	public void setCPU2(Queue CPU2) {
		CPU2 = CPU2;
	}
	public Queue<Process> populateReadyQueue()
	{
		int count = 0;
		Process process = new Process();

        do //get new processes until one has an arrival time of 0
        {
        	processList = process.getProcesses();
    		
    		processList.sort(new PrioritySort());
            processList.sort(new ArrivalTimeSort());

        	 for(Process pro : processList)
             {
       		  if(pro.getArrivalTime() == 0)
             	{
             		count++;
             		break; //if there is a process that arrives at time zero exit loop
             	}
            }
       }while(count == 0);
        
      for(Process pro : processList) //add processes to ready queue
      {
         readyQueue.add(pro);
      }
        return readyQueue;
	}
	public void viewReadyQueue(Queue<Process> processList)
	{
        for(Process pro : processList)
        {
        	System.out.println("\t"+pro.getPid()+"\t\t"+pro.getTaskNum()+"\t\t\t"+pro.getArrivalTime()+"\t\t\t"+pro.getPriority()+"\t\t\t"+pro.getBurstTime());
        }
	}
	public Queue assignToCPU1(Process process)
	{
		int taskNum = 0;
		int burstTime = process.getBurstTime();

		System.out.println("Process: " + process.getPid() + " running on CPU1");
		CPU1.add(process);
		taskNum = process.getTaskNum();
		task.runTask(taskNum, process);
		process.setStartTime(process.updateTime());
		try 
		{
			TimeUnit.SECONDS.sleep(burstTime);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		process.setEndTime(process.updateTime());
		//CPU1.remove(process);
		return CPU1;
	}
	public Queue assignToCPU2(Process process)
	{
		int taskNum = 0;
		int burstTime = process.getBurstTime();
		
		System.out.println("Process:  " + process.getPid() + " running on CPU2");
		CPU2.add(process);
		taskNum = process.getTaskNum();
		task.runTask(taskNum, process);
		process.setStartTime(process.updateTime());
		try 
		{
			TimeUnit.SECONDS.sleep(burstTime);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		process.setEndTime(process.updateTime());
		//CPU2.remove(process);
		return CPU2;
	}
}
