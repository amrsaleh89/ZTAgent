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

import java.net.InetAddress;
import java.util.Random;


/**
 * Load test add-on module.
 */
public class DNSRoundRobinLoadBalancing implements LoadtestPluginInterface
{
	private String vDnsHostName = "www.";		// input parameter #1 - label "DNS Round Robin Host Name"

	private LogVector logVector = null;		// internal log vector - use logVector.log(<String>) to write log data

	private String hostIpAddress = null;
	
	private static boolean firstCall = true;


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
		return "DNS Round Robin Load Balancing";
	}


	public String getPluginDescription()
	{
		return "This Plug-in adds support for web servers which are using DNS Round Robin load balancing.\n\n" +
               "At the start of each loop a randomly assigned IP address is used to open the network connections to the web server - based on the DNS translation of the host name. The IP address remains the same overall URL calls in the same loop - but can change when a next loop is executed.\n\n" +
               "You have to configure the DNS host name of the web servers as plug-in input parameter (user input field).";
	}


	public int getAllowedConstructScope()
	{
		return LoadtestPluginInterface.CONSTRUCT_SCOPE_LOOP;
	}


	public int getAllowedExecScope()
	{
		return LoadtestPluginInterface.EXEC_SCOPE_ALL_URLS;
	}


	public int getAllowedExecOrder()
	{
		return LoadtestPluginInterface.EXEC_ORDER_BEFORE;
	}


	public boolean allowMultipleUsage()
	{
		return true;
	}


	public String[] getInputParameterLabels()
	{
		String[] labels = new String[1];
		labels[0] = "DNS Round Robin Host Name";
		return labels;
	}


	public LoadtestPluginFixedUserInputField[] getFixedUserInputFields()
	{
		LoadtestPluginFixedUserInputField[] userInputFields = new LoadtestPluginFixedUserInputField[1];
		userInputFields[0] = new LoadtestPluginFixedUserInputField("vDnsHostName", true, "www.");
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
	 * input parameter #1: (String) vDnsHostName / default value = 'www.' / label "DNS Round Robin Host Name"
	 *
	 * Note: all input parameters are always converted from strings.
	 */
	public void setInputParameter(int parameterNumber, Object parameterValue)
	{
		switch (parameterNumber)
		{
			case 0:
				vDnsHostName = ((String) parameterValue).trim();
				break;
			default:
				break;
		}
	}


	/**
	 * Execute plug-in before every URL call.
	 *
	 * Intrinsic plug-in implementation.
	 */
	public void execute(Object context)
	{
		logVector = new LogVector();
		LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
		HttpTestURL httpTestURL = pluginContext.getHttpTestURL();
		
		if (firstCall)
		{
			pluginContext.getPerformanceData().setErrorStatusTypeTranslation(HttpTestURL.STATUS_TYPE_PLUGIN_ERROR_CODE_1, "Unable to Translate DNS Name");
			firstCall = false;
		}

		if (hostIpAddress == null)
		{
			// make DNS translation
			try
			{
				InetAddress[] inetAddress = InetAddress.getAllByName(vDnsHostName);
				if (inetAddress.length == 1)
					hostIpAddress = inetAddress[0].getHostAddress();
				else
				{
					Random r = new Random();
					hostIpAddress = inetAddress[Math.abs(r.nextInt()) % inetAddress.length].getHostAddress();
				}
				logVector.log("HOST ADDRESS inside this loop for '" + vDnsHostName + "' = " + hostIpAddress);
			}
			catch (java.net.UnknownHostException ue)
			{
				String errMsg = "*** No DNS translation for host name '" + vDnsHostName + "' ***";
				logVector.log(errMsg);
				pluginContext.markUrlAsFailed(HttpTestURL.STATUS_TYPE_PLUGIN_ERROR_CODE_1, errMsg);
				return;
			}
			catch (Exception e)
			{
				String errMsg = "*** error: " + e + " ***";
				logVector.log(errMsg);
				pluginContext.markUrlAsFailed(HttpTestURL.STATUS_TYPE_PLUGIN_ERROR_CODE_1, errMsg);
				return;
			}
		}

		if (httpTestURL.getRequestHeaderObject().getTargetHost().equalsIgnoreCase(vDnsHostName))
		{
			// set DNS round robin IP address
			httpTestURL.setDnsRoundRobinIpAddress(hostIpAddress);
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

