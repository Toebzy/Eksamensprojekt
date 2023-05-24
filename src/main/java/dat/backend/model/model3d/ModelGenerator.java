package dat.backend.model.model3d;
import dat.backend.model.entities.Calculator;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class ModelGenerator {
    private final Calculator calculator;

    public ModelGenerator(Calculator calculator){
        this.calculator = calculator;
    }

    public void create3DModel() {
        JavaCSG csg = JavaCSGFactory.createDefault();
        RafterGenerator Rafter = new RafterGenerator(csg,calculator);
        FasciaGenerator Fascia = new FasciaGenerator(csg,calculator);
        PoleGenerator Poles = new PoleGenerator(csg,calculator);
        BeamGenerator Beams = new BeamGenerator(csg, calculator);
        RoofGenerator Roof = new RoofGenerator(csg, calculator);

        Geometry3D Model = csg.union3D(Rafter.getRafters(), Fascia.getFascia(), Poles.getPoles(),Beams.getBeams(), Roof.getRoof());
        csg.view(Model);
    }
}
