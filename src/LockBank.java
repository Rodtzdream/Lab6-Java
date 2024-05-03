import java.util.Arrays;
import java.util.concurrent.locks.*;

public class LockBank extends Bank
{
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;

    public LockBank(int accountsNum, int accountBalance)
    {
        accounts = new double[accountsNum];
        Arrays.fill(accounts, accountBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount)
    {
        bankLock.lock();
        try
        {
            // якщо грошей недостатьо - чекаємо, поки вони зявляться
            while (accounts[from] < amount)
            {
                sufficientFunds.await();
            }
            accounts[from] -= amount;
            System.out.printf("%s makes transfer of %6.2f$ from account %d to account %d\n",
                    Thread.currentThread(), amount, from, to);
            accounts[to] += amount;
            System.out.printf("Total Balance: %10.2f$\n", getTotalBalance());
            sufficientFunds.signalAll();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Помилка при роботі з потоками!");
            ex.printStackTrace();
        }
        finally
        {
            bankLock.unlock();
        }
    }

    public double getTotalBalance()
    {
        bankLock.lock();
        try
        {
            double total = 0;
            for (double account : accounts) {
                total += account;
            }
            return total;
        }
        finally
        {
            bankLock.unlock();
        }
    }

    public int getSize()
    {
        return accounts.length;
    }
}