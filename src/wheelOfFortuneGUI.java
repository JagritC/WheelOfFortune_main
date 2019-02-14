import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.imageio.*;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.SwingConstants;

/*
 * author: Jagrit Chaitanya
 * date: January 2018
 * 
 * MethodList: setPlayer1(PlayerRecord) : method used by playerinfoGUI to transfer player data between classes
 * 				setPlayer2(PlayerRecord)
 * 				display(): converts the letter that haven't been guessed into blank spaces
 * 				actionPerformed (actionEvent): method that runs when user interacts with a component that has actionListener attached to it
 * 				itemStateChanged(ItemEvent): method that runs when a state of a component which has itemListener changes (eg. radiobutton)
 */
public class wheelOfFortuneGUI extends JFrame implements ItemListener, ActionListener {

	//declare all variables used through this class
	private JPanel contentPane;
	private int order, player1RndBal, player2RndBal, round;
	private PlayerList playersPlaying;
	private WordsCategory [] questions;
	private PhrasesCategory [] phraseQuestions;
	private ImagePicture player1Turn, player2Turn, txtAreaBackgnd, backgndLbl;
	private JPanel titlePanel, questionPanel, spinnerPanel, radioButtonPanel, cardPanel, vowelPanel, consonantPanel;
	private JFrame spinnerFrame;
	private JRadioButton consonantRdBtn, vowelRdBtn;
	private JLabel questionLbl, background2, lblRoundBalPlayer1, lblPlayer1TBal, lblRoundBalPlayer2, lblPlayer2TBal ;
	private JLabel lblTBalTitle, lblRndBalTitle, titlePic, lblHint;
	private JTextArea questionTxtArea, guessesTxtArea;
	private Border thickBorder, thinBorder,customC; //declares and initialized the border
	private String questionDisplay = " ", guesses = " ,.?;'-!", holder, guessesDisplay = "", answer, preHint;
	private Die d;
	private Color c, purple, darkPurple;
	private JButton btnGuessAConsonant, btnBuyAVowel, btnSpin;
	private JComboBox guessConsonants, guessVowel;
	private PlayerRecord player1, player2;
	private Spinner spinner;
	private int turn;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnGuessAnswer;
	private JLabel lblTurnTitle;
	private JLabel lblTurnTitle2;

	//methods that playerinfo gui calls to set the player data from that class
	public void setPlayer1(PlayerRecord player1) {
		this.player1 = player1;
	}

	public void setPlayer2(PlayerRecord player2) {
		this.player2 = player2;
	}
	// main method for self testing
	public static void main(String[] args){

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					wheelOfFortuneGUI frame = new wheelOfFortuneGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	//constructor class
	public wheelOfFortuneGUI() {

		//content info
		setTitle("Wheel Of Fortune");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 0, 995, 700);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		//colors and borders used in this class
		c = new Color (123, 104, 238);
		purple = new Color(204, 153, 255);
		darkPurple = new Color(153, 51, 255);
		thickBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
		thinBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		customC = BorderFactory.createLineBorder(darkPurple, 2);

		//panel for the title
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(10, 24, 959, 93);
		titlePanel.setLayout(null);
		titlePanel.setOpaque(false);
		contentPane.setLayout(null);

		//panel for guessed words
		JPanel gwPanel = new JPanel();
		gwPanel.setBackground(purple);
		contentPane.add(gwPanel);
		gwPanel.setBounds(466, 466, 211, 52);

		//txtArea to display the guessed letters
		guessesTxtArea = new JTextArea(guessesDisplay);
		guessesTxtArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		guessesTxtArea.setOpaque(false);
		guessesTxtArea.setEditable(false);
		guessesTxtArea.setLineWrap(true);
		gwPanel.add(guessesTxtArea);
		gwPanel.setBorder(thickBorder);

		//txt area to display the question
		questionTxtArea = new JTextArea();
		questionTxtArea.setLineWrap(true);
		questionTxtArea.setWrapStyleWord(true);
		Font font = new Font("Tahoma", Font.PLAIN, 70);
		questionTxtArea.setFont(new Font("Tahoma", Font.PLAIN, 50));
		questionTxtArea.setEditable(false);
		questionTxtArea.setOpaque(false);
		questionTxtArea.setBounds(102, 159, 520, 249);
		contentPane.add(questionTxtArea);
		questionTxtArea.setLayout(null);

		//titleImage
		try {
			titlePic = new JLabel (new ImageIcon(ImageIO.read(new FileInputStream("res/gameTitle.png"))));
		}
		catch (IOException e4)
		{
			System.out.println("titlePic ImageIcon error");
		}

		//background image for the question
		try {
			txtAreaBackgnd = new ImagePicture (new ImageIcon(ImageIO.read(new FileInputStream("res/rsz_border.png"))));
		} catch (IOException e1) {
			System.out.println("txtAreaBackgnd ImageIcon error");
		}	
		txtAreaBackgnd.setBounds(33,114,680,330);
		contentPane.add(txtAreaBackgnd);

		//hint label to display depended on the question
		preHint = "  Hint: ";
		lblHint = new JLabel(preHint);
		lblHint.setBackground(purple);
		lblHint.setOpaque(true);
		lblHint.setBorder(thinBorder);
		lblHint.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblHint.setBounds(33, 466, 391, 50);
		contentPane.add(lblHint);

		//die to decide if the first question 
		d = new Die (2);
		d.rollDie();
		//**************************************************************************************
		questions = new WordsCategory [2];

		// gets 2 random questions from the word category and puts it into the wordsCategory array
		for (int i = 0; i<questions.length; i++) 
		{
			try {
				questions[i] = new WordsCategory ("WordsFile.txt");
				questions[i].loadFile();
				questions[i].randomElement();
				questions[i].setElement();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		phraseQuestions = new PhrasesCategory [2];
		// gets 2 random questions from the phrase category and puts it into the phraseCategory array
		for (int i = 0; i<phraseQuestions.length; i++) 
		{
			try {
				phraseQuestions[i] = new PhrasesCategory ("phrasesForWOF_1.txt");
				phraseQuestions[i].loadFile();
				phraseQuestions[i].randomElement();
				phraseQuestions[i].setElement();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//decides if the first question will be a phrase or a word. The questions for the next 2 rounds are dependent on the die roll as 
		//they will be alternating categories

		if (d.getValue() == 1) 
		{
			holder = questions[0].getSentence();
			lblHint.setText(preHint + questions[0].getHint());
		}
		else
		{
			holder = phraseQuestions[0].getSentence();
			lblHint.setText(preHint + "Phrase");
		}

		holder = holder.toUpperCase();
		//TODO remove System out here
		System.out.println(holder);
		displayQuestion();
		questionTxtArea.setText(questionDisplay);
		//******************************************************************************************************************************//


		//panel which holds the radio buttons, vowel or consonants
		radioButtonPanel = new JPanel ();
		radioButtonPanel.setBounds(33, 545, 391, 32);
		radioButtonPanel.setBackground(purple);
		radioButtonPanel.setBorder(thinBorder);
		radioButtonPanel.setEnabled(false);
		contentPane.add(radioButtonPanel);
		radioButtonPanel.setLayout(null);

		//vowel radio button which allows the user to buy a vowel when this is on
		vowelRdBtn = new JRadioButton("Vowel");
		vowelRdBtn.setOpaque(false);
		vowelRdBtn.addItemListener(this);
		buttonGroup.add(vowelRdBtn);
		vowelRdBtn.setBounds(228, 0, 109, 30);
		radioButtonPanel.add(vowelRdBtn);

		//consonant radio button which allows the user to guess a consonant
		consonantRdBtn = new JRadioButton("Consonant");
		consonantRdBtn.setOpaque(false);
		consonantRdBtn.addItemListener(this);
		buttonGroup.add(consonantRdBtn);
		consonantRdBtn.setBounds(18, 0, 109, 30);
		radioButtonPanel.add(consonantRdBtn);

		//loads all consonants into a string array
		String [] consonants = new String [21];
		int ascii = 65;
		for (int i = 0; i < 21; i++)
		{
			if ((ascii == 65) || (ascii == 69) || (ascii == 73) || (ascii == 79) || (ascii == 85))
			{
				ascii++;
			}
			consonants [i] = String.valueOf(Character.toChars(ascii));
			ascii++;
		}

		//spin button to launch the spinner
		btnSpin = new JButton("SPIN");
		btnSpin.addActionListener(this);
		btnSpin.setBounds(466, 545, 140, 38);
		btnSpin.setBackground(new Color(247, 227, 79));
		btnSpin.setBorder(thickBorder);
		contentPane.add(btnSpin);

		//user clicks this button when the think they know the answer to guess the word
		btnGuessAnswer = new JButton("Guess Answer");
		btnGuessAnswer.addActionListener(this);
		btnGuessAnswer.setBounds(466, 603, 140, 38);
		btnGuessAnswer.setBackground(new Color(247, 227, 79));
		btnGuessAnswer.setBorder(thickBorder);
		contentPane.add(btnGuessAnswer);

		//-------------------------------------------------------------------------------------------------------//
		//this panel shows up when vowelRadio button is on and holds the combobox and buy a vowel button
		vowelPanel = new JPanel ();
		vowelPanel.setBackground(purple);
		vowelPanel.setBounds(33, 582, 391, 72);
		vowelPanel.setBorder(thinBorder);
		contentPane.add(vowelPanel);

		vowelPanel.setVisible(false);
		vowelPanel.setLayout(null);

		//combobox to hold the vowels
		guessVowel = new JComboBox();
		guessVowel.setModel(new DefaultComboBoxModel(new String[] {"A", "E", "I", "O", "U"}));
		guessVowel.setBounds(29, 26, 48, 20);
		vowelPanel.add(guessVowel);


		btnBuyAVowel = new JButton("BUY A VOWEL");
		btnBuyAVowel.setBackground(new Color(247, 227, 79));
		btnBuyAVowel.setBorder(thinBorder);
		btnBuyAVowel.addActionListener(this);
		btnBuyAVowel.setBounds(136, 18, 207, 37);
		vowelPanel.add(btnBuyAVowel);

		//----------------------------------------------------------------------------------------------------------//

		//panel that turns on when consonantRadio button is turned on. this panel holds the comboBox and the button to guess a consonant
		consonantPanel = new JPanel ();
		consonantPanel.setBackground(purple);
		consonantPanel.setBounds(33, 582, 391, 72);
		contentPane.add(consonantPanel);
		consonantPanel.setBorder(thinBorder);
		consonantPanel.setVisible(true);
		consonantPanel.setLayout(null);

		guessConsonants = new JComboBox();
		guessConsonants.setBounds(29, 26, 48, 20);
		guessConsonants.setModel(new DefaultComboBoxModel(consonants));
		consonantPanel.add(guessConsonants);

		btnGuessAConsonant = new JButton("GUESS A CONSONANT");
		btnGuessAConsonant.setBackground(new Color(247, 227, 79));
		btnGuessAConsonant.setBorder(thinBorder);
		btnGuessAConsonant.addActionListener(this);
		btnGuessAConsonant.setBounds(136, 18, 207, 37);
		consonantPanel.add(btnGuessAConsonant);

		// ------------------------------------------------------------------------------------------------------------//

		//2 players that will play the game
		player1 = new PlayerRecord ();
		player2 = new PlayerRecord ();
		playersPlaying = new PlayerList();

		turn = 2; //allows players to switch each every turn. by if turn%2 = 0, if turn is even then its player1's turn otherwise its player2's turn
		order = 1;
		round = 1;
		//holds the image that shows who's turn it is
		JPanel playerTurn = new JPanel();
		playerTurn.setBounds(642, 498, 327, 156);
		contentPane.add(playerTurn);
		playerTurn.setLayout(null);
		playerTurn.setOpaque(false);

		//image to show its player1's turn
		try {
			player1Turn = new ImagePicture (new ImageIcon(ImageIO.read(new FileInputStream("res/player1Turn .png"))));
		} catch (IOException e) {
			System.out.println("player1Turn ImageIcon error");
		}	

		player1Turn.setBounds(10,11,307,115);
		player1Turn.setVisible(true);

		//image to show it's player 2's turn
		try {
			player2Turn = new ImagePicture (new ImageIcon(ImageIO.read(new FileInputStream("res/player2Turn.png"))));
		} catch (IOException e) {
			System.out.println("player2Turn ImageIcon error");
		}

		player2Turn.setBounds(10,11,307, 120);
		player2Turn.setVisible(false);

		//add the 2 whosTurn images to the playerTurn panel
		playerTurn.add(player1Turn);
		playerTurn.add(player2Turn);



		titlePic.setBounds(10, 0, 939, 93);
		titlePanel.add(titlePic);

		contentPane.add(titlePanel);

		//shows the round balance of player 1
		lblRoundBalPlayer1 = new JLabel("New label");
		lblRoundBalPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRoundBalPlayer1.setText(Integer.toString(player1RndBal));
		lblRoundBalPlayer1.setBounds(747, 300, 84, 34);
		contentPane.add(lblRoundBalPlayer1);

		//shows total balance of player 1
		lblPlayer1TBal = new JLabel("New label");
		lblPlayer1TBal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlayer1TBal.setText(Double.toString(player1.getBal()));
		lblPlayer1TBal.setBounds(747, 412, 84, 32);
		contentPane.add(lblPlayer1TBal);

		//shows round balance of player 2
		lblRoundBalPlayer2 = new JLabel("New label");
		lblRoundBalPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRoundBalPlayer2.setText(Integer.toString(player2RndBal));
		lblRoundBalPlayer2.setBounds(841, 300, 92, 34);
		contentPane.add(lblRoundBalPlayer2);

		//shows total balance of player 2
		lblPlayer2TBal = new JLabel("New label");
		lblPlayer2TBal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlayer2TBal.setText(Double.toString(player2.getBal()));
		lblPlayer2TBal.setBounds(841, 412, 92, 32);
		contentPane.add(lblPlayer2TBal);

		//all images on the right side of the window. I.e the player 1 and player 2 title, and roundbal and totalbal title
		try {
			lblRndBalTitle = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/RoundBal2.png"))));
			lblTBalTitle = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/TotalBal2.png"))));
			lblTurnTitle2 = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/player2TurnTitle.png"))));
			lblTurnTitle = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/player1TurnTitle.png"))));
		} catch (IOException e1) {
			System.out.println("line 366 ImageIcon error");
		}


		lblRndBalTitle.setBounds(737, 235, 204, 45);
		lblRndBalTitle.setBorder(customC);
		contentPane.add(lblRndBalTitle);

		lblTBalTitle.setBounds(737, 345, 204, 45);
		lblTBalTitle.setBorder(customC);
		contentPane.add(lblTBalTitle);

		lblTurnTitle.setBounds(711, 135, 120, 90);
		lblTurnTitle.setBorder(customC);
		contentPane.add(lblTurnTitle);

		lblTurnTitle2.setBounds(837, 135, 120, 90);
		lblTurnTitle2.setBorder(customC);
		contentPane.add(lblTurnTitle2);

		JLabel lblPlayerInfoBackgnd = new JLabel("");
		lblPlayerInfoBackgnd.setOpaque(true);
		lblPlayerInfoBackgnd.setBackground(purple);
		lblPlayerInfoBackgnd.setBorder(thickBorder);
		lblPlayerInfoBackgnd.setBounds(701, 128, 268, 359);
		contentPane.add(lblPlayerInfoBackgnd);

		//background
		try {
			backgndLbl = new ImagePicture (new ImageIcon(ImageIO.read(new FileInputStream("res/background.jpg"))));
		} catch (IOException e) {
			System.out.println("backgndLbl ImageIcon error");
		}

		backgndLbl.setBounds(0, 0, 989, 670);
		contentPane.add(backgndLbl);
	}

	//actionperformed method that runs everytime something which is being listened for actions is interacted with by the user.
	public void actionPerformed (ActionEvent evt) 
	{
		//if person clicks guess a consonant
		if (evt.getSource() == btnGuessAConsonant)
		{	
			if (order == 2)//when order == 2 player is able to guess a consonant or buy a vowel but they arent able to spin the wheel
				// this disables the players from spinning the wheel again incase they got a bad spin
			{
				vowelRdBtn.setEnabled(true);//this is here because if the user hasnt guessed a consonant yet they shouldn't be able to buy a vowel
				int count = 0; //counter for amount of guessed letter in the question

				if (!guessesDisplay.contains(guessConsonants.getSelectedItem().toString())) //if the user guessed consonant hasn't already been guessed
				{
					guesses += guessConsonants.getSelectedItem();
					guessesDisplay += guessConsonants.getSelectedItem() + " "; //add the newly guessed consonant to the guessed letters box
					displayQuestion();
					questionTxtArea.setText(questionDisplay);
					guessesTxtArea.setText(guessesDisplay);

					//if the user guessed word isnt in the question then its next player's turn
					if (!holder.contains(guessConsonants.getSelectedItem().toString()))
					{
						turn++;
					}
					//convert the guessed letter into a char
					char guessedLetter = guessConsonants.getSelectedItem().toString().charAt(0);
					//player didn't land on lose a turn or bankrupt then
					if ((spinner.getAmount() != -5) && (spinner.getAmount() != -1))
					{
						//get how many of the guessed letter are in the question for example are there 2 s's or 3 l's
						for (char letter : holder.toCharArray())
						{
							if (guessedLetter == letter)
								count++;
						}

						if (turn%2 == 0) //if player 1's turn then add the won amount to their round bal
						{
							player1RndBal += (spinner.getAmount()*count);
							lblRoundBalPlayer1.setText(Integer.toString(player1RndBal));
						}
						else //if player 2's turn then add the won amount to their round bal
						{
							player2RndBal += (spinner.getAmount()*count);
							lblRoundBalPlayer2.setText(Integer.toString(player2RndBal));
						}
					}

					order = 1;//switch the order so that user can click on spin but cannot guess again
				}
				else 
				{
					JOptionPane.showMessageDialog(contentPane, "This word is already guessed\nTry Again.");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(contentPane, "Spin the wheel");
			}

		}
		else if (evt.getSource() == btnBuyAVowel) 
		{
			if (!guessesDisplay.contains(guessVowel.getSelectedItem().toString())) //if the user guessed vowel hasn't already been guessed then proceed
			{
				if (turn%2 == 0) //if player 1's turn
				{
					if(player1RndBal>=500)//check if player 1 has sufficient bal to buy a vowel
					{
						guesses += guessVowel.getSelectedItem();
						guessesDisplay += guessVowel.getSelectedItem() + " ";
						displayQuestion();
						questionTxtArea.setText(questionDisplay);
						guessesTxtArea.setText(guessesDisplay);

						player1RndBal -= 500;//reduct the price of vowel from their round bal
						lblRoundBalPlayer1.setText(Integer.toString(player1RndBal));

					}
					else
						JOptionPane.showMessageDialog(contentPane, "You cannot afford a vowel.\nSpin the wheel or Guess a consonant");
				}
				else
				{
					if (player2RndBal >= 500) //check if player 2 has sufficient bal to buy a vowel
					{
						guesses += guessVowel.getSelectedItem();
						guessesDisplay += guessVowel.getSelectedItem() + " ";
						displayQuestion();
						questionTxtArea.setText(questionDisplay);
						guessesTxtArea.setText(guessesDisplay);

						player2RndBal -= 500;//deduct the price of a vowel from their round balance
						lblRoundBalPlayer2.setText(Integer.toString(player2RndBal));
					}
					else 
						JOptionPane.showMessageDialog(contentPane, "You cannot afford a vowel.\nSpin the wheel or Guess a consonant");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This vowel has already been guessed.\nTry Again.");
			}
		}

		else if (evt.getSource() == btnSpin)
		{
			if (order == 1) //allows the button to only spin if the order =1 meaning if they haven't already spun yet
			{
				vowelRdBtn.setEnabled(false);//prevents the user for buying a vowel right after a spin 

				//launch spinner
				spinner = new Spinner ();
				spinnerFrame = new JFrame ();
				try {
					background2 = new JLabel (new ImageIcon(ImageIO.read(new FileInputStream("res/spinnerBackgnd.jpg"))));
				} catch (IOException e) {
					System.out.println("backgroud2 imageicon error");
				}
				background2.setBounds(0, 0, 511, 490);
				//	spinnerFrame.add(background2);

				spinnerFrame.setSize(511, 530);
				spinnerFrame.setVisible(true);
				spinnerFrame.getContentPane().add(spinner);
				//get monetary value for where the spinner landed
				spinner.monetaryAssigner();

				order = 2;
				//if spinner landed on bankrupt then change their round bal to 0
				if (spinner.getAmount() == -1)
				{
					if (turn%2 == 0) //if player 1's turn
					{
						player1RndBal = 0;
					}
					else 
					{
						player2RndBal = 0;
					}

					lblRoundBalPlayer1.setText(Integer.toString(player1RndBal));
					lblRoundBalPlayer2.setText(Integer.toString(player2RndBal));

					order = 1;
					turn++;
				}
				else if (spinner.getAmount() == -5)	//if player landed on lose a turn then switch players and keep order 1 to make them spin again
				{
					turn++;
					order = 1;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(contentPane, "You already spinned. \n Guess a consonant or Buy a vowel");
			}
		}
		else if (evt.getSource() == btnGuessAnswer)
			//if the user click on guess answer
		{
			answer = JOptionPane.showInputDialog("What do you think is the answer?");
			answer = answer.toUpperCase();
			//if the user is wrong
			if (!answer.equalsIgnoreCase(holder))
			{
				JOptionPane.showMessageDialog(contentPane, "Wrong!, Next Player's Turn");
				turn++;
			}
			else//if the user is right
			{
				JOptionPane.showMessageDialog(contentPane, "Congratulations! You got it!");
				
				if (turn%2 == 0)//if player 1's turn
				{
					player1.setBal(player1.getBal() + player1RndBal);//if player 1 won add their round balance to their total balance
					lblPlayer1TBal.setText(Double.toString(player1.getBal()));
				}
				else//if player 2's turn
				{
					player2.setBal(player2.getBal() + player2RndBal); //if player 2 won then add their round balance to their total balance
					lblPlayer2TBal.setText(Double.toString(player2.getBal()));
				}
				if (round < 3)//if 3 rounds haven't been over yet then change question and start new round
				{
					guesses+=holder;
					displayQuestion();
					questionTxtArea.setText(questionDisplay);


					//**********************************************************//
					//clear all fields, change round balances to 0
					player1RndBal = 0;
					lblRoundBalPlayer1.setText(Integer.toString(player1RndBal));
					player2RndBal = 0;
					lblRoundBalPlayer2.setText(Integer.toString(player2RndBal));

					guesses = " ,.?;'-!";
					guessesDisplay = "";
					guessesTxtArea.setText(guessesDisplay);

					round++;
					turn++;

					//alternate the category for round 2 depending on what category was round 1
					if (d.getValue() == 1) 
					{
						if (round == 2)
						{
							holder = phraseQuestions[0].getSentence();
							lblHint.setText(preHint + "Phrase");
						}
						else if (round == 3)
						{
							holder = questions[1].getSentence();
							lblHint.setText(preHint + questions[1].getHint());
						}
					}
					else 
					{
						if (round == 2) 
						{
							holder = questions[0].getSentence();
							lblHint.setText(preHint + questions[0].getHint());
						}
						else if (round == 3)
						{
							holder = phraseQuestions[1].getSentence();
							lblHint.setText(preHint + "Phrase");
						}
					}
					holder = holder.toUpperCase();
					System.out.println(holder);
					JOptionPane.showMessageDialog(contentPane, "Round " + round);
					displayQuestion();
					questionTxtArea.setText(questionDisplay);
					order = 1;

				}
				else // if 3 rounds are over
				{
					if (player1.getBal()>player2.getBal()) //if player 1 won
					{
						JOptionPane.showMessageDialog(contentPane, "Congratulations you win! " + player1.getName() + " You won $" + player1.getBal()+"\nCheck out the leaderboard in the main menu"
								+ " \nTo see what other people won");
					}
					else if (player1.getBal()<player2.getBal())
					{
						JOptionPane.showMessageDialog(contentPane, "Congratulations you win! " + player2.getName() + " You won $" + player2.getBal()+"\nCheck out the leaderboard in the main menu"
								+ " \nTo see what other people won");
					}
					else 
					{
						JOptionPane.showMessageDialog(contentPane, "Its a tie! Wow this is so rare. Close this to go back to main menu.");
					}
					playersPlaying.insert(player1);
					playersPlaying.insert(player2);
					playersPlaying.saveToFile();
					new WheelOfFortuneMainMenu();
					super.dispose();
				}

			}
		}

		if (turn%2 == 0) //if player 1's turn
		{
			player1Turn.setVisible(true);
			player2Turn.setVisible(false);
		}
		else 
		{
			player2Turn.setVisible(true);
			player1Turn.setVisible(false);
		}

	}

	//custom method with no parameters. takes the question and replaces the letters that haven't been guessed with blank spaces
	public void displayQuestion () {

		questionDisplay = "";

		for (char letter : holder.toCharArray())//iterate over the letters
		{
			if (guesses.indexOf(letter) == -1)//if the letter hasn't already been guessed
			{
				questionDisplay += "_ ";
			}
			else 
				questionDisplay += letter + " ";
		}
	}

	//itemstatechanged method for item listener. this changes the buyAVowel method and the guessAConsonant method  depending on which radiobutton is clicked
	public void itemStateChanged (ItemEvent evt)
	{
		if (vowelRdBtn.isSelected()) {
			consonantPanel.setVisible(false);
			vowelPanel.setVisible(true);
		}

		else if (consonantRdBtn.isSelected()){
			vowelPanel.setVisible(false);
			consonantPanel.setVisible(true);
		}
	}
}

