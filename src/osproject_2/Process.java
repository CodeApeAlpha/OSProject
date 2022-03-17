package osproject_2;

public class Process {


    private long pID;
    private int taskID;
    private int priority;
    private int arrivalTime;
    private int burstTime;


    private String startTime;
    private String endTime;
    private String blockedTime;


    public Process() {

    }

    public Process(long pID, int taskID, int priority, int arrivalTime, int burstTime) {
        this.pID = pID;
        this.taskID = taskID;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    public long getpID() {
        return pID;
    }

    public void setpID(long pID) {
        this.pID = pID;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBlockedTime() {
        return blockedTime;
    }

    public void setBlockedTime(String blockedTime) {
        this.blockedTime = blockedTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "pID=" + pID +
                ", taskID=" + taskID +
                ", arrivalTime=" + arrivalTime +
                ", priority=" + priority +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", blockedTime='" + blockedTime + '\'' +
                ", burstTime='" + burstTime + '\'' +
                '}';
    }
}
