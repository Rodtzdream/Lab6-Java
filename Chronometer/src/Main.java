public class Main
{
    public static void main(String[] args)
    {
        TimerThread timerThread = new TimerThread();
        timerThread.start();

        MessageThread messageThread5 = new MessageThread(5);
        messageThread5.start();

        MessageThread messageThread10 = new MessageThread(10);
        messageThread10.start();

        MessageThread messageThread15 = new MessageThread(15);
        messageThread15.start();

        MessageThread messageThread20 = new MessageThread(20);
        messageThread20.start();
    }
}