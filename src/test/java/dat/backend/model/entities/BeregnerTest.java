package dat.backend.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeregnerTest
{
    Beregner beregner;
    List<Materiale> list = new ArrayList<>();

    @BeforeEach
    void setUp()
    {
        beregner = new Beregner();
        for(Materiale m: list)
        {
            list.remove(m);
        }
    }
    @Test
    void testBrædder200()
    {
        list.add(new Materiale(2, 540));
        list.add(new Materiale(2, 540));
        list.add(new Materiale(1, 360));
        list.add(new Materiale(1, 360));
        assertEquals(list.get(0).getLength(), beregner.trykImpBrædt200Beregner(800).get(0).getLength());
        assertEquals(list.get(1).getLength(), beregner.trykImpBrædt200Beregner(800).get(1).getLength());
        assertEquals(list.get(2).getLength(), beregner.trykImpBrædt200Beregner(800).get(2).getLength());
        assertEquals(list.get(3).getLength(), beregner.trykImpBrædt200Beregner(800).get(3).getLength());
    }

    @Test
    void testBrædder100()
    {
        list.add(new Materiale(27, 540));
        list.add(new Materiale(26, 360));
        list.add(new Materiale(27, 540));
        list.add(new Materiale(27, 540));
        list.add(new Materiale(26, 360));
        list.add(new Materiale(26, 360));
        assertEquals(list.get(0).getMvariant(), beregner.trykImpBrædt100Beregner(800, 800).get(0).getMvariant());
        assertEquals(list.get(1).getMvariant(), beregner.trykImpBrædt100Beregner(800, 800).get(1).getMvariant());
        assertEquals(list.get(2).getMvariant(), beregner.trykImpBrædt100Beregner(800, 800).get(2).getMvariant());
        assertEquals(list.get(3).getMvariant(), beregner.trykImpBrædt100Beregner(800, 800).get(3).getMvariant());
        assertEquals(list.get(4).getMvariant(), beregner.trykImpBrædt100Beregner(800, 800).get(4).getMvariant());
        assertEquals(list.get(5).getMvariant(), beregner.trykImpBrædt100Beregner(800, 800).get(5).getMvariant());
    }
}