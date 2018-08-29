package module.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class HbaseInfoWritable implements Writable {
	
	private String message;
	private String seq;
	
	public HbaseInfoWritable(){
		
	}
	
	public HbaseInfoWritable(String message,String seq){
		this.setMessage(message);
		this.setSeq(seq);
	}
	
	public HbaseInfoWritable(HbaseInfoWritable info){
		if(info!=null){
			this.message=new String(info.message);
			this.seq=new String(info.getSeq());
		}
	}
	
	public HbaseInfoWritable getInfoWritable(){
		return this;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.message=new String(in.readUTF());
		this.seq=new String(in.readUTF());
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(this.message);
		out.writeUTF(this.seq);
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

}
