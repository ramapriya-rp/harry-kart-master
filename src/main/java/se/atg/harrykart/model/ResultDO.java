package se.atg.harrykart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ResultDO implements Comparable<ResultDO>{
    int position;
    String horse;
    @JsonIgnore
    double points;


    public ResultDO(int position, String horse, double time) {
        this.position = position;
        this.horse = horse;
        this.points = time;
    }
	
   
    /*
     *  comparing the points and selecting the order 
     * */
    @Override
    public int compareTo(ResultDO other) {
        if(this.getPoints() == other.getPoints())
            return 0;
        else if(this.getPoints() > other.getPoints())
            return 1;
        else
            return -1;
    }

   
    @Override
    public String toString() {
        return "[" +
                "position=" + position +
                ", horse='" + horse + '\'' +
               // ", Points =" + points +
                ']';
    }
}