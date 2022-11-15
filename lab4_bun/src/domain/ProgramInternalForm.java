package domain;

import java.util.ArrayList;

public class ProgramInternalForm {
    // (token, ((lineST, colST), tokenCode))
    private final ArrayList<Pair<String, Pair<Pair<Integer, Integer>, Integer>>> pif = new ArrayList<>();

    public void add(String token, Pair<Pair<Integer, Integer>, Integer> value) {
        Pair<String, Pair<Pair<Integer, Integer>, Integer>> elem = new Pair<>(token, value);
        pif.add(elem);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Pair<String, Pair<Pair<Integer, Integer>, Integer>> elem : pif) {
            res.append(elem.getKey()).append(" : (").append(elem.getValue().getKey().getKey()).append(", ")
                    .append(elem.getValue().getKey().getValue()).append("), ").append(elem.getValue().getValue()).append("\n");
        }
        return res.toString();
    }
}
