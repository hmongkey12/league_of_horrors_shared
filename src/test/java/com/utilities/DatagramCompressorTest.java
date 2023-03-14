package com.utilities;

import com.utilities.exceptions.OversizedDataException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.*;

public class DatagramCompressorTest {
    private final String UNCOMPRESSED_DATA = "uncompressed data";
    private final static int DATAGRAM_PACKET_SIZE = 16000;

    @Test
    public void decompress_shouldReturnOriginalData_whenDecompressingCompressedData() throws DataFormatException, OversizedDataException {
        // Setup
        byte[] originalData = UNCOMPRESSED_DATA.getBytes();

        // Trigger
        byte[] compressedData = DatagramCompressor.compress(originalData);
        byte[] decompressedData = DatagramCompressor.decompress(compressedData);

        // Verify
        assertArrayEquals(originalData, decompressedData);
    }

    @Test
    public void decompress_shouldReturnDataWithSameLengthAsOriginal_whenItDecompresses() throws OversizedDataException, DataFormatException {
        // Setup
        byte[] originalData = new byte[DATAGRAM_PACKET_SIZE];

        // Trigger
        byte[] compressedData = DatagramCompressor.compress(originalData);

        // Verify
        assertEquals(DATAGRAM_PACKET_SIZE, compressedData.length);

        // Trigger
        byte[] decompressedData = DatagramCompressor.decompress(compressedData);

        // Verify
        assertEquals(decompressedData.length, DATAGRAM_PACKET_SIZE);
    }

    @Test
    public void decompress_shouldThrowOversizedDataException_whenDataToBeCompressedIsLargerThanBuffer() {

        // Setup
        byte[] originalData = new byte[DATAGRAM_PACKET_SIZE * 5];
        Arrays.fill(originalData, (byte) 0xFF);

        // Trigger and Verify
        assertThrows(OversizedDataException.class, () -> DatagramCompressor.compress(originalData));
    }

    @Test
    public void testDecompressInvalidData() {
        // Setup
        byte[] invalidData = new byte[DATAGRAM_PACKET_SIZE];

        // Trigger and Verify
        assertThrows(DataFormatException.class, () -> DatagramCompressor.decompress(invalidData));
    }
}
