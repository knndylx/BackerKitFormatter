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
import java.util.Collections;

/**
 *
 * @author akenn
 */
public class OrderList {
    //WHAT WE CAN DO WITH THE ORDER LIST CLASS
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
    
//==============================================================================
// Constructors
//==============================================================================
    /**
     * Construct the order list from a CSV file from BackerKit.
     * Produces the combos of SKUs and makes the Full Address field pretty
     * makes each cooler on a separate line and adds accessories to the one that
     * is going to ship first
     * @param f CSV file from BackerKit
     */
    public OrderList(File f){
        orderList = populateOrderList(f);
        for(Order o : orderList){
            o.formatFullAddress();
            //o.getSKUs().produceCombos();  
        }
        //want to sort after the lines are made
        //TODO: test to make sure that the accessories are on the first cooler
        //  shipment
        Collections.sort(orderList);
    }
    
    /**
     * Copy constructor
     */
    public OrderList(OrderList o){
        for(Order order: o.getOrderList()){
            orderList.add(order);
        }
    }
//==============================================================================
// Test
//==============================================================================
    public void outputOrderFiles(){
        //WORKS
        //no combos
        //test check that the numbers add up to the original file
        writeOrderListToFile("OrdersNoCombos.csv", "OrderListTest", orderList);
        //WORKS
        //make the combos and print another file
        //test check the numbers against the original file and the non combo file
        for(Order o: orderList){
            o.getSKUs().produceCombos();
        }
        writeOrderListToFile("OrdersWithCombos.csv", "OrderListTest", orderList);
        //make lines
        //check that the numbers add up to the original file
        makeLines();
        writeOrderListToFile("OrdersAllSeparateLines.csv", "OrderListTest", orderList);
        
    }
//==============================================================================
// Methods
//==============================================================================
    /**
     * Get orders from the csv file
     * eventually will be used to populate the order objects
     * @returns a 2d array list with the orders and their entries for each field
     */
    private ArrayList<Order> populateOrderList(File originalCSVFile){
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
     * @see populateOrderList
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
    private void writeOrderListToFile(String fileName, String dirName, ArrayList<Order> oList){
        File dout = new File(dirName);
        File dfOut = new File(dout, fileName);
	FileOutputStream fos = null;
        BufferedWriter bw = null;
        try{
            fos = new FileOutputStream(dfOut);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write("\"Id\",\"Service Guid\",\"Platform\",\"Reward Id\",\"Name\",\"Email\",\"Address Name\",\"Address Line 1\",\"Address Line 2\",\"Address City\",\"Address State\",\"Address Country\",\"Address Postal Code\",\"Address Phone Number\",\"Pledge Amount\",\"Pledge Status\",\"Notes\",\"Public Notes\",\"Pledged At\",\"Survey Answered At\",\"Full Address\",\"Full Country\",\"Reward Price\",\"Reward Description\",\"Order Placed\",\"Order ID\",\"Order Status\",\"Order Charged\",\"Charge Token\",\"Funds Added in BackerKit\",\"Add-on Shipping Subtotal\",\"Pledge Level Shipping Subtotal\",\"Total Spent\",\"ABH\",\"ABO\",\"ACL\",\"DESERT COOLER\",\"ADCH\",\"MOSS COOLER\",\"NARWHAL BLUE COOLER\",\"ANL\",\"POWDER COOLER\",\"APBT\",\"PINK UNICORN COOLER\",\"ARTLC\",\"ARTMC\",\"ARTSC\",\"ARTXLC\",\"ARTXXLC\",\"ASB\",\"ATD\",\"ATL\",\"60DROLLR\",\"60MROLLR\",\"60PROLLR\",\"60NBROLLR\",\"60PUROLLR\",\"60DBIKR\",\"60MBIKR\",\"60PBIKR\",\"60NBBIKR\",\"60PUBIKR\"");
            bw.newLine();
            for (int i = 0; i < oList.size() - 1; i++) {
                    bw.write(oList.get(i).toString());
                    bw.newLine();
            }
            bw.write(oList.get(oList.size() - 1).toString());
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
    
    /**
     * save a file that has each of the orders split up into lines
     * one line for each cooler that was ordered and the accessories go into the first line
     */
    private void makeLines(){
        ArrayList<Order> orderLineSplit = new ArrayList<>();
        ArrayList<Order> orderListCopy = copyArrayList(this.orderList);
        
        
        for(Order o: orderListCopy){
            Order copyOrder = new Order(o);
            SKUs sku = new SKUs(copyOrder.getSKUs());
            boolean hasAddedAccessoriesForThisOrder = false;
            SKUs accessorySKUs = new SKUs(copyOrder);
            //SKUs accessorySKUs = new SKUs(o);
            int dRollrCount = sku.getdRollr();
            while(dRollrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().dRollr(1).build());
                if(!hasAddedAccessoriesForThisOrder){
                    copyOrder.getSKUs().add(accessorySKUs);
                    hasAddedAccessoriesForThisOrder = true;
                }
                orderLineSplit.add(new Order(copyOrder));
                
                dRollrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int pRollrCount = sku.getpRollr();
            while(pRollrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().pRollr(1).build());
                if(!hasAddedAccessoriesForThisOrder){
                    copyOrder.getSKUs().add(accessorySKUs);
                    hasAddedAccessoriesForThisOrder = true;
                }
                orderLineSplit.add(new Order(copyOrder));
                pRollrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int mRollrCount = sku.getmRollr();
            while(mRollrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().mRollr(1).build());
                if(!hasAddedAccessoriesForThisOrder){
                    copyOrder.getSKUs().add(accessorySKUs);
                    hasAddedAccessoriesForThisOrder = true;
                }
                orderLineSplit.add(new Order(copyOrder));
                mRollrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int nbRollrCount = sku.getNbRollr();
            while(nbRollrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().nbRollr(1).build());
                if(!hasAddedAccessoriesForThisOrder){
                    copyOrder.getSKUs().add(accessorySKUs);
                    hasAddedAccessoriesForThisOrder = true;
                }
                orderLineSplit.add(new Order(copyOrder));
                nbRollrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int puRollrCount = sku.getPuRollr();
            while(puRollrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().puRollr(1).build());
                if(!hasAddedAccessoriesForThisOrder){
                    copyOrder.getSKUs().add(accessorySKUs);
                    hasAddedAccessoriesForThisOrder = true;
                }
                orderLineSplit.add(new Order(copyOrder));
                puRollrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int dBikrCount = sku.getdBikr();
            while(dBikrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().dBikr(1).build());
                if(!hasAddedAccessoriesForThisOrder){
                    copyOrder.getSKUs().add(accessorySKUs);
                    hasAddedAccessoriesForThisOrder = true;
                }
                orderLineSplit.add(new Order(copyOrder));
                dBikrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int pBikrCount = sku.getpBikr();
            while(pBikrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().pBikr(1).build());
                if(!hasAddedAccessoriesForThisOrder){
                    copyOrder.getSKUs().add(accessorySKUs);
                    hasAddedAccessoriesForThisOrder = true;
                }
                orderLineSplit.add(new Order(copyOrder));
                pBikrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int mBikrCount = sku.getmBikr();
            while(mBikrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().mBikr(1).build());
                if(!hasAddedAccessoriesForThisOrder){
                    copyOrder.getSKUs().add(accessorySKUs);
                    hasAddedAccessoriesForThisOrder = true;
                }
                orderLineSplit.add(new Order(copyOrder));
                mBikrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int nbBikrCount = sku.getNbBikr();
            while(nbBikrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().nbBikr(1).build());
                if(!hasAddedAccessoriesForThisOrder){
                    copyOrder.getSKUs().add(accessorySKUs);
                    hasAddedAccessoriesForThisOrder = true;
                }
                orderLineSplit.add(new Order(copyOrder));
                nbBikrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int puBikrCount = sku.getPuBikr();
            while(puBikrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().puBikr(1).build());
                if(!hasAddedAccessoriesForThisOrder){
                    copyOrder.getSKUs().add(accessorySKUs);
                    hasAddedAccessoriesForThisOrder = true;
                }
                orderLineSplit.add(new Order(copyOrder));
                puBikrCount -= 1;
            }
            if(sku.getdBikr() + sku.getdRollr() + sku.getmBikr() + sku.getmRollr() + sku.getpBikr() + sku.getpRollr() + sku.getNbBikr() + sku.getNbRollr() + sku.getPuBikr() + sku.getPuRollr() == 0){
                orderLineSplit.add(new Order(copyOrder));
            }
        }
        
//        boolean hasAdded = false;
//        for(Order o1 : orderListCopy){
//            SKUs accessorySKU = new SKUs(o1);
//            for(Order o2 : orderLineSplit){
//                if(o1.getId().equals(o2.getId())){
//                    if(!hasAdded){
//                        o2.getSKUs().add(accessorySKU);
//                        hasAdded = true;
//                    }
//                }
//            }
//            hasAdded = false;
//        }
        
        orderList = orderLineSplit;
    }
        
    /**
     * Make a deep copy of an ArrayList
     * @param listToCopy
     * @return the copy of the arraylist
     */
    private ArrayList<Order> copyArrayList(ArrayList<Order> listToCopy){
        ArrayList<Order> copiedList = new ArrayList<>();
        
        for(Order o: listToCopy){
            copiedList.add(new Order(o));
        }
        
        return copiedList;
    }
//==============================================================================
// Accessors
//==============================================================================

    /**
     * @return the orderList
     */
    public ArrayList<Order> getOrderList() {
        return orderList;
    }
}
