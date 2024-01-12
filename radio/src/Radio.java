/**
 * Representa la interface del programa.
 */
public class Radio implements IRadio{

    Vista vista = new Vista(); 
    {
 vista.menu(); }

    
    private boolean encendido = false;
    private String frecuencia;
    private double estacion;
    
    
    /** 
     * @return boolean
     */
    @Override
    public boolean isAM() {
        if ( frecuencia.equals("AM")){
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public boolean isOn() {
        if (encendido == false){
            return false;
        }
        else{
            return true; 
        }
        
    }

    @Override
    public double nextStation() {
        
        return 0;
    }

    @Override
    public void saveStation(int buttonId, double station) {
       
        
    }

    @Override
    public double selectStation(int buttonId) {
      
        return 0;
    }

    @Override
    public void switchAMFM() {
        if (frecuencia.equals("AM")){
            frecuencia = "FM";
        }

        if (frecuencia.equals("FM")){
            frecuencia = "AM";
        }
        
    }

    @Override
    public void switchOnOff() {
        if (isOn() == false){
            encendido = true;
            frecuencia = "AM";
            estacion = 530.0;

        }  
        
        if (isOn() == true){
            encendido = false;
        }

        
    }

    
    
 
}