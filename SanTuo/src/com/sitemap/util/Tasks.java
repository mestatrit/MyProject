package com.sitemap.util;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * + * 定时任务服务的友好封装 + * +
 */
public abstract class Tasks {
	// 缺省的定时任务线程池大小，默认10个应该够了吧？
	private static final int DefaultPoolSize = 10;
	private static ScheduledThreadPoolExecutor taskScheduler = new ScheduledThreadPoolExecutor(DefaultPoolSize);

	/**
	 * 立即启动，并以固定的频率来运行任务。后续任务的启动时间不受前次任务延时影响。
	 * 
	 * @param task
	 *            具体待执行的任务
	 * @param period
	 *            每次执行任务的间隔时间(单位秒)
	 */
	public static ScheduledFuture<?> scheduleAtFixedRate(Runnable task, int periodSeconds) {
		return scheduleAtFixedRate(task, 0, periodSeconds, TimeUnit.SECONDS);
	}

	/**
	 * 在指定的延时之后开始以固定的频率来运行任务。后续任务的启动时间不受前次任务延时影响。
	 * 
	 * @param task
	 *            具体待执行的任务
	 * @param initialDelay
	 *            首次执行任务的延时时间
	 * @param periodSeconds
	 *            每次执行任务的间隔时间(单位秒)
	 * @param unit
	 *            时间单位
	 */
	public static ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long initialDelay, int period, TimeUnit unit) {
		return taskScheduler.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
	}

	/**
	 * 在指定的时间点开始以固定的频率运行任务。后续任务的启动时间不受前次任务延时影响。
	 * 
	 * @param task
	 *            具体待执行的任务
	 * @param startTime
	 *            首次运行的时间点,支持 "yyyy-MM-dd HH:mm:ss" 格式
	 * @param period
	 *            每次执行任务的间隔时间
	 * @param unit
	 *            时间单位
	 * @throws Exception
	 */
	public static void scheduleAtFixedRate(Runnable task, String startTime, int period, TimeUnit unit) throws Exception {
		Date dt = TimeUtil.formatDate(startTime, "yyyy-MM-dd HH:mm:ss");
		scheduleAtFixedRate(task, dt, period, unit);
	}

	/**
	 * 在指定的时间点开始以固定的频率运行任务。后续任务的启动时间不受前次任务延时影响。
	 * 
	 * @param task
	 *            具体待执行的任务
	 * @param startTime
	 *            首次运行的时间点
	 * @param period
	 *            每次执行任务的间隔时间
	 * @param unit
	 *            时间单位
	 */
	public static void scheduleAtFixedRate(final Runnable task, Date startTime, final int period, final TimeUnit unit) {
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				taskScheduler.scheduleAtFixedRate(task, 0, period, unit);
				timer.cancel();
			}
		}, startTime);
	}

	/**
	 * 立即启动，两次任务间保持固定的时间间隔
	 * 
	 * @param task
	 *            具体待执行的任务
	 * @param period
	 *            两次任务的间隔时间(单位秒)
	 */
	public static ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, int periodSeconds) {
		return scheduleWithFixedDelay(task, 0, periodSeconds, TimeUnit.SECONDS);
	}

	/**
	 * 在指定的延时之后启动，两次任务间保持固定的时间间隔
	 * 
	 * @param task
	 *            具体待执行的任务
	 * @param initialDelay
	 *            首次执行任务的延时时间
	 * @param period
	 *            两次任务的间隔时间(单位秒)
	 * @param unit
	 *            时间单位
	 */
	public static ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long initialDelay, int period, TimeUnit unit) {
		return taskScheduler.scheduleWithFixedDelay(task, initialDelay, period, TimeUnit.SECONDS);
	}

	/**
	 * 在指定的时间点启动，两次任务间保持固定的时间间隔
	 * 
	 * @param task
	 *            具体待执行的任务
	 * @param startTime
	 *            首次运行的时间点,支持 "yyyy-MM-dd HH:mm:ss" 格式
	 * @param period
	 *            两次任务的间隔时间
	 * @param unit
	 *            时间单位
	 * @throws Exception
	 */
	public static void scheduleWithFixedDelay(Runnable task, String startTime, int period, TimeUnit unit)
			throws Exception {
		Date dt = TimeUtil.formatDate(startTime, "yyyy-MM-dd HH:mm:ss");
		scheduleWithFixedDelay(task, dt, period, unit);
	}
	/**
	 * @param task 执行的任务
	 * @param startTime 开始的时间 HH:mm:ss
	 * @param period 任务间隔（天）
	 * @throws Exception
	 */
	public static void startDayForTime(Runnable task, String startTime, int period)throws Exception{
		String time=TimeUtil.getFormatDate("yyyy-MM-dd ")+startTime;
		System.out.println("定时任务开启 ===》 "+time+"间隔"+period +"小时");
		Date dt = TimeUtil.formatDate(TimeUtil.getFormatDate("yyyy-MM-dd ")+startTime, "yyyy-MM-dd HH:mm:ss");
		scheduleWithFixedDelay(task, dt, period, TimeUnit.HOURS);
	}
	

	/**
	 * 在指定的时间点启动，两次任务间保持固定的时间间隔
	 * 
	 * @param task
	 *            具体待执行的任务
	 * @param startTime
	 *            首次运行的时间点
	 * @param period
	 *            两次任务的间隔时间
	 * @param unit
	 *            时间单位
	 */
	public static void scheduleWithFixedDelay(final Runnable task, Date startTime, final int period, final TimeUnit unit) {
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				taskScheduler.scheduleWithFixedDelay(task, 0, period, unit);
				timer.cancel();
			}
		}, startTime);
	}

	/**
	 * 调整线程池大小
	 * 
	 * @param threadPoolSize
	 */
	public static void resizeThreadPool(int threadPoolSize) {
		taskScheduler.setCorePoolSize(threadPoolSize);
	}

	/**
	 * 返回定时任务线程池，可做更高级的应用
	 * 
	 * @return
	 */
	public static ScheduledThreadPoolExecutor getTaskScheduler() {
		return taskScheduler;
	}

	/**
	 * 关闭定时任务服务
	 * <p>
	 * 系统关闭时可调用此方法终止正在执行的定时任务，一旦关闭后不允许再向线程池中添加任务，
	 * 否则会报RejectedExecutionException异常
	 * </p>
	 */
	public static void depose() {
		List<Runnable> awaitingExecution = taskScheduler.shutdownNow();
		System.out.println("Tasks stopping. Tasks awaiting execution: %d " + awaitingExecution.size());
	}

	/**
	 * 重启动定时任务服务
	 */
	public static void reset() {
		depose();
		taskScheduler = new ScheduledThreadPoolExecutor(DefaultPoolSize);
	}

	public static void main(String[] args) throws Exception {
		TimerTask task=new TimerTask() {
			public void run() {
				System.out.println("开始任务！！！！！！！！");
			};
			
		};
		Tasks.startDayForTime(task, "9:26:50", 1);
		//Tasks.scheduleAtFixedRate(task, "2014-09-24 9:16:35", 12,TimeUnit.HOURS);
	}
}