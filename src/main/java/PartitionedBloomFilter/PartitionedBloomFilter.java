package PartitionedBloomFilter;

import Utils.HashFunction;
import Utils.MurmurFunction;

public class PartitionedBloomFilter {

    private boolean[] data;
    private MurmurFunction[] functions;

    private int partitionSize;
    private int overLoad;

    public PartitionedBloomFilter(int size, int numberOfFunction) {

        //Init arrays
        data = new boolean[size];
        functions = new MurmurFunction[numberOfFunction];

        partitionSize = size/numberOfFunction;
        overLoad = size%numberOfFunction;
        /*System.out.println("Each hash function will have a partition of " +
                partitionSize + "/" + numberOfFunction + " except the last one which will have " +  (partitionSize + overLoad));*/

        for(int j = 0; j<numberOfFunction-1;j++ )
            functions[j] = new MurmurFunction(partitionSize);

        functions[numberOfFunction-1] = new MurmurFunction(partitionSize+overLoad);

        //Init data to -1
        for(int i = 0; i < size; i++)
            data[i] = false;
    }

    public void insert(int obj)
    {
        for(int j = 0; j<functions.length-1;j++ )
            data[functions[j].getIndex32(obj)+partitionSize*j] = true;

        data[functions[functions.length-1].getIndex32(obj)+partitionSize*(functions.length-1)+overLoad] = true;
    }

    public boolean lookUp(int obj)
    {
        for(int j = 0; j<functions.length-1;j++ )
            if(!data[functions[j].getIndex32(obj)+partitionSize*j])
                return false;

        return data[functions[functions.length - 1].getIndex32(obj) + partitionSize * (functions.length - 1)];
    }
}
