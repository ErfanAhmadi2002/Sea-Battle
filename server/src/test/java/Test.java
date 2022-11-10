import java.security.SecureRandom;

public class Test {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            byte[] bytes = secureRandom.generateSeed(5);
        }
    }
}
