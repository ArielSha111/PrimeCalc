public class PrimeThread extends Thread
{
    private PrimeController _controller;
    private int _num;

    /**
     * A constractor that creates a thread and give the global values controller and num needed values
     *

     */
    public PrimeThread(PrimeController controller)
    {       
        _controller = controller;
        synchronized (this)
        {
            _num = _controller.getCurrentNum();   
            _controller.nextNum();
        }        
    }

    /**
     * this method is overriding of thr run method its being called when ever start() is hopping
     * this method checks if the global num is a prime
     *
     */
    public void run()
    {        
        long _number;   
        long sqr_of_number=(long)Math.sqrt(_num)+1;
        for (long i=3;i<=sqr_of_number;i+=2) 
        {
            if(_num % i==0)
            {
                synchronized (this)
                {
                    _controller.finished();
                    return;
                }
            }
        }
        synchronized (this)
        {
            _controller.addPrime(_num);
            _controller.finished();     
        }
    }

}
