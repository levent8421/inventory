package com.monolithiot.inventory.service.task;

/**
 * Create By Levent8421
 * Create Time: 2020/12/22 11:07
 * Class Name: PartOutboundException
 * Author: Levent8421
 * Description:
 * 物料出库异常
 *
 * @author Levent8421
 */
public class PartOutboundException extends Exception {
    public PartOutboundException() {
    }

    public PartOutboundException(String message) {
        super(message);
    }

    public PartOutboundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PartOutboundException(Throwable cause) {
        super(cause);
    }

    public PartOutboundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
