package com.theclcode.unfinished.workdistribution;

import java.util.Scanner;

public class WorkDistribution {

	public static final int WAIT = 				0;
	public static final int IN_PROGRESS = 		1;
	public static final int DONE = 				2;

	static class RESULT {
		int status;
		int finish;
		int worker;
	}

	/**************** START OF USER SOLUTION ****************/

	static class Worker {
		int id;
		int bossId;
		LinkedList<Worker> juniors = new LinkedList<>();
		LinkedList<Work> workQueue = new LinkedList<>();

		int wid = -1;
		int startTime = -1;
		int endTime = -1;

		Worker(int id, int bossId){
			this.id = id;
			this.bossId = bossId;
		}

		void clear(){
			if(wid != -1){

				Work currWork = works[wid];
				currWork.status = DONE;
				currWork.finishedOn = endTime;
				this.wid = -1;
				this.startTime = -1;
				this.endTime = -1;
			}
		}

		void pickWork(Work work, Work currWork, int ctime){
			if(currWork.originalWorkerId == bossId){
				Worker boss = workers[bossId];
				currWork.runTime = workers[currWork.workerId].endTime - ctime;
				currWork.status = WAIT;
				currWork.wid = -1;
				boss.workQueue.add(currWork, QueueType.WORK);
			} else {
				LinkedList.Node juniorNode = this.juniors.head;
				boolean hasAvailableWorker = false;
				boolean reassigned = false;
				while (juniorNode != null){
					Worker junior = (Worker) juniorNode.value;
					if(junior.startTime == -1){
						junior.assignWork(work, ctime);
						hasAvailableWorker = true;
						reassigned = true;
					}
					juniorNode = juniorNode.next;
				}
				if(!hasAvailableWorker){
					juniorNode = this.juniors.head;
					while(juniorNode != null){
						Worker junior = (Worker) juniorNode.value;
						if(junior.assignWork(currWork, ctime)){
							reassigned = true;
							break;
						}
						juniorNode = juniorNode.next;
					}
				}
				if(!reassigned){
					if(currWork.workerId != -1){
						currWork.runTime = workers[currWork.workerId].endTime - ctime;
						currWork.status = WAIT;
						currWork.wid = -1;
					}
					workQueue.add(currWork, QueueType.WORK);
				}
			}
			pickWork(work, ctime);
		}

		void pickWork(Work work, int ctime){
			this.wid = work.wid;
			this.startTime = ctime;
			this.endTime = ctime + work.runTime;
			work.workerId = this.id;
			work.status = IN_PROGRESS;
		}

		public boolean assignWork(Work work, int ctime){
			if(this.wid == -1){
				pickWork(work, ctime);
				return true;
			} else {
				Work currWork = works[this.wid];
				if(currWork.originalWorkerId == this.id && work.originalWorkerId == this.id
						&& hasHigherPriority(currWork, work)){
					pickWork(work, currWork, ctime);
					return true;
				} else if(currWork.originalWorkerId == this.id && work.originalWorkerId == this.bossId){
					pickWork(work, currWork, ctime);
					return true;
				} else {
					if(work.originalWorkerId != this.bossId){
						LinkedList.Node juniorsNode = this.juniors.head;
						while(juniorsNode != null){
							Worker junior = (Worker) juniorsNode.value;
							if(junior.startTime == -1){
								return junior.assignWork(work, ctime);
							}
							juniorsNode = juniorsNode.next;
						}
						juniorsNode = this.juniors.head;
						while(juniorsNode != null){
							Worker junior = (Worker) juniorsNode.value;
							if(junior.assignWork(work, ctime)){
								return true;
							}
							juniorsNode = juniorsNode.next;
						}
					}

				}
			}
			return false;
		}

		private boolean hasHigherPriority(Work currWork, Work newWork){
			return currWork.priority < newWork.priority
					|| (currWork.priority == newWork.priority && newWork.wid < currWork.priority);
		}
	}

	static class Work {
		int wid;
		int runTime;
		int priority;
		int status;
		int originalWorkerId;
		int workerId;
		int finishedOn;

		Work(int wid, int imp, int runTime, int workerId){
			this.wid = wid;
			this.priority = imp;
			this.runTime = runTime;
			this.originalWorkerId = workerId;
			this.workerId = workerId;
			this.status = WAIT;
		}
	}

	enum QueueType {
		WORK
	}

	static class LinkedList<E> {

		int size;
		Node<E> head;
		Node<E> tail;

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

		public void remove(E value){
			Node<E> existing = head;
			while(existing != null){
				if(existing.value == value){
					remove(existing);
					break;
				}
				existing = existing.next;
			}
		}

		public void remove(Node node){
			if(node.prev != null){
				node.prev.next = node.next;
			}
			if(node.next != null){
				node.next.prev = node.prev;
			}
			if(node == head){
				head = node.next;
			}
			if(node == tail){
				tail = node.prev;
			}
			size--;
		}


		public void add(E value, QueueType queueType){
			Node<E> node = new Node<>(value);
			if(size == 0){
				head = tail = node;
			} else {
				if (queueType == QueueType.WORK) {
					Node<E> existing = head;
					boolean inserted = false;
					while (existing != null) {
						Work nw = (Work) node.value;
						Work ew = (Work) existing.value;
						if (nw.priority >= ew.priority) {
							if(nw.priority > ew.priority || nw.wid < ew.wid) {
								insertNode(node, existing);
								inserted = true;
								break;
							}
						}
						existing = existing.next;
					}
					if (!inserted) {
						tail.next = node;
						node.prev = tail;
						tail = node;
					}
				} else {
					tail.next = node;
					node.prev = tail;
					tail = node;
				}
			}
			size++;
		}

		private void insertNode(Node<E> node, Node<E> existing){
			node.prev = existing.prev;
			node.next = existing;
			if(existing.prev != null){
				existing.prev.next = node;
			}
			if(existing == head){
				head = node;
			}
			existing.prev = node;
		}

		class Node<E>{
			E value;
			Node<E> prev;
			Node<E> next;

			Node(E value){
				this.value = value;
			}
		}
	}

	static Worker ceo;
	static LinkedList<Worker> workersList;
	static LinkedList<Worker> workersAtWork;
	static LinkedList<Work> workQueue;
	static Worker[] workers;
	static Work[] works;

	static void init() {
		ceo = new Worker(1, -1);
		workers = new Worker[101];
		works = new Work[70_001];
		workers[1] = ceo;

		workersAtWork = new LinkedList<>();
		workQueue = new LinkedList<>();
		workersList = new LinkedList<>();
		workersList.add(ceo, null);
	}

	static void addMember(int ctime, int id, int boss) {
		Worker worker = new Worker(id, boss);
		workers[id] = worker;
		Worker theBoss = workers[boss];
		theBoss.juniors.add(worker, null);
		workersList.add(worker, null);
		updateQueues(ctime);
	}

	static void addWork(int ctime, int wid, int id, int imp, int runtime) {
		Work work = new Work(wid, imp, runtime, id);
		works[wid] = work;
		Worker worker = workers[id];
		worker.workQueue.add(work, QueueType.WORK);
		updateQueues(ctime);
	}

	static void updateQueues(int ctime){
		clearFinishedWork(ctime);
		addNewWork(ctime);
	}

	static void clearFinishedWork(int ctime){
		LinkedList.Node workerNode = workersList.head;
		while(workerNode != null){
			Worker worker = (Worker) workerNode.value;
			if(worker.startTime != -1 && ctime >= worker.endTime){
				worker.clear();
			}
			workerNode = workerNode.next;
		}
	}

	static void addNewWork(int ctime){
		LinkedList.Node workerNode = workersList.head;
		while(workerNode != null){
			Worker worker = (Worker) workerNode.value;
			if(worker.workQueue.size > 0){
				LinkedList.Node workNode = worker.workQueue.head;
				while(workNode != null){
					Work work = (Work) workNode.value;
					if(worker.assignWork(work, ctime)){
						worker.workQueue.remove(workNode);
					}
					workNode = workNode.next;
				}
			}
			workerNode = workerNode.next;
		}
	}

	static RESULT workStatus(int ctime, int wid) {

		updateQueues(ctime);

		RESULT result = new RESULT();
		Work work = works[wid];

		switch (work.status){
			case WAIT:
				result.finish = 0;
				result.worker = 0;
				break;
			case IN_PROGRESS:
				result.finish = workers[work.workerId].endTime;
				result.worker = work.workerId;
				break;
			case DONE:
				result.finish = work.finishedOn;
				result.worker = work.workerId;
				break;
			default:
				throw new RuntimeException();
		}

		result.status = work.status;

		return result;
	}

	/***************** END OF USER SOLUTION *****************/


	public static final int INIT = 			1;
	public static final int ADD_MEMBER = 	2;
	public static final int ADD_WORK = 		3;
	public static final int WORK_STATUS = 	4;

	static void run()
	{
		int Q, ctime, dt;

		int type, id, boss, wid, imp, runtime;
		int rStatus, rFinish, rWorker;

		RESULT res;

		Q = sc.nextInt();

		ctime = 0;
		for (int i = 0; i < Q; ++i)
		{
			type = sc.nextInt();
			
			switch (type)
			{
				case INIT:
					init();
					break;
				case ADD_MEMBER:
					dt = sc.nextInt();
					id = sc.nextInt();
					boss = sc.nextInt();
					
					ctime += dt;
					addMember(ctime, id, boss);
					break;
				case ADD_WORK:
					dt = sc.nextInt();
					wid = sc.nextInt();
					id = sc.nextInt();
					imp = sc.nextInt();
					runtime = sc.nextInt();

					ctime += dt;
					addWork(ctime, wid, id, imp, runtime);
					break;
				case WORK_STATUS:
					dt = sc.nextInt();
					wid = sc.nextInt();
					
					ctime += dt;
					res = workStatus(ctime, wid);

					System.out.printf("%d %d %d\n", res.status, res.finish, res.worker);

					break;
				default:
					break;
			}
		}
	}

	static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);

		int T;
		T = sc.nextInt();

		for (int tc = 1; tc <= T; ++tc)	{
			System.out.printf("Case #%d:\n", tc);
			run();
		}
	}
}
