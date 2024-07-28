Command Pattern

Overview
The Command Pattern is a behavioral design pattern that turns a request into a stand-alone object that contains all information about the request. This transformation lets you parameterize methods with different requests, delay or queue a request's execution, and support undoable operations.

Code Analysis
In this example, the Command Pattern is implemented in a home automation system where the user can turn appliances on and off using a remote control. The Command interface defines the execute method that all concrete command classes must implement. The Light and Fan classes are the receivers, while the LightOnCommand, LightOffCommand, FanOnCommand, and FanOffCommand classes are the concrete commands. The RemoteControl class acts as the invoker that executes the commands. This pattern decouples the objects that invoke the operation from the objects that perform the operation, making the system flexible and extensible.