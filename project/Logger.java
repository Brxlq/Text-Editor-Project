package project;

public class Logger implements Observer_Editing {

    @Override
    public void event(String eventType, String eventDetails) {
        System.out.println("LOG: " + eventType + " : " + eventDetails);
    }

}
