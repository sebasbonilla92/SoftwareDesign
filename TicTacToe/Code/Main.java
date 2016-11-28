
public class Main {

 	//-Controller: controller
		private Controller controller;

	  //-View: view
		private View view;

	  //-UnitTest: test
		private UnitTest test;
		
		public static void main(String[] args)
		{
			Main main=new Main();
			main.main();
		}
		public Main()
		{
			test=new UnitTest();
			controller=new Controller();
			view=new View();
		}
		public void main()
		{
			test.runAllTest();
			view.showWelcome();			
			controller.UCSetupGame();
			controller.UCStartGame();
		}
	}