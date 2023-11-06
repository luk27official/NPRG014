class Counter {
    static long counter = 0
    private static Object lock = new Object();

    Counter() {
        synchronized(lock) {
            counter++;
        }
    }
}

def pool = java.util.concurrent.Executors.newFixedThreadPool(8)
(1..50).collect {
    pool.submit {
        new Counter()
    }
}.collect {
    it.get()
}

println Counter.counter
// kill the pool
pool.shutdown()

//TASK Properly synchronize
//TASK Replace thread creation with a thread pool (e.g. using java.util.concurrent.Executors.newFixedThreadPool(8))
