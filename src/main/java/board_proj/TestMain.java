package board_proj;

public class TestMain {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String className = "board_proj.Sum";
		
		// ?는 뭐가 올 지 모르니까 Object로 받겠다는 말
		Class<?> cls = Class.forName(className);
		
		// new 대신에 생성을 해주는 방법
		Sum s1 = (Sum) cls.newInstance();
		s1.add(5, 3);
		
		// 아래와 똑같음
		Sum s2 = new Sum();
		s2.add(5, 3);
		
	}
}
