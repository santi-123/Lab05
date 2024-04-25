package domain;

import java.util.ArrayList;
import java.util.Random;

public class Square {
    private final int size = 5;
    private ArrayList<ArrayList<Integer>> fillerPositions;
    private ArrayList<ArrayList<Integer>> holePositions;

    public Square(){
        fillerPositions = setAletoryPositionsF();
        holePositions = setAletoryPositionsH(fillerPositions);
    }

    private ArrayList<ArrayList<Integer>> setAletoryPositionsF(){
        ArrayList<ArrayList<Integer>> positions = new ArrayList<ArrayList<Integer>>();
        int centinela = 0;
        while (centinela < size-1){
            ArrayList<Integer> singularPosition = new ArrayList<>();
            Random random = new Random();
            singularPosition.add(random.nextInt(size));
            singularPosition.add(random.nextInt(size));

            if(!positions.contains(singularPosition)){
                positions.add(singularPosition);
                centinela += 1;
            }
        }
        System.out.println(positions);
        return positions;
    }

    private ArrayList<ArrayList<Integer>> setAletoryPositionsH(ArrayList<ArrayList<Integer>> holeList){
        ArrayList<ArrayList<Integer>> positions = new ArrayList<ArrayList<Integer>>();
        int centinela = 0;
        while (centinela < size-1){
            ArrayList<Integer> singularPosition = new ArrayList<>();
            Random random = new Random();
            singularPosition.add(random.nextInt(size));
            singularPosition.add(random.nextInt(size));

            if(!positions.contains(singularPosition) && !holeList.contains(singularPosition)){
                positions.add(singularPosition);
                centinela += 1;
            }
        }
        System.out.println(positions);
        return positions;
    }

    public ArrayList<ArrayList<Integer>> getFillerPositionsAletoryPositions(){
        return fillerPositions;
    }

    public ArrayList<ArrayList<Integer>> getHoleositionsAletoryPositions(){
        return holePositions;
    }

    public int getSize(){return size; }

    public void move(){}
}
