/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backerkitorderdataanalyzer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author akenn
 */
public class BackerKitOrderDataAnalyzerTest {
//    @Test
//    public void testOrderClassFileOut() {
//        System.out.println("ordersToFile");
//        BackerKitOrderDataAnalyzer bkoda = new BackerKitOrderDataAnalyzer();
//        File fOriginal = new File("CSVTest111317.csv");
//        File fMine = new File("outputTest111317.csv");
//        byte[] fOriginalb = null;
//        byte[] fMineb = null;
//        try{
//            fOriginalb = Files.readAllBytes(Paths.get("replace111317.csv"));
//            fMineb = Files.readAllBytes(Paths.get("outputTest111317.csv"));
//        } catch (Exception e){
//            System.out.println("Error");
//        }
//        System.out.println(Arrays.equals(fOriginalb, fMineb));
//    }
    
    @Test
    public void testComboMakerOrder() {
        System.out.println("combo maker test");
        BackerKitOrderDataAnalyzer bkoda = new BackerKitOrderDataAnalyzer();
        String output = "replaceComboMakerTest.csv";
        String input = "Original111417.csv";
        bkoda.makeAllOrdersOneLine(input, output);
        ArrayList<Order> orders = bkoda.getOrders(output);
        //make a copy of the orders
        ArrayList<Order> ordersAfterCombo = new ArrayList<>(orders);
        //combine orders
        for(Order o: ordersAfterCombo)
            o.getSKUs().produceCombos();
        for(Order o: ordersAfterCombo){
            int dRollrCount = o.getSKUs().getdRollr();
            int mRollrCount = o.getSKUs().getmRollr();
            int pRollrCount = o.getSKUs().getpRollr();
            int nbRollrCount = o.getSKUs().getNbRollr();
            int puRollrCount = o.getSKUs().getPuRollr();
            int dBikrCount = o.getSKUs().getdBikr();
            int mBikrCount = o.getSKUs().getmBikr();
            int pBikrCount = o.getSKUs().getpBikr();
            int nbBikrCount = o.getSKUs().getNbBikr();
            int puBikrCount = o.getSKUs().getPuBikr();
            for(int i = dRollrCount; i > 0; i--){
                o.getSKUs().setCupHolders(o.getSKUs().getCupHolders() + 1);
                o.getSKUs().setPreppingBoard(o.getSKUs().getPreppingBoard() + 1);
                o.getSKUs().setDesertCooler(o.getSKUs().getDesertCooler() + 1);
            }
            for(int i = mRollrCount; i > 0; i--){
                o.getSKUs().setCupHolders(o.getSKUs().getCupHolders() + 1);
                o.getSKUs().setPreppingBoard(o.getSKUs().getPreppingBoard() + 1);
                o.getSKUs().setMossCooler(o.getSKUs().getMossCooler() + 1);
            }
            for(int i = pRollrCount; i > 0; i--){
                o.getSKUs().setCupHolders(o.getSKUs().getCupHolders() + 1);
                o.getSKUs().setPreppingBoard(o.getSKUs().getPreppingBoard() + 1);
                o.getSKUs().setPowderCooler(o.getSKUs().getPowderCooler() + 1);
            }
            for(int i = nbRollrCount; i > 0; i--){
                o.getSKUs().setCupHolders(o.getSKUs().getCupHolders() + 1);
                o.getSKUs().setPreppingBoard(o.getSKUs().getPreppingBoard() + 1);
                o.getSKUs().setNarwhalBlueCooler(o.getSKUs().getNarwhalBlueCooler() + 1);
            }
            for(int i = puRollrCount; i > 0; i--){
                o.getSKUs().setCupHolders(o.getSKUs().getCupHolders() + 1);
                o.getSKUs().setPreppingBoard(o.getSKUs().getPreppingBoard() + 1);
                o.getSKUs().setPinkUnicornCooler(o.getSKUs().getPinkUnicornCooler() + 1);
                o.getSKUs().setBikerHitch(o.getSKUs().getBikerHitch() + 1);
            }for(int i = dBikrCount; i > 0; i--){
                o.getSKUs().setCupHolders(o.getSKUs().getCupHolders() + 1);
                o.getSKUs().setPreppingBoard(o.getSKUs().getPreppingBoard() + 1);
                o.getSKUs().setDesertCooler(o.getSKUs().getDesertCooler() + 1);
                o.getSKUs().setBikerHitch(o.getSKUs().getBikerHitch() + 1);
            }
            for(int i = mBikrCount; i > 0; i--){
                o.getSKUs().setCupHolders(o.getSKUs().getCupHolders() + 1);
                o.getSKUs().setPreppingBoard(o.getSKUs().getPreppingBoard() + 1);
                o.getSKUs().setMossCooler(o.getSKUs().getMossCooler() + 1);
                o.getSKUs().setBikerHitch(o.getSKUs().getBikerHitch() + 1);
            }
            for(int i = pBikrCount; i > 0; i--){
                o.getSKUs().setCupHolders(o.getSKUs().getCupHolders() + 1);
                o.getSKUs().setPreppingBoard(o.getSKUs().getPreppingBoard() + 1);
                o.getSKUs().setPowderCooler(o.getSKUs().getPowderCooler() + 1);
                o.getSKUs().setBikerHitch(o.getSKUs().getBikerHitch() + 1);
            }
            for(int i = nbBikrCount; i > 0; i--){
                o.getSKUs().setCupHolders(o.getSKUs().getCupHolders() + 1);
                o.getSKUs().setPreppingBoard(o.getSKUs().getPreppingBoard() + 1);
                o.getSKUs().setNarwhalBlueCooler(o.getSKUs().getNarwhalBlueCooler() + 1);
                o.getSKUs().setBikerHitch(o.getSKUs().getBikerHitch() + 1);
            }
            for(int i = puBikrCount; i > 0; i--){
                o.getSKUs().setCupHolders(o.getSKUs().getCupHolders() + 1);
                o.getSKUs().setPreppingBoard(o.getSKUs().getPreppingBoard() + 1);
                o.getSKUs().setPinkUnicornCooler(o.getSKUs().getPinkUnicornCooler() + 1);
                o.getSKUs().setBikerHitch(o.getSKUs().getBikerHitch() + 1);
            }
        }
        for(int i = 0; i < orders.size(); i++) {
            assertEquals(orders.get(i).getSKUs().getBikerHitch(),ordersAfterCombo.get(i).getSKUs().getBikerHitch());
            assertEquals(orders.get(i).getSKUs().getDesertCooler(),ordersAfterCombo.get(i).getSKUs().getDesertCooler());
            assertEquals(orders.get(i).getSKUs().getCupHolders(),ordersAfterCombo.get(i).getSKUs().getCupHolders());
            assertEquals(orders.get(i).getSKUs().getMossCooler(),ordersAfterCombo.get(i).getSKUs().getMossCooler());
            assertEquals(orders.get(i).getSKUs().getNarwhalBlueCooler(),ordersAfterCombo.get(i).getSKUs().getNarwhalBlueCooler());
            assertEquals(orders.get(i).getSKUs().getPowderCooler(),ordersAfterCombo.get(i).getSKUs().getPowderCooler());
            assertEquals(orders.get(i).getSKUs().getPreppingBoard(),ordersAfterCombo.get(i).getSKUs().getPreppingBoard());
            assertEquals(orders.get(i).getSKUs().getPinkUnicornCooler(),ordersAfterCombo.get(i).getSKUs().getPinkUnicornCooler());
            assertEquals(orders.get(i).getSKUs().getdBikr(),ordersAfterCombo.get(i).getSKUs().getdBikr());
            assertEquals(orders.get(i).getSKUs().getmBikr(),ordersAfterCombo.get(i).getSKUs().getmBikr());
            assertEquals(orders.get(i).getSKUs().getpBikr(),ordersAfterCombo.get(i).getSKUs().getpBikr());
            assertEquals(orders.get(i).getSKUs().getNbBikr(),ordersAfterCombo.get(i).getSKUs().getNbBikr());
            assertEquals(orders.get(i).getSKUs().getPuBikr(),ordersAfterCombo.get(i).getSKUs().getPuBikr());
            assertEquals(orders.get(i).getSKUs().getdRollr(),ordersAfterCombo.get(i).getSKUs().getdRollr());
            assertEquals(orders.get(i).getSKUs().getmRollr(),ordersAfterCombo.get(i).getSKUs().getmRollr());
            assertEquals(orders.get(i).getSKUs().getNbRollr(),ordersAfterCombo.get(i).getSKUs().getNbRollr());
            assertEquals(orders.get(i).getSKUs().getpRollr(),ordersAfterCombo.get(i).getSKUs().getpRollr());
            assertEquals(orders.get(i).getSKUs().getPuRollr(),ordersAfterCombo.get(i).getSKUs().getPuRollr());
        }   
        
    }
    
    //test if the any of the number of the bikers that are shipping later are greater than
    //the previous biker kits that are going to ship
    @Test 
    public void testShippingOrder() {
        System.out.println("shipping order test");
        BackerKitOrderDataAnalyzer bkoda = new BackerKitOrderDataAnalyzer();
        String output = "replaceComboMakerTest.csv";
        String input = "Original111417.csv";
        bkoda.makeAllOrdersOneLine(input, output);
        ArrayList<Order> orders = bkoda.getOrders(output);
        ArrayList<Order> ordersAfterCombo = new ArrayList<>(orders);
        for(Order o: ordersAfterCombo)
            o.getSKUs().produceCombos();
        
        for(Order o : ordersAfterCombo){
            SKUs sku = o.getSKUs();
        
            
        }
    }
    
    //test that if the number of biker hitches is more than 0 then the number of all rollrs is 0
    @Test
    public void testAllRollrsZeroIfBikersGreaterThanOne(){
        System.out.println("shipping order test");
        BackerKitOrderDataAnalyzer bkoda = new BackerKitOrderDataAnalyzer();
        String output = "replaceComboMakerTest.csv";
        String input = "Original111417.csv";
        bkoda.makeAllOrdersOneLine(input, output);
        ArrayList<Order> orders = bkoda.getOrders(output);
        ArrayList<Order> ordersAfterCombo = new ArrayList<>(orders);
        for(Order o: ordersAfterCombo)
            o.getSKUs().produceCombos();
        
        
//        for(Order o: ordersAfterCombo){
//            if(o.getSKUs().getBikerHitch() > ( o.getSKUs().getdRollr()  + 
//                                               o.getSKUs().getpRollr()  +
//                                               o.getSKUs().getmRollr()  +
//                                               o.getSKUs().getNbRollr() +
//                                               o.getSKUs().getPuRollr() )
//               ) 
//            {
//                System.out.println(o.toString());
//                fail("There are still rollrs available to be paired with bikers.");
//            }
//        }
    }
    
    //test that all of the old coolers are 0
    
}
