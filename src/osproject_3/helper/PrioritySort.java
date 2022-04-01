package osproject_3.helper;

import osproject_3.processmanagement.Process;

import java.util.Comparator;

public class PrioritySort implements Comparator<Process>
{
    public int compare(Process process1, Process process2)
    {
        return process1.getPriority()-process2.getPriority();
    }
}
