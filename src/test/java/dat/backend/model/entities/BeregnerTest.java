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
        list.add(new Materiale(2, 540));
        list.add(new Materiale(2, 540));
        list.add(new Materiale(2, 540));
        list.add(new Materiale(2, 540));
        list.add(new Materiale(2, 540));
        list.add(new Materiale(2, 540));
        list.add(new Materiale(2, 540));
        assertEquals(list.get(0).getMvariant(), beregner.trykImpBrædt200Beregner(1000, 1000).get(0).getMvariant());
        assertEquals(list.get(1).getMvariant(), beregner.trykImpBrædt200Beregner(1000, 1000).get(1).getMvariant());
        assertEquals(list.get(2).getMvariant(), beregner.trykImpBrædt200Beregner(1000, 1000).get(2).getMvariant());
        assertEquals(list.get(3).getMvariant(), beregner.trykImpBrædt200Beregner(1000, 1000).get(3).getMvariant());
        assertEquals(list.get(4).getMvariant(), beregner.trykImpBrædt200Beregner(1000, 1000).get(4).getMvariant());
        assertEquals(list.get(5).getMvariant(), beregner.trykImpBrædt200Beregner(1000, 1000).get(5).getMvariant());
        assertEquals(list.get(6).getMvariant(), beregner.trykImpBrædt200Beregner(1000, 1000).get(6).getMvariant());
        assertEquals(list.get(7).getMvariant(), beregner.trykImpBrædt200Beregner(1000, 1000).get(7).getMvariant());

    }
    @Test
    void trykImp125Test()
    {
        list.add(new Materiale(7, 540));
        list.add(new Materiale(6, 480));
        list.add(new Materiale(7, 540));
        list.add(new Materiale(7, 540));
        list.add(new Materiale(6, 480));
        list.add(new Materiale(6, 480));
        assertEquals(list.get(0).getMvariant(), beregner.trykImpBrædt125Beregner(1000, 1000).get(0).getMvariant());
        assertEquals(list.get(1).getMvariant(), beregner.trykImpBrædt125Beregner(1000, 1000).get(1).getMvariant());
        assertEquals(list.get(2).getMvariant(), beregner.trykImpBrædt125Beregner(1000, 1000).get(2).getMvariant());
        assertEquals(list.get(3).getMvariant(), beregner.trykImpBrædt125Beregner(1000, 1000).get(3).getMvariant());
        assertEquals(list.get(4).getMvariant(), beregner.trykImpBrædt125Beregner(1000, 1000).get(4).getMvariant());
        assertEquals(list.get(5).getMvariant(), beregner.trykImpBrædt125Beregner(1000, 1000).get(5).getMvariant());
    }

    @Test
    void spærTest()
    {
        for (int i = 0; i < 38; i++)
        {
            list.add(new Materiale(19, 540));
        }
        for (int l = 0; l < 38; l++)
        {
            assertEquals(list.get(l).getMvariant(), beregner.spærBeregner(1000, 1000).get(l).getMvariant());
        }
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
        assertEquals(30, beregner.tagpladerBeregner(600, 780).get(1).getMvariant());
        assertEquals(30, beregner.tagpladerBeregner(600, 780).get(2).getMvariant());
        assertEquals(30, beregner.tagpladerBeregner(600, 780).get(3).getMvariant());
        assertEquals(30, beregner.tagpladerBeregner(600, 780).get(4).getMvariant());
        assertEquals(30, beregner.tagpladerBeregner(600, 780).get(5).getMvariant());
        assertEquals(29, beregner.tagpladerBeregner(600, 780).get(6).getMvariant());
        assertEquals(29, beregner.tagpladerBeregner(600, 780).get(7).getMvariant());
        assertEquals(29, beregner.tagpladerBeregner(600, 780).get(8).getMvariant());
        assertEquals(29, beregner.tagpladerBeregner(600, 780).get(9).getMvariant());
        assertEquals(29, beregner.tagpladerBeregner(600, 780).get(10).getMvariant());
        assertEquals(29, beregner.tagpladerBeregner(600, 780).get(11).getMvariant());
        assertEquals(12, beregner.tagpladerBeregner(600, 780).size());
    }

}