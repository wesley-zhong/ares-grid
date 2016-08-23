package com.wd.ares;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        ReentrantLock  lock = new ReentrantLock();
        lock.lock();
        lock.unlock();

    }
}
