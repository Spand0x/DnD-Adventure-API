package com.dndadventure.repositories;

import com.dndadventure.domain.entities.Action;
import com.dndadventure.domain.entities.constants.ActionTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, String> {

    List<Action> findAllByType(ActionTypeEnum actionTypeEnum);
}
