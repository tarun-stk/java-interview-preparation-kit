package multple.inheritance;

public class Main {
    public static void main(String[] args) {
        /*Below causes no issue, because we're calling rotate on twowheeler*/
        TwoWheeler twoWheeler = new TwoWheeler();
        twoWheeler.rotate();
        BackWheel bw = new TwoWheeler();
        bw.rotate();
    }
}