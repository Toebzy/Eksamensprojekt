package dat.backend.model.model3d;
import dat.backend.model.entities.Calculator;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class RoofGenerator {
    private final Calculator calculator;
    private final JavaCSG csg;
    private Geometry3D roof;

    public RoofGenerator(JavaCSG csg, Calculator calculator) {
        this.csg = csg;
        this.calculator = calculator;
        this.roof = createRoof();
    }

    private Geometry3D createRoof() {
        int width = calculator.getWidth();
        int length = calculator.getLength();
        int height = calculator.getHeight();
        Geometry3D roof = csg.translate3DZ(height).transform(csg.box3D(width,length,2, false));
        Geometry3D roof2=csg.translate3DY((length/2)).transform(roof);

        return roof2;
    }

    public Geometry3D getRoof() {
        return roof;
    }
}
