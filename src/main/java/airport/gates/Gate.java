package airport;

public class Gate implements Observer {
    private String gateNumber;

    public Gate(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    @Override
    public void update(String event) {
        System.out.println("Gate " + gateNumber + " received update: " + event);
    }

    public String getGateNumber() {
        return gateNumber;
    }
}
