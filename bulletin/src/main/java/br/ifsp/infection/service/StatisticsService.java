package br.ifsp.infection.service;

import br.ifsp.infection.model.Bulletin;

import java.util.ArrayList;
import java.util.List;

public class StatisticsService {
    private StatisticsService(){};
    public static StatisticData createStatistics(List<Bulletin> bulletins){
        List<StatisticData> statisticData = new ArrayList<>();
        int cases = bulletins.stream().mapToInt(Bulletin::getInfected).sum();
        int deaths = bulletins.stream().mapToInt(Bulletin::getDeaths).sum();
        double avarageIcu= bulletins.stream().mapToDouble(Bulletin::getIcuRatio).average().orElse(0);
        return  new StatisticData(cases,deaths,avarageIcu);
    }
}
