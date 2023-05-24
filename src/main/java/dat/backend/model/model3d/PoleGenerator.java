package dat.backend.model.model3d;
import dat.backend.model.entities.Calculator;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class PoleGenerator {
    private final Calculator calculator;
    private final JavaCSG csg;
    private final Geometry3D poles;

    public PoleGenerator(JavaCSG csg, Calculator calculator){
        this.csg=csg;
        this.calculator=calculator;
        this.poles=createPoles();
    }
    private Geometry3D createPoles(){
        int width=calculator.getWidth();
        int length=calculator.getLength();

        Geometry3D Pole1=csg.box3D(9.7, 9.7, calculator.getHeight(), false);
        Geometry3D Pole2=csg.translate3DX(width).transform(Pole1);
        Geometry3D Poles=csg.union3D(Pole1,Pole2);
        Geometry3D Poles2=csg.translate3DY(length).transform(Poles);
        Geometry3D allPoles=csg.union3D(Poles, Poles2);

        if((int)Math.ceil(length/310.0)>1){
            int a = (int) (Math.ceil(length/310.0));
            int b = (int) (length/(Math.ceil((length/310.0))));
            for(int i=0;i<a;i++){
                Geometry3D extraPole=csg.translate3DY(b).transform(Pole1);
                Geometry3D extraPole2=csg.translate3DX(width).transform(extraPole);
                allPoles=csg.union3D(allPoles,extraPole,extraPole2);
                b+=length/(Math.ceil((length/310.0)));
            }
        }
        if((int)Math.ceil(width/310.0)>1){
            int a = (int) (Math.ceil(width/310.0));
            int b = (int) (width/(Math.ceil((width/310.0))));
            for(int i=0;i<a;i++){
                Geometry3D extraPole=csg.translate3DX(b).transform(Pole1);
                Geometry3D extraPole2=csg.translate3DY(length).transform(extraPole);
                allPoles=csg.union3D(allPoles,extraPole,extraPole2);
                b+=width/(Math.ceil((width/310.0)));
            }
        }
        return csg.translate3DX(-width/2.0).transform(allPoles);
    }
    public Geometry3D getPoles() {
        return poles;
    }
}
