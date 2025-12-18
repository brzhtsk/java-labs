package task3;

public class KeyDecoder implements Decoder {
    private final char key;

    public KeyDecoder(char key) {
        this.key = key;
    }

    @Override
    public byte[] decode(byte[] encoded) {
        byte[] result = new byte[encoded.length];
        for (int i = 0; i < encoded.length; i++) {
            result[i] = (byte) (encoded[i] - (byte) key);
        }
        return result;
    }
}