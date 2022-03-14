package osproject;

import java.util.ArrayList;

public class Task {
    private int taskID;
    private int priority;
    private ArrayList<Integer> sharedList;
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
        firstLocation=getRandomNumber();

        if(taskID==3){
            indexRequiredList.add(firstLocation);
        }else{

            secondLocation=getRandomNumber();
            while(secondLocation==firstLocation){
                secondLocation = getRandomNumber();
            }
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
