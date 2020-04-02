package tema_5;

public class ActivityTime {
	long days, hr, min, sec;

	public ActivityTime(long days, long hr, long min, long sec) {
		this.days = days;
		this.hr = hr;
		this.min = min;
		this.sec = sec;
	}

	public ActivityTime() {}

	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}

	public long getHr() {
		return hr;
	}

	public void setHr(long hr) {
		this.hr = hr;
	}

	public long getMin() {
		return min;
	}

	public void setMin(long min) {
		this.min = min;
	}

	public long getSec() {
		return sec;
	}

	public void setSec(long sec) {
		this.sec = sec;
	}
	
	public void update(ActivityTime a) {
		long d,h,m,s;
		a.setMin(a.getMin()+a.getSec()/60);
		a.setSec(a.getSec()%60);
		a.setHr(a.getHr()+a.getMin()/60);
		a.setMin(a.getMin()%60);
		a.setDays(a.getDays()+a.getHr()/24);
		a.setHr(a.getHr()%24);
	}
}
