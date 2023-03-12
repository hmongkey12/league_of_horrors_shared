package com.serializers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SerializableGameStateDecoratorTest {

    @Mock
    private Serializer mockSerializer;

    private SerializableGameStateDecorator decorator;

    @BeforeEach
    public void setUp() {
        // Manually initialize mocks
        MockitoAnnotations.openMocks(this);
        decorator = new SerializableGameStateDecorator(mockSerializer);
    }

    @Test
    public void testSerializeValidObject() throws Exception {
        // Setup: Create a gamestate and serialize it
        SerializableGameState gameState = new SerializableGameState();
        byte[] serializedData = decorator.serialize(gameState);

        // Setup: mockSerializer returns a gameState object, and should trigger SerializableGameStateDecorator conditions
        Mockito.when(mockSerializer.deserialize(serializedData)).thenReturn(gameState);

        // Trigger: Deserialize the serialized data using the decorator
        Object deserializedGameState = decorator.deserialize(serializedData);

        // Verify: Deserialized object is equal to the expected object
        assertEquals(gameState, deserializedGameState);
    }

    @Test
    public void testDeserializeInvalidObject() throws Exception {
        // Setup: Create a Serializable object that is not a SerializableGameState object
        SerializableHeroEntity heroEntity = SerializableHeroEntity.builder().build();
        byte[] serializedData = decorator.serialize(heroEntity);

        // Setup: mockSerializer returns an object that is not SerializableGameState object
        Mockito.when(mockSerializer.deserialize(serializedData)).thenReturn(heroEntity);

        // Trigger: Deserialize the serialized data using the decorator
        Object deserializedObject = decorator.deserialize(serializedData);

        // Verify: deserialized object should be null
        assertNull(deserializedObject);
    }
}