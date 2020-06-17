import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiForMovie implements ActionListener {

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

        // create a panel to add button and text field
        JPanel panel = new JPanel();

        // adding button and text field to panel
        panel.add(textField);
        panel.add(button);
        panel.add(label);
        panel.add(scrollPane);

        // add panel to frame
        frame.add(panel);

        // set size of frame
        frame.setSize(500,600);

        // set look and feel within try catch block
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.show();
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("submit")) {
            label.setText(textField.getText());
            textArea.setText(movieRequest.details);

            textFromTextField = textField.getText();
            try {
                movieRequest.executeAPI(textFromTextField);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            textField.setText(" ");
        }
    }
}
