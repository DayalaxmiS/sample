import java.util.Scanner;

// Command interface
interface Command {
    void execute();
}

// Receiver classes
class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }

    public void turnOff() {
        System.out.println("Light is OFF");
    }
}

class Fan {
    public void start() {
        System.out.println("Fan is ON");
    }

    public void stop() {
        System.out.println("Fan is OFF");
    }
}

// Concrete Command classes
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

class FanOnCommand implements Command {
    private Fan fan;

    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.start();
    }
}

class FanOffCommand implements Command {
    private Fan fan;

    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.stop();
    }
}

// Invoker class
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Client
public class CommandPatternDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Light light = new Light();
        Fan fan = new Fan();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command fanOn = new FanOnCommand(fan);
        Command fanOff = new FanOffCommand(fan);

        RemoteControl remote = new RemoteControl();

        while (true) {
            System.out.println("Enter command: 1 - Light On, 2 - Light Off, 3 - Fan On, 4 - Fan Off, 0 - Exit");
            int command = scanner.nextInt();
            
            switch (command) {
                case 1:
                    remote.setCommand(lightOn);
                    break;
                case 2:
                    remote.setCommand(lightOff);
                    break;
                case 3:
                    remote.setCommand(fanOn);
                    break;
                case 4:
                    remote.setCommand(fanOff);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid command");
                    continue;
            }
            remote.pressButton();
        }
    }
}