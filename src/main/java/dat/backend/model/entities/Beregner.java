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
            overskydende = bredde - (brædder540*540);
            if(overskydende > 360)
            {
                for(int i = 0; i < 4; i++)
                {
                    Materiale materiale = new Materiale(1, 360);
                    materialeList.add(materiale);
                }
            }
            if(overskydende < 360 && overskydende > 0)
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
    public List<Materiale> trykImpBrædt100Beregner(double bredde, double længde)  //sætter først brædder ind til bredde derefter længde
    {
        List<Materiale> materialeList = new ArrayList<>();
        int brædder540 = 0;
        double overskydende = 0;
        if(bredde >= 720)
        {
            brædder540 = (int) bredde/540;
            for(int i = 0; i < brædder540; i++)
            {
                Materiale materiale = new Materiale(27, 540);
                materialeList.add(materiale);
            }
            overskydende = bredde - brædder540*540;
            if(overskydende > 360)
            {
                for(int i = 0; i < 2; i++)
                {
                    Materiale materiale = new Materiale(26, 360);
                    materialeList.add(materiale);
                }
            }
            if(overskydende < 360)
            {
                materialeList.add(new Materiale(26, 360));
            }
        }
        else if(bredde < 720 && bredde > 360)
        {
            for(int i = 0; i < 2; i++)
            {
                Materiale materiale = new Materiale(26, 360);
                materialeList.add(materiale);
            }
        }
        else if(bredde <= 360)
        {
            materialeList.add(new Materiale(26, 360));
        }

        if(længde >= 720)
        {
            brædder540 = (int) længde/540;
            for(int i = 0; i < brædder540*2; i++)
            {
                Materiale materiale = new Materiale(27, 540);
                materialeList.add(materiale);
            }
            overskydende = længde - brædder540*540;
            if(overskydende > 360)
            {
                for(int i = 0; i < 4; i++)
                {
                    Materiale materiale = new Materiale(26, 360);
                    materialeList.add(materiale);
                }
            }
            if(overskydende < 360)
            {
                materialeList.add(new Materiale(26, 360));
                materialeList.add(new Materiale(26, 360));
            }
        }
        else if(længde < 720 && bredde > 360)
        {
            for(int i = 0; i < 4; i++)
            {
                Materiale materiale = new Materiale(26, 360);
                materialeList.add(materiale);
            }
        }
        else if(længde <= 360)
        {
            materialeList.add(new Materiale(26, 360));
            materialeList.add(new Materiale(26, 360));
        }
        return materialeList;
    }

    public List<Materiale> plastmoBeregner(double bredde, double længde)
    {
        List<Materiale> materialeList = new ArrayList<>();
        int antalPladerbredde = 1;
        int antalplader600;
        double overskydende;
        if(bredde%100 == 0)
        {
            antalPladerbredde = (int)bredde/100;
        }
        else if(bredde%100 != 0)
        {
            antalPladerbredde = (int)(bredde/100) + 1;
        }
        if(længde >= 600)
        {
            antalplader600 = (int)længde/600;
            for(int i = 0; i < antalplader600*antalPladerbredde; i++)
            {
                materialeList.add(new Materiale(29, 600));
            }
            overskydende = længde - antalplader600*600;
            if(overskydende > 360)
            {
                for(int i = 0; i < 2*antalPladerbredde; i++)
                {
                    materialeList.add(new Materiale(28, 360));
                }
            }
            else if(overskydende < 360 && overskydende > 0)
            {
                for(int i = 0; i < 1*antalPladerbredde; i++)
                {
                    materialeList.add(new Materiale(28, 360));
                }
            }
        }
        else if(længde < 600 && længde > 360)
        {
            for(int i = 0; i < 2*antalPladerbredde; i++)
            {
                materialeList.add(new Materiale(28, 360));
            }
        }
        else if(længde < 360 && længde > 0)
        {
            for(int i = 0; i < 1*antalPladerbredde; i++)
            {
                materialeList.add(new Materiale(28, 360));
            }
        }
        return materialeList;
    }
    public List<Materiale> trykImpBrædt125Beregner(double bredde)
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
            overskydende = bredde - (brædder540*540);
            if(overskydende > 360)
            {
                for(int i = 0; i < 2; i++)
                {
                    Materiale materiale = new Materiale(1, 360);
                    materialeList.add(materiale);
                }
            }
            if(overskydende < 360 && overskydende > 0)
            {
                materialeList.add(new Materiale(1, 360));
                materialeList.add(new Materiale(1, 360));
            }
        }
        else if(bredde < 720 && bredde > 360)
        {
            for(int i = 0; i < 2; i++)
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
    public List<Materiale> spærtræ195Beregner(double bredde)
    {
        List<Materiale> materialeList = new ArrayList<>();
        int spærtræ600 = 0;
        double overskydende = 0;
        if(bredde >= 960)
        {
            spærtræ600 = (int) bredde/600;
            for(int i = 0; i < spærtræ600*2; i++)
            {
                Materiale materiale = new Materiale(19, 600);
                materialeList.add(materiale);
            }
            overskydende = bredde - (spærtræ600*600);
            if(overskydende > 480)
            {
                for(int i = 0; i < 2; i++)
                {
                    Materiale materiale = new Materiale(17, 480);
                    materialeList.add(materiale);
                }
            }
            if(overskydende < 480 && overskydende > 0)
            {
                materialeList.add(new Materiale(17, 480));
                materialeList.add(new Materiale(17, 480));
            }
        }
        else if(bredde < 960 && bredde > 480)
        {
            for(int i = 0; i < 2; i++)
            {
                Materiale materiale = new Materiale(17, 480);
                materialeList.add(materiale);
            }
        }
        else if(bredde <= 480)
        {
            materialeList.add(new Materiale(17, 480));
            materialeList.add(new Materiale(17, 480));
        }
        return materialeList;
    }
    public List<Materiale> trykImpStolpe300Beregner(double bredde)
    {
        List<Materiale> materialeList = new ArrayList<>();
        int stolpe420 = 0;
        double overskydende = 0;
        if(bredde >= 420)
        {
            stolpe420 = (int) bredde/420;
            for(int i = 0; i < stolpe420*2; i++)
            {
                Materiale materiale = new Materiale(23, 420);
                materialeList.add(materiale);
            }
            overskydende = bredde - (stolpe420*420);
            if(overskydende > 300)
            {
                for(int i = 0; i < 2; i++)
                {
                    Materiale materiale = new Materiale(21, 300);
                    materialeList.add(materiale);
                }
            }
            if(overskydende < 300 && overskydende > 0)
            {
                materialeList.add(new Materiale(21, 300));
                materialeList.add(new Materiale(21, 300));
            }
        }
        else if(bredde < 420 && bredde > 300)
        {
            for(int i = 0; i < 2; i++)
            {
                Materiale materiale = new Materiale(21, 300);
                materialeList.add(materiale);
            }
        }
        else if(bredde <= 300)
        {
            materialeList.add(new Materiale(21, 300));
            materialeList.add(new Materiale(21, 300));
        }
        return materialeList;
    }
}