package quest.crealytics.exception;

import java.io.Serializable;

/**
 * Created by Pranav S Kurup on 5/13/2018.
 */
public class CrealyticsException extends RuntimeException implements Serializable {
    public CrealyticsException(String msg) {
        super(msg);
    }
}
