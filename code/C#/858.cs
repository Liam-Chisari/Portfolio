using System;
using System.Collections;
using static System.IO.TextWriter;

public class ButtonClass {

    private void MyButton_Click(object sender, EventArgs e) {
      MyButton_Click_Aux();
    }

    private static volatile int reportCount;

    private static void MyButton_Click_Aux() {
      try { /*remove because stack overflows without*/ }
      finally {
        var myLogData = new ArrayList();
        myLogData.Add(reportCount);
        myLogData.Add("method MyButtonClickAux");
        Log(myLogData);
      }
    }

    private static void Log(object logData) {
      // my log code is not matter
    }

    public static void Main(string[] args) {

        MyButton_Click_Aux();
    }
}
