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
    private int ultimoClickedButt;

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

        if(isOn()){
            if(isAM() ){
                estacion += 10;
                
                if(estacion >1610 ){
                    estacion= 530;
                }
            } 
            else{
                estacion += 0.2;
                
                if(estacion >107.9) {
                    estacion =87.9;
                }
            }
        }

        return estacion;
    }

    @Override
    public void saveStation( int buttonId,  double station ){
        if(isOn()){

            if( isAM() ){
                listaAM[ buttonId - 1 ] =station; //Se le resta 1 para que no exista error de index outofbonds
            } 
            else{
                listaFM[ buttonId -  1] =station;
            }
        }
    }

    @Override
    public double selectStation(int buttonId){
        if(isOn()){
            ultimoClickedButt = buttonId;

            if (isAM()){
                return listaAM[buttonId-1];
            } 
            else{
                return listaFM[buttonId-1]; //Se le resta 1 para que no exista error de index outofbonds
            }
        }

        return 0.0; // valor que se retorna si está off
    }

    // Agrega este método para obtener el último botón presionado
    public int getultimoClickedButt() {
        return ultimoClickedButt;
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
                estacion = 530;
            }
        }
        
    }

    @Override
    public void switchOnOff() {
        if (!isOn()){
            encendido = true;
            frecuencia = "AM";
            estacion = 530;

        }  
       else{
        encendido=false ;
       }

        
    }

    public double getCurrentStation() {
        return estacion;
    }
    
    
 
}
