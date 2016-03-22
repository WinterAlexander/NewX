package me.winter.newx;


public abstract class Task implements Runnable
{
	private long delay;
	private long lastWork;
	private boolean repeating;
	private Scheduler scheduler;

	public Task(long delay, boolean repeating)
	{
		this.delay = delay;
		this.lastWork = System.nanoTime() / 1_000_000;
		this.repeating = repeating;
		this.scheduler = null;
	}
	
	public long getDelay() 
	{
		return this.delay;
	}
	
	public long getLastWork() 
	{
		return lastWork;
	}
	
	public void setLastWork(long lastWork) 
	{
		this.lastWork = lastWork;
	}

	public boolean isRepeating()
	{
		return repeating;
	}

	public void cancel()
	{
		register(null);
	}

	public void register(Scheduler scheduler)
	{
		if(this.scheduler != null)
			this.scheduler.cancel(this);

		this.scheduler = scheduler;
	}

	public Scheduler getScheduler()
	{
		return scheduler;
	}
}
