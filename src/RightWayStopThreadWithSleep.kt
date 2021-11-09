/**
 * 描述：     带有sleep的中断线程的写法
 */
object RightWayStopThreadWithSleep {
    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val runnable = Runnable {
            var num = 0
            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted) {
                    if (num % 100 == 0) {
                        println(num.toString() + "是100的倍数")
                    }
                    num++
                }
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        val thread = Thread(runnable)
        thread.start()
        Thread.sleep(500)
        thread.interrupt()
    }
}