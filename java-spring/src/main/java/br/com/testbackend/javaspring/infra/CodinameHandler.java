package br.com.testbackend.javaspring.infra;

import br.com.testbackend.javaspring.model.GroupType;
import br.com.testbackend.javaspring.service.CodinameService;
import org.springframework.stereotype.Component;

@Component
public class CodinameHandler {

    private CodinameService codinameService;

    public CodinameHandler(CodinameService codinameService) {
        this.codinameService = codinameService;
    }

    public String findCodiname(GroupType groupType){
        if(groupType == GroupType.AVENGERS){
            String firstMatch = codinameService.getAvengersCodinameList().stream().findFirst().orElseThrow();
            this.codinameService.getAvengersCodinameList().remove(firstMatch);
            return firstMatch;
        }


        String firstMatch = codinameService.getJusticeLeagueCodinameList().stream().findFirst().orElseThrow();
        this.codinameService.getJusticeLeagueCodinameList().remove(firstMatch);
        return firstMatch;

    }
}
