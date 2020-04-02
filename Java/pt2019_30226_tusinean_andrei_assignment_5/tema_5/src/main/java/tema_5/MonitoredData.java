package tema_5;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonitoredData {
	String start, end, label;
	static List<MonitoredData> data = new ArrayList<MonitoredData>();
	static Map<String, Integer> map = new HashMap<>();
	static Map<String, Map<String, Integer>> eachDayAppear = new HashMap<>();
	static int Leaving = 0, Toileting = 0, Showering = 0, Sleeping = 0, Breakfast = 0, Lunch = 0, Dinner = 0, Snack = 0,
			Spare_Time = 0, Grooming = 0, uLeaving = 0, uToileting = 0, uShowering = 0, uSleeping = 0, uBreakfast = 0,
			uLunch = 0, uDinner = 0, uSnack = 0, uSpare_Time = 0, uGrooming = 0, leave = 0, toil = 0, show = 0,
			sleep = 0, breakf = 0, lunch = 0, dinner = 0, snack = 0, spare = 0, groom = 0;
	static ActivityTime tLeaving = new ActivityTime(0, 0, 0, 0);
	static ActivityTime tToileting = new ActivityTime(0, 0, 0, 0);
	static ActivityTime tShowering = new ActivityTime(0, 0, 0, 0);
	static ActivityTime tSleeping = new ActivityTime(0, 0, 0, 0);
	static ActivityTime tBreakfast = new ActivityTime(0, 0, 0, 0);
	static ActivityTime tLunch = new ActivityTime(0, 0, 0, 0);
	static ActivityTime tDinner = new ActivityTime(0, 0, 0, 0);
	static ActivityTime tSnack = new ActivityTime(0, 0, 0, 0);
	static ActivityTime tSpare_Time = new ActivityTime(0, 0, 0, 0);
	static ActivityTime tGrooming = new ActivityTime(0, 0, 0, 0);
	static ActivityTime at = new ActivityTime();

	public MonitoredData(String start, String end, String label) {
		this.start = start;
		this.end = end;
		this.label = label;
	}

	public static void read() {
		List<String> list = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get("Activities.txt"))) {
			list = stream.filter(line -> !line.startsWith(" ")).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			String[] spl = list.get(i).split("		", 3);
			MonitoredData aux = new MonitoredData(spl[0], spl[1], spl[2]);
			data.add(aux);
		}
	}

	public static void dataPrint() {
		List<MonitoredData> d = data.stream().collect(Collectors.toList());
		d.forEach((m)->System.out.println(m.toString()));
	}

	public static int days(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	public static int daysBetween() {
		int days = 0;
		MonitoredData d1 = data.get(0);
		MonitoredData d2 = data.get(data.size() - 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = sdf.parse(d1.start.substring(0, 10));
			Date date2 = sdf.parse(d2.end.substring(0, 10));
			days = days(date1, date2) + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}

	public String getActivity() {
		return label;
	}
	
	public String getStartTime() {
		return start;
	}
	
	public String getEndTime() {
		return end;
	}

public static Map<String, Integer> totalAppear() {

		Map<String, Integer> counted = data.stream().collect(Collectors.toMap(md -> md.getActivity(), md -> 1, Integer::sum));
	
		List<MonitoredData> d = data.stream().collect(Collectors.toList());
			d.forEach((m)->
			{
			switch (m.label.replaceAll("\\s+", "")) {
			case "Leaving":
				Leaving++;
				break;
			case "Toileting":
				Toileting++;
				break;
			case "Showering":
				Showering++;
				break;
			case "Sleeping":
				Sleeping++;
				break;
			case "Breakfast":
				Breakfast++;
				break;
			case "Lunch":
				Lunch++;
				break;
			case "Dinner":
				Dinner++;
				break;
			case "Snack":
				Snack++;
				break;
			case "Spare_Time/TV":
				Spare_Time++;
				break;
			case "Grooming":
				Grooming++;
				break;
			}
		});
		map.put("Leaving", Leaving);
		map.put("Toileting", Toileting);
		map.put("Showering ", Showering);
		map.put("Sleeping", Sleeping);
		map.put("Breakfast", Breakfast);
		map.put("Lunch", Lunch);
		map.put("Dinner", Dinner);
		map.put("Snack", Snack);
		map.put("Spare_Time/TV", Spare_Time);
		map.put("Grooming", Grooming);
		
		return counted;
	}

	public static Map<String, Map<String, Integer>> eachDayAppear() {		
		Map<String, Map<String, Integer>> counted = data.stream()
				.collect(Collectors.groupingBy(md -> md.getStartTime().substring(0, 10),
						Collectors.toMap(md -> md.getActivity(), md -> 1, Integer::sum)));
		
		/*List<MonitoredData> d = data.stream().collect(Collectors.toList());
		d.forEach((m1)->
		{
			Map<String, Integer> x = new HashMap<>();			
			d.forEach((m2)->
			{
				if(m1.start.substring(0, 10).contains(m2.start.substring(0, 10))){
					switch (m2.label.replaceAll("\\s+", "")) {
					case "Leaving":
						leave++;
						x.put("Leaving", leave);
						break;
					case "Toileting":
						toil++;
						x.put("Toileting", toil);
						break;
					case "Showering":
						show++;
						x.put("Showering", show);
						break;
					case "Sleeping":
						sleep++;
						x.put("Sleeping", sleep);
						break;
					case "Breakfast":
						breakf++;
						x.put("Breakfast", breakf);
						break;
					case "Lunch":
						lunch++;
						x.put("Lunch", lunch);
						break;
					case "Dinner":
						dinner++;
						x.put("Dinner", dinner);
						break;
					case "Snack":
						snack++;
						x.put("Snack", snack);
						break;
					case "Spare_Time/TV":
						spare++;
						x.put("Spare_Time/TV", spare);
						break;
					case "Grooming":
						groom++;
						x.put("Grooming", groom);
						break;
					}
				}
			});
			leave = 0; toil = 0; show = 0; sleep = 0; breakf = 0; lunch = 0; dinner = 0; snack = 0; spare = 0; groom = 0;
			eachDayAppear.put(m1.start.substring(0, 10), x);
		});
		return eachDayAppear;
		*/
		return counted;
	}

	public long duration() {
		Date d1 = null, d2 = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d1 = f.parse(getStartTime());
			d2 = f.parse(getEndTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long x = d2.getTime() - d1.getTime();
		return x;
	}
	
	public static String times(long diff) {
		long sec = diff / 1000 % 60;
		long min = diff / (60 * 1000) % 60;
		long hr = diff / (60 * 60 * 1000) % 24;
		long dy = diff / (24 * 60 * 60 * 1000);
		String s = hr + " hours, " + min + " minutes, " + sec + " seconds.";
		return s;
	}
	
	static int cnt = 0;
	public static void eachTime() {

		Stream<String> x = data.stream().map(md -> md.getActivity() + "			" + times(md.duration()));		
		x.forEach(s -> {
			System.out.println("start " + data.get(cnt).start + "\t end " + data.get(cnt).end + "\t" + s);
		    cnt++;
		});
		
//		List<MonitoredData> d = data.stream().collect(Collectors.toList());
//		d.forEach((m)->
//		{
//			Date d1;
//			try {
//				d1 = sdf.parse(m.start);
//				Date d2 = sdf.parse(m.end);
//				long diff = d2.getTime() - d1.getTime();
//				long sec = diff / 1000 % 60;
//				long min = diff / (60 * 1000) % 60;
//				long hr = diff / (60 * 60 * 1000) % 24;
//				long dy = diff / (24 * 60 * 60 * 1000);
//
//				String s = m.toString();
//				if (m.label.contains("Grooming") || m.label.contains("Leaving") || m.label.contains("Snack"))
//					s += "		";
//				else {
//					if (!m.label.contains("Spare_Time/TV"))
//						s += "	";
//				}
//				s += hr + " hours, " + min + " minutes, " + sec + " seconds.";// dy + " days, " +
//				System.out.println(s);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		});
	}

	public static void entireTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<MonitoredData> d = data.stream().collect(Collectors.toList());
		d.forEach((m)->
		{
			Date d1;
			try {
				d1 = sdf.parse(m.start);
				Date d2 = sdf.parse(m.end);
				long diff = d2.getTime() - d1.getTime();
				long sec = diff / 1000 % 60;
				long min = diff / (60 * 1000) % 60;
				long hr = diff / (60 * 60 * 1000) % 24;
				long dy = diff / (24 * 60 * 60 * 1000);
				switch (m.label.replaceAll("\\s+", "")) {
				case "Leaving":
					tLeaving.setDays(tLeaving.getDays() + dy);tLeaving.setHr(tLeaving.getHr() + hr);tLeaving.setMin(tLeaving.getMin() + min);tLeaving.setSec(tLeaving.getSec() + sec);
					break;
				case "Toileting":
					tToileting.setDays(tToileting.getDays() + dy);tToileting.setHr(tToileting.getHr() + hr);tToileting.setMin(tToileting.getMin() + min);tToileting.setSec(tToileting.getSec() + sec);
					break;
				case "Showering":
					tShowering.setDays(tShowering.getDays()+dy);tShowering.setHr(tShowering.getHr()+hr);tShowering.setMin(tShowering.getMin()+min);tShowering.setSec(tShowering.getSec()+sec);
					break;
				case "Sleeping":
					tSleeping.setDays(tSleeping.getDays()+dy);tSleeping.setHr(tSleeping.getHr()+hr);tSleeping.setMin(tSleeping.getMin()+min);tSleeping.setSec(tSleeping.getSec()+sec);
					break;
				case "Breakfast":
					tBreakfast.setDays(tBreakfast.getDays()+dy);tBreakfast.setHr(tBreakfast.getHr()+hr);tBreakfast.setMin(tBreakfast.getMin()+min);tBreakfast.setSec(tBreakfast.getSec()+sec);
					break;
				case "Lunch":
					tLunch.setDays(tLunch.getDays()+dy);tLunch.setHr(tLunch.getHr()+hr);tLunch.setMin(tLunch.getMin()+min);tLunch.setSec(tLunch.getSec()+sec);
					break;
				case "Dinner":
					tDinner.setDays(tDinner.getDays()+dy);tDinner.setHr(tDinner.getHr()+hr);tDinner.setMin(tDinner.getMin()+min);tDinner.setSec(tDinner.getSec()+sec);
					break;
				case "Snack":
					tSnack.setDays(tSnack.getDays()+dy);tSnack.setHr(tSnack.getHr()+hr);tSnack.setMin(tSnack.getMin()+min);tSnack.setSec(tSnack.getSec()+sec);
					break;
				case "Spare_Time/TV":
					tSpare_Time.setDays(tSpare_Time.getDays()+dy);tSpare_Time.setHr(tSpare_Time.getHr()+hr);tSpare_Time.setMin(tSpare_Time.getMin()+min);tSpare_Time.setSec(tSpare_Time.getSec()+sec);
					break;
				case "Grooming":
					tGrooming.setDays(tGrooming.getDays()+dy);tGrooming.setHr(tGrooming.getHr()+hr);tGrooming.setMin(tGrooming.getMin()+min);tGrooming.setSec(tGrooming.getSec()+sec);
					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
		at.update(tLeaving);
		at.update(tToileting);
		at.update(tShowering);
		at.update(tSleeping);
		at.update(tBreakfast);
		at.update(tLunch);
		at.update(tDinner);
		at.update(tSnack);
		at.update(tSpare_Time);
		at.update(tGrooming);
		System.out.println("\nLeaving: " + tLeaving.days + " days, " + tLeaving.hr + " hours, " + tLeaving.min + " minutes, " + tLeaving.sec + " seconds ");
		System.out.println("Toileting: " + tToileting.days + " days, " + tToileting.hr + " hours, " + tToileting.min + " minutes, " + tToileting.sec + " seconds ");
		System.out.println("Showering: " + tShowering.days + " days, " + tShowering.hr + " hours, " + tShowering.min + " minutes, " + tShowering.sec + " seconds ");
		System.out.println("Sleeping: " + tSleeping.days + " days, " + tSleeping.hr + " hours, " + tSleeping.min + " minutes, " + tSleeping.sec + " seconds ");
		System.out.println("Breakfast: " + tBreakfast.days + " days, " + tBreakfast.hr + " hours, " + tBreakfast.min + " minutes, " + tBreakfast.sec + " seconds ");
		System.out.println("Lunch: " + tLunch.days + " days, " + tLunch.hr + " hours, " + tLunch.min + " minutes, " + tLunch.sec + " seconds ");
		System.out.println("Dinner: " + tDinner.days + " days, " + tDinner.hr + " hours, " + tDinner.min + " minutes, " + tDinner.sec + " seconds ");
		System.out.println("Snack: " + tSnack.days + " days, " + tSnack.hr + " hours, " + tSnack.min + " minutes, " + tSnack.sec + " seconds ");
		System.out.println("Spare_Time: " + tSpare_Time.days + " days, " + tSpare_Time.hr + " hours, " + tSpare_Time.min + " minutes, " + tSpare_Time.sec + " seconds ");
		System.out.println("Grooming: " + tGrooming.days + " days, " + tGrooming.hr + " hours, " + tGrooming.min+ " minutes, " + tGrooming.sec + " seconds ");
	}

	public static void filter() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<MonitoredData> d = data.stream().collect(Collectors.toList());
		d.forEach((m)->
		{
			Date d1;
			try {
				d1 = sdf.parse(m.start);
				Date d2 = sdf.parse(m.end);
				long diff = d2.getTime() - d1.getTime();
				long sec = diff / 1000 % 60;
				long min = diff / (60 * 1000) % 60;
				long hr = diff / (60 * 60 * 1000) % 24;
				long dy = diff / (24 * 60 * 60 * 1000);
				if (dy == 0 && hr == 0 && min < 5) {
					switch (m.label.replaceAll("\\s+", "")) {
					case "Leaving":
						uLeaving++;
						break;
					case "Toileting":
						uToileting++;
						break;
					case "Showering":
						uShowering++;
						break;
					case "Sleeping":
						uSleeping++;
						break;
					case "Breakfast":
						uBreakfast++;
						break;
					case "Lunch":
						uLunch++;
						break;
					case "Dinner":
						uDinner++;
						break;
					case "Snack":
						uSnack++;
						break;
					case "Spare_Time/TV":
						uSpare_Time++;
						break;
					case "Grooming":
						uGrooming++;
						break;
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
		System.out.println("\n90% under 5min:");
		String s="";
		if((double)uLeaving/Leaving>0.9)
			s+="Leaving= " + uLeaving;
		if((double)uToileting/Toileting>0.9)
			s+=" Toileting= " + uToileting;
		if((double)uShowering/Showering>0.9)
			s+=" Showering= " + uShowering;
		if((double)uSleeping/Sleeping>0.9)
			s+=" Sleeping= " + uSleeping;
		if((double)uBreakfast/Breakfast>0.9)
			s+=" Breakfast= " + uBreakfast;
		if((double)uLunch/Lunch>0.9)
			s+=" Lunch= " + uLunch;
		if((double)uDinner/Dinner>0.9)
			s+=" Dinner= " + uDinner;
		if((double)uSnack/Snack>0.9)
			s+=" Snack= " + uSnack;
		if((double)uSpare_Time/Spare_Time>0.9)
			s+=" Spare_Time/TV= " + uSpare_Time;
		if((double)uGrooming/Grooming>0.9)
			s+=" Grooming= " + uGrooming;
//		System.out.println(map.toString());
//		System.out.println("Leaving= " + uLeaving + " Toileting=" + uToileting + " Showering= " + uShowering
//				+ " Sleeping= " + uSleeping + " Breakfast= " + uBreakfast + " Lunch= " + uLunch + " Dinner= " + uDinner
//				+ " Snack= " + uSnack + " Spare_Time/TV= " + uSpare_Time + " Grooming= " + uGrooming);
		System.out.println(s);
	}
	
	public String toString() {
		return "start: " + start + "		end: " + end + "		label: " + label;
	}

	public static void main(String[] args) {

		read();
		dataPrint();
		System.out.println("Total days: " + daysBetween());
		map = totalAppear();
		System.out.println(map.toString());
		eachDayAppear = eachDayAppear();
		System.out.println(eachDayAppear.toString());
		eachTime();
		entireTime();
		filter();
	}
}
