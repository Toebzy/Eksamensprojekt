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
        list.add(new Materiale(2, 540));
        list.add(new Materiale(2, 540));
        list.add(new Materiale(1, 360));
        list.add(new Materiale(1, 360));

    }
    @Test
    void testBrædder200()
    {
        assertEquals(list.get(0).getLength(), beregner.trykImpBrædt200Beregner(800).get(0).getLength());
        assertEquals(list.get(1).getLength(), beregner.trykImpBrædt200Beregner(800).get(1).getLength());
        assertEquals(list.get(2).getLength(), beregner.trykImpBrædt200Beregner(800).get(2).getLength());
        assertEquals(list.get(3).getLength(), beregner.trykImpBrædt200Beregner(800).get(3).getLength());
    }
}