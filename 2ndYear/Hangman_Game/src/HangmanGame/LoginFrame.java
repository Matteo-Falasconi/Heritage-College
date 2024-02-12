package HangmanGame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextPane txtpnDescription;
	private JLabel lblQuest;
	private JButton btnStart;
	private JLabel lblLogin;
	private JLabel lblContinue;
	private JComboBox cmbxContinue;
	private JLabel lblStartNew;
	private JLabel lblName;
	private JLabel lblWitch;
	private ScoreBoard board = new ScoreBoard();
	private ArrayList<String> playerList = new ArrayList<>();
	private boolean continued;
	private Player continuedPlayer;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} // main(String[])

	public LoginFrame() {
		continued = false;
		try {
			board.loadPlayerListData();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(LoginFrame.this,
					"Error with class.\nThe class does not seem to be accessible.", "Class Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(LoginFrame.this,
					"Error with File.\nThe file seems to have a some corruption.", "File Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 666, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtpnDescription = new JTextPane();
		txtpnDescription.setFont(new Font("Monotype Corsiva", Font.PLAIN, 22));
		txtpnDescription.setText(
				"A goblin titled: \"Goblin Guy Grug\" has acquired the powerful sword to punish inexperienced magic users. You've been assigned to take him! Use your words to cast out the magic within your trusty Flame Staff to eliminate Grug!\r\n\r\nYou're our last hope, Goodluck!\r\n\r\n-The One Above All");
		txtpnDescription.setEditable(false);
		txtpnDescription.setBounds(315, 110, 322, 300);
		contentPane.add(txtpnDescription);

		lblQuest = new JLabel("Quest");
		lblQuest.setFont(new Font("Monotype Corsiva", Font.BOLD, 26));
		lblQuest.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuest.setBounds(315, 70, 322, 29);
		contentPane.add(lblQuest);

		btnStart = new JButton("Start");
		btnStart.setEnabled(false);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnStart.setBounds(315, 421, 322, 39);
		contentPane.add(btnStart);

		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(170, 11, 283, 48);
		contentPane.add(lblLogin);

		lblContinue = new JLabel("Continue Adventure");
		lblContinue.setHorizontalAlignment(SwingConstants.CENTER);
		lblContinue.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblContinue.setBounds(10, 77, 283, 22);
		contentPane.add(lblContinue);

		playerList.add(" - Select - ");
		for (int i = board.getPlayers().getLength() - 1; i >= 0; i--) {
			Player p = board.getPlayer(i);
			playerList.add("Adventurer:  " + p.getUsername() + "    Wins: " + p.getGamesWon() + "  |  " + " Loses: "
					+ (p.getGamesPlayed() - p.getGamesWon()));
		}
		String[] usernames = new String[playerList.size()];
		for (int i = 0; i < usernames.length; i++) {
			usernames[i] = playerList.get(i);
		}
		cmbxContinue = new JComboBox(new DefaultComboBoxModel<>(usernames));
		cmbxContinue.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = ((JComboBox) e.getSource()).getSelectedIndex();
				if (i != 0 && e.getStateChange() == ItemEvent.SELECTED) {
					continuedPlayer = board.getPlayer(i - 1);
					continued = true;
					startGame();
				}
			}
		});
		cmbxContinue.setBounds(10, 111, 283, 22);
		contentPane.add(cmbxContinue);

		lblStartNew = new JLabel("Start New Adventure");
		lblStartNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartNew.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStartNew.setBounds(10, 177, 283, 22);
		contentPane.add(lblStartNew);

		lblName = new JLabel("Your Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(10, 210, 90, 22);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!txtName.getText().isEmpty() && txtName.getText().length() <= 10) {
					btnStart.setEnabled(true);
				} else {
					btnStart.setEnabled(false);
				}
			}
		});
		txtName.setBounds(98, 213, 195, 20);
		txtName.setColumns(10);
		contentPane.add(txtName);

		lblWitch = new JLabel("");
		lblWitch.setIcon(new ImageIcon(LoginFrame.class.getResource("/HangmanGame/witch.PNG")));
		lblWitch.setBounds(48, 224, 283, 300);
		contentPane.add(lblWitch);
	} // LoginFrame()

	public void startGame() {
		Player p;
		HangmanFrame hangman;
		if (!continued) {
			int index = board.addPlayer(txtName.getText());
			hangman = new HangmanFrame(board.getPlayer(index), board, false);
		} else {
			hangman = new HangmanFrame(continuedPlayer, board, true);
		}
		setVisible(false);
		hangman.setVisible(true);
	} // startGame()
} // LoginFrame
