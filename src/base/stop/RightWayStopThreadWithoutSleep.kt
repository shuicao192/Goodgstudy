package base.stop

class RightWayStopThreadWithoutSleep : Runnable {
    override fun run() {
        var num = 0
        while (!Thread.currentThread().isInterrupted && num <= Int.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                println(num.toString() + "是10000的倍数")
            }
            num++
        }
        println("任务运行结束了")
    }

    companion object {
        @Throws(InterruptedException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val thread = Thread(RightWayStopThreadWithoutSleep())
            thread.start()
            Thread.sleep(20)
            thread.interrupt()
        }
    }
}