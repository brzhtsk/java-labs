package task2;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DecodeOutputStream extends FilterOutputStream {
    private final Decoder decoder;
    private static final Logger logger = LogManager.getLogger(DecodeOutputStream.class);

    public DecodeOutputStream(OutputStream out, Decoder decoder) {
        super(out);
        this.decoder = decoder;
        logger.info("DecodeOutputStream initialized");
    }

    @Override
    public void write(byte[] b) throws IOException {
        logger.debug("Decoding and writing {} bytes", b.length);
        try {
            byte[] decoded = decoder.decode(b);
            super.write(decoded);
            logger.info("Successfully wrote decoded data");
        } catch (IOException e) {
            logger.error("Error during decoding/write", e);
            throw e;
        }
    }
}