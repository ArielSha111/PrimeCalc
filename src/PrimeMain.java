public class PrimeMain
{
    private static final int NUM_OF_THREADS = 10;
    private static final int NUMS_LIMIT = 1000;
    public static void main(String[]args)
    {
        int n = NUM_OF_THREADS, m = NUMS_LIMIT;
        primesCalc p_c = new primesCalc();      
        String result = p_c.getPrimes(n,m);
        System.out.println(result);
    }
}

