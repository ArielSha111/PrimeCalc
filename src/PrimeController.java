import java.util.*; 
public class PrimeController
{
    private ArrayList<Long> _primes; 
    private int _num_of_threads, _activeThreads, _primes_limit, _currentNumber;
    private int amount_of_checked_numbs;

    /**
     * a parameters constractor for the prime controler
     * 
     * @param   num_of_threads,  primes_limit
     */
    public PrimeController(int num_of_threads, int primes_limit)
    {
        _num_of_threads = num_of_threads;
        _primes_limit = primes_limit;
        _activeThreads = 0;
        _currentNumber = 3;
        amount_of_checked_numbs = 3;
        _primes = new ArrayList<Long>();
    }

    /**
     * a synchronized method the responsible for waiting until a thread finish its work 
     * before it lunches another one, all in order to have no more the _num_of_threads threads running
     * 
     */
    public synchronized void waitForThread()
    {
        while(_activeThreads == _num_of_threads)//if there are 5 threads that use the cpu
        {
            try             
            {
                wait();//then wait until one of the thread is done
            }
            catch(InterruptedException e){}//catch an error that can accrue
        }
        _activeThreads++;//if you reached here then another thread is using the cpu at the moment
    }

    /**
     * a synchronized method that makes sure all the running threads have finished.
     * this method will be done only when all the threads have done
     * 
     */
    public synchronized void waitForAll()
    {
        while(amount_of_checked_numbs < _primes_limit)//while the amount of numbers you calculated is smaller then the amount you need too
        {
            try            
            {
                wait();//then wait until one of the thread is done
            }
            catch(InterruptedException e){}//catch an error that can accrue
        }
    }

    /**
     * a synchronized method that lets a thread call it and announce he just finish its job.
     * the method will let the program know about it bt change values and notifying
     */
    public synchronized void finished()
    {
        _activeThreads--;
        amount_of_checked_numbs +=2;//skips the even numbs
        notifyAll();
    }

    /**
     * this method give each thread a new number to be choked
     * 
     * @return   int the current number that needs to be checked
     */
    public int getCurrentNum()
    {
        return _currentNumber;
    }

    /**
     * this method gets called after a number is been called by get number method
     * in order to never have two threads working together
     * the method does it by adding 2 on each call to the global number to that needs be checked
     * 
     */
    public void nextNum()
    {
        _currentNumber+=2;
    }

    /**
     * a synchronized method to add a new prime to the prime list 
     * 
     * @param  num - the prime to be added
     */
    public synchronized void addPrime(long num)
    {
        _primes.add(num);
    }

    /**
     * a method that sorts the prime list
     * 
     */
    public void sortPrimes()
    {
        Collections.sort(_primes);
    }

    public String toString()
    {
        return _primes.toString();
    }

}

