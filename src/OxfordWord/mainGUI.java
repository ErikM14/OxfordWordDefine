package OxfordWord;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author erikm
 */
public class mainGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textf;
    private JButton def;
    private JTextArea textarea;
    
    public mainGUI(){
    //frame
    frame = new JFrame("Dictionary Definitions");
    panel = new JPanel();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600,450);
    frame.add(panel);
    
    //make panel layout null, personally set location of fields
    panel.setLayout(null);
    
    label = new JLabel("Oxford Dictionary Definitions");
    label.setBounds(160, 20, 400, 100);
    label.setFont(label.getFont().deriveFont(20f));
    
    textf = new JTextField(20);
    textf.setBounds(200,360,180,25);
     
    
    //Button setup and action
    def = new JButton("Define");
    def.setBounds(250,390,75,25);
    def.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            OxfordDictionaryAPI a = new OxfordDictionaryAPI();
            String word = textf.getText();
            String w1 = a.searchedWord(word);
            String w2 = a.retrieveInfo(w1);
            textarea.setText(w2);
            
        }
    });
    
    //JTextArea setup
    textarea = new JTextArea();
    textarea.setBounds(30,100,530,250);
    textarea.setEditable(false);
    textarea.setLineWrap(true);
    textarea.setWrapStyleWord(true);
    textarea.setFont(textarea.getFont().deriveFont(18f));
    
    //adding fields to jtextarea
    panel.add(label);
    panel.add(textf);
    panel.add(def);
    panel.add(textarea);
        
    
    //show frame
    frame.setVisible(true);
    }
 
    public static void main(String []args){
        new mainGUI();
    }
}



