package br.ifsp.infection.service;

import br.ifsp.infection.model.Bulletin;
import br.ifsp.infection.model.State;

import java.time.LocalDate;
import java.util.List;

public class FilterBulletinService {
    private  FilterBulletinService(){};
    public static List<Bulletin> filter(List<Bulletin> bulletins, String city, State state, LocalDate startDate, LocalDate endDate){
        return bulletins.stream()
                .filter(b->city==null || city.equals(b.getCity()))
                .filter(b->state == null || state == b.getState())
                .filter(b-> startDate==null ||!b.getDate().isBefore(startDate))
                .filter(b-> endDate==null ||!b.getDate().isAfter(endDate))
                .toList();
    }
}
