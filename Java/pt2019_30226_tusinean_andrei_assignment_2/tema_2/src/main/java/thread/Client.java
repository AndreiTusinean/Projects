package thread;

public class Client {
	private int arrTime,fnTime;
	int prcTime;
	int ID;
	
	public Client(int arrTime,int prcTime,int ID) {
		this.arrTime=arrTime;
		this.prcTime=prcTime;
		this.ID=ID;
	}
	
	public Client() {}

	public int getArrTime() {
		return arrTime;
	}

	public void setArrTime(int arrTime) {
		this.arrTime = arrTime;
	}

	public int getFnTime() {
		return fnTime;
	}

	public void setFnTime(int fnTime) {
		this.fnTime = fnTime;
	}

	public int getPrcTime() {
		return prcTime;
	}

	public void setPrcTime(int prcTime) {
		this.prcTime = prcTime;
	}
	
	public String toString() {
		return "Client "+ID+" ajuns la "+arrTime+" cu prc time: "+prcTime;
	}
}
