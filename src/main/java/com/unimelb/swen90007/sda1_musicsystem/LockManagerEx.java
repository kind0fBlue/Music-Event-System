package com.unimelb.swen90007.sda1_musicsystem;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LockManagerEx {
    private static LockManagerEx instance;

    //Key: lockable
    //Value: owner
    private ConcurrentMap<Integer, String> lockMap;
    private ConcurrentMap<Integer, String> orderLockMap;

    public static synchronized LockManagerEx getInstance() {
        if(instance == null) {
            instance = new LockManagerEx();
        }
        return instance;
    }

    private LockManagerEx() {
        lockMap = new ConcurrentHashMap<Integer, String>();
        orderLockMap = new ConcurrentHashMap<>();
    }

    public void acquireLock(Integer lockable, String owner) {
        if(!lockMap.containsKey(lockable)) {
            //no lock on lockable, grant lock
            lockMap.put(lockable, owner);

        } else {
            throw new RuntimeException("Concurrency exception, " + owner + " could not acquire lock for " + lockable);

        }
    }

    public void acquireOrderLock(Integer lockable, String owner) {
        if(!orderLockMap.containsKey(lockable)) {
            //no lock on lockable, grant lock
            orderLockMap.put(lockable, owner);
        } else {
            throw new RuntimeException("Concurrency exception, " + owner + " could not acquire lock for " + lockable);
        }
    }

    public void releaseLock(Integer lockable, String owner) {
        lockMap.remove(lockable);
    }
    public void releaseOrderLck(Integer lockable, String owner) {
        orderLockMap.remove(lockable);
    }
}
