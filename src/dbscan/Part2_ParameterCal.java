package dbscan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.junit.Test;

import function.CriteriaFunc;

public class Part2_ParameterCal {

	private List<List<String>> dataList=new ArrayList<>();
	private String compus;
	static double Eps=0;
	static long MinPts=0;

	public void execute(String leaf){
		List<String> list=new ArrayList<>();
		String[] strings=leaf.split(";");
		for (String string : strings) {
			list.add(string);
		}
		dataList.add(list);
		Eps+=calEps(list);
	}
	
	public void calMinPts(){
		Eps/=dataList.size();
		for (List<String> list : dataList) {
			MinPts+=calMinPts(list);
		}
		MinPts/=dataList.size();
	}
	

	public double calEps(List<String> dataList){
		double sum=0;
		Vector<Double> p1=new Vector<>(),p2=new Vector<>();
		for(int i=0;i<dataList.size();i++){
			p1.clear();
			String[] strings0=dataList.get(i).split(",");
			p1.add(Double.valueOf(strings0[0]));
			p1.add(Double.valueOf(strings0[1]));
			for(int j=0;j<dataList.size();j++){
				p2.clear();
				String[] strings1=dataList.get(j).split(",");
				p2.add(Double.valueOf(strings1[0]));
				p2.add(Double.valueOf(strings1[1]));
				sum+=getDistance(p1, p2);
			}
		}
		sum/=dataList.size()*dataList.size();
		return sum;
	}
	
	public int calMinPts(List<String> dataList){
		Random random=new Random();
		double clusterNum=0;
		long total=dataList.size();
		Vector<Double> p1=new Vector<>(),p2=new Vector<>();
		while(dataList.size()!=0){
			int i=random.nextInt(dataList.size());
			p1.clear();
			String[] strings0=dataList.get(i).split(",");
			p1.add(Double.valueOf(strings0[0]));
			p1.add(Double.valueOf(strings0[1]));
			dataList.remove(i);
			clusterNum++;
			for(int j=0;j<dataList.size();j++){
				p2.clear();
				String[] strings1=dataList.get(j).split(",");
				p2.add(Double.valueOf(strings1[0]));
				p2.add(Double.valueOf(strings1[1]));
				double dist=getDistance(p1, p2);
				if(dist<=Eps){
					dataList.remove(j);
				}
			}
		}
		System.out.println(clusterNum);
		System.out.println(total/clusterNum);
		return (int) Math.ceil(total/clusterNum);
	}
	
	public static double getDistance(Vector<Double> p0,Vector<Double> p1)
	{
	   double radLat1 = rad(p0.get(1).doubleValue());
	   double radLat2 = rad(p1.get(1).doubleValue());
	   double a = radLat1 - radLat2;	
	   double b = rad(p0.get(0).doubleValue()) - rad(p1.get(0).doubleValue());
	   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
	    Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	   s = s * 6371.393;
	   s = Math.round(s * 1000);
	   return s;
	}
	
	private static double rad(double d)
	{
	   return d * Math.PI / 180.0;
	}
	
	public double getEps() {
		return Eps;
	}
	public void setEps(double eps) {
		Eps = eps;
	}
	public long getMinPts() {
		return MinPts;
	}
	public void setMinPts(int minPts) {
		MinPts = minPts;
	}

	public String getCompus() {
		return compus;
	}

	public void setCompus(String compus) {
		this.compus = compus;
	}
	

}
