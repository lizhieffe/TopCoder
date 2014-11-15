package SRM152Div2;

public class DotForm {

	public class ProblemWriting {
		public String myCheckData(String dotForm) {
			if (dotForm == null || dotForm.length() == 0 || dotForm.length() > 25)
				return "dotForm must contain between 1 and 25 characters, inclusive.";
			int result = check(dotForm, 0, dotForm.length());
			if (result != -1)
				return "dotForm is not in dot notation, check character " + result + ".";
			return "";
		}
		
		private int check(String dotForm, int beg, int end) {
			if (end < beg)
				return -1;
			for (int i = beg; i <= end; i++) {
				if (!isValidChar(dotForm.charAt(i)))
					return i;
				if (i == end && !isNum(dotForm.charAt(end)))
					return end;
				if (isNum(dotForm.charAt(end))) {
					int tmp = checkDotForm(dotForm, beg, i);
					if (tmp != -1)
						return tmp;
					return check(dotForm, i + 1, end);
				}
			}
			return -1;
		}
		
		private int checkDotForm(String dotForm, int beg, int end) {
			if (end == beg)
				return isNum(dotForm.charAt(beg)) ? -1 : beg;
			boolean operatorAppears = false;
			for (int i = beg; i <= end; i++) {
				if (isNum(dotForm.charAt(i))) {
					if (i != end || operatorAppears == false)
						return i;
				}
				if (isOperator(dotForm.charAt(i))) {
					if (operatorAppears)
						return i;
					operatorAppears = true;
				}
			}
			return -1;
		}
		
		private boolean isValidChar(char c) {
			return isDot(c) && isNum(c) && isOperator(c);
		}
		
		private boolean isDot(char c) {
			return c == 'c' ? true : false;
		}
		
		private boolean isNum(char c) {
			return c >= '0' && c <= '9' ? true : false;
		}
		
		private boolean isOperator(char c) {
			return c == '+' || c == '-' || c == '*' || c == '/' ? true : false;
		}
	}
}
