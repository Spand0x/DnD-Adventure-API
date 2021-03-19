package com.dndadventure.domain.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stats_modifiers")
public class StatsModifier extends Stats {
//    private CharacterRace race;

    public StatsModifier() {
    }

//    @ManyToOne
//    @JoinColumn(name = "race_uuid")
//    public CharacterRace getRace() {
//        return race;
//    }
//
//    public RaceStatsModifier setRace(CharacterRace race) {
//        this.race = race;
//        return this;
//    }
}
