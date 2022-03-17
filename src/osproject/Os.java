package osproject;


import java.util.*;


public class Os {

    //Shared list of integer which is represented by an Array list of integers
    static ArrayList<Integer> sharedList = new ArrayList<>();


    //All list that stores all the process created
    ArrayList<ProcessStructure> jobList  = new ArrayList<>();

    //1,2,3,4,5,6,7,8,9
    //Process A - 2,7 - Adding
    //Process B- 2,5 - Copying (lock:5,2)
    //A list that will hold all the 5 processes in a FCFS order.
    ArrayList<ProcessStructure> activeProcesses = new ArrayList<>();
    //A list that stores all the highest priorities process from the active process list
    ArrayList<ProcessStructure> priorityQueue = new ArrayList<>();
    //A list that stores all the integer accessed by a process, these integers
    //accessed are considered locked integers
    static ArrayList<Integer> lockedInteger = new ArrayList<>();
    //Store the order in which all the process are accessed
    ArrayList<Integer> accessedSequence = new ArrayList<>();

    ArrayList<ProcessStructure> terminatedProcesses = new ArrayList<>();


    //Start of everything
    public void beginSimulation() {
        Scanner scan = new Scanner(System.in);
        //initializing the number of processes and starting position to 0
        int processesToBeSimulated = 0;
        int startingPositionInList = 0;
        int numTries = 0;

        //While loop which attempts to read the user input on how much process is to be simulated
        //if an integer is not entered. An error is thrown, num of tries is decrease and the user is prompted
        //again for the number of process to be simulated until num of tries reach 0
        while (true) {


            System.out.println("Please enter the number of processes to be simulated (10-30)");
            try {
                processesToBeSimulated = scan.nextInt();
                if (processesToBeSimulated < 10 || processesToBeSimulated > 30) {
                    throw new Exception();
                }
                numTries = 3;
                break;
            } catch (Exception e) {

                if (--numTries == 0) {
                    System.out.println("Maximum amount of tries reached.Please try again later");
                    break;
                }
            }
            System.out.println("Invalid input, Please try again");
            scan.nextLine();
        }

        //While loop which attempts to read the user input on the starting position of the list
        //if an integer is not entered. An error is thrown, num of tries is decrease and the user is prompted
        //again for the starting position of the list until num of tries reach 0
        while (true) {


            System.out.println("Please enter the starting position in the list of integers (0-9)");
            try {
                startingPositionInList = scan.nextInt();
                if (startingPositionInList < 0 || startingPositionInList > 9) {
                    throw new Exception();
                }
                numTries = 3;
                break;
            } catch (Exception e) {

                if (--numTries == 0) {
                    System.out.println("Maximum amount of tries reached.Please try again later");
                    break;
                }
            }
            System.out.println("Invalid input, Please try again");
            scan.nextLine();
        }
        //A function that will populate the shared list with 10 random numbers.
        populateSharedList();



    //A loop that initializes certain amount of new process structures depending on the user input
    //of processes to be simulated. After creating a process.
    //the process is then added to the job list
        for (int i = 1; i <= processesToBeSimulated; i++) {
            ProcessStructure processStructure = new ProcessStructure();
            jobList.add(processStructure);

        }

        //A loop that check if the joblist size is not 0
        while (jobList.size() != 0) {

            //if the joblist is not 0, a next loop will check if the active process list size
            // is not equal to 5. While it's not equal to 5, it will remove the first process
            //out of the joblist and add it to the active process list
            while ( activeProcesses.size() != 5) {
                ProcessStructure element = jobList.remove(0);
                activeProcesses.add(element);
            }

            //create a variable called currentPriority and initialize it to 1
            int currentPriority = 1;

            //Check if the active process list is not empty
            while (activeProcesses.size() >= 1) {

                //if the currentPriority is greater than 3 than set the priority back to 1
                if(currentPriority==4) currentPriority=1;


                //A loop that gets the all the process that has the priority number as the current priority
                //being worked with. If the any element like that are found then it is remove
                //from the active process list and added to the priorityQueue list
                for (int i = 0; i < activeProcesses.size(); i++) {
                    if (activeProcesses.get(i).getTask().getPriority() == currentPriority) {
                        priorityQueue.add(activeProcesses.remove(i));
                        --i;
                    }

                }

                // set the current position of the list into a variable called counter
                int counter = startingPositionInList;



                //Check if the priority queue size is not equal to 0
                while (priorityQueue.size() != 0) {

                    //If the list is not equal to 0 , for each process in the priority queue
                    //it will check if that process requires the current location to complete it task.
                    //if the does require the location. the integer will be "locked" (placed in the lock list) and the process
                    //attempt to lock will be increase to indicate an attempt was made.
                    //if the process does not require it, it will skip the element.
                    //if the process requires it but the location is locked. An attempt will be made on the process
                    for (ProcessStructure element : priorityQueue) {


                        if ((element.getTask().firstLocation==counter) ||(element.getTask().secondLocation==counter) && lockedInteger.contains(counter)) {
                            element.setAttempts();
                        }
                        if ((element.getTask().firstLocation==counter) ||(element.getTask().secondLocation==counter) && !lockedInteger.contains(counter)) {
                            lockedInteger.add(counter);
                            accessedSequence.add(counter);
                            System.out.println("locked:"+lockedInteger);
                            System.out.println("element:"+element.getTask().firstLocation);
                            System.out.println("element:"+element.getTask().secondLocation);
                            System.out.println("counter:"+counter);
                            System.out.println("---------------");
                            element.setAttempts();
                            element.addToData(counter);
                            element.getTask().getIndexRequiredList().remove((Integer) counter);
                            break;

                        }


                    }

                    //increase the counter
                    counter++;

                    //if the counter is 10 then set the counter back to 0
                    if (counter == 10) {
                        counter = 0;
                    }

                    //for each element in the priority queue check if the endtime is not null
                    //if the endtime is not null, this indicates the process has been terminated.
                    //The terminated process will be remove from the active process list and added to the terminated process list
                    for (int i = 0; i < priorityQueue.size(); i++) {
                        if (priorityQueue.get(i).getEndTime() != null) {
                            increasePriority(activeProcesses);
                            currentPriority=0;
                            terminatedProcesses.add(priorityQueue.remove(i));
                          --i;
                        }
                    }

                }
                //increase the current priority by one
                currentPriority++;

            }
        }

        System.out.println("Sequence in which records are accessed:");
        for (int i = 0; i <accessedSequence.size() ; i++) {
            System.out.print(accessedSequence.get(i));
            if(i==accessedSequence.size()-1){
                System.out.print(".");
            }else{
                System.out.print(",");
            }
        }
        System.out.println("\n");
        System.out.println("-----------------------------------------------------------------");

        System.out.println("PID \t\t TaskID \t\t StartTime \t\t\t EndTime \t\t Attempts \t\t Priority At Termination");
        for (ProcessStructure process:terminatedProcesses) {
            System.out.println(process.getPID()+"\t\t"+process.getTask().getTaskID()+"\t\t\t  "+process.getStartTime()+"\t\t\t "+process.getEndTime()+"\t\t\t"+process.getAttempts()+"\t\t\t\t\t"+process.getTask().getPriority());

        }


    }





    //populate the shared-list with 10 random number
    private void populateSharedList(){

        for (int i = 0; i <10; i++) {
            sharedList.add(generateRandomNumber());


        }

    }
    //function to generate random number between 1 and 3000
    private int generateRandomNumber(){
        return (int) Math.floor(Math.random()*(3000-1+1)+1);

    }

    //increase the priority of all process in the list by 1
    private void increasePriority(ArrayList<ProcessStructure> processes) {
        for (ProcessStructure process : processes) {
            if (process.getTask().getPriority() != 1) {
                process.getTask().setPriority(process.getTask().getPriority() - 1);
            }

        }
    }



    }
