package com.dndadventure.domain.entities.raceTraits;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "race_disadvantages")
public class RaceDisadvantage extends BaseRaceTraits {
    public RaceDisadvantage() {
    }
}
