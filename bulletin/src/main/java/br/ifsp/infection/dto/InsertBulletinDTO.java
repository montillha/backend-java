package br.ifsp.infection.dto;

import br.ifsp.infection.model.State;

import java.time.LocalDate;

public record InsertBulletinDTO( String city, State state, int infected, int deaths, double icuRatio, LocalDate date) {
}
