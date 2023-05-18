package dat.backend.model.entities;

import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.MaterialFacade;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.*;

public class Calculator {
    private final ConnectionPool connectionPool;
    private final int length;
    private final int width;

    private Material pole; //stolpe
    private int amountOfPoles;

    private Material rafter; //spær
    private int amountOfRafters;

    private List<Material> fascia; //stern

    private final int[] fasciaAmount;

    private List<Material> roof;
    private final int[] roofAmount;

    private float totalPrice;

    public Calculator(int length, int width, ConnectionPool connectionPool) throws DatabaseException {
        this.connectionPool = connectionPool;
        this.length = length;
        this.width = width;
        this.pole = Material.newMaterial(22, connectionPool);
        this.rafter = Material.newMaterial(20, connectionPool);
        this.amountOfPoles = calculatePoleAmount(length, width);
        this.amountOfRafters = calculateRafterAmount(length,width);

        this.fasciaAmount=calculateFasciaAmount(length, width, connectionPool);
        this.fascia=fasciaMaterial(fasciaAmount, connectionPool);


        this.roofAmount = calculateRoofAmount(length, width, connectionPool);
        this.roof = roofMaterial(roofAmount, connectionPool);

        this.totalPrice = getTotalPrice();
    }

    public static int calculateRafterAmount(int length, int width) {
        return (int) ((int) Math.ceil(length / 105)*(Math.ceil(width/600)));
    }

    public static int calculatePoleAmount(int length, int width) {
        int maxDistance = 310;
        return (int) ((Math.ceil(length / maxDistance)) * 2 + (Math.ceil(width / maxDistance) * 2));
    }

    public static int[] calculateAmount(int id, int width, ConnectionPool connectionPool) throws DatabaseException {
        List<Material> material= MaterialFacade.getMaterialById(id, connectionPool);
        int length1 = (int) material.get(0).getLength();
        int length2 = (int) material.get(1).getLength();

        int[] optimalCounts = new int[2];
        int minWastage = Integer.MAX_VALUE;

        for (int count1 = 0; count1 <= width / length1; count1++) {
            for (int count2 = 0; count2 <= width / length2; count2++) {
                int widthCovered = count1 * length1 + count2 * length2;
                int wastage = width - widthCovered;

                if (wastage >= 0 && wastage < minWastage) {
                    minWastage = wastage;
                    optimalCounts[0] = count1;
                    optimalCounts[1] = count2;

                    if (wastage > 0) {
                        optimalCounts[0]++;
                    }
                }
            }
        }
        return optimalCounts;
    }


    public static int[] calculateFasciaAmount(int length, int width, ConnectionPool connectionPool) throws DatabaseException{

        int[] optimalFasciaLengthCounts = calculateAmount(1 ,length,  connectionPool);
        int[] optimalFasciaWidthCounts = calculateAmount(1 ,width,  connectionPool);
        int[] optimalFasciaCounts = new int[2];

        optimalFasciaCounts[0]=(optimalFasciaWidthCounts[0]+optimalFasciaLengthCounts[0])*2;
        optimalFasciaCounts[1]=(optimalFasciaWidthCounts[1]+optimalFasciaLengthCounts[1])*2;
        return optimalFasciaCounts;
    }

    public static List<Material> fasciaMaterial(int[] fasciaAmount, ConnectionPool connectionPool) {

        List<Material> fasciaMaterials = new ArrayList<Material>();
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

    public static List<Material> roofMaterial(int[] roofAmount, ConnectionPool connectionPool) {
        List<Material> RoofMaterials = new ArrayList<Material>();
        if (roofAmount[0] > 0 && roofAmount[1] > 0) {
            RoofMaterials.add(Material.newMaterial(29, connectionPool));
            RoofMaterials.add(Material.newMaterial(30, connectionPool));
        } else if (roofAmount[0] > 0) {
            RoofMaterials.add(Material.newMaterial(29, connectionPool));
        } else if (roofAmount[1] > 0) {
            RoofMaterials.add(Material.newMaterial(30, connectionPool));
        } else {
            throw new IllegalArgumentException("Invalid roof tile combination.");
        }
        return RoofMaterials;
    }

    public static int[] calculateRoofAmount(int length, int width, ConnectionPool connectionPool)throws DatabaseException {
        int[] optimalTileCounts = calculateAmount(8 ,length,  connectionPool);

        optimalTileCounts[0]= (int) (optimalTileCounts[0]*Math.ceil(width/100));
        optimalTileCounts[1]= (int) (optimalTileCounts[1]*Math.ceil(width/100));
        return optimalTileCounts;
    }

    public float getTotalPrice(){
        return (float) ((pole.getPrice()*amountOfPoles)+(rafter.getPrice()*amountOfRafters));
    }

    public Map<Material, Integer> getPartsList(){
        Map<Material, Integer> map = new LinkedHashMap<>();
        map.put(pole, amountOfPoles);
        map.put(rafter, amountOfRafters);

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
    @Override
    public String toString() {
        Map<Material, Integer> partsList = getPartsList();
        StringBuilder sb = new StringBuilder();
        sb.append("Stykliste for carport med længden: "+length+" og bredden: "+width+"\n");
        for (Map.Entry<Material, Integer> entry : partsList.entrySet()) {
            sb.append(entry.getKey().getDescription()).append("| Længde: ").append(entry.getKey().getLength()).append("| Mængde: ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}