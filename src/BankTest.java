private static void BankTest()
{
    final int ACCOUNTS = 100;   // кількість рахунків в банку
    final int BALANCE = 1000;   // сукупний баланс коштів на рахунках

    Bank bank = new SimpleBank(ACCOUNTS, BALANCE);

    for (int i = 0; i < ACCOUNTS; i++)
    {
        Transfer transfer = new Transfer(bank, i, BALANCE);
        Thread thread = new Thread(transfer);
        thread.start();
    }
}

public void main()
{
    BankTest();
}