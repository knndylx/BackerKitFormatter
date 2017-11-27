/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackerKitFormatter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author akenn
 */
public class OrderList {
    //WHAT WE CAN DO WITH THE ORDER LIST CLASS
    //make all orders one line so that they are readable by the constructor
    //make the addresses full again, and produce the combos needed
    //move all cancelled and failed to a separate file
    //move all people that haven't filled out a survey to a new file
    //make each cooler go on to a separate line and place the accessories
    //  onto the cooler that is going to ship first
    //get a file with the first 300 coolers that are going to ship
    //get another file with the rest of the coolers that aren't the first 300
//==============================================================================
// Properties
//==============================================================================
    private ArrayList<Order> orderList = new ArrayList<>();
    private ArrayList<Order> cancelFail = new ArrayList<>();
    
//==============================================================================
// Constructors
//==============================================================================
    /**
     * Construct the order list from a CSV file from BackerKit.
     * Produces the combos of SKUs and makes the Full Address field pretty
     * @param f CSV file from BackerKit
     */
    public OrderList(File f){
        orderList = getOrders(f);
        for(Order o : orderList){
            o.formatFullAddress();
            o.getSKUs().produceCombos();  
        }
    }
//==============================================================================
// Methods
//==============================================================================
    /**
     * Get orders from the csv file
     * eventually will be used to populate the order objects
     * @returns a 2d array list with the orders and their entries for each field
     */
    private ArrayList<Order> getOrders(File originalCSVFile){
        String inputFileName = makeAllOrdersOneLine(originalCSVFile);
        ArrayList<ArrayList<String>> orders = new ArrayList<ArrayList<String>>();
        ArrayList<String> currentOrder = new ArrayList<String>();
        
        File oneLineOrdersFile = new File(inputFileName);
        BufferedReader br = null;
        
        try{
            br = new BufferedReader(new FileReader(oneLineOrdersFile));
            //skip two lines for the titles and example person
            br.readLine();
            br.readLine();
            String line = br.readLine();
            while(line!=null){
                currentOrder = new ArrayList<String>();
                //splits the line where there are commas but ignores all commas that are embedded within quotes
                String[] order = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for(String token : order){
                    currentOrder.add(token);
                }
                orders.add(currentOrder);
                line = br.readLine();
            }
        } catch (FileNotFoundException e){
            System.err.println("Error opening the file in the reader.");
        } catch (IOException e){
            System.err.println("Error reading the lines of the file.");
        } finally{
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                System.err.println("Error closing the reader.");
            }
        }
        
        ArrayList<Order> arrayReturn = new ArrayList<>();
        
        for(ArrayList<String> o : orders){
            arrayReturn.add(new Order(o));
        }
        
        return arrayReturn;
    }
    
    /**
     * makes all of the orders on one line so that they can be read by getOrders
     * @param fileName is the name of the file that you would like to organize
     */
    private String makeAllOrdersOneLine(File f){
        String outputFileName = "originalAllOrdersOneLine.csv";
        //replace all new lines that aren't after a quote by a slash
        //trying to make all of the orders one line and replace the new line character from the weird address box with a slash
        try{
            Path path = f.toPath();
            Path newPath = Paths.get(outputFileName);
            Charset charset = StandardCharsets.UTF_8;

            String content = new String(Files.readAllBytes(path), charset);
            //replace all new lines with a space
            content = content.replaceAll("\\r\\n|\\r|\\n", " ");
            //replace all quote then space then quote by a new line
            content = content.replaceAll("\" \"","\"\n\"");
            char[] contentCharArray = content.toCharArray();
            char[] contentMinusLastSpace = Arrays.copyOfRange(contentCharArray, 0, contentCharArray.length - 1);
            content = String.valueOf(contentMinusLastSpace);
            Files.write(newPath, content.getBytes(charset));
        } catch (IOException e) {
            System.out.println("Error reading or writing the file.");
        }
        return outputFileName;
    }
    
    /**
     * Output orderList to a CSV
     * @param fileName The name of the CSV file that is the output
     * @param dirName The name of the output directory that the file goes into
     */
    public void writeOrdersToFile(String fileName, String dirName){
        File dout = new File(dirName);
        File dfOut = new File(dout, fileName);
	FileOutputStream fos = null;
        BufferedWriter bw = null;
        try{
            fos = new FileOutputStream(dfOut);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write("\"Id\",\"Service Guid\",\"Platform\",\"Reward Id\",\"Name\",\"Email\",\"Address Name\",\"Address Line 1\",\"Address Line 2\",\"Address City\",\"Address State\",\"Address Country\",\"Address Postal Code\",\"Address Phone Number\",\"Pledge Amount\",\"Pledge Status\",\"Notes\",\"Public Notes\",\"Pledged At\",\"Survey Answered At\",\"Full Address\",\"Full Country\",\"Reward Price\",\"Reward Description\",\"Order Placed\",\"Order ID\",\"Order Status\",\"Order Charged\",\"Charge Token\",\"Funds Added in BackerKit\",\"Add-on Shipping Subtotal\",\"Pledge Level Shipping Subtotal\",\"Total Spent\",\"ABH\",\"ABO\",\"ACL\",\"DESERT COOLER\",\"ADCH\",\"MOSS COOLER\",\"NARWHAL BLUE COOLER\",\"ANL\",\"POWDER COOLER\",\"APBT\",\"PINK UNICORN COOLER\",\"ARTLC\",\"ARTMC\",\"ARTSC\",\"ARTXLC\",\"ARTXXLC\",\"ASB\",\"ATD\",\"ATL\",\"60DROLLR\",\"60MROLLR\",\"60PROLLR\",\"60NBROLLR\",\"60PUROLLR\",\"60DBIKR\",\"60MBIKR\",\"60PBIKR\",\"60NBBIKR\",\"60PUBIKR\"");
            bw.newLine();
            for (int i = 0; i < orderList.size() - 1; i++) {
                    bw.write(orderList.get(i).toString());
                    bw.newLine();
            }
            bw.write(orderList.get(orderList.size() - 1).toString());
        } catch(Exception e){
            System.out.println("Error writing the file.");
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
            } catch (IOException e) {
                System.err.println("Error closing the writer.");
            }
        }
    }
//==============================================================================
// Accessors
//==============================================================================
}
