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

    //Test para validar si se guarda correctamente una estación en la frecuencia FM.
    @Test
    public void testSavingInFM(){
        Radio radio = new Radio();
        radio.switchOnOff();
        double stationToSave = radio.getCurrentStation();
        radio.saveStation(1, stationToSave);
        for (int i = 0; i < 4; i++) {       //Se itera para simular varios cambios manuales en las estaciones
            radio.nextStation();
        }
        radio.selectStation(1);
        assertTrue(radio.getCurrentStation() == stationToSave);
    }

    @Test
    public void testSavingInAM(){
        Radio radio = new Radio();
        radio.switchOnOff();
        radio.switchAMFM();     //Se llama este método porque inicialmente la radio enciende en FM. 
        double stationToSave = radio.getCurrentStation();
        radio.saveStation(3, stationToSave);
        for (int i = 0; i < 5; i++) {   //Se itera para simular varios cambios manuales en las estaciones
            radio.nextStation();    
        }
        radio.selectStation(3);
        assertTrue(radio.getCurrentStation() == stationToSave);
    }

    //Test que fallará. Está hecho para corroborar que las estaciones guardadas en AM difieren de las guardadas en FM
    @Test
    public void failingTryingToCompareSavedInAMWithSavedInFM(){
        Radio radio = new Radio();
        radio.switchOnOff();
        double stationToSave1 = radio.getCurrentStation();
        radio.saveStation(1, stationToSave1);
        radio.switchAMFM();
        radio.selectStation(1);
        assertTrue(radio.getCurrentStation() == stationToSave1);
    }
}
