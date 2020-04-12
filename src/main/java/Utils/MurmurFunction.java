package Utils;

import static Utils.MurmurHash.hash32;
import static Utils.MurmurHash.hash64;
import static Utils.Utils.intToBytes;

public class MurmurFunction {

    private int seed;
    private int size;

    public MurmurFunction(int size) {
        this.size = size;
        seed = Utils.generateRandomIntIntRange(0,(int) Math.pow(2,31)-1);
    }

    public int getIndex32(int val)
    {
        byte[] test = intToBytes(val);
        long hashed = hash32(test,4,seed);
        return Math.floorMod(hashed,size);
    }


}
