

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Assert;
import org.junit.Test;
import schulze.Ballot;
import schulze.Schulze;

/**
 * Performs test of algorythm according to example in <a
 * href="http://en.wikipedia.org/wiki/Schulze_method">Wikipedia</a>
 * 
 * @author
 * 
 */
public class WikiTest {
	@Test
	public void testWikiExample() {
		final Schulze<String> schulze = new Schulze<String>();
		schulze.addAllCandidates(Arrays.asList("A", "B", "C", "D", "E"));
		putBallots(schulze, "ACBED", 5);
		putBallots(schulze, "ADECB", 5);
		putBallots(schulze, "BEDAC", 8);
		putBallots(schulze, "CABED", 3);
		putBallots(schulze, "CAEBD", 7);
		putBallots(schulze, "CBADE", 2);
		putBallots(schulze, "DCEBA", 7);
		putBallots(schulze, "EBADC", 8);
		Assert.assertEquals(schulze.getWinners().toString(), (new LinkedHashSet<String>(Arrays.asList("E", "A", "C", "B", "D"))).toString());
	}
 
        @Test
	public void testWikiExampleSecond() {
		final Schulze<String> schulze = new Schulze<String>();
		schulze.addAllCandidates(Arrays.asList("A", "B", "C", "D"));
		putBallots(schulze, "ACDB", 8);
		putBallots(schulze, "BADC", 2);
		putBallots(schulze, "CDBA", 4);
		putBallots(schulze, "DBAC", 4);
		putBallots(schulze, "DCBA", 3);
		Assert.assertEquals(schulze.getWinners().toString(), new LinkedHashSet<String>(Arrays.asList("D", "A","C", "B")).toString());
	}
        
             @Test
				 public void testWikiExampleSecond_Loop() {
		final Schulze<String> schulze = new Schulze<String>();
		schulze.addAllCandidates(Arrays.asList("A", "B", "C", "D", "E", "G", "I", "H"));
				 putBallotsDevite(schulze, "A,B,C,D,E,G,H,I", 5);
				 putBallotsDevite(schulze, "B,C,A,E,D,I,G,H", 7);
	/*	putBallots(schulze, "CABIHGED", 1);*/

		Assert.assertEquals(schulze.getWinners().toString(), new LinkedHashSet<String>(Arrays.asList("B","C", "A", "E", "D","I", "G","H")).toString());
	}

	@Test
	public void testWikiExampleNameProject8() {
		final Schulze<String> schulze = new Schulze<String>();
		schulze.addAllCandidates(Arrays.asList("Cruze", "Dark-Hive", "Delta", "Dot-Luv", "Eggplant", "Excite-Bike","Flick", "Glass-X" ));
		putBallotsDevite(schulze, "Cruze,Dark-Hive,Delta,Dot-Luv,Eggplant,Excite-Bike,Flick,Glass-X", 10);
		putBallotsDevite(schulze, "Flick,Glass-X,Cruze,Dark-Hive,Delta,Dot-Luv,Eggplant,Excite-Bike", 117);
		putBallotsDevite(schulze, "Glass-X,Cruze,Dark-Hive,Delta,Dot-Luv,Eggplant,Excite-Bike,Flick", 1);
		System.out.println(schulze.getRegisterdCandidates().toString());
		System.out.println(new StringBuilder("Flick,Glass-X,Cruze,Dark-Hive,Delta,Dot-Luv,Eggplant,Excite-Bike,").deleteCharAt(new StringBuilder("Flick,Glass-X,Cruze,Dark-Hive,Delta,Dot-Luv,Eggplant,Excite-Bike,").length() - 1));
		Assert.assertEquals(schulze.getWinners().toString(), new LinkedHashSet<String>(Arrays.asList("Flick", "Glass-X", "Cruze" ,"Dark-Hive", "Delta", "Dot-Luv", "Eggplant", "Excite-Bike")).toString());
	}
        
 /*       
         @Test
	public void testWikiExampleThird() {
		final Schulze<String> schulze = new Schulze<String>();
		schulze.addAllCandidates(Arrays.asList("A", "B", "C", "D"));
		putBallots(schulze, "ACDB", 8);
		putBallots(schulze, "ACDB", 2);
		putBallots(schulze, "ADCB", 3);
		putBallots(schulze, "BACD", 4);
		putBallots(schulze, "CBDA", 3);
                putBallots(schulze, "CDBA", 3);
                putBallots(schulze, "DACB", 1);
                putBallots(schulze, "DBAC", 5);
                putBallots(schulze, "DCBA", 4);
		Assert.assertEquals(schulze.getWinners(), new TreeSet<String>(Arrays.asList("D", "A", "C", "B")));
	}
        
             @Test
	public void testWikiExampleForth() {
		final Schulze<String> schulze = new Schulze<String>();
		schulze.addAllCandidates(Arrays.asList("A", "B", "C", "D", "E"));
		putBallots(schulze, "ABDEC", 3);
		putBallots(schulze, "ADEBC", 5);
		putBallots(schulze, "ADECB", 1);
		putBallots(schulze, "BADEC", 2);
		putBallots(schulze, "BDECA", 2);
                putBallots(schulze, "CABDE", 4);
                putBallots(schulze, "CBADE", 6);
                putBallots(schulze, "DBECA", 2);
                putBallots(schulze, "DECAB", 5);
		Assert.assertEquals(schulze.getWinners(), new TreeSet<String>(Arrays.asList("B", "A", "D", "E", "C")));
	}
        
                 /*    @Test
	public void testWikiExampleFifth() {
		final Schulze<String> schulze = new Schulze<String>();
		schulze.addAllCandidates(Arrays.asList("A", "B", "C", "D"));
		putBallots(schulze, "ABCD", 3);
		putBallots(schulze, "DABC", 2);
		putBallots(schulze, "DBCA", 2);
		putBallots(schulze, "CBDA", 2);
                         System.out.println(schulze.getWinners());
		Assert.assertEquals(schulze.getWinners(), new TreeSet<String>(Arrays.asList("B", "C", "D", "A")));
                         System.out.println("sdf");
	}
        */
   /*
         @Test
	public void testWikiExampleSixth() {
		final Schulze<String> schulze = new Schulze<String>();
		schulze.addAllCandidates(Arrays.asList("A", "B", "C", "D"));
		putBallots(schulze, "DBCA", 3);
		putBallots(schulze, "DCBA", 2);
		putBallots(schulze, "DCAB", 2);
		putBallots(schulze, "BDAC", 2);
                System.out.println(schulze.getWinners());
		Assert.assertEquals(schulze.getWinners(), new TreeSet<String>(Arrays.asList("B", "C", "D", "A")));
          ///      [D, B, C, A]
                       //  System.out.println("sdf");
        }*/
	private void putBallots(Schulze<String> schulze, String rankedList, int number) {
		for (int i = 0; i < number; i++) {
			int rank = 1;
			final Ballot<String> ballot = new Ballot<String>();
			for (char candidate : rankedList.toCharArray()) {
				ballot.vote(String.valueOf(candidate), rank++);
			}
			schulze.registerBallot(ballot);
		}
	}

	private void putBallotsDevite(Schulze<String> schulze, String rankedList, int number) {

		String temp ;
		for (int i = 0; i < number; i++) {
			StringTokenizer st = new StringTokenizer(rankedList, ",");
			int rank = 1;
			final Ballot<String> ballot = new Ballot<String>();

			while(st.hasMoreTokens()){
				temp = st.nextToken();
				ballot.vote(temp, rank++);
				System.out.println(temp);
			}
			schulze.registerBallot(ballot);
		}
	}
}
