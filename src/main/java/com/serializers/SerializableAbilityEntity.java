package com.serializers;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SerializableAbilityEntity implements Serializable {
    private String abilityName;
    private float abilityEnd;
    private float cooldownEnd;
    private float abilityStart;
    private float cooldownStart;
    private float damage;
    int xPos;
    int yPos;
    int width;
    int height;
}
