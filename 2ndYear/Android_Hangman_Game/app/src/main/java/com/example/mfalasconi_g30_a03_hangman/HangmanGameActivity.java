package com.example.mfalasconi_g30_a03_hangman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import hangmanGame.*;
import linked_data_structures.SinglyLinkedList;


public class HangmanGameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnHint;
    private TextView fldWord;
    private TextView incorrectGuesses;
    private TextView lblGuessesLeft;
    private TextView lblUsername;
    private ImageView framesImageView;
    private Button btnAlphabet[];
    private int playerIndex;
    private Game game;
    private Player p;
    private int guess = 0;
    private boolean returning;
    private ArrayList<String> wordStatus = new ArrayList<>();
    private ScoreBoard board;

    public void hangmanExtras() {
        Intent i = getIntent();
            board = i.getSerializableExtra("b", ScoreBoard.class);
            returning = i.getBooleanExtra("returning", false);
            playerIndex = i.getIntExtra("player", 0);
            p = board.getPlayer(playerIndex);
            try {
                if (returning) {
                    game = board.getPlayer(playerIndex).getGame();
                } else {
                    game = new Game(board.getPlayer(playerIndex), this);
                }
            } catch (IllegalArgumentException e) {
                AlertDialog.Builder alertWords = new AlertDialog.Builder(this);
                alertWords.setTitle("No More Words")
                        .setMessage("No valid words were found.\nit seems like you need to gather some more valid words to cast spells.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                saveAndQuit(true);
                            }
                        })
                        .show();
            } catch (FileNotFoundException e1) {
                AlertDialog.Builder alertFile = new AlertDialog.Builder(this);
                alertFile.setTitle("File Not Found")
                        .setMessage("Please select the existing file you would like to select to begin your adventure")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                                System.exit(0);
                            }
                        })
                        .show();
            } catch (IOException e2) {
                AlertDialog.Builder alertError = new AlertDialog.Builder(this);
                alertError.setTitle("Error with File")
                        .setMessage("The file has some corruption, please select another file to begin your adventure!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                                System.exit(0);
                            }
                        })
                        .show();
            }
        }

    private void setUpReturningWord() {
        SinglyLinkedList<Character> guessed = game.getLettersGuessed();
        int guessedLength = guessed.getLength();
        if(guessedLength != 0) {
            for(int i = 0; i < guessedLength; i++) {
                char letter = guessed.getElementAt(i);
                Character.toUpperCase(letter);
                for(Button btn : btnAlphabet) {
                    if(btn.getText().charAt(0) == Character.toUpperCase(letter)) {
                        btn.setEnabled(false);
                    }
                }
                if(updateGuessWord(Character.toLowerCase(letter)) == false) {
                    addIncorrectGuesses(letter);
                }
            }
            updateGuesesLeft();
        }
    } // setUpReturningWord()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_screen);
        btnHint = findViewById(R.id.btnHint);
        btnHint.setOnClickListener(this);
        lblUsername = findViewById(R.id.lblUsername);
        framesImageView = findViewById(R.id.frames);
        fldWord = findViewById(R.id.txtWord);
        incorrectGuesses = findViewById(R.id.txtIncorrectGuesses);
        lblGuessesLeft = findViewById(R.id.lblGuessesLeft);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Word Magic");
        LinearLayout alph = findViewById(R.id.alphabet);
        hangmanExtras();
        lblUsername.setText(board.getPlayer(playerIndex).getUsername());
        btnAlphabet = new Button['Z' - 'A' + 1];
        for(char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            Button alphabetButton = new Button(this);
            alphabetButton.setText(String.valueOf(alphabet));
            alphabetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    char selected = ((Button) v).getText().toString().charAt(0);
                    if(!updateGuessWord(selected)) {
                        addIncorrectGuesses(selected);
                        updateGuesesLeft();
                    }
                    alphabetButton.setEnabled(false);
                    gameComplete();
                    saveAndQuit(false);
                }
            });
            alph.addView(alphabetButton);
            btnAlphabet[alphabet - 'A'] = alphabetButton;
        }
        if(game.getDic().getWordList().getLength() != 0) {
            setUpWord();
            if (returning) {
                setUpReturningWord();
            }
            updateGuesesLeft();
        } else {
            AlertDialog.Builder alertWords = new AlertDialog.Builder(this);
            alertWords.setTitle("No More Words")
                    .setMessage("No valid words were found.\nit seems like you need to gather some more valid words to cast spells.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            saveAndQuit(true);
                        }
                    })
                    .show();
        }
    } // OnCreate(Bundle)

    public void addIncorrectGuesses(char selected) {
        if(guess > 0 && guess % 10 == 0) {
            incorrectGuesses.append("\n" + Character.toUpperCase(selected));
        } else {
            incorrectGuesses.append(" " + Character.toUpperCase(selected));
        }
        guess++;
    } // addIncorrectGuesses(char)

    private boolean updateGuessWord(char guess) {
        boolean inWord = false;
        ArrayList<Integer> letterIndexes = game.letterInWord(guess);
        if(letterIndexes.size() > 0) {
            fldWord.setText("");
            for(int i = 0; i < game.wordLength(); i++) {
                for(int j = 0; j < letterIndexes.size(); j++) {
                    if (i == letterIndexes.get(j)) {
                        wordStatus.set(letterIndexes.get(j), game.word().charAt(i) + " ");
                        inWord = true;
                    }
                }
                fldWord.append(wordStatus.get(i));
            }
        }
        if(game.life() == 1 || game.countDistinctLetters(null) == 1) {
            btnHint.setEnabled(false);
        }
        return inWord;
    } // updateGuessWord(char)

    public void updateGuesesLeft() {
        int life = game.life();
        lblGuessesLeft.setText("You have " + life + " false guesses left");
        if(life != 0) {
            try {
                String imageName = "frame_" + life + ".png";
                InputStream stream = getAssets().open(imageName);
                Drawable drawable = Drawable.createFromStream(stream, null);
                framesImageView.setImageDrawable(drawable);
                stream.close();
            } catch (IOException e) {}
        }
    } // updateGuessesLeft()

    public void gameComplete() {
        String word = game.getDic().getWord();
        if(game.life() == 0) {
            board.getPlayer(playerIndex).addGamesPlayed();
            startNewGame();
            saveAndQuit(false);
            AlertDialog.Builder alertLose = new AlertDialog.Builder(this);
            alertLose.setTitle("Game Over!\nThe Word Was: " + word)
                    .setMessage("OH NO!\nYour spell was unsuccessful and Goblin Guy Grug has slain you.\nBut your journey doesn't have to end here, you can still revive.\nWill you continue or become worm food...")
                    .setPositiveButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                            System.exit(0);
                        }
                    })
                    .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        } else if (game.getRemainingLength() == 0) {
            board.getPlayer(playerIndex).addGamesPlayed();
            board.getPlayer(playerIndex).addGamesWon();
            startNewGame();
            saveAndQuit(false);
            AlertDialog.Builder alertWin = new AlertDialog.Builder(this);
            alertWin.setTitle("You Won!\nThe Word Was: " + word)
                    .setMessage("YES!\nYou have slain Goblin Guy Grug and it seems he isn't getting back up!\nBut, you know what? You could revive him to gain more XP!\nYour choice...")
                    .setPositiveButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                            System.exit(0);
                        }
                    })
                    .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }
    }

    private void setUpWord() {
        for(int i = 0; i < game.wordLength(); i++) {
            char currentChar = game.getDic().getWord().charAt(i);
            if(String.valueOf(currentChar).matches("[A-Za-z]")) {
                wordStatus.add("_ ");
            } else {
                wordStatus.add(currentChar + " ");
            }
            Log.i("Word", "" + wordStatus.get(i));
            fldWord.append(wordStatus.get(i));
        }
    } // setUpWord()

    public void startNewGame() {
        try {
            game.newGame();
            wordStatus.clear();
            incorrectGuesses.setText("Incorrect Guesses:\r\n");
            fldWord.setText("");
            updateGuesesLeft();
            btnHint.setEnabled(true);
            for(Button btn : btnAlphabet) {
                btn.setEnabled(true);
            }
            setUpWord();
        } catch (IllegalArgumentException e) {
            AlertDialog.Builder alertWin = new AlertDialog.Builder(this);
            alertWin.setTitle("No Words Left")
                    .setMessage("It looks like you ran out of words in your textbook to cast a spell.\nYou will have to retreat for now and gather some more magic words to use!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            saveAndQuit(true);
                        }
                    })
                    .show();
        }
    } // startNewGame()

    private void hintBtn() {
        char hint = game.hint();
        updateGuessWord(hint);
        updateGuesesLeft();
        for(Button btn : btnAlphabet) {
            if(btn.getText().charAt(0) == Character.toUpperCase(hint)) {
                btn.setEnabled(false);
                break;
            }
        }
        gameComplete();
        saveAndQuit(false);
    } // hintBtn()

    public void saveAndQuit(boolean goBack) {
        try {
        File file = new File(getFilesDir(), "playersData.ser");
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(board.getPlayers());
        objOut.close();
        fileOut.close();
        if(goBack) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    } catch (IOException e) {
        AlertDialog.Builder alertSave = new AlertDialog.Builder(this);
        alertSave.setTitle("Error in File")
                .setMessage("Error with saving to file.\nThe file has some corruption, please select another file to begin your adventure")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        System.exit(0);
                    }
                })
                .show();
        }
    }

    @Override
    public void onClick(View view) {
        int clicked = view.getId();
        if(clicked == R.id.btnHint) {
            hintBtn();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.itemNewGame) {
                Toast.makeText(this, "New Game Started", Toast.LENGTH_SHORT).show();
                p.addGamesPlayed();
                startNewGame();
                saveAndQuit(false);
                return true;
            } else if (item.getItemId() == R.id.itemScoreBoard) {
                Intent i = new Intent(HangmanGameActivity.this, ScoreBoardActivity.class);
                i.putExtra("b", board);
                startActivity(i);
                return true;
            } else if (item.getItemId() == R.id.itemRules) {
                Intent i = new Intent(HangmanGameActivity.this, RulesActivity.class);
                startActivity(i);
                return true;
            } else if (item.getItemId() == R.id.itemBack) {
                saveAndQuit(true);
                return true;
            } else if (item.getItemId() == R.id.itemExit) {
                AlertDialog.Builder alertExit = new AlertDialog.Builder(this);
                alertExit.setTitle("Exit Program")
                        .setMessage("Are you sure you want to quit")
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                                System.exit(0);
                            }
                        })
                        .show();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }