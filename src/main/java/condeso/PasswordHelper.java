package condeso;

import java.util.Random;

public class PasswordHelper {

    public static String generateRandomPassword()
    {
        int leftLimit = 48; // number 0
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        while(buffer.length() <= targetStringLength) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            if ((58 > randomLimitedInt || randomLimitedInt > 64) && (91 > randomLimitedInt || randomLimitedInt > 96)) {
                buffer.append((char) randomLimitedInt);
            }

        }
        return buffer.toString();
    }
}
