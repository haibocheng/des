package org.dovigo;

import java.util.ArrayList;
import java.util.List;

public class DesRunner {
	public static void main(String[] args) {
		List<String> argList = new ArrayList<String>();
		argList.add("Des");
		for(String s : args) argList.add(s);
		Des.main(argList.toArray(new String[0]));
	}
}