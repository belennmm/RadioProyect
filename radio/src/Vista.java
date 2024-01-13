import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Representa la Vista del programa.
 */
public class  Vista extends  Frame  implements ActionListener{

    private Radio radio;

    private Button  on_offBut;
    private Button  am_fmButt;
    private Button  nextStationBut;
    private Button[ ] estacionesButt ;
    private Button saveB ;
    private Label estadoRadio ;


    public Vista( Radio  radio){
        this.radio =  radio;

        setLayout (new  GridBagLayout() );
        GridBagConstraints espacio=new GridBagConstraints();

        espacio.insets = new Insets(5, 0, 5, 0);

        // botón de On/Off
        on_offBut = new Button(" O ");
        customizeButton( on_offBut);
        espacio.gridx=  2;
        espacio.gridy =  0;
        espacio.gridwidth= 1 ;
        add(on_offBut , espacio) ;

        // botón am/fm
        am_fmButt =new Button("AM/FM");
        customizeButton(am_fmButt);
        espacio.gridx= 3;
        espacio.gridy =  0;
        espacio.gridwidth= 1;
        add(am_fmButt , espacio);

        // botones para 12 estaciones
        estacionesButt = new Button[12];

        for(int count=0 ; count<estacionesButt.length;  count++){
            // se establecen los botones y el espacio en dónde se ubicarán
            estacionesButt[ count] =new Button("" +(count+1) ) ;

            customizeButton( estacionesButt[count]);
            espacio.gridx = count % 6; // para que en cada fila queden 6 estaciones
            espacio.gridy= 1 + count / 6;
            espacio.gridwidth = 1;

            add(estacionesButt[ count], espacio);
        }

        // botón para guardar estaciones
        saveB = new Button("⌧");
        customizeButton(saveB);
        espacio.gridx= 2;
        espacio.gridy =  3;
        espacio.gridwidth= 1;
        add(saveB , espacio) ;

        // botón siguiente
        nextStationBut = new Button(" >> ");
        customizeButton(nextStationBut);
        espacio.gridx= 3;
        espacio.gridy =  3;
        espacio.gridwidth= 1;
        add(nextStationBut , espacio) ;

        // para mostrar el estado del radio 
        estadoRadio= new Label("Status:                    ");
        espacio.gridx= 0;
        espacio.gridy =  4;
        espacio.gridwidth= 5;
        espacio.insets = new Insets(10, 0, 0, 0);
        add(estadoRadio , espacio) ;

        setTitle("Radio");
        setSize(400, 300);
        setVisible(true);
    }

    // carcterísticas de los botones 
    private void customizeButton( Button button){
        button.setFont(new  Font("Monospaced" , Font.PLAIN, 20));
        button.setBackground(Color.lightGray);
        button.addActionListener(this );
    }

    // Acción que realiza cada botón
    @Override

    public void actionPerformed( ActionEvent e ){

        if(e.getSource()== on_offBut){
            radio.switchOnOff();
        }
        else if (e.getSource()== am_fmButt){
            radio.switchAMFM();
        } 
        else if(e.getSource() ==nextStationBut){
            radio.nextStation();
        } 
        else if(e.getSource() ==saveB){
            
        } 
        else{
            
            for( int counter = 0; counter < estacionesButt.length; counter++){
                if (e.getSource() == estacionesButt[ counter]){
                    
                }
            }
        }

        updateestadoRadio();
    }

    // actualiza la etiqueta con el status actual del Radio
    private void updateestadoRadio() {
        String estado = "Status: " + (radio.isOn() ? "On" : "Off") ;

        if(radio.isOn()){
            estado += " " + (radio.isAM() ? "AM": "FM");
            estado += " "+ radio.nextStation();
        }

        estadoRadio.setText(estado);

    }
}
