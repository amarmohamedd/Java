import java.util.*;

class Job implements Comparable<Job> {
    int id;
    int priority;
    int duration;
    int arrivalTime;
    int remainingTime;

    public Job(int id, int priority, int duration, int arrivalTime) {
        this.id = id;
        this.priority = priority;
        this.duration = duration;
        this.arrivalTime = arrivalTime;
        this.remainingTime = duration;
    }

    @Override
    public int compareTo(Job other) {
        if (this.priority != other.priority) {
            return other.priority - this.priority; // Highest priority first
        }
        if (this.arrivalTime != other.arrivalTime) {
            return this.arrivalTime - other.arrivalTime; // Earlier arrival first (FCFS)
        }
        return this.remainingTime - other.remainingTime; // Shortest remaining time first
    }
}

public class CPUScheduler {
    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(1, 5, 10, 0));
        jobs.add(new Job(2, 3, 15, 0));

        PriorityQueue<Job> pq = new PriorityQueue<>(jobs);

        int timeSlice = 0;

        while (!pq.isEmpty()) {
            Job job = pq.poll();

            System.out.println("System time [" + timeSlice + "] - job " + job.id + " is running.");

            job.remainingTime--;
            timeSlice++;

            if (job.remainingTime > 0) {
                pq.offer(job);
            }
        }
    }
}
