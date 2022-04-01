package osproject_2;



import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

// This Class is a Demo Class
public class Processor  {
//  Read Write Lock
    private ReadWriteLock lock= new ReentrantReadWriteLock();

    Lock writeLock = lock.writeLock();
    Lock readLock=lock.readLock();

    private  ZoneId zone = ZoneId.of("America/Jamaica");  //get time zone
    private LocalTime time;

    public Processor(){

    }

    public void coreOne(Process process){


        time = LocalTime.now(zone);
        process.setStartTime(time.toString());
        System.out.println("Core One Processing PID: "+process.getpID());

        try {
            Thread.sleep(process.getBurstTime()*1000l);
            run(process);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time = LocalTime.now(zone);
        process.setEndTime(time.toString());
        System.out.println("Core One Exiting PID: "+process.getpID()+"...");

    }
    public void coreTwo(Process process){
        time = LocalTime.now(zone);
        process.setStartTime(time.toString());
        System.out.println("Core Two Processing PID: "+process.getpID());

        try {
            Thread.sleep(process.getBurstTime()*1000l);
            run(process);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time = LocalTime.now(zone);
        process.setEndTime(time.toString());
        System.out.println("Core Two Exiting PID: "+process.getpID()+".....");

    }


    public void run(Process process){
        if(process.getTaskID()==1){
           if(!writeLock.tryLock()){
                Instant start = Instant.now();
                System.out.println("Process "+process.getpID()+" In Waiting");
                writeLock.lock();
                Instant finish = Instant.now();
                process.setBlockedTime(String.valueOf(Duration.between(start, finish).toNanos()));
            }
            try {
                System.out.println("PID: "+process.getpID()+" Shared Resources locked");
                addRecord();
                System.out.println("PID: "+process.getpID()+" Unlocked");
            }finally {
                writeLock.unlock();
            }
        }
        else if(process.getTaskID()==2){
            if(!writeLock.tryLock()){
                Instant start = Instant.now();
                System.out.println("Process "+process.getpID()+" In Waiting");
                writeLock.lock();
                Instant finish = Instant.now();
                process.setBlockedTime(String.valueOf(Duration.between(start, finish).toNanos()));
            }
            try {
                System.out.println("PID: "+process.getpID()+" Shared Resources locked");
                removeRecord();
                System.out.println("PID: "+process.getpID()+" Unlocked");
            }finally {
                writeLock.unlock();
            }
        }
        else if(process.getTaskID()==3){
            readLock.lock();
            try{
                calculateRecordDataTotal();
            }finally {
                readLock.unlock();
            }
        }
        else if (process.getTaskID()==4) {
            readLock.lock();
            try {
                retrieveRecord();
            }finally {
                readLock.unlock();
            }
        }
        Cpu.listOfProcess.add(process);
    }

//  These operations should lock the resources
    public synchronized void addRecord() {
//      Randomly generates a resource record and updates that record -  id (1-20) and data value (1-100)
        int resource = (int)Math.floor(Math.random()*(20) + 1);
        int value =  (int)Math.floor(Math.random()*(100-1) + 1);
        if( Cpu.listOfSharedResources.containsKey(resource))
        {
            Cpu.listOfSharedResources.replace(resource,value);
        }
        System.out.println("addRecord [R:"+resource+" V:"+value+"]");
    }
    public synchronized void removeRecord(){
//      Randomly generates a record id (1-20) and sets that records data value to 0
        int resource = (int)Math.floor(Math.random()*(20) + 1);
        if( Cpu.listOfSharedResources.containsKey(resource))
        {
            Cpu.listOfSharedResources.replace(resource,0);
        }
        System.out.println("removeRecord [R:"+resource+"]");
    }
    public void calculateRecordDataTotal(){
//      Calculate Record Data Total: Sum and display all the data values in the resource list.
        int sum = 0;
        //Sum and display all the data values in the resource list
        for (int rMap :  Cpu.listOfSharedResources.keySet())
        {
            sum = sum +  Cpu.listOfSharedResources.get(rMap);
        }
        System.out.println("calculateRecordDataTotal sum "+sum);
    }
    public void retrieveRecord(){
//      Randomly generates a record id then reads and outputs the data value for that id
        int resource = (int)Math.floor(Math.random()*(20) + 1);
        if( Cpu.listOfSharedResources.containsKey(resource)) {
            System.out.println("retrieveRecord Resource: " +resource+ "\t\tValue: "+Cpu.listOfSharedResources.get(resource));
        }
    }

}
