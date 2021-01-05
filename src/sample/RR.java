package sample;

public class RR extends Scheduler {

    private int time_Counter=0;
    //private static final int ONE_SECOND_ITERATION = 1000;

    public void delay(int time , int running_Process_Index) {

        if(processes_Queue.get(running_Process_Index).getArrival_TIme()>time_Counter)
            time_Counter=processes_Queue.get(running_Process_Index).getArrival_TIme();

        processes_Queue.get(running_Process_Index).setStart(time_Counter);

        for (int i = 1; i <= time; i++) {

            //for (int k = 0; k < ONE_SECOND_ITERATION; k++) ;

            time_Counter++;
            processes_Queue.get(running_Process_Index).setRemaining_Time(time-i);
            if (time_Quantum < time) {
                if (i == time_Quantum) {
                    break;
                }
            }
        }
        processes_Queue.get(running_Process_Index).setEnd(time_Counter);
    }

    @Override
    public void processHandling() {


        for(int current_Process=0 ; processes_Queue.size()!=0 ; current_Process = (current_Process+1)%processes_Queue.size()){

            boolean flag=false;
            delay(processes_Queue.get(current_Process).getRemaining_Time() , current_Process);
            running_Process.add(processes_Queue.get(current_Process));
            processes_Queue.get(current_Process).getProcessStatus();
            if (processes_Queue.size()>1 && current_Process!=processes_Queue.size()-1) {
                if (processes_Queue.get(current_Process).getEndCopy() < processes_Queue.get(current_Process + 1).getArrival_TIme()
                        && processes_Queue.size() > 1) {
                   flag=true;
                }
            }
            /*System.out.println(processes_Queue.get(current_Process).getProcessStatus());
            System.out.println("...Start at : "+processes_Queue.get(current_Process).getStart()+" Ends at : "+processes_Queue.get(current_Process).getEnd());*/
            if(processes_Queue.get(current_Process).getRemaining_Time()==0) {
                processes_Queue.remove(current_Process);
                if (processes_Queue.size() == 0) break;   //as to avoid dividing by zero when all processes finished
                current_Process--;
            }
            if (flag) current_Process=-1;
        }

    }
}