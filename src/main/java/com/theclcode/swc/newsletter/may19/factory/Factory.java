package com.theclcode.swc.newsletter.may19.factory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Factory {

    /**************** START OF USER SOLUTION ****************/

    static Step[] steps;
    static int currentTime;
    static SortedLinkedList<Lot> warehouse;
    static Step stepZero;

    static void init(int N) {
        steps = new Step[N];
        currentTime = 0;
        warehouse = new SortedLinkedList<>();
        stepZero = null;
    }
    static void setupTool(int T, int stepNo[], int procTime[]) {
        for(int i=0; i<T; i++){
            Tool tool = new Tool(procTime[i]);
            if(steps[stepNo[i]] == null){
                steps[stepNo[i]] = new Step();
            }
            steps[stepNo[i]].availableTools.add(tool);
        }
        stepZero = steps[0];
    }
    static void addLot(int time, int number) {
        for(int i=0; i<number; i++){
            Lot lot = new Lot(time);
            stepZero.lots.add(lot);
        }
//        currentTime = time;
    }
    static int simulate(int time, int wip[]) {
        while(currentTime <= time){
            for(int i=0; i<steps.length; i++){
                Step step = steps[i];
                SortedLinkedList.Node<Tool> busyTool = step.busyTools.getHead();
                while(busyTool != null){
                    if(currentTime >= busyTool.value.endTime){
                        Tool tool = step.busyTools.remove(busyTool);
                        Lot lot = tool.removeLot();
                        step.availableTools.add(tool);
                        if(i == steps.length-1){
                            warehouse.add(lot);
                        } else {
                            steps[i+1].lots.add(lot);
                        }
                    }
                    busyTool = busyTool.next;
                }
                while(step.availableTools.size > 0 && step.lots.size > 0){
                    if(currentTime < step.lots.getHead().value.added){
                        break;
                    }
                    Tool tool = step.availableTools.removeFirst();
                    Lot lot = step.lots.removeFirst();
                    lot.started = currentTime;
                    tool.processLot(lot);
                    step.busyTools.add(tool);
                }
            }
            currentTime++;
        }

        for(int i=0; i<steps.length; i++){
            wip[i] = steps[i].lots.size + steps[i].busyTools.size;
        }

        return warehouse.size;
    }

    static class Step {
        SortedLinkedList<Tool> availableTools = new SortedLinkedList<>();
        SortedLinkedList<Tool> busyTools = new SortedLinkedList<>();
        SortedLinkedList<Lot> lots = new SortedLinkedList<>();
    }

    static class Lot {
        int added;
        int started;

        Lot(int added){
            this.added = added;
        }
    }

    static class Tool {
        int processingTime;
        boolean isBusy;
        int endTime;
        Lot lot;

        Tool(int processingTime){
            this.processingTime = processingTime;
        }

        public void processLot(Lot lot){
            this.lot = lot;
            isBusy = true;
            endTime = lot.started + processingTime;
        }

        public Lot removeLot(){
            Lot lot = this.lot;
            this.lot = null;
            isBusy = false;
            return lot;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }
    }

    static class SortedLinkedList<E> {

        Node<E> head;
        Node<E> tail;
        int size;

        public void add(E value){
            Node<E> node = new Node<>(value);
            if(head == null){
                head = tail = node;
            } else {
                if(value instanceof Tool){
                    Tool tool = (Tool) value;
                    int newVal = tool.isBusy ? tool.endTime : tool.processingTime;

                    Node<E> existing = head;

                    while(existing != null){
                        Tool existingTool = (Tool) existing.value;
                        int existingVal = existingTool.isBusy ? existingTool.endTime : existingTool.processingTime;
                        if(newVal <= existingVal){
                            node.next = existing;
                            node.prev = existing.prev;
                            if(existing.prev != null){
                                existing.prev.next = node;
                            }
                            if(existing == head){
                                head = node;
                            }
                            existing.prev = node;
                            break;
                        } else {
                            if(existing == tail){
                                tail.next = node;
                                node.prev = tail;
                                tail = node;
                                break;
                            }
                        }
                        existing = existing.next;
                    }

                } else {
                    tail.next = node;
                    node.prev = tail;
                    tail = node;
                }
            }
            size++;

        }

        public E remove(Node<E> node){
            Node<E> existing = head;

            while(existing != null){
                if(existing == node){
                    if(node.next != null){
                        node.next.prev = node.prev;
                    }
                    if(node.prev != null){
                        node.prev.next = node.next;
                    }
                    if(node == head){
                        head = node.next;
                        if(head != null){
                            head.prev = null;
                        }
                    }
                    if(node == tail){
                        tail = node.prev;
                        if(tail != null){
                            tail.next = null;
                        }
                    }
                    size--;
                    return node.value;
                }
                existing = existing.next;
            }
            return null;
        }

        public E removeFirst(){
           if(head == null){
               return null;
           }
           Node<E> node = head;
           head = head.next;
           if(head == null){
               tail = null;
           } else {
               head.prev = null;
           }
           size--;
           return node.value;
        }

        public Node<E> getHead() {
            return head;
        }

        static class Node<E> {
            E value;
            Node<E> prev;
            Node<E> next;

            Node(E value){
                this.value = value;
            }
        }


    }



    /***************** END OF USER SOLUTION *****************/

    private static int MAX_N = 100;
    private static int MAX_TOOL = 50;
    private static int ADDLOT = 1;
    private static int SIMUL = 2;

    private static BufferedReader br;
    private static StringBuilder out;

    private static void run() throws Exception
    {

        int totalStep = 0, totalTool = 0;
        int stepID[] = new int[MAX_N * MAX_TOOL];
        int procTime[] = new int[MAX_N * MAX_TOOL];
        int event, time, number;
        int userOut, resultOut;
        int userWip[] = new int[MAX_N];
        int resultWip[] = new int[MAX_N];

        for (int j = 0; j < MAX_N; j++) {
            userWip[j] = resultWip[j] = 0;
        }

        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");
        totalStep = Integer.parseInt(stdin.nextToken());
        totalTool = Integer.parseInt(stdin.nextToken());

        init(totalStep);

        for (int i = 0; i < totalTool; i++) {
            stepID[i] = Integer.parseInt(stdin.nextToken());
            procTime[i] = Integer.parseInt(stdin.nextToken());
        }
        setupTool(totalTool, stepID, procTime);

        while (true) {
            stdin = new StringTokenizer(br.readLine(), " ");
            event = Integer.parseInt(stdin.nextToken());
            if (event==ADDLOT) {
                time = Integer.parseInt(stdin.nextToken());
                number = Integer.parseInt(stdin.nextToken());
                addLot(time, number);
            }
            else if (event==SIMUL) {
                time = Integer.parseInt(stdin.nextToken());
                for (int j = 0; j < totalStep; j++) {
                    userWip[j] = 0;
                }
                userOut   = simulate(time, userWip);

                out.append(String.format("%d", time));
                for (int j = 0; j < totalStep; j++) {
                    out.append(String.format(" %d", userWip[j]));
                }
                out.append(String.format(" %d\n", userOut));
            }
            else {
                break;
            }
        }
    }


    public static void main(String[] args) throws Exception {
		//System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new StringBuilder();

        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");
        int TestCase = Integer.parseInt(stdin.nextToken());

        for (int t = 1; t <= TestCase; t++) {
            out.append(String.format("Case #%d:\n", t));
            run();
        }
        br.close();

        System.out.print(out);
    }
}
