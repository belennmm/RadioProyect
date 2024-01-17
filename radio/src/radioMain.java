public class radioMain {
    /**
     * Main del programa.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("----");

        Radio radio = new Radio();
        
        // Se necesitan 2 líneas para llamar al código compartido por la otra pareja pues programaron su código de forma que necesita un getFrame y no únicamente una instancia de RadioGUI
        //RadioGUI radioGUI = new RadioGUI();
        //radioGUI.getFrame().setVisible(true);

    }
}


