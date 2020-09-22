import javax.swing.*;

public class Candidate_Registration_Number
{
    public static final String Reg_No = "BL.EN.U4AIE_JAVA_";

    private String name;
    private String mobile_number;

    public Candidate_Registration_Number ()
    {
        name = "";
        mobile_number   = "";
    }

    public Candidate_Registration_Number (String mobile_number, String name)
    {
        // Remove spaces and tabs.
        name = name.trim ();
        mobile_number   = mobile_number.trim ();

        if (name.length () == 0)
        {
            JOptionPane.showMessageDialog (null, "Error: name cannot be blank.");
            //throw new Candidate_Registration_Number_Exception  ("Error: Mobile number cannot be blank.");
        }

        else if (mobile_number.length () == 0)
        {
            JOptionPane.showMessageDialog (null, "Error: name cannot be blank.");
            //throw new Candidate_Registration_Number_Exception  ("Error: Name cannot be blank.");
        }

        else
        {
            // We set class data to the values passed in.
            this.name = name;
            this.mobile_number   = mobile_number;
        }
    }

    public String getName ()
    {
        return name;
    }

    public String getMobile_number ()
    {
        return mobile_number;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public void setMobile_number (String mobile_number)
    {
        this.mobile_number = mobile_number;
    }

    @Override
    public String toString ()
    {
        return mobile_number + "\t" + name + "\t"  + Reg_No + name;
    }

}