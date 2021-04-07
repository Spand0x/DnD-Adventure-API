package com.dndadventure.domain.entities;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "races")
public class Race extends BaseEntity {
    private String name;
    private String description;
    private Set<BaseRaceTraits> advantages;
    private Set<BaseRaceTraits> disadvantages;
    private List<StatModifier> modifiers;

    public Race() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public Race setName(String name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public Race setDescription(String description) {
        this.description = description;
        return this;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "race_uuid", referencedColumnName = "uuid")
    public Set<BaseRaceTraits> getAdvantages() {
        return advantages;
    }

    public Race setAdvantages(Set<BaseRaceTraits> advantages) {
        this.advantages = advantages;
        return this;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "race_uuid", referencedColumnName = "uuid")
    public Set<BaseRaceTraits> getDisadvantages() {
        return disadvantages;
    }

    public Race setDisadvantages(Set<BaseRaceTraits> disadvantages) {
        this.disadvantages = disadvantages;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "races_stats_modifiers",
        joinColumns = @JoinColumn(name = "race_uuid", referencedColumnName = "uuid"),
        inverseJoinColumns = @JoinColumn(name = "stat_uuid", referencedColumnName = "uuid")
    )
    public List<StatModifier> getModifiers() {
        return modifiers;
    }

    public Race setModifiers(List<StatModifier> modifiers) {
        this.modifiers = modifiers;
        return this;
    }
}
