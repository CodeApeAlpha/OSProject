package osproject_2.helpers;

import osproject_2.Process;

import java.util.Comparator;

public class arrivalTimeSort implements Comparator<Process> {

    public int compare(Process process_1, Process process_2){
        return process_1.getArrivalTime()-process_2.getArrivalTime();
    }
}
