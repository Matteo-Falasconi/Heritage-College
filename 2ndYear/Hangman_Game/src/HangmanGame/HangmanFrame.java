package HangmanGame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import linked_data_structures.SinglyLinkedList;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;

public class HangmanFrame extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelAlphabet;
	private JTextField fldWord;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmNewGame;
	private JMenuItem mntmQuit;
	private JMenu mnGame;
	private JMenuItem mntmScoreBoard;
	private JMenuItem mntmRules;
	private JLabel lblTitle;
	private ImageIcon imageFrame;
	private JLabel lblImageFrame;
	private JLabel lblUsername;
	private JTextPane paneWordsGuessed;
	private JLabel lblWord;
	private JLabel lblGuessesLeft;
	private JButton btnHint;
	private JButton btnAlphabet[];
	private Game game;
	private int guess = 0;
	private ArrayList<String> wordStatus = new ArrayList<>();
	private ScoreBoard board;
	boolean returningPlayer;

	public HangmanFrame(Player p, ScoreBoard b, boolean returning) {
		returningPlayer = returning;
		board = b;
		try {
			if (returning) {
				game = p.getGame();
			} else {
				game = new Game(p);
			}
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(HangmanFrame.this,
					"No valid words were found. It seems like you need to gather some more valid words to cast spells.",
					"No Valid Words", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(HangmanFrame.this,
					"File Not Found. Please select the existing file you would like to select to begin your adventure.",
					"File Not Found", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(HangmanFrame.this,
					"Error with file. The file has some corruption, please select another file to begin your adventure.",
					"File Corruption", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 837, 596);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.addGamesPlayed();
				startNewGame();
				save();
			}
		});
		mnFile.add(mntmNewGame);

		mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(HangmanFrame.this, "Are you sure you want to quit?",
						"Quit Program", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					save();
					System.exit(1);
				}
			}
		});
		mnFile.add(mntmQuit);

		mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		mntmScoreBoard = new JMenuItem("Score Board");
		mntmScoreBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ScoreBoardFrame(board, HangmanFrame.this);
			}
		});
		mnGame.add(mntmScoreBoard);

		mntmRules = new JMenuItem("Rules");
		mntmRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new RulesFrame(HangmanFrame.this);
			}
		});
		mnGame.add(mntmRules);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTitle = new JLabel("Magic Battle!!");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 20, 234, 37);
		contentPane.add(lblTitle);

		imageFrame = new ImageIcon(HangmanFrame.class.getResource("/HangmanGame/frame_6.PNG"));
		lblImageFrame = new JLabel("");
		lblImageFrame.setIcon(imageFrame);
		lblImageFrame.setBounds(10, 95, 580, 274);
		contentPane.add(lblImageFrame);

		lblUsername = new JLabel(p.getUsername());
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsername.setBounds(20, 68, 224, 28);
		contentPane.add(lblUsername);

		paneWordsGuessed = new JTextPane();
		paneWordsGuessed.setEditable(false);
		paneWordsGuessed.setFont(new Font("Tahoma", Font.BOLD, 20));
		paneWordsGuessed.setText("Incorrect Guesses:\r\n");
		paneWordsGuessed.setBounds(600, 95, 208, 274);
		paneWordsGuessed.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		contentPane.add(paneWordsGuessed);

		lblWord = new JLabel("Complete the word to activate the spell:");
		lblWord.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWord.setBounds(10, 388, 798, 53);
		lblWord.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		contentPane.add(lblWord);

		lblGuessesLeft = new JLabel("You have 6 false guesses left");
		lblGuessesLeft.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGuessesLeft.setBounds(409, 29, 292, 26);
		contentPane.add(lblGuessesLeft);

		btnHint = new JButton("Hint");
		btnHint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hintBtn();
			}
		});
		btnHint.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnHint.setBounds(711, 28, 89, 28);
		contentPane.add(btnHint);

		fldWord = new JTextField();
		fldWord.setFont(new Font("Tahoma", Font.BOLD, 18));
		fldWord.setEditable(false);
		fldWord.setBounds(431, 400, 369, 28);
		contentPane.add(fldWord);
		fldWord.setColumns(10);

		panelAlphabet = new JPanel();
		panelAlphabet.setBounds(10, 452, 813, 72);

		contentPane.add(panelAlphabet);
		panelAlphabet.setLayout(null);
		guessButtons();
		setUpWord();
		if (returning) {
			setUpReturningWord();
		}
		addWindowListener(HangmanFrame.this);
	} // HangmanFrame()

	public void startNewGame() {
		try {
			game.newGame();
			wordStatus.clear();
			paneWordsGuessed.setText("Incorrect Guesses:\r\n");
			fldWord.setText("");
			updateGuessesLeft();
			btnHint.setEnabled(true);
			for (JButton btn : btnAlphabet) {
				btn.setEnabled(true);
			}
			setUpWord();
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(HangmanFrame.this,
					"It looks like you ran out of words in your textbook to cast a spell.\nYou will have to retreat for now and gather some more magic words to use!",
					"No Words Left!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	} // startNewGame()

	private void setUpWord() {
		for (int i = 0; i < game.wordLength(); i++) {
			char currentChar = game.getDic().getWord().charAt(i);
			if (Character.isLetter(currentChar)) {
				wordStatus.add("_ ");
			} else {
				wordStatus.add(currentChar + " ");
			}
			fldWord.setText(fldWord.getText() + wordStatus.get(i));
		}
	} // setUpWord()

	private boolean updateGuessWord(char guess) {
		boolean inWord = false;
		ArrayList<Integer> letterIndexes = game.letterInWord(guess);
		if (letterIndexes.size() > 0) {
			fldWord.setText("");
			for (int i = 0; i < game.wordLength(); i++) {
				for (int j = 0; j < letterIndexes.size(); j++) {
					if (i == letterIndexes.get(j)) {
						wordStatus.set(letterIndexes.get(j), Character.toUpperCase(guess) + " ");
						inWord = true;
						continue;
					}
				}
				fldWord.setText(fldWord.getText() + wordStatus.get(i));
			}
		}
		if (game.life() == 1 || game.countDistinctLetters(null) == 1) {
			btnHint.setEnabled(false);
		}
		return inWord;
	} // updateGuessWord(char)

	public void setUpReturningWord() {
		SinglyLinkedList<Character> guessed = game.getLettersGuessed();
		int guessedLength = guessed.getLength();
		if (guessedLength != 0) {
			for (int i = 0; i < guessedLength; i++) {
				char letter = guessed.getElementAt(i);
				Character.toUpperCase(letter);
				for (JButton btn : btnAlphabet) {
					if (btn.getText().charAt(0) == Character.toUpperCase(letter)) {
						btn.setEnabled(false);
					}
				}
				if (updateGuessWord(Character.toLowerCase(letter)) == false) {
					addIncorrectGuesses(letter);
				}
			}
			updateGuessesLeft();
		}
	} // setUpReturningWord()

	private void updateGuessesLeft() {
		int life = game.life();
		lblGuessesLeft.setText("You have " + life + " false guesses left");
		if (life != 0) {
			imageFrame = new ImageIcon(HangmanFrame.class.getResource("/HangmanGame/frame_" + life + ".PNG"));
			lblImageFrame.setIcon(imageFrame);
		}
	} // updateGuessesLeft()

	private void hintBtn() {
		char hint = game.hint();
		updateGuessWord(hint);
		updateGuessesLeft();
		for (JButton btn : btnAlphabet) {
			if (btn.getText().charAt(0) == Character.toUpperCase(hint)) {
				btn.setEnabled(false);
				break;
			}
		}
		gameComplete();
	} // hintBtn()

	public boolean gameComplete() {
		boolean complete = false;
		if (game.life() == 0) {
			game.player().addGamesPlayed();
			int choice = JOptionPane.showConfirmDialog(HangmanFrame.this,
					"OH NO!\nYour spell was unsuccessful and Goblin Guy Grug has slain you.\nBut your journey doesn't have to end here, you can still revive.\nWill you continue or become worm food...",
					"Game Over!", JOptionPane.YES_NO_OPTION);
			startNewGame();
			save();
			if (choice == JOptionPane.NO_OPTION) {
				System.exit(1);
			}
			complete = true;
		} else if (game.getRemainingLength() == 0) {
			game.player().addGamesPlayed();
			game.player().addGamesWon();
			int choice = JOptionPane.showConfirmDialog(HangmanFrame.this,
					"YES!\nYou have slain Goblin Guy Grug and it seems he isn't getting back up!\nBut, you know what? You could revive him to gain more XP!\nYour choice...",
					"You Won!", JOptionPane.YES_NO_OPTION);
			startNewGame();
			save();
			if (choice == JOptionPane.NO_OPTION) {
				System.exit(1);
			}
			complete = true;
		}
		return complete;
	} // gameComplete()

	private void addIncorrectGuesses(char selected) {
		StyledDocument style = paneWordsGuessed.getStyledDocument();
		try {
			if (guess > 0 && guess % 9 == 0) {
				style.insertString(style.getLength(), "\n " + Character.toUpperCase(selected), null);
			} else {
				style.insertString(style.getLength(), " " + Character.toUpperCase(selected), null);
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		guess++;
	} // addIncorrectGuesses(char)

	public void guessButtons() {
		String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int x = 0;
		int y = 0;
		int width = 57;
		int height = 30;
		Font font = new Font("Tahoma", Font.PLAIN, 22);
		btnAlphabet = new JButton[alph.length()];
		for (char letter : alph.toCharArray()) {
			JButton btn = new JButton(String.valueOf(letter));
			btn.setFont(font);
			btn.setBounds(x, y, width, height);
			panelAlphabet.add(btn);
			btn.addActionListener(e -> {
				JButton source = (JButton) e.getSource();
				char selected = source.getText().charAt(0);
				if (!updateGuessWord(selected)) {
					addIncorrectGuesses(selected);
					updateGuessesLeft();
				}
				source.setEnabled(false);
				gameComplete();
			});
			x += width + 5;
			if (x + width > panelAlphabet.getWidth()) {
				x = 0;
				y += height + 5;
			}
			btnAlphabet[letter - 'A'] = btn;
		}
	} // guessButtons()

	public void save() {
		try {
			board.savePlayerListData();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(HangmanFrame.this,
					"Error with saving to file.\nThe file has some corruption, please select another file to begin your adventure.",
					"Invalid File", JOptionPane.ERROR_MESSAGE);
		}
	} // save()

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		save();
	}

	@Override
	public void windowClosed(WindowEvent e) {
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
} // HangmanFrame
