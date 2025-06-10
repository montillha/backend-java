package br.ifsp.infection.dto;

import br.ifsp.infection.model.Bulletin;
import br.ifsp.infection.model.State;

import java.time.LocalDate;

public class BulletinMapper {
    private BulletinMapper(){};
    public static BulletinDTO toDTO(Bulletin bulletin){
        String date = bulletin.getDate().toString();
        return new BulletinDTO(bulletin.getId(),bulletin.getCity(),bulletin.getState().toString(),bulletin.getInfected(), bulletin.getDeaths(), bulletin.getIcuRatio(),date);

    }
    public static Bulletin fromDTO(BulletinDTO bulletinDTO){
        LocalDate date = LocalDate.parse(bulletinDTO.date());
        State state = State.fromName(bulletinDTO.state());
        return new Bulletin(bulletinDTO.id(),bulletinDTO.city(),state, bulletinDTO.infected(), bulletinDTO.deaths(), bulletinDTO.icuRatio(), date);

    }
}
