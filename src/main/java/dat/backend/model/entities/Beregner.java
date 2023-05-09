package dat.backend.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Beregner
{
    public List<Materiale> trykImpBrædt200Beregner(double bredde)
    {
        List<Materiale> materialeList = new ArrayList<>();
        int brædder540 = 0;
        double overskydende = 0;
        if(bredde >= 720)
        {
            brædder540 = (int) bredde/540;
            for(int i = 0; i < brædder540*2; i++)
            {
                Materiale materiale = new Materiale(2, 540);
                materialeList.add(materiale);
            }
            overskydende = bredde/540 - brædder540;
            if(overskydende > 360)
            {
                for(int i = 0; i < 4; i++)
                {
                    Materiale materiale = new Materiale(1, 360);
                    materialeList.add(materiale);
                }
            }
            if(overskydende < 360)
            {
                materialeList.add(new Materiale(1, 360));
                materialeList.add(new Materiale(1, 360));
            }
        }
        else if(bredde < 720 && bredde > 360)
        {
            for(int i = 0; i < 4; i++)
            {
                Materiale materiale = new Materiale(1, 360);
                materialeList.add(materiale);
            }
        }
        else if(bredde <= 360)
        {
            materialeList.add(new Materiale(1, 360));
            materialeList.add(new Materiale(1, 360));
        }
        return materialeList;
    }
}
