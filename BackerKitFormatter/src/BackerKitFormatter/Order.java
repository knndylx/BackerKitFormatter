/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackerKitFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author akenn
 */
public class Order implements Comparable<Order> {
    /**
     * Data for the orders
     */
//==============================================================================
// Properties
//==============================================================================
    private String id;
    private String guid;
    private Platform platform;
    private String rewardId;
    private String name;
    private String email;
    private Address address;
    private float pledgeAmount;
    private PledgeStatus pledgeStatus;
    private String notes;
    private String publicNotes;
    private String pledgedAt;
    private Calendar pledgedAtDate;
    private String surveyAnsweredAt;
    private String fullAddress;
    private String fullCountry;
    private float rewardPrice;
    private String rewardDescription;
    private String orderPlaced;
    private String orderID;
    private OrderStatus orderStatus;
    private String orderCharged;
    private String chargeToken;
    private float fundsAddedInBackerKit;
    private float addOnShippingSubtotal;
    private float pledgeLevelShippingSubtotal;
    private float totalSpent;
    private SKUs skus;
    
//==============================================================================
// Comparator
//==============================================================================
    /**
     * Compares the Orders based on the time and date plegded at
     * @param o
     * @return ==0 if the same
     *         >0  if this is
     */
    @Override
    public int compareTo(Order o){
        return this.getPledgedAtDate().compareTo(o.getPledgedAtDate());
    }

//==============================================================================
// Constructors
//==============================================================================
    /**
     * complicated ass constructor for the order lol
     * takes all the data as a list of strings and turns it into an object
     * @param dataEntry 
     */
    public Order(ArrayList<String> dataEntry){
        id = dataEntry.get(0).replace("\"","");
        guid = dataEntry.get(1).replace("\"","");
        //platform is always kickstarter in this data set
        platform = Platform.kickstarter;
        rewardId = dataEntry.get(3).replace("\"","");
        name = dataEntry.get(4).replace("\"","");
        email = dataEntry.get(5).replace("\"","");
        address = new Address(dataEntry.get(6).replace("\"",""),
                              dataEntry.get(7).replace("\"",""),
                              dataEntry.get(8).replace("\"",""),
                              dataEntry.get(9).replace("\"",""),
                              dataEntry.get(10).replace("\"",""),
                              dataEntry.get(11).replace("\"",""),
                              dataEntry.get(12).replace("\"",""),
                              dataEntry.get(13).replace("\"","")
                             );
        pledgeAmount = Float.parseFloat(dataEntry.get(14).replace("\"",""));
        String pledgeSt = dataEntry.get(15).replace("\"","");
        if(pledgeSt.equals("collected")){
            pledgeStatus = PledgeStatus.collected;
        } else {
            pledgeStatus = PledgeStatus.failed;
        }
        notes = dataEntry.get(16).replace("\"","");
        publicNotes = dataEntry.get(17).replace("\"","");
        pledgedAt = dataEntry.get(18).replace("\"","");
        //parse the date from the pledgedAt String to a Calendar instance
        parseDateFromString();
        surveyAnsweredAt = dataEntry.get(19).replace("\"","");
        fullAddress = dataEntry.get(20).replace("\"","");
        fullCountry = dataEntry.get(21).replace("\"","");
        rewardPrice = Float.parseFloat(dataEntry.get(22).replace("\"",""));
        rewardDescription = dataEntry.get(23).replace("\"","");
        orderPlaced = dataEntry.get(24).replace("\"","");
        orderID = dataEntry.get(25).replace("\"","");
        String orderSt = dataEntry.get(26).replace("\"","");
        switch(orderSt){
            case "paid":
                orderStatus = OrderStatus.paid;
                break;
            case "locked_down":
                orderStatus = OrderStatus.locked_down;
                break;
            case "open":
                orderStatus = OrderStatus.open;
                break;
            case "errored":
                orderStatus = OrderStatus.errored;
                break;
            default:
                orderStatus = OrderStatus.survey_not_answered;
                break; 
        }
        orderCharged = dataEntry.get(27).replace("\"","");
        chargeToken = dataEntry.get(28).replace("\"","");
        if(!dataEntry.get(29).equals("\"\"")){
            fundsAddedInBackerKit = Float.parseFloat(dataEntry.get(29).replace("\"",""));
        } else {
            fundsAddedInBackerKit = 0;
        }
        if(!dataEntry.get(30).equals("\"\"")){
            addOnShippingSubtotal = Float.parseFloat(dataEntry.get(30).replace("\"",""));
        } else {
            addOnShippingSubtotal = 0;
        }
        if(!dataEntry.get(31).equals("\"\"")){
            pledgeLevelShippingSubtotal = Float.parseFloat(dataEntry.get(31).replace("\"",""));
        } else {
            pledgeLevelShippingSubtotal = 0;
        }
        if(!dataEntry.get(32).equals("\"\"")){
            totalSpent = Float.parseFloat(dataEntry.get(32).replace("\"",""));
        } else {
            totalSpent = 0;
        }

        ArrayList<String> skuList = new ArrayList<>();
        for(int i = 33; i < 52; i ++){
            skuList.add(dataEntry.get(i).replace("\"",""));
        }
        skus = new SKUs(skuList);
    }
    
    //copy constructor
    public Order(Order o){
        id = o.getId();
        guid = o.getGuid();
        //platform is always kickstarter in this data set
        platform = Platform.kickstarter;
        rewardId = o.getRewardId();
        name = o.getName();
        email = o.getEmail();
        address = new Address(o.getAddress().getName(),o.getAddress().getLine1(),
                              o.getAddress().getLine2(),o.getAddress().getCity(),
                              o.getAddress().getState(),o.getAddress().getCountry(),
                              o.getAddress().getPostalcode(),
                              o.getAddress().getPhone());
        pledgeAmount = o.getPledgeAmount();
        String pledgeSt = o.getPledgeStatus().name();
        if(pledgeSt.equals("collected")){
            pledgeStatus = PledgeStatus.collected;
        } else {
            pledgeStatus = PledgeStatus.failed;
        }
        notes = o.getNotes();
        publicNotes = o.getPublicNotes();
        pledgedAt = o.getPledgedAt();
        parseDateFromString();
        surveyAnsweredAt = o.getSurveyAnsweredAt();
        fullAddress = o.getFullAddress();
        fullCountry = o.fullCountry;
        rewardPrice = o.getRewardPrice();
        rewardDescription = o.getRewardDescription();
        orderPlaced = o.orderPlaced;
        orderID = o.getOrderID();
        orderStatus = o.getOrderStatus();
        orderCharged = o.getOrderCharged();
        chargeToken = o.getChargeToken();
        fundsAddedInBackerKit = o.getFundsAddedInBackerKit();
        addOnShippingSubtotal = o.getAddOnShippingSubtotal();
        pledgeLevelShippingSubtotal = o.getPledgeLevelShippingSubtotal();
        totalSpent = o.getTotalSpent();
        skus = new SKUs(o.getSKUs());
    }
    
//==============================================================================
// Methods
//==============================================================================
    /**
     * Get the Calendar object pledgedAtDate from the pledgedAt string
     */
    public final void parseDateFromString(){
        setPledgedAtDate(Calendar.getInstance());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", Locale.ENGLISH);
        try{
            getPledgedAtDate().setTime(sdf.parse(pledgedAt));// all done
        } catch(ParseException e) {
            System.err.println("Error parsing the date for the calendar object in order.");
        }
    }

    /**
     * format full address makes the address look how it does in the original 
     * fill with lines and commas in between where they are needed
     */
    public void formatFullAddress(){
        fullAddress = address.getName() + "\n"   + 
                      address.getLine1() + "\n";
        if(!address.getLine2().equals("")) fullAddress += address.getLine2() + "\n";
        fullAddress += address.getCity() + ", "   +
                       address.getState() + " " +  
                       address.getPostalcode() + "\n";
        if(address.getCountry().equals("US")) fullAddress += "United States of America" + "\n";
        else fullAddress += address.getCountry() + "\n";
        fullAddress += address.getPhone();
                      
    }
    
    /**
     * Returns the CSV String form of an order. 
     * For use with testing data to make sure that it matches the input.
     * @return 
     */
    @Override
    public String toString(){
        StringBuilder returnString = new StringBuilder();
        
        returnString.append("\"").append(getId()).append("\",");
        returnString.append("\"").append(getGuid()).append("\",");
        returnString.append("\"").append(getPlatform().name()).append("\",");
        returnString.append("\"").append(getRewardId()).append("\",");
        returnString.append("\"").append(getName()).append("\",");
        returnString.append("\"").append(getEmail()).append("\",");
        returnString.append(getAddress().toString()).append(",");
        returnString.append("\"").append(Float.toString(getPledgeAmount())).append("\",");
        returnString.append("\"").append(getPledgeStatus().name()).append("\",");
        returnString.append("\"").append(getNotes()).append("\",");
        returnString.append("\"").append(getPublicNotes()).append("\",");
        returnString.append("\"").append(getPledgedAt()).append("\",");
        returnString.append("\"").append(getSurveyAnsweredAt()).append("\",");
        returnString.append("\"").append(getFullAddress()).append("\",");
        returnString.append("\"").append(getFullCountry()).append("\",");
        returnString.append("\"").append(Float.toString(getRewardPrice())).append("\",");
        returnString.append("\"").append(getRewardDescription()).append("\",");
        returnString.append("\"").append(getOrderPlaced()).append("\",");
        returnString.append("\"").append(getOrderID()).append("\",");
        returnString.append("\"").append(getOrderStatus().name()).append("\",");
        returnString.append("\"").append(getOrderCharged()).append("\",");
        returnString.append("\"").append(getChargeToken()).append("\",");
        returnString.append("\"").append(Float.toString(getFundsAddedInBackerKit())).append("\",");
        //weird thing trying to get the numbers to match up exactly to the old spreadsheet, but really it doesn't matter that much
        if(getAddOnShippingSubtotal() == 0){
            returnString.append("\"").append(0).append("\",");
        } else {
            returnString.append("\"").append(Float.toString(getAddOnShippingSubtotal())).append("\",");
        }
        returnString.append("\"").append(Float.toString(getPledgeLevelShippingSubtotal())).append("\",");
        returnString.append("\"").append(Float.toString(getTotalSpent())).append("\",");
        returnString.append(getSkus().toString());
        
        return returnString.toString();
    }
    
//==============================================================================
// Accessors
//==============================================================================

    /**
     * for use changing the skus when producing the combos required for the spreadsheet
     * @return SKUs for the order
     */
    public SKUs getSKUs(){
        return this.getSkus();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid the guid to set
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @param platform the platform to set
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * @return the rewardId
     */
    public String getRewardId() {
        return rewardId;
    }

    /**
     * @param rewardId the rewardId to set
     */
    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the pledgeAmount
     */
    public float getPledgeAmount() {
        return pledgeAmount;
    }

    /**
     * @param pledgeAmount the pledgeAmount to set
     */
    public void setPledgeAmount(float pledgeAmount) {
        this.pledgeAmount = pledgeAmount;
    }

    /**
     * @param pledgeStatus the pledgeStatus to set
     */
    public void setPledgeStatus(PledgeStatus pledgeStatus) {
        this.pledgeStatus = pledgeStatus;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the publicNotes
     */
    public String getPublicNotes() {
        return publicNotes;
    }

    /**
     * @param publicNotes the publicNotes to set
     */
    public void setPublicNotes(String publicNotes) {
        this.publicNotes = publicNotes;
    }

    /**
     * @return the pledgedAt
     */
    public String getPledgedAt() {
        return pledgedAt;
    }

    /**
     * @param pledgedAt the pledgedAt to set
     */
    public void setPledgedAt(String pledgedAt) {
        this.pledgedAt = pledgedAt;
    }

    /**
     * @return the surveyAnsweredAt
     */
    public String getSurveyAnsweredAt() {
        return surveyAnsweredAt;
    }

    /**
     * @param surveyAnsweredAt the surveyAnsweredAt to set
     */
    public void setSurveyAnsweredAt(String surveyAnsweredAt) {
        this.surveyAnsweredAt = surveyAnsweredAt;
    }

    /**
     * @return the fullAddress
     */
    public String getFullAddress() {
        return fullAddress;
    }

    /**
     * @param fullAddress the fullAddress to set
     */
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    /**
     * @return the fullCountry
     */
    public String getFullCountry() {
        return fullCountry;
    }

    /**
     * @param fullCountry the fullCountry to set
     */
    public void setFullCountry(String fullCountry) {
        this.fullCountry = fullCountry;
    }

    /**
     * @return the rewardPrice
     */
    public float getRewardPrice() {
        return rewardPrice;
    }

    /**
     * @param rewardPrice the rewardPrice to set
     */
    public void setRewardPrice(float rewardPrice) {
        this.rewardPrice = rewardPrice;
    }

    /**
     * @return the rewardDescription
     */
    public String getRewardDescription() {
        return rewardDescription;
    }

    /**
     * @param rewardDescription the rewardDescription to set
     */
    public void setRewardDescription(String rewardDescription) {
        this.rewardDescription = rewardDescription;
    }

    /**
     * @return the orderPlaced
     */
    public String getOrderPlaced() {
        return orderPlaced;
    }

    /**
     * @param orderPlaced the orderPlaced to set
     */
    public void setOrderPlaced(String orderPlaced) {
        this.orderPlaced = orderPlaced;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the orderStatus
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return the orderCharged
     */
    public String getOrderCharged() {
        return orderCharged;
    }

    /**
     * @param orderCharged the orderCharged to set
     */
    public void setOrderCharged(String orderCharged) {
        this.orderCharged = orderCharged;
    }

    /**
     * @return the chargeToken
     */
    public String getChargeToken() {
        return chargeToken;
    }

    /**
     * @param chargeToken the chargeToken to set
     */
    public void setChargeToken(String chargeToken) {
        this.chargeToken = chargeToken;
    }

    /**
     * @return the fundsAddedInBackerKit
     */
    public float getFundsAddedInBackerKit() {
        return fundsAddedInBackerKit;
    }

    /**
     * @param fundsAddedInBackerKit the fundsAddedInBackerKit to set
     */
    public void setFundsAddedInBackerKit(float fundsAddedInBackerKit) {
        this.fundsAddedInBackerKit = fundsAddedInBackerKit;
    }

    /**
     * @return the addOnShippingSubtotal
     */
    public float getAddOnShippingSubtotal() {
        return addOnShippingSubtotal;
    }

    /**
     * @param addOnShippingSubtotal the addOnShippingSubtotal to set
     */
    public void setAddOnShippingSubtotal(float addOnShippingSubtotal) {
        this.addOnShippingSubtotal = addOnShippingSubtotal;
    }

    /**
     * @return the pledgeLevelShippingSubtotal
     */
    public float getPledgeLevelShippingSubtotal() {
        return pledgeLevelShippingSubtotal;
    }

    /**
     * @param pledgeLevelShippingSubtotal the pledgeLevelShippingSubtotal to set
     */
    public void setPledgeLevelShippingSubtotal(float pledgeLevelShippingSubtotal) {
        this.pledgeLevelShippingSubtotal = pledgeLevelShippingSubtotal;
    }

    /**
     * @return the totalSpent
     */
    public float getTotalSpent() {
        return totalSpent;
    }

    /**
     * @param totalSpent the totalSpent to set
     */
    public void setTotalSpent(float totalSpent) {
        this.totalSpent = totalSpent;
    }

    /**
     * @return the skus
     */
    public SKUs getSkus() {
        return skus;
    }

    /**
     * @param skus the skus to set
     */
    public void setSkus(SKUs skus) {
        this.skus = skus;
    }
    
    public PledgeStatus getPledgeStatus(){
        return pledgeStatus;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getName(){
        return name;
    }

    /**
     * @return the pledgedAtDate
     */
    public Calendar getPledgedAtDate() {
        return pledgedAtDate;
    }

    /**
     * @param pledgedAtDate the pledgedAtDate to set
     */
    public void setPledgedAtDate(Calendar pledgedAtDate) {
        this.pledgedAtDate = pledgedAtDate;
    }
}
