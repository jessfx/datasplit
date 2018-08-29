package module;

public class Info {
	
	private String facility;
	private String priority;
	private String date;
	private String time;
	private String host;
	private String message;
	private String seq;
	
	public Info(){
		
	}
	
	public Info(String facility,String priority,String date,String time,String host,String message,String seq){
		this.setFacility(facility);
		this.setPriority(priority);
		this.setDate(date);
		this.setTime(time);
		this.setMessage(message);
		this.setSeq(seq);
	}
	
	public Info(Info info){
		if(info!=null){
			this.facility=new String(info.facility);
			this.priority=new String(info.priority);
			this.date=new String(info.date);
			this.time=new String(info.time);
			this.host=new String(info.host);
			this.message=new String(info.message);
			this.seq=new String(info.seq);
		}
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
