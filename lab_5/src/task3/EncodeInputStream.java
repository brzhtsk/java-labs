package task3;

import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class EncodeInputStream extends FilterInputStream {
    private final Encoder encoder;

    public EncodeInputStream(InputStream in, Encoder encoder) {
        super(in);
        this.encoder = encoder;
    }

    public byte[] readAndEncode() throws IOException {
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            byte[] chunk = new byte[bytesRead];
            System.arraycopy(buffer, 0, chunk, 0, bytesRead);
            byte[] encodedChunk = encoder.encode(chunk);
            baos.write(encodedChunk);
        }
        return baos.toByteArray();
    }
}