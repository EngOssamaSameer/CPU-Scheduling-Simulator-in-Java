package com.mycompany.osprojectgeneral;

/**
 *
 * @author Eng Ossama Samir 
 */
import java.util.*;

public class Simulator {
    private Scheduler scheduler = new Scheduler();
    private List<Process> processes = new ArrayList<>();

    public void createProcesses(int count) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < count; i++) {
            System.out.println("Enter arrival time, burst time, and priority for Process " + (i + 1));
            int arrival = scanner.nextInt();
            int burst = scanner.nextInt();
            int priority = scanner.nextInt();
            processes.add(new Process(i + 1, arrival, burst, priority));
        }
    }

    public void simulateFCFS() {
        scheduler.executeFCFS(processes);
        displayMetrics();
    }

    public void simulateSJF() {
        scheduler.executeSJF(processes);
        displayMetrics();
    }

    public void simulateRoundRobin(int timeQuantum) {
        scheduler.executeRoundRobin(processes, timeQuantum);
        displayMetrics();
    }
    
    public void simulateMultilevelQueue(int timeThreshold) {
    scheduler.executeMultilevelQueue(processes, timeThreshold);
    displayMetrics();
}


    private void displayMetrics() {
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        for (Process process : processes) {
            totalWaitingTime += process.getWaitingTime();
            totalTurnaroundTime += process.getTurnaroundTime();
            System.out.println("Process " + process.getProcessID() +
                               ": Waiting Time=" + process.getWaitingTime() +
                               ", Turnaround Time=" + process.getTurnaroundTime());
        }
        System.out.println("Average Waiting Time: " + (totalWaitingTime / processes.size()));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / processes.size()));
    }

    
}
