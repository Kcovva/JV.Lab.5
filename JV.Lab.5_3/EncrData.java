package org.example;
import java.io.Serializable;

public class EncrData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cipherText;

    public EncrData(String cipherText) {
        this.cipherText = cipherText;
    }

    public String getCipherText() {
        return cipherText;
    }

}

