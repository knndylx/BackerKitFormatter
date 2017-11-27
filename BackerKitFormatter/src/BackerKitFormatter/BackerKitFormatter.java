/*
 * This project reads and edits and analyzes the data from a CSV file from the BackerKit export feature.
 */
package BackerKitFormatter;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
//import java.lang.StringBuilder;
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
 * @author Alex
 */
public class BackerKitFormatter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        BackerKitFormatter formatter = new BackerKitFormatter();
//        String output = "replace.csv";
//        String input = "Original111417.csv";
//        formatter.makeAllOrdersOneLine(input, output);
//        ArrayList<Order> orderList = formatter.getOrders(output);
//        for(Order o : orderList){
//            o.formatFullAddress();
//            o.getSKUs().produceCombos();  
//        }
//        ArrayList<Order> cancelFail = formatter.moveCanceledFailed(orderList);
//        String finalOutput = "outputTest" + System.currentTimeMillis() + ".csv";
//        ArrayList<Order> separateOrderList = formatter.makeLines(formatter.copyOrderList(orderList));
//        formatter.first300OrderFiles(formatter.copyOrderList(separateOrderList));
//        System.out.println();
//        formatter.writeOrdersToFile(finalOutput, orderList);
//        formatter.writeOrdersToFile("failCancel.csv", cancelFail);
//        File file = new File("failCancel.csv");
        
        OrderList oList = new OrderList(new File("Original111417.csv"));
        oList.writeOrdersToFile("oListTest.CSV", "OrderListTest");
    }
    
    //organize the list in order of time pledged to the campaign
    
    //make a deep copy of the order list
    public ArrayList<Order> copyOrderList(ArrayList<Order> listToCopy){
        ArrayList<Order> copiedList = new ArrayList<>();
        
        for(Order o: listToCopy){
            copiedList.add(new Order(o));
        }
        
        return copiedList;
    }
    
    //separate the order list into the first 300 coolers that aren't blue or pink and then
    //all of the others
    public void first300OrderFiles(ArrayList<Order> dataEntry){
        BackerKitFormatter bkoda = new BackerKitFormatter();
        ArrayList<Order> notFirst300 = bkoda.copyOrderList(dataEntry);
        ArrayList<Order> first300NotBlueOrPink = new ArrayList<>();
        
        Collections.sort(notFirst300);
        
        int i = 0;
        while(i < 300){
            outer: for(Order o: notFirst300){
                if(o.getSKUs().getdRollr() == 1 ||
                   o.getSKUs().getdBikr() == 1  ||
                   o.getSkus().getmRollr() == 1 ||
                   o.getSKUs().getmBikr() == 1  ||
                   o.getSKUs().getpRollr() == 1 ||
                   o.getSKUs().getpBikr() == 1    
                   )
                {
                    first300NotBlueOrPink.add(o);
                    notFirst300.remove(o);
                    i++;
                    break outer;
                }
            }
        }
        
        bkoda.writeOrdersToFile("notFirst300.csv", notFirst300);
        bkoda.writeOrdersToFile("first300NotBlueOrPink.csv", first300NotBlueOrPink);
    }
    
    /**
     * move all canceled and failed orders to another array.  modifies the input list and removes
     * the canceled and fails
     * @return failed and canceled orders
     */
    public ArrayList<Order> moveCanceledFailed(ArrayList<Order> orders){
        ArrayList<Order> cancelFail = new ArrayList<>();
        ArrayList<Order> cancelFailReturn = new ArrayList<>();
        for(Order o: orders){
            if(o.getEmail().contains("canceled") || o.getPledgeStatus() == PledgeStatus.failed){
                cancelFail.add(o);
            }
        }
        orders.removeAll(cancelFail);
        for(Order o: cancelFail){
            cancelFailReturn.add(new Order(o));
        }
        return cancelFailReturn;
    }
    
    /**
     * save a file that has each of the orders split up into lines
     * one line for each cooler that was ordered and the accessories go into the first line
     */
    public ArrayList<Order> makeLines(ArrayList<Order> finalOrder){
        ArrayList<Order> orderLineSplit = new ArrayList<>();
        BackerKitFormatter bkoda = new BackerKitFormatter();
        ArrayList<Order> orderListCopy = bkoda.copyOrderList(finalOrder);
        
        
        for(Order o: orderListCopy){
            Order copyOrder = new Order(o);
            SKUs sku = new SKUs(copyOrder.getSKUs());
            SKUs accessorySKUs = new SKUs(o);
            int dRollrCount = sku.getdRollr();
            while(dRollrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().dRollr(1).build());
                orderLineSplit.add(new Order(copyOrder));
                dRollrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int pRollrCount = sku.getpRollr();
            while(pRollrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().pRollr(1).build());
                orderLineSplit.add(new Order(copyOrder));
                pRollrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int mRollrCount = sku.getmRollr();
            while(mRollrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().mRollr(1).build());
                orderLineSplit.add(new Order(copyOrder));
                mRollrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int nbRollrCount = sku.getNbRollr();
            while(nbRollrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().nbRollr(1).build());
                orderLineSplit.add(new Order(copyOrder));
                nbRollrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int puRollrCount = sku.getPuRollr();
            while(puRollrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().puRollr(1).build());
                orderLineSplit.add(new Order(copyOrder));
                puRollrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int dBikrCount = sku.getdBikr();
            while(dBikrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().dBikr(1).build());
                orderLineSplit.add(new Order(copyOrder));
                dBikrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int pBikrCount = sku.getpBikr();
            while(pBikrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().pBikr(1).build());
                orderLineSplit.add(new Order(copyOrder));
                pBikrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int mBikrCount = sku.getmBikr();
            while(mBikrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().mBikr(1).build());
                orderLineSplit.add(new Order(copyOrder));
                mBikrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int nbBikrCount = sku.getNbBikr();
            while(nbBikrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().nbBikr(1).build());
                orderLineSplit.add(new Order(copyOrder));
                nbBikrCount -= 1;
            }
            copyOrder = new Order(o);
            sku = new SKUs(copyOrder.getSKUs());
            int puBikrCount = sku.getPuBikr();
            while(puBikrCount > 0){
                copyOrder.setSkus(new SKUs.SKUsBuilder().puBikr(1).build());
                orderLineSplit.add(new Order(copyOrder));
                puBikrCount -= 1;
            }
            if(sku.getdBikr() + sku.getdRollr() + sku.getmBikr() + sku.getmRollr() + sku.getpBikr() + sku.getpRollr() + sku.getNbBikr() + sku.getNbRollr() + sku.getPuBikr() + sku.getPuRollr() == 0){
                orderLineSplit.add(new Order(copyOrder));
            }
        }
        
        boolean hasAdded = false;
        for(Order o1 : orderListCopy){
            SKUs accessorySKU = new SKUs(o1);
            for(Order o2 : orderLineSplit){
                if(o1.getId().equals(o2.getId())){
                    if(!hasAdded){
                        o2.getSKUs().add(accessorySKU);
                        hasAdded = true;
                    }
                }
            }
            hasAdded = false;
        }
        
        writeOrdersToFile("outputLinesTest.csv", orderLineSplit);
        return orderLineSplit;
    }
    
    
    /**
     * Writes an arraylist of orders to a csv file in the same way that 
     * the parent file came in to compare and check for errors in the text
     * to object conversion process
     */
    public void writeOrdersToFile(String filename, ArrayList<Order> orders){
        File fout = new File(filename);
	FileOutputStream fos = null;
        BufferedWriter bw = null;
        try{
            fos = new FileOutputStream(fout);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write("\"Id\",\"Service Guid\",\"Platform\",\"Reward Id\",\"Name\",\"Email\",\"Address Name\",\"Address Line 1\",\"Address Line 2\",\"Address City\",\"Address State\",\"Address Country\",\"Address Postal Code\",\"Address Phone Number\",\"Pledge Amount\",\"Pledge Status\",\"Notes\",\"Public Notes\",\"Pledged At\",\"Survey Answered At\",\"Full Address\",\"Full Country\",\"Reward Price\",\"Reward Description\",\"Order Placed\",\"Order ID\",\"Order Status\",\"Order Charged\",\"Charge Token\",\"Funds Added in BackerKit\",\"Add-on Shipping Subtotal\",\"Pledge Level Shipping Subtotal\",\"Total Spent\",\"ABH\",\"ABO\",\"ACL\",\"DESERT COOLER\",\"ADCH\",\"MOSS COOLER\",\"NARWHAL BLUE COOLER\",\"ANL\",\"POWDER COOLER\",\"APBT\",\"PINK UNICORN COOLER\",\"ARTLC\",\"ARTMC\",\"ARTSC\",\"ARTXLC\",\"ARTXXLC\",\"ASB\",\"ATD\",\"ATL\",\"60DROLLR\",\"60MROLLR\",\"60PROLLR\",\"60NBROLLR\",\"60PUROLLR\",\"60DBIKR\",\"60MBIKR\",\"60PBIKR\",\"60NBBIKR\",\"60PUBIKR\"");
            bw.newLine();
            for (int i = 0; i < orders.size() - 1; i++) {
                    bw.write(orders.get(i).toString());
                    bw.newLine();
            }
            bw.write(orders.get(orders.size() - 1).toString());
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
     * Get orders from the csv file
     * eventually will be used to populate the order objects
     * @returns a 2d array list with the orders and their entries for each field
     */
    public ArrayList<Order> getOrders(String filename){
        ArrayList<ArrayList<String>> orders = new ArrayList<ArrayList<String>>();
        ArrayList<String> currentOrder = new ArrayList<String>();
        
        File f = new File(filename);
        BufferedReader br = null;
        
        try{
            br = new BufferedReader(new FileReader(f));
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
     * makes all of the orders on one line so that they can be read by the order reader
     * @param fileName is the name of the file that you would like to organize
     */
    public void makeAllOrdersOneLine(String fileName, String output){
        //replace all new lines that aren't after a quote by a slash
        //trying to make all of the orders one line and replace the new line character from the weird address box with a slash
        try{
            Path path = Paths.get(fileName);
            Path newPath = Paths.get(output);
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
    }
}