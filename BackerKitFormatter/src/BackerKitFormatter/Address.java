/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackerKitFormatter;
/**
 *
 * @author akenn
 */
public class Address {
//==============================================================================
// Properties
//==============================================================================
    private String name;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String country;
    private String postalcode;
    private String phone;
    
//==============================================================================
// Constructor
//==============================================================================
    public Address(String name, String line1, String line2, String city, 
                   String state, String country, String postalcode, 
                   String phone){
        this.name = name;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalcode = postalcode;
        this.phone = phone;
    }
//==============================================================================
// Methods
//==============================================================================
    /**
     * @return the string representation of the address 
     */
    @Override
    public String toString(){
        StringBuilder returnString = new StringBuilder();
        
        returnString.append("\"").append(getName()).append("\",");
        returnString.append("\"").append(getLine1()).append("\",");
        returnString.append("\"").append(getLine2()).append("\",");
        returnString.append("\"").append(getCity()).append("\",");
        returnString.append("\"").append(getState()).append("\",");
        returnString.append("\"").append(getCountry()).append("\",");
        returnString.append("\"").append(getPostalcode()).append("\",");
        returnString.append("\"").append(getPhone()).append("\"");
        
        return returnString.toString();
    }
//==============================================================================
// Accessors
//==============================================================================
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the line1
     */
    public String getLine1() {
        return line1;
    }

    /**
     * @param line1 the line1 to set
     */
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     * @return the line2
     */
    public String getLine2() {
        return line2;
    }

    /**
     * @param line2 the line2 to set
     */
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the postalcode
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * @param postalcode the postalcode to set
     */
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
