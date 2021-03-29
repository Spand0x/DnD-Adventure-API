package com.dndadventure.domain.entities.constants;

public enum DiceTypeEnum {
    D4(4),
    D6(6),
    D8(8),
    D10(10),
    D12(12),
    D20(20),
    D100(100);

    public final Integer value;

    private DiceTypeEnum(Integer value){
        this.value = value;
    }
}
