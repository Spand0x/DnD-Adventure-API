package com.dndadventure.domain.entities.raceTraits;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "race_advantages")
public class RaceAdvantage extends BaseRaceTraits {
    public RaceAdvantage() {
    }
}
