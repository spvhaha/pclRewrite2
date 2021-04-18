//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pathwayserver.appnote;

import appnote.ddl.IO_Appnotesrv_rep;
import appnote.ddl.IO_Appnotesrv_req;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageHandler {
    private static final Logger logger = Logger.getLogger(MessageHandler.class.getName());

    public MessageHandler() {
    }

    public Object[] reqGENSERVICE(byte[] argsAsByteArray) {
        if (logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Start reqGENSERVICE: " + System.currentTimeMillis());
        }

        IO_Appnotesrv_req iMessageIn = new IO_Appnotesrv_req();
        iMessageIn.clear_All();

        try {
            iMessageIn.unmarshal(argsAsByteArray, iMessageIn.getLength());
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE, "Input request: " + iMessageIn.toString());
            }
        } catch (Exception var4) {
            logger.log(Level.SEVERE, "Exception in  MessageHandler.reqGENSERVICE(): ", var4);
            throw new RuntimeException(var4);
        }

        if (logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "End reqGENSERVICE: " + System.currentTimeMillis());
        }

        return new Object[]{iMessageIn};
    }

    public byte[] repGENSERVICE(Object returnObject) {
        if (logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Start repGENSERVICE: " + System.currentTimeMillis());
            logger.log(Level.FINE, "Reply object: " + ((IO_Appnotesrv_rep)returnObject).toString());
        }

        try {
            return ((IO_Appnotesrv_rep)returnObject).marshal();
        } catch (Exception var3) {
            logger.log(Level.SEVERE, "Error in MessageHandler.repGENSERVICE(): ", var3);
            throw new RuntimeException(var3);
        }
    }
}
