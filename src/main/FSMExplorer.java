package main;

import java.util.HashMap;
import java.util.Iterator;

import net.automatalib.automata.transout.impl.compact.CompactMealy;

public class FSMExplorer extends Thread {

	public FSMExplorer(CompactMealy<String, String> fsm, int qid, int iter, int length, HashMap<String, Integer> mapOtoI, String output) {
		this.fsm = fsm;
		this.initQid = qid;
		this.initIter = iter;
		this.length = length;
		this.mapOtoI = mapOtoI;
		this.initOutput = output;
	}
	
	public int getInputs() {
		return inps;
	}
	
	public void run() {

		inps = ExploreFSM(initQid, initIter, initOutput);
		
		return;
	}


	private int ExploreFSM(int qid, int iter, String output) {

		int inputs = 0;
		int count = 0;
		String inp = "";
		
		if (iter == length) {
			try {
				inputs++;
				Operations.sem.acquire();
				if (mapOtoI.containsKey(output)) {
					mapOtoI.put(output, mapOtoI.get(output) + 1);
				} else {
					mapOtoI.put(output, 1);
				}
				Operations.sem.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			count = 0;
			for (Iterator<String> it = fsm.getInputAlphabet().iterator(); it.hasNext(); ) {
				inp = it.next();
				if (fsm.getTransition(qid, inp) != null) {
					inputs += ExploreFSM(fsm.getSuccessor(qid, inp), iter + 1, output + fsm.getOutput(qid, inp));
					count++;
				}
			}
			if (count == 0) {
				try {
					inputs++;
					Operations.sem.acquire();
					if (mapOtoI.containsKey(output)) {
						mapOtoI.put(output, mapOtoI.get(output) + 1);
					} else {
						mapOtoI.put(output, 1);
					}
					Operations.sem.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		return inputs;
	}
	
	private CompactMealy<String, String> fsm;
	private int initQid;
	private int initIter;
	private int length;
	private HashMap<String, Integer> mapOtoI;
	private String initOutput;
	private int inps;
}
