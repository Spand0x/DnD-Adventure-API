package com.dndadventure.domain.entities;

import com.dndadventure.domain.entities.constants.ActionTypeEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "action_types")
public class ActionType extends BaseEntity {
    private ActionTypeEnum actionType;

    public ActionType() {
    }

    @Enumerated(value = EnumType.STRING)
    public ActionTypeEnum getActionType() {
        return actionType;
    }

    public ActionType setActionType(ActionTypeEnum actionType) {
        this.actionType = actionType;
        return this;
    }
}
