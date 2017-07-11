// Some parts of this code have been automatically generated - copyright for generic plug-in procedure reserved by Ingenieurbuero David Fischer AG, Switzerland.
// Copyright for manual written code belongs to <your name>, <your company>, <your country>
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.StringReader;

import dfischer.utils.GenericPluginInterface;
import dfischer.utils.LoadtestPluginInterface;
import dfischer.utils.LoadtestPluginFixedUserInputField;
import dfischer.utils.LogVector;
import dfischer.utils.LoadtestPluginContext;
import dfischer.utils.HttpLoadTest;
import dfischer.utils.PerformanceData;
import dfischer.utils.CookieHandler;
import dfischer.utils.HttpTestURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
* Load test add-on module.
*/
public class SelectSeatNumberJetBlue implements LoadtestPluginInterface
{
    private String inputJSON = "";        // input parameter #1 - label "Input JSON"
    private String keyword = "EVEN_MORE_SPACE_SEAT";        // input parameter #2 - label "Keyword"
    
    private String seatNumber = "";        // output parameter #1 - label "Seat number"
    
    private LogVector logVector = null;        // internal log vector - use logVector.log(<String>) to write log data
    
    
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
        return "Select seat number for Jet Blue";
    }
    
    
    public String getPluginDescription()
    {
        return "Plugin to select the first available \"EXTRA_MORE_SPACE_SEAT\" from input json string.\n\n";
    }
    
    
    public int getAllowedConstructScope()
    {
        return LoadtestPluginInterface.EXEC_SCOPE_NOT_FIXED;
    }
    
    
    public int getAllowedExecScope()
    {
        return LoadtestPluginInterface.EXEC_SCOPE_NOT_FIXED;
    }
    
    
    public int getAllowedExecOrder()
    {
        return LoadtestPluginInterface.EXEC_ORDER_NOT_FIXED;
    }
    
    
    public boolean allowMultipleUsage()
    {
        return true;
    }
    
    
    public String[] getInputParameterLabels()
    {
        String[] labels = new String[2];
        labels[0] = "Input JSON";
        labels[1] = "Keyword";
        return labels;
    }
    
    
    public LoadtestPluginFixedUserInputField[] getFixedUserInputFields()
    {
        return null;
    }
    
    
    public int allowOptionalInputParameter()
    {
        return 1;        // optional input parameters starting from parameter #2
    }
    
    
    public String[] getOutputParameterLabels()
    {
        String[] labels = new String[1];
        labels[0] = "Seat number";
        return labels;
    }
    
    
    public int allowOptionalOutputParameter()
    {
        return -1;        // all output parameters required
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
     * Initialize plug-in at start of load test.
     */
    public void construct(Object context)
    {
        // LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
    }
    
    
    /**
     * Transfer input parameter before execute() is called.
     *
     * input parameter #1: (String) inputJSON / default value = '' / label "Input JSON"
     * input parameter #2: (String) keyword / default value = 'EVEN_MORE_SPACE_SEAT' / label "Keyword" / [optional]
     *
     * Note: all input parameters are always converted from strings.
     */
    public void setInputParameter(int parameterNumber, Object parameterValue)
    {
        switch (parameterNumber)
        {
            case 0:
                inputJSON = (String) parameterValue;
                break;
            case 1:
                keyword = (String) parameterValue;
                break;
            default:
                break;
        }
    }
    
    
    /**
     * Execute plug-in .
     *
     * Intrinsic plug-in implementation.
     */
    public void execute(Object context)
    {

    	 logVector = new LogVector();
    	 LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
    	 
    	 
    	 try{

    		 BufferedReader reader = new BufferedReader(new StringReader(inputJSON));
    		 String seats = reader.readLine();
    		 while(!seats.contains("plane:"))
    			 seats = reader.readLine();
    	 
    		 seats = "{"+seats+"}";
    		 JSONObject obj = null;
    		 try{
    		 	obj = new JSONObject(seats);
    		 }catch(Exception exc){
    			 exc.printStackTrace();
    			inputJSON = JSONObject.quote(seats);
    		 	obj = new JSONObject(seats);
    		 }	     
    		 
    		 String seatNum = "";
    		 JSONArray arr = obj.getJSONArray("plane");
    		 
    		 setSeat(arr, keyword);
    		 //If we couldnt find the seat we are searching for, grab first available
    		 if(seatNumber == "")
    			 setSeat(arr, "");
    		 
    		 if(seatNumber == "")
    			 logVector.log("Didn't find an available seat");
    			 
    		 //ExtractValues(obj, arr);
    		 
    		 //outputString = String.format(outputString, arr);
    		 
    	 }catch(Exception e){
    		 e.printStackTrace();
    		 logVector.log("jsonString: "+inputJSON);
    		 logVector.log(e.getMessage());
    	 }
    	 
    }


	private void setSeat(JSONArray arr, String seatTypeKeyWord) throws JSONException {
		String seatNum;
		for(int i=0;i<arr.length();i++){
			 JSONArray tempArr = arr.getJSONArray(i);
			 seatNum = tempArr.get(0).toString();
			 logVector.log(seatNum);
			 JSONArray subArray = tempArr.getJSONArray(2);
			 for(int j=0;j<subArray.length();j++){
				 // skip invalid seats
				 try{
					 subArray.getJSONArray(j).get(3);
				 }catch(Exception e){
					 continue;
				 }
				 String availabilityCode = subArray.getJSONArray(j).get(1).toString();
				 String seatType = subArray.getJSONArray(j).get(3).toString(); 
				 //If we're looking for a specific seat
				 if(seatTypeKeyWord != ""){
					 // code 8 means available seat otherwise 14 for seat taken
					 if(availabilityCode.equals("8") && seatType.contains(seatTypeKeyWord)){
						 logVector.log(availabilityCode);
						 logVector.log(seatType);
						 seatNumber = seatNum + subArray.getJSONArray(j).get(0);
						 
					 }
				 }else{
					 if(availabilityCode.equals("8")){
						 logVector.log(availabilityCode);
						 logVector.log(seatType);
						 seatNumber = seatNum + subArray.getJSONArray(j).get(0);
						 
					 }
					 
				 }
			 }

		 }
		
		
	}
    
    
    /**
     * Return plug-in output parameter. 
     *
     * output parameter #1: (String) seatNumber / default value = '' / label "Seat number"
     *
     * Note: all output parameters are always converted to strings.
     */
    public Object getOutputParameter(int parameterNumber)
    {
        switch (parameterNumber)
        {
            case 0:
                return seatNumber;
            default:
                return null;
        }
    }
    
    
    /**
     * Finalize plug-in at end of load test.
     */
    public void deconstruct(Object context)
    {
        // LoadtestPluginContext pluginContext = (LoadtestPluginContext) context;
    }
    
    
    
    // ----------------------------------------------------------------------------
    // PART 3: stand-alone test utility - optional - used for plug-in development
    // ----------------------------------------------------------------------------
    
    
    public static void main(String[] args)
    {
        try
        {
            // vvv --- sample code --- vvv
            
            SelectSeatNumberJetBlue plugin = new SelectSeatNumberJetBlue();
            plugin.construct(null);
            plugin.setInputParameter(0, "plane:[[1,[11],[[\"A\",14,[85,49,72],\"EVEN_MORE_SPACE_SEAT\"],[\"B\",14,[49,3,72],\"EVEN_MORE_SPACE_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[2,[11],[[\"A\",8,[85,49,8],\"REGULAR_SEAT\"],[\"B\",8,[49,3,8],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",8,[49,3,8],\"REGULAR_SEAT\"],[\"D\",8,[85,49,8],\"REGULAR_SEAT\"]]],[3,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[4,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[5,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[6,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[7,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[8,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[9,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[10,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[11,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",8,[49,3,63],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[12,[11,4],[[\"A\",8,[17,85,49,72],\"EVEN_MORE_SPACE_SEAT_2\"],[\"B\",8,[17,49,3,72],\"EVEN_MORE_SPACE_SEAT_2\"],[\"\",3,[47,103]],[\"C\",8,[17,49,3,72],\"EVEN_MORE_SPACE_SEAT_2\"],[\"D\",8,[17,85,49,72],\"EVEN_MORE_SPACE_SEAT_2\"]]],[13,[11],[[\"A\",8,[85,49,72],\"EVEN_MORE_SPACE_SEAT_2\"],[\"B\",8,[49,3,72],\"EVEN_MORE_SPACE_SEAT_2\"],[\"\",3,[47,103]],[\"C\",8,[49,3,72],\"EVEN_MORE_SPACE_SEAT_2\"],[\"D\",8,[85,49,72],\"EVEN_MORE_SPACE_SEAT_2\"]]],[14,[11],[[\"A\",8,[85,49,72],\"EVEN_MORE_SPACE_SEAT_2\"],[\"B\",8,[49,3,72],\"EVEN_MORE_SPACE_SEAT_2\"],[\"\",3,[47,103]],[\"C\",8,[49,3,72],\"EVEN_MORE_SPACE_SEAT_2\"],[\"D\",8,[85,49,72],\"EVEN_MORE_SPACE_SEAT_2\"]]],[15,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[16,[11],[[\"A\",8,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[17,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[18,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[19,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[20,[11],[[\"A\",8,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[21,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[22,[11],[[\"A\",14,[85,49],\"REGULAR_SEAT\"],[\"B\",14,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",14,[49,3],\"REGULAR_SEAT\"],[\"D\",14,[85,49],\"REGULAR_SEAT\"]]],[23,[11],[[\"A\",8,[85,49],\"REGULAR_SEAT\"],[\"B\",8,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",8,[49,3],\"REGULAR_SEAT\"],[\"D\",8,[85,49],\"REGULAR_SEAT\"]]],[24,[11],[[\"A\",8,[85,49],\"REGULAR_SEAT\"],[\"B\",8,[49,3],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",8,[49,3],\"REGULAR_SEAT\"],[\"D\",8,[85,49],\"REGULAR_SEAT\"]]],[25,[11],[[\"A\",8,[85,49,8],\"REGULAR_SEAT\"],[\"B\",8,[49,3,8],\"REGULAR_SEAT\"],[\"\",3,[47,103]],[\"C\",8,[49,3,8],\"REGULAR_SEAT\"],[\"D\",8,[85,49,8],\"REGULAR_SEAT\"]]]]");
            plugin.execute(null);
            System.out.println(plugin.getOutputParameter(0));
            plugin.deconstruct(null);
            
            // ^^^ --- sample code --- ^^^
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    


}    // end of class
