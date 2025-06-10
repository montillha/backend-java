package br.ifsp.infection.service;

import br.ifsp.infection.model.Bulletin;

import java.util.ArrayList;
import java.util.List;

public class StatisticsService {
    private StatisticsService(){};
    public static StatisticData createStatistics(List<Bulletin> bulletins){
        List<StatisticData> statisticData = new ArrayList<>();
        int cases = bulletins.stream().map(Bulletin::getInfected).reduce(0,Integer::sum);
        int deaths = bulletins.stream().map(Bulletin::getDeaths).reduce(0,Integer::sum);
        double avarageIcu= (bulletins.stream().map(Bulletin::getIcuRatio).reduce(0.0,Double::sum))/ bulletins.size();
        return  new StatisticData(cases,deaths,avarageIcu);
    }
}
