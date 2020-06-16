import javax.swing.*;

public class GuiForMovie {

    MovieRequest movieRequest = new MovieRequest();

    static JTextField textField;
    static JTextArea textArea;
    static JScrollPane scrollPane;
    static JFrame frame;
    static JButton button;
    static JLabel label;
    String textFromTextField;

    public static void main(String[] args) {

        // create a new frame to store text field and button
        frame = new JFrame("textfield");

        //create a label to display text
        label = new JLabel("nothing entered");

        // create a new button
        button = new JButton("submit");


    }
}
