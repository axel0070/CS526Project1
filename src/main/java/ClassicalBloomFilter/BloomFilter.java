package ClassicalBloomFilter;

import Utils.MurmurFunction;

public class BloomFilter {

    private boolean[] data;
    private MurmurFunction[] functions;

    public BloomFilter(int size,int numberOfFunction) {

        data = new boolean[size];
        functions = new MurmurFunction[numberOfFunction];
        for(int j = 0; j< numberOfFunction; j++ )
            functions[j] = new MurmurFunction(size);

        for(int i = 0; i < size; i++)
            data[i] = false;
    }

    public void insert(int obj)
    {
        for (MurmurFunction function : functions)
            data[function.getIndex32(obj)] = true;

    }

    public boolean lookUp(int obj)
    {
        for (MurmurFunction function : functions)
            if (!data[function.getIndex32(obj)])
                return false;

        return true;
    }


}
