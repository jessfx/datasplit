package function;

import java.util.Vector;

/**
 * Created by 哲帆 on 2018.5.5.
 */
public class CriteriaFunc {

    public static double euclidFunc(Vector<Double> p0, Vector<Double> p1) {
        double sum = 0;
        for (int i = 0; i < p0.size(); i++) {
            sum += (p0.get(i).doubleValue() - p1.get(i).doubleValue()) * (p0.get(i).doubleValue() - p1.get(i).doubleValue());
        }
        return Math.sqrt(sum);
    }
    
    public static double euclidEarthFunc(double[] p0,double[] p1){
        return Math.sqrt(
				Math.pow((p0[0]-p1[0])*6371004/180*Math.PI, 2)+
				Math.pow((p0[1]-p1[1])*6371004/180*Math.PI, 2)
				);
    }

}
