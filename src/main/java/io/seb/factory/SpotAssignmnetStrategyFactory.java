package io.seb.factory;

import io.seb.models.SpotAssignmentStrategyType;
import io.seb.strategies.RandomSpotAssignmentStrategy;
import io.seb.strategies.SpotAssignmentStrategy;

public class SpotAssignmnetStrategyFactory {

    public static SpotAssignmentStrategy getSpotAssignmentStrategy(
            SpotAssignmentStrategyType spotAssignmentStrategyType) {

//        if(spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.NEAREST)) {
//            return new NearestSpotAssignmentStrategy();
//        } else if(spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.CHEAPEST)) {
//            return new CheapestSpotAssignmentStrategy();
//        }
        return new RandomSpotAssignmentStrategy();
    }

}
