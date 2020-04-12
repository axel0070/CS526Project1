import ClassicalBloomFilter.BloomFilter;
import HashTable.HashTable;
import PartitionedBloomFilter.PartionedBloomFilter;

import java.util.ArrayList;
import java.util.Random;

import static Utils.MurmurHash.hash64;
import static Utils.Stat.*;
import static Utils.Utils.intToBytes;

public class BloomFilterActivity {

    //We have the two type of bloomfilter
    static PartionedBloomFilter PartionedBloomFilter;
    static BloomFilter bloomFilter;


    //Settings
    static int size = (int) Math.pow(2,16);
    static int n = 6228;
    static int k = 4;
    static int lookUp = 5000;


    static int loop = 10000;

    static HashTable table;

    public static void main(String[] args)
    {
        double[][] result = new double[2][loop];

        for(int i = 0; i < loop;i++)
        {
            initFilters();

            float[] result_ = operationsOnFilters();
            result[0][i]=result_[0];
            result[1][i]=result_[1];
        }



        System.out.println("--Result--\nNormal:"+mean(result[0],loop) + "% min:"+ min(result[0]) + "max:"+ max(result[0]) + " standardDeviation:"+ standardDeviation(result[0],loop)+"\nPartitioned:"+mean(result[1],loop) + "% min:"+ min(result[0]) + "max:"+ max(result[0]) + " standardDeviation:"+standardDeviation(result[1],loop));

    }

    private static void initFilters()
    {
        //System.out.println("--Creating BloomFilters--");
        PartionedBloomFilter = new PartionedBloomFilter(size,k);
        bloomFilter = new BloomFilter(size,k);
    }

    private static float[] operationsOnFilters()
    {
        //We want to evaluate the number of false positive, so we need to store
        //our object somewhere, hopefully we implemented a hashable previously that
        //we can reuse for this purpose.
        //System.out.println("--Creating hashtable--");
        table = new HashTable(size);

        //System.out.println("--Load BloomFilters--");
        loadBloomFilters(n);

        //System.out.println("--LookUp BloomFilters--");

        return lookUpBloomFilters(lookUp);
    }


    public static void loadBloomFilters(int nbOfElement)
    {

        for(int i = 0; i < nbOfElement;i++)
        {
            int obj = generateRandomIntIntRange(0,(int) Math.pow(2,16));
            //System.out.println("Elem: " + obj);
            //Saving this object into the hashtable
            table.insert(obj);
            //System.out.println("--");
            //Inserting this object in the bloom filters
            bloomFilter.insert(obj);
            PartionedBloomFilter.insert(obj);
        }

    }

    public static float[] lookUpBloomFilters(int numberOfLook)
    {
        int bloomFilterPositive = 0;
        int bloomFilterFalsePositive = 0;

        int PartionedBloomFilterFalsePositive = 0;
        int PartionedBloomFilterPositive = 0;

        for(int i = 0; i < numberOfLook;i++)
        {
            //Generating random object
            int obj = generateRandomIntIntRange(0,(int) Math.pow(2,16));
            //System.out.println("Elem: " + obj);

            //Checking only on time the hashtable
            boolean isInside = table.lookup(obj);

            if(bloomFilter.lookUp(obj))
                if(!isInside)
                    bloomFilterFalsePositive++;
                else
                    bloomFilterPositive++;

            if(PartionedBloomFilter.lookUp(obj))
                if(!isInside)
                    PartionedBloomFilterFalsePositive++;
                else
                    PartionedBloomFilterPositive++;
           // System.out.println("--");
        }

        float pourcentageNormal = ((float) bloomFilterFalsePositive/numberOfLook)*100;
        float pourcentagePartioned = ((float) PartionedBloomFilterFalsePositive/numberOfLook)*100;

        //System.out.println("[Normal] Right Positive: " + bloomFilterPositive + " | False positive: " + bloomFilterFalsePositive + " | false positive " + pourcentageNormal + "%");

        //System.out.println("[Partioned] Right Positive: " + PartionedBloomFilterPositive + " | False positive: " + PartionedBloomFilterFalsePositive + " | false positive " + pourcentagePartioned +"%");

        return new float[]{pourcentageNormal,pourcentagePartioned};
    }


    //Ref https://dzone.com/articles/random-number-generation-in-java
    public static int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
