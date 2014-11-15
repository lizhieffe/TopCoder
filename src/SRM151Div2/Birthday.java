package SRM151Div2;

public class Birthday {
	public String getNext(String cDate, String[] birthdays) {
		if (cDate == null || birthdays == null || birthdays.length == 0)
			return "";
		int now = getDate(cDate);
		int min = Integer.MAX_VALUE;
		String result = "";
		for (int i = 0; i < birthdays.length; i++) {
			int tmp = getDate(birthdays[i]);
			int diff = getDiff(now, tmp);
			if (diff < min) {
				min = diff;
				result = birthdays[i].split(" ")[0];
			}
			if (diff == 0)
				break;
		}
		return result;
	}
	
	private int getDate(String s) {
		String tmp = s.split(" ")[0];
		int month = Integer.parseInt(tmp.split("/")[0]);
		int day = Integer.parseInt(tmp.split("/")[1]);
		return 30 * (month - 1) + day;
	}
	
	private int getDiff(int now, int tmp) {
		if (tmp >= now)
			return tmp - now;
		else
			return 360 + tmp - now;
	}
}