import java.util.Arrays;

public class SimpleBank extends Bank
{
    private final double[] accounts;

    public SimpleBank(int accountsNum, int accountBalance)
    {
        accounts = new double[accountsNum];
        Arrays.fill(accounts, accountBalance);
    }

    public void transfer(int from, int to, double amount)
    {
        if (accounts[from] < amount)
        {
            return;
        }
        // якщо грошей достатьо - здіснюємо переказ
        accounts[from] -= amount;
        System.out.printf("%s makes transfer of %6.2f$ from account %d to account %d\n",
                Thread.currentThread(), amount, from, to);
        accounts[to] += amount;
        System.out.printf("Total Balance: %10.2f$\n", getTotalBalance());
    }

    public double getTotalBalance()
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