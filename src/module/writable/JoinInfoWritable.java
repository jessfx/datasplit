package module.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

import module.GpsInfo;

public class JoinInfoWritable implements Writable {
	
	private String date;
	private String time;
	private String mac;
	private String gps;
	private String address;
	
	public JoinInfoWritable(){
		
	}
	
	public JoinInfoWritable(String date,String time,String mac,String gps,String address){
		this.setDate(date);
		this.setTime(time);
		this.setMac(mac);
		this.setGps(gps);
		this.setAddress(address);
	}
	
	public JoinInfoWritable(JoinInfoWritable info){
		date=new String(info.date);
		time=new String(info.time);
		mac=new String(info.mac);
		gps=new String(info.gps);
		address=new String(info.address);
	}
	
	public void set(JoinInfoWritable info){
		date=new String(info.date);
		time=new String(info.time);
		mac=new String(info.mac);
		gps=new String(info.gps);
		address=new String(info.address);
	}
	
	public void set(String date,String time,String mac,String gps,String address){
		this.setDate(date);
		this.setTime(time);
		this.setMac(mac);
		this.setGps(gps);
		this.setAddress(address);
	}
	
	@Override
	public String toString(){
		return mac+" "+date+" "+time+" "+gps+" "+address;
	}
	
	public JoinInfoWritable(InfoWritable infoWritable,GpsInfo gpsInfo){
		this.setDate(infoWritable.getDate());
		this.setTime(infoWritable.getTime());
		this.setMac(infoWritable.getMac());
		this.setGps(gpsInfo.getGps());
		this.setAddress(gpsInfo.getAddress());
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.date=new String(in.readUTF());
		this.time=new String(in.readUTF());
		this.mac=new String(in.readUTF());
		this.gps=new String(in.readUTF());
		this.address=new String(in.readUTF());
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(this.date);
		out.writeUTF(this.time);
		out.writeUTF(this.mac);
		out.writeUTF(this.gps);
		out.writeUTF(this.address);
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
	
	public String getMac() {
		return mac;
	}
	
	public void setMac(String mac) {
		this.mac = mac;
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
