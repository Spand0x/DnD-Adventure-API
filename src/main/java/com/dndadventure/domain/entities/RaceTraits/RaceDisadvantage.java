package com.dndadventure.domain.entities.RaceTraits;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "race_disadvantages")
public class RaceDisadvantage extends BaseRaceTraits {
    public RaceDisadvantage() {
    }
}
