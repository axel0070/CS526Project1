package HashTable;

import Utils.HashFunction;

public class HashTable {

    private int size;
    private int[] data;
    private HashFunction function;

    public HashTable(int size) {
        this.size = size;
        data = new int[size];
        function = new HashFunction(size);

        //Init data to -1
        for(int i = 0; i < size; i++)
            data[i] = -1;
    }

    //Using Linear probing while we are not in an empty space we add one to index
    //Best cas O(1) wrost O(n)
    public void insert(int obj)
    {
        int index = function.getIndex(obj);
        boolean run = true;
        while(run)
        {
            if(data[index] == -1)
            {
                data[index] = obj;
                run = false;
            }
            else
            {
                index++;
                if(index>=size)
                    index=0;
            }
        }
    }

    //While we haven't the one we search OR an empty space we keep looking
    public boolean lookup(int obj)
    {
        int index = function.getIndex(obj);
        int loopCheck = index;

        while(true)
        {
            if(data[index] == -1)
                return false;
            else if(data[index] == (obj))
                return true;

            index++;
            if(index>=size)
                index=0;

            if(index == loopCheck)
                break;
        }

        return false;

    }
}
