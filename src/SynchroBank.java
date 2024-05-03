import java.util.Arrays;

public class SynchroBank extends Bank
{
    private final double[] accounts;

    public SynchroBank(int accountsNum, int accountBalance)
    {
        accounts = new double[accountsNum];
        Arrays.fill(accounts, accountBalance);
    }

    public synchronized void transfer(int from, int to, double amount)
    {
        try
        {
            // якщо грошей недостатьо - чекаємо, поки вони зявляться
            while (accounts[from] < amount)
            {
                wait();
            }
            accounts[from] -= amount;
            System.out.printf("%s makes transfer of %6.2f$ from account %d to account %d\n",
                    Thread.currentThread(), amount, from, to);
            accounts[to] += amount;
            notifyAll();
            System.out.printf("Total Balance: %10.2f$\n", getTotalBalance());
        }
        catch (InterruptedException ex)
        {
            System.out.println("Помилка при роботіз потоками!");
            ex.printStackTrace();
        }
    }

    public synchronized double getTotalBalance()
    {
        double total = 0;
        for (double account : accounts) {
            total += account;
        }
        return total;
    }

    public int getSize()
    {
        return accounts.length;
    }
}