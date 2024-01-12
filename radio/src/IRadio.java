public interface IRadio {
    void saveStation(int buttonId, double station);
    boolean isAM();
    boolean isOn();
    double selectStation(int buttonId);
    void switchOnOff();
    void switchAMFM();
    double nextStation();
    
}
