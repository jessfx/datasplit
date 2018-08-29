import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;




public class Driver {
	
	public List<DataObject> createList(int DIM){
		int i,j=0;
		String str;
		List<DataObject> rects =new ArrayList();
		double[] zeroes= new double[DIM];
		double[] tempf;
		for(i=0;i<DIM;i++){
			zeroes[i]=0;
		}
		try{
			FileInputStream fstream1 = new FileInputStream("point.txt");
			BufferedReader br ;
			br=new BufferedReader(new InputStreamReader(fstream1));
			br.readLine();
			br.readLine();
			while((str=br.readLine())!=null){
			j++;
			String[] temp=str.split(",");
			tempf=new double[DIM];
			for(i=0;i<DIM;i++){
				tempf[i]=Double.parseDouble(temp[i]);
				
			}
			 rects.add(new DataObject(tempf,zeroes,j));
		}
		br.close();
		}catch(Exception e){
			System.out.println("Error");
		}
	return rects;	
	}
	
 
	   
}
