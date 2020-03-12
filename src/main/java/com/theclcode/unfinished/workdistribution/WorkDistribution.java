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

		int wid = -1;
		int startTime = -1;
		int endTime = -1;

		Worker(int id, int bossId){
			this.id = id;
			this.bossId = bossId;
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
		boolean isBossWork;

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
		WORKER_ID, WORKER_WORK, WORK
	}

	static class LinkedList<E> {

		int size;
		Node<E> head;
		Node<E> tail;

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
				if(queueType == QueueType.WORKER_ID){
					tail.next = node;
					node.prev = tail;
					tail = node;
				} else {
					Node<E> existing = head;
					boolean inserted = false;
					while(existing != null){
						if(queueType == QueueType.WORK){
							Work nw = (Work) node.value;
							Work ew = (Work) existing.value;
							if(nw.priority >= ew.priority){
								if(nw.priority > ew.priority || nw.wid < ew.wid){
									insertNode(node, existing);
									inserted = true;
									break;
								}
							}
						} else if(queueType == QueueType.WORKER_WORK){
							Worker nw = (Worker) node.value;
							Worker ew = (Worker) existing.value;
							if(nw.endTime <= ew.endTime){
								insertNode(node, existing);
								inserted = true;
								break;
							}
						}
						existing = existing.next;
					}
					if(!inserted){
						tail.next = node;
						node.prev = tail;
						tail = node;
					}
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
	}

	static void addMember(int ctime, int id, int boss) {
		Worker worker = new Worker(id, boss);
		workers[id] = worker;
		Worker theBoss = workers[boss];
		theBoss.juniors.add(worker, QueueType.WORKER_ID);
		updateQueues(ctime);
	}

	static void addWork(int ctime, int wid, int id, int imp, int runtime) {
		Work work = new Work(wid, imp, runtime, id);
		works[wid] = work;
		workQueue.add(work, QueueType.WORK);
		updateQueues(ctime);
	}

	static void assignWork(Worker worker, Work work, int ctime, LinkedList.Node nodeToRemove){
		if(worker.startTime != -1){
			Work currWork = works[worker.wid];
			currWork.runTime = worker.endTime - ctime;
			currWork.status = WAIT;
			currWork.workerId = 0;
			workQueue.add(currWork, QueueType.WORK);

			if(nodeToRemove != null){
				workersAtWork.remove(nodeToRemove);
			} else {
				workersAtWork.remove(worker);
			}
		}
		work.status = IN_PROGRESS;
		work.workerId = worker.id;
		worker.wid = work.wid;
		worker.startTime = ctime;
		worker.endTime = ctime+work.runTime;

		workersAtWork.add(worker, QueueType.WORKER_WORK);
	}

	static void updateQueues(int ctime){
		dequeueWorkersAtWork(ctime);
		dequeueWorkQueue(ctime);
	}

	static void dequeueWorkersAtWork(int ctime){
		LinkedList.Node node = workersAtWork.head;
		while(node != null){
			Worker worker = (Worker) node.value;
			if(ctime >= worker.endTime){
				Work work = works[worker.wid];

				work.status = DONE;
				work.finishedOn = worker.endTime;

				worker.startTime = -1;
				worker.endTime = -1;
				worker.wid = -1;
				workersAtWork.remove(node);
			}
			node = node.next;
		}
	}

	static void dequeueWorkQueue(int ctime){
		LinkedList.Node node = workQueue.head;
		while(node != null){
			Work work = (Work) node.value;
			if(getWorker(work, ctime)){
				workQueue.remove(node);
			}
			node = node.next;
		}
	}

	static boolean getWorker(Work work, int ctime){
		Worker worker = workers[work.originalWorkerId];
		if(worker.startTime == -1 || works[worker.wid].priority < work.priority
				|| (works[worker.wid].priority == work.priority && worker.wid > work.wid) || work.isBossWork){
			assignWork(worker, work, ctime, null);
			return true;
		} else {
			LinkedList.Node juniorsNode = worker.juniors.head;
			while(juniorsNode != null){
				Worker junior = (Worker) juniorsNode.value;
				if(junior.startTime == -1 || works[junior.wid].priority < work.priority
					|| (works[junior.wid].priority == work.priority && works[junior.wid].wid > work.wid)
					|| work.isBossWork){
					assignWork(junior, work, ctime, juniorsNode);
					return true;
				}
				juniorsNode = juniorsNode.next;
			}
		}
		return false;
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
