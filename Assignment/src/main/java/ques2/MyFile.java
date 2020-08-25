package ques2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

class MyFile {

	public static enum Months {
		JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
	}

	private Months month;

	public Months getMonth() {
		return month;
	}

	public MyFile(Months month) {
		this.month = month;
	}

	public static List<MyFile> files() {

		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		File folder = new File("c:/Madhur");
		List<MyFile> list = new ArrayList<MyFile>();
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				String month = sdf.format(file.lastModified());
				if (month.equals("01")) {
					list.add(new MyFile(Months.JANUARY));
				} else if (month.equals("02")) {
					list.add(new MyFile(Months.FEBRUARY));
				} else if (month.equals("03")) {
					list.add(new MyFile(Months.MARCH));
				} else if (month.equals("04")) {
					list.add(new MyFile(Months.APRIL));
				} else if (month.equals("05")) {
					list.add(new MyFile(Months.MAY));
				} else if (month.equals("06")) {
					list.add(new MyFile(Months.JUNE));
				} else if (month.equals("07")) {
					list.add(new MyFile(Months.JULY));
				} else if (month.equals("08")) {
					list.add(new MyFile(Months.AUGUST));
				} else if (month.equals("09")) {
					list.add(new MyFile(Months.SEPTEMBER));
				} else if (month.equals("10")) {
					list.add(new MyFile(Months.OCTOBER));
				} else if (month.equals("11")) {
					list.add(new MyFile(Months.NOVEMBER));
				} else if (month.equals("12")) {
					list.add(new MyFile(Months.DECEMBER));
				}
			}
		}
		return list;
	}
}