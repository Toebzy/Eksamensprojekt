package dat.backend.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Beregner
{
    public List<Materiale> trykImpBrædt200Beregner(int bredde, int længde) // I arraylisten er først indsat materialer til bredden, derefter til længden
    {
        List<Materiale> materialeList = new ArrayList<>();
        List<Integer> listbredde = new ArrayList<>();
        List<Integer> listlængde = new ArrayList<>();
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int maxbredde = 2160;
        int maxlængde = 2160;
        int total;
        for (int i = 540; i >= 0; i -= 180)
        {
            if (i < 360)
            {
                i = 0;
            }
            for (int l = 540; l >= 0; l -= 180)
            {
                if (l < 360)
                {
                    l = 0;
                }
                for (int o = 540; o >= 0; o -= 180)
                {
                    if (o < 360)
                    {
                        o = 0;
                    }
                    for (int p = 540; p >= 0; p -= 180)
                    {
                        if (p < 360)
                        {
                            p = 0;
                        }
                        total = i + l + o + p;
                        if (total >= bredde && total < maxbredde)
                        {
                            maxbredde = total;
                            a = i;
                            b = l;
                            c = o;
                            d = p;

                        }
                    }
                }
            }
        }
        listbredde.add(a);
        listbredde.add(b);
        listbredde.add(c);
        listbredde.add(d);
        for (Integer i : listbredde)
        {
            if (i == 540)
            {
                materialeList.add(new Materiale(2, 540));
                materialeList.add(new Materiale(2, 540));
            } else if (i == 360)
            {
                materialeList.add(new Materiale(1, 360));
                materialeList.add(new Materiale(1, 360));
            }
        }
        for (int i = 540; i >= 0; i -= 180)
        {
            if (i < 360)
            {
                i = 0;
            }
            for (int l = 540; l >= 0; l -= 180)
            {
                if (l < 360)
                {
                    l = 0;
                }
                for (int o = 540; o >= 0; o -= 180)
                {
                    if (o < 360)
                    {
                        o = 0;
                    }
                    for (int p = 540; p >= 0; p -= 180)
                    {
                        if (p < 360)
                        {
                            p = 0;
                        }
                        total = i + l + o + p;
                        if (total >= længde && total < maxlængde)
                        {
                            maxlængde = total;
                            a = i;
                            b = l;
                            c = o;
                            d = p;
                        }
                    }
                }
            }
        }
        listlængde.add(a);
        listlængde.add(b);
        listlængde.add(c);
        listlængde.add(d);
        for (Integer i : listlængde)
        {
            if (i == 540)
            {
                materialeList.add(new Materiale(2, 540));
                materialeList.add(new Materiale(2, 540));
            } else if (i == 360)
            {
                materialeList.add(new Materiale(1, 360));
                materialeList.add(new Materiale(1, 360));
            }
        }
        return materialeList;
    }
    public List<Materiale> trykImpBrædt125Beregner(int bredde, int længde) // I arraylisten er først indsat materialer til bredden, derefter til længden
    {
        List<Materiale> materialeList = new ArrayList<>();
        List<Integer> listbredde = new ArrayList<>();
        List<Integer> listlængde = new ArrayList<>();
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int maxbredde = 2160;
        int maxlængde = 2160;
        int total;
        for (int i = 540; i >= 0; i -= 60)
        {
            if (i < 300)
            {
                i = 0;
            }
            for (int l = 540; l >= 0; l -= 60)
            {
                if (l < 300)
                {
                    l = 0;
                }
                for (int o = 540; o >= 0; o -= 60)
                {
                    if (o < 300)
                    {
                        o = 0;
                    }
                    for (int p = 540; p >= 0; p -= 60)
                    {
                        if (p < 300)
                        {
                            p = 0;
                        }
                        total = i + l + o + p;
                        if (total >= bredde && total < maxbredde)
                        {
                            maxbredde = total;
                            a = i;
                            b = l;
                            c = o;
                            d = p;
                        }
                    }
                }
            }
        }
        listbredde.add(a);
        listbredde.add(b);
        listbredde.add(c);
        listbredde.add(d);
        for (Integer i : listbredde)
        {
            if (i == 540)
            {
                materialeList.add(new Materiale(7, 540));
            } else if (i == 480)
            {
                materialeList.add(new Materiale(6, 480));
            } else if (i == 420)
            {
                materialeList.add(new Materiale(5, 420));
            } else if (i == 360)
            {
                materialeList.add(new Materiale(4, 360));
            } else if (i == 300)
            {
                materialeList.add(new Materiale(3, 300));
            }
        }
        for (int i = 540; i >= 0; i -= 60)
        {
            if (i < 300)
            {
                i = 0;
            }
            for (int l = 540; l >= 0; l -= 60)
            {
                if (l < 300)
                {
                    l = 0;
                }
                for (int o = 540; o >= 0; o -= 60)
                {
                    if (o < 300)
                    {
                        o = 0;
                    }
                    for (int p = 540; p >= 0; p -= 60)
                    {
                        if (p < 300)
                        {
                            p = 0;
                        }
                        total = i + l + o + p;
                        if (total >= længde && total < maxlængde)
                        {
                            maxlængde = total;
                            a = i;
                            b = l;
                            c = o;
                            d = p;
                        }
                    }
                }
            }

        }
        listlængde.add(a);
        listlængde.add(b);
        listlængde.add(c);
        listlængde.add(d);
        for (Integer i : listlængde)
        {
            if (i == 540)
            {
                materialeList.add(new Materiale(7, 540));
                materialeList.add(new Materiale(7, 540));
            } else if (i == 480)
            {
                materialeList.add(new Materiale(6, 480));
                materialeList.add(new Materiale(6, 480));
            } else if (i == 420)
            {
                materialeList.add(new Materiale(5, 420));
                materialeList.add(new Materiale(5, 420));
            } else if (i == 360)
            {
                materialeList.add(new Materiale(4, 360));
                materialeList.add(new Materiale(4, 360));
            } else if (i == 300)
            {
                materialeList.add(new Materiale(3, 300));
                materialeList.add(new Materiale(3, 300));
            }
        }
        return materialeList;
    }
    public List<Materiale> remBeregner(int bredde, int længde)
    {
        int over600 = 1;
        int antalrem;
        if(længde%55 == 0)
        {
            antalrem = længde/55;
        }
        else
        {
            antalrem = længde/55 +1;
        }
        List<Materiale> materialeList = new ArrayList<>();
        List<Integer> listbredde = new ArrayList<>();
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int nybredde = bredde;
        int maxbredde = 2160;
        int total;
        if(bredde > 600)
        {
            nybredde = bredde/2;
            over600 = 2;
        }
        for (int i = 600; i >= 0; i -= 60)
        {
            if (i < 360)
            {
                i = 0;
            }
            for (int l = 600; l >= 0; l -= 60)
            {
                if (l < 360)
                {
                    l = 0;
                }
                for (int o = 600; o >= 0; o -= 60)
                {
                    if (o < 360)
                    {
                        o = 0;
                    }
                    for (int p = 600; p >= 0; p -= 60)
                    {
                        if (p < 360)
                        {
                            p = 0;
                        }
                        total = i + l + o + p;
                        if (total >= nybredde && total < maxbredde)
                        {
                            maxbredde = total;
                            a = i;
                            b = l;
                            c = o;
                            d = p;
                            System.out.println(i + " " + l + " " + o + " " + p);
                        }
                    }
                }
            }
        }
        listbredde.add(a);
        listbredde.add(b);
        listbredde.add(c);
        listbredde.add(d);
        for (Integer i : listbredde)
        {
            if (i == 600)
            {
                for(int l = 0; l < antalrem * over600; l++)
                {
                    materialeList.add(new Materiale(20, 600));
                }
            }
            else if (i == 540)
            {
                for(int l = 0; l < antalrem * over600; l++)
                {
                    materialeList.add(new Materiale(19, 540));
                }
            }
            else if (i == 480)
            {
                for(int l = 0; l < antalrem * over600; l++)
                {
                    materialeList.add(new Materiale(18, 480));
                }
            }
            else if (i == 420)
            {
                for(int l = 0; l < antalrem * over600; l++)
                {
                    materialeList.add(new Materiale(17, 420));
                }
            }
            else if (i == 360)
            {
                for(int l = 0; l < antalrem * over600; l++)
                {
                    materialeList.add(new Materiale(16, 360));
                }
            }
        }
        return materialeList;
    }
}