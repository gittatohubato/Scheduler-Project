package sample;

import java.util.ArrayList;

public class Process {

    /*
        -Description:
        *time is the time of process input by the user , priority is the priority of every process
        *remaining_Time is the remaining time of the process after execution and goes out of CPU Initialized = time
        *start is the start time that process get in cpu , end is the time that the process get out of cpu
        *arrival_Time is the arrival time of every process , process_Number is the number of the process
        *is_Arrived is to check if the process ready or not
     */

    private int time , remaining_Time , priority ;
    float waitingTime;
    private int arrival_TIme;
    private String process_Number;
    private boolean is_Arrived = false;
    private ArrayList<Integer> start = new ArrayList<>();
    private ArrayList<Integer> end = new ArrayList<>();

    public Process(int time, int arrival_TIme, String process_Number) { //For SJF
        this.time = time;
        this.arrival_TIme = arrival_TIme;
        this.process_Number = process_Number;
        remaining_Time=time;
    }

    public Process(int time, int priority, int arrival_TIme, String process_Number) { //For Priority
        this.time = time;
        this.remaining_Time = time;
        this.priority = priority;
        this.arrival_TIme = arrival_TIme;
        this.process_Number = process_Number;
    }

    public int getStart() {
        int start = this.start.get(0);
        this.start.remove(0);
        return start;
    }

    public void setStart(int start) {
        this.start.add(start);
    }

    public int getEnd() {

        int end = this.end.get(0);
        this.end.remove(0);
        return end;
    }

    public int getEndCopy() {

        return end.get(end.size()-1);
    }

    public void setEnd(int end) {
        this.end.add(end);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTime() {
        return time;
    }

    public int getArrival_TIme() {
        return arrival_TIme;
    }

    public String getProcess_Number() {
        return process_Number;
    }

    public boolean isIs_Arrived() {
        return is_Arrived;
    }

    public void setIs_Arrived(boolean is_Arrived) {
        this.is_Arrived = is_Arrived;
    }

    public void setRemaining_Time(int remaining_Time){
        this.remaining_Time = remaining_Time;
    }

    public int getRemaining_Time(){
        return remaining_Time;
    }

    public void getProcessStatus(){

        if(start.get(start.size()-1)==arrival_TIme)
            waitingTime=0;
        else if (start.size()>1)
            waitingTime=waitingTime + (float)(start.get(start.size()-1)-end.get(end.size()-2));
        else
            waitingTime=(float)(start.get(0)-arrival_TIme);
    }

    public float getWaitingTime() {
        return waitingTime;
    }
}

