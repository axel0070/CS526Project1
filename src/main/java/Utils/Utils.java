package Utils;

import java.nio.ByteBuffer;
import java.util.Random;

public class Utils {
    //Ref https://dzone.com/articles/random-number-generation-in-java
    public static int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    //Ref https://www.tutorialspoint.com/java-program-to-check-for-prime-and-find-next-prime-in-java
    public static int nextPrime(int num) {
        num++;
        for (int i = 2; i < num; i++) {
            if(num%i == 0) {
                num++;
                i=2;
            }
        }
        return num;
    }

    public static byte[] intToBytes( final int i ) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(i);
        return bb.array();
    }
}
