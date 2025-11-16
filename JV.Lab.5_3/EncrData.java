package org.example;
import java.io.Serializable;

public class EncrData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cipherText;
    private String note;

    public EncrData(String cipherText, String note) {
        this.cipherText = cipherText;
        this.note = note;
    }

    public String getCipherText() {
        return cipherText;
    }

    public String getNote() {
        return note;
    }
}

