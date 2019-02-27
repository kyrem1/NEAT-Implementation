import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    void testSigmoid() {
        float[] xs = {0, 1, 5, 7};
        for(float x : xs) {
            assertEquals((float)1 / (1 + Math.exp(x)), Util.sigmoid(x));
        }
    }


}