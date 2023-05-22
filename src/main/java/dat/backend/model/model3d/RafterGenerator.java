package dat.backend.model.model3d;
import dat.backend.model.entities.Calculator;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class RafterGenerator {
    private final Calculator calculator;
    private final JavaCSG csg;
    private Geometry3D rafters;

    public RafterGenerator(JavaCSG csg, Calculator calculator){
        this.csg=csg;
        this.calculator=calculator;
        this.rafters=createRafters();
    }
    private Geometry3D createRafters(){
        int length = calculator.getLength();
        int width = calculator.getWidth();
        int height = calculator.getHeight();

        int rafterAmount= (int) Math.ceil(length / 64.5);
        int spaceBetween=length/rafterAmount;
        int j = spaceBetween/2;
        Geometry3D allRafters = csg.box3D(0,0,0, false);

        for(int i=0;i<rafterAmount;i++){
            Geometry3D rafter = csg.box3D(width, 4.5, 19.5, false);
            Geometry3D newRafter = csg.translate3DY(j).transform(rafter);
            j+=spaceBetween;
            allRafters=csg.union3D(allRafters,newRafter);
        }
        Geometry3D Rafters = csg.translate3DZ(600-40).transform(allRafters);
        return Rafters;
    }

    public Geometry3D getRafters() {
        return rafters;
    }
}
