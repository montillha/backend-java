package br.ifsp.infection.model;

import java.time.LocalDate;
import java.util.Objects;

public class Bulletin{
    private final int id;
    private String city;
    private State state;
    private int infected;
    private int deaths;
    private double icuRatio;
    private LocalDate date;

    public Bulletin(int id, String city, State state, int infected, int deaths, double icuRatio, LocalDate date) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.infected = infected;
        this.deaths = deaths;
        this.icuRatio = icuRatio;
        this.date = date;
    }

    public Bulletin(){
        this(0, null, null, 0, 0, 0.0, null);
    }

    public int getId() { return id; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public State getState() { return state; }

    public void setState(State state) { this.state = state; }

    public int getInfected() { return infected; }

    public void setInfected(int infected) { this.infected = infected; }

    public int getDeaths() { return deaths; }

    public void setDeaths(int deaths) { this.deaths = deaths; }

    public double getIcuRatio() { return icuRatio; }

    public void setIcuRatio(double icuRatio) {this.icuRatio = icuRatio;}

    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bulletin that = (Bulletin) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Bulletin{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", infected=" + infected +
                ", deaths=" + deaths +
                ", icuRatio=" + icuRatio +
                ", date=" + date +
                '}';
    }
}
