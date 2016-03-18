package com.pool;

import java.util.concurrent.*;

/**
 * 异步执行类
 * 最后一定要shutdown 线程池
 *
 * @author jiawenfeng
 * @Date 2016/3/17 20:02
 */
public class SyncExcutor{

    private static final int corePoolSize = 10;
    private static final int maximumPoolSize = 30;
    private static final int CAPACITY = 10000;
    private static final int keepAliveTime= 3;

    // 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY)。
    public static final ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(CAPACITY));

    public Future excute(Callable task){
        if (null == task){
            return null;
        }
        return pool.submit(task);
    }

    /***
     * 得到异步执行结果
     * @param future
     * @param timeOut 超时设置  单位毫秒
     * @return
     * @throws RuntimeException
     */
    public Object getExcuteResult(Future future, long timeOut)throws RuntimeException{
        if (null == future){
            throw  new RuntimeException(this.getClass()+" Call Object future , 空指针错误");
        }
        try {
            return future.get(timeOut, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(this.getClass()+" 中断异常");
        } catch (ExecutionException e) {
            throw new RuntimeException(this.getClass()+" 执行异常");
        } catch (TimeoutException e) {
            throw new RuntimeException(this.getClass()+" 超时异常");
        }
    }

    /***
     * 得到异步执行结果 默认超时时间  10 秒
     * @param future
     * @return
     * @throws RuntimeException
     */
    public Object getExcuteResult(Future future)throws RuntimeException{
        return getExcuteResult(future, 10000L);
    }

    static class User{
        public Integer id;
        public String name;
    }

    public static void shutDown(){
        if (!pool.isShutdown()) {
            pool.shutdown();
        }
    }
    public static void main(String[] args) {
        SyncExcutor syncExcutor = new SyncExcutor();
        Future future = syncExcutor.excute(new Callable() {
            @Override
            public User call() throws Exception {
                User user = new User();
                user.id = 1;
                user.name = "张珊";
                Thread.sleep(3000);
                return user;
            }
        });
        try {
            User user = (User) syncExcutor.getExcuteResult(future);
            if (null == user) {
                System.out.println();
            } else {
                System.out.println(user.id + " " + user.name);
            }
        }catch (Exception e){
        }finally {
            shutDown();
        }
    }

}
