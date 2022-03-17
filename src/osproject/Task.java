package osproject;

import java.util.ArrayList;



// shared list each processor have access to
// [9,8,7,6,5,4,3,2,1]

// the process "A" wants location 5,9 to complete its task

// the process "B" wants location 3,7 to complete its task



public class Task {

//  Individual Process Task
    private int taskID;

//  Is reduced after other processes run takes is called afterburner
    private int priority;

//  Shared list containing resources, used to copy list of resources
    private ArrayList<Integer> sharedList;

//  indexRequiredList that holds the index location
//  inorder to complete its task
    private ArrayList<Integer> indexRequiredList = new ArrayList<>();
    
    int firstLocation=0;
    int secondLocation=-1;


    public Task(int taskID,ArrayList<Integer> sharedList) {
        this.taskID = taskID;
        this.sharedList = sharedList;
        this.priority = taskID;
        getIndexes();

    }

    private void Adding(){
        int firstLocationValue = sharedList.get(firstLocation);
        int secondLocationValue = sharedList.get(secondLocation);
        int result = firstLocationValue+secondLocationValue;
        sharedList.set(secondLocation,result);
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("The first location(%s) value:%s\n",firstLocation,firstLocationValue);
        System.out.printf("The second location(%s) value:%s\n",secondLocation,secondLocationValue);
        System.out.printf("result = %s\n",result);
        System.out.println("-----------------------------------------------------------------");

    }
    private void Copying(){

        int firstLocationValue = sharedList.get(firstLocation);
        int secondLocationValue = sharedList.get(secondLocation);
        sharedList.set(secondLocation,firstLocationValue);
        int secondLocationValueUpdated = sharedList.get(secondLocation);
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("The previous value of index %s was %s\n",secondLocation,secondLocationValue);
        System.out.printf("The updated value of index %s is %s\n",secondLocation,secondLocationValueUpdated);
        System.out.println("-----------------------------------------------------------------");

    }
    private void Display(){
        int indexToDisplay = firstLocation;
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("Displaying index %s: %s\n",indexToDisplay,sharedList.get(indexToDisplay));
        System.out.println("-----------------------------------------------------------------");

    }
    private int getRandomNumber(){
       return (int) Math.floor(Math.random()*(9-0+1)+0);
    }


    private void getIndexes(){
//      Get random number between 0-9
        firstLocation=getRandomNumber();
//      Checking if the task is a display task
//      As every other process requires two location
        if(taskID==3){
//          Assign a random number to the indexRequiredList that holds the index location
//          inorder to complete its task
            indexRequiredList.add(firstLocation);
        }else{
//          Get random number between 0-9
            secondLocation=getRandomNumber();
//          Validation check
            while(secondLocation==firstLocation){
                secondLocation = getRandomNumber();
            }
//          Assign a random number to the indexRequiredList that holds the index location
//          inorder to complete its task
            indexRequiredList.add(firstLocation);
            indexRequiredList.add(secondLocation);
        }

    }

    public int getTaskID() {
        return taskID;
    }
    public void runTask (){
      switch (taskID) {
            case 1 -> Adding();
            case 2 -> Copying();
            case 3 -> Display();
           default -> throw new IllegalStateException("Unexpected value: " + taskID);
       }

    }

    public ArrayList<Integer> getIndexRequiredList() {
        return indexRequiredList;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
