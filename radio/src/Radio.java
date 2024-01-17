/**
 * Implementa la interface del programa.
 */
public class Radio implements IRadio{

    private Vista vista;
    public Radio() {
        this.vista = new Vista(this);

    }

    
    private boolean encendido = false;
    private String frecuencia;
    private double estacion;

    
    
    public double [] listaAM = new double[12];    
    public double [] listaFM = new double[12];
    private int ultimoClickedButt;  //Se crea esta variable para tener registro de los botones oprimidos y que la gui no produzca un error al intentar leer el botón de guardado

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
        boolean hayElementosNoCeroAM = false;   //Métodos para verificar si existen datos guardados en los arrays creados. Usado para evitar guardar una estación como 0.0 por error. 
        for (double elemento : listaAM) {
            if (elemento != 0) {
                hayElementosNoCeroAM = true;
                break;
            }
}
boolean hayElementosNoCeroFM = false;
        for (double elemento : listaFM) {
            if (elemento != 0) {
                hayElementosNoCeroFM = true;
                break;
            }
}
        if(isOn()){
            ultimoClickedButt = buttonId;

            if (isAM()){
                if (estacion != 0 && hayElementosNoCeroAM) {
                    if (listaAM[buttonId-1] !=0 ){      
                        estacion = listaAM[buttonId-1]; 
                    }
                     
                }
                
                return listaAM[buttonId-1]; //Se le resta 1 para que no exista error de index outofbonds
            } 
            else{
                if (estacion != 0 && hayElementosNoCeroFM) {
                    if (listaFM[buttonId-1] !=0 ){
                        estacion = listaFM[buttonId-1];
                    }
                     
                }
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
                estacion = 87.9;    //Al cambiar de frecuencia, se asigna una estación predeterminada inicial.
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
            frecuencia = "FM";
            estacion = 87.9;    //Configuración predeterminada inicial al encender. 

        }  
       else{
        encendido=false ;
       }

        
    }

    public double getCurrentStation() {
        return estacion;
    }
    
    
    
 
}
