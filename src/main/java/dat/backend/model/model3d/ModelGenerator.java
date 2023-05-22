package dat.backend.model.model3d;
import dat.backend.model.entities.Calculator;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class ModelGenerator {
    private final Calculator calculator;
    private static JavaCSG csg;

    public ModelGenerator(Calculator calculator) {
        this.calculator = calculator;
        csg=JavaCSGFactory.createDefault();
    }


    private void create3DModel(int width, int length, int height){
        JavaCSG csg = JavaCSGFactory.createDefault();
        RafterGenerator Rafter = new RafterGenerator(csg,calculator);
        FasciaGenerator Fascia = new FasciaGenerator(csg,calculator);
        PoleGenerator Poles = new PoleGenerator(csg,calculator);

    }


}
