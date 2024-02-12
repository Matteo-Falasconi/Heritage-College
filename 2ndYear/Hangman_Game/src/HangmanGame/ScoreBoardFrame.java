package HangmanGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;

public class ScoreBoardFrame extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ScoreBoard board;
	private HangmanFrame hangman;
	private JScrollPane scrollPane;
	private JLabel lblTitle;
	private JButton btnClose;

	public ScoreBoardFrame(ScoreBoard sb, HangmanFrame hf) {
		board = sb;
		hangman = hf;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 585, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 549, 303);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);

		listOfPlayers();

		btnClose = new JButton("Close");
		btnClose.setBounds(10, 347, 549, 32);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				hangman.setVisible(true);
			}
		});
		contentPane.add(btnClose);

		lblTitle = new JLabel("Goblin Guy Grug Ongoing Attempts");
		lblTitle.setBounds(10, 1, 530, 31);
		contentPane.add(lblTitle);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		addWindowListener(ScoreBoardFrame.this);
	} // ScoreBoardFrame()

	private void listOfPlayers() {
		int y = 0;
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(0, 1));
		for (int i = board.getPlayers().getLength() - 1; i >= 0; i--) {
			Player playerInfo = board.getPlayer(i);
			JButton playerButton = new JButton(
					"Adventurer: " + playerInfo.getUsername() + " | Wins: " + playerInfo.getGamesWon() + " | Losses: "
							+ (playerInfo.getGamesPlayed() - playerInfo.getGamesWon()) + " | Games Played: "
							+ playerInfo.getGamesPlayed());
			playerButton.setBounds(0, y, 530, 45);
			playerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hangman.save();
					hangman = new HangmanFrame(playerInfo, board, true);
					setVisible(false);
					hangman.setVisible(true);
				}
			});
			playerPanel.add(playerButton);
		}
		scrollPane.setViewportView(playerPanel);
	} // listOfPlayers()

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		hangman.setVisible(true);

	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
} // ScoreBoardFrame
