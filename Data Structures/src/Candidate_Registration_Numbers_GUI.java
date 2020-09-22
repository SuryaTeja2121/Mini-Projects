import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

public class Candidate_Registration_Numbers_GUI extends JFrame
{
    // GUI Components:

    JTextArea  Candidate_Details  = new JTextArea ();

    ImageIcon image = new ImageIcon("image.jpg");
    JLabel icon = new JLabel(image);

    JLabel     NameLabel                 = new JLabel     (" Name : ");
    JTextField NameTextField             = new JTextField (10);
    JLabel     MobileNumberLabel         = new JLabel     (" Mobile Number : ");
    JTextField MobileNumberTextField     = new JTextField (10);

    JButton    addButton                 = new JButton (" Add ");
    JButton    deleteButton              = new JButton (" Delete ");
    JButton    editButton                = new JButton (" Edit ");
    JButton    editSaveButton            = new JButton (" Save ");
    JButton    displayAllButton          = new JButton (" Display All ");
    JButton    exitButton                = new JButton (" Exit ");

    // Class Instance Data:

    private LinkedList<Candidate_Registration_Number> CandidateLinkedList = new LinkedList<Candidate_Registration_Number> ();
    private int editIndex;

    public Candidate_Registration_Numbers_GUI ()
    {
        JOptionPane.showMessageDialog(Candidate_Registration_Numbers_GUI.this,
                " Welcome to Online Java Course Registration \uD83D\uDE03 \uD83D\uDE03 \uD83D\uDE03 \n\n"
                        + "Created by Ch. Surya Teja\n\n"
                        + "Instructions for the porcess :\n\n"
                        + " 1.) Enter the Candidate's name. \n"
                        + " 2.) Enter His/Her respective Mobile number. \n"
                        + " 3.) And then, they'll be getting their Registration Number. \n"
                        + " 4.) You can also delete any entry. \n"
                        + " 5.) You can edit the inputs. \n"
                        + " 6.) You can always display all the candidate's lists if you've lost track. \n"
                        + " 7.) After you've done, click exit to exit the app.\n\n"
                        +  " Hope you like it ! \n");

        JPanel flow1Panel = new JPanel (new FlowLayout (FlowLayout.CENTER));
        JPanel flow2Panel = new JPanel (new FlowLayout (FlowLayout.CENTER));
        JPanel gridPanel  = new JPanel (new GridLayout (2, 3));

        Candidate_Details.setEditable (false);

        NameLabel.setForeground(Color.blue);
        MobileNumberLabel.setForeground(Color.blue);

        Font f1 = new Font("Arial",Font.BOLD,20);
        NameLabel.setFont(f1);

        addButton.setBackground(Color.green);
        deleteButton.setBackground(Color.green);
        editButton.setBackground(Color.green);
        editSaveButton.setBackground(Color.green);
        displayAllButton.setBackground(Color.green);
        exitButton.setBackground(Color.green);

        Font f2= new Font("Arial",Font.BOLD, 17);
        MobileNumberLabel.setFont(f2);

        Font f3 = new Font("Arial", Font.BOLD, 13);
        MobileNumberTextField.setFont(f3);

        Font f4 = new Font("Arial", Font.BOLD, 14);
        NameTextField.setFont(f4);

        Candidate_Details.setBackground(Color.ORANGE);
        Candidate_Details.setForeground(Color.red);

        flow1Panel.add (NameLabel);
        flow1Panel.add (NameTextField);
        flow1Panel.add (MobileNumberLabel);
        flow1Panel.add (MobileNumberTextField);
        flow1Panel.setBackground(Color.PINK);

        flow2Panel.add (addButton);
        flow2Panel.add (editButton);
        flow2Panel.add (editSaveButton);
        flow2Panel.add (deleteButton);
        flow2Panel.add (displayAllButton);
        flow2Panel.add (exitButton);
        flow2Panel.setBackground(Color.PINK);

        gridPanel.add (flow1Panel);
        gridPanel.add (flow2Panel);
        gridPanel.add(icon);
        gridPanel.setBackground(Color.PINK);

        editSaveButton.setEnabled (false);

        add (Candidate_Details, BorderLayout.CENTER);
        add (gridPanel,         BorderLayout.SOUTH);

        addButton.addActionListener        (event -> Add_Candidate ());
        displayAllButton.addActionListener (event -> Display_All ());
        editButton.addActionListener       (event -> Edit_Candidate ());
        editSaveButton.addActionListener   (event -> Edit_Save_Candidate ());
        exitButton.addActionListener       (event -> Exit_Application ());
        deleteButton.addActionListener     (event -> Delete_Candidate ());

        setTitle (" Online Registration Portal for Java Course ! ");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        " Are you sure you want to exit the program? ", " Exit Program Message Box ",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
    }

    private boolean isCandidate_number_In_LinkedList (String NameStr)
    {
        boolean inList = false;

        for (Candidate_Registration_Number Cand : CandidateLinkedList)
        {
            if (Cand.getName ().compareToIgnoreCase (NameStr) == 0)
            {
                inList = true;
            }
        }

        return inList;
    }

    private void Add_Candidate ()
    {
        if (isCandidate_number_In_LinkedList ( MobileNumberTextField.getText()) == true)
        {
            JOptionPane.showMessageDialog (Candidate_Registration_Numbers_GUI.this,
                    "Error: This Mobile Number has already been take in the DataBase.");
        }
        else
        {

                Candidate_Registration_Number Cand = new Candidate_Registration_Number (NameTextField.getText(),
                                                                                        MobileNumberTextField.getText());

                CandidateLinkedList.add (Cand);

                Display_All ();

                MobileNumberTextField.setText("");
                NameTextField.setText("");
            }

        }


    private void Delete_Candidate ()
    {
        if (CandidateLinkedList.size() == 0)
        {
            JOptionPane.showMessageDialog (Candidate_Registration_Numbers_GUI.this,
                    "Error: Database is empty.");
        }
        else if (isCandidate_number_In_LinkedList (MobileNumberTextField.getText()) == false)
        {
            JOptionPane.showMessageDialog (Candidate_Registration_Numbers_GUI.this,
                    "Error: Candidate's Mobile number is not in the database.");
        }

        else
        {
            for (int s = 0; s < CandidateLinkedList.size(); s++)
            {
                String currMN = CandidateLinkedList.get (s).getName ();

                if (currMN.compareToIgnoreCase (MobileNumberTextField.getText()) == 0)
                {
                    CandidateLinkedList.remove(s);
                }
            }

            Display_All ();

            MobileNumberTextField.setText("");
            NameTextField.setText("");
        }
    }

    private void Edit_Candidate ()
    {
        if (CandidateLinkedList.size() == 0)
        {
            JOptionPane.showMessageDialog (Candidate_Registration_Numbers_GUI.this,
                    "Error: Database is empty.");
        }
        else if (isCandidate_number_In_LinkedList (MobileNumberTextField.getText()) == false)
        {
            JOptionPane.showMessageDialog (Candidate_Registration_Numbers_GUI.this,
                    "Error: Candidate's Mobile number is not in the database.");
        }
        else
        {
            editIndex = -1;

            for (int s = 0; s < CandidateLinkedList.size(); s++)
            {
                String currMN = CandidateLinkedList.get (s).getName ();

                if (currMN.compareToIgnoreCase (MobileNumberTextField.getText()) == 0)
                {
                    editIndex = s;
                    s = CandidateLinkedList.size(); // Exit Loop
                }
            }

            // index cannot be less than 0, because we checked if the Cand MN was in
            // the linked list before we looped above.
            if (editIndex >= 0)
            {
                editSaveButton.setEnabled   (true);

                editButton.setEnabled       (false);
                addButton.setEnabled        (false);
                deleteButton.setEnabled     (false);
                displayAllButton.setEnabled (false);
                exitButton.setEnabled       (false);

                MobileNumberTextField.setText (CandidateLinkedList.get (editIndex).getName () );
                NameTextField.setText         (CandidateLinkedList.get (editIndex).getMobile_number ());
            }

        }

    }

    private void Edit_Save_Candidate ()

    {
        // This code will save the changes the user made to the Candidate
        // they were editing - and save them back into the Linked List.

        CandidateLinkedList.get (editIndex).setName (MobileNumberTextField.getText() );
        CandidateLinkedList.get (editIndex).setMobile_number (NameTextField.getText() );

        Display_All ();

        MobileNumberTextField.setText ("");
        NameTextField.setText   ("");

        editSaveButton.setEnabled   (false);

        editButton.setEnabled       (true);
        addButton.setEnabled        (true);
        deleteButton.setEnabled     (true);
        displayAllButton.setEnabled (true);
        exitButton.setEnabled       (true);
    }

    private void Display_All ()

    {
        Font f = new Font("Arial",Font.BOLD,19);
        Candidate_Details.setText ("");
        Candidate_Details.setFont(f);

        for (Candidate_Registration_Number Cand : CandidateLinkedList)
        {
            Candidate_Details.append (Cand + "\n");
        }
    }

    private void Exit_Application ()
    {
        System.exit (0);
    }

    public static void main (String[] args)
    {
        Candidate_Registration_Numbers_GUI app = new Candidate_Registration_Numbers_GUI ();
        app.setVisible  (true);
        app.setSize     (1000, 500);
        app.setLocation (475, 280);
        app.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }
}