package osproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class ProcessStructure {

    private int PID;
    private Task task;
    private ArrayList<Integer> data = new ArrayList<>();
    private String startTime=null;
    private String endTime=null;
    private int attempts;

    public ProcessStructure() {
        this.task = new Task(generateRandomNumberForTask(),Os.sharedList);
        this.PID = generatePID();
        setStartTime();


    }
    //generate a random task number
    //1 indicates adding task
    //2 indicates copying task
    //3 indicates display task
    private int generateRandomNumberForTask(){
        return (int) Math.floor(Math.random()*(3-1+1)+1);

    }
    //generate a random number to use as the PID between 1 and the Integer max value
    private int generatePID(){
        return (int) Math.floor(Math.random()*(Integer.MAX_VALUE-1+1)+1);

    }
    //Set the start time of the process using Datetime formatter and localDateTime
    private void setStartTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.startTime = dtf.format(now);


    }

    public int getPID() {
        return PID;
    }

    public Task getTask() {
        return task;
    }

    //add the location accessed from the sharedList.
    //if all the data needed is accessed it will execute its task then terminate
    public void addToData(int index){
        data.add(index);
        if(data.size()==1 && task.getTaskID()==3){
            task.runTask();
            terminateTask();
            return;

        }
        if(data.size()==2 && (task.getTaskID()==1 || task.getTaskID()==2)){
            task.runTask();
            terminateTask();
        }


    }

    //set the end time of the process which indicates the process has been terminated
    public void terminateTask(){

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            this.endTime = dtf.format(now);
           Thread.sleep(1000);


            for (int i = 0; i < data.size() ; i++) {
                Os.lockedInteger.remove(data.get(i));

            }
        }catch (Exception e){
            System.out.println(e);
        }


    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public ArrayList<Integer> getData() {
        return data;
    }
    public void setAttempts(){
        attempts++;
    }

    public int getAttempts() {
        return attempts;
    }
}
