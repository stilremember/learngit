package org.springside.modules.utils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ThreadUtils {
    public ThreadUtils() {
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException var3) {
            ;
        }

    }

    public static void gracefulShutdown(ExecutorService pool, int shutdownTimeout, int shutdownNowTimeout, TimeUnit timeUnit) {
        pool.shutdown();

        try {
            if(!pool.awaitTermination((long)shutdownTimeout, timeUnit)) {
                pool.shutdownNow();
                if(!pool.awaitTermination((long)shutdownNowTimeout, timeUnit)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException var5) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }

    }

    public static void normalShutdown(ExecutorService pool, int timeout, TimeUnit timeUnit) {
        try {
            pool.shutdownNow();
            if(!pool.awaitTermination((long)timeout, timeUnit)) {
                System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException var4) {
            Thread.currentThread().interrupt();
        }

    }

    public static class CustomizableThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        public CustomizableThreadFactory(String poolName) {
            this.namePrefix = poolName + "-pool-";
        }

        public Thread newThread(Runnable runable) {
            return new Thread(runable, this.namePrefix + this.threadNumber.getAndIncrement());
        }
    }
}
