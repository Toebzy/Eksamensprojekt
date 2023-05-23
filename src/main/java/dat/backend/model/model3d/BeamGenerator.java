package dat.backend.model.model3d;

import dat.backend.model.entities.Calculator;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class BeamGenerator {
    private final Calculator calculator;
    private final JavaCSG csg;
    private Geometry3D beams;

    public BeamGenerator(JavaCSG csg, Calculator calculator) {
        this.csg = csg;
        this.calculator = calculator;
        this.beams = createBeam();
    }

    private Geometry3D createBeam() {
        int width = calculator.getWidth();
        int length = calculator.getLength();
        int height = calculator.getHeight();
        Geometry3D Beam = csg.translate3DX(9).transform(csg.box3D(4.5, length, 19.5, false));
        Geometry3D Beam2 = csg.translate3DX(width-18).transform(Beam);
        Geometry3D Beams = csg.translate3DZ(height-59).transform(csg.translate3DY(length/2).transform(csg.translate3DX(-width/2).transform(csg.union3D(Beam,Beam2))));
        return Beams;
    }
    public Geometry3D getBeams() {
        return beams;
    }
}