package contacts;

import java.io.Serializable;

public interface Contact extends Serializable {

        void displayInfo();
        String regexToMatch();
}
