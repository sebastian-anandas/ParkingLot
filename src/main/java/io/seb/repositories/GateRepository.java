package io.seb.repositories;

import io.seb.models.Gate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GateRepository {

    Map<Long, Gate> gateMap = new HashMap<>();

    public Optional<Gate> findByGateId(Long gateId) {
        if(gateMap.containsKey(gateId)) {
            return Optional.of(gateMap.get(gateId));
        }
        return Optional.empty();
    }

}
