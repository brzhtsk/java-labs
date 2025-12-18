package task2;

public class KeyEncoder implements Encoder {
    private final char key;

    public KeyEncoder(char key) {
        this.key = key;
    }

    @Override
    public byte[] encode(byte[] input) {
        byte[] result = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = (byte) (input[i] + (byte) key);
        }
        return result;
    }
}