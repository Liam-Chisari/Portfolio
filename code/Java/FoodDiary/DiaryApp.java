public class DiaryApp {

    public static void main(String[] args) {

        //Instantiate model, view, controller here.

        DiaryView view = new DiaryView();
        DiaryModel model = new DiaryModel(view);
        DiaryController controller = new DiaryController(view, model);

        controller.run();


    }


}


