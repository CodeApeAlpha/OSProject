package osproject_3.processmanagement;


import java.time.*;
import java.util.ArrayList;
import java.util.Random;

public class Process
{
	ZoneId zone = ZoneId.of("America/Jamaica");  //get time zone

    private int pid;
    private Task task;
    private int taskNum;
	private int priority;
	private int arrivalTime;
	private LocalTime startTime;
	private int burstTime;
	private int blockedTime;
	private LocalTime endTime;
  
	public Process() 
	{ 
		pid = 0;
		task = new Task();
		taskNum = 0;
		priority = 0;
		arrivalTime = 0;
		startTime = LocalTime.now(zone); 
		endTime = LocalTime.now(zone);
		burstTime = 0;
		blockedTime = 0;
    }
    public Process(int pid, int taskNum, int priority, int arrivalTime, LocalTime startTime,int burstTime, int blockedTime, LocalTime endTime) 
    {
		this.pid = pid;
		this.taskNum = taskNum;
		this.priority = priority;
		this.arrivalTime = arrivalTime;
		this.startTime = startTime;
		this.burstTime = burstTime;
		this.blockedTime = blockedTime;
		this.endTime = endTime;
	}
    public Process(Process process) 
    {
		this.pid = process.pid;
		this.taskNum = process.taskNum;
		this.priority = process.priority;
		this.arrivalTime = process.arrivalTime;
		this.startTime = process.startTime;
		this.burstTime = process.burstTime;
		this.blockedTime = process.blockedTime;
		this.endTime = process.endTime;
	}
    
    //Getters & Setters
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public int getBurstTime() {
		return burstTime;
	}
	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}
	public int getBlockedTime() {
		return blockedTime;
	}
	public void setBlockedTime(int blockedTime) {
		this.blockedTime = blockedTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public int getTaskNum() {
		return taskNum;
	}
	public void setTaskNum(int taskNum) {
		this.taskNum = taskNum;
	}
	@Override
    public String toString() 
	{
        return pid + " \t\t" + taskNum + " \t\t" + arrivalTime +" \t\t\t"+ priority +" \t\t" + burstTime;
    }
	public ArrayList<Process> getProcesses()
	{
		int  pID;
		ArrayList<Process> processList = new ArrayList<Process>();
		ArrayList<Integer> pidArray = new ArrayList<Integer>();
		Random rand = new Random();
		
		for(int i = 0; i<150; i++) //generates 20 unique PIDs and stores them a arraylist
		{	
			pID = rand.nextInt((20 - 1) + 1) + 1;
			if (!pidArray.contains(pID)) //check if PID taken
			{
		        pidArray.add(pID);
		    }
		}
		for(int j = 0; j<20; j++)
		{	
			pid = pidArray.get(j);
			priority = rand.nextInt((5 - 1) + 1) + 1;
			arrivalTime = rand.nextInt(29 + 1);
			burstTime = rand.nextInt((5 - 1) + 1) + 1;
			startTime = null;
			endTime = null;
			blockedTime = 0;
			taskNum = task.generateTaskNumber();
			
			processList.add(new Process(pid, taskNum, priority, arrivalTime, startTime, burstTime, blockedTime, endTime));
		}
		return processList;
	}
	public LocalTime updateTime()
	{
		LocalTime time = LocalTime.now(zone);
		return time;
	}
}
