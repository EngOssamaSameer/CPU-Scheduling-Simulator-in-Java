package com.mycompany.osprojectgeneral;

/**
 *
 * @author Eng Ossama Samir 
 */
import java.util.*;

public class Scheduler {

    // First-Come-First-Served (FCFS) Scheduling
    public void executeFCFS(List<Process> processes) {
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
        int currentTime = 0;

        for (Process process : processes) {
            process.setStartTime(currentTime);
            int waitTime = Math.max(0, currentTime - process.getArrivalTime());
            process.setWaitingTime(waitTime);
            currentTime += process.getBurstTime();
            process.setEndTime(currentTime);
            process.setTurnaroundTime(process.getWaitingTime() + process.getBurstTime());
        }
    }
    int currentTime = 0;
    // Shortest Job First (SJF) Scheduling
    public void executeSJF(List<Process> processes) {
        List<Process> readyQueue = new ArrayList<>();


        while (!processes.isEmpty() || !readyQueue.isEmpty()) {
            processes.removeIf(p -> {
                if (p.getArrivalTime() <= currentTime) {
                    readyQueue.add(p);
                    return true;
                }
                return false;
            });

            readyQueue.sort(Comparator.comparingInt(Process::getBurstTime));
            if (!readyQueue.isEmpty()) {
                Process shortestJob = readyQueue.remove(0);
                shortestJob.setStartTime(currentTime);
                shortestJob.setWaitingTime(currentTime - shortestJob.getArrivalTime());
                currentTime += shortestJob.getBurstTime();
                shortestJob.setEndTime(currentTime);
                shortestJob.setTurnaroundTime(shortestJob.getWaitingTime() + shortestJob.getBurstTime());
            } else {
                currentTime++;
            }
        }
    }

    // Round Robin Scheduling
    public void executeRoundRobin(List<Process> processes, int timeQuantum) {
        Queue<Process> readyQueue = new LinkedList<>(processes);
        int currentTime = 0;

        while (!readyQueue.isEmpty()) {
            Process process = readyQueue.poll();

            if (process.getRemainingTime() > timeQuantum) {
                if (process.getStartTime() == 0) process.setStartTime(currentTime);
                currentTime += timeQuantum;
                process.setRemainingTime(process.getRemainingTime() - timeQuantum);
                readyQueue.add(process);
            } else {
                if (process.getStartTime() == 0) process.setStartTime(currentTime);
                currentTime += process.getRemainingTime();
                process.setEndTime(currentTime);
                process.setRemainingTime(0);
                process.setWaitingTime(currentTime - process.getArrivalTime() - process.getBurstTime());
                process.setTurnaroundTime(process.getWaitingTime() + process.getBurstTime());
            }
        }
    }
    
    public void executeMultilevelQueue(List<Process> processes, int timeThreshold) {
    Queue<Process> queue1 = new PriorityQueue<>(Comparator.comparingInt(Process::getPriority));
    Queue<Process> queue2 = new PriorityQueue<>(Comparator.comparingInt(Process::getPriority));
    List<Process> queue3 = new ArrayList<>();

    int currentTime = 0;

    // Place processes in appropriate queues based on priority
    for (Process process : processes) {
        if (process.getPriority() == 1) {
            process.setCurrentQueueLevel(1);
            queue1.add(process);
        } else if (process.getPriority() == 2) {
            process.setCurrentQueueLevel(2);
            queue2.add(process);
        } else {
            process.setCurrentQueueLevel(3);
            queue3.add(process);
        }
    }

    while (!queue1.isEmpty() || !queue2.isEmpty() || !queue3.isEmpty()) {
        // Priority scheduling for Queue 1
        if (!queue1.isEmpty()) {
            Process process = queue1.poll();
            process.setStartTime(currentTime);
            process.setWaitingTime(currentTime - process.getArrivalTime());
            currentTime += process.getBurstTime();
            process.setEndTime(currentTime);
            process.setTurnaroundTime(process.getWaitingTime() + process.getBurstTime());
        } 
        // Priority scheduling for Queue 2
        else if (!queue2.isEmpty()) {
            Process process = queue2.poll();
            process.setStartTime(currentTime);
            process.setWaitingTime(currentTime - process.getArrivalTime());
            currentTime += process.getBurstTime();
            process.setEndTime(currentTime);
            process.setTurnaroundTime(process.getWaitingTime() + process.getBurstTime());
        } 
        // SJF for Queue 3 with time threshold check
        else if (!queue3.isEmpty()) {
            Process shortestJob = queue3.stream().min(Comparator.comparingInt(Process::getBurstTime)).orElse(null);
            if (shortestJob != null) {
                if (currentTime - shortestJob.getArrivalTime() >= timeThreshold) {
                    queue3.remove(shortestJob);
                    shortestJob.setCurrentQueueLevel(2);
                    queue2.add(shortestJob);  // Promote process to Queue 2
                } else {
                    shortestJob.setStartTime(currentTime);
                    shortestJob.setWaitingTime(currentTime - shortestJob.getArrivalTime());
                    currentTime += shortestJob.getBurstTime();
                    shortestJob.setEndTime(currentTime);
                    shortestJob.setTurnaroundTime(shortestJob.getWaitingTime() + shortestJob.getBurstTime());
                    queue3.remove(shortestJob);
                }
            }
        } else {
            currentTime++;  // Idle time if no process is available
        }
    }
}

}
