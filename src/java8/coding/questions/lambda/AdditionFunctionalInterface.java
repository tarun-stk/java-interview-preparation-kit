package java8.coding.questions.lambda;

@FunctionalInterface
public interface AdditionFunctionalInterface {
	
	int sum(int i, int j);
	
	static void staticFunctionDemo() {
		System.out.println("From static function");
	}

}
