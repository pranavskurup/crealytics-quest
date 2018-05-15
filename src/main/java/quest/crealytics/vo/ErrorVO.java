package quest.crealytics.vo;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@Getter
@Builder
public class ErrorVO implements Serializable {
    private static final long serialVersionUID = 7457694782438657935L;
    String statusCode;
    String msg;
}

