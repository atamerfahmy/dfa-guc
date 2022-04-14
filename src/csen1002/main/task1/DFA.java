package csen1002.main.task1;

import java.util.ArrayList;

/**
 * Write your info here
 * 
 * @name Ahmed Tamer
 * @id 43-2117
 * @labNumber 11
 */
public class DFA {

	ArrayList<State> DFAStates;
	State currentState;

	/**
	 * DFA constructor
	 * 
	 * @param description is the string describing a DFA
	 */
	public DFA(String description) {
		// TODO Write Your Code Here
		String[] parts = description.split("#");
		String statesTransitions = parts[0];
		String acceptStates = parts[1];

		String[] statesData = statesTransitions.split(";");
		String[] acceptStatesData = acceptStates.split(",");

		boolean[] isAcceptState = new boolean[statesData.length];

		for (String x : acceptStatesData) {
			isAcceptState[Integer.parseInt(x)] = true;
		}

		ArrayList<State> states = new ArrayList<State>();

		for (String x : statesData) {
			String[] stateTransData = x.split(",");
			State state = new State(Integer.parseInt(stateTransData[0]), Integer.parseInt(stateTransData[1]),
					Integer.parseInt(stateTransData[2]), isAcceptState[Integer.parseInt(stateTransData[0])]);
			states.add(state);
		}

		this.DFAStates = states;
		this.currentState = states.get(0);

	}

	/**
	 * Returns true if the string is accepted by the DFA and false otherwise.
	 * 
	 * @param input is the string to check by the DFA.
	 * @return if the string is accepted or not.
	 */
	public boolean run(String input) {
		// TODO Write Your Code Here
		for (int i = 0; i < input.length(); i++) {
			int x = Integer.parseInt("" + input.charAt(i));
			int transition;
			
			if (x == 0) {
				transition = this.getCurrentState().getZeroTransition();
			} else {
				transition = this.getCurrentState().getOneTransition();
			}
			
			this.currentState = this.DFAStates.get(transition);
		}
		
		if(this.getCurrentState().isAcceptState()) {
			return true;
		}
		
		return false;
	}
	
	public ArrayList<State> getDFAStates() {
		return DFAStates;
	}

	public void setDFAStates(ArrayList<State> dFAStates) {
		DFAStates = dFAStates;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public static void main(String[] args) {
		DFA dfa = new DFA("0,0,1;1,2,1;2,0,3;3,3,3#1,3");
		System.out.println(dfa.run("101"));
	}
}

class State {

	int stateNumber;
	int zeroTransition;
	int oneTransition;
	boolean isAcceptState;

	public State(int stateNumber, int zeroTransition, int oneTransition, boolean isAcceptState) {
		this.stateNumber = stateNumber;
		this.zeroTransition = zeroTransition;
		this.oneTransition = oneTransition;
		this.isAcceptState = isAcceptState;
	}

	public int getStateNumber() {
		return stateNumber;
	}

	public int getZeroTransition() {
		return zeroTransition;
	}

	public int getOneTransition() {
		return oneTransition;
	}

	public boolean isAcceptState() {
		return isAcceptState;
	}

}