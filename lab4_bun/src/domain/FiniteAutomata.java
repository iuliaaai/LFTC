package domain;

import java.io.File;
import java.util.*;

public class FiniteAutomata {
    public Set<String> states, alphabet, finalStates;
    public Map<Pair<String, String>, Set<String>> transitions;
    public String initialState;

    public FiniteAutomata(String file) {
        this.states = new HashSet<>();
        this.alphabet = new HashSet<>();
        this.finalStates = new HashSet<>();
        this.transitions = new HashMap<>();

        this.readFromFile(file);
    }

    private void readFromFile(String file) {
        try {
            File f = new File(file);
            Scanner reader = new Scanner(f);

            String statesLine = reader.nextLine();
            states = new HashSet<>(Arrays.asList(statesLine.split(" ")));

            String alphabetLine = reader.nextLine();
            alphabet = new HashSet<>(Arrays.asList(alphabetLine.split(" ")));

            initialState = reader.nextLine();

            String finalStatesLine = reader.nextLine();
            finalStates = new HashSet<>(Arrays.asList(finalStatesLine.split(" ")));

            while(reader.hasNextLine()){
                String transitionLine = reader.nextLine();
                String[] transitionElements = transitionLine.split(" ");

                if(states.contains(transitionElements[0]) && states.contains(transitionElements[2]) && alphabet.contains(transitionElements[1])) {

                    Pair<String, String> transitionStates = new Pair<>(transitionElements[0], transitionElements[1]);

                    if (!transitions.containsKey(transitionStates)) {
                        Set<String> transitionStatesSet = new HashSet<>();
                        transitionStatesSet.add(transitionElements[2]);
                        transitions.put(transitionStates, transitionStatesSet);
                    } else {
                        transitions.get(transitionStates).add(transitionElements[2]);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String printTransitions(){
        StringBuilder builder = new StringBuilder();
        builder.append("Transitions: \n");
        transitions.forEach((key, val) -> {
            builder.append("(").append(key.key).append(",").append(key.value).append(") -> ").append(val).append("\n");
        });

        return builder.toString();
    }

    public boolean checkIfDFA(){
        return this.transitions.values().stream().noneMatch(list -> list.size() > 1);
    }

    public boolean checkSequence(String sequence){
        if(sequence.length() == 0)
            return finalStates.contains(initialState);

        String state = initialState;
        for(int i = 0; i < sequence.length(); ++i){
            Pair<String, String> key = new Pair<>(state, String.valueOf(sequence.charAt(i)));
            if(transitions.containsKey(key))
                state = transitions.get(key).iterator().next();
            else
                return false;
        }

        return finalStates.contains(state);
    }

    @Override
    public String toString() {
        return "FiniteAutomaton{" +
                "alphabet=" + alphabet +
                ", states=" + states +
                ", finalStates=" + finalStates +
                ", initialState='" + initialState + '\'' +
                ", transitions=" + transitions +
                '}';
    }
}
