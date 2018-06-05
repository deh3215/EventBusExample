package tw.jim.eventbusexample.event;

/**
 * Created by deh3215 on 2018/6/5.
 */

public class MessageEvent {
    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

}
