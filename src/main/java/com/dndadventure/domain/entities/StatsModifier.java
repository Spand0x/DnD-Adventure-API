package com.dndadventure.domain.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stats_modifiers")
public class StatsModifier extends Stats {

    public StatsModifier() {
    }

}
