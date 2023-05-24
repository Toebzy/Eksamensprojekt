package dat.backend.model.entities;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.MaterialFacade;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.*;

public class Calculator {
    private final ConnectionPool connectionPool;

    private final int length;//CARPORT DIMENSIONS
    private final int width;
    private final int height;

    private final Material pole;//POLES/STOLPE
    private final int amountOfPoles;

    private final Material rafter;//RAFTER/SPÆR
    private final int amountOfRafters;

    private final Material beam;//BEAM/REM
    private final int amountOfBeams;

    private final List<Material> fascia;//FASCIA/STERN
    private final int[] fasciaAmount;

    private final List<Material> roof;//ROOF/TAG
    private final int[] roofAmount;

    private final float totalPrice;
    private final Map<Material, Integer> partsList; //Map of material and amount of material

    public Calculator(int length, int width, int height, ConnectionPool connectionPool) throws DatabaseException, SQLException {
        this.connectionPool = connectionPool;

        this.length = length;
        this.width = width;
        this.height = height;
        this.pole = Material.newMaterial(22, connectionPool);
        this.amountOfPoles = calculatePoleAmount(length, width);
        this.rafter = Material.newMaterial(20, connectionPool);
        this.amountOfRafters = calculateRafterAmount(length,width);
        this.beam = Material.newMaterial(20, connectionPool);
        this.amountOfBeams = calculateBeamAmount(length);
        this.fasciaAmount = calculateFasciaAmount(length, width);
        this.fascia = fasciaMaterial(fasciaAmount);
        this.roofAmount = calculateRoofAmount(length, width);
        this.roof = roofMaterial(roofAmount);
        this.totalPrice = calculateTotalPrice();
        this.partsList = createPartsList();
    }

    private int calculateRafterAmount(int length, int width) {
        // Checking how many 600 cm long rafters are needed for the width of the carport
        int i = (int) Math.ceil(width / 600.0);

        //Checking how many rafters are needed for the length of the carport, by dividing it by the max amount of space between
        //rafters (60), and the width of a rafter (4.5). Then multiplying it with the amount needed for the width
        return (int) Math.ceil(length / 64.5) * i;
    }
    private int calculatePoleAmount(int length, int width) {
        double maxDistance = 310.0; //Max distance between poles
        double poleAmount = Math.ceil(length / maxDistance) * 2 + Math.ceil(width / maxDistance) * 2;
        return (int) poleAmount;
    }
    private int calculateBeamAmount(int length){
        double beamLength=beam.getLength();
        return (int) Math.ceil(length / beamLength) * 2;
    }
    private int[] calculateAmount(int id, int width, ConnectionPool connectionPool) throws DatabaseException {
        //Getting different lengths of materials from the database
        List<Material> material= MaterialFacade.getMaterialById(id, connectionPool);
        int length1 = material.get(0).getLength();
        int length2 = material.get(1).getLength();
        int[] optimalCounts = new int[2];
        int minWastage = Integer.MAX_VALUE;

        //Finding the optimal amount of each material of the different lengths, that is equal to or greater than
        //the width/length of the carport with as little wasted material as possible.
        //Using a nested loop to get every combination of the two materials, where 3 is the max amount of each material
        //Then checking how much material is wasted for each combination and returning the one with the least waste
        for (int count1 = 0; count1 <= 3; count1++) {
            for (int count2 = 0; count2 <= 3; count2++) {
                int widthCovered = count1 * length1 + count2 * length2;
                int wastage = widthCovered-width;

                if (wastage >= 0 && wastage <= minWastage && widthCovered>=width) {
                    minWastage = wastage;
                    optimalCounts[0] = count1;
                    optimalCounts[1] = count2;
                }
            }
        }
        return optimalCounts;
    }
    private int[] calculateFasciaAmount(int length, int width) throws DatabaseException{

        int[] optimalFasciaCounts = new int[2];

        int[] optimalFasciaLengthCounts = calculateAmount(1 ,length,  connectionPool);
        int[] optimalFasciaWidthCounts = calculateAmount(1 ,width,  connectionPool);

        //Amount of fascia with length 360
        optimalFasciaCounts[0]=(optimalFasciaWidthCounts[0]+optimalFasciaLengthCounts[0])*2;
        //Amount of fascia with length 540
        optimalFasciaCounts[1]=(optimalFasciaWidthCounts[1]+optimalFasciaLengthCounts[1])*2;
        return optimalFasciaCounts;
    }

    private List<Material> fasciaMaterial(int[] fasciaAmount) {

        List<Material> fasciaMaterials = new ArrayList<>();
        if (fasciaAmount[0] > 0 && fasciaAmount[1] > 0) {
            fasciaMaterials.add(Material.newMaterial(1, connectionPool));
            fasciaMaterials.add(Material.newMaterial(2, connectionPool));
            fasciaMaterials.add(Material.newMaterial(4, connectionPool));
            fasciaMaterials.add(Material.newMaterial(7, connectionPool));
        } else if (fasciaAmount[0] > 0) {
            fasciaMaterials.add(Material.newMaterial(1, connectionPool));
            fasciaMaterials.add(Material.newMaterial(4, connectionPool));
        } else if (fasciaAmount[1] > 0) {
            fasciaMaterials.add(Material.newMaterial(2, connectionPool));
            fasciaMaterials.add(Material.newMaterial(7, connectionPool));
        } else {
            throw new IllegalArgumentException("Invalid fascia combination.");
        }
        return fasciaMaterials;
    }

    private List<Material> roofMaterial(int[] roofAmount) {
        List<Material> roofMaterials = new ArrayList<>();
        if (roofAmount[0] > 0 && roofAmount[1] > 0) {
            roofMaterials.add(Material.newMaterial(29, connectionPool));
            roofMaterials.add(Material.newMaterial(30, connectionPool));
        } else if (roofAmount[0] > 0) {
            roofMaterials.add(Material.newMaterial(29, connectionPool));
        } else if (roofAmount[1] > 0) {
            roofMaterials.add(Material.newMaterial(30, connectionPool));
        } else {
            throw new IllegalArgumentException("Invalid roof tile combination.");
        }
        return roofMaterials;
    }

    private int[] calculateRoofAmount(int length, int width)throws DatabaseException {
        int[] optimalTileCounts = calculateAmount(8 ,length,  connectionPool);

        optimalTileCounts[0]= (int) (optimalTileCounts[0]*Math.ceil(width/100.0));
        optimalTileCounts[1]= (int) (optimalTileCounts[1]*Math.ceil(width/100.0));
        return optimalTileCounts;
    }

    private float calculateTotalPrice(){
        float totalPrice;
        float polePrice= (float) (amountOfPoles*((pole.getLength()/100)*pole.getPrice()));
        float rafterPrice= (float) (amountOfRafters*((rafter.getLength()/100)*rafter.getPrice()));
        float beamPrice = (float) (amountOfBeams*((beam.getLength()/100)*beam.getPrice()));
        float fasciaPrice = priceCalculator(fasciaAmount,fascia);
        float roofPrice = priceCalculator(roofAmount,roof);

        totalPrice=polePrice+rafterPrice+beamPrice+fasciaPrice+roofPrice;
        return totalPrice;
    }
    private float priceCalculator(int[] amount,List<Material> material){
        float price = 0;
        int i = 0;
        for(Material m: material) {
            //Since materials only get added if there is less than 0 of them, we want to skip any empty amounts
            if(amount[i]==0&&i<1){
                i++;
            }
            if(amount[i]==0&&i>0){
                i--;
            }
            price += amount[i]*(m.getPrice()*(m.getLength()/100.0));
            i++;

            //Avoiding ArrayIndexOutOfBoundsException in case of more than 2 materials, this is specifically for getting the price of fascia
            //where it is possible to get 4 materials, since over and under-fascia uses different materials which both come in the two same lengths.
            //Since there is the same amount of over and under-fascia, amount[0] would represent the amount of each fascia material with same length
            if(i==2){
                i=0;
            }
        }
        return price;
    }
    private Map<Material, Integer> createPartsList(){
        Map<Material, Integer> map = new LinkedHashMap<>();
        map.put(pole, amountOfPoles);
        map.put(rafter, amountOfRafters);
        map.put(beam, amountOfBeams);

        if (fasciaAmount[0] > 0 && fasciaAmount[1] > 0) {
            map.put(fascia.get(0),fasciaAmount[0]);
            map.put(fascia.get(1),fasciaAmount[1]);
            map.put(fascia.get(2),fasciaAmount[0]);
            map.put(fascia.get(3),fasciaAmount[1]);
        } else if (fasciaAmount[0] > 0) {
            map.put(fascia.get(0),fasciaAmount[0]);
            map.put(fascia.get(1),fasciaAmount[0]);
        } else if (fasciaAmount[1] > 0) {
            map.put(fascia.get(0), fasciaAmount[1]);
            map.put(fascia.get(1), fasciaAmount[1]);
        }

        if (roofAmount[0] > 0 && roofAmount[1] > 0) {
            map.put(roof.get(0),roofAmount[0]);
            map.put(roof.get(1),roofAmount[1]);
        } else if (roofAmount[0] > 0) {
            map.put(roof.get(0),roofAmount[0]);
        } else if (roofAmount[1] > 0) {
            map.put(roof.get(0), roofAmount[1]);
        }
        return map;
    }
    public Map<Material, Integer> getPartsList(){
        return partsList;
    }
    public int getWidth(){
        return width;
    }
    public int getLength(){
        return length;
    }
    public int getHeight(){
        return height;
    }
    public float getTotalPrice(){
        return totalPrice;
    }
    public int getAmountOfPoles() {
        return amountOfPoles;
    }
    public int getAmountOfRafters() {
        return amountOfRafters;
    }
    public int getAmountOfBeams() {
        return amountOfBeams;
    }
    public int[] getFasciaAmount() {
        return fasciaAmount;
    }

    public int[] getRoofAmount() {
        return roofAmount;
    }
}