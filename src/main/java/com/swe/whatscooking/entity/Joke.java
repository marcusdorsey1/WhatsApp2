package com.swe.whatscooking.entity;

public class Joke {
    private Long id;
    private String type;
    private String setup;
    private String delivery;

    public Joke() {}

    public Joke(Long id, String type, String setup, String punchline) {
        this.id = id;
        this.type = type;
        this.setup = setup;
        this.delivery = punchline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String punchline) {
        this.delivery = punchline;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", setup='" + setup + '\'' +
                ", punchline='" + delivery + '\'' +
                '}';
    }

}
