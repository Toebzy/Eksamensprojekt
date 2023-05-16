package dat.backend.model.entities;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.persistence.ConnectionPool;

import java.util.*;

public class Beregner
{
    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://64.226.113.12:3306/carport?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";
    private static ConnectionPool connectionPool = new ConnectionPool(USER, PASSWORD, URL);
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
                materialeList.add(Materiale.newMateriale(2, connectionPool));
                materialeList.add(Materiale.newMateriale(2, connectionPool));
            } else if (i == 360)
            {
                materialeList.add(Materiale.newMateriale(1, connectionPool));
                materialeList.add(Materiale.newMateriale(1, connectionPool));
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
                materialeList.add(Materiale.newMateriale(2, connectionPool));
                materialeList.add(Materiale.newMateriale(2, connectionPool));
            } else if (i == 360)
            {
                materialeList.add(Materiale.newMateriale(1, connectionPool));
                materialeList.add(Materiale.newMateriale(1, connectionPool));
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
                materialeList.add(Materiale.newMateriale(7, connectionPool));

            } else if (i == 480)
            {
                materialeList.add(Materiale.newMateriale(6, connectionPool));
            } else if (i == 420)
            {
                materialeList.add(Materiale.newMateriale(5, connectionPool));
            } else if (i == 360)
            {
                materialeList.add(Materiale.newMateriale(4, connectionPool));
            } else if (i == 300)
            {
                materialeList.add(Materiale.newMateriale(3, connectionPool));
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
                materialeList.add(Materiale.newMateriale(7, connectionPool));
                materialeList.add(Materiale.newMateriale(7, connectionPool));
            } else if (i == 480)
            {
                materialeList.add(Materiale.newMateriale(6, connectionPool));
                materialeList.add(Materiale.newMateriale(6, connectionPool));
            } else if (i == 420)
            {
                materialeList.add(Materiale.newMateriale(5, connectionPool));
                materialeList.add(Materiale.newMateriale(5, connectionPool));
            } else if (i == 360)
            {
                materialeList.add(Materiale.newMateriale(4, connectionPool));
                materialeList.add(Materiale.newMateriale(4, connectionPool));
            } else if (i == 300)
            {
                materialeList.add(Materiale.newMateriale(3, connectionPool));
                materialeList.add(Materiale.newMateriale(3, connectionPool));
            }
        }
        return materialeList;
    }
    public List<Materiale> spærBeregner(int bredde, int længde)
    {
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
        int maxbredde = 2160;
        int total;
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
                        if (total >= bredde && total < maxbredde)
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
                for(int l = 0; l < antalrem; l++)
                {
                    materialeList.add(Materiale.newMateriale(20, connectionPool));
                }
            }
            else if (i == 540)
            {
                for(int l = 0; l < antalrem; l++)
                {
                    materialeList.add(Materiale.newMateriale(19, connectionPool));
                }
            }
            else if (i == 480)
            {
                for(int l = 0; l < antalrem; l++)
                {
                    materialeList.add(Materiale.newMateriale(18, connectionPool));
                }
            }
            else if (i == 420)
            {
                for(int l = 0; l < antalrem; l++)
                {
                    materialeList.add(Materiale.newMateriale(17, connectionPool));
                }
            }
            else if (i == 360)
            {
                for(int l = 0; l < antalrem; l++)
                {
                    materialeList.add(Materiale.newMateriale(16, connectionPool));
                }
            }
        }
        return materialeList;
    }
    public List<Materiale> remBeregner(int længde)
    {
        List<Materiale> materialeList = new ArrayList<>();
        List<Integer> listlængde = new ArrayList<>();
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int maxlængde = 2160;
        int total;
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
            if (i == 600)
            {
                materialeList.add(Materiale.newMateriale(20, connectionPool));
                materialeList.add(Materiale.newMateriale(20, connectionPool));
            }
            else if (i == 540)
            {
                materialeList.add(Materiale.newMateriale(19, connectionPool));
                materialeList.add(Materiale.newMateriale(19, connectionPool));
            }
            else if (i == 480)
            {
                materialeList.add(Materiale.newMateriale(18, connectionPool));
                materialeList.add(Materiale.newMateriale(18, connectionPool));
            }
            else if (i == 420)
            {
                materialeList.add(Materiale.newMateriale(17, connectionPool));
                materialeList.add(Materiale.newMateriale(17, connectionPool));
            }
            else if (i == 360)
            {
                materialeList.add(Materiale.newMateriale(16, connectionPool));
                materialeList.add(Materiale.newMateriale(16, connectionPool));
            }
        }
        return materialeList;
    }
    public List<Materiale> stolperBeregner(int længde)
    {
        List<Materiale> materialeList = new ArrayList<>();
        int antalStolper = 0;
        if(længde%310 == 0)
        {
            antalStolper = længde/310;
        }
        else
        {
            antalStolper = længde/310 + 1;
        }
        for(int i = 0; i < antalStolper *2; i++)
        {
            materialeList.add(Materiale.newMateriale(22, connectionPool));
        }
        return materialeList;
    }
    public List<Materiale> tagpladerBeregner(int bredde, int længde)
    {
        List<Materiale> materialeList = new ArrayList<>();
        List<Integer> listlængde = new ArrayList<>();
        int pladerbredde = 0;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int maxbredde = 2160;
        int maxlængde = 2160;
        int total;
        if(bredde%100 == 0)
        {
            pladerbredde = bredde/100;
        }
        else
        {
            pladerbredde = bredde/100 + 1;
        }
        for (int i = 600; i >= 0; i -= 240)
        {
            if (i < 360)
            {
                i = 0;
            }
            for (int l = 600; l >= 0; l -= 240)
            {
                if (l < 360)
                {
                    l = 0;
                }
                for (int o = 600; o >= 0; o -= 240)
                {
                    if (o < 360)
                    {
                        o = 0;
                    }
                    for (int p = 600; p >= 0; p -= 240)
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
            if (i == 600)
            {
                for(int l = 0; l < pladerbredde; l++)
                {
                    materialeList.add(Materiale.newMateriale(30, connectionPool));
                }
            } else if (i == 360)
            {
                for(int l = 0; l < pladerbredde; l++)
                {
                    materialeList.add(Materiale.newMateriale(29, connectionPool));
                }
            }
        }
        return materialeList;
    }

    public List<Materiale> carportBeregner(int bredde, int længde)
    {
        List<Materiale> carport = new ArrayList<>();
        carport.addAll(stolperBeregner(længde));
        carport.addAll(remBeregner(længde));
        carport.addAll(spærBeregner(bredde, længde));
        carport.addAll(tagpladerBeregner(bredde, længde));

        return carport;

    }
}