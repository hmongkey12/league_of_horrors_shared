package com.utilities;

import com.utilities.exceptions.OversizedDataException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Component
@NoArgsConstructor
public class DatagramCompressor {
    private final static int DATAGRAM_PACKET_SIZE = 16000;

    public static byte[] compress(byte[] data) throws OversizedDataException {
        if (data.length > DATAGRAM_PACKET_SIZE) {
            throw new OversizedDataException("Data is trying to compress into a smaller buffer.");
        } else {
            byte[] output = new byte[DATAGRAM_PACKET_SIZE];
            Deflater compresser = new Deflater();
            compresser.setInput(data);
            compresser.finish();
            compresser.deflate(output);
            compresser.end();
            return output;
        }
    }

    public static byte[] decompress(byte[] data) throws DataFormatException {
        byte[] output = new byte[DATAGRAM_PACKET_SIZE];
        Inflater decompresser = new Inflater();
        decompresser.setInput(data, 0, data.length);
        int sizeOfOriginal = decompresser.inflate(output);
        decompresser.end();
        byte[] newOutput = Arrays.copyOf(output, sizeOfOriginal);
        return newOutput;
    }
}
