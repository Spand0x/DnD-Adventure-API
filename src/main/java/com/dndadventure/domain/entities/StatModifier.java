package com.dndadventure.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stats_modifiers")
public class StatModifier extends Stat {

    public StatModifier() {
    }

}
