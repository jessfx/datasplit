package module.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class InfoWritable implements Writable {
	
	private String date;
	private String time;
	private String ap;
	private String mac;
	
	public InfoWritable(){

	}
	
	public InfoWritable(String date,String time,String ap,String mac){
		this.setDate(date);
		this.setTime(time);
		this.setAp(ap);
		this.setMac(mac);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.date=new String(in.readUTF());
		this.time=new String(in.readUTF());
		this.ap=new String(in.readUTF());
		this.mac=new String(in.readUTF());
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(this.date);
		out.writeUTF(this.time);
		out.writeUTF(this.ap);
		out.writeUTF(this.mac);
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

	public String getAp() {
		return ap;
	}

	public void setAp(String ap) {
		this.ap = ap;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
