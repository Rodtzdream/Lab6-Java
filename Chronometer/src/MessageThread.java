class MessageThread extends Thread
{
    private final int interval;

    public MessageThread(int interval)
    {
        this.interval = interval;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(interval * 1000);
                System.out.println("Message every " + interval + " seconds");
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}