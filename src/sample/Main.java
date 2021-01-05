package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        //launch(args);

        //FCFS:
        //-----

        /*zzProcess[] process = new Process[4];
        process[0] = new Process(4, 5, "1");
        process[1] = new Process(1, 1, "2");
        process[2] = new Process(1, 0, "3");
        process[3] = new Process(4, 3, "4");

        sort(process , 0 , process.length-1);

        FCFS fcfs = new FCFS();
        fcfs.num_Of_Processes = process.length;

        for (int i = 0; i < process.length; i++) {
            fcfs.processes_Queue.add(process[i]);
        }

        fcfs.processHandling();

        for (int i = 0; i < fcfs.running_Process.size(); i++) {
            System.out.println("Process number_" + fcfs.running_Process.get(i).getProcess_Number());
            System.out.println("start: " + fcfs.running_Process.get(i).getStart());
            System.out.println("end: " + fcfs.running_Process.get(i).getEnd());
            if (i < fcfs.num_Of_Processes)
                fcfs.avgWaitingTime += (process[i].getWaitingTime());
        }
        fcfs.avgWaitingTime = fcfs.avgWaitingTime / fcfs.num_Of_Processes;
        System.out.println("AWT: " + fcfs.avgWaitingTime);*/


        //SJF:
        //....

        Process[] process = new Process[4];
        process[0] = new Process(4,0,"1");
        process[1] = new Process(5,4,"2");
        process[2] = new Process(8,5,"3");
        process[3] = new Process(2,6,"4");

        sort(process , 0 , process.length-1);

        SJF sjf = new SJF();
        sjf.preemptive=true;
        sjf.num_Of_Processes=process.length;
        for(int i = 0 ; i<process.length; i++){
            sjf.processes_Queue.add(process[i]);
        }

        sjf.processHandling();

        for(int i = 0 ; i<sjf.running_Process.size(); i++){
            System.out.println("Process number_"+sjf.running_Process.get(i).getProcess_Number());
            System.out.println("start: "+sjf.running_Process.get(i).getStart());
            System.out.println("end: "+sjf.running_Process.get(i).getEnd());
            if (i<sjf.num_Of_Processes)
                sjf.avgWaitingTime+=(process[i].getWaitingTime());
        }
        sjf.avgWaitingTime=sjf.avgWaitingTime/sjf.num_Of_Processes;
        System.out.println("AWT: "+sjf.avgWaitingTime);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //RR:
        //...

        /*Process[] process = new Process[4];
        process[0] = new Process(50,0 ,"1");
        process[1] = new Process(17,0 ,"2");
        process[2] = new Process(68,0 ,"3");
        process[3] = new Process(24, 0 ,"4");

        RR rr = new RR();
        rr.num_Of_Processes=process.length;
        rr.time_Quantum = 20;
        for(int i = 0 ; i<process.length; i++){
            rr.processes_Queue.add(process[i]);
        }
        rr.processHandling();

        for(int i = 0 ; i<rr.running_Process.size(); i++){
            System.out.println("Process number_"+rr.running_Process.get(i).getProcess_Number());
            System.out.println("start: "+rr.running_Process.get(i).getStart());
            System.out.println("end: "+rr.running_Process.get(i).getEnd());
            if (i<rr.num_Of_Processes)
                rr.avgWaitingTime+=(process[i].getWaitingTime());
        }
        rr.avgWaitingTime=rr.avgWaitingTime/rr.num_Of_Processes;
        System.out.println("AWT: "+rr.avgWaitingTime);*/

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Priority:
        //.........

        /*Process[] process = new Process[7];
        process[0] = new Process(4, 6 ,0,"1");
        process[1] = new Process(2, 5, 1,"2");
        process[2] = new Process(3, 4 ,2,"3");
        process[3] = new Process(5, 1 ,3,"4");
        process[4] = new Process(1, 3 ,4,"5");
        process[5] = new Process(4, 2 ,21,"6");
        process[6] = new Process(6, 0 ,23,"7");

        Priority priority = new Priority();
        priority.preemptive=true;
        priority.num_Of_Processes=process.length;
        for(int i = 0 ; i<process.length; i++){
            priority.processes_Queue.add(process[i]);
        }
        priority.processHandling();

        for(int i = 0 ; i<priority.running_Process.size(); i++){
            System.out.println("Process number_"+priority.running_Process.get(i).getProcess_Number());
            System.out.println("start: "+priority.running_Process.get(i).getStart());
            System.out.println("end: "+priority.running_Process.get(i).getEnd());
            if (i<priority.num_Of_Processes)
                priority.avgWaitingTime+=(process[i].getWaitingTime());
        }
        priority.avgWaitingTime=priority.avgWaitingTime/priority.num_Of_Processes;
        System.out.println("AWT: "+priority.avgWaitingTime);*/
    }

    public static void merge(Process arr[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        Process[] L = new Process[n1];
        Process[] R = new Process[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].getArrival_TIme() <= R[j].getArrival_TIme()) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void sort(Process arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

}

