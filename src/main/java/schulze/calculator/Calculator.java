package schulze.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import schulze.Schulze;

import javax.annotation.PostConstruct;
import java.util.Arrays;

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
        System.out.println("/////////////////" +count);
        schulze.addAllCandidates(Arrays.asList("Cruze", "Dark-Hive", "Delta", "Dot-Luv", "Eggplant", "Excite-Bike", "Flick", "Glass-X"));
    }


}
