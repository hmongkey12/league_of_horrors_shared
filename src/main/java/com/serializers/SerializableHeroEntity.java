package com.serializers;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class SerializableHeroEntity implements Serializable {
    private String heroName;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private boolean isAttacking;
    private boolean isMoving;
    private List<SerializableAbilityEntity> abilities;
    private String id;
    private boolean isFalling;
    private boolean isJumping;
    private float jumpStart;
    private int health;
    private float movingStart;
    private float movingEnd;
    private float attackStart;
    private float attackEnd;
    private String facingDirection;
}
