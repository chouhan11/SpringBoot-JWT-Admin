package ques2;

import java.util.Map;
import java.util.stream.Collectors;

public class FileCount {

	public static void main(String[] args) {
		// Example output
		// {AUGUST=1, SEPTEMBER=1, DECEMBER=2, JANUARY=2}
		Map<MyFile.Months, Long> count = MyFile.files().stream()
				.collect(Collectors.groupingBy(MyFile::getMonth, Collectors.counting()));
		System.out.println("Number of files by month : " + count);
	}
}
