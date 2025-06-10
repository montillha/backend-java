package br.ifsp.infection.model;

import java.util.Arrays;

public enum State {
    AC ("Acre"),
    AL ("Alagoas"),
    AP ("Amapá"),
    AM ("Amazonas"),
    BA ("Bahia"),
    CE ("Ceará"),
    DF ("Distrito Federal"),
    ES ("Espírito Santo"),
    GO ("Goiás"),
    MA ("Maranhão"),
    MT ("Mato Grosso"),
    MS ("Mato Grosso do Sul"),
    MG ("Minas Gerais"),
    PA ("Pará"),
    PB ("Paraíba"),
    PR ("Paraná"),
    PE ("Pernambuco"),
    PI ("Piauí"),
    RJ ("Rio de Janeiro"),
    RN ("Rio Grande do Norte"),
    RS ("Rio Grande do Sul"),
    RO ("Rondônia"),
    RR ("Roraima"),
    SC ("Santa Catarina"),
    SP ("São Paulo"),
    SE ("Sergipe"),
    TO ("Tocantins");

    private final String name;

    State(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static State fromName(String name) {
        return Arrays.stream(values()).filter(value -> value.name.equals(name)).findAny().orElseThrow();
    }
}
