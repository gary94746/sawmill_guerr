package controllers.utils;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Validators {


    public static EventHandler<KeyEvent> onlyLettersWithSpace() {
        return new EventHandler<>() {

            private boolean willConsume = false;

            @Override
            public void handle(KeyEvent event) {

                if (willConsume)
                    event.consume();

                if (!event.getCode().isLetterKey() && event.getCode().getCode() != 32) {
                    if (event.getEventType() == KeyEvent.KEY_PRESSED)
                        willConsume = true;
                    else if (event.getEventType() == KeyEvent.KEY_RELEASED)
                        willConsume = false;
                }
            }
        };
    }

    public static EventHandler<KeyEvent> onlyNumbers() {
        return new EventHandler<>() {

            private boolean willConsume = false;

            @Override
            public void handle(KeyEvent event) {

                if (willConsume)
                    event.consume();

                if (!event.getCode().isDigitKey()) {
                    if (event.getEventType() == KeyEvent.KEY_PRESSED)
                        willConsume = true;
                    else if (event.getEventType() == KeyEvent.KEY_RELEASED)
                        willConsume = false;
                }
            }
        };
    }
}
