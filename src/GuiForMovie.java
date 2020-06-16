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

        // create new text field of 16 columns
        textField = new JTextField(16);

        // create new text area with 10 rows and 35 columns
        textArea = new JTextArea(10, 35);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);

        scrollPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
}
