package processmanagement;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Task 
{
	private int taskNum;
	private HashMap<Integer, Integer> sharedResource;
	Random rand;

    public Task()
    {
    	taskNum = 0;
    	sharedResource = new HashMap<Integer, Integer>();
    	rand = new Random();
    }
    public Task(int taskNum, HashMap<Integer, Integer> sharedResource) 
	{
		this.taskNum = taskNum;
		this.sharedResource = sharedResource;
	}

	public int generateTaskNumber()
    {
    	Random rand=new Random();
		taskNum = rand.nextInt((4 - 1) + 1) + 1;

		return taskNum;
    }
    public void initializeResourceList()
    {
    	int i;
    	for(i = 1; i<=20; i++)
    	{
    		sharedResource.put(i, 0);
    	}
    }

    public void addRecord()
	{
    	//randomly generates a resource record and updates that record -  id (1-20) and data value (1-100)
		int resource = rand.nextInt((20 - 1) + 1) + 1;
		int value = rand.nextInt(100 + 1);

		if(sharedResource.containsKey(resource))
		{
			sharedResource.replace(resource,value);
			System.out.println("Resouce ID: " +resource+ " data value successfully updated to "  + sharedResource.get(resource));
		}
	}
	public void removeRecord()
	{
		//randomly generates a record id (1-20) and sets that records data value to 0
		int resource = rand.nextInt((20 - 1) + 1) + 1;
		
		if(sharedResource.containsKey(resource))
		{
			sharedResource.remove(resource);
			System.out.println("Resouce ID: " +resource+ " was removed successfully");
		}
	}
	public void retrieveRecord()
	{
		//randomly generates a record id then reads and outputs the data value for that id
		int resource = rand.nextInt((20 - 1) + 1) + 1;
		
		if(sharedResource.containsKey(resource))
		{
			System.out.println("Resouce: " +resource+ "\t\tValue: "  + sharedResource.get(resource));
		}
	}
	public void calculateRecordDataTotal() 
	{
		int sum = 0;
		
		//Sum and display all the data values in the resource list
		for (int rMap : sharedResource.keySet()) 
		{
			 sum = sum + sharedResource.get(rMap);
		}
		System.out.println("Sum of data values in resource list: " + sum);
	}

	public void viewSharedResourceList()
	{
		for (int rMap : sharedResource.keySet()) 
		{
			 System.out.println("Resource Id: " + rMap + "\t\tData Value: " + sharedResource.get(rMap));
		}
	}
 	public void runTask(int taskNum)
	{
		switch (taskNum) 
		{
			case 1: 
				System.out.println("\nAdding Record to Shared Resource List");
				addRecord();
//				System.out.println("Shared Resource List After Updating Record");
//				viewSharedResourceList();
				break;
			case 2: 
				System.out.println("\nRemoving Record from Shared Resource List");
				removeRecord();
//				System.out.println("Shared Resource List After Removing Record");
//				viewSharedResourceList();
				break;
			case 3: 
				System.out.println("\nRetrieving Record from Shared Resource List");
				retrieveRecord();
				break;
			case 4:
				System.out.println("\nCalculating Shared Resource List Data Values");
				calculateRecordDataTotal();
				break;
			default:
				System.out.println("No such task exist!");
		}
	}
}
