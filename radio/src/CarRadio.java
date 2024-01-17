public class CarRadio implements IRadio {
    private boolean isOn;
    private boolean isAm;
    private double currentStation;
    private final double[] amPresets = new double[12];
    private final double[] fmPresets = new double[12];
    
    public double getCurrentStation() {
        return currentStation;
    }
    
    public CarRadio() {
        isOn = false;
        isAm = true; // Supongamos que inicia en AM por defecto
        currentStation = isAm ? 530 : 87.9; // Estación inicial dependiendo de si es AM o FM
    }

    @Override
    public void saveStation(int buttonId, double station) {
        if (buttonId < 1 || buttonId > 12) {
            System.out.println("Número de botón inválido.");
            return;
        }
        int index = buttonId - 1; // Los arrays en Java comienzan en 0
        if (isAm) {
            amPresets[index] = station;
        } else {
            fmPresets[index] = station;
        }
    }

    @Override
    public boolean isAM() {
        return isAm;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public double selectStation(int buttonId) {
        if (buttonId < 1 || buttonId > 12) {
            System.out.println("Número de botón inválido.");
            return 0.0;
        }
        int index = buttonId - 1; // Ajuste para el índice del array
        return isAm ? amPresets[index] : fmPresets[index];
    }

    @Override
    public void switchOnOff() {
        isOn = !isOn;
        if (isOn) {
            System.out.println("Radio encendido.");
        } else {
            System.out.println("Radio apagado.");
        }
    }

    @Override
    public void switchAMFM() {
        if (!isOn) {
            System.out.println("El radio está apagado. Por favor, enciéndalo primero.");
            return;
        }

        isAm = !isAm; // Cambia entre AM y FM

        // Restablece la estación al valor predeterminado para AM o FM
        if (isAm) {
            System.out.println("Cambiado a AM.");
            currentStation = 530; // Establece la estación a la frecuencia inicial de AM
        } else {
            System.out.println("Cambiado a FM.");
            currentStation = 87.9; // Establece la estación a la frecuencia inicial de FM
        }
    }

    @Override
    public double nextStation() {
        if (!isOn) {
            System.out.println("El radio está apagado. Por favor, enciéndalo primero.");
            return 0.0;
        }

        if (isAm) {
            currentStation += 10;
            if (currentStation > 1610) {
                currentStation = 530; // Reinicia al inicio del rango AM
            }
        } else {
            currentStation += 0.2;
            if (currentStation > 107.9) {
                currentStation = 87.9; // Reinicia al inicio del rango FM
            }
        }

        return currentStation;
    }

}