/**
 * Representa la interface del programa.
 */
public class Radio implements IRadio{

    private Vista vista;
    public Radio() {
        this.vista = new Vista(this);

    }

    
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

        
        if(isAM() ){
            estacion += 10;

            if(estacion > 1610){
                estacion = 530;
            }
        }
        else{
            estacion += 0.2;

            if(estacion> 107.9){
                estacion= 87.9;
            }
        }

        return estacion;
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
        if(isOn()){

            if(frecuencia.equals("AM")){
                frecuencia = "FM";
                estacion = 87.9;
            }
            else if(frecuencia.equals("FM")){
                frecuencia = "AM";
                estacion = 530.0;
            }
        }
        
    }

    @Override
    public void switchOnOff() {
        if (!isOn()){
            encendido = true;
            frecuencia = "AM";
           

        }  
       else{
        encendido=false ;
       }

        
    }

    
    
 
}
