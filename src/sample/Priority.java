package sample;

public class Priority extends Scheduler {

    private int arrival_Time=0 , next_Process;
    private static final int ONE_SECOND_ITERATION = 1000;

    public void grantProcessArrival(int current_Process){

        processes_Queue.get(current_Process).setIs_Arrived(true);
        for (int i=current_Process+1 ; i<processes_Queue.size() ; i++)
            if(processes_Queue.get(current_Process).getArrival_TIme()>=processes_Queue.get(i).getArrival_TIme())
                processes_Queue.get(i).setIs_Arrived(true);
            else {
                if(!processes_Queue.get(i).isIs_Arrived()) next_Process=i;
                break;
            }
    }

    public int min(int assumed_Min_Process){

        for(int i=0 ; i<processes_Queue.size() ; i++){

            if(processes_Queue.get(i).isIs_Arrived()) {
                if (processes_Queue.get(assumed_Min_Process).getPriority() > processes_Queue.get(i).getPriority()) {
                    assumed_Min_Process = i;
                }
            }
            else break;
        }
        return assumed_Min_Process;
    }


    public void delay(int time , int running_Process_Index) {

        int trick_Arrival_time = arrival_Time*1000;
        if(processes_Queue.get(running_Process_Index).getArrival_TIme()>arrival_Time)
            arrival_Time=processes_Queue.get(running_Process_Index).getArrival_TIme();

        processes_Queue.get(running_Process_Index).setStart(arrival_Time);

        for (int i = 1; i <= time; i++) {

            arrival_Time++;
            processes_Queue.get(running_Process_Index).setRemaining_Time(time-i);
            if (next_Process < processes_Queue.size() && next_Process>-1) {
                if (arrival_Time == processes_Queue.get(next_Process).getArrival_TIme()) {
                    processes_Queue.get(next_Process).setIs_Arrived(true);
                    next_Process++;
                    //if(preemptive) break;
                    if(preemptive)
                        if(min(running_Process_Index)!=running_Process_Index) break;
                }
            }
        }
        processes_Queue.get(running_Process_Index).setEnd(arrival_Time);
    }

    @Override
    public void processHandling() {

        int current_Process=0;
        String previous_Process_Status="";
        while(processes_Queue.size()!=0){

            grantProcessArrival(current_Process);                   //set is_Arrival to the equaled arrival process
            int min_Process_Index = min(current_Process);           //check the smallest process to execute among the arrived ones
            delay(processes_Queue.get(min_Process_Index).getRemaining_Time() , min_Process_Index);  //run process
            running_Process.add(processes_Queue.get(min_Process_Index));
            processes_Queue.get(min_Process_Index).getProcessStatus();
            /*if(!previous_Process_Status.equals(processes_Queue.get(min_Process_Index).getProcessStatus()))
                System.out.println(processes_Queue.get(min_Process_Index).getProcessStatus());  //print the process status(running , finished)
            previous_Process_Status = processes_Queue.get(min_Process_Index).getProcessStatus();*/ //for not repeating the sae status
            if(processes_Queue.get(min_Process_Index).getRemaining_Time()==0){  //check if the process finished
                processes_Queue.remove(min_Process_Index);      //remove the process
                if(next_Process!=0)next_Process--;     //as you remove one process then the index of next process decreased by 1
            }
        }
    }
}
