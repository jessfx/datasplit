package module.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class MetaDataWritable implements Writable {

	private String mac;
	private String date;
	private String time;
	private String gps;
	private String address;
	
	public MetaDataWritable(String mac,String date,String time,String gps,String address){
		this.mac=mac;
		this.date=date;
		this.time=time;
		this.gps=gps;
		this.address=address;
	}
	
	public MetaDataWritable(){
		
	}
	
	public MetaDataWritable(MetaDataWritable deviceInfoWritable){
		mac=new String(deviceInfoWritable.getMac());
		date=new String(deviceInfoWritable.getDate());
		time=new String(deviceInfoWritable.getTime());
		gps=new String(deviceInfoWritable.getGps());
		address=new String(deviceInfoWritable.getAddress());
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		mac=new String(in.readUTF());
		date=new String(in.readUTF());
		time=new String(in.readUTF());
		gps=new String(in.readUTF());
		address=new String(in.readUTF());
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(mac);
		out.writeUTF(date);
		out.writeUTF(time);
		out.writeUTF(gps);
		out.writeUTF(address);
	}
	
	public String toString(){
		return mac+" "+date+" "+time+" "+gps+" "+address;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

}
