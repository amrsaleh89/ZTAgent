
// *********************************************************************************************
//
// Automatically Generated Load Test Program
// -----------------------------------------
//
// Source: A2scenarioV1.java
// Date  : 14 Mar 2017 11:10:44 PST
// Author: Apica ZebraTester V5.4-I / automatically generated
//
// Procedure Copyright by Ingenieurbuero David Fischer AG  |  A Company of the Apica Group
// All Rights Reserved
//
// http://www.apicasystem.com                                         http://www.zebratester.com
// *********************************************************************************************


import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import dfischer.utils.AbstractInputFileReader;
import dfischer.utils.Base64Decoder;
import dfischer.utils.Base64Encoder;
import dfischer.utils.ConvertToDoubleNumber;
import dfischer.utils.ContentTokenExtractor;
import dfischer.utils.ContentTokenExtractorItem;
import dfischer.utils.Cookie;
import dfischer.utils.CookieHandler;
import dfischer.utils.DigestAuthContext;
import dfischer.utils.DNSCache;
import dfischer.utils.DNSTranslationTable;
import dfischer.utils.DynamicProtobufContentParser;
import dfischer.utils.ExternalParamFile;
import dfischer.utils.FileCache;
import dfischer.utils.GenericPluginInterface;
import dfischer.utils.GetRealTimeUserInputFieldsInterface;
import dfischer.utils.HtmlContentParser;
import dfischer.utils.HtmlHeaderCookieExtractor;
import dfischer.utils.HttpLoadTest;
import dfischer.utils.HttpLoadTestIncrementUserThread;
import dfischer.utils.HttpLoadTestUserContext;
import dfischer.utils.HttpSocketPool;
import dfischer.utils.HttpTestURL;
import dfischer.utils.HttpTestURLDNSContext;
import dfischer.utils.HttpTestURLThreadHandler;
import dfischer.utils.InlineScriptExecutionException;
import dfischer.utils.InlineScriptExecutor;
import dfischer.utils.InnerLoopContext;
import dfischer.utils.Lib;
import dfischer.utils.LoadtestInlineScriptContext;
import dfischer.utils.LoadtestInlineScriptVar;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.NextProxyConfig;
import dfischer.utils.ParseArgs;
import dfischer.utils.ParseUrl;
import dfischer.utils.PerformanceData;
import dfischer.utils.PerformanceDataTickExtension;
import dfischer.utils.ProtobufFieldAndValueElement;
import dfischer.utils.ProtobufLib;
import dfischer.utils.RealTimeUserInputField;
import dfischer.utils.ScreenshotImage;
import dfischer.utils.SetThreadStepInterface;
import dfischer.utils.SSLInit;
import dfischer.utils.SSLSessionCacheStatistic;
import dfischer.utils.SSLSessionCacheStatisticInterface;
import dfischer.utils.SuspendResumeInterface;
import dfischer.utils.TextLineTokenExtractor;
import dfischer.utils.TextLineTokenExtractorItem;
import dfischer.utils.ThreadStepInterface;
import dfischer.utils.UserInputField;
import dfischer.utils.UserTransactionContext;
import dfischer.utils.UserTransactionRuntimeHandler;
import dfischer.utils.VarInputFileReader;
import dfischer.utils.VarRandomInputFileReader;
import dfischer.utils.VaryingLoadInterface;
import dfischer.utils.VaryingTestDurationInterface;
import dfischer.utils.XmlContentParser;
import dfischer.utils.XmlDoctypeCommentParser;
import dfischer.utils.ZoneTime;
import dfischer.proxysniffer.ProxySnifferVarSourceInlineScript;


/**
 * Automatically generated load test program.
 */
public class A2scenarioV1 extends HttpLoadTest implements Runnable, ThreadStepInterface, SetThreadStepInterface, SSLSessionCacheStatisticInterface, VaryingLoadInterface, VaryingTestDurationInterface, SuspendResumeInterface, GetRealTimeUserInputFieldsInterface
{
	public static final String prxVersion = "V5.4-I";
	public static final int    prxCharEncoding = 2;                         // 1 = OS Default, 2 = ISO-8859-1, 3 = UTF-8
	public static final String testDescription = "";

	public static String USER_AGENT_1 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:51.0) Gecko/20100101 Firefox/51.0";

	private static final boolean CONTAINS_PARALLEL_EXECUTED_URLS = false;
	private static final int MAX_PARALLEL_THREADS_PER_USER = 6;                       // default value for max. parallel executed URLs per user
	private static int maxParallelThreadsPerUser = MAX_PARALLEL_THREADS_PER_USER;     // configured value for max. parallel executed URLs per user

	private static final boolean CONTAINS_EXTERNAL_RESOURCES = false;       // note: external resources are typically additional Java library files (*.jar files) invoked by self-developed plug-ins. Consider that Input Files and the Main Class of Plug-Ins are NOT external resources in this context because ZebraTester knows already their declarations.

	private static int plannedStartupDelay = 200;                           // startup delay between concurrent users in milliseconds, see main argument "-sdelay"
	private static int plannedTestDuration = -1;                            // planned load test duration in seconds, 0 = unlimited, see main argument "-d"
	private static int maxPlannedLoops = 0;  								// maximum planned loops per user, 0 = unlimited, see main argument "-maxloops"
	private static int plannedRequestTimeout = 0;                           // planned request timeout in seconds, see main argument "-t"
	
	private static String defaultTimeZone = "PST";                          // use main argument -tz <timezone> to alter
	private static char defaultNumberGroupingSeparator = '\'';              // use main argument -dgs a|c to alter
	private static String sslProtocolVersion = "all";                       // applied ssl protocol version: "all" = v3/tls/tls11/tls12, use main argument -ssl to set a fixed protocol version
	private static boolean sslcmode = false;                                // support of deficient ssl servers, use main argument -sslcmode to enable
	private static boolean sslECC = false;                                  // elliptic curve cryptography (ECC) is disabled by default, use main argument -ecc to enable
	private static boolean sslSNI = true;                                   // server name indication (SNI) is enabled by default, use main argument -nosni to disable
	private static boolean sslSNICirical = false;                           // the SNI TLS extension is by default set as non-critical, use main argument -snicritical to set as critical
	private static final long loopErrorDelay = 20;                          // error delay (in milliseconds) applied if a loop of a virtual user has failed
	private static final String THREAD_NAME = "T000000";                    // internal
	private static Integer dumpLock = new Integer(0);                       // internal
	private static Integer inputFileLock = new Integer(0);                  // internal
	private volatile int threadStep = ThreadStepInterface.THREAD_NO_STEP;   // internal
	private boolean urlCallPassed = false;                                  // internal
	private String localIpAddress = null;                                   // internal
	private static long downlinkBandwidth = 0;                              // max. downlink bandwidth per user. 0 = unlimited. use main argument -downlink <kbps> to alter
	private static long uplinkBandwidth = 0;                                // max. uplink bandwidth per user. 0 = unlimited. use main argument -uplink <kbps> to alter
	private static boolean dnsPerLoop = false;								// true if main argument "-dnsperloop" is set = perform new DNS resolves for each executed loop. normally false
	private HttpTestURLDNSContext userDNSContext = null;                    // user specific DNS context - normally null when no special DNS servers are used
	
	private static volatile boolean debugFailedLoops = false;               // true if main argument "-dfl" is set
	private static volatile boolean debugLoops = false;                     // true if main argument "-dl" is set
	private static volatile boolean debugHttp = false;                      // true if main argument "-dh" is set
	private static volatile boolean debugContent = false;                   // true if main argument "-dc" is set
	private static volatile boolean debugCookies = false;                   // true if main argument "-dC" is set
	private static volatile boolean debugKeepAlive = false;                 // true if main argument "-dK" is set
	private static volatile boolean debugSsl = false;                       // true if main argument "-dssl" is set
	
	private static String resultFile = null;                                // name of binary test result file or null
	
	private final static String httpProtocolVersion = "1.1";      // applied HTTP protocol version V1.1
	
	private static ExternalParamFile externalParamFile = null;                              // used only for very large parameter values (commonly unused)
	private static final String EXTERNAL_PARAM_FILE = "A2scenarioV1ExtParams.dat";          // input file name for very large parameter values
	private static FileCache requestFileCache = new FileCache();  // file cache for large XML, SOAP and ASCII requests
	
	private int requestTimeout = -1;                              // thread input data from constructor, timeout per url request in seconds
	private int remainingLoops = -1;                              // thread input data from constructor, number of loops per thread (optional)
	private boolean checkLoopCount = false;                       // thread input data from constructor, number of loops per thread (optional)
	private int threadNumber = -1;                                // thread input data from constructor, internal thread number
	private int threadLoopCounter = 0;                            // internal loop counter per thread
	private volatile int userResumeStartWaitDelay = 0;            // internal, user specific delay when the load test execution is resumed
	private volatile boolean decrementEndOfLoopFlag = false;	  // internal flag to decrement the simulated user at runtime
	private volatile boolean incrementUserFlag = false;           // internal flag to increment the simulated user at runtime
	private volatile long incrementUserStartTime = -1;            // internal start time when increment the simulated user at runtime
	private static AtomicInteger totalLoopCounter = new AtomicInteger(0);    // internal overall loop counter

	private LoadtestPluginContext userPluginContext = null;       // plug-in context per user
	private LoadtestInlineScriptContext inlineScriptContext = null;          // re-used, scratch, the returned context of an inline script that runs at item or URL exec scope

	private Object sslSessionCache = null;                        // internal ssl session cache per thread
	private SSLSessionCacheStatistic sslStatistic = null;         // internal ssl session cache statistic per thread
	private static int sslSessionCacheTimeout = 300;              // timeout of ssl session cache in seconds, 0 = session cache disabled
	private static int sslHandshakeRandomGeneratorType = -1;      // invalid initial value, use the default secure random generator for SSL handshakes
	private HttpSocketPool socketPool = null;                     // re-used, scratch, internal socket pool per thread and loop
	private CookieHandler cookieHandler = null;                   // re-used, scratch, internal cookie handler per thread and loop

	private HttpTestURL testURL = null;                           // re-used, scratch, http request and response
	private HttpTestURLThreadHandler pageThreadHandler = null;    // re-used, scratch, support for parallel processing of http request within a page
	private int httpStatus = -1;                                  // re-used, scratch, http response status code from webserver
	private HtmlContentParser htmlContentParser = null;           // re-used, scratch, used to extract vars from http response
	private XmlContentParser xmlContentParser = null;             // re-used, scratch, used to extract vars from http response
	private DynamicProtobufContentParser protobufContentParser = null;  // re-used, scratch, used to extract vars from http response
	private TextLineTokenExtractor textLineTokenExtractor = null; // re-used, scratch, used to extract vars from http response
	private ContentTokenExtractor contentTokenExtractor = null;   // re-used, scratch, used to extract vars from http response
	
	private static String ReturnSeat = "";                                       // var declaration from web admin var handler: scope = global
	private static String DepartureDate = Lib.getFormattedCurrentDateAndTime("yyyy-MM-dd", 7, "PST"); // var declaration from web admin var handler: scope = global
	private static String SeatKeyword = "REGULAR_SEAT";                          // var declaration from web admin var handler: scope = global
	private static String ReturnDate = Lib.getFormattedCurrentDateAndTime("yyyy-MM-dd", 14, "PST"); // var declaration from web admin var handler: scope = global

	private volatile UserTransactionRuntimeHandler transactionHandler = new UserTransactionRuntimeHandler();		// re-used, support to manage user-defined transactions

	private static Object plugin0002 = null;                      // plug-in #2: "SelectSeatNumberJetBlue" - definition from web admin var handler: construction scope = global
	private static String plugin0002SelectSeatNumberJetBlue = null;// internal - not used in this load test program, but used by the GUI - reflective information

	private String DepSeat = null;                       // var declaration from web admin var handler: scope = per loop
	private String DepSeatData = null;                   // var declaration from web admin var handler: scope = per loop
	private String ReturnSeatData = null;                // var declaration from web admin var handler: scope = per loop
	
	private Object plugin0001 = null;                             // plug-in #1: "SelectSeatNumberJetBlue" - definition from web admin var handler: construction scope = per loop
	private static String plugin0001SelectSeatNumberJetBlue = null;// internal - not used in this load test program, but used by the GUI - reflective information


	/**
	 * constructor: called from load test plug-ins (scope = global).
	 */
	public A2scenarioV1()
	{
		super();
	}


	/**
	 * constructor: called when a user is created (per user).
	 */
	public A2scenarioV1(int maxLoops, int requestTimeout, int threadNumber)
	{
		super();
		this.requestTimeout = requestTimeout;
		this.remainingLoops = maxLoops;
		this.checkLoopCount = (maxLoops > 0);
		this.threadNumber = threadNumber;
		this.sslSessionCache = SSLInit.getNewSslSessionCache(sslSessionCacheTimeout);
		this.sslStatistic = new SSLSessionCacheStatistic();
		if (isMultihomed() && (!ipPerLoop()))
			this.localIpAddress = getNextMultihomedIpAddress();
		if (dnsCache != null)
			userDNSContext = new HttpTestURLDNSContext(dnsCache, threadNumber);
		
		// initialize context for plug-ins which are executed per user
		userPluginContext = new LoadtestPluginContext(prxVersion, prxCharEncoding, this, threadNumber);
	}


	/**
	 * internal method: called when a user starts a loop.
	 * contains the recorded session which is called by users x loops.
	 * 
	 * @param totalLoopCounter total number of loops (0..n-1) counted overall threads. This value is unique per loop.
	 * 
	 * @return  true:  loop successful completed.
	 *          false: loop failed.
	 */
	private boolean execute(int totalLoopCounter) throws Exception
	{
		markStartOfLoop();
		threadStep = 0;                 // internal - start loop at thread step 0
		
		// enable DNS resolves per loop?
		if (dnsPerLoop)
			userDNSContext = new HttpTestURLDNSContext(dnsCache.clone(false), threadNumber);
		
		// debug http headers?
		Object httpLogVectorObject = null;
		if (debugHttp)
			httpLogVectorObject = this;
		
		// create socket pool per loop
		sslSessionCache = SSLInit.getNewSslSessionCache(sslSessionCacheTimeout);		// reset the SSL session cache to get new SSL session IDs for this loop
		socketPool = new HttpSocketPool(this, sslProtocolVersion, sslSessionCache, sslStatistic, sslcmode);
		if (sslHandshakeRandomGeneratorType != -1)
			socketPool.setSslHandshakeRandomGeneratorType(sslHandshakeRandomGeneratorType);
		socketPool.setSupportEllipticCurves(sslECC);
		socketPool.setHintUseSNI(sslSNI);
		socketPool.setSniCritical(sslSNICirical);
		if (debugKeepAlive)
		{
			socketPool.setlogVectorObject(this);
			if (debugSsl)
				socketPool.enableSslLog();
		}
		
		if (downlinkBandwidth > 0)
			socketPool.setDownlinkBandwidth(downlinkBandwidth);
		if (uplinkBandwidth > 0)
			socketPool.setUplinkBandwidth(uplinkBandwidth);
		
		if (isMultihomed())
		{
			if (ipPerLoop())
				localIpAddress = getNextMultihomedIpAddress();
			socketPool.setClientIpAddress(localIpAddress);
			log();
			log("multihomed client ip address = " + localIpAddress);
		}
		
		// setup cookie handler per loop
		cookieHandler = new CookieHandler();
		if (debugCookies)
			cookieHandler.setLogVectorInterface(this);
		
		// customised vars from web admin var handler: scope = per loop
		htmlContentParser = null;
		xmlContentParser = null;
		protobufContentParser = null;
		DepSeat = null;
		DepSeatData = null;
		ReturnSeatData = null;
		log();
		log("<<< DepSeat = " + DepSeat);
		log("<<< DepSeatData = " + DepSeatData);
		log("<<< ReturnSeatData = " + ReturnSeatData);
		
		// initialize context for plug-ins which are executed per loop
		LoadtestPluginContext loopPluginContext = new LoadtestPluginContext(prxVersion, prxCharEncoding, this, threadNumber, socketPool, cookieHandler);
		
		// initialize all per loop constructed plug-ins
		try
		{
			// define object for plug-in #1 and call plug-in constructor
			Class pluginClass0 = Class.forName("SelectSeatNumberJetBlue");
			plugin0001 = pluginClass0.newInstance();
			((GenericPluginInterface) plugin0001).construct(loopPluginContext);
		}
		catch (Exception classException)
		{
			System.out.println("*** ERROR: UNABLE TO LOAD CLASS FOR PLUG-IN ***");
			classException.printStackTrace();
			System.exit(-2);
		}
		



		// --- VIRTUAL PAGE #0 ---
		if (!executePage_0(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
			return false;



		// --- PAGE BREAK: Test [0] ---
		log();
		log();
		log("# Page #1: Landing Page");
		log("# ---------------------");
		threadStep = setPageBreak(performanceData, threadStep, "Page #1: Landing Page", 3000, 35, -1);		// hint: param #4 is the user's think time in milliseconds, param #5 is randomness of the user's think time in percent (+/- 0..100%), param #6 is the maximum acceptable response time in milliseconds (-1 = not configured)
		pageThreadHandler = new HttpTestURLThreadHandler(threadStep - 1, maxParallelThreadsPerUser, performanceData, this);   // support for parallel processing of http(s) requests within a page. hint: param #2 is the number of parallel threads per user
		log();

		if (!executePage_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 1 failed
			synchResponsesParallelRequestsPage_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!synchResponsesParallelRequestsPage_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
			return false;		// execution of page 1 failed
		
		// page 1 successfully executed
		pageThreadHandler.addPageResponseTimeToResult(this);



		// --- PAGE BREAK: Test [11] ---
		log();
		log();
		log("# Page #2: Find Flights");
		log("# ---------------------");
		threadStep = setPageBreak(performanceData, threadStep, "Page #2: Find Flights", 3000, 35, -1);		// hint: param #4 is the user's think time in milliseconds, param #5 is randomness of the user's think time in percent (+/- 0..100%), param #6 is the maximum acceptable response time in milliseconds (-1 = not configured)
		pageThreadHandler = new HttpTestURLThreadHandler(threadStep - 1, maxParallelThreadsPerUser, performanceData, this);   // support for parallel processing of http(s) requests within a page. hint: param #2 is the number of parallel threads per user
		log();

		if (!executePage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 2 failed
			synchResponsesParallelRequestsPage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!synchResponsesParallelRequestsPage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
			return false;		// execution of page 2 failed
		
		// page 2 successfully executed
		pageThreadHandler.addPageResponseTimeToResult(this);



		// --- PAGE BREAK: Test [27] ---
		log();
		log();
		log("# Page #3: Fill Info");
		log("# ------------------");
		threadStep = setPageBreak(performanceData, threadStep, "Page #3: Fill Info", 3000, 35, -1);		// hint: param #4 is the user's think time in milliseconds, param #5 is randomness of the user's think time in percent (+/- 0..100%), param #6 is the maximum acceptable response time in milliseconds (-1 = not configured)
		pageThreadHandler = new HttpTestURLThreadHandler(threadStep - 1, maxParallelThreadsPerUser, performanceData, this);   // support for parallel processing of http(s) requests within a page. hint: param #2 is the number of parallel threads per user
		log();

		if (!executePage_3(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 3 failed
			synchResponsesParallelRequestsPage_3(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!synchResponsesParallelRequestsPage_3(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
			return false;		// execution of page 3 failed
		
		// page 3 successfully executed
		pageThreadHandler.addPageResponseTimeToResult(this);



		// --- PAGE BREAK: Test [40] ---
		log();
		log();
		log("# Page #4: Pick Seats");
		log("# -------------------");
		threadStep = setPageBreak(performanceData, threadStep, "Page #4: Pick Seats", 3000, 35, -1);		// hint: param #4 is the user's think time in milliseconds, param #5 is randomness of the user's think time in percent (+/- 0..100%), param #6 is the maximum acceptable response time in milliseconds (-1 = not configured)
		pageThreadHandler = new HttpTestURLThreadHandler(threadStep - 1, maxParallelThreadsPerUser, performanceData, this);   // support for parallel processing of http(s) requests within a page. hint: param #2 is the number of parallel threads per user
		log();

		if (!executePage_4(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 4 failed
			synchResponsesParallelRequestsPage_4(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!executePage_4_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 4 failed
			synchResponsesParallelRequestsPage_4(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!synchResponsesParallelRequestsPage_4(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
			return false;		// execution of page 4 failed
		
		// page 4 successfully executed
		pageThreadHandler.addPageResponseTimeToResult(this);



		// --- PAGE BREAK: Test [63] ---
		log();
		log();
		log("# Page #5: Payment Info");
		log("# ---------------------");
		threadStep = setPageBreak(performanceData, threadStep, "Page #5: Payment Info", 3000, 35, -1);		// hint: param #4 is the user's think time in milliseconds, param #5 is randomness of the user's think time in percent (+/- 0..100%), param #6 is the maximum acceptable response time in milliseconds (-1 = not configured)
		pageThreadHandler = new HttpTestURLThreadHandler(threadStep - 1, maxParallelThreadsPerUser, performanceData, this);   // support for parallel processing of http(s) requests within a page. hint: param #2 is the number of parallel threads per user
		log();

		if (!executePage_5(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 5 failed
			synchResponsesParallelRequestsPage_5(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!synchResponsesParallelRequestsPage_5(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
			return false;		// execution of page 5 failed
		
		// page 5 successfully executed
		pageThreadHandler.addPageResponseTimeToResult(this);
		
		
		// loop successful done
		// --------------------
		
		markEndOfPage(performanceData);
		
		endOfExecuteLoop(true, null, -1, loopPluginContext);
		log();
		return true;
	}		// end of execute()
		
		
		
	/**
	 * internal method: called when a user has completed a loop.
	 */
	private void endOfExecuteLoop(boolean loopPassed, HttpTestURL testURL, int threadStep, LoadtestPluginContext loopPluginContext) throws Exception
	{
		// log URL if last call has failed
		if ((!loopPassed) && (testURL != null))
			log(testURL);
		
		// update plugin context
		loopPluginContext.setLoopPassed(loopPassed);
		if ((testURL != null) && (loopPluginContext.getHttpTestURL() == null))
			loopPluginContext.setHttpTestURL(testURL);
		if ((threadStep != -1) && (loopPluginContext.getThreadStep() == -1))
			loopPluginContext.setThreadStep(threadStep);
		
		// deconstruct all per loop constructed plug-ins
		((GenericPluginInterface) plugin0001).deconstruct(loopPluginContext);
	}


	/**
	 * Recorded http requests of page #0.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_0(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{

		// all http requests of page #0 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #1.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_1(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// # Page #1: Landing Page
		// # ---------------------



		// --- HTTP REQUEST: Test [1] <- WEB ADMIN Index 1 ---
		String requestProt0001 = "http";
		String requestHost0001 = "book.jetblue.com";
		int    requestPort0001 = 80;
		String requestFile0001 = "/";
		String requestHeader0001 = "GET " + requestFile0001 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0001, requestHost0001, requestPort0001, requestHeader0001, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0001 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 301, content type = [verification disabled], header text fragment = [verification disabled], recorded content size = 0
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {301}, null, null, null);		// Test [1] <- Index 1
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [2] <- WEB ADMIN Index 2 ---
		log();
		log("# title: 302 Found");
		String requestProt0002 = "https";
		String requestHost0002 = "book.jetblue.com";
		int    requestPort0002 = 443;
		String requestFile0002 = "/";
		String requestHeader0002 = "GET " + requestFile0002 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0002, requestHost0002, requestPort0002, requestHeader0002, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0002 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 302, content type = [verification disabled], header text fragment = [verification disabled], recorded content size = 305
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {302}, null, null, null);		// Test [2] <- Index 2
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [3] <- WEB ADMIN Index 3 ---
		String requestProt0003 = "https";
		String requestHost0003 = "book.jetblue.com";
		int    requestPort0003 = 443;
		String requestFile0003 = "/B6/";
		String requestHeader0003 = "GET " + requestFile0003 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0003, requestHost0003, requestPort0003, requestHeader0003, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0003 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 321
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [3] <- Index 3
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [4] <- WEB ADMIN Index 4 ---
		String requestProt0004 = "https";
		String requestHost0004 = "book.jetblue.com";
		int    requestPort0004 = 443;
		String requestFile0004 = "/B6/ApplicationStartAction.do";
		String requestHeader0004 = "GET " + requestFile0004 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0004, requestHost0004, requestPort0004, requestHeader0004, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0004 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 182
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [4] <- Index 4
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [5] <- WEB ADMIN Index 5 ---
		log();
		log("# title: JetBlue | Search flights");
		String requestProt0005 = "https";
		String requestHost0005 = "book.jetblue.com";
		int    requestPort0005 = 443;
		String requestFile0005 = "/shop/search/";
		String requestHeader0005 = "GET " + requestFile0005 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0005, requestHost0005, requestPort0005, requestHeader0005, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0005 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 5569
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [5] <- Index 5
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [6] <- WEB ADMIN Index 6 ---
		String requestProt0006 = "https";
		String requestHost0006 = "book.jetblue.com";
		int    requestPort0006 = 443;
		String requestFile0006 = "/shop/search/app/partials/main.html";
		String requestHeader0006 = "GET " + requestFile0006 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/plain, */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0006, requestHost0006, requestPort0006, requestHeader0006, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0006 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 135
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [6] <- Index 6
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [7] <- WEB ADMIN Index 7 ---
		String requestProt0007 = "https";
		String requestHost0007 = "www2.jetblue.com";
		int    requestPort0007 = 443;
		String requestFile0007 = "/JetBlueAlerts/MainTitle_Content.aspx";
		String requestHeader0007 = "GET " + requestFile0007 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Origin: https://book.jetblue.com\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0007, requestHost0007, requestPort0007, requestHeader0007, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0007 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 669
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [7] <- Index 7
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [8] <- WEB ADMIN Index 8 ---
		String requestProt0008 = "https";
		String requestHost0008 = "www2.jetblue.com";
		int    requestPort0008 = 443;
		String requestFile0008 = "/includes/bookjetblueSidekick.aspx";
		String requestHeader0008 = "GET " + requestFile0008 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Origin: https://book.jetblue.com\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0008, requestHost0008, requestPort0008, requestHeader0008, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0008 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 1307
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [8] <- Index 8
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [9] <- WEB ADMIN Index 9 ---
		String requestProt0009 = "https";
		String requestHost0009 = "www2.jetblue.com";
		int    requestPort0009 = 443;
		String requestFile0009 = "/includes/globalAjaxFooter.aspx";
		String requestHeader0009 = "GET " + requestFile0009 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Origin: https://book.jetblue.com\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0009, requestHost0009, requestPort0009, requestHeader0009, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0009 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 3743
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [9] <- Index 9
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [10] <- WEB ADMIN Index 10 ---
		String requestProt0010 = "https";
		String requestHost0010 = "book.jetblue.com";
		int    requestPort0010 = 443;
		String requestFile0010 = "/assets/globals/partials/footer.html";
		String requestHeader0010 = "GET " + requestFile0010 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0010, requestHost0010, requestPort0010, requestHeader0010, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0010 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 437
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [10] <- Index 10
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #1 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #2.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_2(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// # Page #2: Find Flights
		// # ---------------------



		// --- HTTP REQUEST: Test [12] <- WEB ADMIN Index 12 ---
		log();
		log("# title: Travel Distribution Platform - IBE");
		String requestProt0012 = "https";
		String requestHost0012 = "book.jetblue.com";
		int    requestPort0012 = 443;
		String requestFile0012 = "/B6/webqtrip.html";
		String requestContent0012 = 
				"lang=en" +
				"&pos=JETBLUE" +
				"&flexibleSearch=TRUE" +
				"&searchType=NORMAL" +
				"&jbBookerSearchType=flights" +
				"&journeySpan=RT" +
				"&jbBookerCurrency-getaways=usd" +
				"&origin=BOS" +
				"&destination=FLL" +
				"&roundTripFaresFlag=false" +
				"&sharedMarket=false" +
				"&departureDate=" + java.net.URLEncoder.encode(DepartureDate) +		// recorded value = 2017-03-21
				"&returnDate=" + java.net.URLEncoder.encode(ReturnDate) +		// recorded value = 2017-03-28
				"&numAdults=1" +
				"&numChildren=0" +
				"&numInfants=0" +
				"&jbBookerCurrency-flights=usd" +
				"&refundable=false" +
				"&fareFamily=LOWESTFARE" +
				"&fareDisplay=lowest" +
				"&X-uniqueStateKey=AMT94s1aAQAAFevNrH2s4py9T-8wSDig8iuJ3TiJZbV78MkIen4hUocS8knt" +
				"&X-woBNMIPDr=QgmsentaY%3D0PJEtfO9tAknEo58Urppz1klpGkDJI8%3DpSJ6UAk6uI58ZoEutdy%3D0NJlpNuo0IJ9gxOnd%3DJEtIk6U_OnoLOlpxN6p%3DJ6U-pVdLJkoAY9D-y6UWJ80np8doknpCkbd-e6udO_ZgYEwMY6oNe%3DpxrED8O9p2klpx2nAvrn0Ke%3DpNZlpLyEt-29DdOEpdYnDdz9pRynpN49DuynuAkbJ-yEt9JEztYno%3De6kgkl0NNlpGklpNYnoS4nkSY%3DtgYbJvkEZ-Y%3DH1YnoRJYqvN_fdOnJKOnugYnDd4_DAJ%3DUgYVs9N%3DZKY8DKYEfSJEZd%3DlZKY8DKY9ZdY9ZbY%3DoxJ6ZoknpCk1pCJDAxY%3DuwY%3DU-J6U-zl0gJlpxZEJdY9ZzkloNkbUxY%3DutY9ZdOnoWkld%3DJOAxY%3DubY%3Doxe6U92VtdOEpdO_ZzkloNkbuNJEDLY%3DU2JpD-yEt-TSkvOn0_O%3DpN4Vp2JEjR2ntNY_k2JEtrYVp9e6U22nDTOn0RJpkdy9D-Y_td26DNJ6o-J8pSJ6udY9zjJlo-JpD-OndCJcdAY6kzOnD2JEzmeno%3DypDuOVfKO9zDY6pxe6o1JEJAy%3DptJDtLJEtnY_tRy6UWJpDuOVfKO9zDOVtKOlpNkVdwelpWecULOn0LJEt-hpJgYVpdOcU2y_tdJ6UMknpNOndxJOA2kloWeuZNy6DdN9gTOdDuOVfKO95Wu%3DggO-ZdJnouYVZaOn0_O%3DpN5lpSOlpN-6ggOukAJlp%3De6Ud8l4uJ%3DdC4_fSk6kAY9sWN%3DggOuZK8%3D0uOnDdN9DKkEtWJ8ggO%3DWjO_ZNe6U95lo2ejEiJl0Wk6udY9zbJl0Wk6udY9ZaY%3DZUcLH2wQbDP0Lxy%3DZWE%3Do2JlAnYlo2kEZKOlJTknDezluWJn4IcLM2fDpIE_kdynZNeEJdOd02y_tAOVZIJnHwy64S2lDTyEtgy_ZdOdDdkbkWeloNO%3Dp-NnDKYEfgk1uKJlEbJl0Wk6udY9ZDY%3DZd2nkdk1pSJ6udY9Zah8dxunkdk1pSJ6udY9Z2r9dwYlo2O-UgY6EDelo2rEZ-OndvkEZdOcJAY6o9JEMlYloUJEt22EouJEtU8%3DpSJ6D-Y_j9N%3DDKY9ZdhVZDJ6UuN6dCYnpN5oZDzbdAYnUdOdZdhVz8Y60X8np4k6p2k1JuYl4zy_tdJ6HZOnp4k6p2k1JuYl4zy_tdJ6Htk%3DpvJVtAknpNu_kdynRAkotdOEpdO_Zlk64S8%3DDNJ6pCTOdzJEouJ6U-k6_-tSufy_ZAknpyz%3DtqJ6D-2-oLOl4d8loU8%3Dp2O%3DdKYSZle64dN8dCkwgfO9tghkfDkEZgkldKYx0vO%3DpNknpN21UKkldne6DgkldKYSRrkEDTz6oCy6kdOS4zeloNJ6ZEY_tPJEmjpl0uy%3DgoknpCkbUyZl0Ry6dC8np4k6p2kMbwcLziE_fTy6U-Y%3D_ry6ZxZEJdY9ZbeED-J6UdOSRgkVZgy%3DgoknpCkbRWy64S8lggY9ZKYORWOnpgklprY_fuObRxJEZgy%3DgoknpCkbuxeEDLyEZWe1p%3DJ6U-46p%3DJ6U-cLHtJndNJ8p%3DJ6U-4nJNy6udO0f9JEZwY%3DuLkEZdJoD-h64d26kSY%3DtgYoD-Y_tgJ%3DEbYl0Wy64zkl0Ny6kduluKhdt8rufdJEtwY%3DUCJ6D-e60C%3DluKhdtdOEpdO_ZfYndRyEZAY%3DUlOnoRJOkLeloCkl0RN_fKO_ZDJED2y6kdu_tdJ%3Dd2klpN8VtKkl0WY%3D4iy6UxYlpN-_tdY60%3DJ8p%3DJ6U-zld2klpCJEmpOnp4k6p2k1oCe6ugkldKYxJNy6ud29DdO_DAY%3DUzkl0Ny6kd4_DAJlpvyEmEk%3Dpve%3Dd-8dZw8lpdOxDKYnUdy_ZAY%3DHYk%3Dpve%3Dd-8np4k6p2k1oCe6ugkldKYxJNy6ud__kdynRAkotdO%3D0SknpbY%3DDgY1JAYlpzhED-J6up8xNg4_JAy9tgkl6dcLQmYl0WyEZAY%3DUvyEm2wOJ2y_tdJ6HVkl0KYltgOqIbJ9tgY6poYlpRJ6U-N%3DdCYnpN5lpAJ%3Dg-NndCYnpNp%3DdxklWmY_p-JEtiJ6d9eVzjY_p-JEtEe6Z-ebk2y_tdJ6Uy4_DWOnpdYdnSN%3DoLO1DKJlpsy6ud4%3DoLO1UgY6EjyEfLpnpNO%3DdKYSkvk6dSJ1d1NnZKzn0-pVtgy%3DKzeloNJVkgOnpwY%3DUWkEtNJ6UWhOgLYlo-Jn0NYOkLOn0xk6D-N9fNY%3DZuy_Zzk6mtkEDdOxo9J6U-49JdYnZKOSd%3DJ6UxY_tzk6jnN%3Do%3Dy6dS5lpAJ%3Dg-Nno%3Dy6dSp%3DdxklWjy%3D0SY_t1JEf-ebJTJ6d9eVzjOldGJ641JEf-ebp_e6Z-ejYmJlp2y_tAOVZAY%3DHVY6p2O%3Do9JIbZL%3DUuYOp2kloWecA2kloWe_ZNy6DdAbUPJEdvY%3DoNJ1p%3DJ6U-OcRRY_p2J8p%3DJ6U-O0oKOnddY9ZgkldKYxp%3DJ6U-OcR-Y_pWe1p%3DJ6U-OXMVe%3DpUZl0_YSgPJEdrOnp2OcpPJEdpOjKly64-5%3DpU4%3DD-On4mJE92fIbl4%3DRdh8DKJlE2abkRJEZg5%3DpUcLPiO%3DgAJ9ZmJE92wMbVAOJTe6ZxJ6Hae6z21Ok-y6ksy6udNVZUOlpfkVZNAbARY_p2J8DSe6DPN6uKkEDdZl0_YSdRY_p2J8uKknEVY60uO%3DppOjK21MbocLY2abtK6btK6IbjcL22fcoGLEnTc4w2fOdny6RdZEJdY9z2wMbV29pCkVtuO_ZdJ1p%3DJ6U-LEWfheaWNVZKk6DTZ6UxNEZKk6DTz60%3DJOA-Y_pWeoD-yEt-3FwGFhOFFFoe2hbaiCOFFFoe2hbaVhOFFFoe2hbfKhOFFFoe2hbfC3OFFFoe2hm0bCOFFFoe2hmcE3OFFFoe2hmcEI7a3FMGfsw7AsyYnnsJXx3%3DdL_qoq3du3y0lVajGM7oGhy4VIXQUW1kQPQL8_pCi1ouJNFLD5F4szruiw1%3DsW1%3DsWFLi1kDpa-LD2FLiagrpurAFsVgXxZWG29g3FYUfL0q3r7F3rxF3rTQfaRdGMxm7M7b3F_vrHJV71rgZGfoFfIg3FHVz60Xe64SyOgsJEZ2y%3DoLJOcumWF7j1ugy%3DdCkl02ea9sbWF4D2F4bW8Lszr4b2Vfbrziz6oW56U-J62oZ%3DpWe%3DQibWF4bwF4bwVLz60Xe64Sy5cumWF7j1ugy%3DdCkl02ewS756U-J6L7z6oWi10zio77bzFCbziHiVt%3DsW84mWFAi1kdy%3DRKm2iLbzFLbzF4i1JAOnpnY_7KDz1CbFwFLM7MZaoV7fg1sfgV7sVGfhVdoFKIXnAm4EEG1sM6Y9pSYafTyEb7Ync7OVtKOlpNklddOcd8hEfdZEtNY_mW_-fp8xLjJ1dFpptbanp8rop5zFAKk1fp8xLjrop5zFPWGI7Z3fjd3fM7GFV7ahV7FswGosw2aOw2FIbaUr1CSI7zGsFfGfwgGFVg3fz7cLwFcLV2FC8fbYmG13w7FhFfGhFaGI78GMbFLMbfcLmdFzbf3fM7GFV7FhV7F3VGosw2Fbw2FIbaUr1-bM7zGsFfGFVgGFzg3fz7cLwFcLV2FC8fDb5x3fM7GFM7ahV7FswGosw2aOw2FIbaUr1CRK7zGsFwGFVgGFmg3fz7cLwFcLV2FC8fbLEG13w7F3FfGhFwGI78GMbFLMbfcLmdFzr23fM7GFM7FhV7fsVGosw2Fbw2FIbaUr1-4XEG13w7FCFtGhFFGM78GMbFLMbfcLmdF5323fM7GFm7FhV7FCVGosw2Fbw2FIbaUr127M7zGsFaGFVgGFMg3fz7cLwFcLV2FC8fDiQG13w7FCFfGhF1GI78GMbFLMbfcLmdFz8d3fM7GFm71sV7FhwGosw2Fbw2FIbaUr1u6K7pTI76FFM7Gh4atc2FH1o5i7FV3fz7Lbw2FIbmUro0vxpErdXg3fyFFhwgH1i92bwSrpivFFIGoswFLMbfcLKdFEu%3DZpkaEPKGo3i1G3mdFERVG-8%3DrdmGo3i1G3mdFECVG-8%3DrdmGo3i1G3mdFECMG-8%3DrdVGo3i1G3mdFECEG-8%3DrdVGo3i1G3mdFECRG-8%3DrdwGo3i1G3mdFECNG-8%3DrdwGo3i1G3mdFEKzG-8%3DrdwGo3i1G3mdFEKIG-8_rdwGo3i1G3mdFEKdG-8UrdwGo3i1G3mdFEKRG-8XrdwGo3i1G3mdFEKuG-8crdwGo3i1G3mdFEK0G-80rdwGo3i1G3mdFELVG-8QrdVGo3i1G3mdFELEG-pwrdmGo3i1G3mdFELkG-pordmGo3i1G3mdFELWG-pirdzGo3i1G3mdFELRG-pjrdzGo3i1G3mdFEL2G-pbrdYGo3i1G3mdFE4FG-pMrdYGo3i1G3mdFE4wG-prrdIGo3i1G3mdFE4mG-pZrdWGo3i1G3mdFE4zG-pzrdPGo3i1G3mdFE4YG-p8rdKGo3i1G3mdFE4xG-p6rd2Go3i1G3mdFE4uG-pErd_Go3i1G3mdFE4HG-pErd_Go3i1G3mdFENwG-pErdXg3fyFFCwgH1i92bwSrpivFFIGoswFLMbfcLKdFE%3DVZpkaEKxy3fn7TjF";
		String requestHeader0012 = "POST " + requestFile0012 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"Content-Length: " + requestContent0012.length() + "\r\n" + 		// Content-Length: 5327
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0012, requestHost0012, requestPort0012, requestHeader0012, requestContent0012.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0012 = null;		// support garbage collector to reduce memory
		requestContent0012 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 24541
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [12] <- Index 12
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [13] <- WEB ADMIN Index 13 ---
		String requestProt0013 = "https";
		String requestHost0013 = "book.jetblue.com";
		int    requestPort0013 = 443;
		String requestFile0013 = "/B6/AirLowFareSearchExt.do";
		String requestContent0013 = 
				"ajaxAction=true";
		String requestHeader0013 = "POST " + requestFile0013 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"Content-Length: " + requestContent0013.length() + "\r\n" + 		// Content-Length: 15
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0013, requestHost0013, requestPort0013, requestHeader0013, requestContent0013.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0013 = null;		// support garbage collector to reduce memory
		requestContent0013 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 381
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [13] <- Index 13
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [14] <- WEB ADMIN Index 14 ---
		log();
		log("# title: JetBlue Airways");
		String requestProt0014 = "https";
		String requestHost0014 = "www.jetblue.com";
		int    requestPort0014 = 443;
		String requestFile0014 = "/content/snippets/interstitials/booking/index1.html" +
				"?version=201702040020";
		String requestHeader0014 = "GET " + requestFile0014 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0014, requestHost0014, requestPort0014, requestHeader0014, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0014 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 664
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [14] <- Index 14
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [15] <- WEB ADMIN Index 15 ---
		log();
		log("# title: Flight Selection");
		String requestProt0015 = "https";
		String requestHost0015 = "book.jetblue.com";
		int    requestPort0015 = 443;
		String requestFile0015 = "/B6/AirFareFamiliesFlexibleForward.do";
		String requestHeader0015 = "GET " + requestFile0015 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0015, requestHost0015, requestPort0015, requestHeader0015, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0015 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 110465
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [15] <- Index 15
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [16] <- WEB ADMIN Index 16 ---
		String requestProt0016 = "https";
		String requestHost0016 = "book.jetblue.com";
		int    requestPort0016 = 443;
		String requestFile0016 = "/B6/ValidateFlow.do";
		String requestContent0016 = 
				"validator=SHOPPING";
		String requestHeader0016 = "POST " + requestFile0016 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0016.length() + "\r\n" + 		// Content-Length: 18
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0016, requestHost0016, requestPort0016, requestHeader0016, requestContent0016.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0016 = null;		// support garbage collector to reduce memory
		requestContent0016 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 78
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [16] <- Index 16
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [17] <- WEB ADMIN Index 17 ---
		String requestProt0017 = "https";
		String requestHost0017 = "book.jetblue.com";
		int    requestPort0017 = 443;
		String requestFile0017 = "/B6/html/empty.html" +
				"?version=201702040020";
		String requestHeader0017 = "GET " + requestFile0017 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0017, requestHost0017, requestPort0017, requestHeader0017, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0017 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 195
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [17] <- Index 17
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [18] <- WEB ADMIN Index 18 ---
		String requestProt0018 = "https";
		String requestHost0018 = "book.jetblue.com";
		int    requestPort0018 = 443;
		String requestFile0018 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=FLIGHT_RESULTS";
		String requestHeader0018 = "GET " + requestFile0018 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0018, requestHost0018, requestPort0018, requestHeader0018, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0018 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 212
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [18] <- Index 18
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [19] <- WEB ADMIN Index 19 ---
		String requestProt0019 = "https";
		String requestHost0019 = "book.jetblue.com";
		int    requestPort0019 = 443;
		String requestFile0019 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=AIR_COMBINABLE_FFF_SEARCH_RESULTS_BOT";
		String requestHeader0019 = "GET " + requestFile0019 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0019, requestHost0019, requestPort0019, requestHeader0019, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0019 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 212
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [19] <- Index 19
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [20] <- WEB ADMIN Index 20 ---
		String requestProt0020 = "https";
		String requestHost0020 = "book.jetblue.com";
		int    requestPort0020 = 443;
		String requestFile0020 = "/B6/Merchandizing.do" +
				"?touchPoint=AIR_COMBINABLE_FFF_SEARCH_RESULTS_BOT";
		String requestHeader0020 = "GET " + requestFile0020 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0020, requestHost0020, requestPort0020, requestHeader0020, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0020 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 817
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [20] <- Index 20
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [21] <- WEB ADMIN Index 21 ---
		String requestProt0021 = "https";
		String requestHost0021 = "book.jetblue.com";
		int    requestPort0021 = 443;
		String requestFile0021 = "/B6/AirSelectOWCFlight.do";
		String requestContent0021 = 
				"isFareFamilySearchResult=true" +
				"&selectedItineraries=0%2C7" +
				"&selectedFlightIds=0%2C7" +
				"&checkedFlightIds=0%2C7" +
				"&combinabilityReloadRequired=true" +
				"&flightIndex=" +
				"&flowStep=AIR_COMBINABLE_FARE_FAMILIES_FLEXIBLE_SEARCH_RESULTS" +
				"&alignment=horizontal" +
				"&context=airSelection";
		String requestHeader0021 = "POST " + requestFile0021 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0021.length() + "\r\n" + 		// Content-Length: 252
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0021, requestHost0021, requestPort0021, requestHeader0021, requestContent0021.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0021 = null;		// support garbage collector to reduce memory
		requestContent0021 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 1783
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [21] <- Index 21
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [22] <- WEB ADMIN Index 22 ---
		String requestProt0022 = "https";
		String requestHost0022 = "book.jetblue.com";
		int    requestPort0022 = 443;
		String requestFile0022 = "/B6/AirSelectOWCFlight.do";
		String requestContent0022 = 
				"isFareFamilySearchResult=true" +
				"&selectedItineraries=0%2C7" +
				"&selectedItineraries=1%2C151" +
				"&selectedFlightIds=0%2C7%2C1%2C151" +
				"&checkedFlightIds=0%2C7%2C1%2C151" +
				"&combinabilityReloadRequired=true" +
				"&flightIndex=" +
				"&flowStep=AIR_COMBINABLE_FARE_FAMILIES_FLEXIBLE_SEARCH_RESULTS" +
				"&alignment=horizontal" +
				"&context=airSelection";
		String requestHeader0022 = "POST " + requestFile0022 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0022.length() + "\r\n" + 		// Content-Length: 300
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0022, requestHost0022, requestPort0022, requestHeader0022, requestContent0022.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0022 = null;		// support garbage collector to reduce memory
		requestContent0022 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 3298
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [22] <- Index 22
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [23] <- WEB ADMIN Index 23 ---
		log();
		log("# title: Api Cross Sell");
		String requestProt0023 = "https";
		String requestHost0023 = "book.jetblue.com";
		int    requestPort0023 = 443;
		String requestFile0023 = "/B6/Merchandizing.do" +
				"?touchPoint=FLIGHT_RESULTS";
		String requestHeader0023 = "GET " + requestFile0023 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0023, requestHost0023, requestPort0023, requestHeader0023, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0023 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 2402
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [23] <- Index 23
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [24] <- WEB ADMIN Index 24 ---
		log();
		log("# title: Api Cross Sell");
		String requestProt0024 = "https";
		String requestHost0024 = "vacations.jetblue.com";
		int    requestPort0024 = 443;
		String requestFile0024 = "/travel/api_cross_sell.cfm" +
				"?crossSellToken=12738cd5-d507-4398-a574-92727e61f444" +
				"&nav=inpathdefault";
		String requestHeader0024 = "GET " + requestFile0024 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: vacations.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0024, requestHost0024, requestPort0024, requestHeader0024, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0024 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 1726
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [24] <- Index 24
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [25] <- WEB ADMIN Index 25 ---
		String requestProt0025 = "https";
		String requestHost0025 = "vacations.jetblue.com";
		int    requestPort0025 = 443;
		String requestFile0025 = "/travel/api_cross_sell_ajax_update.cfm" +
				"?nav=inpathdefault";
		String requestContent0025 = 
				"crossSellToken=12738cd5-d507-4398-a574-92727e61f444";
		byte[] requestContentBinary0025 = requestContent0025.getBytes("UTF-8");
		String requestHeader0025 = "POST " + requestFile0025 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: vacations.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/javascript, text/html, application/xml, text/xml, */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"X-Prototype-Version: 1.7\r\n" + 
				"Content-Type: application/x-www-form-urlencoded; charset=UTF-8\r\n" + 
				"Content-Length: " + requestContentBinary0025.length + "\r\n" + 		// Content-Length: 51
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0025, requestHost0025, requestPort0025, requestHeader0025, requestContentBinary0025, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0025 = null;		// support garbage collector to reduce memory
		requestContent0025 = null;		// support garbage collector to reduce memory
		requestContentBinary0025 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 1141
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [25] <- Index 25
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [26] <- WEB ADMIN Index 26 ---
		String requestProt0026 = "https";
		String requestHost0026 = "vacations.jetblue.com";
		int    requestPort0026 = 443;
		String requestFile0026 = "/travel/api_cross_sell/record_cross_sell_load_event.cfm" +
				"?crossSellToken=12738cd5-d507-4398-a574-92727e61f444" +
				"&nav=inpathdefault";
		String requestHeader0026 = "GET " + requestFile0026 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: vacations.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0026, requestHost0026, requestPort0026, requestHeader0026, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0026 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 129
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [26] <- Index 26
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #2 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #3.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_3(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// # Page #3: Fill Info
		// # ------------------



		// --- HTTP REQUEST: Test [28] <- WEB ADMIN Index 28 ---
		String requestProt0028 = "https";
		String requestHost0028 = "book.jetblue.com";
		int    requestPort0028 = 443;
		String requestFile0028 = "/B6/ValidateFlow.do";
		String requestContent0028 = 
				"validator=SHOPPING_ON_SUBMIT";
		String requestHeader0028 = "POST " + requestFile0028 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0028.length() + "\r\n" + 		// Content-Length: 28
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0028, requestHost0028, requestPort0028, requestHeader0028, requestContent0028.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0028 = null;		// support garbage collector to reduce memory
		requestContent0028 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 78
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [28] <- Index 28
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [29] <- WEB ADMIN Index 29 ---
		String requestProt0029 = "https";
		String requestHost0029 = "book.jetblue.com";
		int    requestPort0029 = 443;
		String requestFile0029 = "/B6/ValidateFormAction.do";
		String requestContent0029 = 
				"validateAction=AirVerifyFareFamiliesItineraryCheckLogin" +
				"&hiddenFlightSelection=0%2C7%2C1%2C151" +
				"&markUpMoneyAmount=" +
				"&flightItineraryId%5B0%5D=7" +
				"&flightItineraryGroupId%5B0%5D_00=7" +
				"&flightItineraryId%5B1%5D=151" +
				"&flightItineraryGroupId%5B1%5D_00=151" +
				"&vsessionid=";
		String requestHeader0029 = "POST " + requestFile0029 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0029.length() + "\r\n" + 		// Content-Length: 252
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0029, requestHost0029, requestPort0029, requestHeader0029, requestContent0029.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0029 = null;		// support garbage collector to reduce memory
		requestContent0029 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 327
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [29] <- Index 29
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [30] <- WEB ADMIN Index 30 ---
		log();
		log("# title: Consumer IBE");
		String requestProt0030 = "https";
		String requestHost0030 = "book.jetblue.com";
		int    requestPort0030 = 443;
		String requestFile0030 = "/B6/en/pages/tdp/interstitials/hotelSelection.html" +
				"?version=201702040020";
		String requestHeader0030 = "GET " + requestFile0030 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0030, requestHost0030, requestPort0030, requestHeader0030, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0030 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 528
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [30] <- Index 30
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [31] <- WEB ADMIN Index 31 ---
		String requestProt0031 = "https";
		String requestHost0031 = "book.jetblue.com";
		int    requestPort0031 = 443;
		String requestFile0031 = "/B6/AirVerifyFareFamiliesItineraryCheckLogin.do";
		String requestContent0031 = 
				"ajaxAction=true" +
				"&hiddenFlightSelection=0%2C7%2C1%2C151" +
				"&markUpMoneyAmount=" +
				"&flightItineraryId%5B0%5D=7" +
				"&flightItineraryGroupId%5B0%5D_00=7" +
				"&flightItineraryId%5B1%5D=151" +
				"&flightItineraryGroupId%5B1%5D_00=151" +
				"&vsessionid=";
		String requestHeader0031 = "POST " + requestFile0031 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0031.length() + "\r\n" + 		// Content-Length: 212
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0031, requestHost0031, requestPort0031, requestHeader0031, requestContent0031.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0031 = null;		// support garbage collector to reduce memory
		requestContent0031 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 379
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [31] <- Index 31
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [32] <- WEB ADMIN Index 32 ---
		log();
		log("# title: Traveler Details");
		String requestProt0032 = "https";
		String requestHost0032 = "book.jetblue.com";
		int    requestPort0032 = 443;
		String requestFile0032 = "/B6/TravelersDetailsForwardAction.do";
		String requestHeader0032 = "GET " + requestFile0032 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0032, requestHost0032, requestPort0032, requestHeader0032, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0032 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 68573
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [32] <- Index 32
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [33] <- WEB ADMIN Index 35 ---
		String requestProt0033 = "https";
		String requestHost0033 = "book.jetblue.com";
		int    requestPort0033 = 443;
		String requestFile0033 = "/B6/ValidateFlow.do";
		String requestContent0033 = 
				"validator=PASSENGERS";
		String requestHeader0033 = "POST " + requestFile0033 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0033.length() + "\r\n" + 		// Content-Length: 20
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0033, requestHost0033, requestPort0033, requestHeader0033, requestContent0033.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0033 = null;		// support garbage collector to reduce memory
		requestContent0033 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 78
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [33] <- Index 33
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [34] <- WEB ADMIN Index 36 ---
		String requestProt0034 = "https";
		String requestHost0034 = "book.jetblue.com";
		int    requestPort0034 = 443;
		String requestFile0034 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=PASSENGER_DETAILS";
		String requestHeader0034 = "GET " + requestFile0034 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0034, requestHost0034, requestPort0034, requestHeader0034, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0034 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 183
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [34] <- Index 34
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [35] <- WEB ADMIN Index 38 ---
		String requestProt0035 = "https";
		String requestHost0035 = "book.jetblue.com";
		int    requestPort0035 = 443;
		String requestFile0035 = "/B6/Merchandizing.do" +
				"?touchPoint=PASSENGER_DETAILS";
		String requestHeader0035 = "GET " + requestFile0035 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0035, requestHost0035, requestPort0035, requestHeader0035, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0035 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 923
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [35] <- Index 35
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [36] <- WEB ADMIN Index 39 ---
		String requestProt0036 = "https";
		String requestHost0036 = "book.jetblue.com";
		int    requestPort0036 = 443;
		String requestFile0036 = "/B6/SearchStateByCountry.do";
		String requestContent0036 = 
				"countryCode=KH" +
				"&isStateDropdownReadonly=";
		String requestHeader0036 = "POST " + requestFile0036 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0036.length() + "\r\n" + 		// Content-Length: 38
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0036, requestHost0036, requestPort0036, requestHeader0036, requestContent0036.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0036 = null;		// support garbage collector to reduce memory
		requestContent0036 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 57
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [36] <- Index 36
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [37] <- WEB ADMIN Index 40 ---
		String requestProt0037 = "https";
		String requestHost0037 = "book.jetblue.com";
		int    requestPort0037 = 443;
		String requestFile0037 = "/B6/SearchStateByCountry.do";
		String requestContent0037 = 
				"countryCode=UG" +
				"&isStateDropdownReadonly=";
		String requestHeader0037 = "POST " + requestFile0037 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0037.length() + "\r\n" + 		// Content-Length: 38
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0037, requestHost0037, requestPort0037, requestHeader0037, requestContent0037.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0037 = null;		// support garbage collector to reduce memory
		requestContent0037 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 57
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [37] <- Index 37
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [38] <- WEB ADMIN Index 41 ---
		String requestProt0038 = "https";
		String requestHost0038 = "book.jetblue.com";
		int    requestPort0038 = 443;
		String requestFile0038 = "/B6/SearchStateByCountry.do";
		String requestContent0038 = 
				"countryCode=AE" +
				"&isStateDropdownReadonly=";
		String requestHeader0038 = "POST " + requestFile0038 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0038.length() + "\r\n" + 		// Content-Length: 38
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0038, requestHost0038, requestPort0038, requestHeader0038, requestContent0038.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0038 = null;		// support garbage collector to reduce memory
		requestContent0038 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 176
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [38] <- Index 38
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [39] <- WEB ADMIN Index 42 ---
		String requestProt0039 = "https";
		String requestHost0039 = "book.jetblue.com";
		int    requestPort0039 = 443;
		String requestFile0039 = "/B6/SearchStateByCountry.do";
		String requestContent0039 = 
				"countryCode=US" +
				"&isStateDropdownReadonly=";
		String requestHeader0039 = "POST " + requestFile0039 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0039.length() + "\r\n" + 		// Content-Length: 38
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0039, requestHost0039, requestPort0039, requestHeader0039, requestContent0039.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0039 = null;		// support garbage collector to reduce memory
		requestContent0039 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 705
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [39] <- Index 39
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #3 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #4.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_4(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// # Page #4: Pick Seats
		// # -------------------



		// --- HTTP REQUEST: Test [41] <- WEB ADMIN Index 44 ---
		String requestProt0041 = "https";
		String requestHost0041 = "book.jetblue.com";
		int    requestPort0041 = 443;
		String requestFile0041 = "/B6/ValidateFormAction.do";
		String requestContent0041 = 
				"validateAction=UpdateReservation" +
				"&operation=SEAT_SELECTION" +
				"&travellersCount=1" +
				"&travellersInfo%5B0%5D.isLeadPax=true" +
				"&travellersInfo%5B0%5D.selectedFromProfileId=" +
				"&travellersInfo%5B0%5D.travellerType=ADT" +
				"&travellersInfo%5B0%5D.isLogged=false" +
				"&travellersInfo%5B0%5D.title=MR" +
				"&travellersInfo%5B0%5D.firstName=Amr" +
				"&travellersInfo%5B0%5D.lastName=Saleh" +
				"&travellersInfo%5B0%5D.middleName=" +
				"&travellersInfo%5B0%5D.suffix=" +
				"&travellersInfo%5B0%5D.travellerAge=35" +
				"&travellersInfo%5B0%5D.travellerBirthMonth=" +
				"&travellersInfo%5B0%5D.travellerBirthDay=" +
				"&travellersInfo%5B0%5D.travellerBirthYear=" +
				"&travellersInfo%5B0%5D.travellerBirthDate=" +
				"&travellersInfo%5B0%5D.specialRequests%5B0%5D=" +
				"&travellersInfo%5B0%5D.specialRequestsText%5B0%5D=" +
				"&travellersInfo%5B0%5D.specialRequests%5B1%5D=" +
				"&travellersInfo%5B0%5D.specialRequestsText%5B1%5D=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(loyaltyMemberships)=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(loyaltyNumbers)=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(loyaltyLevels)=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(gender)=M" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(dobMonth)=11" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(dobDay)=1" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(dobYear)=1989" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(dobDate)=11%2F01%2F1989" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(knownTravelerNumber)=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(redressNumber)=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(addressLine1)=1250%206th%20St" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(addressLine2)=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(cityName)=Santa%20Monica" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(countryCode)=US" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(stateCode)=CA" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(postalCode)=90017" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(personalEmail)=amr.saleh%40apicasystem.com" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(confirmPersonalEmail)=amr.saleh%40apicasystem.com" +
				"&travellersInfo%5B0%5D.homePhone.phoneNumber=5622294809" +
				"&travellersInfo%5B0%5D.mobilePhone.phoneNumber=" +
				"&travellersInfo%5B0%5D.assignedItems%5B0%5D=1" +
				"&travellersInfo%5B0%5D.emergencyContactTitle=" +
				"&travellersInfo%5B0%5D.emergencyContactFirstName=" +
				"&travellersInfo%5B0%5D.emergencyContactMiddleName=" +
				"&travellersInfo%5B0%5D.emergencyContactLastName=" +
				"&travellersInfo%5B0%5D.emergencyContactNameSuffix=" +
				"&travellersInfo%5B0%5D.emergencyContactRelationship=" +
				"&travellersInfo%5B0%5D.emergencyContactPhone.phoneCountryISO2Code=US" +
				"&travellersInfo%5B0%5D.emergencyContactPhone.phoneNumber=" +
				"&qrPassword=" +
				"&qrConfirmPassword=" +
				"&qrAcceptTermsAndConditions=false" +
				"&acceptTermsAndConditions=true" +
				"&acceptHazardousMaterials=true" +
				"&vsessionid=";
		String requestHeader0041 = "POST " + requestFile0041 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0041.length() + "\r\n" + 		// Content-Length: 2722
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0041, requestHost0041, requestPort0041, requestHeader0041, requestContent0041.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0041 = null;		// support garbage collector to reduce memory
		requestContent0041 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 327
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [41] <- Index 41
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [42] <- WEB ADMIN Index 45 ---
		log();
		log("# title: JetBlue Airways");
		String requestProt0042 = "https";
		String requestHost0042 = "www.jetblue.com";
		int    requestPort0042 = 443;
		String requestFile0042 = "/content/snippets/interstitials/booking/index3.html" +
				"?version=201702040020";
		String requestHeader0042 = "GET " + requestFile0042 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0042, requestHost0042, requestPort0042, requestHeader0042, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0042 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 1167
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [42] <- Index 42
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [43] <- WEB ADMIN Index 46 ---
		String requestProt0043 = "https";
		String requestHost0043 = "book.jetblue.com";
		int    requestPort0043 = 443;
		String requestFile0043 = "/B6/UpdateReservation.do";
		String requestContent0043 = 
				"ajaxAction=true" +
				"&operation=SEAT_SELECTION" +
				"&travellersCount=1" +
				"&travellersInfo%5B0%5D.isLeadPax=true" +
				"&travellersInfo%5B0%5D.selectedFromProfileId=" +
				"&travellersInfo%5B0%5D.travellerType=ADT" +
				"&travellersInfo%5B0%5D.isLogged=false" +
				"&travellersInfo%5B0%5D.title=MR" +
				"&travellersInfo%5B0%5D.firstName=Amr" +
				"&travellersInfo%5B0%5D.lastName=Saleh" +
				"&travellersInfo%5B0%5D.middleName=" +
				"&travellersInfo%5B0%5D.suffix=" +
				"&travellersInfo%5B0%5D.travellerAge=35" +
				"&travellersInfo%5B0%5D.travellerBirthMonth=" +
				"&travellersInfo%5B0%5D.travellerBirthDay=" +
				"&travellersInfo%5B0%5D.travellerBirthYear=" +
				"&travellersInfo%5B0%5D.travellerBirthDate=" +
				"&travellersInfo%5B0%5D.specialRequests%5B0%5D=" +
				"&travellersInfo%5B0%5D.specialRequestsText%5B0%5D=" +
				"&travellersInfo%5B0%5D.specialRequests%5B1%5D=" +
				"&travellersInfo%5B0%5D.specialRequestsText%5B1%5D=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(loyaltyMemberships)=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(loyaltyNumbers)=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(loyaltyLevels)=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(gender)=M" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(dobMonth)=11" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(dobDay)=1" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(dobYear)=1989" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(dobDate)=11%2F01%2F1989" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(knownTravelerNumber)=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(redressNumber)=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(addressLine1)=1250%206th%20St" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(addressLine2)=" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(cityName)=Santa%20Monica" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(countryCode)=US" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(stateCode)=CA" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(postalCode)=90017" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(personalEmail)=amr.saleh%40apicasystem.com" +
				"&travellersInfo%5B0%5D.advancedPassengerDetails(confirmPersonalEmail)=amr.saleh%40apicasystem.com" +
				"&travellersInfo%5B0%5D.homePhone.phoneNumber=5622294809" +
				"&travellersInfo%5B0%5D.mobilePhone.phoneNumber=" +
				"&travellersInfo%5B0%5D.assignedItems%5B0%5D=1" +
				"&travellersInfo%5B0%5D.emergencyContactTitle=" +
				"&travellersInfo%5B0%5D.emergencyContactFirstName=" +
				"&travellersInfo%5B0%5D.emergencyContactMiddleName=" +
				"&travellersInfo%5B0%5D.emergencyContactLastName=" +
				"&travellersInfo%5B0%5D.emergencyContactNameSuffix=" +
				"&travellersInfo%5B0%5D.emergencyContactRelationship=" +
				"&travellersInfo%5B0%5D.emergencyContactPhone.phoneCountryISO2Code=US" +
				"&travellersInfo%5B0%5D.emergencyContactPhone.phoneNumber=" +
				"&qrPassword=" +
				"&qrConfirmPassword=" +
				"&qrAcceptTermsAndConditions=false" +
				"&acceptTermsAndConditions=true" +
				"&acceptHazardousMaterials=true" +
				"&vsessionid=";
		String requestHeader0043 = "POST " + requestFile0043 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0043.length() + "\r\n" + 		// Content-Length: 2705
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0043, requestHost0043, requestPort0043, requestHeader0043, requestContent0043.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0043 = null;		// support garbage collector to reduce memory
		requestContent0043 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 377
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [43] <- Index 43
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [44] <- WEB ADMIN Index 47 ---
		log();
		log("# title: Seats");
		String requestProt0044 = "https";
		String requestHost0044 = "book.jetblue.com";
		int    requestPort0044 = 443;
		String requestFile0044 = "/B6/ReservationSeatSelectionForward.do";
		String requestHeader0044 = "GET " + requestFile0044 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0044, requestHost0044, requestPort0044, requestHeader0044, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0044 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 56200
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [44] <- Index 44
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [45] <- WEB ADMIN Index 48 ---
		String requestProt0045 = "https";
		String requestHost0045 = "book.jetblue.com";
		int    requestPort0045 = 443;
		String requestFile0045 = "/B6/ValidateFlow.do";
		String requestContent0045 = 
				"validator=PASSENGERS";
		String requestHeader0045 = "POST " + requestFile0045 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0045.length() + "\r\n" + 		// Content-Length: 20
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0045, requestHost0045, requestPort0045, requestHeader0045, requestContent0045.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0045 = null;		// support garbage collector to reduce memory
		requestContent0045 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 78
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [45] <- Index 45
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [46] <- WEB ADMIN Index 49 ---
		String requestProt0046 = "https";
		String requestHost0046 = "book.jetblue.com";
		int    requestPort0046 = 443;
		String requestFile0046 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=SEAT_SELECTION_BOT";
		String requestHeader0046 = "GET " + requestFile0046 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0046, requestHost0046, requestPort0046, requestHeader0046, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0046 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 196
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [46] <- Index 46
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [47] <- WEB ADMIN Index 50 ---
		String requestProt0047 = "https";
		String requestHost0047 = "book.jetblue.com";
		int    requestPort0047 = 443;
		String requestFile0047 = "/B6/ValidateFormAction.do";
		String requestContent0047 = 
				"validateAction=ReservationStoreSeatSelection" +
				"&fieldName=seatInfo" +
				"&isFastValidation=true" +
				"&forwardToPayment=" +
				"&forwardToConfirmation=" +
				"&sendConfirmationEmail=false" +
				"&seatsConfirmed=false" +
				"&seatInfo%5B0%5D.flightSegmentId=" +
				"&seatInfo%5B1%5D.flightSegmentId=" +
				"&legSegment_0_seatField_1=" +
				"&seatInfo%5B0%5D.options%5B0%5D.paxId=" +
				"&seatInfo%5B0%5D.options%5B0%5D.seat=" +
				"&seatInfo%5B0%5D.options%5B0%5D.seatFeePricing=" +
				"&legSegment_1_seatField_1=" +
				"&seatInfo%5B1%5D.options%5B0%5D.paxId=" +
				"&seatInfo%5B1%5D.options%5B0%5D.seat=" +
				"&seatInfo%5B1%5D.options%5B0%5D.seatFeePricing=" +
				"&vsessionid=";
		String requestHeader0047 = "POST " + requestFile0047 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0047.length() + "\r\n" + 		// Content-Length: 549
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0047, requestHost0047, requestPort0047, requestHeader0047, requestContent0047.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0047 = null;		// support garbage collector to reduce memory
		requestContent0047 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 327
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [47] <- Index 47
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [48] <- WEB ADMIN Index 51 ---
		String requestProt0048 = "https";
		String requestHost0048 = "book.jetblue.com";
		int    requestPort0048 = 443;
		String requestFile0048 = "/B6/AirRetrieveSeatMapAction.do" +
				"?departureDate=1490089200000" +
				"&fareFamilyCode=AN" +
				"&flightSegmentKey=0_0" +
				"&equipment=320" +
				"&flightNumber=269" +
				"&bookingClassCode=O" +
				"&currency=USD" +
				"&flightSegmentId=0" +
				"&airlineCode=B6" +
				"&destinationAirportCode=FLL" +
				"&operatingAirlineCode=B6" +
				"&arrivalDate=1490101620000" +
				"&originAirportCode=BOS" +
				"&inventorySystem=SABRE" +
				"&cacheKey=196397720" +
				"&seatMapEditableMode=true";
		String requestHeader0048 = "POST " + requestFile0048 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: 0\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0048, requestHost0048, requestPort0048, requestHeader0048, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0048 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 4632
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [48] <- Index 48
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// extract variable(s) from response
		try
		{
			// extract var 'DepSeatData'
			DepSeatData = testURL.getContentString("ISO-8859-1");
			log("<<< DepSeatData = " + DepSeatData);
		}
		catch (Exception e) { DepSeatData = null; log(e); }
		if (DepSeatData == null)
		{
			// failure - dump wrong response content and abort current outer loop - after that start next loop
			String errorText = "*** error: unable to extract var 'DepSeatData' from http response content";
			log(errorText);
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			threadStep = performanceData.setFailed(threadStep, HttpTestURL.STATUS_TYPE_USER_SPECIFIC_TEST_FAILED, errorText, testURL, this);
		
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [49] <- WEB ADMIN Index 52 ---
		String requestProt0049 = "https";
		String requestHost0049 = "book.jetblue.com";
		int    requestPort0049 = 443;
		String requestFile0049 = "/B6/Merchandizing.do" +
				"?touchPoint=SEAT_SELECTION_BOT";
		String requestHeader0049 = "GET " + requestFile0049 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0049, requestHost0049, requestPort0049, requestHeader0049, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		
		LoadtestPluginContext urlPluginBeforeCallContext0049 = new LoadtestPluginContext(prxVersion, prxCharEncoding, this, threadNumber, socketPool, cookieHandler, testURL, threadStep);
		
		// execute plug-in #2 before url call
		log(">>> execute plug-in #2: SelectSeatNumberJetBlue");
		synchronized (plugin0002)
		{
			((GenericPluginInterface) plugin0002).setInputParameter(0, DepSeatData);		// set plug-in input parameter
			((GenericPluginInterface) plugin0002).setInputParameter(1, SeatKeyword);		// set plug-in input parameter
			((GenericPluginInterface) plugin0002).execute(urlPluginBeforeCallContext0049);			// execute plug-in
			if (((GenericPluginInterface) plugin0002).getLogVector() != null)			// dump plug-in log
				log(((GenericPluginInterface) plugin0002).getLogVector().getLog());
			DepSeat = (String) ((GenericPluginInterface) plugin0002).getOutputParameter(0);		// get plug-in output parameter
			log("<<< DepSeat = " + DepSeat);
		}

		testURL.execute(performanceData);
		requestHeader0049 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 1120
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [49] <- Index 49
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [50] <- WEB ADMIN Index 53 ---
		String requestProt0050 = "https";
		String requestHost0050 = "book.jetblue.com";
		int    requestPort0050 = 443;
		String requestFile0050 = "/B6/ReservationSelectSeat.do";
		String requestContent0050 = 
				"forwardToPayment=" +
				"&forwardToConfirmation=" +
				"&sendConfirmationEmail=false" +
				"&seatsConfirmed=false" +
				"&seatInfo%5B0%5D.flightSegmentId=0_0" +
				"&seatInfo%5B1%5D.flightSegmentId=1_0" +
				"&legSegment_0_seatField_1=" + java.net.URLEncoder.encode(DepSeat) +		// recorded value = 7A
				"&seatInfo%5B0%5D.options%5B0%5D.paxId=1" +
				"&seatInfo%5B0%5D.options%5B0%5D.seat=" + java.net.URLEncoder.encode(DepSeat) +		// recorded value = 7A
				"&seatInfo%5B0%5D.options%5B0%5D.seatFeePricing=" +
				"&legSegment_1_seatField_1=" +
				"&seatInfo%5B1%5D.options%5B0%5D.paxId=1" +
				"&seatInfo%5B1%5D.options%5B0%5D.seat=" +
				"&seatInfo%5B1%5D.options%5B0%5D.seatFeePricing=" +
				"&vsessionid=";
		String requestHeader0050 = "POST " + requestFile0050 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0050.length() + "\r\n" + 		// Content-Length: 475
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0050, requestHost0050, requestPort0050, requestHeader0050, requestContent0050.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0050 = null;		// support garbage collector to reduce memory
		requestContent0050 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 1946
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [50] <- Index 50
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [51] <- WEB ADMIN Index 54 ---
		String requestProt0051 = "https";
		String requestHost0051 = "book.jetblue.com";
		int    requestPort0051 = 443;
		String requestFile0051 = "/B6/ValidateFormAction.do";
		String requestContent0051 = 
				"validateAction=ReservationStoreSeatSelection" +
				"&fieldName=seatInfo" +
				"&isFastValidation=true" +
				"&forwardToPayment=" +
				"&forwardToConfirmation=" +
				"&sendConfirmationEmail=false" +
				"&seatsConfirmed=false" +
				"&seatInfo%5B0%5D.flightSegmentId=0_0" +
				"&seatInfo%5B1%5D.flightSegmentId=1_0" +
				"&legSegment_0_seatField_1=" + java.net.URLEncoder.encode(DepSeat) +		// recorded value = 7A
				"&seatInfo%5B0%5D.options%5B0%5D.paxId=1" +
				"&seatInfo%5B0%5D.options%5B0%5D.seat=" + java.net.URLEncoder.encode(DepSeat) +		// recorded value = 7A
				"&seatInfo%5B0%5D.options%5B0%5D.seatFeePricing=" +
				"&legSegment_1_seatField_1=" +
				"&seatInfo%5B1%5D.options%5B0%5D.paxId=1" +
				"&seatInfo%5B1%5D.options%5B0%5D.seat=" +
				"&seatInfo%5B1%5D.options%5B0%5D.seatFeePricing=" +
				"&vsessionid=";
		String requestHeader0051 = "POST " + requestFile0051 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0051.length() + "\r\n" + 		// Content-Length: 561
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0051, requestHost0051, requestPort0051, requestHeader0051, requestContent0051.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0051 = null;		// support garbage collector to reduce memory
		requestContent0051 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 327
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [51] <- Index 51
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [52] <- WEB ADMIN Index 55 ---
		String requestProt0052 = "https";
		String requestHost0052 = "book.jetblue.com";
		int    requestPort0052 = 443;
		String requestFile0052 = "/B6/AirRetrieveSeatMapAction.do" +
				"?departureDate=1490704080000" +
				"&fareFamilyCode=AN" +
				"&flightSegmentKey=1_0" +
				"&equipment=320" +
				"&flightNumber=470" +
				"&bookingClassCode=M" +
				"&currency=USD" +
				"&flightSegmentId=10" +
				"&airlineCode=B6" +
				"&destinationAirportCode=BOS" +
				"&operatingAirlineCode=B6" +
				"&arrivalDate=1490715300000" +
				"&originAirportCode=FLL" +
				"&inventorySystem=SABRE" +
				"&cacheKey=251530554" +
				"&seatMapEditableMode=true";
		String requestHeader0052 = "POST " + requestFile0052 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: 0\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0052, requestHost0052, requestPort0052, requestHeader0052, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		
		LoadtestPluginContext urlPluginBeforeCallContext0052 = new LoadtestPluginContext(prxVersion, prxCharEncoding, this, threadNumber, socketPool, cookieHandler, testURL, threadStep);
		
		// execute plug-in #1 before url call
		log(">>> execute plug-in #1: SelectSeatNumberJetBlue");
		((GenericPluginInterface) plugin0001).setInputParameter(1, SeatKeyword);		// set plug-in input parameter
		((GenericPluginInterface) plugin0001).execute(urlPluginBeforeCallContext0052);			// execute plug-in
		if (((GenericPluginInterface) plugin0001).getLogVector() != null)			// dump plug-in log
			log(((GenericPluginInterface) plugin0001).getLogVector().getLog());
		ReturnSeat = (String) ((GenericPluginInterface) plugin0001).getOutputParameter(0);		// get plug-in output parameter
		log("<<< ReturnSeat = " + ReturnSeat);

		testURL.execute(performanceData);
		requestHeader0052 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 4582
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [52] <- Index 52
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// extract variable(s) from response
		try
		{
			// extract var 'ReturnSeatData'
			ReturnSeatData = testURL.getContentString("ISO-8859-1");
			log("<<< ReturnSeatData = " + ReturnSeatData);
		}
		catch (Exception e) { ReturnSeatData = null; log(e); }
		if (ReturnSeatData == null)
		{
			// failure - dump wrong response content and abort current outer loop - after that start next loop
			String errorText = "*** error: unable to extract var 'ReturnSeatData' from http response content";
			log(errorText);
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			threadStep = performanceData.setFailed(threadStep, HttpTestURL.STATUS_TYPE_USER_SPECIFIC_TEST_FAILED, errorText, testURL, this);
		
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [53] <- WEB ADMIN Index 56 ---
		String requestProt0053 = "https";
		String requestHost0053 = "book.jetblue.com";
		int    requestPort0053 = 443;
		String requestFile0053 = "/B6/ReservationSelectSeat.do";
		String requestContent0053 = 
				"forwardToPayment=" +
				"&forwardToConfirmation=" +
				"&sendConfirmationEmail=false" +
				"&seatsConfirmed=false" +
				"&seatInfo%5B0%5D.flightSegmentId=0_0" +
				"&seatInfo%5B1%5D.flightSegmentId=1_0" +
				"&legSegment_0_seatField_1=" + java.net.URLEncoder.encode(DepSeat) +		// recorded value = 7A
				"&seatInfo%5B0%5D.options%5B0%5D.paxId=1" +
				"&seatInfo%5B0%5D.options%5B0%5D.seat=" + java.net.URLEncoder.encode(DepSeat) +		// recorded value = 7A
				"&seatInfo%5B0%5D.options%5B0%5D.seatFeePricing=" +
				"&legSegment_1_seatField_1=" + java.net.URLEncoder.encode(ReturnSeat) +		// recorded value = 9E
				"&seatInfo%5B1%5D.options%5B0%5D.paxId=1" +
				"&seatInfo%5B1%5D.options%5B0%5D.seat=" + java.net.URLEncoder.encode(ReturnSeat) +		// recorded value = 9E
				"&seatInfo%5B1%5D.options%5B0%5D.seatFeePricing=" +
				"&vsessionid=";
		String requestHeader0053 = "POST " + requestFile0053 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0053.length() + "\r\n" + 		// Content-Length: 479
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0053, requestHost0053, requestPort0053, requestHeader0053, requestContent0053.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0053 = null;		// support garbage collector to reduce memory
		requestContent0053 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 1947
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [53] <- Index 53
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [54] <- WEB ADMIN Index 57 ---
		String requestProt0054 = "https";
		String requestHost0054 = "book.jetblue.com";
		int    requestPort0054 = 443;
		String requestFile0054 = "/B6/ValidateFormAction.do";
		String requestContent0054 = 
				"validateAction=ReservationStoreSeatSelection" +
				"&forwardToPayment=" +
				"&forwardToConfirmation=" +
				"&sendConfirmationEmail=false" +
				"&seatsConfirmed=true" +
				"&seatInfo%5B0%5D.flightSegmentId=0_0" +
				"&seatInfo%5B1%5D.flightSegmentId=1_0" +
				"&legSegment_0_seatField_1=" + java.net.URLEncoder.encode(DepSeat) +		// recorded value = 7A
				"&seatInfo%5B0%5D.options%5B0%5D.paxId=1" +
				"&seatInfo%5B0%5D.options%5B0%5D.seat=" + java.net.URLEncoder.encode(DepSeat) +		// recorded value = 7A
				"&seatInfo%5B0%5D.options%5B0%5D.seatFeePricing=" +
				"&legSegment_1_seatField_1=" + java.net.URLEncoder.encode(ReturnSeat) +		// recorded value = 9E
				"&seatInfo%5B1%5D.options%5B0%5D.paxId=1" +
				"&seatInfo%5B1%5D.options%5B0%5D.seat=" + java.net.URLEncoder.encode(ReturnSeat) +		// recorded value = 9E
				"&seatInfo%5B1%5D.options%5B0%5D.seatFeePricing=" +
				"&vsessionid=";
		String requestHeader0054 = "POST " + requestFile0054 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0054.length() + "\r\n" + 		// Content-Length: 523
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0054, requestHost0054, requestPort0054, requestHeader0054, requestContent0054.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0054 = null;		// support garbage collector to reduce memory
		requestContent0054 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 327
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [54] <- Index 54
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [55] <- WEB ADMIN Index 58 ---
		log();
		log("# title: JetBlue Airways");
		String requestProt0055 = "https";
		String requestHost0055 = "www.jetblue.com";
		int    requestPort0055 = 443;
		String requestFile0055 = "/content/snippets/interstitials/booking/index4.html" +
				"?version=201702040020";
		String requestHeader0055 = "GET " + requestFile0055 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0055, requestHost0055, requestPort0055, requestHeader0055, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0055 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 660
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [55] <- Index 55
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [56] <- WEB ADMIN Index 59 ---
		String requestProt0056 = "https";
		String requestHost0056 = "book.jetblue.com";
		int    requestPort0056 = 443;
		String requestFile0056 = "/B6/ReservationStoreSeatSelection.do";
		String requestContent0056 = 
				"ajaxAction=true" +
				"&forwardToPayment=" +
				"&forwardToConfirmation=" +
				"&sendConfirmationEmail=false" +
				"&seatsConfirmed=true" +
				"&seatInfo%5B0%5D.flightSegmentId=0_0" +
				"&seatInfo%5B1%5D.flightSegmentId=1_0" +
				"&legSegment_0_seatField_1=" + java.net.URLEncoder.encode(DepSeat) +		// recorded value = 7A
				"&seatInfo%5B0%5D.options%5B0%5D.paxId=1" +
				"&seatInfo%5B0%5D.options%5B0%5D.seat=" + java.net.URLEncoder.encode(DepSeat) +		// recorded value = 7A
				"&seatInfo%5B0%5D.options%5B0%5D.seatFeePricing=" +
				"&legSegment_1_seatField_1=" + java.net.URLEncoder.encode(ReturnSeat) +		// recorded value = 9E
				"&seatInfo%5B1%5D.options%5B0%5D.paxId=1" +
				"&seatInfo%5B1%5D.options%5B0%5D.seat=" + java.net.URLEncoder.encode(ReturnSeat) +		// recorded value = 9E
				"&seatInfo%5B1%5D.options%5B0%5D.seatFeePricing=" +
				"&vsessionid=";
		String requestHeader0056 = "POST " + requestFile0056 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0056.length() + "\r\n" + 		// Content-Length: 494
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0056, requestHost0056, requestPort0056, requestHeader0056, requestContent0056.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0056 = null;		// support garbage collector to reduce memory
		requestContent0056 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 374
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [56] <- Index 56
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [57] <- WEB ADMIN Index 60 ---
		log();
		log("# title: Extras");
		String requestProt0057 = "https";
		String requestHost0057 = "book.jetblue.com";
		int    requestPort0057 = 443;
		String requestFile0057 = "/B6/MerchandizingSearchShowPageForward.do" +
				"?flowId=seatsFlow";
		String requestHeader0057 = "GET " + requestFile0057 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0057, requestHost0057, requestPort0057, requestHeader0057, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0057 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 45377
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [57] <- Index 57
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [58] <- WEB ADMIN Index 61 ---
		String requestProt0058 = "https";
		String requestHost0058 = "book.jetblue.com";
		int    requestPort0058 = 443;
		String requestFile0058 = "/B6/ValidateFlow.do";
		String requestContent0058 = 
				"validator=BOOKING_PREPAYMENT";
		String requestHeader0058 = "POST " + requestFile0058 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0058.length() + "\r\n" + 		// Content-Length: 28
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0058, requestHost0058, requestPort0058, requestHeader0058, requestContent0058.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0058 = null;		// support garbage collector to reduce memory
		requestContent0058 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 78
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [58] <- Index 58
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [59] <- WEB ADMIN Index 62 ---
		String requestProt0059 = "https";
		String requestHost0059 = "book.jetblue.com";
		int    requestPort0059 = 443;
		String requestFile0059 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=BOOKING_PREPAYMENT";
		String requestHeader0059 = "GET " + requestFile0059 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0059, requestHost0059, requestPort0059, requestHeader0059, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0059 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 261
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [59] <- Index 59
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [60] <- WEB ADMIN Index 63 ---
		String requestProt0060 = "https";
		String requestHost0060 = "book.jetblue.com";
		int    requestPort0060 = 443;
		String requestFile0060 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=BOOKING_PREPAYMENT_BOT";
		String requestHeader0060 = "GET " + requestFile0060 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0060, requestHost0060, requestPort0060, requestHeader0060, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0060 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 198
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [60] <- Index 60
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #4 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #4_1.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_4_1(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #4 (fragment 2 of 2)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [61] <- WEB ADMIN Index 64 ---
		String requestProt0061 = "https";
		String requestHost0061 = "book.jetblue.com";
		int    requestPort0061 = 443;
		String requestFile0061 = "/B6/Merchandizing.do" +
				"?touchPoint=BOOKING_PREPAYMENT_BOT";
		String requestHeader0061 = "GET " + requestFile0061 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0061, requestHost0061, requestPort0061, requestHeader0061, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0061 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 1115
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [61] <- Index 61
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [62] <- WEB ADMIN Index 65 ---
		String requestProt0062 = "https";
		String requestHost0062 = "book.jetblue.com";
		int    requestPort0062 = 443;
		String requestFile0062 = "/B6/Merchandizing.do" +
				"?touchPoint=BOOKING_PREPAYMENT";
		String requestHeader0062 = "GET " + requestFile0062 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0062, requestHost0062, requestPort0062, requestHeader0062, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0062 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 4482
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [62] <- Index 62
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #4_1 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #5.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_5(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// # Page #5: Payment Info
		// # ---------------------



		// --- HTTP REQUEST: Test [64] <- WEB ADMIN Index 67 ---
		log();
		log("# title: JetBlue Airways");
		String requestProt0064 = "https";
		String requestHost0064 = "www.jetblue.com";
		int    requestPort0064 = 443;
		String requestFile0064 = "/content/snippets/interstitials/booking/index5.html" +
				"?version=201702040020";
		String requestHeader0064 = "GET " + requestFile0064 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0064, requestHost0064, requestPort0064, requestHeader0064, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0064 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 674
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [64] <- Index 64
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [65] <- WEB ADMIN Index 68 ---
		String requestProt0065 = "https";
		String requestHost0065 = "book.jetblue.com";
		int    requestPort0065 = 443;
		String requestFile0065 = "/B6/ReservationAddMerchandizingOptionsFromSeats.do";
		String requestContent0065 = 
				"ajaxAction=true" +
				"&vsessionid=";
		String requestHeader0065 = "POST " + requestFile0065 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0065.length() + "\r\n" + 		// Content-Length: 27
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0065, requestHost0065, requestPort0065, requestHeader0065, requestContent0065.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0065 = null;		// support garbage collector to reduce memory
		requestContent0065 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 368
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [65] <- Index 65
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [66] <- WEB ADMIN Index 69 ---
		log();
		log("# title: Payment");
		String requestProt0066 = "https";
		String requestHost0066 = "pay.jetblue.com";
		int    requestPort0066 = 443;
		String requestFile0066 = "/B6/PaymentForward.do";
		String requestHeader0066 = "GET " + requestFile0066 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: pay.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0066, requestHost0066, requestPort0066, requestHeader0066, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0066 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 64117
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [66] <- Index 66
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [67] <- WEB ADMIN Index 70 ---
		String requestProt0067 = "https";
		String requestHost0067 = "pay.jetblue.com";
		int    requestPort0067 = 443;
		String requestFile0067 = "/B6/ValidateFlow.do";
		String requestContent0067 = 
				"validator=PAYMENT";
		String requestHeader0067 = "POST " + requestFile0067 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: pay.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0067.length() + "\r\n" + 		// Content-Length: 17
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0067, requestHost0067, requestPort0067, requestHeader0067, requestContent0067.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0067 = null;		// support garbage collector to reduce memory
		requestContent0067 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 78
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [67] <- Index 67
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [68] <- WEB ADMIN Index 71 ---
		String requestProt0068 = "https";
		String requestHost0068 = "pay.jetblue.com";
		int    requestPort0068 = 443;
		String requestFile0068 = "/B6/SelectFormOfPayment.do";
		String requestContent0068 = 
				"afterUpdateHandler=" +
				"&formOfPayment(TRAVELBANK).selected=false" +
				"&formOfPayment(TRAVELBANK).selectedByDefault=false" +
				"&formOfPayment(CREDITCARD_POS).selected=true" +
				"&formOfPayment(CREDITCARD_POS).selectedByDefault=false" +
				"&formOfPayment(MASTERPASS).selected=false" +
				"&formOfPayment(MASTERPASS).selectedByDefault=false" +
				"&formOfPayment(PAYPAL).selected=false" +
				"&formOfPayment(PAYPAL).selectedByDefault=false" +
				"&formOfPayment(PAYPALCREDIT).selected=false" +
				"&formOfPayment(PAYPALCREDIT).selectedByDefault=false" +
				"&deliveryRule=ETKT" +
				"&fop=TRAVELBANK" +
				"&fopId=TRAVELBANK";
		String requestHeader0068 = "POST " + requestFile0068 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: pay.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0068.length() + "\r\n" + 		// Content-Length: 527
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0068, requestHost0068, requestPort0068, requestHeader0068, requestContent0068.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0068 = null;		// support garbage collector to reduce memory
		requestContent0068 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 391
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [68] <- Index 68
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [69] <- WEB ADMIN Index 72 ---
		String requestProt0069 = "https";
		String requestHost0069 = "pay.jetblue.com";
		int    requestPort0069 = 443;
		String requestFile0069 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=PAYMENT_TOP";
		String requestHeader0069 = "GET " + requestFile0069 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: pay.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0069, requestHost0069, requestPort0069, requestHeader0069, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0069 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 240
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [69] <- Index 69
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [70] <- WEB ADMIN Index 73 ---
		String requestProt0070 = "https";
		String requestHost0070 = "pay.jetblue.com";
		int    requestPort0070 = 443;
		String requestFile0070 = "/B6/UpdateCreditCard.do";
		String requestContent0070 = 
				"type=" +
				"&fop=CREDITCARD" +
				"&fopType=POS" +
				"&fopSequence=" +
				"&fopId=CREDITCARD_POS" +
				"&creditCard.type=" +
				"&creditCard.code=CREDITCARD" +
				"&creditCard.fopType=POS" +
				"&creditCard.fopSequence=";
		String requestHeader0070 = "POST " + requestFile0070 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: pay.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0070.length() + "\r\n" + 		// Content-Length: 157
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0070, requestHost0070, requestPort0070, requestHeader0070, requestContent0070.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");
		if (debugLoops)
		{
			if (Lib.isAsciiContent(testURL.getRequestContent(), 256))
				log(">>> " + new String(testURL.getRequestContent()));
			else
				log(">>> [binary data]");
		}

		testURL.execute(performanceData);
		requestHeader0070 = null;		// support garbage collector to reduce memory
		requestContent0070 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 345
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [70] <- Index 70
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [71] <- WEB ADMIN Index 74 ---
		String requestProt0071 = "https";
		String requestHost0071 = "pay.jetblue.com";
		int    requestPort0071 = 443;
		String requestFile0071 = "/B6/Merchandizing.do" +
				"?touchPoint=PAYMENT_TOP";
		String requestHeader0071 = "GET " + requestFile0071 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: pay.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/javascript, */*; q=0.01\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"X-Requested-With: XMLHttpRequest\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0071, requestHost0071, requestPort0071, requestHeader0071, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0071 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 3198
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [71] <- Index 71
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #5 successful done
		return true;
	}



	boolean synchResponsesParallelRequestsPage_1(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		int lastThreadStepInMainThread = threadStep;		// save last executed thread step
		
		// wait for the response of all parallel requests
		threadStep = pageThreadHandler.getLastThreadStep();
		pageThreadHandler.waitForSynch();
		log("Page 1 Time = " + pageThreadHandler.getPageTime() + " ms");
		
		
		// all done
		threadStep = lastThreadStepInMainThread;		// restore last executed thread step
		return true;		// end of asynch response checks for this page
	}



	boolean synchResponsesParallelRequestsPage_2(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		int lastThreadStepInMainThread = threadStep;		// save last executed thread step
		
		// wait for the response of all parallel requests
		threadStep = pageThreadHandler.getLastThreadStep();
		pageThreadHandler.waitForSynch();
		log("Page 2 Time = " + pageThreadHandler.getPageTime() + " ms");
		
		
		// all done
		threadStep = lastThreadStepInMainThread;		// restore last executed thread step
		return true;		// end of asynch response checks for this page
	}



	boolean synchResponsesParallelRequestsPage_3(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		int lastThreadStepInMainThread = threadStep;		// save last executed thread step
		
		// wait for the response of all parallel requests
		threadStep = pageThreadHandler.getLastThreadStep();
		pageThreadHandler.waitForSynch();
		log("Page 3 Time = " + pageThreadHandler.getPageTime() + " ms");
		
		
		// all done
		threadStep = lastThreadStepInMainThread;		// restore last executed thread step
		return true;		// end of asynch response checks for this page
	}



	boolean synchResponsesParallelRequestsPage_4(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		int lastThreadStepInMainThread = threadStep;		// save last executed thread step
		
		// wait for the response of all parallel requests
		threadStep = pageThreadHandler.getLastThreadStep();
		pageThreadHandler.waitForSynch();
		log("Page 4 Time = " + pageThreadHandler.getPageTime() + " ms");
		
		
		// all done
		threadStep = lastThreadStepInMainThread;		// restore last executed thread step
		return true;		// end of asynch response checks for this page
	}



	boolean synchResponsesParallelRequestsPage_5(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		int lastThreadStepInMainThread = threadStep;		// save last executed thread step
		
		// wait for the response of all parallel requests
		threadStep = pageThreadHandler.getLastThreadStep();
		pageThreadHandler.waitForSynch();
		log("Page 5 Time = " + pageThreadHandler.getPageTime() + " ms");
		
		
		// all done
		threadStep = lastThreadStepInMainThread;		// restore last executed thread step
		return true;		// end of asynch response checks for this page
	}



	/**
	 * implement GetRealTimeUserInputFieldsInterface: get the definition and the value of all real-time user input fields.
	 */
	public ArrayList<RealTimeUserInputField> getRealTimeUserInputFields()
	{
		try
		{
			ArrayList<RealTimeUserInputField> realTimeUserInputFieldList = new ArrayList<RealTimeUserInputField>();
			return realTimeUserInputFieldList;
		}
		catch (Exception e)
		{
			log(e);
			return null;
		}
	}



	/**
	 * implement ThreadStepInterface: get the current execution step (current URL call or page break) of a simulated user.
	 */
	public int getExecutionStep()
	{
		return threadStep;
	}



	/**
	 * implement SetThreadStepInterface: set the current execution step (current URL call or page break) of a simulated user.
	 */
	public void setExecutionStep(int threadStep)
	{
		this.threadStep = threadStep;
	}



	/**
	 * implement SSLSessionCacheStatisticInterface: get statistic data about the SSL session cache behavior of a simulated user.
	 */
	public SSLSessionCacheStatistic getSSLSessionCacheStatistic()
	{
		return sslStatistic;
	}



	/**
	 * implement VaryingTestDurationInterface: support to extend or to reduce the planned test duration.
	 */
	public int getPlannedTestDuration()
	{
		return plannedTestDuration;
	}
	
	public void setExtendTestDuration(int deltaSeconds)
	{
		if (plannedTestDuration == 0)
		{
			plannedTestDuration = deltaSeconds;		// the old planned test duration was unlimited but is now limited
			return;
		}
		if (deltaSeconds == 0)
		{
			plannedTestDuration = 0;		// the new planned test duration is now unlimited
			return;
		}
		plannedTestDuration = plannedTestDuration + deltaSeconds;
	}



	/**
	 * implement SuspendResumeInterface: support to suspend and to resume the test execution.
	 */
	public int getPlannedStartupDelay()
	{
		return plannedStartupDelay;
	}
	
	public void setUserResumeStartWaitDelay(int millis)
	{
		userResumeStartWaitDelay = millis;
	}



	/**
	 * implement VaryingLoadInterface: support to decrement the number of simulated users at runtime.
	 */
	public void setDecrementEndOfLoopFlag(boolean decrementEndOfLoopFlag)
	{
		this.decrementEndOfLoopFlag = decrementEndOfLoopFlag;
	}

	public boolean isDecrementEndOfLoopFlag()
	{
		return decrementEndOfLoopFlag;
	}



	/**
	 * internal method: execute the loops for one simulated user as a thread.
	 * controls the thread and displays the (thread-)log if one loop has been completed.
	 */
	public void run()
	{
		// user terminated by inline script, at start of user ?
		if (isTerminateUser())
		{
			System.out.println("# --- thread " + Thread.currentThread().getName() + " aborted --- " + ZoneTime.dateToShortString() + " ---");
			return;
		}
		try
		{
			while (true)
			{
				threadStep = ThreadStepInterface.THREAD_NO_STEP;
				clearLog();
				
				// execute loop
				log("--- loop started --- " + ZoneTime.dateToShortString() + " ---");
				boolean loopPassed = this.execute(totalLoopCounter.getAndIncrement());
				performanceData.addUserTransactionMeasuredSamples(transactionHandler.getPendingTransactions());
				performanceData.addUserTransactionNotExecutedSamples(transactionHandler.getNotExecutedTransactions());
				transactionHandler.resetTransactions();
				performanceData.addSocktPoolStatistic(socketPool);
				socketPool.closePool();
				performanceData.addSSLCacheStatistic(sslStatistic.getSSLSessionCacheStatisticResult());
				sslStatistic.reset();
				
				// eof of input file ?
				if (abortedByEofInputFile())
					return;		// endOfRun() not called in such a case
				
				// check loop result
				String remainingLoopInfo = "";
				if (checkLoopCount)
					remainingLoopInfo = " [remaining loops = " + (remainingLoops - 1) + "]";
				if (loopPassed)
				{
					performanceData.addPassedLoop();
					log("--- loop passed ---  " + ZoneTime.dateToShortString() + " ---" + remainingLoopInfo);
				}
				else
				{
					performanceData.addFailedLoop();
					log("--- loop failed ---  " + ZoneTime.dateToShortString() + " ---" + remainingLoopInfo);
				}
				threadStep = ThreadStepInterface.THREAD_NO_STEP;
				
				// display thread log to standard output
				if (debugLoops || (debugFailedLoops && (!loopPassed)))
				{
					synchronized (dumpLock)
					{
						dumpLog(System.out);				// full log
					}
				}
				
				// check if max loops reached
				if (checkLoopCount)
				{
					// all done ?
					remainingLoops--;
					if (remainingLoops <= 0)
					{
						endOfRun();
						return;
					}
				}
				
				// check if simulated user must be decremented
				if (decrementEndOfLoopFlag)
				{
					endOfRun();
					decrementEndOfLoopFlag = false; // reset flag for further use
					return;
				}
				
				// check if max test duration reached
				if ((plannedTestDuration > 0) && (((System.currentTimeMillis() - testDurationStart) / 1000) >= plannedTestDuration))
				{
					endOfRun();
					return;
				}
				
				// check if this specific user is terminated earlier than planned by a HTTP content verification or by a plug-in
				if (isTerminateUser())
				{
					endOfRun();
					System.out.println("# --- thread " + Thread.currentThread().getName() + " terminate failed user --- " + ZoneTime.dateToShortString() + " ---");
					return;
				}
				
				// check if load test execution is temporary suspended
				boolean wasSuspended = false;
				while (isSuspend())
				{
					wasSuspended = true;
					try { Thread.currentThread().sleep(20); } catch (InterruptedException ie) {}
					if (abortedByRemote() || abortedByEofInputFile())
					{
						endOfRun();
						return;
					}
					
					// check if max test duration reached during suspend
					if ((plannedTestDuration > 0) && (((System.currentTimeMillis() - testDurationStart) / 1000) >= plannedTestDuration))
					{
						endOfRun();
						return;
					}
				}
				// check if load test execution is resumed
				if (wasSuspended)
				{
					try { sleepRemoteInterruptable(userResumeStartWaitDelay); } catch (InterruptedException ie) {}
				}
				
				// wait 20 milliseconds if loop has failed
				if (!loopPassed)
					try { Thread.currentThread().sleep(loopErrorDelay); } catch (InterruptedException ie) {}
				
				// execute next loop
				threadLoopCounter++;
			}	// end: while (true)
		}
		catch (Throwable tex)
		{
			log("*** INTERNAL ERROR / LOAD TEST ABORTED ***");
			log(tex);
			log();
			synchronized (dumpLock)
			{
				dumpLog(System.out);				// full log
				System.err.println("*** INTERNAL ERROR / LOAD TEST ABORTED ***");
				tex.printStackTrace(System.err);
				System.exit(-2);
			}
		}
		finally
		{
			// remove the reference to this load test instance and the reference to the thread that runs this instance
			try
			{
				getUserContextTable().getWriteLock().lock();
				getOwnLoadTestUserContext().setLoadTestUserFinallyEnded();
				getUserContextTable().getWriteLock().unlock();
			}
			catch (Throwable texFinal)
			{
				System.err.println("*** INTERNAL FINALLY ERROR / LOAD TEST ABORTED ***");
				texFinal.printStackTrace(System.err);
				System.exit(-2);
			}
		}
	}



	/**
	 * internal method: called when a user has completed the test-run.
	 */
	public void endOfRun()
	{
		clearLog();
		
		if (debugLoops && (getLog().size() > 0))
		{
			synchronized (dumpLock)
			{
				dumpLog(System.out);		// dump log of inline scripts and load test plug-ins which are executed at end of user
			}
		}
	}



	/**
	 * Main program. Starts the test and waits until all have been done.
	 */
	public static void main(String[] args)
	{
		// check command line argument -h or -help
		if ((ParseArgs.hasOption(args, "-h")) || (ParseArgs.hasOption(args, "-help")))
		{
			System.out.println();
			System.out.println("Help - Proxy Sniffer Load Test Program:");
			System.out.println();
			System.out.println("-u <number>              ->> required argument: number of concurrent users");
			System.out.println("-d <seconds>             ->> required argument: planned test duration in seconds (default: 30, 0 = unlimited)");
			System.out.println("-t <seconds>             ->> required argument: request timeout per url in seconds");
			System.out.println();
			System.out.println("-sdelay <milliseconds>   ->> startup delay time between concurrent users in milliseconds (default: 200)");
			System.out.println("-mtpu <number>           ->> maximum number of parallel threads per user (default: " + MAX_PARALLEL_THREADS_PER_USER + ")");
			System.out.println("-maxloops <number>       ->> maximum number of loops per user (default: 0 = unlimited)");
			System.out.println("-downlink <kbps>         ->> maximum downlink network bandwidth per user in kilobits per second (default: 0 = unlimited)");
			System.out.println("-uplink <kbps>           ->> maximum uplink network bandwidth per user in kilobits per second (default: 0 = unlimited)");
			System.out.println("-multihomed <filename>   ->> use serveral client ip addresses - file format: <addr1>, <addr2>, ... (all on the same line)");
			System.out.println("-sampling <seconds>      ->> statistic sampling interval in seconds (default: 15)");
			System.out.println("-percpage <percent>      ->> additional sampling rate per web page call in percent (default: 100)");
			System.out.println("-percurl <percent>       ->> additional sampling rate per url call in percent (default: 0)");
			System.out.println("-percurlopt <level>      ->> extended sampling level per url call, see application reference manual (default: 0 = disabled)");
			System.out.println("-maxerrsnap <number>     ->> maximum number of error snapshots per url (default: 0 = unlimited)");
			System.out.println("-maxerrmem <megabytes>   ->> maximum size of memory in megabytes which can be used for error snapshots (default: 20, -1 = unlimited)");
			System.out.println("-nosdelayCluster         ->> apply startup delay time between concurrent users per exec agent, but not per cluster job (default: apply per cluster job)");
			System.out.println("-setuseragent \"<text>\"   ->> replace the recorded value of the HTTP request header field User-Agent with a new value");
			System.out.println("-collect <host>[:<port>] ->> collect additional data from external measuring agents (data collectors)");
			System.out.println("-res <filename>          ->> overrides the default name of the binary output file");
			System.out.println("-nores                   ->> disables to create the binary output file");
			System.out.println();
			System.out.println("-ssl <version>           ->> set SSL version: possible options are \"all\" (default), \"v3\", \"tls\", \"tls11\" or \"tls12\"");
			System.out.println("-sslcache <seconds>      ->> timeout of user-related SSL session cache (default: 300, 0 = cache disabled)");
			System.out.println("-sslrandom <type>        ->> set the type of the random generator used for SSL handshakes: possible options are \"fast\", \"iaik\" (default) or \"java\"");
			System.out.println("-sslcmode                ->> apply SSL/HTTPS compatibility workarounds for deficient SSL servers");
			System.out.println("-ecc                     ->> enable support of elliptic curve cryptography (ECC)");
			System.out.println("-nosni                   ->> disable support of server name indication (SNI)");
			System.out.println("-snicritical             ->> set the TLS SNI extension as critical (default: non-critical)");
			System.out.println();
			System.out.println("-dnssrv <IP-1>[,IP-N])   ->> use specific DNS server(s) to resolve DNS host names (default: use OS to resolve host names)");
			System.out.println("-dnshosts <filename>     ->> use specific DNS hosts file (default: use OS to resolve host names)");
			System.out.println("-dnstranslation <filename> ->> use a DNS translation file that converts DNS names. It might be needed to disable TLS SNI if this option is used");
			System.out.println("-dnsenattl               ->> enable consideration of DNS TTL by using the received TTL-values from the DNS Server(s) (default: TTL disabled)");
			System.out.println("-dnsfixttl <seconds>     ->> enable DNS TTL by using a fixed TTL-value of seconds for all DNS resolves");
			System.out.println("-dnsperloop              ->> perform new DNS resolves for each executed loop. All resolves are stable within the same loop (no consideration of DNS TTL within a loop)");
			System.out.println("-dnsstatistic            ->> collect statistical data about DNS resolutions. Note: use this option only if not any other, more specific DNS option is enabled");
			System.out.println("-dnsdebug                ->> debug DNS resolves and the DNS cache");
			System.out.println();
			System.out.println("-dfl                     ->> debug execution steps of all failed loops to standard output");
			System.out.println("-dl                      ->> debug execution steps of all loops to standard output");
			System.out.println("-dh                      ->> debug HTTP protocol headers to standard output, includes the -dl option");
			System.out.println("-dc                      ->> debug HTTP content data to standard output, includes the -dl option");
			System.out.println("-dhc                     ->> debug HTTP protocol headers and HTTP content data to standard output, includes the -dl option");
			System.out.println("-dC                      ->> debug cookies to standard output, includes the -dl option");
			System.out.println("-dK                      ->> debug keep-alive (socket pool) to standard output, includes the -dl option");
			System.out.println("-dssl                    ->> debug SSL handshake (https) to standard output, includes the -dl and the -dK option");
			System.out.println();
			System.out.println("-tz <timezone>           ->> set time zone (see Application Reference Manual: supported time zones)");
			System.out.println("-dgs a|c                 ->> set number format (decimal group separator) a = '  c = ,");
			System.out.println("-annotation <text>       ->> adds an annotation for this test run");
			System.out.println();
			System.out.println("-execAgentHost <ip address or dns name>      ->> set the ip address or the dns name of the exec agent from which the license is used (default: 127.0.0.1)");
			System.out.println("-execAgentPort <port>                        ->> set the tcp/ip port of the exec agent (default: 7993)");
			System.out.println("-execAgentProtocol <plain | http | https>    ->> set the protocol of the exec agent (default: plain)");
			System.out.println("-execAgentUsername <string>                  ->> set the auth. username for the exec agent (default: [no username])");
			System.out.println("-execAgentPassword <string>                  ->> set the auth. password for the exec agent (default: [no password])");
			System.out.println();
			System.out.println("-h                       ->> display this help text");
			System.out.println("-help                    ->> display this help text");
			System.out.println();
			System.exit(-1);
		}
		
		System.out.println("+----------------------------------------------------------------+");
		System.out.println("| Welcome to the ZebraTester Load Test Program.                  |");
		System.out.println("| Additional help available with program argument \"-help\"        |");
		System.out.println("| Procedure Copyright by Ingenieurbuero David Fischer AG,        |");
		System.out.println("| a company of the Apica group. All rights reserved.             |");
		System.out.println("+----------------------------------------------------------------+");
		
		// check command line argument -ecc
		sslECC = (ParseArgs.hasOption(args, "-ecc"));       // enable ssl ecc ?
		
		// initialize ssl/https support
		SSLInit.execute();
		if (sslECC)
			SSLInit.enableECC();
		
		// check command line argument -nosni
		if (ParseArgs.hasOption(args, "-nosni"))            // disable ssl sni ?
			sslSNI = false;
		
		// check command line argument -snicritical
		if (ParseArgs.hasOption(args, "-snicritical"))      // set ssl sni as critical tls extension ?
			sslSNICirical = true;
		
		// set default character set for response content tests
		setCharEncoding(prxCharEncoding);
		
		// check command line argument -tz <timezone>
		String timeZoneString = ParseArgs.getString(args, "-tz");
		if (timeZoneString != null)
			defaultTimeZone = timeZoneString.toUpperCase(); // set time zone?
		ZoneTime.setDefaultTimeZone(defaultTimeZone);
		
		// check command line argument -dgs a|c
		String dgs = ParseArgs.getString(args, "-dgs");
		if (dgs != null)
		{
			if (dgs.equalsIgnoreCase("a"))
				defaultNumberGroupingSeparator = '\'';
			if (dgs.equalsIgnoreCase("c"))
				defaultNumberGroupingSeparator = ',';
		}
		Lib.setDefaultNumberGroupingSeparator(defaultNumberGroupingSeparator);
		
		// check command line argument -annotation <text>
		String testRunAnnotation = ParseArgs.getString(args, "-annotation");
		if (testRunAnnotation != null)
		{
			if (testRunAnnotation.startsWith("\"") && testRunAnnotation.endsWith("\""))
				testRunAnnotation = testRunAnnotation.substring(1, testRunAnnotation.length() - 1);
		}
		
		// check command line argument -ssl
		String newSslProtocolVersion = ParseArgs.getString(args, "-ssl");
		if (newSslProtocolVersion != null)
		{
			if (newSslProtocolVersion.equalsIgnoreCase("v2"))
				sslProtocolVersion = "v2";
			if (newSslProtocolVersion.equalsIgnoreCase("v3"))
				sslProtocolVersion = "v3";
			if (newSslProtocolVersion.equalsIgnoreCase("tls"))
				sslProtocolVersion = "tls";
			if (newSslProtocolVersion.equalsIgnoreCase("tls11"))
				sslProtocolVersion = "tls11";
			if (newSslProtocolVersion.equalsIgnoreCase("tls12"))
				sslProtocolVersion = "tls12";
		}
		
		// check command line argument -sslcache <seconds>
		Integer newSslSessionCacheTimeout = ParseArgs.getInteger(args, "-sslcache");
		if (newSslSessionCacheTimeout != null)
			sslSessionCacheTimeout = newSslSessionCacheTimeout.intValue();
		
		// check command line argument -sslrandom
		String sslHandshakeRandomGeneratorTypeStr = ParseArgs.getString(args, "-sslrandom");
		if (sslHandshakeRandomGeneratorTypeStr != null)
		{
			if (sslHandshakeRandomGeneratorTypeStr.equalsIgnoreCase("java"))
				sslHandshakeRandomGeneratorType = HttpSocketPool.SSL_HANDSHAKE_RANDOM_GENERATOR_TYPE_JAVA_DEFAULT;
			if (sslHandshakeRandomGeneratorTypeStr.equalsIgnoreCase("iaik"))
				sslHandshakeRandomGeneratorType = HttpSocketPool.SSL_HANDSHAKE_RANDOM_GENERATOR_TYPE_IAIK_DEFAULT;
			if (sslHandshakeRandomGeneratorTypeStr.equalsIgnoreCase("fast"))
				sslHandshakeRandomGeneratorType = HttpSocketPool.SSL_HANDSHAKE_RANDOM_GENERATOR_TYPE_FAST;
		}
		
		// check command line argument -sslcmode
		sslcmode = ParseArgs.hasOption(args, "-sslcmode");  // enable ssl/https compatibility workarounds?
		
		// check command line argument -dfl
		debugFailedLoops = ParseArgs.hasOption(args, "-dfl"); // debug failed loops?
		
		// check command line argument -dl
		debugLoops = ParseArgs.hasOption(args, "-dl");      // debug loops?
		
		// check command line argument -dh
		debugHttp = ParseArgs.hasOption(args, "-dh");       // debug http?
		if (debugHttp)
			debugLoops = true;
		
		// check command line argument -dc
		debugContent = ParseArgs.hasOption(args, "-dc");    // debug content?
		if (debugContent)
			debugLoops = true;
		
		// check command line argument -dhc
		if (ParseArgs.hasOption(args, "-dhc"))				// debug http and content
		{
			debugLoops = true;
			debugHttp = true;
			debugContent = true;
		}
		
		// check command line argument -dC
		debugCookies = ParseArgs.hasOption(args, "-dC");    // debug cookies?
		if (debugCookies)
			debugLoops = true;
		
		// check command line argument -dK
		debugKeepAlive = ParseArgs.hasOption(args, "-dK");  // debug keep.alive (socket pool)?
		if (debugKeepAlive)
			debugLoops = true;
		
		// check command line argument -dssl
		debugSsl = ParseArgs.hasOption(args, "-dssl");      // debug keep.alive (socket pool)?
		if (debugSsl)
		{
			debugLoops = true;
			debugKeepAlive = true;
		}
		
		// get startup delay
		Integer newStartupDelay = ParseArgs.getInteger(args, "-sdelay");
		if (newStartupDelay != null)
			plannedStartupDelay = newStartupDelay.intValue();
		
		// get max. parallel threads per user - note: only valid if CONTAINS_PARALLEL_EXECUTED_URLS = true
		Integer newMaxParallelThreadsPerUser = ParseArgs.getInteger(args, "-mtpu");
		if (newMaxParallelThreadsPerUser != null)
		{
			if (newMaxParallelThreadsPerUser.intValue() > 0)
				maxParallelThreadsPerUser = newMaxParallelThreadsPerUser.intValue();
		}
		// get maximum number of loops per user
		maxPlannedLoops = 0;  // maximum loops per user, 0 = unlimited
		Integer newLoops = ParseArgs.getInteger(args, "-maxloops");
		if (newLoops != null)
			maxPlannedLoops = newLoops.intValue();
		
		// get maximum downlink network bandwidth per user (default = unlimited)
		Integer newDownlinkBandwidth = ParseArgs.getInteger(args, "-downlink");
		if (newDownlinkBandwidth != null)
			downlinkBandwidth = newDownlinkBandwidth.intValue();
		
		// get maximum uplink network bandwidth per user (default = unlimited)
		Integer newUplinkBandwidth = ParseArgs.getInteger(args, "-uplink");
		if (newUplinkBandwidth != null)
			uplinkBandwidth = newUplinkBandwidth.intValue();
		
		// use test-specific DNS hosts file (optional) ?
		String dnsHostsFile = ParseArgs.getIgnoreCaseString(args, "-dnshosts");
		if (dnsHostsFile != null)
		{
			try
			{
				dnsCache = new DNSCache(dnsHostsFile);
			}
			catch (IOException ie)
			{
				System.out.println("*** ERROR: unable to read DNS hosts file " + dnsHostsFile + " ***");
				System.out.println("*** Hint: you have to ZIP " + dnsHostsFile + " together with the compiled class of the load test program ***");
				ie.printStackTrace();
				System.exit(-1);
			}
		}
		
		// use test-specific DNS translation table (optional) ?
		String dnsTranslationTableFile = ParseArgs.getIgnoreCaseString(args, "-dnstranslation");
		if (dnsTranslationTableFile != null)
		{
			try
			{
				DNSTranslationTable dnsTranslationTable = new DNSTranslationTable(new File(dnsTranslationTableFile));
				if (dnsCache == null)
					dnsCache = new DNSCache();
				dnsCache.setDNSTranslationTable(dnsTranslationTable);
			}
			catch (IOException ie)
			{
				System.out.println("*** ERROR: unable to read DNS translation table file " + dnsTranslationTableFile + " ***");
				System.out.println("*** Hint: you have to ZIP " + dnsTranslationTableFile + " together with the compiled class of the load test program ***");
				ie.printStackTrace();
				System.exit(-1);
			}
		}
		
		// use test-specific DNS servers (optional) ?
		String dnsSrvStr = ParseArgs.getIgnoreCaseString(args, "-dnssrv");
		if (dnsSrvStr != null)
		{
			ArrayList<String> dnsSrvList = new ArrayList<String>();
			StringTokenizer dnsTok = new StringTokenizer(dnsSrvStr, ",;");
			while (dnsTok.hasMoreTokens())
				dnsSrvList.add(dnsTok.nextToken());
			if (dnsCache == null)
				dnsCache = new DNSCache(dnsSrvList);
			else
				dnsCache.setDnsServers(dnsSrvList);
		}
		
		// enable DNS TTL ?
		if (ParseArgs.hasOption(args, "-dnsenattl"))
		{
			if (dnsCache == null)
				dnsCache = new DNSCache();
			dnsCache.enableTTL();
		}
		
		// set fixed DNS TTL ?
		Integer dnsFixTTL = ParseArgs.getInteger(args, "-dnsfixttl");
		{
			if (dnsFixTTL != null)
			{
				if (dnsCache == null)
					dnsCache = new DNSCache();
				dnsCache.setFixedTTL(dnsFixTTL.intValue());
			}
		}
		
		// enable DNS resolves per loop ?
		if (ParseArgs.hasOption(args, "-dnsperloop"))
		{
			if (dnsCache == null)
				dnsCache = new DNSCache();
			dnsPerLoop = true;
		}
		
		// enable DNS statistic ?   // note: use this option only if not any other, more specific DNS option is enabled.
		if (ParseArgs.hasOption(args, "-dnsstatistic"))
		{
			if (dnsCache == null)
				dnsCache = new DNSCache();
		}
		
		// debug DNS resolver ?
		if (ParseArgs.hasOption(args, "-dnsdebug"))
		{
			if (dnsCache == null)
				dnsCache = new DNSCache();
			dnsCache.setDebugToStdout(true);
		}
		
		// get statistic sampling interval
		int samplingInterval = 15;  // statistic sampling interval in seconds
		Integer newSamplingInterval = ParseArgs.getInteger(args, "-sampling");
		if (newSamplingInterval != null)
			samplingInterval = newSamplingInterval.intValue();
		
		// get additional sampling rate per web page call
		int percentilePageSampling = 100;  // additional sampling rate per web page call in percent
		Integer newPercentilePageSampling = ParseArgs.getInteger(args, "-percpage");
		if (newPercentilePageSampling != null)
			percentilePageSampling = newPercentilePageSampling.intValue();
		
		// get additional sampling rate per url call
		int percentileUrlSampling = 0;  // additional sampling rate per url call in percent
		Integer newPercentileUrlSampling = ParseArgs.getInteger(args, "-percurl");
		if (newPercentileUrlSampling != null)
			percentileUrlSampling = newPercentileUrlSampling.intValue();
		
		// get extended sampling level per url call
		int percentileUrlSamplingAddOption = 0;  // extended sampling level per url call, 0 = disabled
		Integer newPercentileUrlSamplingAddOption = ParseArgs.getInteger(args, "-percurlopt");
		if (newPercentileUrlSamplingAddOption != null)
			percentileUrlSamplingAddOption = newPercentileUrlSamplingAddOption.intValue();
		
		// get maximum number of error snapshots per URL (0 = unlimited)
		int maxErrorSnapshots = 0;
		Integer newMaxErrorSnapshots = ParseArgs.getInteger(args, "-maxerrsnap");
		if (newMaxErrorSnapshots != null)
			maxErrorSnapshots = newMaxErrorSnapshots.intValue();
		
		// get maximum memory in megabytes which can be used for error snapshots (-1 = unlimited, default = 10)
		long maxErrorSnapshotMemory = 20;
		Long newMaxErrorSnapshotMemory = ParseArgs.getLong(args, "-maxerrmem");
		if (newMaxErrorSnapshotMemory != null)
			maxErrorSnapshotMemory = newMaxErrorSnapshotMemory.longValue();
		
		// single user mode ?
		boolean singleUserMode = ParseArgs.hasOption(args, "-singleuser");
		
		// override value for http user agent field ?
		String setUserAgentStr = ParseArgs.getIgnoreCaseString(args, "-setuseragent");
		if (setUserAgentStr != null)
		{
			USER_AGENT_1 = setUserAgentStr;
		}
		// get required input arguments
		int concurrentUsers = 0;     // number of concurrent users
		plannedRequestTimeout = 0;   // request timeout in seconds
		
		System.out.println();
		
		// parse -u argument or ask --> <number of concurrent users>
		Integer i = ParseArgs.getIntegerOrAsk(args, "-u", "Concurrent Users, <RETURN>=1 : ", new Integer(1));
		if (i == null)
			System.exit(-1);
		else
			concurrentUsers = i.intValue();
		
		// parse -d argument or ask --> <test duration in seconds>
		i = ParseArgs.getIntegerOrAsk(args, "-d", "Test Duration in Seconds, <RETURN>=30 : ", new Integer(30));
		if (i == null)
			System.exit(-1);
		else
			plannedTestDuration = i.intValue();
		
		// parse -t argument or ask --> <request timeout in seconds>
		i = ParseArgs.getIntegerOrAsk(args, "-t", "HTTP Request Timeout per URL in Seconds, <RETURN>=60 : ", new Integer(60));
		if (i == null)
			System.exit(-1);
		else
			plannedRequestTimeout = i.intValue();
		
		String genericFileName = PerformanceData.proposeFileName("A2scenarioV1", concurrentUsers);
		
		// auto-configure binary result file
		if (!ParseArgs.hasOption(args, "-nores"))
		{
			String newResultFile = ParseArgs.getString(args, "-res");
			if (newResultFile == null)
				resultFile = genericFileName + ".prxres";
			else
				resultFile = newResultFile;
			System.out.println("Result File : " + resultFile);
		}
		
		// display common arguments at console output
		System.out.println();
		System.out.println("# concurrent users = " + concurrentUsers);
		System.out.println("# max. parallel threads per user = [serial execution order for all URLs]");
		System.out.print("# planned test duration = ");
		if (plannedTestDuration == 0)
			System.out.println("unlimited");
		else
			System.out.println("" + plannedTestDuration + " seconds");
		System.out.println("# http request timeout = " + plannedRequestTimeout + " seconds");
		System.out.println("# startup delay = " + plannedStartupDelay + " milliseconds");
		System.out.println("# statistic sampling interval = " + samplingInterval + " seconds");
		System.out.println("# additional sampling rate per web page call = " + percentilePageSampling + " %");
		System.out.println("# additional sampling rate per url call = " + percentileUrlSampling + " %");
		System.out.println("# extended sampling per url call = " + PerformanceDataTickExtension.extTypeToString(percentileUrlSamplingAddOption).toLowerCase());
		System.out.print("# max loops per user = ");
		if (maxPlannedLoops == 0)
			System.out.println("unlimited");
		else
			System.out.println("" + maxPlannedLoops + " loops");
		if (downlinkBandwidth > 0)
			System.out.println("# max downlink bandwidth per user = " + downlinkBandwidth + " kbps");
		if (uplinkBandwidth > 0)
			System.out.println("# max uplink bandwidth per user = " + uplinkBandwidth + " kbps");
		System.out.println("# http protocol version = v" + httpProtocolVersion);
		System.out.println("# ssl protocol version = " + sslProtocolVersion);
		if (sslSessionCacheTimeout != 0)
			System.out.println("# ssl session cache timeout = " + sslSessionCacheTimeout + " seconds");
		else
			System.out.println("# ssl session cache disabled");
		if (dnsCache != null)
		{
			System.out.println("# OS-independent DNS access enabled. " + dnsCache.getConfigInfoText());
			if (dnsPerLoop)
				System.out.println("# DNS option -dnsperloop enabled");
		}
		if (resultFile != null)
			System.out.println("# result file = " + resultFile);
		else
			System.out.println("# no result file");
		if (debugFailedLoops)
			System.out.println("# debug failed loops");
		if (debugLoops)
			System.out.println("# debug loops");
		if (debugHttp)
			System.out.println("# debug http protocol headers");
		if (debugContent)
			System.out.println("# debug http content data");
		if (debugCookies)
			System.out.println("# debug cookies");
		if (debugKeepAlive)
			System.out.println("# debug keep-alive (socket pool)");
		if (debugSsl)
			System.out.println("# debug ssl (https)");
		if (debugLoops || debugFailedLoops)
		{
			System.out.println("# global var DepartureDate = " + DepartureDate);
			System.out.println("# global var ReturnDate = " + ReturnDate);
			System.out.println("# global var ReturnSeat = " + ReturnSeat);
			System.out.println("# global var SeatKeyword = " + SeatKeyword);
		}
		System.out.println();
		
		
		
		// --------------------------
		// *** start of load test ***
		// --------------------------
		
		
		// initialize performance data
		// ---------------------------
		final int PAGE_BREAKS = 5;  // number of page breaks in execute()  - modify this value if you add or delete some page breaks
		final int MAX_THREAD_STEPS = 67;  // number of URL requests in execute() - modify this value if you add or delete some requests
		performanceData = new PerformanceData(PAGE_BREAKS, MAX_THREAD_STEPS, concurrentUsers, -1, plannedTestDuration, maxPlannedLoops, httpProtocolVersion, plannedStartupDelay, plannedRequestTimeout, samplingInterval, percentilePageSampling, percentileUrlSampling, percentileUrlSamplingAddOption);
		performanceData.setInfoText("A2scenarioV1");
		performanceData.setExecutorsAnnotation(testRunAnnotation);
		performanceData.setResultFileName(resultFile);
		performanceData.setDumpStream(System.out, "# ");
		performanceData.setMaxErrorSnapshots(maxErrorSnapshots);		// 0 = unlimited
		if (maxErrorSnapshotMemory >= 0)
			performanceData.setMaxErrorSnapshotsMemory(maxErrorSnapshotMemory * 1048576l);		// value in bytes
		
		if (dnsCache != null)
		{
			performanceData.addDNSCacheStatistic(dnsCache.getCacheStatistic());
			performanceData.addTestDescription("*** Warning: OS-independent DNS access enabled. " + dnsCache.getConfigInfoText() + " ***");
			if (dnsPerLoop)
				performanceData.addTestDescription("*** Warning: DNS option -dnsperloop enabled ***");
		}
		
		if (sslECC)
			performanceData.addTestDescription("*** Warning: SSL/TLS option -ecc enabled ***");
		
		if ((downlinkBandwidth != 0) || (uplinkBandwidth != 0))
		{
			String downlinkBandwidthStr = "unlimited";
			String uplinkBandwidthStr = "unlimited";
			
			if (downlinkBandwidth != 0)
				downlinkBandwidthStr = "" + downlinkBandwidth + " kbps";
			if (uplinkBandwidth != 0)
				uplinkBandwidthStr = "" + uplinkBandwidth + " kbps";
			
			performanceData.addTestDescription("*** Warning: Max. network bandwidth per user limited. downlink = " + downlinkBandwidthStr + ", uplink = " + uplinkBandwidthStr + " ***");
		}
		
		if ((percentileUrlSamplingAddOption != PerformanceDataTickExtension.EXT_TYPE_NOTYPE) && (percentileUrlSampling > 0))
			performanceData.addTestDescription("*** Warning: Option \"" + PerformanceDataTickExtension.extTypeToString(percentileUrlSamplingAddOption).toLowerCase() + "\" enabled for additional sampling rate per URL call ***");
		if (debugFailedLoops)
			performanceData.addTestDescription("*** Warning: Debug failed loops enabled ***");
		if (debugLoops)
			performanceData.addTestDescription("*** Warning: Debug loops enabled ***");
		if (debugHttp)
			performanceData.addTestDescription("*** Warning: Debug HTTP protocol headers enabled ***");
		if (debugContent)
			performanceData.addTestDescription("*** Warning: Debug HTTP content data enabled ***");
		if (debugCookies)
			performanceData.addTestDescription("*** Warning: Debug cookies enabled ***");
		if (debugKeepAlive)
			performanceData.addTestDescription("*** Warning: Debug keep-alive enabled ***");
		if (debugSsl)
			performanceData.addTestDescription("*** Warning: Debug SSL/TLS enabled ***");
		if (!sslProtocolVersion.equalsIgnoreCase("all"))
			performanceData.addTestDescription("*** Warning: SSL/TLS protocol version fixed to " + sslProtocolVersion.toUpperCase() + " ***");
		if (sslSessionCacheTimeout == 0)
			performanceData.addTestDescription("*** Warning: SSL/TLS session cache disabled ***");
		if (singleUserMode)
		{
			String singleUserModeWarning = "*** Warning: Single user mode enabled ***";
			System.out.println(singleUserModeWarning);
			performanceData.addTestDescription(singleUserModeWarning);
		}
		if (setUserAgentStr != null)
		{
			String overrideUserAgentWarning = "*** Warning: User agent set to \"" + setUserAgentStr + "\" ***";
			System.out.println(overrideUserAgentWarning);
			performanceData.addTestDescription(overrideUserAgentWarning);
		}
		
		
		// ready to start load test...
		initNull(concurrentUsers);
		performanceData.setUserContextTable(getUserContextTable());
		
		// ... but init first remote interface ...
		initRemote(args);
		
		// ... and check multihomed option ...
		initMultihomed(args);
		
		// ... and add dynaTrace session ID to load test result - if available ...
		initDynaTrace();
		
		
		// calculate sampling offset and virtual user startup offset for cluster jobs (time shift per cluster member)
		int samplingTimeshift = 0;	// value in seconds
		if (!ParseArgs.hasOption(args, "-nosdelayCluster"))
		{
			if ((getClusterTotalMemberCount() > 1) && (getClusterMemberLocalId() > 0))
			{
				samplingTimeshift = Math.round(((float) samplingInterval / (float) getClusterTotalMemberCount()) * (float) getClusterMemberLocalId());
				System.out.println("# samplingTimeshift = " + samplingTimeshift + " seconds");
				
				long startupDelayOffset = (plannedStartupDelay / ((long) getClusterTotalMemberCount())) * ((long) getClusterMemberLocalId());
				System.out.println("# startupDelayTimeshift = " + startupDelayOffset + " milliseconds");
				System.out.println();
				if (startupDelayOffset > 0)
				{
					try { sleepRemoteInterruptable(startupDelayOffset); } catch (InterruptedException ie) {}
				}
			}
		}
		
		
		// start virtual users as threads
		// ------------------------------
		testDurationStart = System.currentTimeMillis();
		performanceData.setStartDate();
		performanceData.addSnapshot(getCpuUsagePercent());
		performanceData.setSnapshotsTimeshift(samplingTimeshift);
		performanceData.setEndDate();
		
		// initialize all global constructed plug-ins
		LoadtestPluginContext globalPluginContext = new LoadtestPluginContext(prxVersion, prxCharEncoding, new A2scenarioV1());
		try
		{
			// define object for plug-in #2 and call plug-in constructor
			Class pluginClass0 = Class.forName("SelectSeatNumberJetBlue");
			plugin0002 = pluginClass0.newInstance();
			((GenericPluginInterface) plugin0002).construct(globalPluginContext);
		}
		catch (Exception classException)
		{
			System.out.println("*** ERROR: UNABLE TO LOAD CLASS FOR PLUG-IN ***");
			classException.printStackTrace();
			System.exit(-2);
		}
		
		for (int x = 0; x < concurrentUsers; x++)
		{
			// start load test thread
			// ----------------------
			Thread t = null;
			try
			{
				getUserContextTable().getWriteLock().lock();
				
				A2scenarioV1 simulatedUser = new A2scenarioV1(maxPlannedLoops, plannedRequestTimeout, getUserContextTable().getUserContextList().size());
				t = new Thread(simulatedUser);
				String threadName = "" + getUserContextTable().getUserContextList().size();
				threadName = THREAD_NAME.substring(0, 7 - threadName.length()) + threadName;
				t.setName(threadName);
				if (debugLoops && (simulatedUser.getLog().size() > 0))
				{
					synchronized (dumpLock)
					{
						simulatedUser.dumpLog(threadName + " ", System.out);		// dump log of constructor
					}
				}
				HttpLoadTestUserContext userContext = new HttpLoadTestUserContext(simulatedUser, t);
				getUserContextTable().getUserContextList().add(userContext);
				simulatedUser.setOwnLoadTestUserContext(userContext);
			}
			finally
			{
				getUserContextTable().getWriteLock().unlock();
			}
			t.start();
			System.out.println("# --- thread " + t.getName() + " created --- " + ZoneTime.dateToShortString() + " ---");
			
			while (isSuspend())
			{
				try { Thread.currentThread().sleep(100); } catch (InterruptedException ie) { break; }
				if (abortedByRemote() || abortedByEofInputFile())
					break;
				
				// check if max test duration reached during suspend
				if ((plannedTestDuration > 0) && (((System.currentTimeMillis() - testDurationStart) / 1000) >= plannedTestDuration))
					break;
				
				// display and sample temporary performance data all "sampling interval" seconds - also during suspend
				if (((System.currentTimeMillis() - performanceData.getLastSnapshotTime()) / 1000) >= samplingInterval)
				{
					performanceData.addSnapshot(getCpuUsagePercent());
					performanceData.setEndDate();
				}
			}
			
			if (abortedByRemote() || abortedByEofInputFile())
				break;
			
			if ((plannedTestDuration > 0) && (((System.currentTimeMillis() - testDurationStart) / 1000) >= plannedTestDuration))
				break;
			
			// startup delay for next thread
			// -----------------------------
			try
			{
				if (!singleUserMode)   // create concurrent users
				{
					if (plannedStartupDelay <= 3000)
						sleepRemoteInterruptable(plannedStartupDelay);
					else
					{
						long startupDelayStartTime = System.currentTimeMillis();
						while (true)
						{
							if (abortedByRemote() || abortedByEofInputFile())
								break;
							if ((System.currentTimeMillis() - startupDelayStartTime) >= plannedStartupDelay)
								break;
							
							Thread.currentThread().sleep(100);
							
							// display and sample temporary performance data all "sampling interval" seconds
							if (((System.currentTimeMillis() - performanceData.getLastSnapshotTime()) / 1000) >= samplingInterval)
							{
								performanceData.addSnapshot(getCpuUsagePercent());
								performanceData.setEndDate();
							}
						}
					}
				}
				else
					t.join();   // single user mode
			}
			catch (InterruptedException ie) { break; }
			if (abortedByRemote() || abortedByEofInputFile())
				break;
			
			
			// display and sample temporary performance data all "sampling interval" seconds
			if (((System.currentTimeMillis() - performanceData.getLastSnapshotTime()) / 1000) >= samplingInterval)
			{
				performanceData.addSnapshot(getCpuUsagePercent());
				performanceData.setEndDate();
				if (!isRemote())
				{
					synchronized (dumpLock)
					{
						performanceData.dump(false);
					}
				}
			}
		}
		
		
		// wait for test-end in a loop
		// ---------------------------
		boolean allDone = false;
		while (!allDone)
		{
			// test aborted ?
			if (abortedByRemote() || abortedByEofInputFile())
			{
				nearRemoteEnd();
				String abort = "";
				if (abortedByRemote())
					abort = "*** test aborted by remote command ***";
				if (abortedByEofInputFile())
				{
					abort = "*** test aborted at eof of input file ***";
					performanceData.addTestDescription(abort);
				}
				synchronized (dumpLock)
				{
					addSSLCacheStatistic();
					performanceData.addSnapshot(getCpuUsagePercent());
					performanceData.setEndDate();
					try
					{
						
						// deconstruct all global constructed plug-ins
						((GenericPluginInterface) plugin0002).deconstruct(globalPluginContext);
					}
					catch (Throwable pluginThrowable)
					{
						System.out.println("*** ERROR: EXECUTION OF PLUG-IN FAILED ***");
						pluginThrowable.printStackTrace(System.err);
						System.exit(-2);
					}
					
					if (!isRemote())
						performanceData.dump(false);
					if (resultFile != null)
						try { performanceData.writeObjectToFile(resultFile); } catch (IOException ie) { ie.printStackTrace(); }
					
					System.out.println();
					System.out.println(abort);
					System.out.println();
					System.out.flush();
					try { Thread.currentThread().sleep(10000); } catch (InterruptedException ie) {}
					System.exit(0);
				}
			}
			
			// display and sample temporary performance data all "sampling interval" seconds
			if (((System.currentTimeMillis() - performanceData.getLastSnapshotTime()) / 1000) >= samplingInterval)
			{
				performanceData.addSnapshot(getCpuUsagePercent());
				performanceData.setEndDate();
				if (!isRemote())
				{
					synchronized (dumpLock)
					{
						performanceData.dump(false);
					}
				}
			}
			
			// check if test has been done
			allDone = isTestDone();
			
			// if not all is done: sleep one second
			if (!allDone)
				try { sleepRemoteInterruptable(1000); } catch (InterruptedException ie) { break; }
		} // end of wait loop
		
		
		// mark near end of test - only if a remote interface has been used
		nearRemoteEnd();
		
		
		
		// final test result - test completed
		// ----------------------------------
		performanceData.addSnapshot(getCpuUsagePercent());
		performanceData.setEndDate();
		try
		{
			
			// deconstruct all global constructed plug-ins
			((GenericPluginInterface) plugin0002).deconstruct(globalPluginContext);
		}
		catch (Throwable pluginThrowable)
		{
			System.out.println("*** ERROR: EXECUTION OF PLUG-IN FAILED ***");
			pluginThrowable.printStackTrace(System.err);
			System.exit(-2);
		}
		
		// save test result
		performanceData.dump(true);
		if (resultFile != null)
			try { performanceData.writeObjectToFile(resultFile); } catch (IOException ie) { ie.printStackTrace(); }
		
		// all done
		System.out.flush();
		
		// wait for official end of test - only if a remote interface has been used
		waitRemoteEnd();
		
		System.out.println();
		System.out.println("Result File: " + resultFile);
		
		// now dead
		System.exit(0);
	}


	/**
	 * implement VaryingLoadInterface: support to increment the number of simulated users at runtime.
	 */
	public void setIncrementUser(long startTime)
	{
		incrementUserStartTime = startTime;
		incrementUserFlag = true;
	}

	public boolean isIncrementUser()
	{
		return incrementUserFlag;
	}

	public long getIncrementUserStartTime()
	{
		return incrementUserStartTime;
	}

	public void setIncrementUserStartTime(long startTime)
	{
		incrementUserStartTime = startTime;
	}

	public void clearIncrementUser()
	{
		incrementUserFlag = false;
	}

	public int incrementUsers(int numAddUsers, long startupDelay)
	{
		if (startupDelay < 0)
			startupDelay = plannedStartupDelay;
		return incrementUsersImplementation(numAddUsers, startupDelay, this);
	}

	static int incrementUsersImplementation(int numAddUsers, long startupDelay, HttpLoadTest httpLoadTest)
	{
		long startupDelayOffset = 0;
		if ((getClusterTotalMemberCount() > 1) && (getClusterMemberLocalId() > 0))
			startupDelayOffset = (startupDelay / ((long) getClusterTotalMemberCount())) * ((long) getClusterMemberLocalId());
		
		ArrayList<HttpLoadTestUserContext> addUserContextList = new ArrayList<HttpLoadTestUserContext>(numAddUsers);
		for (int x = 0; x < numAddUsers; x++)
		{
			if (abortedByRemote() || abortedByEofInputFile())
				return x;
			
			// initialize load test thread
			// ---------------------------
			try
			{
				getUserContextTable().getWriteLock().lock();
				
				A2scenarioV1 simulatedUser = new A2scenarioV1(maxPlannedLoops, plannedRequestTimeout, getUserContextTable().getUserContextList().size());
				HttpLoadTestUserContext userContext = prepareIncrementUser(simulatedUser, System.currentTimeMillis() + startupDelayOffset + (x * startupDelay));
				addUserContextList.add(userContext);
				simulatedUser.setOwnLoadTestUserContext(userContext);
				if (debugLoops && (simulatedUser.getLog().size() > 0))
				{
					synchronized (dumpLock)
					{
						simulatedUser.dumpLog(userContext.getLoadTestThread().getName() + " ", System.out);		// dump log of constructor
					}
				}
			}
			finally
			{
				getUserContextTable().getWriteLock().unlock();
			}
		}
		
		// start load test threads by a thread
		// -----------------------------------
		HttpLoadTestIncrementUserThread incrementUserThread = new HttpLoadTestIncrementUserThread(httpLoadTest, addUserContextList);
		incrementUserThread.start();
		return numAddUsers;
	}




}	// end of class

