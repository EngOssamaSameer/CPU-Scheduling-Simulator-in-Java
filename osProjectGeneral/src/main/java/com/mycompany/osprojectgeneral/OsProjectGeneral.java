package com.mycompany.osprojectgeneral;

import java.util.Scanner;

/**
 *
 * @author Eng Ossama Samir 
 */
public class OsProjectGeneral {
    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of processes:");
        int numProcesses = scanner.nextInt();
        simulator.createProcesses(numProcesses);

        System.out.println("Choose scheduling algorithm:");
        System.out.println("1. First-Come-First-Served (FCFS)");
        System.out.println("2. Shortest Job First (SJF)");
        System.out.println("3. Round Robin");
        System.out.println("4. Multilevel Queue Scheduling");

        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1 -> {
                System.out.println("Simulating FCFS...");
                simulator.simulateFCFS();
            }
            case 2 -> {
                System.out.println("Simulating SJF...");
                simulator.simulateSJF();
            }
            case 3 -> {
                System.out.println("Enter time quantum:");
                int timeQuantum = scanner.nextInt();
                System.out.println("Simulating Round Robin with time quantum: " + timeQuantum);
                simulator.simulateRoundRobin(timeQuantum);
            }
            case 4 -> {
                System.out.println("Enter time threshold for starvation prevention:");
                int timeThreshold = scanner.nextInt();
                System.out.println("Simulating Multilevel Queue Scheduling with time threshold: " + timeThreshold);
                simulator.simulateMultilevelQueue(timeThreshold);
            }
            default -> System.out.println("Invalid choice. Please restart and select a valid scheduling algorithm.");
        }

        scanner.close();
    }
}
