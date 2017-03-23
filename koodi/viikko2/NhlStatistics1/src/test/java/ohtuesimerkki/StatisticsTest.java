/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juslesan
 */
public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Staatistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void nullPelaaja() {
        assertEquals(null, stats.search("Crosby"));
    }

    @Test
    public void pelaajaLoytyy() {
        assertEquals(true, stats.search("Semenko").toString().contains("Semenko"));
    }

    @Test
    public void joukkueLista() {
        assertEquals(3, stats.team("EDM").size());
        assertEquals(0, stats.team("MIN").size());
    }

    @Test
    public void parasPelaaja() {
        assertEquals("Gretzky", stats.topScorers(0).get(0).getName());

    }
    @Test
    public void huonoinPelaaja() {
        assertEquals("Semenko", stats.topScorers(4).get(4).getName());
    }
}
