import java.util.Scanner;

// Component interface
interface Text {
    String format();
}

// Concrete Component
class PlainText implements Text {
    private String text;

    public PlainText(String text) {
        this.text = text;
    }

    @Override
    public String format() {
        return text;
    }
}

// Decorator class
abstract class TextDecorator implements Text {
    protected Text decoratedText;

    public TextDecorator(Text decoratedText) {
        this.decoratedText = decoratedText;
    }

    @Override
    public String format() {
        return decoratedText.format();
    }
}

// Concrete Decorators
class BoldText extends TextDecorator {
    public BoldText(Text decoratedText) {
        super(decoratedText);
    }

    @Override
    public String format() {
        return "<b>" + super.format() + "</b>";
    }
}

class ItalicText extends TextDecorator {
    public ItalicText(Text decoratedText) {
        super(decoratedText);
    }

    @Override
    public String format() {
        return "<i>" + super.format() + "</i>";
    }
}

class UnderlineText extends TextDecorator {
    public UnderlineText(Text decoratedText) {
        super(decoratedText);
    }

    @Override
    public String format() {
        return "<u>" + super.format() + "</u>";
    }
}

// Client
public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter text to format:");
        String inputText = scanner.nextLine();

        Text text = new PlainText(inputText);

        while (true) {
            System.out.println("Choose formatting: 1 - Bold, 2 - Italic, 3 - Underline, 0 - Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    text = new BoldText(text);
                    break;
                case 2:
                    text = new ItalicText(text);
                    break;
                case 3:
                    text = new UnderlineText(text);
                    break;
                case 0:
                    System.out.println("Final formatted text: " + text.format());
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}