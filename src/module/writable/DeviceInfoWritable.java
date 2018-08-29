package module.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class DeviceInfoWritable implements Writable {

	private String mac;
	private String date;
	private String time;
	private String address;
	
	public DeviceInfoWritable(String mac,String date,String time,String address){
		this.mac=mac;
		this.date=date;
		this.time=time;
		this.address=address;
	}
	
	public DeviceInfoWritable(){
		
	}
	
	public DeviceInfoWritable(DeviceInfoWritable deviceInfoWritable){
		mac=new String(deviceInfoWritable.getMac());
		date=new String(deviceInfoWritable.getDate());
		time=new String(deviceInfoWritable.getTime());
		address=new String(deviceInfoWritable.getAddress());
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		mac=new String(in.readUTF());
		date=new String(in.readUTF());
		time=new String(in.readUTF());
		address=new String(in.readUTF());
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(mac);
		out.writeUTF(date);
		out.writeUTF(time);
		out.writeUTF(address);
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

}
