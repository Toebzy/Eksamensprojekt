package dat.backend.model.entities;

import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.MaterialeFacade;
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
    void trykImp200Test()
    {
        list.add(new Materiale(2, 540));
        assertEquals(list.get(0).getMvariant(), beregner.trykImpBrædt200Beregner(1000, 1000).get(0).getMvariant());
        assertEquals(8, beregner.trykImpBrædt200Beregner(600, 740).size());
    }
    @Test
    void trykImp125Test()
    {
        list.add(new Materiale(3, 300));
        assertEquals(list.get(0).getMvariant(), beregner.trykImpBrædt125Beregner(600, 740).get(0).getMvariant());
        assertEquals(6, beregner.trykImpBrædt125Beregner(600, 740).size());
    }

    @Test
    void spærTest()
    {
            list.add(new Materiale(20, 540));
            assertEquals(list.get(0).getMvariant(), beregner.spærBeregner(600, 740).get(0).getMvariant());
            assertEquals(14, beregner.spærBeregner(600, 740).size());
    }
    @Test
    void remTest()
    {
        assertEquals(16, beregner.remBeregner(360).get(0).getMvariant());
        assertEquals(4, beregner.remBeregner(740).size());
    }
    @Test
    void stolperTest()
    {
        assertEquals(22, beregner.stolperBeregner(780).get(0).getMvariant());
        assertEquals(6, beregner.stolperBeregner(780).size());
    }
    @Test
    void tagpladerTest()
    {
        assertEquals(30, beregner.tagpladerBeregner(600, 780).get(0).getMvariant());
        assertEquals(12, beregner.tagpladerBeregner(600, 780).size());
    }

}