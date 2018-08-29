package module;

public class GpsInfo {

	private String ap;
	private String gps;
	private String address;
	
	public GpsInfo(String ap,String gps,String address){
		this.ap=ap;
		this.gps=gps;
		this.address=address;
	}
	
	public GpsInfo(GpsInfo info){
		if(info!=null){
			this.ap=new String(info.ap);
			this.gps=new String(info.gps);
			this.address=new String(info.address);
		}
	}
	
	public String getAP() {
		return ap;
	}
	
	public void setAP(String aP) {
		ap = aP;
	}
	
	public String getGps() {
		return gps;
	}
	
	public void setGps(String gps) {
		this.gps = gps;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

}
