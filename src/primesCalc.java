public class primesCalc
{
    /**
     * this method return the primes list in a nice format
     *
     * @param   n,  m
     * @return    String - the list of the primes printed
     */
    public String getPrimes(int n, int m)
    {
        PrimeController controller = new PrimeController (n,m);
        if(m==2)
        {
            controller.addPrime(2);
        }
        else if(m>2)
        {
            controller.addPrime(2);
            while(controller.getCurrentNum()<m)
            {           
                controller.waitForThread();
                (new PrimeThread(controller)).start();
            }
            controller.waitForAll();
            controller.sortPrimes();
        }
        return controller.toString();
    }
}
