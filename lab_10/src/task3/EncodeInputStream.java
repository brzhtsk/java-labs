package task3;

import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EncodeInputStream extends FilterInputStream {
    private final Encoder encoder;
    private static final Logger logger = LogManager.getLogger(EncodeInputStream.class);

    public EncodeInputStream(InputStream in, Encoder encoder) {
        super(in);
        this.encoder = encoder;
        logger.info("EncodeInputStream initialized with encoder");
    }

    public byte[] readAndEncode() throws IOException {
        logger.info("Starting read and encode operation");
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int bytesRead;

        try {
            while ((bytesRead = in.read(buffer)) != -1) {
                logger.debug("Read {} bytes from input stream", bytesRead);

                byte[] chunk = new byte[bytesRead];
                System.arraycopy(buffer, 0, chunk, 0, bytesRead);

                byte[] encodedChunk = encoder.encode(chunk);
                logger.debug("Encoded chunk size: {}", encodedChunk.length);

                baos.write(encodedChunk);
            }

            logger.info("Read and encode completed. Total encoded bytes: {}", baos.size());
            return baos.toByteArray();

        } catch (IOException e) {
            logger.error("IO error during readAndEncode", e);
            throw e;
        }
    }
}