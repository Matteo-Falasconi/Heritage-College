package HangmanGame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class RulesFrame extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private HangmanFrame hangman;

	public RulesFrame(HangmanFrame hf) {
		hangman = hf;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Magic Limitations");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 576, 37);
		contentPane.add(lblNewLabel);
		
		JTextPane txtpnThoughYouAre = new JTextPane();
		txtpnThoughYouAre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnThoughYouAre.setText("Though you are chosen to complete this task it would also be important to tell you about your limits as a magic user.\r\n\r\nFirst of all, you have only six hits of health given in order to take out your target. However, any complete word you culminate can take anything out in one shot. Though, it would be simple to name any word you have in mind it is completely random. So be prepared for the chances of you guessing the correct letters of the word are very low but never 0. For that reason things may get a little too tough for you. So, I have provided you with the ability to peak into the letters of the word. But, for everytime you use it your health is depleted. I am sure you can do this. That is why I chose you of course.\r\n\r\n- The One Above All");
		txtpnThoughYouAre.setBounds(10, 47, 576, 323);
		contentPane.add(txtpnThoughYouAre);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				hangman.setVisible(true);
			}
		});
		btnClose.setBounds(10, 381, 576, 32);
		contentPane.add(btnClose);
		addWindowListener(RulesFrame.this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		hangman.setVisible(true);
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
