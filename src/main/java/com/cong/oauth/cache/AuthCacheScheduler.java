package com.cong.oauth.cache;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 身份验证缓存调度程序
 *
 * @author cong
 * @date 2024/04/22
 */
public enum AuthCacheScheduler {

    /**
     * 实例
     */
    INSTANCE;

    private final AtomicInteger cacheTaskNumber = new AtomicInteger(1);

    private ScheduledExecutorService scheduler;
    AuthCacheScheduler() {
        create();
    }

    private void create() {
        this.shutdown();
        this.scheduler = new ScheduledThreadPoolExecutor(10, r -> new Thread(r, String.format("OneAuth-Task-%s", cacheTaskNumber.getAndIncrement())));
    }

    public void shutdown() {
        if (null != scheduler) {
            this.scheduler.shutdown();
        }
    }

    public void schedule(Runnable task, long delay) {
        this.scheduler.scheduleAtFixedRate(task, delay, delay, TimeUnit.MILLISECONDS);
    }
}
