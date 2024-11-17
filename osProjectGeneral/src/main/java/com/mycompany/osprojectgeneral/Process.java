package com.mycompany.osprojectgeneral;

/**
 *
 * @author Eng Ossama Samir 
 */

public class Process {
    private int processID;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int waitingTime;
    private int turnaroundTime;
    private int startTime;
    private int endTime;
    private int remainingTime;
    private int currentQueueLevel;
    
    // Getter and Setter for currentQueueLevel



    // Constructor
    public Process(int processID, int arrivalTime, int burstTime, int priority) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
    }

    // Getters and Setters
    public int getProcessID() { return processID; }
    public int getArrivalTime() { return arrivalTime; }
    public int getBurstTime() { return burstTime; }
    public int getPriority() { return priority; }
    public int getWaitingTime() { return waitingTime; }
    public int getTurnaroundTime() { return turnaroundTime; }
    public int getStartTime() { return startTime; }
    public int getEndTime() { return endTime; }
    public int getRemainingTime() { return remainingTime; }
    public int getCurrentQueueLevel() { return currentQueueLevel; }
    
    
    public void setWaitingTime(int waitingTime) { this.waitingTime = waitingTime; }
    public void setTurnaroundTime(int turnaroundTime) { this.turnaroundTime = turnaroundTime; }
    public void setStartTime(int startTime) { this.startTime = startTime; }
    public void setEndTime(int endTime) { this.endTime = endTime; }
    public void setRemainingTime(int remainingTime) { this.remainingTime = remainingTime; }
    public void setCurrentQueueLevel(int level) { this.currentQueueLevel = level; }
}
