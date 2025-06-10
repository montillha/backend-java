package br.ifsp.infection.main;

import br.ifsp.infection.model.Bulletin;
import br.ifsp.infection.model.State;
import br.ifsp.infection.persistence.RemoveBulletinService;
import br.ifsp.infection.persistence.SqliteBulletinDao;
import br.ifsp.infection.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SqliteBulletinDao bulletinDaoClass= new SqliteBulletinDao();

        List<Bulletin> bulletins = List.of(
                new Bulletin(0,"Descalvado", State.SP,24,7,50, LocalDate.parse("2020-07-07")),
                new Bulletin(0,"Descalvado",State.SP,30,9,60, LocalDate.parse("2020-08-07"))
        );

        //Registrando e guardando os novos registros com o id gerado:
        List<Bulletin> bulletinsComId = new ArrayList<>();
        RegisterBulletinService registerBulletinService = new RegisterBulletinService(bulletinDaoClass);
        for (Bulletin bulletin : bulletins) {
            int id =registerBulletinService.register(bulletin);
            bulletinsComId.add( new Bulletin(id,bulletin.getCity(),bulletin.getState(),bulletin.getInfected(),bulletin.getDeaths(),bulletin.getIcuRatio(),bulletin.getDate()));
        }

        //atualizando:
        Bulletin updatedBulletin = bulletinsComId.getFirst();
        updatedBulletin.setDeaths(10);
        UpdateBulletinService updateBulletinService= new UpdateBulletinService(bulletinDaoClass);
        updateBulletinService.update(updatedBulletin);

        //deletando:
        RemoveBulletinService removeBulletin = new RemoveBulletinService(bulletinDaoClass);
        removeBulletin.remove(bulletinsComId.getFirst().getId());


        //encontando os dados:
        FindBulletins findBulletins = new FindBulletins(bulletinDaoClass);
        List<Bulletin> allbulletins = findBulletins.findAll();

        //filtrando os dados:
       List<Bulletin> filtredBulletin= FilterBulletinService.filter(allbulletins,"São Carlos",null,LocalDate.parse("2022-04-29"),LocalDate.parse("2022-10-20"));
        for (Bulletin allbulletin : allbulletins) {
            System.out.println(allbulletin.toString());

        }

        //mostrando statisticas:
        StatisticData statisticData = StatisticsService.createStatistics(allbulletins);
        System.out.println(statisticData.toString());


    }
}
