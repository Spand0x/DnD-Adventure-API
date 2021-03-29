package com.dndadventure.domain.entities;


import com.dndadventure.domain.entities.RaceTraits.RaceAdvantage;
import com.dndadventure.domain.entities.RaceTraits.RaceDisadvantage;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "races")
public class CharacterRace extends BaseEntity {
    private String name;
    private String description;
    private Set<RaceAdvantage> advantages;
    private Set<RaceDisadvantage> disadvantages;
    private List<StatsModifier> modifiers;

    public CharacterRace() {
    }

    public String getName() {
        return name;
    }

    public CharacterRace setName(String name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public CharacterRace setDescription(String description) {
        this.description = description;
        return this;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "race_uuid",referencedColumnName = "uuid")
    public Set<RaceAdvantage> getAdvantages() {
        return advantages;
    }

    public CharacterRace setAdvantages(Set<RaceAdvantage> advantages) {
        this.advantages = advantages;
        return this;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "race_uuid", referencedColumnName = "uuid")
    public Set<RaceDisadvantage> getDisadvantages() {
        return disadvantages;
    }

    public CharacterRace setDisadvantages(Set<RaceDisadvantage> disadvantages) {
        this.disadvantages = disadvantages;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "races_stats_modifiers",
        joinColumns = @JoinColumn(name = "race_uuid", referencedColumnName = "uuid"),
        inverseJoinColumns = @JoinColumn(name = "class_uuid", referencedColumnName = "uuid")
    )
    public List<StatsModifier> getModifiers() {
        return modifiers;
    }

    public CharacterRace setModifiers(List<StatsModifier> modifiers) {
        this.modifiers = modifiers;
        return this;
    }
}
