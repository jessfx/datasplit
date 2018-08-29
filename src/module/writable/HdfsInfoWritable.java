package module.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class HdfsInfoWritable implements Writable {
	
	private String facility;
	private String priority;
	private String date;
	private String time;
	private String host;
	private String message;
	private String seq;
	
	public HdfsInfoWritable(){
		
	}
	
	public HdfsInfoWritable(String facility,String priority,String date,String time,String host,String message,String seq){
		this.setFacility(facility);
		this.setPriority(priority);
		this.setDate(date);
		this.setTime(time);
		this.setHost(host);
		this.setMessage(message);
		this.setSeq(seq);
	}
	
	public HdfsInfoWritable(HdfsInfoWritable info){
		if(info!=null){
			this.facility=new String(info.facility);
			this.priority=new String(info.priority);
			this.date=new String(info.date);
			this.time=new String((info.time));
			this.host=new String(info.host);
			this.message=new String(info.message);
			this.seq=new String(info.seq);
		}
	}
	
	public HdfsInfoWritable getInfoWritable(){
		return this;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.facility=new String(in.readUTF());
		this.priority=new String(in.readUTF());
		this.date=new String(in.readUTF());
		this.time=new String(in.readUTF());
		this.host=new String(in.readUTF());
		this.message=new String(in.readUTF());
		this.seq=new String(in.readUTF());
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(this.facility);
		out.writeUTF(this.priority);
		out.writeUTF(this.date);
		out.writeUTF(this.time);
		out.writeUTF(this.host);
		out.writeUTF(this.message);
		out.writeUTF(this.seq);
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
