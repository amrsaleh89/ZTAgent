
// *********************************************************************************************
//
// Automatically Generated Load Test Program
// -----------------------------------------
//
// Source: A1_Recording.java
// Date  : 29 Mar 2017 10:41:16 PST
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
public class A1_Recording extends HttpLoadTest implements Runnable, ThreadStepInterface, SetThreadStepInterface, SSLSessionCacheStatisticInterface, VaryingLoadInterface, VaryingTestDurationInterface, SuspendResumeInterface, GetRealTimeUserInputFieldsInterface
{
	public static final String prxVersion = "V5.4-I";
	public static final int    prxCharEncoding = 2;                         // 1 = OS Default, 2 = ISO-8859-1, 3 = UTF-8
	public static final String testDescription = "";

	public static String USER_AGENT_1 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:52.0) Gecko/20100101 Firefox/52.0";

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
	private static final String EXTERNAL_PARAM_FILE = "A1_RecordingExtParams.dat";          // input file name for very large parameter values
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
	
	private static String keyword = Lib.getFormattedCurrentDateAndTime("yyyy-MM-dd", 0, "PST"); // var declaration from web admin var handler: scope = global
	private static String departureDate = Lib.getFormattedCurrentDateAndTime("yyyy-MM-dd", 5, "PST"); // var declaration from web admin var handler: scope = global

	private volatile UserTransactionRuntimeHandler transactionHandler = new UserTransactionRuntimeHandler();		// re-used, support to manage user-defined transactions

	private static Object plugin0001 = null;                      // plug-in #1: "SelectSeatNumberJetBlue" - definition from web admin var handler: construction scope = global
	private static String plugin0001SelectSeatNumberJetBlue = null;// internal - not used in this load test program, but used by the GUI - reflective information

	private String flightNumber0 = null;                 // var declaration from web admin var handler: scope = per loop
	private String departureDate0 = null;                // var declaration from web admin var handler: scope = per loop
	private String bookingClassCode0 = null;             // var declaration from web admin var handler: scope = per loop
	private String cacheKey0 = null;                     // var declaration from web admin var handler: scope = per loop
	private String arrivalDate0 = null;                  // var declaration from web admin var handler: scope = per loop
	private String FirstFlightID = null;                 // var declaration from web admin var handler: scope = per loop
	private String FlightResponseData = null;            // var declaration from web admin var handler: scope = per loop
	private String flightItineraryId0 = null;            // var declaration from web admin var handler: scope = per loop
	private String SeatNumber = null;                    // var declaration from web admin var handler: scope = per loop
	private String SeatResponseData = null;              // var declaration from web admin var handler: scope = per loop
	

	/**
	 * constructor: called from load test plug-ins (scope = global).
	 */
	public A1_Recording()
	{
		super();
	}


	/**
	 * constructor: called when a user is created (per user).
	 */
	public A1_Recording(int maxLoops, int requestTimeout, int threadNumber)
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
		flightNumber0 = "";
		departureDate0 = "";
		bookingClassCode0 = "";
		cacheKey0 = "";
		arrivalDate0 = "";
		FirstFlightID = "";
		FlightResponseData = null;
		flightItineraryId0 = "";
		SeatNumber = null;
		SeatResponseData = null;
		log();
		log("<<< flightNumber0 = " + flightNumber0);
		log("<<< departureDate0 = " + departureDate0);
		log("<<< bookingClassCode0 = " + bookingClassCode0);
		log("<<< cacheKey0 = " + cacheKey0);
		log("<<< arrivalDate0 = " + arrivalDate0);
		log("<<< FirstFlightID = " + FirstFlightID);
		log("<<< FlightResponseData = " + FlightResponseData);
		log("<<< flightItineraryId0 = " + flightItineraryId0);
		log("<<< SeatNumber = " + SeatNumber);
		log("<<< SeatResponseData = " + SeatResponseData);
		
		// initialize context for plug-ins which are executed per loop
		LoadtestPluginContext loopPluginContext = new LoadtestPluginContext(prxVersion, prxCharEncoding, this, threadNumber, socketPool, cookieHandler);
		



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
		if (!executePage_1_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 1 failed
			synchResponsesParallelRequestsPage_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!executePage_1_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 1 failed
			synchResponsesParallelRequestsPage_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!executePage_1_3(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 1 failed
			synchResponsesParallelRequestsPage_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!executePage_1_4(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 1 failed
			synchResponsesParallelRequestsPage_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!synchResponsesParallelRequestsPage_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
			return false;		// execution of page 1 failed
		
		// page 1 successfully executed
		pageThreadHandler.addPageResponseTimeToResult(this);



		// --- PAGE BREAK: Test [86] ---
		log();
		log();
		log("# Page #2: Find Trips");
		log("# -------------------");
		threadStep = setPageBreak(performanceData, threadStep, "Page #2: Find Trips", 3000, 35, -1);		// hint: param #4 is the user's think time in milliseconds, param #5 is randomness of the user's think time in percent (+/- 0..100%), param #6 is the maximum acceptable response time in milliseconds (-1 = not configured)
		pageThreadHandler = new HttpTestURLThreadHandler(threadStep - 1, maxParallelThreadsPerUser, performanceData, this);   // support for parallel processing of http(s) requests within a page. hint: param #2 is the number of parallel threads per user
		log();

		if (!executePage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 2 failed
			synchResponsesParallelRequestsPage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!executePage_2_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 2 failed
			synchResponsesParallelRequestsPage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!executePage_2_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 2 failed
			synchResponsesParallelRequestsPage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!executePage_2_3(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 2 failed
			synchResponsesParallelRequestsPage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!executePage_2_4(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 2 failed
			synchResponsesParallelRequestsPage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!executePage_2_5(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 2 failed
			synchResponsesParallelRequestsPage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!executePage_2_6(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 2 failed
			synchResponsesParallelRequestsPage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!synchResponsesParallelRequestsPage_2(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
			return false;		// execution of page 2 failed
		
		// page 2 successfully executed
		pageThreadHandler.addPageResponseTimeToResult(this);



		// --- PAGE BREAK: Test [209] ---
		log();
		log();
		log("# Page #3: Add Information");
		log("# ------------------------");
		threadStep = setPageBreak(performanceData, threadStep, "Page #3: Add Information", 3000, 35, -1);		// hint: param #4 is the user's think time in milliseconds, param #5 is randomness of the user's think time in percent (+/- 0..100%), param #6 is the maximum acceptable response time in milliseconds (-1 = not configured)
		pageThreadHandler = new HttpTestURLThreadHandler(threadStep - 1, maxParallelThreadsPerUser, performanceData, this);   // support for parallel processing of http(s) requests within a page. hint: param #2 is the number of parallel threads per user
		log();

		if (!executePage_3(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 3 failed
			synchResponsesParallelRequestsPage_3(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!executePage_3_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 3 failed
			synchResponsesParallelRequestsPage_3(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!synchResponsesParallelRequestsPage_3(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
			return false;		// execution of page 3 failed
		
		// page 3 successfully executed
		pageThreadHandler.addPageResponseTimeToResult(this);



		// --- PAGE BREAK: Test [238] ---
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



		// --- PAGE BREAK: Test [267] ---
		log();
		log();
		log("# Page #5: Extra's");
		log("# ----------------");
		threadStep = setPageBreak(performanceData, threadStep, "Page #5: Extra's", 3000, 35, -1);		// hint: param #4 is the user's think time in milliseconds, param #5 is randomness of the user's think time in percent (+/- 0..100%), param #6 is the maximum acceptable response time in milliseconds (-1 = not configured)
		pageThreadHandler = new HttpTestURLThreadHandler(threadStep - 1, maxParallelThreadsPerUser, performanceData, this);   // support for parallel processing of http(s) requests within a page. hint: param #2 is the number of parallel threads per user
		log();

		if (!executePage_5(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 5 failed
			synchResponsesParallelRequestsPage_5(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!executePage_5_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 5 failed
			synchResponsesParallelRequestsPage_5(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!synchResponsesParallelRequestsPage_5(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
			return false;		// execution of page 5 failed
		
		// page 5 successfully executed
		pageThreadHandler.addPageResponseTimeToResult(this);



		// --- PAGE BREAK: Test [296] ---
		log();
		log();
		log("# Page #6: Payment information");
		log("# ----------------------------");
		threadStep = setPageBreak(performanceData, threadStep, "Page #6: Payment information", 3000, 35, -1);		// hint: param #4 is the user's think time in milliseconds, param #5 is randomness of the user's think time in percent (+/- 0..100%), param #6 is the maximum acceptable response time in milliseconds (-1 = not configured)
		pageThreadHandler = new HttpTestURLThreadHandler(threadStep - 1, maxParallelThreadsPerUser, performanceData, this);   // support for parallel processing of http(s) requests within a page. hint: param #2 is the number of parallel threads per user
		log();

		if (!executePage_6(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 6 failed
			synchResponsesParallelRequestsPage_6(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!executePage_6_1(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
		{
			// execution of page 6 failed
			synchResponsesParallelRequestsPage_6(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject);
			return false;
		}
		if (!synchResponsesParallelRequestsPage_6(totalLoopCounter, loopPluginContext, new InnerLoopContext(), httpLogVectorObject))
			return false;		// execution of page 6 failed
		
		// page 6 successfully executed
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
		String requestFile0006 = "/assets/globals/js/jb-74873.js";
		String requestHeader0006 = "GET " + requestFile0006 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0006, requestHost0006, requestPort0006, requestHeader0006, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0006 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "APPLICATION/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 22862
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "APPLICATION/JAVASCRIPT", null, null);		// Test [6] <- Index 6
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestHost0007 = "book.jetblue.com";
		int    requestPort0007 = 443;
		String requestFile0007 = "/assets/globals/css/resolve.css";
		String requestHeader0007 = "GET " + requestFile0007 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0007, requestHost0007, requestPort0007, requestHeader0007, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0007 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 325
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [7] <- Index 7
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestHost0008 = "book.jetblue.com";
		int    requestPort0008 = 443;
		String requestFile0008 = "/assets/globals/js/jbConsoleProtect.js";
		String requestHeader0008 = "GET " + requestFile0008 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0008, requestHost0008, requestPort0008, requestHeader0008, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0008 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 696
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [8] <- Index 8
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestHost0009 = "book.jetblue.com";
		int    requestPort0009 = 443;
		String requestFile0009 = "/assets/globals/css/global.css" +
				"?v=1.7";
		String requestHeader0009 = "GET " + requestFile0009 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0009, requestHost0009, requestPort0009, requestHeader0009, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0009 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 10975
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [9] <- Index 9
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestFile0010 = "/assets/globals/css/Background.css";
		String requestHeader0010 = "GET " + requestFile0010 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0010, requestHost0010, requestPort0010, requestHeader0010, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0010 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 451
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [10] <- Index 10
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [11] <- WEB ADMIN Index 11 ---
		String requestProt0011 = "https";
		String requestHost0011 = "book.jetblue.com";
		int    requestPort0011 = 443;
		String requestFile0011 = "/assets/globals/js/modernizr-2.0.6.min.js";
		String requestHeader0011 = "GET " + requestFile0011 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0011, requestHost0011, requestPort0011, requestHeader0011, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0011 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 4781
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [11] <- Index 11
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [12] <- WEB ADMIN Index 12 ---
		String requestProt0012 = "https";
		String requestHost0012 = "book.jetblue.com";
		int    requestPort0012 = 443;
		String requestFile0012 = "/assets/globals/js/angular.min.js";
		String requestHeader0012 = "GET " + requestFile0012 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0012, requestHost0012, requestPort0012, requestHeader0012, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0012 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 40012
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [12] <- Index 12
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestFile0013 = "/assets/globals/js/angular-route.min.js";
		String requestHeader0013 = "GET " + requestFile0013 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0013, requestHost0013, requestPort0013, requestHeader0013, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0013 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1846
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [13] <- Index 13
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [14] <- WEB ADMIN Index 14 ---
		String requestProt0014 = "https";
		String requestHost0014 = "book.jetblue.com";
		int    requestPort0014 = 443;
		String requestFile0014 = "/assets/globals/js/angular-resource.min.js";
		String requestHeader0014 = "GET " + requestFile0014 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0014, requestHost0014, requestPort0014, requestHeader0014, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0014 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1647
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [14] <- Index 14
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [15] <- WEB ADMIN Index 15 ---
		String requestProt0015 = "https";
		String requestHost0015 = "book.jetblue.com";
		int    requestPort0015 = 443;
		String requestFile0015 = "/assets/globals/js/jquery.min.js";
		String requestHeader0015 = "GET " + requestFile0015 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0015, requestHost0015, requestPort0015, requestHeader0015, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0015 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 32111
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [15] <- Index 15
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestFile0016 = "/assets/globals/js/jquery.placeholder.js";
		String requestHeader0016 = "GET " + requestFile0016 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0016, requestHost0016, requestPort0016, requestHeader0016, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0016 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1713
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [16] <- Index 16
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestFile0017 = "/assets/globals/js/jquery-ui-1.8.16.custom.min.js";
		String requestHeader0017 = "GET " + requestFile0017 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0017, requestHost0017, requestPort0017, requestHeader0017, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0017 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 20007
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [17] <- Index 17
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestFile0018 = "/assets/globals/js/touchDetection.js";
		String requestHeader0018 = "GET " + requestFile0018 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0018, requestHost0018, requestPort0018, requestHeader0018, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0018 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 452
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [18] <- Index 18
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestFile0019 = "/assets/globals/js/jquery.cookie.js" +
				"?v=1.0";
		String requestHeader0019 = "GET " + requestFile0019 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0019, requestHost0019, requestPort0019, requestHeader0019, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0019 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 837
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [19] <- Index 19
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestHost0020 = "www2.jetblue.com";
		int    requestPort0020 = 443;
		String requestFile0020 = "/apis/odapi/jsdata.aspx" +
				"?ODType=ALL";
		String requestHeader0020 = "GET " + requestFile0020 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0020, requestHost0020, requestPort0020, requestHeader0020, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0020 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 80423
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [20] <- Index 20
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
	 * Recorded http requests of page #1_1.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_1_1(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #1 (fragment 2 of 5)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [21] <- WEB ADMIN Index 21 ---
		String requestProt0021 = "https";
		String requestHost0021 = "www2.jetblue.com";
		int    requestPort0021 = 443;
		String requestFile0021 = "/ajax-data/booker/v1/config.js";
		String requestHeader0021 = "GET " + requestFile0021 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0021, requestHost0021, requestPort0021, requestHeader0021, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0021 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "APPLICATION/X-JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 4078
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "APPLICATION/X-JAVASCRIPT", null, null);		// Test [21] <- Index 21
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestHost0022 = "www2.jetblue.com";
		int    requestPort0022 = 443;
		String requestFile0022 = "/ajax-data/seasonalDayOfWeek.js";
		String requestHeader0022 = "GET " + requestFile0022 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0022, requestHost0022, requestPort0022, requestHeader0022, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0022 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "APPLICATION/X-JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 364
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "APPLICATION/X-JAVASCRIPT", null, null);		// Test [22] <- Index 22
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [23] <- WEB ADMIN Index 23 ---
		String requestProt0023 = "https";
		String requestHost0023 = "www2.jetblue.com";
		int    requestPort0023 = 443;
		String requestFile0023 = "/apis/odapi/jsdata.aspx" +
				"?ODType=All";
		String requestHeader0023 = "GET " + requestFile0023 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0023, requestHost0023, requestPort0023, requestHeader0023, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0023 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 80423
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [23] <- Index 23
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [24] <- WEB ADMIN Index 24 ---
		String requestProt0024 = "https";
		String requestHost0024 = "book.jetblue.com";
		int    requestPort0024 = 443;
		String requestFile0024 = "/assets/ieUpgrade/IE-overlay.js";
		String requestHeader0024 = "GET " + requestFile0024 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0024, requestHost0024, requestPort0024, requestHeader0024, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0024 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 850
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [24] <- Index 24
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestHost0025 = "book.jetblue.com";
		int    requestPort0025 = 443;
		String requestFile0025 = "/assets/globals/js/sfh4pzq.js";
		String requestHeader0025 = "GET " + requestFile0025 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0025, requestHost0025, requestPort0025, requestHeader0025, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0025 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 7892
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [25] <- Index 25
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestHost0026 = "book.jetblue.com";
		int    requestPort0026 = 443;
		String requestFile0026 = "/assets/globals/css/_pages.css";
		String requestHeader0026 = "GET " + requestFile0026 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0026, requestHost0026, requestPort0026, requestHeader0026, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0026 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 768
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [26] <- Index 26
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [27] <- WEB ADMIN Index 27 ---
		String requestProt0027 = "https";
		String requestHost0027 = "book.jetblue.com";
		int    requestPort0027 = 443;
		String requestFile0027 = "/shop/search/app/styles/style.54f2.css";
		String requestHeader0027 = "GET " + requestFile0027 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0027, requestHost0027, requestPort0027, requestHeader0027, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0027 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 2300
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [27] <- Index 27
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [28] <- WEB ADMIN Index 28 ---
		String requestProt0028 = "https";
		String requestHost0028 = "www2.jetblue.com";
		int    requestPort0028 = 443;
		String requestFile0028 = "/apis/odapi/JSScheduleExt.aspx";
		String requestHeader0028 = "GET " + requestFile0028 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0028, requestHost0028, requestPort0028, requestHeader0028, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0028 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 109
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [28] <- Index 28
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestFile0029 = "/shop/search/components/component.78fc.css";
		String requestHeader0029 = "GET " + requestFile0029 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0029, requestHost0029, requestPort0029, requestHeader0029, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0029 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 8811
		// content test algorithm: search text
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, "roundtrip");		// Test [29] <- Index 29
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [30] <- WEB ADMIN Index 30 ---
		String requestProt0030 = "https";
		String requestHost0030 = "book.jetblue.com";
		int    requestPort0030 = 443;
		String requestFile0030 = "/shop/search/app/config/scheduleExt.js";
		String requestHeader0030 = "GET " + requestFile0030 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0030, requestHost0030, requestPort0030, requestHeader0030, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0030 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 73
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [30] <- Index 30
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		String requestFile0031 = "/shop/search/app/config/config.js";
		String requestHeader0031 = "GET " + requestFile0031 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0031, requestHost0031, requestPort0031, requestHeader0031, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0031 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 436
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [31] <- Index 31
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [32] <- WEB ADMIN Index 32 ---
		String requestProt0032 = "https";
		String requestHost0032 = "book.jetblue.com";
		int    requestPort0032 = 443;
		String requestFile0032 = "/shop/search/app/components.4a05.js";
		String requestHeader0032 = "GET " + requestFile0032 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0032, requestHost0032, requestPort0032, requestHeader0032, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0032 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 42397
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [32] <- Index 32
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [33] <- WEB ADMIN Index 33 ---
		String requestProt0033 = "https";
		String requestHost0033 = "book.jetblue.com";
		int    requestPort0033 = 443;
		String requestFile0033 = "/shop/search/app/main.min.136f.js";
		String requestHeader0033 = "GET " + requestFile0033 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0033, requestHost0033, requestPort0033, requestHeader0033, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0033 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1483
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [33] <- Index 33
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [34] <- WEB ADMIN Index 34 ---
		String requestProt0034 = "https";
		String requestHost0034 = "book.jetblue.com";
		int    requestPort0034 = 443;
		String requestFile0034 = "/assets/header/img/bg-main-nav-primary.png";
		String requestHeader0034 = "GET " + requestFile0034 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0034, requestHost0034, requestPort0034, requestHeader0034, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0034 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 58021
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [34] <- Index 34
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [35] <- WEB ADMIN Index 35 ---
		String requestProt0035 = "https";
		String requestHost0035 = "book.jetblue.com";
		int    requestPort0035 = 443;
		String requestFile0035 = "/assets/header/img/bg-true-blue-label.jpg";
		String requestHeader0035 = "GET " + requestFile0035 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0035, requestHost0035, requestPort0035, requestHeader0035, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0035 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 16004
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [35] <- Index 35
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [36] <- WEB ADMIN Index 36 ---
		String requestProt0036 = "https";
		String requestHost0036 = "book.jetblue.com";
		int    requestPort0036 = 443;
		String requestFile0036 = "/assets/header/img/jetblue-white-reg.png";
		String requestHeader0036 = "GET " + requestFile0036 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0036, requestHost0036, requestPort0036, requestHeader0036, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0036 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 8435
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [36] <- Index 36
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [37] <- WEB ADMIN Index 37 ---
		String requestProt0037 = "https";
		String requestHost0037 = "book.jetblue.com";
		int    requestPort0037 = 443;
		String requestFile0037 = "/assets/globals/css/_page-title.css";
		String requestHeader0037 = "GET " + requestFile0037 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0037, requestHost0037, requestPort0037, requestHeader0037, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0037 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 1198
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [37] <- Index 37
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [38] <- WEB ADMIN Index 38 ---
		String requestProt0038 = "https";
		String requestHost0038 = "book.jetblue.com";
		int    requestPort0038 = 443;
		String requestFile0038 = "/assets/header/img/new-window-icon.png";
		String requestHeader0038 = "GET " + requestFile0038 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0038, requestHost0038, requestPort0038, requestHeader0038, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0038 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 3221
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [38] <- Index 38
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [39] <- WEB ADMIN Index 39 ---
		String requestProt0039 = "https";
		String requestHost0039 = "book.jetblue.com";
		int    requestPort0039 = 443;
		String requestFile0039 = "/assets/header/img/bg_main_nav_secondary_icons_trans.png" +
				"?1";
		String requestHeader0039 = "GET " + requestFile0039 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0039, requestHost0039, requestPort0039, requestHeader0039, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0039 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 52624
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [39] <- Index 39
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [40] <- WEB ADMIN Index 40 ---
		String requestProt0040 = "https";
		String requestHost0040 = "www2.jetblue.com";
		int    requestPort0040 = 443;
		String requestFile0040 = "/favicon.ico";
		String requestHeader0040 = "GET " + requestFile0040 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0040, requestHost0040, requestPort0040, requestHeader0040, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0040 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/X-ICON", header text fragment = [verification disabled], recorded content size = 32988
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/X-ICON", null, null);		// Test [40] <- Index 40
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #1_1 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #1_2.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_1_2(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #1 (fragment 3 of 5)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [41] <- WEB ADMIN Index 41 ---
		String requestProt0041 = "https";
		String requestHost0041 = "www.jetblue.com";
		int    requestPort0041 = 443;
		String requestFile0041 = "/img/new-window-icon.png";
		String requestHeader0041 = "GET " + requestFile0041 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0041, requestHost0041, requestPort0041, requestHeader0041, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0041 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1108
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [41] <- Index 41
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [42] <- WEB ADMIN Index 42 ---
		String requestProt0042 = "https";
		String requestHost0042 = "book.jetblue.com";
		int    requestPort0042 = 443;
		String requestFile0042 = "/assets/header/js/src/JB.Global.js" +
				"?12";
		String requestHeader0042 = "GET " + requestFile0042 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0042, requestHost0042, requestPort0042, requestHeader0042, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0042 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 5626
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [42] <- Index 42
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [43] <- WEB ADMIN Index 43 ---
		String requestProt0043 = "https";
		String requestHost0043 = "fonts.jetblue.com";
		int    requestPort0043 = 443;
		String requestFile0043 = "/k/sfh4pzq-d.css" +
				"?3bb2a6e53c9684ffdc9a99f71d5b2a62d9c86262f6f33f54f782cc3094d11df2f53a7c7868bea7f833fbebbf0b91b54b870ad16d56a61057bb3bdcb086687872f604fbbc904fd849e337ee5a801c4405129ae51551135a0e0a5d3466a8beaac71467f307a85e92d9e389ffc519fc3aff85d43768617fa7fdea045840ddcb1154e234762b910076baa912bb814ee358ff53a52618213bc533f43a52d522a2999048176b727eea64a8ef1c581883bfbd5a2db633fe2d35a4081a9e63d0f1dab8ba8aa562d9716d46f2c97c7515ec40feb8d22df95c1aea8db30d372a185a56bd3cd933fdcc66fccee695d15efaca70b2adfe4964126b961cd53bcf71f7cb426c75abd9fe0bc61a0796a9ef16a7793186089370c6aa306a1973a314c43a88fe04060977b4e019d7158fea5b21c2bcc060a97c3bdc77077a6b807aa2094417";
		String requestHeader0043 = "GET " + requestFile0043 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: fonts.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0043, requestHost0043, requestPort0043, requestHeader0043, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0043 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 115646
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [43] <- Index 43
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [44] <- WEB ADMIN Index 44 ---
		String requestProt0044 = "https";
		String requestHost0044 = "book.jetblue.com";
		int    requestPort0044 = 443;
		String requestFile0044 = "/assets/header/img/bg-jetblue-body-nologo.jpg";
		String requestHeader0044 = "GET " + requestFile0044 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0044, requestHost0044, requestPort0044, requestHeader0044, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0044 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 29660
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [44] <- Index 44
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [45] <- WEB ADMIN Index 45 ---
		String requestProt0045 = "https";
		String requestHost0045 = "book.jetblue.com";
		int    requestPort0045 = 443;
		String requestFile0045 = "/shop/search/app/extras/includes/ajax-loader.gif";
		String requestHeader0045 = "GET " + requestFile0045 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0045, requestHost0045, requestPort0045, requestHeader0045, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0045 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 7091
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [45] <- Index 45
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [46] <- WEB ADMIN Index 46 ---
		String requestProt0046 = "https";
		String requestHost0046 = "book.jetblue.com";
		int    requestPort0046 = 443;
		String requestFile0046 = "/assets/header/img/bg-main-nav-secondary_strips.jpg";
		String requestHeader0046 = "GET " + requestFile0046 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0046, requestHost0046, requestPort0046, requestHeader0046, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0046 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 562
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [46] <- Index 46
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [47] <- WEB ADMIN Index 47 ---
		String requestProt0047 = "https";
		String requestHost0047 = "book.jetblue.com";
		int    requestPort0047 = 443;
		String requestFile0047 = "/shop/search/app/config/scheduleExt.js" +
				"?1490803722035";
		String requestHeader0047 = "GET " + requestFile0047 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0047, requestHost0047, requestPort0047, requestHeader0047, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0047 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 73
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [47] <- Index 47
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [48] <- WEB ADMIN Index 48 ---
		String requestProt0048 = "https";
		String requestHost0048 = "book.jetblue.com";
		int    requestPort0048 = 443;
		String requestFile0048 = "/shop/search/app/partials/main.html";
		String requestHeader0048 = "GET " + requestFile0048 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: application/json, text/plain, */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
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

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 135
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

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [49] <- WEB ADMIN Index 49 ---
		String requestProt0049 = "https";
		String requestHost0049 = "www2.jetblue.com";
		int    requestPort0049 = 443;
		String requestFile0049 = "/JetBlueAlerts/MainTitle_Content.aspx";
		String requestHeader0049 = "GET " + requestFile0049 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0049, requestHost0049, requestPort0049, requestHeader0049, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0049 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 669
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





		// --- HTTP REQUEST: Test [50] <- WEB ADMIN Index 50 ---
		String requestProt0050 = "https";
		String requestHost0050 = "www2.jetblue.com";
		int    requestPort0050 = 443;
		String requestFile0050 = "/includes/bookjetblueSidekick.aspx";
		String requestHeader0050 = "GET " + requestFile0050 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0050, requestHost0050, requestPort0050, requestHeader0050, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0050 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 1307
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





		// --- HTTP REQUEST: Test [51] <- WEB ADMIN Index 51 ---
		String requestProt0051 = "https";
		String requestHost0051 = "www2.jetblue.com";
		int    requestPort0051 = 443;
		String requestFile0051 = "/includes/globalAjaxFooter.aspx";
		String requestHeader0051 = "GET " + requestFile0051 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0051, requestHost0051, requestPort0051, requestHeader0051, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0051 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 3737
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





		// --- HTTP REQUEST: Test [52] <- WEB ADMIN Index 52 ---
		String requestProt0052 = "https";
		String requestHost0052 = "book.jetblue.com";
		int    requestPort0052 = 443;
		String requestFile0052 = "/assets/globals/partials/footer.html";
		String requestHeader0052 = "GET " + requestFile0052 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0052, requestHost0052, requestPort0052, requestHeader0052, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0052 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 437
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

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [53] <- WEB ADMIN Index 53 ---
		String requestProt0053 = "https";
		String requestHost0053 = "www2.jetblue.com";
		int    requestPort0053 = 443;
		String requestFile0053 = "/includes/modules/booker_refresh/src/assets/context-switcher-btn-sp.png";
		String requestHeader0053 = "GET " + requestFile0053 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0053, requestHost0053, requestPort0053, requestHeader0053, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0053 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 3449
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [53] <- Index 53
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [54] <- WEB ADMIN Index 54 ---
		String requestProt0054 = "https";
		String requestHost0054 = "book.jetblue.com";
		int    requestPort0054 = 443;
		String requestFile0054 = "/shop/search/src/assets/up-dwn-arrows-comp.png";
		String requestHeader0054 = "GET " + requestFile0054 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0054, requestHost0054, requestPort0054, requestHeader0054, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0054 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1160
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [54] <- Index 54
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [55] <- WEB ADMIN Index 55 ---
		String requestProt0055 = "https";
		String requestHost0055 = "book.jetblue.com";
		int    requestPort0055 = 443;
		String requestFile0055 = "/shop/search/src/assets/radio-btn-unchecked.png";
		String requestHeader0055 = "GET " + requestFile0055 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0055, requestHost0055, requestPort0055, requestHeader0055, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0055 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 518
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [55] <- Index 55
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [56] <- WEB ADMIN Index 56 ---
		String requestProt0056 = "https";
		String requestHost0056 = "www2.jetblue.com";
		int    requestPort0056 = 443;
		String requestFile0056 = "/includes/modules/booker_refresh/src/assets/rgt-arrow.png";
		String requestHeader0056 = "GET " + requestFile0056 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0056, requestHost0056, requestPort0056, requestHeader0056, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0056 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 187
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [56] <- Index 56
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [57] <- WEB ADMIN Index 57 ---
		String requestProt0057 = "https";
		String requestHost0057 = "book.jetblue.com";
		int    requestPort0057 = 443;
		String requestFile0057 = "/shop/search/src/assets/radio-btn-checked.png";
		String requestHeader0057 = "GET " + requestFile0057 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0057, requestHost0057, requestPort0057, requestHeader0057, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0057 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 634
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [57] <- Index 57
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [58] <- WEB ADMIN Index 58 ---
		String requestProt0058 = "https";
		String requestHost0058 = "book.jetblue.com";
		int    requestPort0058 = 443;
		String requestFile0058 = "/shop/search/src/assets/form-btn-sp.png";
		String requestHeader0058 = "GET " + requestFile0058 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0058, requestHost0058, requestPort0058, requestHeader0058, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0058 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 3359
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [58] <- Index 58
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [59] <- WEB ADMIN Index 59 ---
		String requestProt0059 = "https";
		String requestHost0059 = "book.jetblue.com";
		int    requestPort0059 = 443;
		String requestFile0059 = "/shop/search/components/jbSelect/src/up-dwn-arrows.png";
		String requestHeader0059 = "GET " + requestFile0059 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0059, requestHost0059, requestPort0059, requestHeader0059, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0059 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 2888
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [59] <- Index 59
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [60] <- WEB ADMIN Index 60 ---
		String requestProt0060 = "https";
		String requestHost0060 = "book.jetblue.com";
		int    requestPort0060 = 443;
		String requestFile0060 = "/shop/search/src/assets/city-lrg-disc-orange.png";
		String requestHeader0060 = "GET " + requestFile0060 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0060, requestHost0060, requestPort0060, requestHeader0060, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0060 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 153
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [60] <- Index 60
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #1_2 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #1_3.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_1_3(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #1 (fragment 4 of 5)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [61] <- WEB ADMIN Index 61 ---
		String requestProt0061 = "https";
		String requestHost0061 = "book.jetblue.com";
		int    requestPort0061 = 443;
		String requestFile0061 = "/shop/search/src/assets/city-lrg-disc-grey.png";
		String requestHeader0061 = "GET " + requestFile0061 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0061, requestHost0061, requestPort0061, requestHeader0061, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0061 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 124
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [61] <- Index 61
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [62] <- WEB ADMIN Index 62 ---
		String requestProt0062 = "https";
		String requestHost0062 = "www2.jetblue.com";
		int    requestPort0062 = 443;
		String requestFile0062 = "/img/calendar-arrow-sp.png";
		String requestHeader0062 = "GET " + requestFile0062 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0062, requestHost0062, requestPort0062, requestHeader0062, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0062 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 593
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [62] <- Index 62
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [63] <- WEB ADMIN Index 63 ---
		String requestProt0063 = "https";
		String requestHost0063 = "www2.jetblue.com";
		int    requestPort0063 = 443;
		String requestFile0063 = "/includes/modules/booker_refresh/src/assets/special-needs-icon.gif";
		String requestHeader0063 = "GET " + requestFile0063 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0063, requestHost0063, requestPort0063, requestHeader0063, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0063 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1405
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [63] <- Index 63
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [64] <- WEB ADMIN Index 64 ---
		String requestProt0064 = "https";
		String requestHost0064 = "www2.jetblue.com";
		int    requestPort0064 = 443;
		String requestFile0064 = "/includes/modules/booker_refresh/src/assets/baggage-icon.png";
		String requestHeader0064 = "GET " + requestFile0064 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0064, requestHost0064, requestPort0064, requestHeader0064, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0064 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1602
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [64] <- Index 64
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [65] <- WEB ADMIN Index 65 ---
		String requestProt0065 = "https";
		String requestHost0065 = "www2.jetblue.com";
		int    requestPort0065 = 443;
		String requestFile0065 = "/includes/modules/booker_refresh/src/assets/pets-baggage-icon.gif";
		String requestHeader0065 = "GET " + requestFile0065 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0065, requestHost0065, requestPort0065, requestHeader0065, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0065 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1374
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [65] <- Index 65
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [66] <- WEB ADMIN Index 66 ---
		String requestProt0066 = "https";
		String requestHost0066 = "www2.jetblue.com";
		int    requestPort0066 = 443;
		String requestFile0066 = "/img/icon-travel-alert.png";
		String requestHeader0066 = "GET " + requestFile0066 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0066, requestHost0066, requestPort0066, requestHeader0066, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0066 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 14954
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [66] <- Index 66
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [67] <- WEB ADMIN Index 67 ---
		String requestProt0067 = "https";
		String requestHost0067 = "www2.jetblue.com";
		int    requestPort0067 = 443;
		String requestFile0067 = "/img/rgt-arrow.png";
		String requestHeader0067 = "GET " + requestFile0067 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0067, requestHost0067, requestPort0067, requestHeader0067, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0067 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 187
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [67] <- Index 67
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [68] <- WEB ADMIN Index 68 ---
		String requestProt0068 = "https";
		String requestHost0068 = "book.jetblue.com";
		int    requestPort0068 = 443;
		String requestFile0068 = "/assets/content/media/jetBlue-even-more-space.png";
		String requestHeader0068 = "GET " + requestFile0068 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0068, requestHost0068, requestPort0068, requestHeader0068, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0068 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 28457
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [68] <- Index 68
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [69] <- WEB ADMIN Index 69 ---
		String requestProt0069 = "https";
		String requestHost0069 = "www2.jetblue.com";
		int    requestPort0069 = 443;
		String requestFile0069 = "/includes/snacks.png";
		String requestHeader0069 = "GET " + requestFile0069 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0069, requestHost0069, requestPort0069, requestHeader0069, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0069 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 70021
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [69] <- Index 69
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [70] <- WEB ADMIN Index 70 ---
		String requestProt0070 = "https";
		String requestHost0070 = "book.jetblue.com";
		int    requestPort0070 = 443;
		String requestFile0070 = "/assets/content/media/Datalex_Pods_FlyFi_V01.jpg";
		String requestHeader0070 = "GET " + requestFile0070 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0070, requestHost0070, requestPort0070, requestHeader0070, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0070 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 16560
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [70] <- Index 70
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [71] <- WEB ADMIN Index 71 ---
		String requestProt0071 = "https";
		String requestHost0071 = "www2.jetblue.com";
		int    requestPort0071 = 443;
		String requestFile0071 = "/img/jbapp-icon.png" +
				"?v=1";
		String requestHeader0071 = "GET " + requestFile0071 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0071, requestHost0071, requestPort0071, requestHeader0071, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0071 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 3322
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [71] <- Index 71
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [72] <- WEB ADMIN Index 72 ---
		String requestProt0072 = "https";
		String requestHost0072 = "www2.jetblue.com";
		int    requestPort0072 = 443;
		String requestFile0072 = "/img/blogicon.png";
		String requestHeader0072 = "GET " + requestFile0072 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0072, requestHost0072, requestPort0072, requestHeader0072, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0072 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 15145
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [72] <- Index 72
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [73] <- WEB ADMIN Index 73 ---
		String requestProt0073 = "https";
		String requestHost0073 = "www2.jetblue.com";
		int    requestPort0073 = 443;
		String requestFile0073 = "/img/facebookicon.png";
		String requestHeader0073 = "GET " + requestFile0073 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0073, requestHost0073, requestPort0073, requestHeader0073, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0073 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1464
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [73] <- Index 73
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [74] <- WEB ADMIN Index 74 ---
		String requestProt0074 = "https";
		String requestHost0074 = "www2.jetblue.com";
		int    requestPort0074 = 443;
		String requestFile0074 = "/img/soflybubbleicon.png";
		String requestHeader0074 = "GET " + requestFile0074 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0074, requestHost0074, requestPort0074, requestHeader0074, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0074 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1353
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [74] <- Index 74
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [75] <- WEB ADMIN Index 75 ---
		String requestProt0075 = "https";
		String requestHost0075 = "www2.jetblue.com";
		int    requestPort0075 = 443;
		String requestFile0075 = "/img/twittericon.png";
		String requestHeader0075 = "GET " + requestFile0075 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0075, requestHost0075, requestPort0075, requestHeader0075, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0075 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1854
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [75] <- Index 75
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [76] <- WEB ADMIN Index 76 ---
		String requestProt0076 = "https";
		String requestHost0076 = "www2.jetblue.com";
		int    requestPort0076 = 443;
		String requestFile0076 = "/img/new-window-icon.png";
		String requestHeader0076 = "GET " + requestFile0076 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0076, requestHost0076, requestPort0076, requestHeader0076, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0076 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1108
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [76] <- Index 76
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [77] <- WEB ADMIN Index 77 ---
		String requestProt0077 = "https";
		String requestHost0077 = "www2.jetblue.com";
		int    requestPort0077 = 443;
		String requestFile0077 = "/img/InstagramIcon4.jpg";
		String requestHeader0077 = "GET " + requestFile0077 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0077, requestHost0077, requestPort0077, requestHeader0077, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0077 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 10923
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [77] <- Index 77
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [78] <- WEB ADMIN Index 78 ---
		String requestProt0078 = "https";
		String requestHost0078 = "www2.jetblue.com";
		int    requestPort0078 = 443;
		String requestFile0078 = "/img/bg-footer-bar-top.gif";
		String requestHeader0078 = "GET " + requestFile0078 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0078, requestHost0078, requestPort0078, requestHeader0078, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0078 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1165
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [78] <- Index 78
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [79] <- WEB ADMIN Index 79 ---
		log();
		log("# title: 404 Not Found");
		String requestProt0079 = "https";
		String requestHost0079 = "book.jetblue.com";
		int    requestPort0079 = 443;
		String requestFile0079 = "/img/input-bg.png";
		String requestHeader0079 = "GET " + requestFile0079 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0079, requestHost0079, requestPort0079, requestHeader0079, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0079 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 404, content type = [verification disabled], header text fragment = [verification disabled], recorded content size = 307
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {404}, null, null, null);		// Test [79] <- Index 79
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





		// --- HTTP REQUEST: Test [80] <- WEB ADMIN Index 80 ---
		String requestProt0080 = "https";
		String requestHost0080 = "www2.jetblue.com";
		int    requestPort0080 = 443;
		String requestFile0080 = "/img/getaway-cities-list-arrow-up.jpg";
		String requestHeader0080 = "GET " + requestFile0080 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0080, requestHost0080, requestPort0080, requestHeader0080, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0080 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 454
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [80] <- Index 80
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #1_3 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #1_4.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_1_4(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #1 (fragment 5 of 5)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [81] <- WEB ADMIN Index 81 ---
		String requestProt0081 = "https";
		String requestHost0081 = "www2.jetblue.com";
		int    requestPort0081 = 443;
		String requestFile0081 = "/img/getaway-cities-list-arrow.jpg";
		String requestHeader0081 = "GET " + requestFile0081 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0081, requestHost0081, requestPort0081, requestHeader0081, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0081 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 455
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [81] <- Index 81
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [82] <- WEB ADMIN Index 82 ---
		String requestProt0082 = "https";
		String requestHost0082 = "www2.jetblue.com";
		int    requestPort0082 = 443;
		String requestFile0082 = "/img/youtubeicon.png";
		String requestHeader0082 = "GET " + requestFile0082 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0082, requestHost0082, requestPort0082, requestHeader0082, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0082 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1660
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [82] <- Index 82
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [83] <- WEB ADMIN Index 83 ---
		String requestProt0083 = "https";
		String requestHost0083 = "www2.jetblue.com";
		int    requestPort0083 = 443;
		String requestFile0083 = "/img/bg-footer-bar-bottom.gif";
		String requestHeader0083 = "GET " + requestFile0083 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0083, requestHost0083, requestPort0083, requestHeader0083, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0083 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1165
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [83] <- Index 83
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [84] <- WEB ADMIN Index 84 ---
		String requestProt0084 = "https";
		String requestHost0084 = "www2.jetblue.com";
		int    requestPort0084 = 443;
		String requestFile0084 = "/img/loupe-btn-sp.gif";
		String requestHeader0084 = "GET " + requestFile0084 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www2.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0084, requestHost0084, requestPort0084, requestHeader0084, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0084 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 240
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [84] <- Index 84
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [85] <- WEB ADMIN Index 85 ---
		String requestProt0085 = "https";
		String requestHost0085 = "www.jetblue.com";
		int    requestPort0085 = 443;
		String requestFile0085 = "/img/calendar-vertical.gif";
		String requestHeader0085 = "GET " + requestFile0085 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0085, requestHost0085, requestPort0085, requestHeader0085, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0085 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 192
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [85] <- Index 85
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #1_4 successful done
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
		// # Page #2: Find Trips
		// # -------------------



		// --- HTTP REQUEST: Test [87] <- WEB ADMIN Index 87 ---
		log();
		log("# title: Travel Distribution Platform - IBE");
		String requestProt0087 = "https";
		String requestHost0087 = "book.jetblue.com";
		int    requestPort0087 = 443;
		String requestFile0087 = "/B6/webqtrip.html";
		String requestContent0087 = 
				"lang=en" +
				"&pos=JETBLUE" +
				"&flexibleSearch=TRUE" +
				"&searchType=NORMAL" +
				"&jbBookerSearchType=flights" +
				"&journeySpan=OW" +
				"&jbBookerCurrency-getaways=usd" +
				"&origin=BOS" +
				"&destination=JFK" +
				"&roundTripFaresFlag=false" +
				"&sharedMarket=false" +
				"&departureDate=" + java.net.URLEncoder.encode(departureDate) +		// recorded value = 2017-04-03
				"&numAdults=1" +
				"&numChildren=0" +
				"&numInfants=0" +
				"&jbBookerCurrency-flights=usd" +
				"&refundable=false" +
				"&fareFamily=LOWESTFARE" +
				"&fareDisplay=lowest" +
				"&X-uniqueStateKey=AB980xpbAQAAb-0H_60c8h39Ol1Of1sbGX5jwc_e1LIj-KTpnfmuzQdPBh0d" +
				"&X-FctdzjSze=I-spWTYc6DvUPqYJxeYM1Tq5ujgBRRfO1QRH19PFjDRmPlgM1laFujn5qaYoLDv0PQR0a5vFPe-wxToDPqYF1lgdxT5CxQRw0lRDPlgGRsoCP15M6e9GLlgzPjvTRjo51TRK1ZoGWlaoxdn-6q8i6l50WDRwBq9jxeR71QRw7e9oxqRo6T9ofeRyLTR0Ae9aLTaM1ZPGLqYePqfY6T5DWl1-1Qv00QRH1QR06T5mAT1m6DY-6ZPX1qnG6D2O6T5yP6tX0dJoxTPExTa-6T9oAd9MPDg-6spe0DnE6j9E6qJmPqnoDQnE6j9E6eno6enZ6D5wPln51TRK1ORKP9Mw6Da86DgGPlgGfQv-PQRwnqPo6enf1Q501Zgw6DaY6enoxT5z1QoDPxMw6DaZ6D5wWlge7sYoxqRoxdnf1Q501Za0Pq9C6Dg7PR9GLqYGkm1XxTvdxDR0AsR7PqVy7TY06d17PqYB6sReWlg77T9kxTvyPR1oLe9G6dYo7l90Pl5GPjRmPlao6efVPQ5GPR9GxToKP3oM6l1fxT97PqfhWT5DLR9axsJExef96lRwWl5OPqPMLDRYP9YCPqYT6dYyLlgzPR9axsJExef9xsYExQR01so8WQRzW3gCxTvCPqYG_RP-6sRox3g7LdYoPlgi1TR0xTowPxM71Q5zWan0Ll9o0e-kxo9axsJExeuzaD--xGnoPT5a6sncxTvdxDR0uQRmxQR0Gl--xa1MPQRDWlgojQAaPDoKAdJm1l1M6epz0D--xanEjDvaxT9o0e9E1qYzPj--xDzVxdn0WlgeuQ57WVqbPQvz1lao6efZPQvz1lao6enc6Dng3Cd78EZZUvCwLDnzqD57PQMT6Q571qnExQPk1T9WfQazPTAF3Ci7J9RFqd1oLTn0WqPoxov7LdYMxsnFPT28LlAm7Q9kLqY-Ldnoxo9o1Z1zWQ50xDRG0T9E6qJ-1OaEPQqZPQvz1lao6en96Dno7T1o1ORmPlao6enc_jowaT1o1ORmPlao6en7Beo86Q57xGg-6lq9WQ57BqnGxToX1qnox3PM6l5ePqiQ6Q5gPqY77q5aPqYgjDRmPl9G6dVe0D9E6eno_sn9Plga0loK6TR0u5n9fZoM6Tgoxono_sfj6lvNjTRA1lR71OPa6QAfLdYoPl2nxTRA1lR71OPa6QAfLdYoPl2Y1DRXPsYM1TR0ad1oLTyM15YoxqRoxdnQ1lAmjD90PlRKkxofPq5aPlgG1ldGYmaJLdnM1TRLfDYtPl9G7G5CxQAojQ5gjDR7xDoE6mnQWlAo0joK18-JxeY-_1J91qn-1QoE6wvXxDR01TR07OgE1QoTWl9-1QoE6myB1q9kfl5KLl1oxmAfWQ50Plnq6dYUPqhVRQvaLD-51TRK1ZgLnQvyLloKjTRA1lR71iZ83CfbqdJkLlgG6DdBLlnwnqPo6enZWq9GPlgoxmy-1sn-LD-51TRK1ZyzLlAmjQ--6enE6xyzxTR-1QRB6dJaxZywPqn-LD-51TRK1ZawWq9CLqnzWORDPlgGAlRDPlgG3CdYPTo0PjRDPlgGATP0LlaoxvJePqn86DaC1qnoP59G_lAo7l1m6DY-659G6dY-PDqZ6QvzLlAf1Qv0Ll1oaQaE_oYjBaJoPqY86DgKPl9GWlvKDQaE_oYoxqRoxdnJ6ToyLqnM6DgQxT5yPx1CWQ5K1Qvy0dJExdn9Pq97Ll1oadYoPDo71QR0jsYE1Qvz6DAbLlgw6QR0GdYo6lvDPjRDPlgGfQo71QRKPqhRxTRA1lR71O5KWla-1QoE6wP0Llao7e9oxd9M6Dgf1Qv0Ll1oAd9MPQRXLqhq1DRXWDoGjon8jQRoxw9E6TgoLdnM6D261DRXWDoGjTRA1lR71O5KWla-1QoE6wP0Llaodd1oLTyM15YoxDvm1TRZ6D9-6OPM6QRf_q9GPlaRjw0-AdPMLeY-1Qlo3C2h6QvzLqnM6DgXLqh78ZP7LdYoPl2s1QvE6QY-xtFZPeY-6lR56QRyPlgG0DoK6TR0uQRMPD-G0ToK6TR0RDow1Qzh6dRGPqYbPloeWsfV6dRGPqYqWlnGWZ17LdYoPlgLAd9zxTRo6oTm0D5CxO9EPQRpLlaoAD5CxOg-6lqVLqJCRTR0xDoE6m1X1lomPOoO0TnEfTvGRsY-LDEfWQ50Ps1-xTR86Dgz1qY0Plgz_x-C6Q5GPTv06x1CxTvw1l9G0eJ06DnaLdnf1lhY1q9oxw5ePlgGAePo6TnExmoDPlgw6dYf1lVT0D5DLlomuQRMPD-G0T5DLlomRDow1QzVLDvm6dYOPqJGWZPkPloeWsfVxQoHPlAOPqJGWZRdWlnGWV6hPQR7LdYMxsnM6D2s6lR7xD5ePFZBCDga6xR71Q5zW3M71Q5zWdn0Ll9oMZgUPqoX6D50PORDPlgGx3yy6dR7PjRDPlgGxv5ExToo6en-1QoE6wRDPlgGx3yG6dRzWORDPlgGxNisWDRgnQvd6m-UPqoBxTR7x3RUPqoRxVEQLlAGuDRgAD9GxTAhPqe7JFZQADyo_j9EPQq7cZ1yPqn-uDRg3CebxD-MPenhPqe7cIZsMxPkWlnwPl2cWlf7OZ1GLl1pLlao0sngxQRJ1sn0MZMy6dR7Pj9mWl9U0laE1q9onQvd6moy6dR7PjaE1Tqs6lvaxDRRxVE78IZ53C67cZYElZYElFZY3CE7J35HCqTk3CI7JxoTLlyonqPo6ef7cIZs7eRK1sYaxdnoPORDPlgGCqzJ_Wcz0snE1l9knlgw0qnE1l9kflvDPxMG6dRzW59GLqYGr%3D8H%3D_x%3D%3D%3D56QypbPpx%3D%3D%3D56QypbqKx%3D%3D%3D56QypbO_x%3D%3D%3D56Qypb8rx%3D%3D%3D56QypO-Kx%3D%3D%3D56Qyp8Dpx%3D%3D%3D56Qyp8aIScr%3DiHJp8Sk_L_ZH0cHiS5H_LAsFNIgzO1IUICjdRKbO5aP0%3DC9u%3DApfBab8ODpzODpz%3DCbO19RcGC97%3DCbc-BRaBM%3DpsSNTeUsf6-r%3D6gJCvtrBS%3DrBw%3DrBkIJcyoHiwhSiSZr%3DdXB2PsSOB-nHJ5%3DJF-r%3D2sflvNWlAmLx-pPqn7LD5CPx3ahz%3DSVOa-LDoK1Qv7WcepZz%3DA97%3D7ZfL0ZfZHZzeJZBfbfl5zulgGPl75nDRzWDIbZz%3DAZ8%3DAZ8sCflvNWlAmLu3ahz%3DSVOa-LDoK1Qv7W8mSulgGPlCSfl5zbOvfb5SSZf%3DKZfb2bsYDpzj0hz%3DMbO1oLDyEh7bCZf%3DCZf%3DAbOPMxTRT6dSE9fbKZ%3D8%3DCiSinc5sSJ-OpJ-sSpsHJ_so5%3DEFNTMhAqqHOpil6eRm6cJkLqZS6T3SxsYExQR01Qoox3oj_qJonqY06dhzdGJRjwCVPOo%3DRRYZcTRjB5Ruf%3DME1OJRjwCVB5Ruf%3DUzHFSnrJVwrJiSH%3DsSOpsS%3DpsH5p87%3DZ87%3DFZcWn7HOr8S%3D_%3DJH_%3DJHFSjHiZ%3DCiZJ3CYtSESfHp%3DJH%3Ds-H%3Dh-rJfS3C8%3D3Cs7%3DTy8rJiSH%3DsS%3D_sS%3DrsH5p87%3DZ87%3DFZcW3NzrJiSH%3DiS%3D_sS%3D_sH5p87%3DZ87%3DFZcWk6HOr8S%3Dr%3DJH_%3DcHFSjHiZ%3DCiZJ3CYUnFSfHp%3D8H%3Ds-H%3Di-rJfS3C8%3D3Cs7%3DTEnMiSfHp%3DcH%3Ds-H%3Ds-rJfS3C8%3D3Cs7%3DTUyrJiSH%3DhS%3D_sS%3DKsH5p87%3DZ87%3DFZcW22HOr8S%3DK%3DJH_%3D8HFSjHiZ%3DCiZJ3CYmVISfHp%3DcHJ8-H%3D8SrJfS3C8%3D3Cs7%3DTCmrJl-rJL%3D%3Dr8-2ObR7Z8mBRbX%3D%3D6H5p8%3DCiZJ3CUwtUM5lwY_kFSl%3D%3DsSH_Ac5x7%3D2O5ubS%3DQrJfSCZ87%3DFZVgVkdnRMcqUEH5rbOHrhwt5zznRJcniSqHSfzHKukWp95jjYsrJFXJpiXgV-KHGRuBwzH5rbOHrhwts6znRYcuFSqHSfzHKukFK95jGYVrJFXJpiXgVXQHGRfBwEH5rbOHrhwtY7znRncfESqHSfzHKukeK95ROYprJFXJpiXgVXTHGRRBwIH5rbOHrhwtV2znRRcjiSqHSfzHKukyp95RjYnrJFXJpiXgVX3HGRlBohH5rbOHrhwtZfznRPcjISqHSfzHKuk7p95RwYjrJFXJpiXgVzjHGRqBoqH5rbOHrhwt97znR1cRESqHSfzHKukgp95lOYqrJFXJpiXgVzmHGRLBozH5rbOHrhwtifznR-clESqHSfzHKukIp95ljY6rJFXJpiXgVwOHGRPBoEH5rbOHrhwtB7znRocqiSqHSfzHKuM5p95ljYxrJFXJpiXgVwxHGRPBodH5rbOHrhwtuFznRocqFSqHSfzHKuMVK95lwY1rJFXJpiXgVw7HGRWBoN-rJL%3D%3DK8-2ObR7Z8mBRbX%3D%3D6H5p8%3DCiZJ3CUwtU15lwY_rnzHQWcSk%3D";
		String requestHeader0087 = "POST " + requestFile0087 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"Content-Length: " + requestContent0087.length() + "\r\n" + 		// Content-Length: 5086
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0087, requestHost0087, requestPort0087, requestHeader0087, requestContent0087.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0087 = null;		// support garbage collector to reduce memory
		requestContent0087 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 24436
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [87] <- Index 87
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





		// --- HTTP REQUEST: Test [88] <- WEB ADMIN Index 88 ---
		String requestProt0088 = "https";
		String requestHost0088 = "book.jetblue.com";
		int    requestPort0088 = 443;
		String requestFile0088 = "/B6/css/all.css" +
				"?version=201702040020";
		String requestHeader0088 = "GET " + requestFile0088 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0088, requestHost0088, requestPort0088, requestHeader0088, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0088 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 241644
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [88] <- Index 88
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [89] <- WEB ADMIN Index 89 ---
		String requestProt0089 = "https";
		String requestHost0089 = "book.jetblue.com";
		int    requestPort0089 = 443;
		String requestFile0089 = "/B6/css/print.css" +
				"?version=201702040020";
		String requestHeader0089 = "GET " + requestFile0089 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0089, requestHost0089, requestPort0089, requestHeader0089, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0089 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 65
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [89] <- Index 89
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [90] <- WEB ADMIN Index 90 ---
		String requestProt0090 = "https";
		String requestHost0090 = "book.jetblue.com";
		int    requestPort0090 = 443;
		String requestFile0090 = "/B6/js/jquery-1.7.2.min.js" +
				"?version=201702040020";
		String requestHeader0090 = "GET " + requestFile0090 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0090, requestHost0090, requestPort0090, requestHeader0090, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0090 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 33622
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [90] <- Index 90
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [91] <- WEB ADMIN Index 91 ---
		String requestProt0091 = "https";
		String requestHost0091 = "book.jetblue.com";
		int    requestPort0091 = 443;
		String requestFile0091 = "/B6/js/common.js" +
				"?version=201702040020";
		String requestHeader0091 = "GET " + requestFile0091 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0091, requestHost0091, requestPort0091, requestHeader0091, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0091 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 20090
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [91] <- Index 91
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [92] <- WEB ADMIN Index 92 ---
		String requestProt0092 = "https";
		String requestHost0092 = "book.jetblue.com";
		int    requestPort0092 = 443;
		String requestFile0092 = "/B6/js/dialog.js" +
				"?version=201702040020";
		String requestHeader0092 = "GET " + requestFile0092 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0092, requestHost0092, requestPort0092, requestHeader0092, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0092 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 8601
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [92] <- Index 92
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [93] <- WEB ADMIN Index 93 ---
		String requestProt0093 = "https";
		String requestHost0093 = "book.jetblue.com";
		int    requestPort0093 = 443;
		String requestFile0093 = "/B6/js/global.js" +
				"?version=201702040020";
		String requestHeader0093 = "GET " + requestFile0093 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0093, requestHost0093, requestPort0093, requestHeader0093, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0093 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 15014
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [93] <- Index 93
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [94] <- WEB ADMIN Index 94 ---
		String requestProt0094 = "https";
		String requestHost0094 = "book.jetblue.com";
		int    requestPort0094 = 443;
		String requestFile0094 = "/B6/js/utils.js" +
				"?version=201702040020";
		String requestHeader0094 = "GET " + requestFile0094 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0094, requestHost0094, requestPort0094, requestHeader0094, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0094 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 7173
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [94] <- Index 94
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [95] <- WEB ADMIN Index 95 ---
		String requestProt0095 = "https";
		String requestHost0095 = "book.jetblue.com";
		int    requestPort0095 = 443;
		String requestFile0095 = "/B6/js/navigation.js" +
				"?version=201702040020";
		String requestHeader0095 = "GET " + requestFile0095 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0095, requestHost0095, requestPort0095, requestHeader0095, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0095 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 2121
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [95] <- Index 95
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [96] <- WEB ADMIN Index 96 ---
		String requestProt0096 = "https";
		String requestHost0096 = "book.jetblue.com";
		int    requestPort0096 = 443;
		String requestFile0096 = "/B6/js/popup.js" +
				"?version=201702040020";
		String requestHeader0096 = "GET " + requestFile0096 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0096, requestHost0096, requestPort0096, requestHeader0096, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0096 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 9774
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [96] <- Index 96
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [97] <- WEB ADMIN Index 97 ---
		String requestProt0097 = "https";
		String requestHost0097 = "book.jetblue.com";
		int    requestPort0097 = 443;
		String requestFile0097 = "/B6/js/drilldown.js" +
				"?version=201702040020";
		String requestHeader0097 = "GET " + requestFile0097 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0097, requestHost0097, requestPort0097, requestHeader0097, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0097 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 5138
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [97] <- Index 97
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [98] <- WEB ADMIN Index 98 ---
		String requestProt0098 = "https";
		String requestHost0098 = "book.jetblue.com";
		int    requestPort0098 = 443;
		String requestFile0098 = "/B6/js/session_timer.js" +
				"?version=201702040020";
		String requestHeader0098 = "GET " + requestFile0098 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0098, requestHost0098, requestPort0098, requestHeader0098, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0098 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1933
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [98] <- Index 98
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [99] <- WEB ADMIN Index 99 ---
		String requestProt0099 = "https";
		String requestHost0099 = "book.jetblue.com";
		int    requestPort0099 = 443;
		String requestFile0099 = "/B6/js/session_management.js" +
				"?version=201702040020";
		String requestHeader0099 = "GET " + requestFile0099 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0099, requestHost0099, requestPort0099, requestHeader0099, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0099 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 450
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [99] <- Index 99
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [100] <- WEB ADMIN Index 100 ---
		String requestProt0100 = "https";
		String requestHost0100 = "book.jetblue.com";
		int    requestPort0100 = 443;
		String requestFile0100 = "/B6/js/calendar.js" +
				"?version=201702040020";
		String requestHeader0100 = "GET " + requestFile0100 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0100, requestHost0100, requestPort0100, requestHeader0100, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0100 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 9329
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [100] <- Index 100
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [101] <- WEB ADMIN Index 101 ---
		String requestProt0101 = "https";
		String requestHost0101 = "book.jetblue.com";
		int    requestPort0101 = 443;
		String requestFile0101 = "/B6/js/shopping.js" +
				"?version=201702040020";
		String requestHeader0101 = "GET " + requestFile0101 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0101, requestHost0101, requestPort0101, requestHeader0101, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0101 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 4256
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [101] <- Index 101
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [102] <- WEB ADMIN Index 102 ---
		String requestProt0102 = "https";
		String requestHost0102 = "book.jetblue.com";
		int    requestPort0102 = 443;
		String requestFile0102 = "/B6/js/guest_type.js" +
				"?version=201702040020";
		String requestHeader0102 = "GET " + requestFile0102 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0102, requestHost0102, requestPort0102, requestHeader0102, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0102 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 2406
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [102] <- Index 102
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [103] <- WEB ADMIN Index 103 ---
		String requestProt0103 = "https";
		String requestHost0103 = "book.jetblue.com";
		int    requestPort0103 = 443;
		String requestFile0103 = "/B6/js/air_search_extensions.js" +
				"?version=201702040020";
		String requestHeader0103 = "GET " + requestFile0103 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0103, requestHost0103, requestPort0103, requestHeader0103, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0103 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 187
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [103] <- Index 103
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [104] <- WEB ADMIN Index 104 ---
		String requestProt0104 = "https";
		String requestHost0104 = "book.jetblue.com";
		int    requestPort0104 = 443;
		String requestFile0104 = "/B6/js/air_search_common.js" +
				"?version=201702040020";
		String requestHeader0104 = "GET " + requestFile0104 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0104, requestHost0104, requestPort0104, requestHeader0104, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0104 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 14749
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [104] <- Index 104
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [105] <- WEB ADMIN Index 105 ---
		String requestProt0105 = "https";
		String requestHost0105 = "book.jetblue.com";
		int    requestPort0105 = 443;
		String requestFile0105 = "/B6/js/air/air_shopping.js" +
				"?version=201702040020";
		String requestHeader0105 = "GET " + requestFile0105 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0105, requestHost0105, requestPort0105, requestHeader0105, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0105 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 14930
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [105] <- Index 105
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [106] <- WEB ADMIN Index 106 ---
		String requestProt0106 = "https";
		String requestHost0106 = "book.jetblue.com";
		int    requestPort0106 = 443;
		String requestFile0106 = "/B6/js/seatmap.js" +
				"?version=201702040020";
		String requestHeader0106 = "GET " + requestFile0106 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0106, requestHost0106, requestPort0106, requestHeader0106, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0106 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 26740
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [106] <- Index 106
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
	 * Recorded http requests of page #2_1.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_2_1(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #2 (fragment 2 of 7)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [107] <- WEB ADMIN Index 107 ---
		String requestProt0107 = "https";
		String requestHost0107 = "book.jetblue.com";
		int    requestPort0107 = 443;
		String requestFile0107 = "/B6/css/print-options.css";
		String requestHeader0107 = "GET " + requestFile0107 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0107, requestHost0107, requestPort0107, requestHeader0107, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0107 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 608
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [107] <- Index 107
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [108] <- WEB ADMIN Index 108 ---
		String requestProt0108 = "https";
		String requestHost0108 = "book.jetblue.com";
		int    requestPort0108 = 443;
		String requestFile0108 = "/B6/css/print-visual.css";
		String requestHeader0108 = "GET " + requestFile0108 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0108, requestHost0108, requestPort0108, requestHeader0108, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0108 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 268
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [108] <- Index 108
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [109] <- WEB ADMIN Index 109 ---
		String requestProt0109 = "https";
		String requestHost0109 = "book.jetblue.com";
		int    requestPort0109 = 443;
		String requestFile0109 = "/B6/AirLowFareSearchExt.do";
		String requestContent0109 = 
				"ajaxAction=true";
		String requestHeader0109 = "POST " + requestFile0109 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"Content-Length: " + requestContent0109.length() + "\r\n" + 		// Content-Length: 15
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0109, requestHost0109, requestPort0109, requestHeader0109, requestContent0109.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0109 = null;		// support garbage collector to reduce memory
		requestContent0109 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 381
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [109] <- Index 109
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





		// --- HTTP REQUEST: Test [110] <- WEB ADMIN Index 110 ---
		String requestProt0110 = "https";
		String requestHost0110 = "book.jetblue.com";
		int    requestPort0110 = 443;
		String requestFile0110 = "/B6/pictures/header/bg-jetblue-body.png" +
				"?version=201611241128";
		String requestHeader0110 = "GET " + requestFile0110 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0110, requestHost0110, requestPort0110, requestHeader0110, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0110 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 60793
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [110] <- Index 110
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [111] <- WEB ADMIN Index 111 ---
		log();
		log("# title: JetBlue Airways");
		String requestProt0111 = "https";
		String requestHost0111 = "www.jetblue.com";
		int    requestPort0111 = 443;
		String requestFile0111 = "/content/snippets/interstitials/booking/index1.html" +
				"?version=201702040020";
		String requestHeader0111 = "GET " + requestFile0111 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0111, requestHost0111, requestPort0111, requestHeader0111, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0111 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 664
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [111] <- Index 111
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





		// --- HTTP REQUEST: Test [112] <- WEB ADMIN Index 112 ---
		String requestProt0112 = "https";
		String requestHost0112 = "www.jetblue.com";
		int    requestPort0112 = 443;
		String requestFile0112 = "/js/vendor/jquery/1.6.2/jquery.min.js";
		String requestHeader0112 = "GET " + requestFile0112 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0112, requestHost0112, requestPort0112, requestHeader0112, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0112 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "APPLICATION/X-JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 32113
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "APPLICATION/X-JAVASCRIPT", null, null);		// Test [112] <- Index 112
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [113] <- WEB ADMIN Index 113 ---
		String requestProt0113 = "https";
		String requestHost0113 = "www.jetblue.com";
		int    requestPort0113 = 443;
		String requestFile0113 = "/media/imgs/interstitials/spinner.gif";
		String requestHeader0113 = "GET " + requestFile0113 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0113, requestHost0113, requestPort0113, requestHeader0113, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0113 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 4786
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [113] <- Index 113
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [114] <- WEB ADMIN Index 114 ---
		String requestProt0114 = "https";
		String requestHost0114 = "www.jetblue.com";
		int    requestPort0114 = 443;
		String requestFile0114 = "/media/imgs/interstitials/blueberries.jpg";
		String requestHeader0114 = "GET " + requestFile0114 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0114, requestHost0114, requestPort0114, requestHeader0114, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0114 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 21738
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [114] <- Index 114
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [115] <- WEB ADMIN Index 115 ---
		String requestProt0115 = "https";
		String requestHost0115 = "www.jetblue.com";
		int    requestPort0115 = 443;
		String requestFile0115 = "/content/media/imgs/interstitials/Interstitial_500x260_FareOptions.png";
		String requestHeader0115 = "GET " + requestFile0115 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0115, requestHost0115, requestPort0115, requestHeader0115, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0115 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 20677
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [115] <- Index 115
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [116] <- WEB ADMIN Index 116 ---
		log();
		log("# title: Flight Selection");
		String requestProt0116 = "https";
		String requestHost0116 = "book.jetblue.com";
		int    requestPort0116 = 443;
		String requestFile0116 = "/B6/AirFareFamiliesFlexibleForward.do";
		String requestHeader0116 = "GET " + requestFile0116 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0116, requestHost0116, requestPort0116, requestHeader0116, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0116 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 73180
		// content test algorithm: search text
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, "Departing flights", "AND", "![no flights found]");		// Test [116] <- Index 116
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
			// extract var 'FlightResponseData'
			FlightResponseData = testURL.getContentString("ISO-8859-1");
			log("<<< FlightResponseData = " + FlightResponseData);
		}
		catch (Exception e) { FlightResponseData = null; log(e); }
		if (FlightResponseData == null)
		{
			// failure - dump wrong response content and abort current outer loop - after that start next loop
			String errorText = "*** error: unable to extract var 'FlightResponseData' from http response content";
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
		
		// execute inline script "GetFlightID"
		inlineScriptContext = executeInlineScript_1490806484385(testURL, urlCallPassed, threadStep - 1, totalLoopCounter, innerLoopContext);
		if (inlineScriptContext.isScriptAbort())
		{
			// special case: url already executed but inline script aborted - set URL as failed and abort current loop
			log("*** INLINE SCRIPT \"" + inlineScriptContext.getScriptTitle() + "\" ABORTED / CURRENT LOOP ABORTED ***");
			log("Abort Message = " + inlineScriptContext.getScriptAbortMessage());
			testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_HANDLE_AS_ERROR);
			testURL.setStatusType(HttpTestURL.STATUS_TYPE_ABORTED_BY_INLINE_SCRIPT);
			threadStep--;
			if (urlCallPassed)
				modPassed(performanceData, threadStep, -1);
			else
				modFailed(performanceData, threadStep, -1);
			performanceData.getPerformanceDataRecord(threadStep).modifyFailureActionType(HttpTestURL.FAILURE_ACTION_HANDLE_AS_ERROR);
			performanceData.setFailed(threadStep, HttpTestURL.STATUS_TYPE_ABORTED_BY_INLINE_SCRIPT, inlineScriptContext.getScriptAbortMessage(), testURL, this);
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}





		// --- HTTP REQUEST: Test [117] <- WEB ADMIN Index 117 ---
		String requestProt0117 = "https";
		String requestHost0117 = "book.jetblue.com";
		int    requestPort0117 = 443;
		String requestFile0117 = "/B6/js/jquery.tabbable.min.js" +
				"?version=201702040020";
		String requestHeader0117 = "GET " + requestFile0117 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0117, requestHost0117, requestPort0117, requestHeader0117, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0117 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 551
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [117] <- Index 117
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [118] <- WEB ADMIN Index 118 ---
		String requestProt0118 = "https";
		String requestHost0118 = "book.jetblue.com";
		int    requestPort0118 = 443;
		String requestFile0118 = "/B6/css/wcag.css" +
				"?version=201702040020";
		String requestHeader0118 = "GET " + requestFile0118 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0118, requestHost0118, requestPort0118, requestHeader0118, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0118 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 929
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [118] <- Index 118
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [119] <- WEB ADMIN Index 119 ---
		String requestProt0119 = "https";
		String requestHost0119 = "book.jetblue.com";
		int    requestPort0119 = 443;
		String requestFile0119 = "/B6/js/namespace.js" +
				"?version=201702040020";
		String requestHeader0119 = "GET " + requestFile0119 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0119, requestHost0119, requestPort0119, requestHeader0119, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0119 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 193
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [119] <- Index 119
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [120] <- WEB ADMIN Index 120 ---
		String requestProt0120 = "https";
		String requestHost0120 = "book.jetblue.com";
		int    requestPort0120 = 443;
		String requestFile0120 = "/B6/js/jquery.tabbable.js" +
				"?version=201702040020";
		String requestHeader0120 = "GET " + requestFile0120 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0120, requestHost0120, requestPort0120, requestHeader0120, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0120 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1222
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [120] <- Index 120
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [121] <- WEB ADMIN Index 121 ---
		String requestProt0121 = "https";
		String requestHost0121 = "book.jetblue.com";
		int    requestPort0121 = 443;
		String requestFile0121 = "/B6/js/jquery.tmpl.min.js" +
				"?version=201702040020";
		String requestHeader0121 = "GET " + requestFile0121 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0121, requestHost0121, requestPort0121, requestHeader0121, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0121 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 2677
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [121] <- Index 121
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [122] <- WEB ADMIN Index 122 ---
		String requestProt0122 = "https";
		String requestHost0122 = "book.jetblue.com";
		int    requestPort0122 = 443;
		String requestFile0122 = "/B6/js/image_checker.js" +
				"?version=201702040020";
		String requestHeader0122 = "GET " + requestFile0122 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0122, requestHost0122, requestPort0122, requestHeader0122, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0122 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1983
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [122] <- Index 122
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [123] <- WEB ADMIN Index 123 ---
		String requestProt0123 = "https";
		String requestHost0123 = "www.jetblue.com";
		int    requestPort0123 = 443;
		String requestFile0123 = "/js/vendor/MP_JB_enes.js";
		String requestHeader0123 = "GET " + requestFile0123 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0123, requestHost0123, requestPort0123, requestHeader0123, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0123 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "APPLICATION/X-JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 749
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "APPLICATION/X-JAVASCRIPT", null, null);		// Test [123] <- Index 123
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [124] <- WEB ADMIN Index 124 ---
		String requestProt0124 = "https";
		String requestHost0124 = "book.jetblue.com";
		int    requestPort0124 = 443;
		String requestFile0124 = "/B6/js/jquery.tools.min.js" +
				"?version=201702040020";
		String requestHeader0124 = "GET " + requestFile0124 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0124, requestHost0124, requestPort0124, requestHeader0124, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0124 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 2438
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [124] <- Index 124
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [125] <- WEB ADMIN Index 125 ---
		String requestProt0125 = "https";
		String requestHost0125 = "book.jetblue.com";
		int    requestPort0125 = 443;
		String requestFile0125 = "/B6/js/addressbook/addressBook.js" +
				"?version=201702040020";
		String requestHeader0125 = "GET " + requestFile0125 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0125, requestHost0125, requestPort0125, requestHeader0125, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0125 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 17780
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [125] <- Index 125
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [126] <- WEB ADMIN Index 126 ---
		String requestProt0126 = "https";
		String requestHost0126 = "book.jetblue.com";
		int    requestPort0126 = 443;
		String requestFile0126 = "/B6/js/tdp_tabs.js" +
				"?version=201702040020";
		String requestHeader0126 = "GET " + requestFile0126 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0126, requestHost0126, requestPort0126, requestHeader0126, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0126 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 3244
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [126] <- Index 126
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #2_1 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #2_2.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_2_2(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #2 (fragment 3 of 7)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [127] <- WEB ADMIN Index 127 ---
		String requestProt0127 = "https";
		String requestHost0127 = "book.jetblue.com";
		int    requestPort0127 = 443;
		String requestFile0127 = "/B6/js/callcenter/ccapResv.js" +
				"?version=201702040020";
		String requestHeader0127 = "GET " + requestFile0127 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0127, requestHost0127, requestPort0127, requestHeader0127, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0127 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 6648
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [127] <- Index 127
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [128] <- WEB ADMIN Index 128 ---
		String requestProt0128 = "https";
		String requestHost0128 = "book.jetblue.com";
		int    requestPort0128 = 443;
		String requestFile0128 = "/B6/js/country_state_component.js" +
				"?version=201702040020";
		String requestHeader0128 = "GET " + requestFile0128 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0128, requestHost0128, requestPort0128, requestHeader0128, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0128 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 2593
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [128] <- Index 128
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [129] <- WEB ADMIN Index 129 ---
		String requestProt0129 = "https";
		String requestHost0129 = "book.jetblue.com";
		int    requestPort0129 = 443;
		String requestFile0129 = "/B6/js/slider.js" +
				"?version=201702040020";
		String requestHeader0129 = "GET " + requestFile0129 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0129, requestHost0129, requestPort0129, requestHeader0129, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0129 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 3110
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [129] <- Index 129
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [130] <- WEB ADMIN Index 130 ---
		String requestProt0130 = "https";
		String requestHost0130 = "book.jetblue.com";
		int    requestPort0130 = 443;
		String requestFile0130 = "/B6/js/callcenter/navigationBar.js" +
				"?version=201702040020";
		String requestHeader0130 = "GET " + requestFile0130 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0130, requestHost0130, requestPort0130, requestHeader0130, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0130 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1532
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [130] <- Index 130
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [131] <- WEB ADMIN Index 131 ---
		String requestProt0131 = "https";
		String requestHost0131 = "book.jetblue.com";
		int    requestPort0131 = 443;
		String requestFile0131 = "/B6/js/policies_fees.js" +
				"?version=201702040020";
		String requestHeader0131 = "GET " + requestFile0131 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0131, requestHost0131, requestPort0131, requestHeader0131, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0131 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 2061
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [131] <- Index 131
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [132] <- WEB ADMIN Index 132 ---
		String requestProt0132 = "https";
		String requestHost0132 = "book.jetblue.com";
		int    requestPort0132 = 443;
		String requestFile0132 = "/B6/js/air/air_image_checker.js" +
				"?version=201702040020";
		String requestHeader0132 = "GET " + requestFile0132 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0132, requestHost0132, requestPort0132, requestHeader0132, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0132 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 410
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [132] <- Index 132
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [133] <- WEB ADMIN Index 133 ---
		String requestProt0133 = "https";
		String requestHost0133 = "book.jetblue.com";
		int    requestPort0133 = 443;
		String requestFile0133 = "/B6/js/itinerary_summary.js" +
				"?version=201702040020";
		String requestHeader0133 = "GET " + requestFile0133 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0133, requestHost0133, requestPort0133, requestHeader0133, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0133 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 7238
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [133] <- Index 133
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [134] <- WEB ADMIN Index 134 ---
		String requestProt0134 = "https";
		String requestHost0134 = "book.jetblue.com";
		int    requestPort0134 = 443;
		String requestFile0134 = "/B6/js/air/air_search_results.js" +
				"?version=201702040020";
		String requestHeader0134 = "GET " + requestFile0134 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0134, requestHost0134, requestPort0134, requestHeader0134, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0134 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 2993
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [134] <- Index 134
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [135] <- WEB ADMIN Index 135 ---
		String requestProt0135 = "https";
		String requestHost0135 = "book.jetblue.com";
		int    requestPort0135 = 443;
		String requestFile0135 = "/B6/js/air_fare_family_tooltip.js" +
				"?version=201702040020";
		String requestHeader0135 = "GET " + requestFile0135 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0135, requestHost0135, requestPort0135, requestHeader0135, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0135 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 605
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [135] <- Index 135
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [136] <- WEB ADMIN Index 136 ---
		String requestProt0136 = "https";
		String requestHost0136 = "book.jetblue.com";
		int    requestPort0136 = 443;
		String requestFile0136 = "/B6/js/air/air_search_results_filter_sort.js" +
				"?version=201702040020";
		String requestHeader0136 = "GET " + requestFile0136 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0136, requestHost0136, requestPort0136, requestHeader0136, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0136 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 2273
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [136] <- Index 136
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [137] <- WEB ADMIN Index 137 ---
		String requestProt0137 = "https";
		String requestHost0137 = "book.jetblue.com";
		int    requestPort0137 = 443;
		String requestFile0137 = "/TDPMedia/java/merchandizing/static-site/js/lib/require-2.1.2.min.js" +
				"?version=201702040020";
		String requestHeader0137 = "GET " + requestFile0137 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0137, requestHost0137, requestPort0137, requestHeader0137, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0137 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 5979
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [137] <- Index 137
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [138] <- WEB ADMIN Index 138 ---
		String requestProt0138 = "https";
		String requestHost0138 = "book.jetblue.com";
		int    requestPort0138 = 443;
		String requestFile0138 = "/B6/pictures/content/best-fare-promo.png";
		String requestHeader0138 = "GET " + requestFile0138 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0138, requestHost0138, requestPort0138, requestHeader0138, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0138 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 7851
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [138] <- Index 138
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [139] <- WEB ADMIN Index 139 ---
		String requestProt0139 = "https";
		String requestHost0139 = "book.jetblue.com";
		int    requestPort0139 = 443;
		String requestFile0139 = "/B6/pictures/content/logo-mosaic.jpg";
		String requestHeader0139 = "GET " + requestFile0139 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0139, requestHost0139, requestPort0139, requestHeader0139, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0139 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 3128
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [139] <- Index 139
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [140] <- WEB ADMIN Index 140 ---
		String requestProt0140 = "https";
		String requestHost0140 = "book.jetblue.com";
		int    requestPort0140 = 443;
		String requestFile0140 = "/B6/pictures/icons/i-new-window.png";
		String requestHeader0140 = "GET " + requestFile0140 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0140, requestHost0140, requestPort0140, requestHeader0140, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0140 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1101
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [140] <- Index 140
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [141] <- WEB ADMIN Index 141 ---
		String requestProt0141 = "https";
		String requestHost0141 = "book.jetblue.com";
		int    requestPort0141 = 443;
		String requestFile0141 = "/B6/pictures/icons/favicon.ico";
		String requestHeader0141 = "GET " + requestFile0141 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0141, requestHost0141, requestPort0141, requestHeader0141, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0141 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/VND.MICROSOFT.ICON", header text fragment = [verification disabled], recorded content size = 4880
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/VND.MICROSOFT.ICON", null, null);		// Test [141] <- Index 141
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [142] <- WEB ADMIN Index 142 ---
		String requestProt0142 = "https";
		String requestHost0142 = "book.jetblue.com";
		int    requestPort0142 = 443;
		String requestFile0142 = "/B6/pictures/misc/x.gif" +
				"?version=201611241128";
		String requestHeader0142 = "GET " + requestFile0142 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0142, requestHost0142, requestPort0142, requestHeader0142, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0142 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 43
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [142] <- Index 142
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [143] <- WEB ADMIN Index 143 ---
		String requestProt0143 = "https";
		String requestHost0143 = "book.jetblue.com";
		int    requestPort0143 = 443;
		String requestFile0143 = "/B6/ValidateFlow.do";
		String requestContent0143 = 
				"validator=SHOPPING";
		String requestHeader0143 = "POST " + requestFile0143 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0143.length() + "\r\n" + 		// Content-Length: 18
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0143, requestHost0143, requestPort0143, requestHeader0143, requestContent0143.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0143 = null;		// support garbage collector to reduce memory
		requestContent0143 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 78
		// content test algorithm: search text
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, "flowValidationResult: \"OK\"");		// Test [143] <- Index 143
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





		// --- HTTP REQUEST: Test [144] <- WEB ADMIN Index 144 ---
		String requestProt0144 = "https";
		String requestHost0144 = "book.jetblue.com";
		int    requestPort0144 = 443;
		String requestFile0144 = "/TDPMedia/java/merchandizing/static-site/js/com/datalex/tdp/merchandizing/cui/merchandizing.js" +
				"?version=201702040020";
		String requestHeader0144 = "GET " + requestFile0144 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0144, requestHost0144, requestPort0144, requestHeader0144, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0144 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 6781
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [144] <- Index 144
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [145] <- WEB ADMIN Index 145 ---
		String requestProt0145 = "https";
		String requestHost0145 = "book.jetblue.com";
		int    requestPort0145 = 443;
		String requestFile0145 = "/TDPMedia/java/merchandizing/static-site/js/com/datalex/tdp/merchandizing/cui/merchandizing_image_checker.js" +
				"?version=201702040020";
		String requestHeader0145 = "GET " + requestFile0145 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0145, requestHost0145, requestPort0145, requestHeader0145, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0145 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1307
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [145] <- Index 145
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [146] <- WEB ADMIN Index 146 ---
		String requestProt0146 = "https";
		String requestHost0146 = "book.jetblue.com";
		int    requestPort0146 = 443;
		String requestFile0146 = "/TDPMedia/java/merchandizing/static-site/js/com/datalex/tdp/merchandizing/cui/merchandizing_filter.js" +
				"?version=201702040020";
		String requestHeader0146 = "GET " + requestFile0146 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0146, requestHost0146, requestPort0146, requestHeader0146, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0146 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1911
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [146] <- Index 146
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #2_2 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #2_3.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_2_3(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #2 (fragment 4 of 7)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [147] <- WEB ADMIN Index 147 ---
		String requestProt0147 = "https";
		String requestHost0147 = "book.jetblue.com";
		int    requestPort0147 = 443;
		String requestFile0147 = "/assets/header/img/bg-jetblue-body-logoonly.jpg";
		String requestHeader0147 = "GET " + requestFile0147 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0147, requestHost0147, requestPort0147, requestHeader0147, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0147 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 21738
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [147] <- Index 147
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [148] <- WEB ADMIN Index 148 ---
		String requestProt0148 = "https";
		String requestHost0148 = "book.jetblue.com";
		int    requestPort0148 = 443;
		String requestFile0148 = "/assets/header/img/lft-arrow.png";
		String requestHeader0148 = "GET " + requestFile0148 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0148, requestHost0148, requestPort0148, requestHeader0148, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0148 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 158
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [148] <- Index 148
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [149] <- WEB ADMIN Index 149 ---
		String requestProt0149 = "https";
		String requestHost0149 = "book.jetblue.com";
		int    requestPort0149 = 443;
		String requestFile0149 = "/B6/pictures/icons/navigation/spr-prev-next-week.png" +
				"?version=201611241128";
		String requestHeader0149 = "GET " + requestFile0149 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0149, requestHost0149, requestPort0149, requestHeader0149, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0149 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1952
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [149] <- Index 149
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [150] <- WEB ADMIN Index 150 ---
		String requestProt0150 = "https";
		String requestHost0150 = "book.jetblue.com";
		int    requestPort0150 = 443;
		String requestFile0150 = "/B6/pictures/icons/i-compare-fares.png" +
				"?version=201611241128";
		String requestHeader0150 = "GET " + requestFile0150 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0150, requestHost0150, requestPort0150, requestHeader0150, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0150 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 448
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [150] <- Index 150
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [151] <- WEB ADMIN Index 151 ---
		String requestProt0151 = "https";
		String requestHost0151 = "book.jetblue.com";
		int    requestPort0151 = 443;
		String requestFile0151 = "/B6/pictures/misc/dwn-arrow-dark.png" +
				"?version=201611241128";
		String requestHeader0151 = "GET " + requestFile0151 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0151, requestHost0151, requestPort0151, requestHeader0151, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0151 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1345
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [151] <- Index 151
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [152] <- WEB ADMIN Index 152 ---
		String requestProt0152 = "https";
		String requestHost0152 = "book.jetblue.com";
		int    requestPort0152 = 443;
		String requestFile0152 = "/B6/pictures/icons/spr-ff-dark-sort.png" +
				"?version=201611241128";
		String requestHeader0152 = "GET " + requestFile0152 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0152, requestHost0152, requestPort0152, requestHeader0152, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0152 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 376
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [152] <- Index 152
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [153] <- WEB ADMIN Index 153 ---
		String requestProt0153 = "https";
		String requestHost0153 = "book.jetblue.com";
		int    requestPort0153 = 443;
		String requestFile0153 = "/B6/pictures/content/ff-benefits/i-baggage.png" +
				"?version=201611241128";
		String requestHeader0153 = "GET " + requestFile0153 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0153, requestHost0153, requestPort0153, requestHeader0153, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0153 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 661
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [153] <- Index 153
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [154] <- WEB ADMIN Index 154 ---
		String requestProt0154 = "https";
		String requestHost0154 = "book.jetblue.com";
		int    requestPort0154 = 443;
		String requestFile0154 = "/B6/pictures/icons/spr-ff-sort.png" +
				"?version=201611241128";
		String requestHeader0154 = "GET " + requestFile0154 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0154, requestHost0154, requestPort0154, requestHeader0154, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0154 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 269
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [154] <- Index 154
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [155] <- WEB ADMIN Index 155 ---
		String requestProt0155 = "https";
		String requestHost0155 = "book.jetblue.com";
		int    requestPort0155 = 443;
		String requestFile0155 = "/B6/pictures/misc/ff-header-ribbon.png" +
				"?version=201611241128";
		String requestHeader0155 = "GET " + requestFile0155 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0155, requestHost0155, requestPort0155, requestHeader0155, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0155 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 506
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [155] <- Index 155
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [156] <- WEB ADMIN Index 156 ---
		String requestProt0156 = "https";
		String requestHost0156 = "book.jetblue.com";
		int    requestPort0156 = 443;
		String requestFile0156 = "/B6/pictures/misc/location-hint-arrow.png" +
				"?version=201611241128";
		String requestHeader0156 = "GET " + requestFile0156 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0156, requestHost0156, requestPort0156, requestHeader0156, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0156 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 308
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [156] <- Index 156
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [157] <- WEB ADMIN Index 157 ---
		String requestProt0157 = "https";
		String requestHost0157 = "book.jetblue.com";
		int    requestPort0157 = 443;
		String requestFile0157 = "/B6/pictures/icons/direction-arrow.png" +
				"?version=201611241128";
		String requestHeader0157 = "GET " + requestFile0157 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0157, requestHost0157, requestPort0157, requestHeader0157, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0157 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 540
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [157] <- Index 157
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [158] <- WEB ADMIN Index 158 ---
		String requestProt0158 = "https";
		String requestHost0158 = "book.jetblue.com";
		int    requestPort0158 = 443;
		String requestFile0158 = "/B6/pictures/misc/spr-radio.png" +
				"?version=201611241128";
		String requestHeader0158 = "GET " + requestFile0158 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0158, requestHost0158, requestPort0158, requestHeader0158, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0158 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 2252
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [158] <- Index 158
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [159] <- WEB ADMIN Index 159 ---
		String requestProt0159 = "https";
		String requestHost0159 = "book.jetblue.com";
		int    requestPort0159 = 443;
		String requestFile0159 = "/B6/pictures/icons/i-flight.png" +
				"?version=201611241128";
		String requestHeader0159 = "GET " + requestFile0159 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0159, requestHost0159, requestPort0159, requestHeader0159, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0159 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 393
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [159] <- Index 159
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [160] <- WEB ADMIN Index 160 ---
		String requestProt0160 = "https";
		String requestHost0160 = "book.jetblue.com";
		int    requestPort0160 = 443;
		String requestFile0160 = "/B6/pictures/icons/i_minus2.gif" +
				"?version=201611241128";
		String requestHeader0160 = "GET " + requestFile0160 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0160, requestHost0160, requestPort0160, requestHeader0160, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0160 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1105
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [160] <- Index 160
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [161] <- WEB ADMIN Index 161 ---
		String requestProt0161 = "https";
		String requestHost0161 = "book.jetblue.com";
		int    requestPort0161 = 443;
		String requestFile0161 = "/B6/pictures/misc/spr-checkbox.gif" +
				"?version=201611241128";
		String requestHeader0161 = "GET " + requestFile0161 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0161, requestHost0161, requestPort0161, requestHeader0161, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0161 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1920
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [161] <- Index 161
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [162] <- WEB ADMIN Index 162 ---
		String requestProt0162 = "https";
		String requestHost0162 = "book.jetblue.com";
		int    requestPort0162 = 443;
		String requestFile0162 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/main.js";
		String requestHeader0162 = "GET " + requestFile0162 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0162, requestHost0162, requestPort0162, requestHeader0162, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0162 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 265
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [162] <- Index 162
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [163] <- WEB ADMIN Index 163 ---
		String requestProt0163 = "https";
		String requestHost0163 = "book.jetblue.com";
		int    requestPort0163 = 443;
		String requestFile0163 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/widget/factory.js";
		String requestHeader0163 = "GET " + requestFile0163 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0163, requestHost0163, requestPort0163, requestHeader0163, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0163 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1297
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [163] <- Index 163
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [164] <- WEB ADMIN Index 164 ---
		String requestProt0164 = "https";
		String requestHost0164 = "book.jetblue.com";
		int    requestPort0164 = 443;
		String requestFile0164 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/widget/theme/consumer/theme.js";
		String requestHeader0164 = "GET " + requestFile0164 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0164, requestHost0164, requestPort0164, requestHeader0164, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0164 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 378
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [164] <- Index 164
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [165] <- WEB ADMIN Index 165 ---
		String requestProt0165 = "https";
		String requestHost0165 = "book.jetblue.com";
		int    requestPort0165 = 443;
		String requestFile0165 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/config.js";
		String requestHeader0165 = "GET " + requestFile0165 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0165, requestHost0165, requestPort0165, requestHeader0165, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0165 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 115
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [165] <- Index 165
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [166] <- WEB ADMIN Index 166 ---
		String requestProt0166 = "https";
		String requestHost0166 = "book.jetblue.com";
		int    requestPort0166 = 443;
		String requestFile0166 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/console.js";
		String requestHeader0166 = "GET " + requestFile0166 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0166, requestHost0166, requestPort0166, requestHeader0166, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0166 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 120
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [166] <- Index 166
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #2_3 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #2_4.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_2_4(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #2 (fragment 5 of 7)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [167] <- WEB ADMIN Index 167 ---
		String requestProt0167 = "https";
		String requestHost0167 = "book.jetblue.com";
		int    requestPort0167 = 443;
		String requestFile0167 = "/B6/html/empty.html" +
				"?version=201702040020";
		String requestHeader0167 = "GET " + requestFile0167 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0167, requestHost0167, requestPort0167, requestHeader0167, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0167 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 195
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [167] <- Index 167
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





		// --- HTTP REQUEST: Test [168] <- WEB ADMIN Index 168 ---
		String requestProt0168 = "https";
		String requestHost0168 = "book.jetblue.com";
		int    requestPort0168 = 443;
		String requestFile0168 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/widget/theme/consumer/css/style.css";
		String requestHeader0168 = "GET " + requestFile0168 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0168, requestHost0168, requestPort0168, requestHeader0168, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0168 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 107
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [168] <- Index 168
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [169] <- WEB ADMIN Index 169 ---
		String requestProt0169 = "https";
		String requestHost0169 = "book.jetblue.com";
		int    requestPort0169 = 443;
		String requestFile0169 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=FLIGHT_RESULTS";
		String requestHeader0169 = "GET " + requestFile0169 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0169, requestHost0169, requestPort0169, requestHeader0169, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0169 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 212
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [169] <- Index 169
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





		// --- HTTP REQUEST: Test [170] <- WEB ADMIN Index 170 ---
		String requestProt0170 = "https";
		String requestHost0170 = "book.jetblue.com";
		int    requestPort0170 = 443;
		String requestFile0170 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/widget/theme/consumer/css/travel-options.css";
		String requestHeader0170 = "GET " + requestFile0170 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0170, requestHost0170, requestPort0170, requestHeader0170, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0170 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 973
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [170] <- Index 170
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [171] <- WEB ADMIN Index 171 ---
		String requestProt0171 = "https";
		String requestHost0171 = "book.jetblue.com";
		int    requestPort0171 = 443;
		String requestFile0171 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/widget/theme/consumer/css/extras.css";
		String requestHeader0171 = "GET " + requestFile0171 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0171, requestHost0171, requestPort0171, requestHeader0171, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0171 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 1657
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [171] <- Index 171
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [172] <- WEB ADMIN Index 172 ---
		String requestProt0172 = "https";
		String requestHost0172 = "book.jetblue.com";
		int    requestPort0172 = 443;
		String requestFile0172 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/widget/theme/consumer/css/package-cross-sell.css";
		String requestHeader0172 = "GET " + requestFile0172 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0172, requestHost0172, requestPort0172, requestHeader0172, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0172 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 3308
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [172] <- Index 172
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [173] <- WEB ADMIN Index 173 ---
		String requestProt0173 = "https";
		String requestHost0173 = "book.jetblue.com";
		int    requestPort0173 = 443;
		String requestFile0173 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/widget/theme/consumer/css/cobrand.css";
		String requestHeader0173 = "GET " + requestFile0173 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0173, requestHost0173, requestPort0173, requestHeader0173, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0173 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 823
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [173] <- Index 173
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [174] <- WEB ADMIN Index 174 ---
		String requestProt0174 = "https";
		String requestHost0174 = "book.jetblue.com";
		int    requestPort0174 = 443;
		String requestFile0174 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/widget/theme/consumer/css/agaInsurance.css";
		String requestHeader0174 = "GET " + requestFile0174 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0174, requestHost0174, requestPort0174, requestHeader0174, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0174 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 998
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [174] <- Index 174
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [175] <- WEB ADMIN Index 175 ---
		String requestProt0175 = "https";
		String requestHost0175 = "book.jetblue.com";
		int    requestPort0175 = 443;
		String requestFile0175 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=AIR_COMBINABLE_FFF_SEARCH_RESULTS_BOT";
		String requestHeader0175 = "GET " + requestFile0175 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0175, requestHost0175, requestPort0175, requestHeader0175, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0175 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 212
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [175] <- Index 175
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





		// --- HTTP REQUEST: Test [176] <- WEB ADMIN Index 176 ---
		String requestProt0176 = "https";
		String requestHost0176 = "book.jetblue.com";
		int    requestPort0176 = 443;
		String requestFile0176 = "/B6/pictures/icons/i_plus2.gif" +
				"?version=201611241128";
		String requestHeader0176 = "GET " + requestFile0176 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0176, requestHost0176, requestPort0176, requestHeader0176, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0176 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1111
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [176] <- Index 176
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [177] <- WEB ADMIN Index 177 ---
		String requestProt0177 = "https";
		String requestHost0177 = "book.jetblue.com";
		int    requestPort0177 = 443;
		String requestFile0177 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/widget/touchpoint/touchpoint.js";
		String requestHeader0177 = "GET " + requestFile0177 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0177, requestHost0177, requestPort0177, requestHeader0177, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0177 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 204
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [177] <- Index 177
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [178] <- WEB ADMIN Index 178 ---
		String requestProt0178 = "https";
		String requestHost0178 = "book.jetblue.com";
		int    requestPort0178 = 443;
		String requestFile0178 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/widget/group/co_brand_content.js";
		String requestHeader0178 = "GET " + requestFile0178 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0178, requestHost0178, requestPort0178, requestHeader0178, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0178 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 300
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [178] <- Index 178
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [179] <- WEB ADMIN Index 179 ---
		String requestProt0179 = "https";
		String requestHost0179 = "book.jetblue.com";
		int    requestPort0179 = 443;
		String requestFile0179 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/widget/group/ext_offer.js";
		String requestHeader0179 = "GET " + requestFile0179 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0179, requestHost0179, requestPort0179, requestHeader0179, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0179 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 435
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [179] <- Index 179
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [180] <- WEB ADMIN Index 180 ---
		String requestProt0180 = "https";
		String requestHost0180 = "book.jetblue.com";
		int    requestPort0180 = 443;
		String requestFile0180 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/widget/group/adv_content.js";
		String requestHeader0180 = "GET " + requestFile0180 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0180, requestHost0180, requestPort0180, requestHeader0180, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0180 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 787
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [180] <- Index 180
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [181] <- WEB ADMIN Index 181 ---
		String requestProt0181 = "https";
		String requestHost0181 = "book.jetblue.com";
		int    requestPort0181 = 443;
		String requestFile0181 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/widget/group/co_brand_content_bot.js";
		String requestHeader0181 = "GET " + requestFile0181 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0181, requestHost0181, requestPort0181, requestHeader0181, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0181 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 305
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [181] <- Index 181
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [182] <- WEB ADMIN Index 182 ---
		String requestProt0182 = "https";
		String requestHost0182 = "book.jetblue.com";
		int    requestPort0182 = 443;
		String requestFile0182 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/widget/base.js";
		String requestHeader0182 = "GET " + requestFile0182 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0182, requestHost0182, requestPort0182, requestHeader0182, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0182 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 999
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [182] <- Index 182
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [183] <- WEB ADMIN Index 183 ---
		String requestProt0183 = "https";
		String requestHost0183 = "book.jetblue.com";
		int    requestPort0183 = 443;
		String requestFile0183 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/template/co_brand_content.soy.js";
		String requestHeader0183 = "GET " + requestFile0183 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0183, requestHost0183, requestPort0183, requestHeader0183, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0183 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1080
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [183] <- Index 183
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [184] <- WEB ADMIN Index 184 ---
		String requestProt0184 = "https";
		String requestHost0184 = "book.jetblue.com";
		int    requestPort0184 = 443;
		String requestFile0184 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/template/group_adv_content.soy.js";
		String requestHeader0184 = "GET " + requestFile0184 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0184, requestHost0184, requestPort0184, requestHeader0184, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0184 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 341
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [184] <- Index 184
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [185] <- WEB ADMIN Index 185 ---
		String requestProt0185 = "https";
		String requestHost0185 = "book.jetblue.com";
		int    requestPort0185 = 443;
		String requestFile0185 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/template/group_ext_offer.soy.js";
		String requestHeader0185 = "GET " + requestFile0185 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0185, requestHost0185, requestPort0185, requestHeader0185, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0185 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 401
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [185] <- Index 185
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [186] <- WEB ADMIN Index 186 ---
		String requestProt0186 = "https";
		String requestHost0186 = "book.jetblue.com";
		int    requestPort0186 = 443;
		String requestFile0186 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/template/co_brand_content_bot.soy.js";
		String requestHeader0186 = "GET " + requestFile0186 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0186, requestHost0186, requestPort0186, requestHeader0186, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0186 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 819
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [186] <- Index 186
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #2_4 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #2_5.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_2_5(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #2 (fragment 6 of 7)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [187] <- WEB ADMIN Index 187 ---
		String requestProt0187 = "https";
		String requestHost0187 = "book.jetblue.com";
		int    requestPort0187 = 443;
		String requestFile0187 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/template/touchpoint.soy.js";
		String requestHeader0187 = "GET " + requestFile0187 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0187, requestHost0187, requestPort0187, requestHeader0187, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0187 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 338
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [187] <- Index 187
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [188] <- WEB ADMIN Index 188 ---
		String requestProt0188 = "https";
		String requestHost0188 = "book.jetblue.com";
		int    requestPort0188 = 443;
		String requestFile0188 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/lib/soyutils.js";
		String requestHeader0188 = "GET " + requestFile0188 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0188, requestHost0188, requestPort0188, requestHeader0188, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0188 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 16119
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [188] <- Index 188
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [189] <- WEB ADMIN Index 189 ---
		String requestProt0189 = "https";
		String requestHost0189 = "book.jetblue.com";
		int    requestPort0189 = 443;
		String requestFile0189 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/core.js";
		String requestHeader0189 = "GET " + requestFile0189 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0189, requestHost0189, requestPort0189, requestHeader0189, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0189 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1010
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [189] <- Index 189
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [190] <- WEB ADMIN Index 190 ---
		String requestProt0190 = "https";
		String requestHost0190 = "book.jetblue.com";
		int    requestPort0190 = 443;
		String requestFile0190 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/functions.js";
		String requestHeader0190 = "GET " + requestFile0190 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0190, requestHost0190, requestPort0190, requestHeader0190, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0190 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 332
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [190] <- Index 190
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [191] <- WEB ADMIN Index 191 ---
		String requestProt0191 = "https";
		String requestHost0191 = "book.jetblue.com";
		int    requestPort0191 = 443;
		String requestFile0191 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/lib/underscore-1.4.4.min.js";
		String requestHeader0191 = "GET " + requestFile0191 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0191, requestHost0191, requestPort0191, requestHeader0191, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0191 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 4589
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [191] <- Index 191
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [192] <- WEB ADMIN Index 192 ---
		String requestProt0192 = "https";
		String requestHost0192 = "book.jetblue.com";
		int    requestPort0192 = 443;
		String requestFile0192 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/i18n-config.js";
		String requestHeader0192 = "GET " + requestFile0192 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0192, requestHost0192, requestPort0192, requestHeader0192, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0192 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 108
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [192] <- Index 192
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [193] <- WEB ADMIN Index 193 ---
		String requestProt0193 = "https";
		String requestHost0193 = "book.jetblue.com";
		int    requestPort0193 = 443;
		String requestFile0193 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/lib/jed-0.5.0.js";
		String requestHeader0193 = "GET " + requestFile0193 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0193, requestHost0193, requestPort0193, requestHeader0193, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0193 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 10947
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [193] <- Index 193
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [194] <- WEB ADMIN Index 194 ---
		String requestProt0194 = "https";
		String requestHost0194 = "book.jetblue.com";
		int    requestPort0194 = 443;
		String requestFile0194 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/lib/i18n-2.0.1.js";
		String requestHeader0194 = "GET " + requestFile0194 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0194, requestHost0194, requestPort0194, requestHeader0194, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0194 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 2388
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [194] <- Index 194
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [195] <- WEB ADMIN Index 195 ---
		String requestProt0195 = "https";
		String requestHost0195 = "book.jetblue.com";
		int    requestPort0195 = 443;
		String requestFile0195 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/lib/backbone-0.9.10.min.js";
		String requestHeader0195 = "GET " + requestFile0195 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0195, requestHost0195, requestPort0195, requestHeader0195, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0195 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 6299
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [195] <- Index 195
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [196] <- WEB ADMIN Index 196 ---
		String requestProt0196 = "https";
		String requestHost0196 = "book.jetblue.com";
		int    requestPort0196 = 443;
		String requestFile0196 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/nls/messages.js";
		String requestHeader0196 = "GET " + requestFile0196 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0196, requestHost0196, requestPort0196, requestHeader0196, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0196 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 225
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [196] <- Index 196
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [197] <- WEB ADMIN Index 197 ---
		String requestProt0197 = "https";
		String requestHost0197 = "book.jetblue.com";
		int    requestPort0197 = 443;
		String requestFile0197 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/nls/en/messages.js";
		String requestHeader0197 = "GET " + requestFile0197 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0197, requestHost0197, requestPort0197, requestHeader0197, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0197 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 4761
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [197] <- Index 197
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [198] <- WEB ADMIN Index 198 ---
		String requestProt0198 = "https";
		String requestHost0198 = "book.jetblue.com";
		int    requestPort0198 = 443;
		String requestFile0198 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/nls/en-jbu/messages.js";
		String requestHeader0198 = "GET " + requestFile0198 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0198, requestHost0198, requestPort0198, requestHeader0198, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0198 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 578
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [198] <- Index 198
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [199] <- WEB ADMIN Index 199 ---
		String requestProt0199 = "https";
		String requestHost0199 = "book.jetblue.com";
		int    requestPort0199 = 443;
		String requestFile0199 = "/B6/Merchandizing.do" +
				"?touchPoint=AIR_COMBINABLE_FFF_SEARCH_RESULTS_BOT";
		String requestHeader0199 = "GET " + requestFile0199 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0199, requestHost0199, requestPort0199, requestHeader0199, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0199 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 818
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [199] <- Index 199
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





		// --- HTTP REQUEST: Test [200] <- WEB ADMIN Index 200 ---
		String requestProt0200 = "https";
		String requestHost0200 = "www.jetblue.com";
		int    requestPort0200 = 443;
		String requestFile0200 = "/content/media/imgs/cobrandcard/JB_Booking_FlightSelection_0Fee_218x375.jpg";
		String requestHeader0200 = "GET " + requestFile0200 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0200, requestHost0200, requestPort0200, requestHeader0200, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0200 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 68428
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [200] <- Index 200
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [201] <- WEB ADMIN Index 201 ---
		String requestProt0201 = "https";
		String requestHost0201 = "book.jetblue.com";
		int    requestPort0201 = 443;
		String requestFile0201 = "/B6/pictures/icons/ajax-loader.gif" +
				"?version=201611241128";
		String requestHeader0201 = "GET " + requestFile0201 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0201, requestHost0201, requestPort0201, requestHeader0201, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0201 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 2608
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [201] <- Index 201
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [202] <- WEB ADMIN Index 202 ---
		String requestProt0202 = "https";
		String requestHost0202 = "book.jetblue.com";
		int    requestPort0202 = 443;
		String requestFile0202 = "/B6/AirSelectOWCFlight.do";
		String requestContent0202 = 
				"isFareFamilySearchResult=true" +
				"&selectedItineraries=" + java.net.URLEncoder.encode(FirstFlightID) +		// recorded value = 0%2C5
				"&selectedFlightIds=" + java.net.URLEncoder.encode(FirstFlightID) +		// recorded value = 0%2C5
				"&checkedFlightIds=" + java.net.URLEncoder.encode(FirstFlightID) +		// recorded value = 0%2C5
				"&combinabilityReloadRequired=false" +
				"&flightIndex=" +
				"&flowStep=AIR_COMBINABLE_FARE_FAMILIES_FLEXIBLE_SEARCH_RESULTS" +
				"&alignment=horizontal" +
				"&context=airSelection";
		String requestHeader0202 = "POST " + requestFile0202 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0202.length() + "\r\n" + 		// Content-Length: 253
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0202, requestHost0202, requestPort0202, requestHeader0202, requestContent0202.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0202 = null;		// support garbage collector to reduce memory
		requestContent0202 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 2588
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [202] <- Index 202
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





		// --- HTTP REQUEST: Test [203] <- WEB ADMIN Index 203 ---
		String requestProt0203 = "https";
		String requestHost0203 = "book.jetblue.com";
		int    requestPort0203 = 443;
		String requestFile0203 = "/B6/pictures/icons/i_plus.gif" +
				"?version=201702040020";
		String requestHeader0203 = "GET " + requestFile0203 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0203, requestHost0203, requestPort0203, requestHeader0203, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0203 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 68
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [203] <- Index 203
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [204] <- WEB ADMIN Index 204 ---
		String requestProt0204 = "https";
		String requestHost0204 = "book.jetblue.com";
		int    requestPort0204 = 443;
		String requestFile0204 = "/B6/pictures/icons/i_minus.gif" +
				"?version=201702040020";
		String requestHeader0204 = "GET " + requestFile0204 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0204, requestHost0204, requestPort0204, requestHeader0204, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0204 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 61
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [204] <- Index 204
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [205] <- WEB ADMIN Index 205 ---
		String requestProt0205 = "https";
		String requestHost0205 = "book.jetblue.com";
		int    requestPort0205 = 443;
		String requestFile0205 = "/B6/Merchandizing.do" +
				"?touchPoint=FLIGHT_RESULTS";
		String requestHeader0205 = "GET " + requestFile0205 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0205, requestHost0205, requestPort0205, requestHeader0205, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0205 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 789
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [205] <- Index 205
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





		// --- HTTP REQUEST: Test [206] <- WEB ADMIN Index 206 ---
		String requestProt0206 = "https";
		String requestHost0206 = "www.jetblue.com";
		int    requestPort0206 = 443;
		String requestFile0206 = "/content/media/imgs/partners/bcus_existing_cm/TB_JBCard_2_Booking_962x100.jpg";
		String requestHeader0206 = "GET " + requestFile0206 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0206, requestHost0206, requestPort0206, requestHeader0206, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0206 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 71419
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [206] <- Index 206
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #2_5 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #2_6.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_2_6(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #2 (fragment 7 of 7)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [207] <- WEB ADMIN Index 207 ---
		String requestProt0207 = "https";
		String requestHost0207 = "book.jetblue.com";
		int    requestPort0207 = 443;
		String requestFile0207 = "/B6/pictures/icons/i_dialog_close.gif" +
				"?version=201702040020";
		String requestHeader0207 = "GET " + requestFile0207 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0207, requestHost0207, requestPort0207, requestHeader0207, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0207 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 368
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [207] <- Index 207
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [208] <- WEB ADMIN Index 208 ---
		String requestProt0208 = "https";
		String requestHost0208 = "book.jetblue.com";
		int    requestPort0208 = 443;
		String requestFile0208 = "/B6/pictures/icons/i_dialog_close.gif" +
				"?version=201611241128";
		String requestHeader0208 = "GET " + requestFile0208 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0208, requestHost0208, requestPort0208, requestHeader0208, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0208 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 368
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [208] <- Index 208
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #2_6 successful done
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
		// # Page #3: Add Information
		// # ------------------------



		// --- HTTP REQUEST: Test [210] <- WEB ADMIN Index 210 ---
		String requestProt0210 = "https";
		String requestHost0210 = "book.jetblue.com";
		int    requestPort0210 = 443;
		String requestFile0210 = "/B6/ValidateFlow.do";
		String requestContent0210 = 
				"validator=SHOPPING_ON_SUBMIT";
		String requestHeader0210 = "POST " + requestFile0210 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0210.length() + "\r\n" + 		// Content-Length: 28
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0210, requestHost0210, requestPort0210, requestHeader0210, requestContent0210.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0210 = null;		// support garbage collector to reduce memory
		requestContent0210 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 78
		// content test algorithm: search text
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, "flowValidationResult: \"OK\"");		// Test [210] <- Index 210
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





		// --- HTTP REQUEST: Test [211] <- WEB ADMIN Index 211 ---
		String requestProt0211 = "https";
		String requestHost0211 = "book.jetblue.com";
		int    requestPort0211 = 443;
		String requestFile0211 = "/B6/ValidateFormAction.do";
		String requestContent0211 = 
				"validateAction=AirVerifyFareFamiliesItineraryCheckLogin" +
				"&hiddenFlightSelection=" + java.net.URLEncoder.encode(FirstFlightID) +		// recorded value = 0%2C5
				"&markUpMoneyAmount=" +
				"&flightItineraryId%5B0%5D=5" +
				"&flightItineraryGroupId%5B0%5D_00=5" +
				"&vsessionid=";
		String requestHeader0211 = "POST " + requestFile0211 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0211.length() + "\r\n" + 		// Content-Length: 176
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0211, requestHost0211, requestPort0211, requestHeader0211, requestContent0211.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0211 = null;		// support garbage collector to reduce memory
		requestContent0211 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 327
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [211] <- Index 211
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





		// --- HTTP REQUEST: Test [212] <- WEB ADMIN Index 212 ---
		log();
		log("# title: Consumer IBE");
		String requestProt0212 = "https";
		String requestHost0212 = "book.jetblue.com";
		int    requestPort0212 = 443;
		String requestFile0212 = "/B6/en/pages/tdp/interstitials/hotelSelection.html" +
				"?version=201702040020";
		String requestHeader0212 = "GET " + requestFile0212 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0212, requestHost0212, requestPort0212, requestHeader0212, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0212 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 528
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [212] <- Index 212
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





		// --- HTTP REQUEST: Test [213] <- WEB ADMIN Index 213 ---
		String requestProt0213 = "https";
		String requestHost0213 = "book.jetblue.com";
		int    requestPort0213 = 443;
		String requestFile0213 = "/B6/AirVerifyFareFamiliesItineraryCheckLogin.do";
		String requestContent0213 = 
				"ajaxAction=true" +
				"&hiddenFlightSelection=" + java.net.URLEncoder.encode(FirstFlightID) +		// recorded value = 0%2C5
				"&markUpMoneyAmount=" +
				"&flightItineraryId%5B0%5D=5" +
				"&flightItineraryGroupId%5B0%5D_00=5" +
				"&vsessionid=";
		String requestHeader0213 = "POST " + requestFile0213 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0213.length() + "\r\n" + 		// Content-Length: 136
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0213, requestHost0213, requestPort0213, requestHeader0213, requestContent0213.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0213 = null;		// support garbage collector to reduce memory
		requestContent0213 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 379
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [213] <- Index 213
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





		// --- HTTP REQUEST: Test [214] <- WEB ADMIN Index 214 ---
		String requestProt0214 = "https";
		String requestHost0214 = "book.jetblue.com";
		int    requestPort0214 = 443;
		String requestFile0214 = "/B6/css/all.css";
		String requestHeader0214 = "GET " + requestFile0214 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: text/css,*/*;q=0.1\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0214, requestHost0214, requestPort0214, requestHeader0214, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0214 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/CSS", header text fragment = [verification disabled], recorded content size = 241644
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/CSS", null, null);		// Test [214] <- Index 214
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [215] <- WEB ADMIN Index 215 ---
		String requestProt0215 = "https";
		String requestHost0215 = "book.jetblue.com";
		int    requestPort0215 = 443;
		String requestFile0215 = "/B6/en/pictures/content/interstitial/inter-1-spinner.gif";
		String requestHeader0215 = "GET " + requestFile0215 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0215, requestHost0215, requestPort0215, requestHeader0215, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0215 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 4786
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [215] <- Index 215
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [216] <- WEB ADMIN Index 216 ---
		String requestProt0216 = "https";
		String requestHost0216 = "book.jetblue.com";
		int    requestPort0216 = 443;
		String requestFile0216 = "/B6/en/pictures/content/interstitial/inter-1-onBoard-online.gif";
		String requestHeader0216 = "GET " + requestFile0216 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0216, requestHost0216, requestPort0216, requestHeader0216, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0216 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 16027
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [216] <- Index 216
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [217] <- WEB ADMIN Index 217 ---
		String requestProt0217 = "https";
		String requestHost0217 = "book.jetblue.com";
		int    requestPort0217 = 443;
		String requestFile0217 = "/B6/en/pictures/content/interstitial/inter-1-fly-fi.gif";
		String requestHeader0217 = "GET " + requestFile0217 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0217, requestHost0217, requestPort0217, requestHeader0217, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0217 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 8656
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [217] <- Index 217
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [218] <- WEB ADMIN Index 218 ---
		String requestProt0218 = "https";
		String requestHost0218 = "book.jetblue.com";
		int    requestPort0218 = 443;
		String requestFile0218 = "/B6/en/pictures/content/interstitial/inter-1-fly-fi-logo.gif";
		String requestHeader0218 = "GET " + requestFile0218 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0218, requestHost0218, requestPort0218, requestHeader0218, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0218 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 4779
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [218] <- Index 218
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [219] <- WEB ADMIN Index 219 ---
		String requestProt0219 = "https";
		String requestHost0219 = "book.jetblue.com";
		int    requestPort0219 = 443;
		String requestFile0219 = "/B6/pictures/decor/inter-1-bg.gif" +
				"?version=201611241128";
		String requestHeader0219 = "GET " + requestFile0219 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0219, requestHost0219, requestPort0219, requestHeader0219, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0219 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 2248
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [219] <- Index 219
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [220] <- WEB ADMIN Index 220 ---
		log();
		log("# title: Traveler Details");
		String requestProt0220 = "https";
		String requestHost0220 = "book.jetblue.com";
		int    requestPort0220 = 443;
		String requestFile0220 = "/B6/TravelersDetailsForwardAction.do";
		String requestHeader0220 = "GET " + requestFile0220 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0220, requestHost0220, requestPort0220, requestHeader0220, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0220 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 68425
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [220] <- Index 220
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





		// --- HTTP REQUEST: Test [221] <- WEB ADMIN Index 221 ---
		String requestProt0221 = "https";
		String requestHost0221 = "book.jetblue.com";
		int    requestPort0221 = 443;
		String requestFile0221 = "/B6/js/hotel/hotel_common.js" +
				"?version=201702040020";
		String requestHeader0221 = "GET " + requestFile0221 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0221, requestHost0221, requestPort0221, requestHeader0221, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0221 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 5805
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [221] <- Index 221
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [222] <- WEB ADMIN Index 222 ---
		String requestProt0222 = "https";
		String requestHost0222 = "book.jetblue.com";
		int    requestPort0222 = 443;
		String requestFile0222 = "/B6/js/cmn_travellerdetails.js" +
				"?version=201702040020";
		String requestHeader0222 = "GET " + requestFile0222 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0222, requestHost0222, requestPort0222, requestHeader0222, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0222 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 28411
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [222] <- Index 222
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [223] <- WEB ADMIN Index 223 ---
		String requestProt0223 = "https";
		String requestHost0223 = "book.jetblue.com";
		int    requestPort0223 = 443;
		String requestFile0223 = "/B6/pictures/icons/true-blue-logo.gif" +
				"?version=201702040020";
		String requestHeader0223 = "GET " + requestFile0223 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0223, requestHost0223, requestPort0223, requestHeader0223, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0223 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 2575
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [223] <- Index 223
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [224] <- WEB ADMIN Index 224 ---
		String requestProt0224 = "https";
		String requestHost0224 = "book.jetblue.com";
		int    requestPort0224 = 443;
		String requestFile0224 = "/B6/ValidateFlow.do";
		String requestContent0224 = 
				"validator=PASSENGERS";
		String requestHeader0224 = "POST " + requestFile0224 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0224.length() + "\r\n" + 		// Content-Length: 20
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0224, requestHost0224, requestPort0224, requestHeader0224, requestContent0224.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0224 = null;		// support garbage collector to reduce memory
		requestContent0224 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 78
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [224] <- Index 224
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





		// --- HTTP REQUEST: Test [225] <- WEB ADMIN Index 225 ---
		String requestProt0225 = "https";
		String requestHost0225 = "book.jetblue.com";
		int    requestPort0225 = 443;
		String requestFile0225 = "/B6/pictures/misc/true-blue-block-sep.gif" +
				"?version=201611241128";
		String requestHeader0225 = "GET " + requestFile0225 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0225, requestHost0225, requestPort0225, requestHeader0225, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0225 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 43
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [225] <- Index 225
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [226] <- WEB ADMIN Index 226 ---
		String requestProt0226 = "https";
		String requestHost0226 = "book.jetblue.com";
		int    requestPort0226 = 443;
		String requestFile0226 = "/B6/pictures/icons/i_mandatory.png" +
				"?version=201611241128";
		String requestHeader0226 = "GET " + requestFile0226 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0226, requestHost0226, requestPort0226, requestHeader0226, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0226 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 226
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [226] <- Index 226
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [227] <- WEB ADMIN Index 227 ---
		String requestProt0227 = "https";
		String requestHost0227 = "book.jetblue.com";
		int    requestPort0227 = 443;
		String requestFile0227 = "/B6/pictures/misc/bg-pax-number.png" +
				"?version=201611241128";
		String requestHeader0227 = "GET " + requestFile0227 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0227, requestHost0227, requestPort0227, requestHeader0227, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0227 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1021
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [227] <- Index 227
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [228] <- WEB ADMIN Index 228 ---
		String requestProt0228 = "https";
		String requestHost0228 = "book.jetblue.com";
		int    requestPort0228 = 443;
		String requestFile0228 = "/B6/pictures/misc/form_sep.gif" +
				"?version=201611241128";
		String requestHeader0228 = "GET " + requestFile0228 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0228, requestHost0228, requestPort0228, requestHeader0228, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0228 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 43
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [228] <- Index 228
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [229] <- WEB ADMIN Index 229 ---
		String requestProt0229 = "https";
		String requestHost0229 = "book.jetblue.com";
		int    requestPort0229 = 443;
		String requestFile0229 = "/B6/pictures/misc/input-text-bg.gif" +
				"?version=201611241128";
		String requestHeader0229 = "GET " + requestFile0229 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0229, requestHost0229, requestPort0229, requestHeader0229, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0229 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 348
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [229] <- Index 229
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
	 * Recorded http requests of page #3_1.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_3_1(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #3 (fragment 2 of 2)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [230] <- WEB ADMIN Index 230 ---
		String requestProt0230 = "https";
		String requestHost0230 = "book.jetblue.com";
		int    requestPort0230 = 443;
		String requestFile0230 = "/B6/pictures/misc/spr-checkbox-type2.gif" +
				"?version=201611241128";
		String requestHeader0230 = "GET " + requestFile0230 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0230, requestHost0230, requestPort0230, requestHeader0230, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0230 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 507
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [230] <- Index 230
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [231] <- WEB ADMIN Index 231 ---
		String requestProt0231 = "https";
		String requestHost0231 = "book.jetblue.com";
		int    requestPort0231 = 443;
		String requestFile0231 = "/B6/pictures/icons/i_info2.png" +
				"?version=201611241128";
		String requestHeader0231 = "GET " + requestFile0231 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0231, requestHost0231, requestPort0231, requestHeader0231, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0231 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 507
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [231] <- Index 231
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [232] <- WEB ADMIN Index 232 ---
		String requestProt0232 = "https";
		String requestHost0232 = "book.jetblue.com";
		int    requestPort0232 = 443;
		String requestFile0232 = "/B6/pictures/icons/i_minus.gif" +
				"?version=201611241128";
		String requestHeader0232 = "GET " + requestFile0232 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0232, requestHost0232, requestPort0232, requestHeader0232, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0232 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 61
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [232] <- Index 232
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [233] <- WEB ADMIN Index 233 ---
		String requestProt0233 = "https";
		String requestHost0233 = "book.jetblue.com";
		int    requestPort0233 = 443;
		String requestFile0233 = "/B6/pictures/misc/custom-select-arrow.gif";
		String requestHeader0233 = "GET " + requestFile0233 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0233, requestHost0233, requestPort0233, requestHeader0233, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0233 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1125
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [233] <- Index 233
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [234] <- WEB ADMIN Index 234 ---
		String requestProt0234 = "https";
		String requestHost0234 = "book.jetblue.com";
		int    requestPort0234 = 443;
		String requestFile0234 = "/B6/pictures/icons/navigation/i_back.gif" +
				"?version=201611241128";
		String requestHeader0234 = "GET " + requestFile0234 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0234, requestHost0234, requestPort0234, requestHeader0234, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0234 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1237
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [234] <- Index 234
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [235] <- WEB ADMIN Index 235 ---
		String requestProt0235 = "https";
		String requestHost0235 = "book.jetblue.com";
		int    requestPort0235 = 443;
		String requestFile0235 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=PASSENGER_DETAILS";
		String requestHeader0235 = "GET " + requestFile0235 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0235, requestHost0235, requestPort0235, requestHeader0235, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0235 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 183
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [235] <- Index 235
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





		// --- HTTP REQUEST: Test [236] <- WEB ADMIN Index 236 ---
		String requestProt0236 = "https";
		String requestHost0236 = "book.jetblue.com";
		int    requestPort0236 = 443;
		String requestFile0236 = "/B6/Merchandizing.do" +
				"?touchPoint=PASSENGER_DETAILS";
		String requestHeader0236 = "GET " + requestFile0236 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0236, requestHost0236, requestPort0236, requestHeader0236, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0236 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 890
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [236] <- Index 236
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





		// --- HTTP REQUEST: Test [237] <- WEB ADMIN Index 237 ---
		String requestProt0237 = "https";
		String requestHost0237 = "book.jetblue.com";
		int    requestPort0237 = 443;
		String requestFile0237 = "/B6/pictures/misc/spr-tooltip-arrows.gif" +
				"?version=201611241128";
		String requestHeader0237 = "GET " + requestFile0237 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0237, requestHost0237, requestPort0237, requestHeader0237, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0237 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 291
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [237] <- Index 237
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #3_1 successful done
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



		// --- HTTP REQUEST: Test [239] <- WEB ADMIN Index 239 ---
		String requestProt0239 = "https";
		String requestHost0239 = "book.jetblue.com";
		int    requestPort0239 = 443;
		String requestFile0239 = "/B6/ValidateFormAction.do";
		String requestContent0239 = 
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
				"&travellersInfo%5B0%5D.advancedPassengerDetails(postalCode)=90401" +
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
		String requestHeader0239 = "POST " + requestFile0239 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0239.length() + "\r\n" + 		// Content-Length: 2722
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0239, requestHost0239, requestPort0239, requestHeader0239, requestContent0239.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0239 = null;		// support garbage collector to reduce memory
		requestContent0239 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 327
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [239] <- Index 239
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





		// --- HTTP REQUEST: Test [240] <- WEB ADMIN Index 240 ---
		log();
		log("# title: JetBlue Airways");
		String requestProt0240 = "https";
		String requestHost0240 = "www.jetblue.com";
		int    requestPort0240 = 443;
		String requestFile0240 = "/content/snippets/interstitials/booking/index3.html" +
				"?version=201702040020";
		String requestHeader0240 = "GET " + requestFile0240 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0240, requestHost0240, requestPort0240, requestHeader0240, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0240 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 658
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [240] <- Index 240
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





		// --- HTTP REQUEST: Test [241] <- WEB ADMIN Index 241 ---
		String requestProt0241 = "https";
		String requestHost0241 = "book.jetblue.com";
		int    requestPort0241 = 443;
		String requestFile0241 = "/B6/UpdateReservation.do";
		String requestContent0241 = 
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
				"&travellersInfo%5B0%5D.advancedPassengerDetails(postalCode)=90401" +
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
		String requestHeader0241 = "POST " + requestFile0241 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0241.length() + "\r\n" + 		// Content-Length: 2705
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0241, requestHost0241, requestPort0241, requestHeader0241, requestContent0241.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0241 = null;		// support garbage collector to reduce memory
		requestContent0241 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 377
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [241] <- Index 241
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





		// --- HTTP REQUEST: Test [242] <- WEB ADMIN Index 242 ---
		String requestProt0242 = "https";
		String requestHost0242 = "www.jetblue.com";
		int    requestPort0242 = 443;
		String requestFile0242 = "/content/media/imgs/interstitials/intersti_ems.png";
		String requestHeader0242 = "GET " + requestFile0242 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0242, requestHost0242, requestPort0242, requestHeader0242, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0242 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 38853
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [242] <- Index 242
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [243] <- WEB ADMIN Index 243 ---
		log();
		log("# title: Seats");
		String requestProt0243 = "https";
		String requestHost0243 = "book.jetblue.com";
		int    requestPort0243 = 443;
		String requestFile0243 = "/B6/ReservationSeatSelectionForward.do";
		String requestHeader0243 = "GET " + requestFile0243 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0243, requestHost0243, requestPort0243, requestHeader0243, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0243 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 55890
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [243] <- Index 243
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





		// --- HTTP REQUEST: Test [244] <- WEB ADMIN Index 244 ---
		String requestProt0244 = "https";
		String requestHost0244 = "book.jetblue.com";
		int    requestPort0244 = 443;
		String requestFile0244 = "/B6/js/seatmap_data_reservation.js" +
				"?version=201702040020";
		String requestHeader0244 = "GET " + requestFile0244 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0244, requestHost0244, requestPort0244, requestHeader0244, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0244 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1018
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [244] <- Index 244
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [245] <- WEB ADMIN Index 245 ---
		String requestProt0245 = "https";
		String requestHost0245 = "book.jetblue.com";
		int    requestPort0245 = 443;
		String requestFile0245 = "/B6/ValidateFlow.do";
		String requestContent0245 = 
				"validator=PASSENGERS";
		String requestHeader0245 = "POST " + requestFile0245 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0245.length() + "\r\n" + 		// Content-Length: 20
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0245, requestHost0245, requestPort0245, requestHeader0245, requestContent0245.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0245 = null;		// support garbage collector to reduce memory
		requestContent0245 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 78
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [245] <- Index 245
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





		// --- HTTP REQUEST: Test [246] <- WEB ADMIN Index 246 ---
		String requestProt0246 = "https";
		String requestHost0246 = "book.jetblue.com";
		int    requestPort0246 = 443;
		String requestFile0246 = "/B6/pictures/icons/seatmap-leg-route-arrow.png" +
				"?version=201611241128";
		String requestHeader0246 = "GET " + requestFile0246 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0246, requestHost0246, requestPort0246, requestHeader0246, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0246 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1404
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [246] <- Index 246
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [247] <- WEB ADMIN Index 247 ---
		String requestProt0247 = "https";
		String requestHost0247 = "book.jetblue.com";
		int    requestPort0247 = 443;
		String requestFile0247 = "/B6/pictures/icons/i_plus.gif" +
				"?version=201611241128";
		String requestHeader0247 = "GET " + requestFile0247 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0247, requestHost0247, requestPort0247, requestHeader0247, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0247 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 68
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [247] <- Index 247
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [248] <- WEB ADMIN Index 248 ---
		String requestProt0248 = "https";
		String requestHost0248 = "book.jetblue.com";
		int    requestPort0248 = 443;
		String requestFile0248 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=SEAT_SELECTION_BOT";
		String requestHeader0248 = "GET " + requestFile0248 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0248, requestHost0248, requestPort0248, requestHeader0248, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0248 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 196
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [248] <- Index 248
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





		// --- HTTP REQUEST: Test [249] <- WEB ADMIN Index 249 ---
		String requestProt0249 = "https";
		String requestHost0249 = "book.jetblue.com";
		int    requestPort0249 = 443;
		String requestFile0249 = "/B6/ValidateFormAction.do";
		String requestContent0249 = 
				"validateAction=ReservationStoreSeatSelection" +
				"&fieldName=seatInfo" +
				"&isFastValidation=true" +
				"&forwardToPayment=" +
				"&forwardToConfirmation=" +
				"&sendConfirmationEmail=false" +
				"&seatsConfirmed=false" +
				"&seatInfo%5B0%5D.flightSegmentId=" +
				"&legSegment_0_seatField_1=" +
				"&seatInfo%5B0%5D.options%5B0%5D.paxId=" +
				"&seatInfo%5B0%5D.options%5B0%5D.seat=" +
				"&seatInfo%5B0%5D.options%5B0%5D.seatFeePricing=" +
				"&vsessionid=";
		String requestHeader0249 = "POST " + requestFile0249 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0249.length() + "\r\n" + 		// Content-Length: 368
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0249, requestHost0249, requestPort0249, requestHeader0249, requestContent0249.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0249 = null;		// support garbage collector to reduce memory
		requestContent0249 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 327
		// content test algorithm: search text
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, "status: \"success\",");		// Test [249] <- Index 249
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





		// --- HTTP REQUEST: Test [250] <- WEB ADMIN Index 250 ---
		String requestProt0250 = "https";
		String requestHost0250 = "book.jetblue.com";
		int    requestPort0250 = 443;
		String requestFile0250 = "/B6/AirRetrieveSeatMapAction.do" +
				"?departureDate=" + departureDate0 +		// recorded value = 1491212640000
				"&fareFamilyCode=AN" +
				"&flightSegmentKey=0_0" +
				"&equipment=E90" +
				"&flightNumber=" + flightNumber0 +		// recorded value = 917
				"&bookingClassCode=" + bookingClassCode0 +		// recorded value = W
				"&currency=USD" +
				"&flightSegmentId=0" +
				"&airlineCode=B6" +
				"&destinationAirportCode=JFK" +
				"&operatingAirlineCode=B6" +
				"&arrivalDate=" + arrivalDate0 +		// recorded value = 1491217200000
				"&originAirportCode=BOS" +
				"&inventorySystem=SABRE" +
				"&cacheKey=" + cacheKey0 +		// recorded value = -237884496
				"&seatMapEditableMode=true";
		String requestHeader0250 = "POST " + requestFile0250 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0250, requestHost0250, requestPort0250, requestHeader0250, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0250 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());
		
		LoadtestPluginContext urlPluginContext0250 = new LoadtestPluginContext(prxVersion, prxCharEncoding, this, threadNumber, socketPool, cookieHandler, testURL, threadStep);

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 4062
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [250] <- Index 250
		if (!urlCallPassed)
		{
			// failure - dump wrong content to thread log and abort current loop
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
		
			// execute plug-in #1 after url call
			urlPluginContext0250.setUrlPassed(false);
			log(">>> execute plug-in #1: SelectSeatNumberJetBlue");
			synchronized (plugin0001)
			{
				((GenericPluginInterface) plugin0001).setInputParameter(1, keyword);		// set plug-in input parameter
				((GenericPluginInterface) plugin0001).setInputParameter(0, FlightResponseData);		// set plug-in input parameter
				((GenericPluginInterface) plugin0001).execute(urlPluginContext0250);			// execute plug-in
				if (((GenericPluginInterface) plugin0001).getLogVector() != null)			// dump plug-in log
					log(((GenericPluginInterface) plugin0001).getLogVector().getLog());
				SeatNumber = (String) ((GenericPluginInterface) plugin0001).getOutputParameter(0);		// get plug-in output parameter
				log("<<< SeatNumber = " + SeatNumber);
			}
		
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// extract variable(s) from response
		try
		{
			// extract var 'SeatResponseData'
			SeatResponseData = testURL.getContentString("ISO-8859-1");
			log("<<< SeatResponseData = " + SeatResponseData);
		}
		catch (Exception e) { SeatResponseData = null; log(e); }
		if (SeatResponseData == null)
		{
			// failure - dump wrong response content and abort current outer loop - after that start next loop
			String errorText = "*** error: unable to extract var 'SeatResponseData' from http response content";
			log(errorText);
			terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
			threadStep = performanceData.setFailed(threadStep, HttpTestURL.STATUS_TYPE_USER_SPECIFIC_TEST_FAILED, errorText, testURL, this);
		
			// execute plug-in #1 after url call
			urlPluginContext0250.setUrlPassed(false);
			log(">>> execute plug-in #1: SelectSeatNumberJetBlue");
			synchronized (plugin0001)
			{
				((GenericPluginInterface) plugin0001).setInputParameter(1, keyword);		// set plug-in input parameter
				((GenericPluginInterface) plugin0001).setInputParameter(0, FlightResponseData);		// set plug-in input parameter
				((GenericPluginInterface) plugin0001).execute(urlPluginContext0250);			// execute plug-in
				if (((GenericPluginInterface) plugin0001).getLogVector() != null)			// dump plug-in log
					log(((GenericPluginInterface) plugin0001).getLogVector().getLog());
				SeatNumber = (String) ((GenericPluginInterface) plugin0001).getOutputParameter(0);		// get plug-in output parameter
				log("<<< SeatNumber = " + SeatNumber);
			}
		
			endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
			return false;
		}

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated

		
		// execute plug-in #1 after url call
		urlPluginContext0250.setUrlPassed(urlCallPassed);
		log(">>> execute plug-in #1: SelectSeatNumberJetBlue");
		synchronized (plugin0001)
		{
			((GenericPluginInterface) plugin0001).setInputParameter(1, keyword);		// set plug-in input parameter
			((GenericPluginInterface) plugin0001).setInputParameter(0, FlightResponseData);		// set plug-in input parameter
			((GenericPluginInterface) plugin0001).execute(urlPluginContext0250);			// execute plug-in
			if (((GenericPluginInterface) plugin0001).getLogVector() != null)			// dump plug-in log
				log(((GenericPluginInterface) plugin0001).getLogVector().getLog());
			if (urlPluginContext0250.getSpecificErrorMessage() != null)
			{
				// special case: url already executed but plug-in specific error released - set URL as failed and abort current loop
				testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_HANDLE_AS_ERROR);
				threadStep--;
				if (urlCallPassed)
					modPassed(performanceData, threadStep, -1);
				else
					modFailed(performanceData, threadStep, -1);
				log(urlPluginContext0250.getSpecificErrorMessage());
				performanceData.getPerformanceDataRecord(threadStep).modifyFailureActionType(HttpTestURL.FAILURE_ACTION_HANDLE_AS_ERROR);
				performanceData.setFailed(threadStep, urlPluginContext0250.getSpecificErrorStatusType(), urlPluginContext0250.getSpecificErrorMessage(), testURL, this);
				terminateFailedUser(testURL);		// set the user to be terminated at end of loop ? - only performed if the URL call has marked before to support that !
				endOfExecuteLoop(false, testURL, threadStep, loopPluginContext);
				return false;
			}
			SeatNumber = (String) ((GenericPluginInterface) plugin0001).getOutputParameter(0);		// get plug-in output parameter
			log("<<< SeatNumber = " + SeatNumber);
		}




		// --- HTTP REQUEST: Test [251] <- WEB ADMIN Index 251 ---
		String requestProt0251 = "https";
		String requestHost0251 = "book.jetblue.com";
		int    requestPort0251 = 443;
		String requestFile0251 = "/B6/pictures/icons/i-seat-selection-flight-info-arrow.png" +
				"?version=201611241128";
		String requestHeader0251 = "GET " + requestFile0251 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0251, requestHost0251, requestPort0251, requestHeader0251, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0251 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1683
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [251] <- Index 251
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [252] <- WEB ADMIN Index 252 ---
		String requestProt0252 = "https";
		String requestHost0252 = "book.jetblue.com";
		int    requestPort0252 = 443;
		String requestFile0252 = "/B6/pictures/icons/seatmap-actleg-route-arrow.png" +
				"?version=201611241128";
		String requestHeader0252 = "GET " + requestFile0252 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0252, requestHost0252, requestPort0252, requestHeader0252, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0252 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1521
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [252] <- Index 252
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [253] <- WEB ADMIN Index 253 ---
		String requestProt0253 = "https";
		String requestHost0253 = "book.jetblue.com";
		int    requestPort0253 = 443;
		String requestFile0253 = "/B6/Merchandizing.do" +
				"?touchPoint=SEAT_SELECTION_BOT";
		String requestHeader0253 = "GET " + requestFile0253 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0253, requestHost0253, requestPort0253, requestHeader0253, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0253 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 1089
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [253] <- Index 253
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





		// --- HTTP REQUEST: Test [254] <- WEB ADMIN Index 254 ---
		String requestProt0254 = "https";
		String requestHost0254 = "book.jetblue.com";
		int    requestPort0254 = 443;
		String requestFile0254 = "/B6/pictures/icons/seats/i_seat_1_f.gif" +
				"?version=201702040020";
		String requestHeader0254 = "GET " + requestFile0254 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0254, requestHost0254, requestPort0254, requestHeader0254, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0254 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 149
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [254] <- Index 254
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [255] <- WEB ADMIN Index 255 ---
		String requestProt0255 = "https";
		String requestHost0255 = "book.jetblue.com";
		int    requestPort0255 = 443;
		String requestFile0255 = "/B6/pictures/icons/seats/i_seat_avail_extra_2_f.gif" +
				"?version=201702040020";
		String requestHeader0255 = "GET " + requestFile0255 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0255, requestHost0255, requestPort0255, requestHeader0255, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0255 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1139
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [255] <- Index 255
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [256] <- WEB ADMIN Index 256 ---
		String requestProt0256 = "https";
		String requestHost0256 = "book.jetblue.com";
		int    requestPort0256 = 443;
		String requestFile0256 = "/B6/pictures/icons/seats/i_seat_avail_f.gif" +
				"?version=201702040020";
		String requestHeader0256 = "GET " + requestFile0256 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0256, requestHost0256, requestPort0256, requestHeader0256, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0256 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 138
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [256] <- Index 256
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [257] <- WEB ADMIN Index 257 ---
		String requestProt0257 = "https";
		String requestHost0257 = "book.jetblue.com";
		int    requestPort0257 = 443;
		String requestFile0257 = "/B6/pictures/icons/seats/i_seat_occup_f.gif" +
				"?version=201702040020";
		String requestHeader0257 = "GET " + requestFile0257 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0257, requestHost0257, requestPort0257, requestHeader0257, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0257 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 135
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [257] <- Index 257
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [258] <- WEB ADMIN Index 258 ---
		String requestProt0258 = "https";
		String requestHost0258 = "book.jetblue.com";
		int    requestPort0258 = 443;
		String requestFile0258 = "/B6/img/seatmaps/spr-seats-legend-b6.gif" +
				"?version=201611241128";
		String requestHeader0258 = "GET " + requestFile0258 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0258, requestHost0258, requestPort0258, requestHeader0258, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0258 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1838
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [258] <- Index 258
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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



		// --- HTTP REQUEST: Test [259] <- WEB ADMIN Index 259 ---
		String requestProt0259 = "https";
		String requestHost0259 = "www.jetblue.com";
		int    requestPort0259 = 443;
		String requestFile0259 = "/i/sabre/images/A321/promo-even-more-space.png" +
				"?b=32";
		String requestHeader0259 = "GET " + requestFile0259 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0259, requestHost0259, requestPort0259, requestHeader0259, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0259 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 72253
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [259] <- Index 259
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [260] <- WEB ADMIN Index 260 ---
		String requestProt0260 = "https";
		String requestHost0260 = "book.jetblue.com";
		int    requestPort0260 = 443;
		String requestFile0260 = "/B6/img/seatmaps/bg_E190.png" +
				"?version=201611241128";
		String requestHeader0260 = "GET " + requestFile0260 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0260, requestHost0260, requestPort0260, requestHeader0260, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0260 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 9508
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [260] <- Index 260
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [261] <- WEB ADMIN Index 261 ---
		String requestProt0261 = "https";
		String requestHost0261 = "www.jetblue.com";
		int    requestPort0261 = 443;
		String requestFile0261 = "/i/sabre/common/images/pod/EMSSeatPage.png" +
				"?b=32";
		String requestHeader0261 = "GET " + requestFile0261 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0261, requestHost0261, requestPort0261, requestHeader0261, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0261 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 5679
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [261] <- Index 261
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [262] <- WEB ADMIN Index 262 ---
		String requestProt0262 = "https";
		String requestHost0262 = "book.jetblue.com";
		int    requestPort0262 = 443;
		String requestFile0262 = "/B6/img/seatmaps/seats-b6-seat-occupied.png" +
				"?version=201611241128";
		String requestHeader0262 = "GET " + requestFile0262 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0262, requestHost0262, requestPort0262, requestHeader0262, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0262 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1185
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [262] <- Index 262
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [263] <- WEB ADMIN Index 263 ---
		String requestProt0263 = "https";
		String requestHost0263 = "book.jetblue.com";
		int    requestPort0263 = 443;
		String requestFile0263 = "/B6/img/seatmaps/seats-b6-seat-ems.png" +
				"?version=201611241128";
		String requestHeader0263 = "GET " + requestFile0263 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0263, requestHost0263, requestPort0263, requestHeader0263, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0263 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 482
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [263] <- Index 263
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [264] <- WEB ADMIN Index 264 ---
		String requestProt0264 = "https";
		String requestHost0264 = "book.jetblue.com";
		int    requestPort0264 = 443;
		String requestFile0264 = "/B6/img/seatmaps/seats-b6-seat-core.png" +
				"?version=201611241128";
		String requestHeader0264 = "GET " + requestFile0264 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0264, requestHost0264, requestPort0264, requestHeader0264, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0264 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 178
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [264] <- Index 264
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [265] <- WEB ADMIN Index 265 ---
		String requestProt0265 = "https";
		String requestHost0265 = "book.jetblue.com";
		int    requestPort0265 = 443;
		String requestFile0265 = "/B6/ReservationSelectSeat.do";
		String requestContent0265 = 
				"forwardToPayment=" +
				"&forwardToConfirmation=" +
				"&sendConfirmationEmail=false" +
				"&seatsConfirmed=false" +
				"&seatInfo%5B0%5D.flightSegmentId=0_0" +
				"&legSegment_0_seatField_1=18A" +
				"&seatInfo%5B0%5D.options%5B0%5D.paxId=1" +
				"&seatInfo%5B0%5D.options%5B0%5D.seat=18A" +
				"&seatInfo%5B0%5D.options%5B0%5D.seatFeePricing=" +
				"&vsessionid=";
		String requestHeader0265 = "POST " + requestFile0265 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0265.length() + "\r\n" + 		// Content-Length: 292
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0265, requestHost0265, requestPort0265, requestHeader0265, requestContent0265.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0265 = null;		// support garbage collector to reduce memory
		requestContent0265 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 1890
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [265] <- Index 265
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





		// --- HTTP REQUEST: Test [266] <- WEB ADMIN Index 266 ---
		String requestProt0266 = "https";
		String requestHost0266 = "book.jetblue.com";
		int    requestPort0266 = 443;
		String requestFile0266 = "/B6/img/seatmaps/seats-b6-seat-selected.png" +
				"?version=201611241128";
		String requestHeader0266 = "GET " + requestFile0266 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0266, requestHost0266, requestPort0266, requestHeader0266, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0266 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 737
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [266] <- Index 266
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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
		// # Page #5: Extra's
		// # ----------------



		// --- HTTP REQUEST: Test [268] <- WEB ADMIN Index 268 ---
		String requestProt0268 = "https";
		String requestHost0268 = "book.jetblue.com";
		int    requestPort0268 = 443;
		String requestFile0268 = "/B6/ValidateFormAction.do";
		String requestContent0268 = 
				"validateAction=ReservationStoreSeatSelection" +
				"&forwardToPayment=" +
				"&forwardToConfirmation=" +
				"&sendConfirmationEmail=false" +
				"&seatsConfirmed=true" +
				"&seatInfo%5B0%5D.flightSegmentId=0_0" +
				"&legSegment_0_seatField_1=18A" +
				"&seatInfo%5B0%5D.options%5B0%5D.paxId=1" +
				"&seatInfo%5B0%5D.options%5B0%5D.seat=18A" +
				"&seatInfo%5B0%5D.options%5B0%5D.seatFeePricing=" +
				"&vsessionid=";
		String requestHeader0268 = "POST " + requestFile0268 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0268.length() + "\r\n" + 		// Content-Length: 336
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0268, requestHost0268, requestPort0268, requestHeader0268, requestContent0268.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0268 = null;		// support garbage collector to reduce memory
		requestContent0268 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 327
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [268] <- Index 268
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





		// --- HTTP REQUEST: Test [269] <- WEB ADMIN Index 269 ---
		log();
		log("# title: JetBlue Airways");
		String requestProt0269 = "https";
		String requestHost0269 = "www.jetblue.com";
		int    requestPort0269 = 443;
		String requestFile0269 = "/content/snippets/interstitials/booking/index4.html" +
				"?version=201702040020";
		String requestHeader0269 = "GET " + requestFile0269 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0269, requestHost0269, requestPort0269, requestHeader0269, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0269 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 660
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [269] <- Index 269
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





		// --- HTTP REQUEST: Test [270] <- WEB ADMIN Index 270 ---
		String requestProt0270 = "https";
		String requestHost0270 = "book.jetblue.com";
		int    requestPort0270 = 443;
		String requestFile0270 = "/B6/ReservationStoreSeatSelection.do";
		String requestContent0270 = 
				"ajaxAction=true" +
				"&forwardToPayment=" +
				"&forwardToConfirmation=" +
				"&sendConfirmationEmail=false" +
				"&seatsConfirmed=true" +
				"&seatInfo%5B0%5D.flightSegmentId=0_0" +
				"&legSegment_0_seatField_1=18A" +
				"&seatInfo%5B0%5D.options%5B0%5D.paxId=1" +
				"&seatInfo%5B0%5D.options%5B0%5D.seat=18A" +
				"&seatInfo%5B0%5D.options%5B0%5D.seatFeePricing=" +
				"&vsessionid=";
		String requestHeader0270 = "POST " + requestFile0270 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0270.length() + "\r\n" + 		// Content-Length: 307
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0270, requestHost0270, requestPort0270, requestHeader0270, requestContent0270.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0270 = null;		// support garbage collector to reduce memory
		requestContent0270 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 374
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [270] <- Index 270
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





		// --- HTTP REQUEST: Test [271] <- WEB ADMIN Index 271 ---
		String requestProt0271 = "https";
		String requestHost0271 = "www.jetblue.com";
		int    requestPort0271 = 443;
		String requestFile0271 = "/content/media/imgs/interstitials/intersti_1step.png";
		String requestHeader0271 = "GET " + requestFile0271 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0271, requestHost0271, requestPort0271, requestHeader0271, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0271 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 70691
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [271] <- Index 271
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [272] <- WEB ADMIN Index 272 ---
		log();
		log("# title: Extras");
		String requestProt0272 = "https";
		String requestHost0272 = "book.jetblue.com";
		int    requestPort0272 = 443;
		String requestFile0272 = "/B6/MerchandizingSearchShowPageForward.do" +
				"?flowId=seatsFlow";
		String requestHeader0272 = "GET " + requestFile0272 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0272, requestHost0272, requestPort0272, requestHeader0272, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0272 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 45288
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [272] <- Index 272
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





		// --- HTTP REQUEST: Test [273] <- WEB ADMIN Index 273 ---
		String requestProt0273 = "https";
		String requestHost0273 = "book.jetblue.com";
		int    requestPort0273 = 443;
		String requestFile0273 = "/B6/js/car/car_itinerary_summary.js" +
				"?version=201702040020";
		String requestHeader0273 = "GET " + requestFile0273 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0273, requestHost0273, requestPort0273, requestHeader0273, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0273 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 669
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [273] <- Index 273
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [274] <- WEB ADMIN Index 274 ---
		String requestProt0274 = "https";
		String requestHost0274 = "book.jetblue.com";
		int    requestPort0274 = 443;
		String requestFile0274 = "/B6/js/car/car_image_checker.js" +
				"?version=201702040020";
		String requestHeader0274 = "GET " + requestFile0274 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0274, requestHost0274, requestPort0274, requestHeader0274, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0274 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 360
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [274] <- Index 274
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [275] <- WEB ADMIN Index 275 ---
		String requestProt0275 = "https";
		String requestHost0275 = "book.jetblue.com";
		int    requestPort0275 = 443;
		String requestFile0275 = "/B6/js/cross_sell.js" +
				"?version=201702040020";
		String requestHeader0275 = "GET " + requestFile0275 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0275, requestHost0275, requestPort0275, requestHeader0275, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0275 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1830
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [275] <- Index 275
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [276] <- WEB ADMIN Index 276 ---
		String requestProt0276 = "https";
		String requestHost0276 = "book.jetblue.com";
		int    requestPort0276 = 443;
		String requestFile0276 = "/B6/js/hotel/hotel_image_checker.js" +
				"?version=201702040020";
		String requestHeader0276 = "GET " + requestFile0276 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0276, requestHost0276, requestPort0276, requestHeader0276, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0276 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1059
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [276] <- Index 276
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [277] <- WEB ADMIN Index 277 ---
		String requestProt0277 = "https";
		String requestHost0277 = "book.jetblue.com";
		int    requestPort0277 = 443;
		String requestFile0277 = "/B6/js/insurance/shopping_insurance.js" +
				"?version=201702040020";
		String requestHeader0277 = "GET " + requestFile0277 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0277, requestHost0277, requestPort0277, requestHeader0277, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0277 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 3886
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [277] <- Index 277
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [278] <- WEB ADMIN Index 278 ---
		String requestProt0278 = "https";
		String requestHost0278 = "book.jetblue.com";
		int    requestPort0278 = 443;
		String requestFile0278 = "/B6/ValidateFlow.do";
		String requestContent0278 = 
				"validator=BOOKING_PREPAYMENT";
		String requestHeader0278 = "POST " + requestFile0278 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0278.length() + "\r\n" + 		// Content-Length: 28
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0278, requestHost0278, requestPort0278, requestHeader0278, requestContent0278.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0278 = null;		// support garbage collector to reduce memory
		requestContent0278 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 78
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [278] <- Index 278
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





		// --- HTTP REQUEST: Test [279] <- WEB ADMIN Index 279 ---
		String requestProt0279 = "https";
		String requestHost0279 = "book.jetblue.com";
		int    requestPort0279 = 443;
		String requestFile0279 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=BOOKING_PREPAYMENT";
		String requestHeader0279 = "GET " + requestFile0279 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0279, requestHost0279, requestPort0279, requestHeader0279, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0279 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 261
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [279] <- Index 279
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





		// --- HTTP REQUEST: Test [280] <- WEB ADMIN Index 280 ---
		String requestProt0280 = "https";
		String requestHost0280 = "book.jetblue.com";
		int    requestPort0280 = 443;
		String requestFile0280 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=BOOKING_PREPAYMENT_BOT";
		String requestHeader0280 = "GET " + requestFile0280 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0280, requestHost0280, requestPort0280, requestHeader0280, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0280 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 198
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [280] <- Index 280
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





		// --- HTTP REQUEST: Test [281] <- WEB ADMIN Index 281 ---
		String requestProt0281 = "https";
		String requestHost0281 = "book.jetblue.com";
		int    requestPort0281 = 443;
		String requestFile0281 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/widget/group/ancillaryGroup.js";
		String requestHeader0281 = "GET " + requestFile0281 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0281, requestHost0281, requestPort0281, requestHeader0281, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0281 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 258
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [281] <- Index 281
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [282] <- WEB ADMIN Index 282 ---
		String requestProt0282 = "https";
		String requestHost0282 = "book.jetblue.com";
		int    requestPort0282 = 443;
		String requestFile0282 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/widget/component/ancillary/ancillaryCommon.js";
		String requestHeader0282 = "GET " + requestFile0282 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0282, requestHost0282, requestPort0282, requestHeader0282, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0282 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1938
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [282] <- Index 282
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [283] <- WEB ADMIN Index 283 ---
		String requestProt0283 = "https";
		String requestHost0283 = "book.jetblue.com";
		int    requestPort0283 = 443;
		String requestFile0283 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/widget/group/cars.js";
		String requestHeader0283 = "GET " + requestFile0283 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0283, requestHost0283, requestPort0283, requestHeader0283, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0283 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 358
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [283] <- Index 283
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [284] <- WEB ADMIN Index 284 ---
		String requestProt0284 = "https";
		String requestHost0284 = "book.jetblue.com";
		int    requestPort0284 = 443;
		String requestFile0284 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/widget/component/car/carComponent.js";
		String requestHeader0284 = "GET " + requestFile0284 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0284, requestHost0284, requestPort0284, requestHeader0284, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0284 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 832
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [284] <- Index 284
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [285] <- WEB ADMIN Index 285 ---
		String requestProt0285 = "https";
		String requestHost0285 = "book.jetblue.com";
		int    requestPort0285 = 443;
		String requestFile0285 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/template/group_ancillary.soy.js";
		String requestHeader0285 = "GET " + requestFile0285 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0285, requestHost0285, requestPort0285, requestHeader0285, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0285 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 368
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [285] <- Index 285
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [286] <- WEB ADMIN Index 286 ---
		String requestProt0286 = "https";
		String requestHost0286 = "book.jetblue.com";
		int    requestPort0286 = 443;
		String requestFile0286 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/template/component_car.soy.js";
		String requestHeader0286 = "GET " + requestFile0286 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0286, requestHost0286, requestPort0286, requestHeader0286, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0286 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 984
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [286] <- Index 286
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [287] <- WEB ADMIN Index 287 ---
		String requestProt0287 = "https";
		String requestHost0287 = "book.jetblue.com";
		int    requestPort0287 = 443;
		String requestFile0287 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/template/group_car.soy.js";
		String requestHeader0287 = "GET " + requestFile0287 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0287, requestHost0287, requestPort0287, requestHeader0287, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0287 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1508
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [287] <- Index 287
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
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


	/**
	 * Recorded http requests of page #5_1.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_5_1(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #5 (fragment 2 of 2)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [288] <- WEB ADMIN Index 288 ---
		String requestProt0288 = "https";
		String requestHost0288 = "book.jetblue.com";
		int    requestPort0288 = 443;
		String requestFile0288 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/template/component_ancillary.soy.js";
		String requestHeader0288 = "GET " + requestFile0288 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0288, requestHost0288, requestPort0288, requestHeader0288, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0288 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1711
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [288] <- Index 288
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [289] <- WEB ADMIN Index 289 ---
		String requestProt0289 = "https";
		String requestHost0289 = "book.jetblue.com";
		int    requestPort0289 = 443;
		String requestFile0289 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/template/extrasDetails.soy.js";
		String requestHeader0289 = "GET " + requestFile0289 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0289, requestHost0289, requestPort0289, requestHeader0289, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0289 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 727
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [289] <- Index 289
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [290] <- WEB ADMIN Index 290 ---
		String requestProt0290 = "https";
		String requestHost0290 = "book.jetblue.com";
		int    requestPort0290 = 443;
		String requestFile0290 = "/B6/Merchandizing.do" +
				"?touchPoint=BOOKING_PREPAYMENT_BOT";
		String requestHeader0290 = "GET " + requestFile0290 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0290, requestHost0290, requestPort0290, requestHeader0290, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0290 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 1083
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [290] <- Index 290
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





		// --- HTTP REQUEST: Test [291] <- WEB ADMIN Index 291 ---
		String requestProt0291 = "https";
		String requestHost0291 = "book.jetblue.com";
		int    requestPort0291 = 443;
		String requestFile0291 = "/B6/Merchandizing.do" +
				"?touchPoint=BOOKING_PREPAYMENT";
		String requestHeader0291 = "GET " + requestFile0291 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0291, requestHost0291, requestPort0291, requestHeader0291, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0291 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 3776
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [291] <- Index 291
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





		// --- HTTP REQUEST: Test [292] <- WEB ADMIN Index 292 ---
		String requestProt0292 = "https";
		String requestHost0292 = "book.jetblue.com";
		int    requestPort0292 = 443;
		String requestFile0292 = "/B6/pictures/misc/x.gif" +
				"?version=201702040020";
		String requestHeader0292 = "GET " + requestFile0292 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0292, requestHost0292, requestPort0292, requestHeader0292, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0292 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 43
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [292] <- Index 292
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [293] <- WEB ADMIN Index 293 ---
		String requestProt0293 = "https";
		String requestHost0293 = "book.jetblue.com";
		int    requestPort0293 = 443;
		String requestFile0293 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/widget/theme/consumer/pictures/misc/even-more-speed-logo.png";
		String requestHeader0293 = "GET " + requestFile0293 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0293, requestHost0293, requestPort0293, requestHeader0293, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0293 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 5434
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [293] <- Index 293
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [294] <- WEB ADMIN Index 294 ---
		String requestProt0294 = "https";
		String requestHost0294 = "book.jetblue.com";
		int    requestPort0294 = 443;
		String requestFile0294 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/customer/jbu/merchandizing/widget/theme/consumer/pictures/misc/fly-with-pet-logo.png";
		String requestHeader0294 = "GET " + requestFile0294 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0294, requestHost0294, requestPort0294, requestHeader0294, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0294 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 9305
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [294] <- Index 294
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [295] <- WEB ADMIN Index 295 ---
		String requestProt0295 = "https";
		String requestHost0295 = "book.jetblue.com";
		int    requestPort0295 = 443;
		String requestFile0295 = "/B6/pictures/icons/navigation/spr-cross-sell-nav.png" +
				"?version=201611241128";
		String requestHeader0295 = "GET " + requestFile0295 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0295, requestHost0295, requestPort0295, requestHeader0295, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0295 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1408
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [295] <- Index 295
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #5_1 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #6.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_6(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// # Page #6: Payment information
		// # ----------------------------



		// --- HTTP REQUEST: Test [297] <- WEB ADMIN Index 297 ---
		log();
		log("# title: JetBlue Airways");
		String requestProt0297 = "https";
		String requestHost0297 = "www.jetblue.com";
		int    requestPort0297 = 443;
		String requestFile0297 = "/content/snippets/interstitials/booking/index5.html" +
				"?version=201702040020";
		String requestHeader0297 = "GET " + requestFile0297 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0297, requestHost0297, requestPort0297, requestHeader0297, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0297 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 674
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [297] <- Index 297
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





		// --- HTTP REQUEST: Test [298] <- WEB ADMIN Index 298 ---
		String requestProt0298 = "https";
		String requestHost0298 = "book.jetblue.com";
		int    requestPort0298 = 443;
		String requestFile0298 = "/B6/ReservationAddMerchandizingOptionsFromSeats.do";
		String requestContent0298 = 
				"ajaxAction=true" +
				"&vsessionid=";
		String requestHeader0298 = "POST " + requestFile0298 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0298.length() + "\r\n" + 		// Content-Length: 27
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0298, requestHost0298, requestPort0298, requestHeader0298, requestContent0298.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0298 = null;		// support garbage collector to reduce memory
		requestContent0298 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 368
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [298] <- Index 298
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





		// --- HTTP REQUEST: Test [299] <- WEB ADMIN Index 299 ---
		String requestProt0299 = "https";
		String requestHost0299 = "www.jetblue.com";
		int    requestPort0299 = 443;
		String requestFile0299 = "/content/media/imgs/interstitials/Interstitial_500x260_LifeHappens.png";
		String requestHeader0299 = "GET " + requestFile0299 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0299, requestHost0299, requestPort0299, requestHeader0299, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0299 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 73081
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [299] <- Index 299
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [300] <- WEB ADMIN Index 300 ---
		log();
		log("# title: Payment");
		String requestProt0300 = "https";
		String requestHost0300 = "pay.jetblue.com";
		int    requestPort0300 = 443;
		String requestFile0300 = "/B6/PaymentForward.do";
		String requestHeader0300 = "GET " + requestFile0300 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0300, requestHost0300, requestPort0300, requestHeader0300, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0300 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 63258
		// content test algorithm: search text
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, "Review Trip Details");		// Test [300] <- Index 300
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





		// --- HTTP REQUEST: Test [301] <- WEB ADMIN Index 301 ---
		String requestProt0301 = "https";
		String requestHost0301 = "book.jetblue.com";
		int    requestPort0301 = 443;
		String requestFile0301 = "/B6/js/payment_creditcard_validation.js" +
				"?version=201702040020";
		String requestHeader0301 = "GET " + requestFile0301 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0301, requestHost0301, requestPort0301, requestHeader0301, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0301 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 838
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [301] <- Index 301
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [302] <- WEB ADMIN Index 302 ---
		String requestProt0302 = "https";
		String requestHost0302 = "book.jetblue.com";
		int    requestPort0302 = 443;
		String requestFile0302 = "/B6/js/payment.js" +
				"?version=201702040020";
		String requestHeader0302 = "GET " + requestFile0302 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0302, requestHost0302, requestPort0302, requestHeader0302, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0302 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 30583
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [302] <- Index 302
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [303] <- WEB ADMIN Index 303 ---
		String requestProt0303 = "https";
		String requestHost0303 = "book.jetblue.com";
		int    requestPort0303 = 443;
		String requestFile0303 = "/B6/js/payment_acculynk.js" +
				"?version=201702040020";
		String requestHeader0303 = "GET " + requestFile0303 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0303, requestHost0303, requestPort0303, requestHeader0303, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0303 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 815
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [303] <- Index 303
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [304] <- WEB ADMIN Index 304 ---
		String requestProt0304 = "https";
		String requestHost0304 = "book.jetblue.com";
		int    requestPort0304 = 443;
		String requestFile0304 = "/B6/js/payment_delivery.js" +
				"?version=201702040020";
		String requestHeader0304 = "GET " + requestFile0304 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0304, requestHost0304, requestPort0304, requestHeader0304, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0304 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 998
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [304] <- Index 304
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [305] <- WEB ADMIN Index 305 ---
		String requestProt0305 = "https";
		String requestHost0305 = "book.jetblue.com";
		int    requestPort0305 = 443;
		String requestFile0305 = "/B6/js/callcenter/callCenter.js" +
				"?version=201702040020";
		String requestHeader0305 = "GET " + requestFile0305 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0305, requestHost0305, requestPort0305, requestHeader0305, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0305 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1452
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [305] <- Index 305
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [306] <- WEB ADMIN Index 306 ---
		String requestProt0306 = "https";
		String requestHost0306 = "book.jetblue.com";
		int    requestPort0306 = 443;
		String requestFile0306 = "/B6/pictures/content/credit-card/icons/JC.gif" +
				"?version=201702040020";
		String requestHeader0306 = "GET " + requestFile0306 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0306, requestHost0306, requestPort0306, requestHeader0306, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0306 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 2211
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [306] <- Index 306
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [307] <- WEB ADMIN Index 307 ---
		String requestProt0307 = "https";
		String requestHost0307 = "book.jetblue.com";
		int    requestPort0307 = 443;
		String requestFile0307 = "/B6/pictures/content/credit-card/icons/AX.gif" +
				"?version=201702040020";
		String requestHeader0307 = "GET " + requestFile0307 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0307, requestHost0307, requestPort0307, requestHeader0307, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0307 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1909
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [307] <- Index 307
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [308] <- WEB ADMIN Index 308 ---
		String requestProt0308 = "https";
		String requestHost0308 = "book.jetblue.com";
		int    requestPort0308 = 443;
		String requestFile0308 = "/B6/pictures/content/credit-card/icons/DS.gif" +
				"?version=201702040020";
		String requestHeader0308 = "GET " + requestFile0308 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0308, requestHost0308, requestPort0308, requestHeader0308, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0308 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 2172
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [308] <- Index 308
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [309] <- WEB ADMIN Index 309 ---
		String requestProt0309 = "https";
		String requestHost0309 = "book.jetblue.com";
		int    requestPort0309 = 443;
		String requestFile0309 = "/B6/pictures/content/credit-card/icons/CA.gif" +
				"?version=201702040020";
		String requestHeader0309 = "GET " + requestFile0309 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0309, requestHost0309, requestPort0309, requestHeader0309, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0309 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 2550
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [309] <- Index 309
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [310] <- WEB ADMIN Index 310 ---
		String requestProt0310 = "https";
		String requestHost0310 = "pay.jetblue.com";
		int    requestPort0310 = 443;
		String requestFile0310 = "/B6/pictures/icons/i-new-window.png";
		String requestHeader0310 = "GET " + requestFile0310 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: pay.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0310, requestHost0310, requestPort0310, requestHeader0310, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0310 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1101
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [310] <- Index 310
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [311] <- WEB ADMIN Index 311 ---
		String requestProt0311 = "https";
		String requestHost0311 = "book.jetblue.com";
		int    requestPort0311 = 443;
		String requestFile0311 = "/B6/pictures/content/credit-card/icons/DC.gif" +
				"?version=201702040020";
		String requestHeader0311 = "GET " + requestFile0311 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0311, requestHost0311, requestPort0311, requestHeader0311, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0311 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 2087
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [311] <- Index 311
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [312] <- WEB ADMIN Index 312 ---
		String requestProt0312 = "https";
		String requestHost0312 = "book.jetblue.com";
		int    requestPort0312 = 443;
		String requestFile0312 = "/B6/pictures/content/credit-card/icons/VI.gif" +
				"?version=201702040020";
		String requestHeader0312 = "GET " + requestFile0312 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0312, requestHost0312, requestPort0312, requestHeader0312, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0312 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 2225
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [312] <- Index 312
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [313] <- WEB ADMIN Index 313 ---
		String requestProt0313 = "https";
		String requestHost0313 = "book.jetblue.com";
		int    requestPort0313 = 443;
		String requestFile0313 = "/B6/pictures/content/credit-card/icons/TP.gif" +
				"?version=201702040020";
		String requestHeader0313 = "GET " + requestFile0313 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0313, requestHost0313, requestPort0313, requestHeader0313, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0313 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 3239
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [313] <- Index 313
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [314] <- WEB ADMIN Index 314 ---
		String requestProt0314 = "https";
		String requestHost0314 = "book.jetblue.com";
		int    requestPort0314 = 443;
		String requestFile0314 = "/B6/pictures/content/credit-card/icons/JB.gif" +
				"?version=201702040020";
		String requestHeader0314 = "GET " + requestFile0314 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0314, requestHost0314, requestPort0314, requestHeader0314, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0314 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 2937
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [314] <- Index 314
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [315] <- WEB ADMIN Index 315 ---
		String requestProt0315 = "https";
		String requestHost0315 = "pay.jetblue.com";
		int    requestPort0315 = 443;
		String requestFile0315 = "/assets/header/img/bg-jetblue-body-logoonly.jpg";
		String requestHeader0315 = "GET " + requestFile0315 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: pay.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0315, requestHost0315, requestPort0315, requestHeader0315, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0315 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 21738
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [315] <- Index 315
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [316] <- WEB ADMIN Index 316 ---
		String requestProt0316 = "https";
		String requestHost0316 = "pay.jetblue.com";
		int    requestPort0316 = 443;
		String requestFile0316 = "/assets/header/img/bg-true-blue-label.jpg";
		String requestHeader0316 = "GET " + requestFile0316 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: pay.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0316, requestHost0316, requestPort0316, requestHeader0316, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0316 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 16004
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [316] <- Index 316
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated



		// all http requests of page #6 successful done
		return true;
	}


	/**
	 * Recorded http requests of page #6_1.
	 *
	 * @return  true:  method successful completed.
	 *          false: method/loop failed.
	 */
	boolean executePage_6_1(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		// ... continuing page #6 (fragment 2 of 2)
		// ----------------------------------------



		// --- HTTP REQUEST: Test [317] <- WEB ADMIN Index 317 ---
		String requestProt0317 = "https";
		String requestHost0317 = "pay.jetblue.com";
		int    requestPort0317 = 443;
		String requestFile0317 = "/assets/header/img/lft-arrow.png";
		String requestHeader0317 = "GET " + requestFile0317 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: pay.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0317, requestHost0317, requestPort0317, requestHeader0317, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0317 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 158
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [317] <- Index 317
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [318] <- WEB ADMIN Index 318 ---
		String requestProt0318 = "https";
		String requestHost0318 = "pay.jetblue.com";
		int    requestPort0318 = 443;
		String requestFile0318 = "/B6/ValidateFlow.do";
		String requestContent0318 = 
				"validator=PAYMENT";
		String requestHeader0318 = "POST " + requestFile0318 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: pay.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0318.length() + "\r\n" + 		// Content-Length: 17
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0318, requestHost0318, requestPort0318, requestHeader0318, requestContent0318.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0318 = null;		// support garbage collector to reduce memory
		requestContent0318 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 78
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [318] <- Index 318
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





		// --- HTTP REQUEST: Test [319] <- WEB ADMIN Index 319 ---
		String requestProt0319 = "https";
		String requestHost0319 = "book.jetblue.com";
		int    requestPort0319 = 443;
		String requestFile0319 = "/B6/pictures/icons/i_info3.png" +
				"?version=201611241128";
		String requestHeader0319 = "GET " + requestFile0319 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0319, requestHost0319, requestPort0319, requestHeader0319, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0319 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 436
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [319] <- Index 319
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [320] <- WEB ADMIN Index 320 ---
		String requestProt0320 = "https";
		String requestHost0320 = "book.jetblue.com";
		int    requestPort0320 = 443;
		String requestFile0320 = "/B6/pictures/misc/spr-checkbox-type3.gif" +
				"?version=201611241128";
		String requestHeader0320 = "GET " + requestFile0320 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0320, requestHost0320, requestPort0320, requestHeader0320, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0320 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1621
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [320] <- Index 320
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [321] <- WEB ADMIN Index 321 ---
		String requestProt0321 = "https";
		String requestHost0321 = "book.jetblue.com";
		int    requestPort0321 = 443;
		String requestFile0321 = "/B6/pictures/content/payment-mode/logo_paypal.gif" +
				"?version=201611241128";
		String requestHeader0321 = "GET " + requestFile0321 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0321, requestHost0321, requestPort0321, requestHeader0321, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0321 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/GIF", header text fragment = [verification disabled], recorded content size = 1110
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/GIF", null, null);		// Test [321] <- Index 321
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [322] <- WEB ADMIN Index 322 ---
		String requestProt0322 = "https";
		String requestHost0322 = "book.jetblue.com";
		int    requestPort0322 = 443;
		String requestFile0322 = "/B6/pictures/content/payment-mode/logo-paypal-credit.png" +
				"?version=201611241128";
		String requestHeader0322 = "GET " + requestFile0322 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0322, requestHost0322, requestPort0322, requestHeader0322, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0322 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 3716
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [322] <- Index 322
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [323] <- WEB ADMIN Index 323 ---
		String requestProt0323 = "https";
		String requestHost0323 = "book.jetblue.com";
		int    requestPort0323 = 443;
		String requestFile0323 = "/B6/pictures/icons/spr-terms-conditions.png" +
				"?version=201611241128";
		String requestHeader0323 = "GET " + requestFile0323 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0323, requestHost0323, requestPort0323, requestHeader0323, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0323 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 7441
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [323] <- Index 323
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [324] <- WEB ADMIN Index 324 ---
		String requestProt0324 = "https";
		String requestHost0324 = "book.jetblue.com";
		int    requestPort0324 = 443;
		String requestFile0324 = "/B6/pictures/icons/i_warning_type4.png" +
				"?version=201611241128";
		String requestHeader0324 = "GET " + requestFile0324 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0324, requestHost0324, requestPort0324, requestHeader0324, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0324 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/PNG", header text fragment = [verification disabled], recorded content size = 1393
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/PNG", null, null);		// Test [324] <- Index 324
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [325] <- WEB ADMIN Index 325 ---
		String requestProt0325 = "https";
		String requestHost0325 = "pay.jetblue.com";
		int    requestPort0325 = 443;
		String requestFile0325 = "/B6/SelectFormOfPayment.do";
		String requestContent0325 = 
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
		String requestHeader0325 = "POST " + requestFile0325 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: pay.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0325.length() + "\r\n" + 		// Content-Length: 527
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0325, requestHost0325, requestPort0325, requestHeader0325, requestContent0325.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0325 = null;		// support garbage collector to reduce memory
		requestContent0325 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 390
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [325] <- Index 325
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





		// --- HTTP REQUEST: Test [326] <- WEB ADMIN Index 326 ---
		String requestProt0326 = "https";
		String requestHost0326 = "pay.jetblue.com";
		int    requestPort0326 = 443;
		String requestFile0326 = "/B6/MerchandizingConfig.do" +
				"?touchPoint=PAYMENT_TOP";
		String requestHeader0326 = "GET " + requestFile0326 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0326, requestHost0326, requestPort0326, requestHeader0326, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0326 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 240
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [326] <- Index 326
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





		// --- HTTP REQUEST: Test [327] <- WEB ADMIN Index 327 ---
		String requestProt0327 = "https";
		String requestHost0327 = "pay.jetblue.com";
		int    requestPort0327 = 443;
		String requestFile0327 = "/B6/UpdateCreditCard.do";
		String requestContent0327 = 
				"type=" +
				"&fop=CREDITCARD" +
				"&fopType=POS" +
				"&fopSequence=" +
				"&fopId=CREDITCARD_POS" +
				"&creditCard.type=" +
				"&creditCard.code=CREDITCARD" +
				"&creditCard.fopType=POS" +
				"&creditCard.fopSequence=";
		String requestHeader0327 = "POST " + requestFile0327 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: pay.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"ADRUM: isAjax:true\r\n" + 
				"Content-Length: " + requestContent0327.length() + "\r\n" + 		// Content-Length: 157
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0327, requestHost0327, requestPort0327, requestHeader0327, requestContent0327.getBytes(), requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
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
		requestHeader0327 = null;		// support garbage collector to reduce memory
		requestContent0327 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 346
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [327] <- Index 327
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





		// --- HTTP REQUEST: Test [328] <- WEB ADMIN Index 328 ---
		String requestProt0328 = "https";
		String requestHost0328 = "book.jetblue.com";
		int    requestPort0328 = 443;
		String requestFile0328 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/widget/group/group_insurance_offer_HTML.js";
		String requestHeader0328 = "GET " + requestFile0328 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0328, requestHost0328, requestPort0328, requestHeader0328, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0328 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1271
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [328] <- Index 328
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [329] <- WEB ADMIN Index 329 ---
		String requestProt0329 = "https";
		String requestHost0329 = "book.jetblue.com";
		int    requestPort0329 = 443;
		String requestFile0329 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/widget/component/insurance/insuranceComponent_external.js";
		String requestHeader0329 = "GET " + requestFile0329 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0329, requestHost0329, requestPort0329, requestHeader0329, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0329 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 1089
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [329] <- Index 329
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [330] <- WEB ADMIN Index 330 ---
		String requestProt0330 = "https";
		String requestHost0330 = "book.jetblue.com";
		int    requestPort0330 = 443;
		String requestFile0330 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/template/group_insurance_offer_HTML.soy.js";
		String requestHeader0330 = "GET " + requestFile0330 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0330, requestHost0330, requestPort0330, requestHeader0330, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0330 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 518
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [330] <- Index 330
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [331] <- WEB ADMIN Index 331 ---
		String requestProt0331 = "https";
		String requestHost0331 = "book.jetblue.com";
		int    requestPort0331 = 443;
		String requestFile0331 = "/TDPMedia/java/merchandizing/static-site/201611241128/js/com/datalex/tdp/merchandizing/template/component_insurance_external.soy.js";
		String requestHeader0331 = "GET " + requestFile0331 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0331, requestHost0331, requestPort0331, requestHeader0331, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0331 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/JAVASCRIPT", header text fragment = [verification disabled], recorded content size = 373
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/JAVASCRIPT", null, null);		// Test [331] <- Index 331
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [332] <- WEB ADMIN Index 332 ---
		String requestProt0332 = "https";
		String requestHost0332 = "pay.jetblue.com";
		int    requestPort0332 = 443;
		String requestFile0332 = "/B6/Merchandizing.do" +
				"?touchPoint=PAYMENT_TOP";
		String requestHeader0332 = "GET " + requestFile0332 + " HTTP/" + httpProtocolVersion + "\r\n" + 
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
		testURL = new HttpTestURL(requestProt0332, requestHost0332, requestPort0332, requestHeader0332, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0332 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "TEXT/HTML", header text fragment = [verification disabled], recorded content size = 3120
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "TEXT/HTML", null, null);		// Test [332] <- Index 332
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





		// --- HTTP REQUEST: Test [333] <- WEB ADMIN Index 333 ---
		String requestProt0333 = "https";
		String requestHost0333 = "www.jetblue.com";
		int    requestPort0333 = 443;
		String requestFile0333 = "/content/media/imgs/partners/bcus_existing_cm/TB_JBCard_2_Booking_962x200.jpg";
		String requestHeader0333 = "GET " + requestFile0333 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: www.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0333, requestHost0333, requestPort0333, requestHeader0333, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		testURL.setFailureActionType(HttpTestURL.FAILURE_ACTION_IGNORE_ERROR);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0333 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 200, content type = "IMAGE/JPEG", header text fragment = [verification disabled], recorded content size = 153376
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {200}, "IMAGE/JPEG", null, null);		// Test [333] <- Index 333
		if (!urlCallPassed)
		{
			// failure but continue current loop: mark pending transactions as defective and dump URL content to thread log
			transactionHandler.setPendingTransactionsAsDefective();
			log(testURL);
		}
		if (debugContent && urlCallPassed)
			log(testURL);

		// update performance data if url call passed
		if (urlCallPassed)
			threadStep = setPassed(performanceData, threadStep, testURL);
		else
			threadStep = threadStep + 1;		// url call failed - performance data already updated





		// --- HTTP REQUEST: Test [334] <- WEB ADMIN Index 334 ---
		String requestProt0334 = "https";
		String requestHost0334 = "book.jetblue.com";
		int    requestPort0334 = 443;
		String requestFile0334 = "/B6/static/images/sprites/common-sprite-payment.png";
		String requestHeader0334 = "GET " + requestFile0334 + " HTTP/" + httpProtocolVersion + "\r\n" + 
				"Host: book.jetblue.com\r\n" + 
				"User-Agent: " + USER_AGENT_1 + "\r\n" + 
				"Accept: */*\r\n" + 
				"Accept-Language: en-US,en;q=0.5\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"DNT: 1\r\n" + 
				"Connection: Keep-Alive\r\n" + 
				"\r\n";

		// execute request
		testURL = new HttpTestURL(requestProt0334, requestHost0334, requestPort0334, requestHeader0334, null, requestTimeout, socketPool, cookieHandler, httpLogVectorObject);
		testURL.setDNSContext(userDNSContext);
		performanceData.setInfoText(threadStep, testURL, -1);		// hint: param #3 is the maximum acceptable response time in milliseconds (-1 = not configured)
		log("[" + threadStep + "] " + testURL.getRequestInfoText() + " ...");

		testURL.execute(performanceData);
		requestHeader0334 = null;		// support garbage collector to reduce memory
		log("   " + testURL.getShortResultText());

		// verify response: status code = 404, content type = [verification disabled], header text fragment = [verification disabled], recorded content size = 321
		// content test algorithm: none - no content check
		urlCallPassed = httpResponseOk(testURL, threadStep, new int[] {404}, null, null, null);		// Test [334] <- Index 334
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



		// all http requests of page #6_1 successful done
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



	boolean synchResponsesParallelRequestsPage_6(int totalLoopCounter, LoadtestPluginContext loopPluginContext, InnerLoopContext innerLoopContext, Object httpLogVectorObject) throws Exception
	{
		int lastThreadStepInMainThread = threadStep;		// save last executed thread step
		
		// wait for the response of all parallel requests
		threadStep = pageThreadHandler.getLastThreadStep();
		pageThreadHandler.waitForSynch();
		log("Page 6 Time = " + pageThreadHandler.getPageTime() + " ms");
		
		
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
		
		String genericFileName = PerformanceData.proposeFileName("A1_Recording", concurrentUsers);
		
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
			System.out.println("# global var departureDate = " + departureDate);
			System.out.println("# global var keyword = " + keyword);
		}
		System.out.println();
		
		
		
		// --------------------------
		// *** start of load test ***
		// --------------------------
		
		
		// initialize performance data
		// ---------------------------
		final int PAGE_BREAKS = 6;  // number of page breaks in execute()  - modify this value if you add or delete some page breaks
		final int MAX_THREAD_STEPS = 329;  // number of URL requests in execute() - modify this value if you add or delete some requests
		performanceData = new PerformanceData(PAGE_BREAKS, MAX_THREAD_STEPS, concurrentUsers, -1, plannedTestDuration, maxPlannedLoops, httpProtocolVersion, plannedStartupDelay, plannedRequestTimeout, samplingInterval, percentilePageSampling, percentileUrlSampling, percentileUrlSamplingAddOption);
		performanceData.setInfoText("A1_Recording");
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
		LoadtestPluginContext globalPluginContext = new LoadtestPluginContext(prxVersion, prxCharEncoding, new A1_Recording());
		try
		{
			// define object for plug-in #1 and call plug-in constructor
			Class pluginClass0 = Class.forName("SelectSeatNumberJetBlue");
			plugin0001 = pluginClass0.newInstance();
			((GenericPluginInterface) plugin0001).construct(globalPluginContext);
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
				
				A1_Recording simulatedUser = new A1_Recording(maxPlannedLoops, plannedRequestTimeout, getUserContextTable().getUserContextList().size());
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
						((GenericPluginInterface) plugin0001).deconstruct(globalPluginContext);
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
			((GenericPluginInterface) plugin0001).deconstruct(globalPluginContext);
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
				
				A1_Recording simulatedUser = new A1_Recording(maxPlannedLoops, plannedRequestTimeout, getUserContextTable().getUserContextList().size());
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



	
	// source code of inline script "GetFlightID"
	public static String getInlineScriptCode_1490806484385()
	{
		return "tdGroupData0 = getTdGroupData(FlightResponseData, 0)\r\nFlight0Data = getSubstring(tdGroupData0, \"showSeatMapPopUp\", \"return false\")\r\n departureDate0 = getSubstring(Flight0Data, \"departureDate:\\\\\\\\\\\\'\", \"\\\\\\\\\\\\',\")\r\n flightNumber0 = getSubstring(Flight0Data, \"flightNumber:\\\\\\\\\\\\'\", \"\\\\\\\\\\\\',\")\r\n cacheKey0 = getSubstring(Flight0Data, \"cacheKey:\\\\\\\\\\\\'\", \"\\\\\\\\\\\\'}\")\r\n arrivalDate0 = = getSubstring(Flight0Data, \"arrivalDate:\\\\\\\\\\\\'\", \"\\\\\\\\\\\\'}\")\r\nbookingCode = getSubstring(Flight0Data, \"bookingClassCode:\\\\\\\\\\\\'\", \"\\\\\\\\',\")\r\n bookingClassCode0 = strReplace(bookingCode, \"\\\\\", \"\")\r\nflightItineraryId = getSubstring(Flight0Data, \"flightItineraryId: \\\\'\", \"', flightNumber\")\r\n flightItineraryId0 = strReplace(flightItineraryId, \"\\\\\", \"\")\r\n\r\n\r\n\r\n\r\nFirstFlightID = \"0,\"+ flightItineraryId0\r\n\r\n//FlightIDs = FlightID1\r\n\r\n// test data\r\n// tdGroupData[0] = showSeatMapPopUp {(\\'{departureDate:\\\\\\'1493290800000\\\\\\',fareFamilyCode:\\\\\\'AN\\\\\\',flightSegmentKey:\\\\\\'0\\\\\\',equipment:\\\\\\'320\\\\\\',flightNumber:\\\\\\'855\\\\\\',bookingClassCode:\\\\\\'S\\\\\\',currency:\\\\\\'USD\\\\\\',flightSegmentId:\\\\\\'0\\\\\\',airlineCode:\\\\\\'B6\\\\\\',destinationAirportCode:\\\\\\'PLS\\\\\\',operatingAirlineCode:\\\\\\'B6\\\\\\',arrivalDate:\\\\\\'1493303880000\\\\\\',originAirportCode:\\\\\\'JFK\\\\\\',inventorySystem:\\\\\\'SABRE\\\\\\',cacheKey:\\\\\\'2044684122\\\\\\'}\\', {selectedFareFamilyCode: selectedFareFamilies[0][0] ? selectedFareFamilies[0][0] : \\'AN\\', selectedDestination: \\'0\\', selectedSegment: \\'0\\', legIndex: \\'\\', flightItineraryId: \\'7\\', flightNumber: \\'855\\'}); return false};\r\n\r\nFUNCTION getTdGroupData(payload, index)\r\nreturn getSubstring(payload, \"tdGroupData[\"+index+\"]\", \"};\")\r\n\r\nFUNCTION getSubstring(inputString, fromString, toString)\r\ntempStr = strSubString(inputString, strIndexOf(inputString, fromString)))\r\nindexTo = strIndexOf(tempStr, toString) - 1\r\nreturn strSubString(tempStr, 1+strLen(fromString), indexTo)\r\n\r\n";
	}
	
	
	/**
	* Execute the inline script "GetFlightID"
	* 
	* @param testURL 	the context of the executed URL call
	* @param urlCallPassed 	a flag that signals (when set to false) if a 'yellow' error did occur after the URL call (triggered by response verification settings)
	* @param threadStep 	the current execution step of the simulated user (page break or URL call)
	* @param totalLoopCounter 	the total number of executed loops - counted overall simulated users
	* @param innerLoopContext 	the context of the current inner loop, or null if the inline script in not executed inside an inner loop
	*
	* @return the context of the inline script
	*/
	public LoadtestInlineScriptContext executeInlineScript_1490806484385(HttpTestURL testURL, boolean urlCallPassed, int threadStep, int totalLoopCounter, InnerLoopContext innerLoopContext)
	{
		LoadtestInlineScriptContext inlineScriptContext = new LoadtestInlineScriptContext("GetFlightID", ProxySnifferVarSourceInlineScript.EXEC_SCOPE_URL_END, "", this, LoadtestInlineScriptContext.RESULT_TYPE_SET_OUTPUT_VARS, 15, threadNumber, threadLoopCounter, socketPool, cookieHandler, testURL, threadStep);
		inlineScriptContext.setUrlCallPassed(urlCallPassed);
		InlineScriptExecutor inlineScriptExecutor = new InlineScriptExecutor(getInlineScriptCode_1490806484385(), inlineScriptContext);
		log("Executing inline script \"" + inlineScriptContext.getScriptTitle() + "\"");
		LoadtestInlineScriptVar inputVar1 = new LoadtestInlineScriptVar("FlightResponseData", Lib.nullToBlank(FlightResponseData), 1);		// note: parameter no. 3 is the scope of the var
		inlineScriptContext.addInputVar(inputVar1);
		LoadtestInlineScriptVar outputVar1 = new LoadtestInlineScriptVar("arrivalDate0", Lib.nullToBlank(arrivalDate0), 1);		// note: parameter no. 3 is the scope of the var
		inlineScriptContext.addOutputVar(outputVar1);
		LoadtestInlineScriptVar outputVar2 = new LoadtestInlineScriptVar("bookingClassCode0", Lib.nullToBlank(bookingClassCode0), 1);		// note: parameter no. 3 is the scope of the var
		inlineScriptContext.addOutputVar(outputVar2);
		LoadtestInlineScriptVar outputVar3 = new LoadtestInlineScriptVar("cacheKey0", Lib.nullToBlank(cacheKey0), 1);		// note: parameter no. 3 is the scope of the var
		inlineScriptContext.addOutputVar(outputVar3);
		LoadtestInlineScriptVar outputVar4 = new LoadtestInlineScriptVar("departureDate0", Lib.nullToBlank(departureDate0), 1);		// note: parameter no. 3 is the scope of the var
		inlineScriptContext.addOutputVar(outputVar4);
		LoadtestInlineScriptVar outputVar5 = new LoadtestInlineScriptVar("FirstFlightID", Lib.nullToBlank(FirstFlightID), 1);		// note: parameter no. 3 is the scope of the var
		inlineScriptContext.addOutputVar(outputVar5);
		LoadtestInlineScriptVar outputVar6 = new LoadtestInlineScriptVar("flightItineraryId0", Lib.nullToBlank(flightItineraryId0), 1);		// note: parameter no. 3 is the scope of the var
		inlineScriptContext.addOutputVar(outputVar6);
		LoadtestInlineScriptVar outputVar7 = new LoadtestInlineScriptVar("flightNumber0", Lib.nullToBlank(flightNumber0), 1);		// note: parameter no. 3 is the scope of the var
		inlineScriptContext.addOutputVar(outputVar7);
		inlineScriptExecutor.execute();		// execute inline script
		arrivalDate0 = inlineScriptContext.getOutputVar("arrivalDate0").getVarValue();
		log("<<< arrivalDate0 = " + arrivalDate0);
		bookingClassCode0 = inlineScriptContext.getOutputVar("bookingClassCode0").getVarValue();
		log("<<< bookingClassCode0 = " + bookingClassCode0);
		cacheKey0 = inlineScriptContext.getOutputVar("cacheKey0").getVarValue();
		log("<<< cacheKey0 = " + cacheKey0);
		departureDate0 = inlineScriptContext.getOutputVar("departureDate0").getVarValue();
		log("<<< departureDate0 = " + departureDate0);
		FirstFlightID = inlineScriptContext.getOutputVar("FirstFlightID").getVarValue();
		log("<<< FirstFlightID = " + FirstFlightID);
		flightItineraryId0 = inlineScriptContext.getOutputVar("flightItineraryId0").getVarValue();
		log("<<< flightItineraryId0 = " + flightItineraryId0);
		flightNumber0 = inlineScriptContext.getOutputVar("flightNumber0").getVarValue();
		log("<<< flightNumber0 = " + flightNumber0);
		for (String stdoutLine : inlineScriptContext.getOutputStreamData())
			log(inlineScriptContext.getScriptTitle() + ": " + stdoutLine);
		for (String stderrLine : inlineScriptContext.getErrorStreamData())
			System.err.println(inlineScriptContext.getScriptTitle() + ": " + stderrLine);
		if (!inlineScriptExecutor.wasSuccessFulExecution())
		{
			if (!inlineScriptContext.isScriptAbort())
				throw new InlineScriptExecutionException("*** Execution of inline script \"" + inlineScriptContext.getScriptTitle() + "\" failed ***");
		}
		return inlineScriptContext;
	}

}	// end of class

