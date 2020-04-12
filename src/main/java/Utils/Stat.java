package Utils;
//Thanks https://coderforevers.com/java/java-program/calculate-standard-deviation/ for some functions
public class Stat {
    // Function for calculating variance
    public static double variance(double[] a, int n)
    {
        // Compute mean (average of elements)
        double sum = 0;

        for (int i = 0; i < n; i++)
            sum += a[i];
        double mean = (double)sum/(double)n;

        // Compute sum squared differences with
        // mean.
        double sqDiff = 0;
        for (int i = 0; i < n; i++)
            sqDiff += (a[i] - mean) * (a[i] - mean);

        return (double)sqDiff/n;
    }

    public static double standardDeviation(double[] arr, int n)
    {
        return Math.sqrt(variance(arr, n));
    }
    public static double mean(double[] arr,int n)
    {
        double val = 0;
        for (double elem:arr) {
            val+=elem;
        }

        return val/n;
    }

    public static double min(double[] arr)
    {
        double val = Double.MAX_VALUE;
        for (double elem:arr) {
            if(elem<val)
                val = elem;
        }
        return val;
    }

    public static double max(double[] arr)
    {
        double val = Double.MIN_VALUE;
        for (double elem:arr) {
            if(elem>val)
                val = elem;
        }
        return val;
    }
}
