import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataPartitionDriver {
	static int epsilon = 2;
	static int DIM = 0;
	static int NUMR = 0;
	static float[] low;
	static int range = 5;
	static float[] dim;
	static float[] zeroes;
	static RTree<Integer> tree;
	static List<DataObject> rects = new ArrayList();

	static int partitions = 0;

	public static void takeInput() {

		try {
			FileInputStream fstream1 = new FileInputStream("point.txt");
			BufferedReader br1;
			br1 = new BufferedReader(new InputStreamReader(fstream1));
		
			NUMR = Integer.parseInt(br1.readLine());
			partitions = NUMR / 7;	
			DIM = Integer.parseInt(br1.readLine());
			tree = new RTree<Integer>(10, 2, DIM, RTree.SeedPicker.LINEAR);
			Driver d = new Driver();
			rects.addAll(d.createList(DIM));
			br1.close();
		} catch (Exception e) {
			System.out.println("Error1");
		}
	}

	public static void main(String args[]) {
		long start = new Date().getTime();
		takeInput();
		insert();
		System.out.println(tree.size + "	" + partitions);
		String filename = "output.txt";
		tree.addLeaf(filename, partitions, epsilon);
		long end = new Date().getTime();
		System.out.println("Job took " + (end - start) + "milliseconds");
		
		
	}

	public static void insert() {
		int i = 0;
		DataObject pi;
		for (i = 0; i < rects.size(); i++) {
			pi = rects.get(i);

			tree.insert(pi.val, pi.dim, pi.id);
		}
		
	}

	
}
