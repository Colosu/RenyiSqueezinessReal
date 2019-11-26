package main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

import net.automatalib.automata.transout.impl.compact.CompactMealy;

public class Operations {
	
	public void SqueezinessAndPColl(Graph g, int length, Vals vals) {

		if (length <= 0) {
			return;
		}

		int inputs = 0;
		HashMap<String, Integer> mapOtoI = new HashMap<String, Integer>();
		double TPColl = 0;
		double TSq1 = 0;
		double TSq2 = 0;
		double TSq3 = 0;
		double TSq4 = 0;
		double TSq5 = 0;
		double TSq6 = 0;
		double TSq7 = 0;
		double TSq8 = 0;
		double TSq9 = 0;
		double TSq10 = 0;
		double a1 = 0; //Done with: 0, 1, 20, 2.1, 3.1
		double a2 = 0.1; //Done with: 0.1, 2, 30, 2.2, PI
		double a3 = 0.2; //Done with: 0.2, 3, 40, 2.3, 3.2
		double a4 = 0.3; //Done with: 0.3, 4, 50, 2.4, 3.3
		double a5 = 0.4; //Done with: 0.4, 5, 60, 2.5, 3.4
		double a6 = 0.5; //Done with: 0.5, 6, 70, 2.6, 3.5
		double a7 = 0.6; //Done with: 0.6, 7, 80, 2.7, 3.6
		double a8 = 0.7; //Done with: 0.7, 8, 90, e, 3.7
		double a9 = 0.8; //Done with: 0.8, 9, 100, 2.8, 3.8
		double a10 = 0.9; //Done with: 0.9, 10, \infty, 2.9, 3.9

		CompactMealy<String, String> fsm = g.getMachine();
		int qid = fsm.getInitialState();
		FSMExplorer[] explorer = new FSMExplorer[fsm.getInputAlphabet().size()];
		String inp = "";
		int i = 0;
		for (Iterator<String> it = fsm.getInputAlphabet().iterator(); it.hasNext(); ) {
			inp = it.next();
			if (fsm.getTransition(qid, inp) != null) {
				explorer[i] = new FSMExplorer(fsm, fsm.getSuccessor(qid, inp), 1, length, mapOtoI, "" + fsm.getOutput(qid, inp));
				explorer[i].start();
			}
			i++;
		}
		for (int j = 0; j < i; j++) {
			try {
				if (explorer[j] != null) {
					explorer[j].join();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int j = 0; j < i; j++) {
			if (explorer[j] != null) {
				inputs += explorer[j].getInputs();
			}
		}
		
//		inputs = ExploreFSM(fsm, qid, 0, length, mapOtoI, "");

//		double max = 0.0;
		Integer val = 0;
		for (Iterator<Integer> it = mapOtoI.values().iterator(); it.hasNext(); ) {
			val = it.next();
			TPColl += (double)(val) * (val - 1);

//			TSq1 += val * log2((double)val);
			TSq1 += Math.pow((double)val, a1);
			TSq2 += Math.pow((double)val, a2);
			TSq3 += Math.pow((double)val, a3);
			TSq4 += Math.pow((double)val, a4);
			TSq5 += Math.pow((double)val, a5);
			TSq6 += Math.pow((double)val, a6);
			TSq7 += Math.pow((double)val, a7);
			TSq8 += Math.pow((double)val, a8);
			TSq9 += Math.pow((double)val, a9);
			TSq10 += Math.pow((double)val, a10);

//			if (val > max) {
//				max = val;
//			}
		}

		vals.setPColl(TPColl/((double)(inputs) * (inputs - 1)));

//		vals.setSq1(TSq1/((double)inputs));
		vals.setSq1((1.0/(1.0 - a1))*log2((double)inputs / TSq1));
		vals.setSq2((1.0/(1.0 - a2))*log2((double)inputs / TSq2));
		vals.setSq3((1.0/(1.0 - a3))*log2((double)inputs / TSq3));
		vals.setSq4((1.0/(1.0 - a4))*log2((double)inputs / TSq4));
		vals.setSq5((1.0/(1.0 - a5))*log2((double)inputs / TSq5));
		vals.setSq6((1.0/(1.0 - a6))*log2((double)inputs / TSq6));
		vals.setSq7((1.0/(1.0 - a7))*log2((double)inputs / TSq7));
		vals.setSq8((1.0/(1.0 - a8))*log2((double)inputs / TSq8));
		vals.setSq9((1.0/(1.0 - a9))*log2((double)inputs / TSq9));
		vals.setSq10((1.0/(1.0 - a10))*log2((double)inputs / TSq10));
//		vals.setSq10(log2(max));
		
		return;
	}
	
//	public int ExploreFSM(CompactMealy<String, String> fsm, int qid, int iter, int length, HashMap<String, Integer> mapOtoI, String output) {
//
//		int inputs = 0;
//		int count = 0;
//		String inp = "";
//		
//		if (iter == length) {
//			try {
//				inputs++;
//				Operations.sem.acquire();
//				if (mapOtoI.containsKey(output)) {
//					mapOtoI.put(output, mapOtoI.get(output) + 1);
//				} else {
//					mapOtoI.put(output, 1);
//				}
//				Operations.sem.release();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		} else {
//			count = 0;
//			for (Iterator<String> it = fsm.getInputAlphabet().iterator(); it.hasNext(); ) {
//				inp = it.next();
//				if (fsm.getTransition(qid, inp) != null) {
//					inputs += ExploreFSM(fsm, fsm.getSuccessor(qid, inp), iter + 1, length, mapOtoI, output + fsm.getOutput(qid, inp));
//					count++;
//				}
//			}
//			if (count == 0) {
//				try {
//					inputs++;
//					Operations.sem.acquire();
//					if (mapOtoI.containsKey(output)) {
//						mapOtoI.put(output, mapOtoI.get(output) + 1);
//					} else {
//						mapOtoI.put(output, 1);
//					}
//					Operations.sem.release();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		return inputs;
//	}
	
	private double log2(double val) {
		return Math.log(val)/Math.log(2.0);
	}
	
	public static Semaphore sem = new Semaphore(1);
}
