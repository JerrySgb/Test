package test;

/**
 * 
 * @author Jerry Song
 * @since 2022-03
 * 
 * 字符串缩减
 */
public class TestReduceAdvanced {

	//整体循环结束标记
	private boolean finishedFlag = false;
	//替换字符
	private String replaceChar = "";
	//被替换的字符串
	private String reduceReplaceStr = "";
	
	public boolean getFinishedFlag() {
		return finishedFlag;
	}
	
	public String getReplaceChar() {
		return replaceChar;
	}
	
	public String getReduceReplaceStr() {
		return reduceReplaceStr;
	}
	
	/**
	 * 字符串缩减
	 * 
	 * @param targetString
	 * @return
	 */
	public String printReduceStr(String targetString) {
		StringBuilder reduceString = new StringBuilder();
		boolean breakFlag = false;
		for(int i = 0; i < targetString.length(); i++) {
			String str = String.valueOf(targetString.charAt(i));
			if(i == targetString.length() - 1) {
				reduceString.append(str);
				finishedFlag = true;
			} else {
				if(!String.valueOf(targetString.charAt(i + 1)).equals(str)) {
					reduceString.append(str);
				} else {
					boolean flag = false;
					int count = 2;
					if(i + 2 >= targetString.length()) {
						reduceString.append(str).append(str);
						finishedFlag = true;
						break;
					}
					for(int j = i + 2; j < targetString.length(); j++) {
						String repeatStr = String.valueOf(targetString.charAt(j));
						if(repeatStr.equals(str)) {
							count++;
							if(j == targetString.length() - 1) {
								for(int k = 0; k < count; k++) {
									reduceReplaceStr += str;
								}
								breakFlag = true;
								finishedFlag = true;
								break;
							}
							continue;
						} else {
							if(count < 3) {
								reduceString.append(str).append(str);
							} else {
								for(int k = 0; k < count; k++) {
									reduceReplaceStr += str;
								}
								flag = true;
							}
							i = j - 1;
							break;
						}
					}
					if(flag) {
						int ascii = (int) str.charAt(0) - 1;
						if(ascii < 97) {
							replaceChar = "";
						} else {
							replaceChar = String.valueOf((char)((int) str.charAt(0) - 1));
						}
						reduceString.append(replaceChar).append(targetString.substring(i + 1, targetString.length()));
						break;
					}
				}
				if(breakFlag) {
					break;
				}
			}
		}
		return reduceString.toString();
	}
	
	/**
	 * 循环调用字符串缩减
	 * 
	 * @param reduceString
	 */
	public void repeatReduce(String reduceString) {
		System.out.println("字符串缩减之前:" + reduceString);
		int count = 0;
		do {
			count++;
			reduceString = printReduceStr(reduceString);
			System.out.println("第" + count+ "次缩减后:" + reduceString + ", " + reduceReplaceStr + " is replaced by " + replaceChar);
			reduceReplaceStr = "";
			replaceChar = "";
		} while(!finishedFlag);
	}
	
	public static void main(String[] args) {
		TestReduceAdvanced testReduceAdvanced = new TestReduceAdvanced();
		String reduceString = "abcccbad";
		testReduceAdvanced.repeatReduce(reduceString);
	}
}