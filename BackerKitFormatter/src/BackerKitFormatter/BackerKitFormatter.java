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
        
        OrderList oList = new OrderList(new File("original112817.csv"));
        oList.outputOrderFiles();
    }
    
    //organize the list in order of time pledged to the campaign

    
//    //separate the order list into the first 300 coolers that aren't blue or pink and then
//    //all of the others
//    public void first300OrderFiles(ArrayList<Order> dataEntry){
//        BackerKitFormatter bkoda = new BackerKitFormatter();
//        ArrayList<Order> notFirst300 = bkoda.copyOrderList(dataEntry);
//        ArrayList<Order> first300NotBlueOrPink = new ArrayList<>();
//        
//        Collections.sort(notFirst300);
//        
//        int i = 0;
//        while(i < 300){
//            outer: for(Order o: notFirst300){
//                if(o.getSKUs().getdRollr() == 1 ||
//                   o.getSKUs().getdBikr() == 1  ||
//                   o.getSkus().getmRollr() == 1 ||
//                   o.getSKUs().getmBikr() == 1  ||
//                   o.getSKUs().getpRollr() == 1 ||
//                   o.getSKUs().getpBikr() == 1    
//                   )
//                {
//                    first300NotBlueOrPink.add(o);
//                    notFirst300.remove(o);
//                    i++;
//                    break outer;
//                }
//            }
//        }
//        
//        bkoda.writeOrdersToFile("notFirst300.csv", notFirst300);
//        bkoda.writeOrdersToFile("first300NotBlueOrPink.csv", first300NotBlueOrPink);
//    }
    
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
    

}