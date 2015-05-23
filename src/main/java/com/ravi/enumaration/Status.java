package com.ravi.enumaration;

/**
 * Created by User on 18.05.2015.
 */
public enum Status {
    APPROVED("Approved"), NOT_APPROVED("Not approved");

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private String label;

    Status(String label) {
        this.label = label;
    }
}
