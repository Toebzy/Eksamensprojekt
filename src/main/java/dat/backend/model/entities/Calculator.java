package dat.backend.model.entities;

import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.exceptions.DatabaseException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.*;

public class Calculator {
    private final ConnectionPool connectionPool;
    private final int length;
    private final int width;

    private Material pole;
    private int amountOfPoles;

    private Material rafter;
    private int amountOfRafters;

    private List<Material> roof;
    private final int[] roofAmount;

    private float totalPrice;

    public Calculator(int length, int width, ConnectionPool connectionPool) throws DatabaseException {
        this.connectionPool = connectionPool;
        this.length = length;
        this.width = width;
        this.pole = Material.newMaterial(22, connectionPool);
        this.rafter = Material.newMaterial(22, connectionPool);
        this.amountOfPoles = calculatePoleAmount(length, width);
        this.amountOfRafters = calculateRafterAmount(length);
        this.roofAmount = calculateRoofAmount(length, width);
        this.roof = roofMaterial(roofAmount, connectionPool);

        this.totalPrice = getTotalPrice();


    }

    public static int calculateRafterAmount(int length) {
        return (int) Math.ceil(length / 105);
    }

    public static int calculatePoleAmount(int length, int width) {
        return (int) ((Math.ceil(length / 310)) * 2 + (Math.ceil(width / 310) * 2));
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

    public static int[] calculateRoofAmount(int length, int width) {
        int tile360=360;
        int tile600=600;
        int[] optimalTileCounts = new int[2];
        int minWastage = Integer.MAX_VALUE;

        for (int count360 = 0; count360 <= length / tile360; count360++) {
            for (int count600 = 0; count600 <= length / tile600; count600++) {
                int lengthCovered = count360 * tile360 + count600 * tile600;
                int wastage = length - lengthCovered;

                if (wastage >= 0 && wastage < minWastage) {
                    minWastage = wastage;
                    optimalTileCounts[0] = count360;
                    optimalTileCounts[1] = count600;

                    if (wastage > 0) {
                        optimalTileCounts[0]++;
                    }
                }
            }
        }
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
        for (Map.Entry<Material, Integer> entry : partsList.entrySet()) {
            sb.append(entry.getKey().getDescription()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}