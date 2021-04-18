package ca.ontario.health.hns.common;

import java.io.Serializable;


public class ByteWrapper implements Serializable {
    private static final long serialVersionUID = 1L;
    private byte [] content;
    public ByteWrapper(){

    }
    public ByteWrapper(byte[] content) {
        this.content = content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return this.content;
    }
}
