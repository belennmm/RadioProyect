import org.junit.Test;
import static org.junit.Assert.*;

public class RadioTest {

    // test para AM
    @Test
    public void testNextStationAM(){
        Radio radio =  new Radio();

        radio.switchOnOff();
        radio.switchAMFM();  // cambiar a AM

        double initialStation =  radio.getCurrentStation() ;
        double nextStation =radio.nextStation();
        assertTrue( nextStation > initialStation ) ;
        assertTrue( nextStation <= 1610  );  // 1610 como la última estación de AM
    }

    // test para FM
    @Test
    public void testNextStationFM(){
        Radio radio =new Radio();
        radio.switchOnOff();

        double initialStation =  radio.getCurrentStation();
        double nextStation =radio.nextStation();

        assertTrue (nextStation>initialStation);
        assertTrue(nextStation <= 107.9  ); // 107.9 como la última estación de FM
    }
}
