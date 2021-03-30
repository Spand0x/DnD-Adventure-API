package com.dndadventure.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class CharacterStats extends Stats {
}
