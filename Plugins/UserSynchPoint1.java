// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Ingenieurbuero David Fischer AG, Switzerland.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>

import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.HttpLoadTest;
import dfischer.utils.PerformanceData;
import dfischer.utils.CookieHandler;
import dfischer.utils.HttpTestURL;

import java.util.Vector;


/**
 * Load test add-on module.
 */
public class UserSynchPoint1 extends Thread implements LoadtestPluginInterface
{
	private long vDeblockDelay = 0l;		// input parameter #1 - label "Deblock Delay per User (ms)"

	private LogVector logVector = null;		// internal log vector - use logVector.log(<String>) to write log data

	HttpLoadTest loadTest = null;				// used by deblock watchdog thread
	PerformanceData performanceData = null;		// used by deblock watchdog thread

	private volatile boolean syncPointFree = false;		// local flag per user
	private static Vector objectVector = new Vector();	// contains the references to all blocked users - objects of this class


	// ----------------------------------------------------------------------------
	// PART 1: var handler GUI definition and load test integration
	//
	// Note: automatically generated - no manual programming required for this part
	// ----------------------------------------------------------------------------


	public int getPluginType()
	{
		return GenericPluginInterface.TYPE_LOADTEST_EXEC;
	}


	public String getPluginName()
	{
		return "User Synchronization Point 1";
	}


	public String getPluginDescription()
	{
		return "This plug-in retain all active users at a synchronization point until all of the users have reached this point.\n\n" +
               "After that, the users are rereleased, by applying a configurable deblock delay which is multiplied with the no. of the actual user (0, 1, 2 ...).\n\n" +
               "This plug-in is bound to an URL call and the synchronization is made AFTER this URL call is executed.\n\n" +
               "Note: some users may be already inactive near the end of the load test. In such a case the synchronization is made with the remaining active users.";
	}


	public int getAllowedConstructScope()
	{
		return LoadtestPluginInterface.CONSTRUCT_SCOPE_LOOP;
	}


	public int getAllowedExecScope()
	{
		return LoadtestPluginInterface.EXEC_SCOPE_URL;
	}


	public int getAllowedExecOrder()
	{
		return LoadtestPluginInterface.EXEC_ORDER_AFTER;
	}


	public boolean allowMultipleUsage()
	{
		return false;
	}


	public String[] getInputParameterLabels()
	{
		String[] labels = new String[1];
		labels[0] = "Deblock Delay per User (ms)";
		return labels;
	}


	public LoadtestPluginFixedUserInputField[] getFixedUserInputFields()
	{
		LoadtestPluginFixedUserInputField[] userInputFields = new LoadtestPluginFixedUserInputField[1];
		userInputFields[0] = new LoadtestPluginFixedUserInputField("vDeblockDelay", true, "0");
		return userInputFields;
	}


	public int allowOptionalInputParameter()
	{
		return -1;		// all input parameters required
	}


	public String[] getOutputParameterLabels()
	{
		String[] labels = new String[0];
		return labels;
	}


	public int allowOptionalOutputParameter()
	{
		return -1;		// all output parameters required
	}


	public LogVector getLogVector()
	{
		return logVector;
	}



	// ----------------------------------------------------------------------------
	// PART 2: runtime behavior / plug-in functionality
	//
	// This part requires manual programming (see sample code section below)
	// ----------------------------------------------------------------------------


	/**
	 * Initialize plug-in at start of loop (new instance per loop).
	 */
	public void construct(Object context)
	{
		// LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
	}


	/**
	 * Transfer input parameter before execute() is called.
	 *
	 * input parameter #1: (long) vDeblockDelay / default value = '0' / label "Deblock Delay per User (ms)"
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				vDeblockDelay = Long.valueOf((String) parameterValue).longValue();
				break;
			default:
				break;
		}
	}


	/**
	 * Execute plug-in after URL call.
	 *
	 * Intrinsic plug-in implementation.
	 */
	public void execute(Object context)
	{
		logVector = new LogVector();
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
		loadTest = pluginContext.getHttpLoadTest();
		performanceData = pluginContext.getPerformanceData();

		// register own object, and calculate deblock delay factor (delay of virtual user after synch point is reached)
		long deblockDelayFactor = 0;
		synchronized (objectVector)
		{
			objectVector.addElement(this);
			deblockDelayFactor = (objectVector.size() - 1);

			// create deblock watchdog thread when the first user reaches the synchronization point
			if (objectVector.size() == 1)
			{
				Thread t = new Thread(this);
				t.start();
			}
		}

		// wait for sync point
		while (!loadTest.abortedByRemote())
		{
			if (syncPointFree)
				break;

			try { Thread.currentThread().sleep(1000); } catch (InterruptedException ie) {}
		}

		// this synchronization is needed for a clean synch point release across all virtual users - do not remove this code
		synchronized (objectVector)
		{
			logVector.log("synch point reached, new vector size = " + objectVector.size());
		}

		// apply deblock delay
		try { Thread.currentThread().sleep(deblockDelayFactor * vDeblockDelay); } catch (InterruptedException ie) {}
	}


	/**
	 * deblock watchdog thread.
	 */
	public void run()
	{
		while (!loadTest.abortedByRemote())
		{
			// calculate actual number of inactive users
            int numInactiveUsers = 0;
            if (loadTest.getAllThreadDoneFlags() != null)
            {
                boolean[] threadDoneFlags = loadTest.getAllThreadDoneFlags();

                for (int x = 0; x < threadDoneFlags.length; x++)
                {
                    if (threadDoneFlags[x] == true)
                        numInactiveUsers++;
                }
            }
            else
               throw new RuntimeException("incompatible: this plug-in does not support Prx version 5.2-O or later");

			// synch point reached ?
			boolean synchPointReached = false;
			synchronized (objectVector)
			{
				if ((performanceData.getParallelUsers() - numInactiveUsers) == objectVector.size())
				{
					// release all users
					for (int x = 0; x < objectVector.size(); x++)
					{
						UserSynchPoint1 usp = (UserSynchPoint1) objectVector.elementAt(x);
						usp.syncPointFree = true;
					}
					objectVector.clear();
					synchPointReached = true;
				}
			}

			if (synchPointReached)
				break;	// all done in thread

			// wait again
			try { Thread.currentThread().sleep(1000); } catch (InterruptedException ie) {}
		}
	}


	/**
	 * Return plug-in output parameter.
	 */
	public Object getOutputParameter(int parameterNumber)
	{
		switch (parameterNumber)
		{
			default:
				return null;
		}
	}


	/**
	 * Finalize plug-in at end of loop.
	 */
	public void deconstruct(Object context)
	{
		// LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
	}


}	// end of class

