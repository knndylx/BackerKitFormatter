/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackerKitFormatter;

import java.util.ArrayList;

/**
 *
 * @author akenn
 */
public class SKUs {
    
//==============================================================================
// Builder
//==============================================================================
    /**
     * Used to build the current SKUs for the coolers with no accessories
     */
    public static class SKUsBuilder {
        private int dBikr = 0;
        private int mBikr = 0;
        private int nbBikr = 0;
        private int pBikr = 0;
        private int puBikr = 0;
        private int dRollr = 0;
        private int mRollr = 0;
        private int nbRollr = 0;
        private int pRollr = 0;
        private int puRollr = 0;
        
        public SKUs build(){
            return new SKUs(this);
        }
        
        public SKUsBuilder dBikr(int dBikr) {
            this.dBikr = dBikr;
            return this;
        }
        
        public SKUsBuilder mBikr(int mBikr) {
            this.mBikr = mBikr;
            return this;
        }
        
        public SKUsBuilder nbBikr(int nbBikr) {
            this.nbBikr = nbBikr;
            return this;
        }
        
        public SKUsBuilder pBikr(int pBikr) {
            this.pBikr = pBikr;
            return this;
        }
        
        public SKUsBuilder puBikr(int puBikr) {
            this.puBikr = puBikr;
            return this;
        }
        
        public SKUsBuilder dRollr(int dRollr) {
            this.dRollr = dRollr;
            return this;
        }
        
        public SKUsBuilder mRollr(int mRollr) {
            this.mRollr = mRollr;
            return this;
        }
        
        public SKUsBuilder nbRollr(int nbRollr) {
            this.nbRollr = nbRollr;
            return this;
        }
        
        public SKUsBuilder pRollr(int pRollr) {
            this.pRollr = pRollr;
            return this;
        }
        
        public SKUsBuilder puRollr(int pRollr) {
            this.pRollr = pRollr;
            return this;
        }
    }
    
//==============================================================================
// Properties
//==============================================================================
    
    /*
    SKUs from the original file.  In the new file there won't be any coolers
    from these SKUs because they will be combined into bundles with the
    produce combos method
    */
    private int bikerHitch, bottleOpener,chargingLantern,desertCooler, 
                cupHolders, mossCooler, narwhalBlueCooler, nightLight, 
                powderCooler, preppingBoard, pinkUnicornCooler,
                teeLarge, teeMedium, teeSmall, teeXLarge, teeXXLarge, 
                stashBag, twistyLight, tieDowns;
    
    /*
    SKUs for the cooler combos that will be changed with the produce combos
    method
    */
    private int dBikr = 0;
    private int mBikr = 0;
    private int nbBikr = 0;
    private int pBikr = 0;
    private int puBikr = 0;
    private int dRollr = 0;
    private int mRollr = 0;
    private int nbRollr = 0;
    private int pRollr = 0;
    private int puRollr = 0;
    
//==============================================================================
// Constructors
//==============================================================================
    
    /**
     * Constructor from String array; in our case these come from the input data
     * from the original CSV file
     * @param dataEntry String array that contains all original SKUs
     */
    public SKUs(ArrayList<String> dataEntry){
        ArrayList<Integer> intDataEntry = new ArrayList<Integer>();
        for(String s : dataEntry){
            intDataEntry.add(Integer.parseInt(s));
        }
        bikerHitch = intDataEntry.get(0);
        bottleOpener = intDataEntry.get(1);
        chargingLantern = intDataEntry.get(2);
        desertCooler = intDataEntry.get(3);
        cupHolders = intDataEntry.get(4);
        mossCooler = intDataEntry.get(5);
        narwhalBlueCooler = intDataEntry.get(6);
        nightLight = intDataEntry.get(7);
        powderCooler = intDataEntry.get(8);
        preppingBoard = intDataEntry.get(9);
        pinkUnicornCooler = intDataEntry.get(10);
        teeLarge = intDataEntry.get(11);
        teeMedium = intDataEntry.get(12);
        teeSmall = intDataEntry.get(13);
        teeXLarge = intDataEntry.get(14);
        teeXXLarge = intDataEntry.get(15);
        stashBag = intDataEntry.get(16);
        tieDowns = intDataEntry.get(17);
        twistyLight = intDataEntry.get(18);
    }
    
    /**
     * Copy constructor
     * @param SKUtoCopy 
     */
    public SKUs(SKUs SKUtoCopy){
        bikerHitch = SKUtoCopy.bikerHitch;
        bottleOpener = SKUtoCopy.bottleOpener;
        chargingLantern = SKUtoCopy.chargingLantern;
        desertCooler = SKUtoCopy.desertCooler;
        cupHolders = SKUtoCopy.cupHolders;
        mossCooler = SKUtoCopy.mossCooler;
        narwhalBlueCooler = SKUtoCopy.narwhalBlueCooler;
        nightLight = SKUtoCopy.nightLight;
        powderCooler = SKUtoCopy.powderCooler;
        preppingBoard = SKUtoCopy.preppingBoard;
        pinkUnicornCooler = SKUtoCopy.pinkUnicornCooler;
        teeLarge = SKUtoCopy.teeLarge;
        teeMedium = SKUtoCopy.teeMedium;
        teeSmall = SKUtoCopy.teeSmall;
        teeXLarge = SKUtoCopy.teeXLarge;
        teeXXLarge = SKUtoCopy.teeXXLarge;
        stashBag = SKUtoCopy.stashBag;
        tieDowns = SKUtoCopy.tieDowns;
        twistyLight = SKUtoCopy.twistyLight;
        dRollr = SKUtoCopy.dRollr;
        dBikr = SKUtoCopy.dBikr;
        pRollr = SKUtoCopy.pRollr;
        pBikr = SKUtoCopy.pBikr;
        mRollr = SKUtoCopy.mRollr;
        mBikr = SKUtoCopy.mBikr;
        nbRollr = SKUtoCopy.nbRollr;
        nbBikr = SKUtoCopy.nbBikr;
        puRollr = SKUtoCopy.puRollr;
        puBikr = SKUtoCopy.puBikr;
    }
    
     /**
      * Accessory constructor
     * Constructs a SKU with all accessories from an order, used after the
     * cooler SKUs have been converted to bundle SKUs
     * @param o 
     */
    public SKUs(Order o){
        SKUs s = o.getSKUs();
        bikerHitch = s.getBikerHitch();
        bottleOpener = s.getBottleOpener();
        chargingLantern = s.getChargingLantern();
        desertCooler = 0;
        cupHolders = s.getCupHolders();
        mossCooler = 0;
        narwhalBlueCooler = 0;
        nightLight = s.getNightLight();
        powderCooler = 0;
        preppingBoard = s.getPreppingBoard();
        pinkUnicornCooler = 0;
        teeLarge = s.getTeeLarge();
        teeMedium = s.getTeeMedium();
        teeSmall = s.getTeeSmall();
        teeXLarge = s.getTeeXLarge();
        teeXXLarge = s.getTeeXXLarge();
        stashBag = s.getStashBag();
        tieDowns = s.getTieDowns();
        twistyLight = s.getTwistyLight();
    }
    
    /**
     * Constructor from the SKUsBuilder, used before accessories are added
     * @param builder 
     */
    private SKUs(SKUsBuilder builder){
        bikerHitch = 0;
        bottleOpener = 0;
        chargingLantern = 0;
        desertCooler = 0;
        cupHolders = 0;
        mossCooler = 0;
        narwhalBlueCooler = 0;
        nightLight = 0;
        powderCooler = 0;
        preppingBoard = 0;
        pinkUnicornCooler = 0;
        teeLarge = 0;
        teeMedium = 0;
        teeSmall = 0;
        teeXLarge = 0;
        teeXXLarge = 0;
        stashBag = 0;
        tieDowns = 0;
        twistyLight = 0;
        this.dRollr = builder.dRollr;
        this.pRollr = builder.pRollr;
        this.mRollr = builder.mRollr;
        this.nbRollr = builder.nbRollr;
        this.puRollr = builder.puRollr;
        this.dBikr = builder.dBikr;
        this.pBikr = builder.pBikr;
        this.mBikr = builder.mBikr;
        this.nbBikr = builder.nbBikr;
        this.puBikr = builder.puBikr;
    }
    
//==============================================================================
// Methods
//==============================================================================
    
    /**
     * Add a SKU to this
     * @param sku 
     */
    public void add(SKUs sku){
        bikerHitch += sku.getBikerHitch();
        bottleOpener += sku.getBottleOpener();
        chargingLantern += sku.getChargingLantern();
        desertCooler += sku.getDesertCooler();
        cupHolders += sku.getCupHolders();
        mossCooler += sku.getMossCooler();
        narwhalBlueCooler += sku.getNarwhalBlueCooler();
        nightLight += sku.getNightLight();
        powderCooler += sku.getPowderCooler();
        preppingBoard += sku.getPreppingBoard();
        pinkUnicornCooler += sku.getPinkUnicornCooler();
        teeLarge += sku.getTeeLarge();
        teeMedium += sku.getTeeMedium();
        teeSmall += sku.getTeeSmall();
        teeXLarge += sku.getTeeXLarge();
        teeXXLarge += sku.getTeeXXLarge();
        stashBag += sku.getStashBag();
        tieDowns += sku.getTieDowns();
        twistyLight += sku.getTwistyLight();
        dRollr += sku.getdRollr();
        pRollr += sku.getpRollr();
        this.mRollr += sku.getmRollr();
        this.nbRollr += sku.getNbRollr();
        this.puRollr += sku.getPuRollr();
        this.dBikr += sku.getdBikr();
        this.pBikr += sku.getpBikr();
        this.mBikr += sku.getmBikr();
        this.nbBikr += sku.getNbBikr();
        this.puBikr += sku.getPuBikr();
    }
    
    /**
     * Sets all properties to zero
     */
    public void zero(){
        bikerHitch = 0;
        bottleOpener = 0;
        chargingLantern = 0;
        desertCooler = 0;
        cupHolders = 0;
        mossCooler = 0;
        narwhalBlueCooler = 0;
        nightLight = 0;
        powderCooler = 0;
        preppingBoard = 0;
        pinkUnicornCooler = 0;
        teeLarge = 0;
        teeMedium = 0;
        teeSmall = 0;
        teeXLarge = 0;
        teeXXLarge = 0;
        stashBag = 0;
        tieDowns = 0;
        twistyLight = 0;
        this.dRollr = 0;
        this.pRollr = 0;
        this.mRollr = 0;
        this.nbRollr = 0;
        this.puRollr = 0;
        this.dBikr = 0;
        this.pBikr = 0;
        this.mBikr = 0;
        this.nbBikr = 0;
        this.puBikr = 0;
    }
    
    /**
     * This function takes the combinations of coolers and accessories and 
     * bundles them together to go from the skus on the backerkit to the
     * in house and distribution skus when matching up bikers it prioritizes 
     * the coolers that are shipping first
     * CURRENT ORDER OF COOLER SHIPPING
     * 1. desert
     * 2. powder
     * 3. moss
     * 4. narwhal blue
     * 5. pink unicorn
     */
    public void produceCombos(){
        //first thing to do is make the rollr combos
        int desertCoolerCount = getDesertCooler();
        int mossCoolerCount = getMossCooler();
        int powderCoolerCount = getPowderCooler();
        int narwhalBlueCoolerCount = getNarwhalBlueCooler();
        int pinkUnicornCoolerCount = getPinkUnicornCooler();
        
        for(int i = desertCoolerCount; i > 0; i--){
            if(getCupHolders() > 0 && getPreppingBoard() > 0)  {  
                setDesertCooler(desertCooler - 1);
                setCupHolders(cupHolders - 1);
                setPreppingBoard(preppingBoard - 1);
                setdRollr(dRollr + 1);
            } else {
                System.err.println("NOT ENOUGH ACCESSORIES. ERROR!");
            }
        }
        for(int i = mossCoolerCount; i > 0; i--){
            if(getCupHolders() > 0 && getPreppingBoard() > 0)  {  
                setMossCooler(mossCooler - 1);
                setCupHolders(cupHolders - 1);
                setPreppingBoard(preppingBoard - 1);
                setmRollr(mRollr + 1);
            } else {
                System.err.println("NOT ENOUGH ACCESSORIES. ERROR!");
            }
        }
        for(int i = powderCoolerCount; i > 0; i--){
            if(getCupHolders() > 0 && getPreppingBoard() > 0)  {  
                setPowderCooler(powderCooler - 1);
                setCupHolders(cupHolders - 1);
                setPreppingBoard(preppingBoard - 1);
                setpRollr(pRollr + 1);
            } else {
                System.err.println("NOT ENOUGH ACCESSORIES. ERROR!");
            }
        }
        for(int i = narwhalBlueCoolerCount; i > 0; i--){
            if(getCupHolders() > 0 && getPreppingBoard() > 0)  {  
                setNarwhalBlueCooler(narwhalBlueCooler - 1);
                setCupHolders(cupHolders - 1);
                setPreppingBoard(preppingBoard - 1);
                setNbRollr(nbRollr + 1);
            } else {
                System.err.println("NOT ENOUGH ACCESSORIES. ERROR!");
            }
        }
        for(int i = pinkUnicornCoolerCount; i > 0; i--){
            if(getCupHolders() > 0 && getPreppingBoard() > 0)  {  
                setPinkUnicornCooler(pinkUnicornCooler - 1);
                setCupHolders(cupHolders - 1);
                setPreppingBoard(preppingBoard - 1);
                setPuRollr(puRollr + 1);
            } else {
                System.err.println("NOT ENOUGH ACCESSORIES. ERROR!");
            }
        }
        //3 cases, exact same bike hitch, less, or more
        int totalCoolers = getdRollr()  + 
                           getmRollr()  + 
                           getpRollr()  + 
                           getNbRollr() + 
                           getPuRollr();
        //assign a biker to each cooler
        if(totalCoolers <= getBikerHitch()){
            int desertRollrCount = getdRollr();
            for(int i = desertRollrCount; i > 0; i--){
                if(getBikerHitch() > 0)  {  
                    setdRollr(getdRollr() - 1);
                    bikerHitch -= 1;
                    setdBikr(dBikr + 1);
                } else {
                    System.err.println("NOT ENOUGH ACCESSORIES. ERROR!");
                }
            }
            int mossRollrCount = getmRollr();
            for(int i = mossRollrCount; i > 0; i--){
                if(getBikerHitch() > 0)  {  
                    setmRollr(getmRollr() - 1);
                    bikerHitch -= 1;
                    setmBikr(mBikr + 1);
                } else {
                    System.err.println("NOT ENOUGH ACCESSORIES. ERROR!");
                }
            }
            int powderRollrCount = getpRollr();
            for(int i = powderRollrCount; i > 0; i--){
                if(getBikerHitch() > 0)  {  
                    setpRollr(getpRollr() - 1);
                    bikerHitch -= 1;
                    setpBikr(pBikr + 1);
                } else {
                    System.err.println("NOT ENOUGH ACCESSORIES. ERROR!");
                }
            }
            int narwhalBlueRollrCount = getNbRollr();
            for(int i = narwhalBlueRollrCount; i > 0; i--){
                if(getBikerHitch() > 0)  {  
                    setNbRollr(getNbRollr() - 1);
                    bikerHitch -= 1;
                    setNbBikr(nbBikr + 1);
                } else {
                    System.err.println("NOT ENOUGH ACCESSORIES. ERROR!");
                }
            }
            int pinkUnicornRollrCount = getPuRollr();
            for(int i = pinkUnicornRollrCount; i > 0; i--){
                if(getBikerHitch() > 0)  {  
                    setPuRollr(getPuRollr() - 1);
                    bikerHitch -= 1;
                    setPuBikr(puBikr + 1);
                } else {
                    System.err.println("NOT ENOUGH ACCESSORIES. ERROR!");
                }
            }
        }
        //assign a biker to the coolers that are shipping first
        else if(totalCoolers > getBikerHitch()){
            int desertRollrCount = dRollr;
            int mossRollrCount = mRollr;
            int powderRollrCount = pRollr;
            int narwhalBlueRollrCount = nbRollr;
            int pinkUnicornRollrCount = puRollr;
            while(desertRollrCount > 0 && bikerHitch > 0){
                dRollr -= 1;
                bikerHitch -= 1;
                dBikr += 1;
            }
            while(powderRollrCount > 0 && bikerHitch > 0){
                pRollr -= 1;
                bikerHitch -= 1;
                pBikr += 1;
            }
            while(mossRollrCount > 0 && bikerHitch > 0){
                mRollr -= 1;
                bikerHitch -= 1;
                mBikr += 1;
            }
            while(narwhalBlueRollrCount > 0 && bikerHitch > 0){
                nbRollr -= 1;
                bikerHitch -= 1;
                nbBikr += 1;
            }
            while(pinkUnicornRollrCount > 0 && bikerHitch > 0){
                puRollr -= 1;
                bikerHitch -= 1;
                puBikr += 1;
            }
        }
    }
    
    /**
     * 
     * @return String representation of SKU 
     */
    @Override
    public String toString(){
        StringBuilder returnString = new StringBuilder();
        
        returnString.append("\"").append(
                                         Integer.toString(bikerHitch)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(bottleOpener)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(chargingLantern)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(desertCooler)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(cupHolders)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(mossCooler)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(narwhalBlueCooler)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(nightLight)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(powderCooler)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(preppingBoard)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(pinkUnicornCooler)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(teeLarge)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(teeMedium)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(teeSmall)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(teeXLarge)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(teeXXLarge)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(stashBag)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(tieDowns)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(twistyLight)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(dRollr)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(mRollr)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(pRollr)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(nbRollr)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(puRollr)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(dBikr)
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(getmBikr())
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(getpBikr())
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(getNbBikr())
                                        ).append("\",");
        returnString.append("\"").append(
                                         Integer.toString(getPuBikr())
                                        ).append("\"");
        
        return returnString.toString();
    }

//==============================================================================
// Accessors
//==============================================================================    
    /**
     * @return the bikerHitch
     */
    public int getBikerHitch() {
        return bikerHitch;
    }

    /**
     * @return the bottleOpener
     */
    public int getBottleOpener() {
        return bottleOpener;
    }

    /**
     * @return the chargingLantern
     */
    public int getChargingLantern() {
        return chargingLantern;
    }

    /**
     * @return the desertCooler
     */
    public int getDesertCooler() {
        return desertCooler;
    }

    /**
     * @return the cupHolders
     */
    public int getCupHolders() {
        return cupHolders;
    }

    /**
     * @return the mossCooler
     */
    public int getMossCooler() {
        return mossCooler;
    }

    /**
     * @return the narwhalBlueCooler
     */
    public int getNarwhalBlueCooler() {
        return narwhalBlueCooler;
    }

    /**
     * @return the nightLight
     */
    public int getNightLight() {
        return nightLight;
    }

    /**
     * @return the powderCooler
     */
    public int getPowderCooler() {
        return powderCooler;
    }

    /**
     * @return the preppingBoard
     */
    public int getPreppingBoard() {
        return preppingBoard;
    }

    /**
     * @return the pinkUnicornCooler
     */
    public int getPinkUnicornCooler() {
        return pinkUnicornCooler;
    }

    /**
     * @return the teeLarge
     */
    public int getTeeLarge() {
        return teeLarge;
    }

    /**
     * @return the teeMedium
     */
    public int getTeeMedium() {
        return teeMedium;
    }

    /**
     * @return the teeSmall
     */
    public int getTeeSmall() {
        return teeSmall;
    }

    /**
     * @return the teeXLarge
     */
    public int getTeeXLarge() {
        return teeXLarge;
    }

    /**
     * @return the teeXXLarge
     */
    public int getTeeXXLarge() {
        return teeXXLarge;
    }

    /**
     * @return the stashBag
     */
    public int getStashBag() {
        return stashBag;
    }

    /**
     * @return the twistyLight
     */
    public int getTwistyLight() {
        return twistyLight;
    }

    /**
     * @return the tieDowns
     */
    public int getTieDowns() {
        return tieDowns;
    }

    /**
     * @return the dBikr
     */
    public int getdBikr() {
        return dBikr;
    }

    /**
     * @return the mBikr
     */
    public int getmBikr() {
        return mBikr;
    }

    /**
     * @return the nbBikr
     */
    public int getNbBikr() {
        return nbBikr;
    }

    /**
     * @return the pBikr
     */
    public int getpBikr() {
        return pBikr;
    }

    /**
     * @return the puBikr
     */
    public int getPuBikr() {
        return puBikr;
    }

    /**
     * @return the dRollr
     */
    public int getdRollr() {
        return dRollr;
    }

    /**
     * @return the mRollr
     */
    public int getmRollr() {
        return mRollr;
    }

    /**
     * @return the nbRollr
     */
    public int getNbRollr() {
        return nbRollr;
    }

    /**
     * @return the pRollr
     */
    public int getpRollr() {
        return pRollr;
    }

    /**
     * @return the puRollr
     */
    public int getPuRollr() {
        return puRollr;
    }

    /**
     * @param bikerHitch the bikerHitch to set
     */
    public void setBikerHitch(int bikerHitch) {
        this.bikerHitch = bikerHitch;
    }

    /**
     * @param bottleOpener the bottleOpener to set
     */
    public void setBottleOpener(int bottleOpener) {
        this.bottleOpener = bottleOpener;
    }

    /**
     * @param chargingLantern the chargingLantern to set
     */
    public void setChargingLantern(int chargingLantern) {
        this.chargingLantern = chargingLantern;
    }

    /**
     * @param desertCooler the desertCooler to set
     */
    public void setDesertCooler(int desertCooler) {
        this.desertCooler = desertCooler;
    }

    /**
     * @param cupHolders the cupHolders to set
     */
    public void setCupHolders(int cupHolders) {
        this.cupHolders = cupHolders;
    }

    /**
     * @param mossCooler the mossCooler to set
     */
    public void setMossCooler(int mossCooler) {
        this.mossCooler = mossCooler;
    }

    /**
     * @param narwhalBlueCooler the narwhalBlueCooler to set
     */
    public void setNarwhalBlueCooler(int narwhalBlueCooler) {
        this.narwhalBlueCooler = narwhalBlueCooler;
    }

    /**
     * @param nightLight the nightLight to set
     */
    public void setNightLight(int nightLight) {
        this.nightLight = nightLight;
    }

    /**
     * @param powderCooler the powderCooler to set
     */
    public void setPowderCooler(int powderCooler) {
        this.powderCooler = powderCooler;
    }

    /**
     * @param preppingBoard the preppingBoard to set
     */
    public void setPreppingBoard(int preppingBoard) {
        this.preppingBoard = preppingBoard;
    }

    /**
     * @param pinkUnicornCooler the pinkUnicornCooler to set
     */
    public void setPinkUnicornCooler(int pinkUnicornCooler) {
        this.pinkUnicornCooler = pinkUnicornCooler;
    }

    /**
     * @param teeLarge the teeLarge to set
     */
    public void setTeeLarge(int teeLarge) {
        this.teeLarge = teeLarge;
    }

    /**
     * @param teeMedium the teeMedium to set
     */
    public void setTeeMedium(int teeMedium) {
        this.teeMedium = teeMedium;
    }

    /**
     * @param teeSmall the teeSmall to set
     */
    public void setTeeSmall(int teeSmall) {
        this.teeSmall = teeSmall;
    }

    /**
     * @param teeXLarge the teeXLarge to set
     */
    public void setTeeXLarge(int teeXLarge) {
        this.teeXLarge = teeXLarge;
    }

    /**
     * @param teeXXLarge the teeXXLarge to set
     */
    public void setTeeXXLarge(int teeXXLarge) {
        this.teeXXLarge = teeXXLarge;
    }

    /**
     * @param stashBag the stashBag to set
     */
    public void setStashBag(int stashBag) {
        this.stashBag = stashBag;
    }

    /**
     * @param twistyLight the twistyLight to set
     */
    public void setTwistyLight(int twistyLight) {
        this.twistyLight = twistyLight;
    }

    /**
     * @param tieDowns the tieDowns to set
     */
    public void setTieDowns(int tieDowns) {
        this.tieDowns = tieDowns;
    }

    /**
     * @param dBikr the dBikr to set
     */
    public void setdBikr(int dBikr) {
        this.dBikr = dBikr;
    }

    /**
     * @param mBikr the mBikr to set
     */
    public void setmBikr(int mBikr) {
        this.mBikr = mBikr;
    }

    /**
     * @param nbBikr the nbBikr to set
     */
    public void setNbBikr(int nbBikr) {
        this.nbBikr = nbBikr;
    }

    /**
     * @param pBikr the pBikr to set
     */
    public void setpBikr(int pBikr) {
        this.pBikr = pBikr;
    }

    /**
     * @param puBikr the puBikr to set
     */
    public void setPuBikr(int puBikr) {
        this.puBikr = puBikr;
    }

    /**
     * @param dRollr the dRollr to set
     */
    public void setdRollr(int dRollr) {
        this.dRollr = dRollr;
    }

    /**
     * @param mRollr the mRollr to set
     */
    public void setmRollr(int mRollr) {
        this.mRollr = mRollr;
    }

    /**
     * @param nbRollr the nbRollr to set
     */
    public void setNbRollr(int nbRollr) {
        this.nbRollr = nbRollr;
    }

    /**
     * @param pRollr the pRollr to set
     */
    public void setpRollr(int pRollr) {
        this.pRollr = pRollr;
    }

    /**
     * @param puRollr the puRollr to set
     */
    public void setPuRollr(int puRollr) {
        this.puRollr = puRollr;
    }
}               
