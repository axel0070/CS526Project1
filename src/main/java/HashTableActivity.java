import HashTable.HashTable;

import java.util.Random;

public class HashTableActivity {

    static HashTable table;
    static int size;


    public static void main(String[] args)
    {


        size = (int) Math.pow(2,16);


        table = new HashTable(size);

        System.out.println("--Load hashtable--");
        loadTable(1);

        System.out.println("--LookUp hashtable--");

        lookUpTable((int) Math.pow(2,10));


    }


    public static void loadTable(float factor)
    {
        int nbOfElement = (int) (size*factor);
        long timediff = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for(int i = 0; i < nbOfElement;i++)
        {
            int obj = generateRandomIntIntRange(0,(int) Math.pow(2,16));
            //We start measuring the lime elapsed
            long startTime = System.nanoTime();
            //We insert the element
            table.insert(obj);
            //We stop the timer
            long endTime = System.nanoTime();

            long timeElapsed = endTime - startTime;

            if(timeElapsed<min)
                min = timeElapsed;

            if(timeElapsed>max)
                max = timeElapsed;

            timediff+=timeElapsed;
        }

        float average = (float) timediff/nbOfElement;
        System.out.println("[Insert] [factor: " + factor + "] Execution time in nanoseconds  : Average: " + average + " Min: " + min + " Max: " + max);
    }

    public static void lookUpTable(int nbOfLook)
    {
        long timediff = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for(int i = 0; i < nbOfLook;i++)
        {
            int obj = generateRandomIntIntRange(0,(int) Math.pow(2,20));
            //We start measuring the lime elapsed
            long startTime = System.nanoTime();
            //We insert the element
            table.lookup(obj);
            //We stop the timer
            long endTime = System.nanoTime();

            long timeElapsed = endTime - startTime;

            //Check min
            if(timeElapsed<min)
                min = timeElapsed;

            //Check max
            if(timeElapsed>max)
                max = timeElapsed;

            //Add to average
            timediff+=timeElapsed;
        }

        float average = (float) timediff/nbOfLook;
        System.out.println("[LookUp] [Number of lookUp : "+ nbOfLook + "] Execution time in nanoseconds  : Average: " + average + " Min: " + min + " Max: " + max);
    }


    //Ref https://dzone.com/articles/random-number-generation-in-java
    public static int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
