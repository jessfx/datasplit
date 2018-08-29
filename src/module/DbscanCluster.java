package module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class DbscanCluster {
	
	private List<Data> centList=new ArrayList<>();
	private List<Data> memList=new ArrayList<>();
	
	public void addCent(Data cent){
		centList.add(cent);
	}
	
	public DbscanCluster(){
		
	}
	
	public DbscanCluster(DbscanCluster cluster){
		centList.addAll(cluster.getCentList());
		memList.addAll(cluster.getMemList());
	}
	
	public void addMem(Data mem){
		if(getMemList().indexOf(mem)==-1){
			getMemList().add(mem);
		}
	}
	
	public void addMems(List<Data> memList){
		for (Data vector : memList) {
			addMem(vector);
		}
	}

	public List<Data> getCentList() {
		return centList;
	}

	public void setCentList(List<Data> centList) {
		this.centList = centList;
	}

	public List<Data> getMemList() {
		return memList;
	}

	public void setMemList(List<Data> memList) {
		this.memList = memList;
	}

}
