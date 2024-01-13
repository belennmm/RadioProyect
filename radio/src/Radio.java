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
    private double [] listaAM = new double[12];    
    private double [] listaFM = new double[12];

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
        if (isAM() == true) {
            listaAM[buttonId-1] = station;
        }
        else {
            listaFM[buttonId-1] = station; 
        }
        
    }

    @Override
    public double selectStation(int buttonId) {
      if (isAM()==true) {
        return listaAM[buttonId-1];
      }
      else {
        return listaFM[buttonId-1];
      }
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
