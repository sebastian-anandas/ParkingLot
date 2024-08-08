package io.seb.strategies;

import io.seb.models.Gate;
import io.seb.models.ParkingSpot;
import io.seb.models.VehicleType;

public interface SpotAssignmentStrategy {

    ParkingSpot assignSpot(VehicleType vehicleType, Gate gate);

}
