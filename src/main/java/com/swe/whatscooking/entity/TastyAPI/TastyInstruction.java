package com.swe.whatscooking.entity.TastyAPI;

public class TastyInstruction {
    private long id;
    private String display_text;
    private int position;

    public TastyInstruction(long id, String display_text, int position) {
        this.id = id;
        this.display_text = display_text;
        this.position = position;
    }

    public TastyInstruction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplay_text() {
        return display_text;
    }

    public void setDisplay_text(String display_text) {
        this.display_text = display_text;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "TastyInstruction{" +
                "id=" + id +
                ", display_text='" + display_text + '\'' +
                ", position=" + position +
                '}';
    }
}
