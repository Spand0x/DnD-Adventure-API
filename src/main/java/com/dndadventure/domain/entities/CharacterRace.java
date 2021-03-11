package com.dndadventure.domain.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "races")
public class CharacterRace extends BaseEntity {
    private String name;
    private String description;
    private String pros;
    private String cons;
    private List<RaceStatsModifier> modifiers;

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

    public String getPros() {
        return pros;
    }

    public CharacterRace setPros(String pros) {
        this.pros = pros;
        return this;
    }

    public String getCons() {
        return cons;
    }

    public CharacterRace setCons(String cons) {
        this.cons = cons;
        return this;
    }

    @OneToMany(mappedBy = "race")
    public List<RaceStatsModifier> getModifiers() {
        return modifiers;
    }

    public CharacterRace setModifiers(List<RaceStatsModifier> modifiers) {
        this.modifiers = modifiers;
        return this;
    }
}
