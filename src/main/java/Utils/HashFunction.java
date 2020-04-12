package Utils;

import Utils.Utils;

public class HashFunction {
    /*Based on Prime multiplicative hashing using the jeff Ericskson hashing paper*/

    private int a; //Random value belonging to ]0;p[
    private int b; //Random value belonging to [0;p[
    private int p; //Next prime number after m
    private int m; //Size

    public HashFunction(int m) {
        //Let's init the hashfunction
        this.m = m;
        p =  Utils.nextPrime(m);
        a = Utils.generateRandomIntIntRange(1,p-1);
        b = Utils.generateRandomIntIntRange(0,p-1);

        //System.out.println("Function : " + "(("+ a + "*x+"+ b + ")%"+ p + ")%"+m);
    }

    public int getIndex(int val)
    {
        return (int)(((long) a*val+b)%p)%m;
    }



}
