package graafdiagrammas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import graafdiagrammas.Boog;
import graafdiagrammas.CirkelvormigeKnoop;
import graafdiagrammas.Knoop;
import graafdiagrammas.RechthoekigeKnoop;

class GraafdiagrammasTest {

	@Test
	void test() {
		RechthoekigeKnoop k1 = new RechthoekigeKnoop(5, 2);
		assertEquals(5, k1.getBreedte());
		assertEquals(2, k1.getHoogte());
		assertTrue(k1.getUitgaandeBogen().isEmpty());
		assertTrue(k1.getInkomendeBogen().isEmpty());
		
		CirkelvormigeKnoop k2 = new CirkelvormigeKnoop(100);
		assertEquals(100, k2.getStraal());
		assertTrue(k1.getUitgaandeBogen().isEmpty());
		assertTrue(k1.getInkomendeBogen().isEmpty());
		
		Boog b1 = new Boog(15);
		assertEquals(15, b1.getUitvalshoek());
		assertNull(b1.getBronknoop());
		assertNull(b1.getDoelknoop());
		
		b1.koppelBronknoop(k1);
		assertSame(k1, b1.getBronknoop());
		assertEquals(Set.of(b1), k1.getUitgaandeBogen());
		
		b1.koppelDoelknoop(k2);
		assertSame(k2, b1.getDoelknoop());
		assertEquals(Set.of(b1), k2.getInkomendeBogen());
		
		RechthoekigeKnoop k3 = new RechthoekigeKnoop(50, 20);
		Boog b2 = new Boog(30);
		b2.koppelBronknoop(k1);
		assertEquals(Set.of(b1, b2), k1.getUitgaandeBogen());
		b2.koppelDoelknoop(k3);
		
		RechthoekigeKnoop k4 = new RechthoekigeKnoop(5, 2);
		Boog b3 = new Boog(45);
		b3.koppelBronknoop(k2);
		b3.koppelDoelknoop(k4);
		
		CirkelvormigeKnoop k5 = new CirkelvormigeKnoop(10);
		Boog b4 = new Boog(60);
		b4.koppelBronknoop(k3);
		b4.koppelDoelknoop(k5);
		
		CirkelvormigeKnoop k6 = new CirkelvormigeKnoop(10);
		Boog b5 = new Boog(75);
		b5.koppelBronknoop(k3);
		b5.koppelDoelknoop(k6);
		
		{
			HashSet<Knoop> k1VolgendeKnopen = new HashSet<>();
			for (Iterator<Knoop> i = k1.getVolgendeKnopenIterator(); i.hasNext();)
				k1VolgendeKnopen.add(i.next());
			assertEquals(Set.of(k2, k3), k1VolgendeKnopen);
		}
		
		Boog b6 = new Boog(90);
		b6.koppelBronknoop(k1);
		
		{
			HashSet<Knoop> k1VolgendeKnopen = new HashSet<>();
			k1.forEachVolgendeKnoop(k -> k1VolgendeKnopen.add(k));
			assertEquals(Set.of(k2, k3), k1VolgendeKnopen);
		}
		
		assertEquals(Set.of(b3, b4, b5), k1.getUitgaandeBogenVanVolgendeKnopenStream().collect(Collectors.toSet()));
		
		b1.ontkoppelBronknoop();
		assertNull(b1.getBronknoop());
		assertEquals(Set.of(b2, b6), k1.getUitgaandeBogen());
		
		b1.ontkoppelDoelknoop();
		assertNull(b1.getDoelknoop());
		assertTrue(k2.getInkomendeBogen().isEmpty());
		
		int[] k1Info = {0, 0};
		((Knoop)k1).berekenInfo(k1Info);
		assertEquals(14, k1Info[0]);
		assertEquals(10, k1Info[1]);
		
		int[] k2Info = {0, 0};
		((Knoop)k2).berekenInfo(k2Info);
		assertEquals(628, k2Info[0]);
		assertEquals(31400, k2Info[1]);
		
		assertTrue(k1.isIsomorfMet(k4));
		assertTrue(k5.isIsomorfMet(k6));
		assertFalse(k1.isIsomorfMet(k2));
		assertFalse(k1.isIsomorfMet(k3));
		assertFalse(k2.isIsomorfMet(k6));
		assertFalse(k2.isIsomorfMet(k1));
	}

}
