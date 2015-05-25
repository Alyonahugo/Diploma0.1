package schulze.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import schulze.Ballot;
import schulze.Schulze;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by User on 16.05.2015.
 */


@Component
public class Calculator {

    private final Schulze<String> schulze;
    private static int count ;

    public Calculator() {
        schulze= new Schulze<String>();
    }



    public Schulze<String> getSchulze() {
        return schulze;
    }

    @PostConstruct
    public void init(){
        count++;

    }

    public void putBallotsDevite(Schulze<String> schulze, String rankedList, int number) {

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
