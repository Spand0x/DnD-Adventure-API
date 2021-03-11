package com.dndadventure.domain.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "race_stats_modifiers")
public class RaceStatsModifier extends Stats {
    private CharacterRace race;

    public RaceStatsModifier() {
    }

    @ManyToOne
    public CharacterRace getRace() {
        return race;
    }

    public RaceStatsModifier setRace(CharacterRace race) {
        this.race = race;
        return this;
    }
}
