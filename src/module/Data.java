package module;

public class Data {
	
	private double[] p;
	private String type;
	private int clusterNo=-1;
	
	public Data(double x,double y){
		p=new double[]{x,y};
		setType(null);
	}
	
	public Data(double x,double y,String type){
		p=new double[]{x,y};
		this.setType(new String(type));
	}
	
	public Data(Data data){
		p=new double[]{data.getP()[0],data.getP()[1]};
		type=new String(data.getType());
	}

	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double[] getP() {
		return p;
	}

	public void setP(double[] p) {
		this.p = p;
	}

	public int getClusterNo() {
		return clusterNo;
	}

	public void setClusterNo(int clusterNo) {
		this.clusterNo = clusterNo;
	}

}
