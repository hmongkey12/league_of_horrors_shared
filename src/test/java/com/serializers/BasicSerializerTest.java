package com.serializers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class BasicSerializerTest {
    @Test
    public void testSerializeAndDeserialize() throws Exception {
        // Create an object to serialize and deserialize
        SerializedHero hero = new SerializedHero(25, "John");

        // Serialize the object
        BasicSerializer serializer = new BasicSerializer();
        byte[] data = serializer.serialize(hero);

        // Deserialize the serialized data
        Object deserializedObject = serializer.deserialize(data);

        // Assert that the deserialized object is equal to the original object
        assertEquals(hero, deserializedObject);
    }
}

