package SRM154Div2;

public class SuperRot {

	public String decoder(String message) {
		if (message == null || message.length() == 0)
			return "";
		char[] c = new char[message.length()];
		for (int i = 0; i < c.length; i++)
			c[i] = decode(message.charAt(i));
		return String.valueOf(c);
	}
	
	private char decode(char c) {
		if ((c >= 'A' && c <= 'M') || (c >= 'a' && c <= 'm'))
			return (char) (c + 13);
		if ((c >= 'N' && c <= 'Z') || (c >= 'n' && c <= 'z'))
			return (char) (c - 13);
		if (c >= 0 && c <= 4)
			return (char) (c + 5);
		else
			return (char) (c - 5);
	}
}
