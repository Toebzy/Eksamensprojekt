package dat.backend.model.model3d;
import dat.backend.model.entities.Calculator;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class FasciaGenerator {
    private final Calculator calculator;
    private final JavaCSG csg;
    private Geometry3D fascia;

    public FasciaGenerator(JavaCSG csg, Calculator calculator){
        this.csg=csg;
        this.calculator=calculator;
        this.fascia=createFascia();
    }
    private Geometry3D createFascia(){
        int width=calculator.getWidth();
        int length=calculator.getLength();

        Geometry3D overFascia = csg.translate3DZ(20).transform(fasciaCreator(width, length, 2.5, 12.5));
        Geometry3D underFascia = fasciaCreator(width, length, 2.5, 20);
        Geometry3D Fascia = csg.translate3DZ(calculator.getHeight()-32.5).transform(csg.union3D(overFascia,underFascia));
        return Fascia;
    }

    private Geometry3D fasciaCreator(int width, int length, double bwidth, double blength){
        Geometry3D FasciaW = csg.box3D(width,bwidth,blength, false);
        Geometry3D FasciaW2 = csg.translate3DY(length).transform(FasciaW);
        Geometry3D FasciaL = csg.box3D(bwidth,length,blength, false);
        Geometry3D FasciaL2 = csg.translate3DX(width).transform(FasciaL);
        Geometry3D fascia = csg.translate3DY(length/2).transform(csg.translate3DX(-width/2).transform(csg.union3D(FasciaL,FasciaL2)));
        Geometry3D allFascia = csg.union3D(FasciaW,FasciaW2,fascia);
        return allFascia;
    }
}
