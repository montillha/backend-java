package br.ifsp.infection.dto;

public record BulletinDTO(int id, String city, String state, int infected, int deaths, double icuRatio, String date) {
}
