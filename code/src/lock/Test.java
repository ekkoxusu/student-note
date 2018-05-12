package lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    //锁
    public Lock myLock = new ReentrantLock();
    //条件
    Condition condition = myLock.newCondition();
    //记录正在执行的
    Map<Thread,TaskConditions> map = new ConcurrentHashMap<>();
    //单例
    static Test t = new Test();

    public static void main(String[] args) {
        TaskConditions one = new TaskConditions(1, 1, "第一个");
        new ThreadExt(one).run();
        TaskConditions two = new TaskConditions(1, 1, "第一个");
        new ThreadExt(two).run();
        TaskConditions three = new TaskConditions(1, 1, "第一个");
        new ThreadExt(three).run();
        TaskConditions four = new TaskConditions(1, 1, "第一个");
        new ThreadExt(four).run();
        TaskConditions five = new TaskConditions(1, 1, "第一个");
        new ThreadExt(five).run();
    }

    public void testMethod(TaskConditions taskConditions) throws Exception{
        myLock.lock();
        try {
            while (have(taskConditions)){
                System.out.println("当前进入的"+taskConditions+"阻塞操作");
                condition.await();
            }
            //业务逻辑
            map.put(Thread.currentThread(),taskConditions);
            System.out.println("当前进入的"+taskConditions+"正常操作"+have(taskConditions));
            Thread.sleep(5000);
        } finally {
            myLock.unlock();
            map.remove(Thread.currentThread());
        }

    }

    public boolean have(TaskConditions taskConditions){
        for (Map.Entry e : map.entrySet()){
            if(taskConditions.equals(e.getValue())){
                return true;
            }
        }
        return false;
    }


}
class ThreadExt implements Runnable{
    private TaskConditions taskConditions;

    public ThreadExt(TaskConditions taskConditions) {
        this.taskConditions = taskConditions;
    }

    @Override
    public void run() {
        try {
            Test.t.testMethod(taskConditions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}