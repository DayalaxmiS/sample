import java.util.Scanner;

// State interface
interface State {
    void insertCoin();
    void selectItem();
    void dispenseItem();
}

// Concrete States
class NoCoinState implements State {
    private VendingMachine machine;

    public NoCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin inserted");
        machine.setState(machine.getHasCoinState());
    }

    @Override
    public void selectItem() {
        System.out.println("Insert coin first");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Insert coin first");
    }
}

class HasCoinState implements State {
    private VendingMachine machine;

    public HasCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin already inserted");
    }

    @Override
    public void selectItem() {
        System.out.println("Item selected");
        machine.setState(machine.getSoldState());
    }

    @Override
    public void dispenseItem() {
        System.out.println("Select item first");
    }
}

class SoldState implements State {
    private VendingMachine machine;

    public SoldState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Wait, item being dispensed");
    }

    @Override
    public void selectItem() {
        System.out.println("Wait, item being dispensed");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Item dispensed");
        machine.setState(machine.getNoCoinState());
    }
}

// Context class
class VendingMachine {
    private State noCoinState;
    private State hasCoinState;
    private State soldState;

    private State currentState;

    public VendingMachine() {
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        soldState = new SoldState(this);

        currentState = noCoinState;
    }

    public void setState(State state) {
        currentState = state;
    }

    public void insertCoin() {
        currentState.insertCoin();
    }

    public void selectItem() {
        currentState.selectItem();
    }

    public void dispenseItem() {
        currentState.dispenseItem();
    }

    public State getNoCoinState() {
        return noCoinState;
    }

    public State getHasCoinState() {
        return hasCoinState;
    }

    public State getSoldState() {
        return soldState;
    }
}

// Client
public class StatePatternDemo {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose action: 1 - Insert Coin, 2 - Select Item, 3 - Dispense Item, 0 - Exit");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    vendingMachine.insertCoin();
                    break;
                case 2:
                    vendingMachine.selectItem();
                    break;
                case 3:
                    vendingMachine.dispenseItem();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid action");
            }
        }
    }
}
