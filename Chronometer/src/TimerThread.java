class TimerThread extends Thread
{
    @Override
    public void run()
    {
        int seconds = 0;
        while (true)
        {
            try
            {
                Thread.sleep(1000);
                seconds++;
                System.out.println("Time: " + seconds + "s");
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}