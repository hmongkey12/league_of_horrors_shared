package com.serializers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SerializerDecoratorTest {

    @Mock
    private Serializer mockSerializer;

    private SerializerDecorator decorator;

    public SerializerDecoratorTest() {
        MockitoAnnotations.openMocks(this);
        decorator = new SerializerDecorator(mockSerializer);
    }

    @Test
    public void testSerialize() throws Exception {
        // Create an object to serialize
        SerializedHero hero = new SerializedHero(25, "John");

        // Set up mock serializer to return the serialized data
        byte[] serializedData = { 1, 2, 3 };
        Mockito.when(mockSerializer.serialize(hero)).thenReturn(serializedData);

        // Serialize the object using the decorator
        byte[] data = decorator.serialize(hero);

        // Assert that the serialized data is equal to the expected serialized data
        assertArrayEquals(serializedData, data);
    }

    @Test
    public void testDeserialize() throws Exception {
        // Create serialized data to deserialize
        byte[] serializedData = { 1, 2, 3 };

        // Set up mock serializer to return the deserialized object
        SerializedHero hero = new SerializedHero(25, "John");
        Mockito.when(mockSerializer.deserialize(serializedData)).thenReturn(hero);

        // Deserialize the serialized data using the decorator
        Object deserializedObject = decorator.deserialize(serializedData);

        // Assert that the deserialized object is equal to the expected object
        assertEquals(hero, deserializedObject);
    }
}
