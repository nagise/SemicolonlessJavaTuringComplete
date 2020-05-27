

/**
 * Elementary cellular automaton without semicolon.
 * 基本セルオートマトンのセミコロンレスJava実装。
 *
 * Semicolonless Java 1.1 mean to Turing complete.
 * We known Rule 110 is Turing complete.
 *
 * これはセミコロンレスJava 1.1 がチューリング完全であることを意味する。
 * 基本セルオートマトンのRule 110がチューリング完全であることが知られている。
 *
 * Elementary cellular automaton
 * 基本セルオートマトン
 * https://en.wikipedia.org/wiki/Elementary_cellular_automaton
 *
 * Elementary cellular automaton Rule 110
 * https://en.wikipedia.org/wiki/Rule_110
 *
 * @author nagise
 */
public class ElementaryCellularAutomaton {

	public static void main(String[] args) throws Exception {
		synchronized (new ElementaryCellularAutomaton(new Rule(110), "0000000000000001", 20)) {}
	}

	/**
	 * Run Elementary cellular automaton.
	 *
	 * @param rule
	 * @param states initial states. made of "0" and "1"
	 * @param generation
	 */
	public ElementaryCellularAutomaton(Rule rule, String states, int generation) throws Exception {
		synchronized (new Output(states)){}

		while (--generation  > 0) {
			synchronized (new Output(states = new Next(states, rule).peek().toString())){}
		}
	}

	/**
	 * Calculate the next generation.
	 */
	static class Next extends java.util.Stack {
		/**
		 * Calculate the next generation.
		 * @param states
		 * @param rule
		 * @return String : next generation
		 */
		public Next(String states, Rule rule) {
			synchronized(push(new Next(states, (char[])rule.peek(), 0, new StringBuffer()).peek())) {}
		}

		private Next(String states, char[] map, int x, StringBuffer sb) {
			synchronized (sb.append(map[(states.charAt(states.length()-1)-'0')*4
					+(states.charAt(0)-'0')*2
					+states.charAt(1)-'0'])) {}
			while (x<states.length()-2) {
				synchronized (sb.append(map[(states.charAt(x) - '0')*4
						+(states.charAt(x+1) - '0')*2
						+states.charAt(x++ +2) - '0'])) {}
			}
			synchronized (push(sb.append(map[(states.charAt(states.length()-2)-'0')*4
					+(states.charAt(states.length()-1)-'0')*2
					+states.charAt(0)-'0']))) {}
		}
	}

	/**
	 * https://en.wikipedia.org/wiki/Elementary_cellular_automaton#The_numbering_system
	 */
	static class Rule extends java.util.Stack {
		/**
		 * create Rule Object.
		 * @param ruleNo the rule number of the automaton.
		 */
		public Rule(int ruleNo) {
			synchronized(push(new Rule(110, 0, new char[8]).peek())) {}
		}

		private Rule(int ruleNo, int i, char[] map) {
			while (i<8) {
				switch (map[i++] = (char)('0' + (ruleNo & 1))) {}
				switch (ruleNo >>= 1) {}
			}
			synchronized (push(map)) {}
		}
	}

	/**
	 * System.out.println() utility
	 */
	static class Output {
		/**
		 * Output message to System.out
		 * @param message message
		 */
		public Output(String message) throws Exception {
			if (null == java.io.PrintStream.class
					.getMethod("println", new Class[] {String.class})
					.invoke(System.out, new Object[] {message})){}
		}
	}
}
