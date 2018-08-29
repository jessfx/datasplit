package dbscan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class DbscanMapper extends Mapper<LongWritable, Text, Text, Text> {

	int count = 0;
	static int minp = 3;
	Text word;
	int index = 1;
	static double e = 213.59;
	
	{
		try {
			System.setOut(new PrintStream(new File("D:\\output")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		Test(value,context);
		
		//context.write(new IntWritable(count++), value);

	}

	private void Test(Text value,Context context) throws IOException, InterruptedException {
		System.out.println(value.toString());
		List<List<Point>> resultList = new ArrayList();
		Point p;
		boolean flag = false;
		String temp = "";
		int i, j = 0;
		List<Point> all = new ArrayList();
		List<Point> mergecandidates = new ArrayList();
		List<Point> noise = new ArrayList();

		// Read a line to String
		Point p1;
		/*
		 * String line = ""; Iterator<Text> itr = values.iterator(); while
		 * (itr.hasNext()) { line = itr.next().toString(); }
		 */
		String line = value.toString();
		// Split the line by for points;
		String[] points = line.split(";");
		String temp1 = points[points.length - 1];
		points[points.length - 1] = "";
		// Split the line by : for border
		for (String w : points) {
			if (!w.isEmpty()) {

				p1 = new Point(w);
				if (!all.contains(p1))
					all.add(p1);
			}
		}
		/*String[] border = temp1.split(":");
		for (String t : border) {
			p1 = new Point(t);
			p1.setBorder(true);
			if (!all.contains(p1))
				all.add(p1);
		}*/

		for (i = 0; i < all.size(); i++) {
			flag = false;
			p = all.get(i);
			if (!p.isClassed()) {
				List<Point> tmpLst = new ArrayList();
				if ((tmpLst = isKeyPoint(all, p, e, minp)) != null) {
					// End point for all clustering to mark
					setListClassed(tmpLst);
					resultList.add(tmpLst);
					
					/*
					 * for (j = 0; j < tmpLst.size(); j++) { if (tmpLst.get(j).isBorder() &&
					 * !mergecandidates.contains(tmpLst.get(j))) {
					 * mergecandidates.add(tmpLst.get(j)); flag = true; } } if (flag &&
					 * !mergecandidates.contains(p)) mergecandidates.add(p);
					 */

				} else
					noise.add(p);
			}
			
		}
		for (int k = 0; k < resultList.size(); k++) {
			for (int k2 = k+1; k2 <resultList.size(); k2++) {
				if(mergeList(resultList.get(k), resultList.get(k2))) {
					resultList.get(k2).clear();
				}
				
			}
			
		}
		for (int k3 = 0; k3 < resultList.size(); k3++) {
			
			System.out.println("cluster" + index);
			List<Point> templist = resultList.get(k3);
			for (int k4 = 0; k4 < templist.size(); k4++) {
				context.write(new Text("cluster" + index), new Text(templist.get(k4).print()+",cluster" + index));
				System.out.println(templist.get(k4).print());
			}
			index++;
		}
		
		/*for (Iterator<List<Point>> it = resultList.iterator(); it.hasNext();) {
	System.out.println("yes");
			List<Point> lst = it.next();
			if (lst.isEmpty()) {
				continue;
			}
			System.out.println("--��" + index + "--����");
			for (Iterator<Point> it1 = lst.iterator(); it1.hasNext();) {
				Point t1 = it1.next();
				
				  if (mergecandidates.contains(t1)) { temp = t1.print() + " " + index + " " +
				  "1"+ t1.isKey(); } else { temp = t1.print() + " " + index + " " + "0" +
				  t1.isKey(); }
				 
				Point point = it1.next();
				System.out.println(point.print());

				word = new Text();
				word.set(temp);
				// context.write(key, word);
				temp = "";
			}
			index++;
		}
		/*int length = resultList.size();
		System.out.println("length"+length);
		
		
		for (i = 0; i < length; i++) {
			for (j = i + 1; j < length; j++) {
				if (mergeList(resultList.get(i), resultList.get(j))) {
					resultList.get(j).clear();
				}
			}
		}
        
		for (Iterator<List<Point>> it = resultList.iterator(); it.hasNext();) {
			
			List<Point> lst = it.next();
			if (lst.isEmpty()) {
				continue;
			}
			System.out.println("--��" + index + "--����");
			for (Iterator<Point> it1 = lst.iterator(); it1.hasNext();) {
				Point t1 = it1.next();
				
				 * if (mergecandidates.contains(t1)) { temp = t1.print() + " " + index + " " +
				 * "1"+ t1.isKey(); } else { temp = t1.print() + " " + index + " " + "0" +
				 * t1.isKey(); }
				 
				Point point = it1.next();
				System.out.println(point.print());

				word = new Text();
				word.set(temp);
				// context.write(key, word);
				temp = "";
			}
			index++;
		}*/

	}

	private void setListClassed(List<Point> tmpLst) {
		for (Iterator<Point> it = tmpLst.iterator(); it.hasNext();) {
			Point p = it.next();
			if (!p.isClassed()) {
				p.setClassed(true);
			}
		}
		
	}

	private boolean mergeList(List<Point> list, List<Point> list2) {
		boolean merge = false;
		for (int index = 0; index < list2.size(); index++) {
			if (list.contains(list2.get(index))) {
				merge = true;
				break;
			}
		}
		if (merge) {
			for (int index = 0; index < list2.size(); index++) {
				if (!list.contains(list2.get(index))) {
					list.add(list2.get(index));
				}
			}
		}
		return merge;
	}

	private List<Point> isKeyPoint(List<Point> all, Point p, double e2, int minp2) {
		int count = 0;
		List<Point> tmpLst = new ArrayList<Point>();
		for (Iterator<Point> it = all.iterator(); it.hasNext();) {
			Point q = it.next();
			if (getDistance(p, q) <= e) {
				++count;
				if (!tmpLst.contains(q)) {
					tmpLst.add(q);
				}
			}
		}
		if (count >= minp) {
			p.setKey(true);
			return tmpLst;
		}
		return null;
	}

	private double getDistance(Point p, Point q) {
		double dx = Math.pow((p.getX() - q.getX())*6371004/180*Math.PI, 2);
		double dy = Math.pow((p.getY() - q.getY())*6371004/180*Math.PI, 2);
		double distance = Math.sqrt(dx + dy);
		return distance;
	}

}
