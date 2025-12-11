/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author John Wayne Enrique
 */
public class GuessTheColorGame extends JFrame{
    private static final String[] WORDS = {"RED", "GREEN", "BLUE", "YELLOW", "ORANGE", "PURPLE"};
    private static final String[] COLORS = {"red", "green", "blue", "yellow", "orange", "purple"};

    private static int score = 0;
    private static int lives = 6;
    private static JLabel wordLabel;
    private static JLabel timeLabel;
    private static JLabel scoreLabel;
    private static JLabel livesLabel;
    private static JButton[] colorButtons;
    private static Timer timer;
    private static int timeRemaining;
    private static int gameDuration;
    private static int startingLives;
    private static final String HIGH_SCORE_FILE = "highscore.txt";
    private static int highScore = 0;
    private static boolean gameInProgress = false;
    private static boolean musicOn = true; // Flag for background music
    private static boolean musicPlaying = false; // Flag to track if music is already playing
    private static Clip backgroundMusicClip;
    
    private static final int TIMER_INITIAL_DELAY_EASY = 2000; // Initial delay in milliseconds
    private static final int TIMER_INITIAL_DELAY_MODERATE = 1500; // Initial delay in milliseconds
    private static final int TIMER_INITIAL_DELAY_HARD = 1000; // Initial delay in milliseconds
    private static final int TIMER_SPEEDUP_INTERVAL = 5; // Number of rounds before speedup
    private static final int TIMER_SPEEDUP_AMOUNT = 200; // Amount to reduce timer delay


    public static void main(String[] args) {
        loadHighScore();
        SwingUtilities.invokeLater(() -> createStartFrame());
    }

    private static void loadHighScore() {
        try (Scanner scanner = new Scanner(new File(HIGH_SCORE_FILE))) {
            if (scanner.hasNextInt()) {
                highScore = scanner.nextInt();
            }
        } catch (IOException e) {
            System.err.println("Error loading high score: " + e.getMessage());
        }
    }

    private static void saveHighScore() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(HIGH_SCORE_FILE))) {
            writer.println(highScore);
        } catch (IOException e) {
            System.err.println("Error saving high score: " + e.getMessage());
        }
    }

    private static void createStartFrame() {
        JFrame startFrame = new JFrame("Guess the Color Game - Start Menu");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(400, 700);
        startFrame.setResizable(false);

        //Panel with GridBagLayout for precise component placement
        JPanel panel = new JPanel(new GridBagLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //the background image
                try {
                    Image backgroundImage = ImageIO.read(new File("hunt-bg.png"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        //Constraints for the settings icon
        GridBagConstraints settingsConstraints = new GridBagConstraints();
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 0;
        settingsConstraints.anchor = GridBagConstraints.NORTH; // Align to the top
        settingsConstraints.insets = new Insets(10, 350, 10, 10); // Padding

        // Settings logo (placeholder for now)
        ImageIcon settingsIcon = new ImageIcon("settings_icon.png");
        Image settingsImage = settingsIcon.getImage();
        Image modifiedSettingsImage = settingsImage.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        settingsIcon = new ImageIcon(modifiedSettingsImage);
        JLabel settingsLabel = new JLabel(settingsIcon);
        panel.add(settingsLabel, settingsConstraints);

        // Constraints for the high score label
        GridBagConstraints highScoreConstraints = new GridBagConstraints();
        highScoreConstraints.gridx = 0;
        highScoreConstraints.gridy = 1;
        highScoreConstraints.anchor = GridBagConstraints.CENTER; // Align to the center horizontally
        highScoreConstraints.insets = new Insets(10, 10, 140, 10); // Padding

        // High score label and add it to the panel
        JLabel highScoreLabel = new JLabel("High Score: " + highScore);
        highScoreLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Custom font
        panel.add(highScoreLabel, highScoreConstraints);

        // Constraints for the start button
        GridBagConstraints startButtonConstraints = new GridBagConstraints();
        startButtonConstraints.gridx = 0;
        startButtonConstraints.gridy = 2;
        startButtonConstraints.gridwidth = 2; // Span across two columns
        startButtonConstraints.anchor = GridBagConstraints.CENTER; // Align to the center horizontally
        startButtonConstraints.insets = new Insets(20, 10, 10, 10); // Padding

        // Add the start button to the panel
        RoundedButton startButton = new RoundedButton("Start Game", Color.WHITE, Color.LIGHT_GRAY, Color.PINK, Color.BLACK);
            startButton.addActionListener(e -> {
            startFrame.dispose();
            chooseGameMode();
            playClickSound(); // Play the click sound when the Start button is clicked
        });
            panel.add(startButton, startButtonConstraints);

        // Constraints for the reset button
        GridBagConstraints resetButtonConstraints = new GridBagConstraints();
        resetButtonConstraints.gridx = 0;
        resetButtonConstraints.gridy = 3;
        resetButtonConstraints.gridwidth = 2; // Span across two columns
        resetButtonConstraints.anchor = GridBagConstraints.CENTER; // Align to the center horizontally
        resetButtonConstraints.insets = new Insets(10, 10, 280, 10); // Padding

        // Add the reset button to the panel
        RoundedButton resetButton = new RoundedButton("Reset High Score", Color.WHITE, Color.LIGHT_GRAY, Color.ORANGE, Color.BLACK);
        resetButton.addActionListener(e -> {
            highScore = 0;
            saveHighScore();
            highScoreLabel.setText("High Score: " + highScore);
            playClickSound(); // Play the click sound when the Easy button is clicked
            JOptionPane.showMessageDialog(null, "High Score reset successfully!");
            playClickSound(); // Play the click sound when the Easy button is clicked
        });
        panel.add(resetButton, resetButtonConstraints);
        
                   
        // Constraints for the help button
        GridBagConstraints helpButtonConstraints = new GridBagConstraints();
        helpButtonConstraints.gridx = 0;
        helpButtonConstraints.gridy = 4;
        helpButtonConstraints.gridwidth = 2; // Span across two columns
        helpButtonConstraints.anchor = GridBagConstraints.CENTER; // Align to the center horizontally
        helpButtonConstraints.insets = new Insets(-535, 10, 10, 10); // Padding

        // Add a button to open the help frame
        RoundedButton helpButton = new RoundedButton("How to Play?", Color.WHITE, Color.LIGHT_GRAY, Color.MAGENTA, Color.BLACK);
        helpButton.addActionListener(e -> {
        openHelpFrame();
        playClickSound(); // Play the click sound when the Easy button is clicked
        });
        panel.add(helpButton, helpButtonConstraints);
        
                
        // Constraints for the exit button
        GridBagConstraints exitButtonConstraints = new GridBagConstraints();
        exitButtonConstraints.gridx = 0;
        exitButtonConstraints.gridy = 4;
        exitButtonConstraints.gridwidth = 2; // Span across two columns
        exitButtonConstraints.anchor = GridBagConstraints.CENTER; // Align to the center horizontally
        exitButtonConstraints.insets = new Insets(10, 10, 15, 10); // Padding

        // Add the exit button to the panel
        RoundedButton exitButton = new RoundedButton("Exit", Color.WHITE, Color.LIGHT_GRAY, Color.RED, Color.BLACK);
        exitButton.addActionListener(e -> { 
            playClickSound(); // Play the click sound when the Easy button is clicked
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to Exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
            playClickSound(); // Play the click sound before exiting
                if (choice == JOptionPane.YES_OPTION){                
                    System.exit(0); // Exit the application after playing the sound
                } 
            });
        panel.add(exitButton, exitButtonConstraints);
        
        startFrame.add(panel); // Panel directly to the frame

        // Action for settings label click
        settingsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openSettingsMenu(startFrame);   
            }
        });

        // Frame on the screen
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);

        // Play background music automatically
        if (musicOn && !musicPlaying) {
        playBackgroundMusic();
    }
}
    
    private static class RoundedButton extends JButton {
        private final  Color backgroundColor;
        private final  Color borderColor;
        private final  Color hoverColor;
        private final  Color textColor;

        public RoundedButton(String text, Color backgroundColor, Color borderColor, Color hoverColor, Color textColor) {
            super(text);
            this.backgroundColor = backgroundColor;
            this.borderColor = borderColor;
            this.hoverColor = hoverColor;
            this.textColor = textColor;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setOpaque(false);
            setForeground(textColor);
            setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
            setPreferredSize(new Dimension(120, 40));
            setFont(new Font("Arial", Font.BOLD, 14));
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setBackground(hoverColor);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    setBackground(backgroundColor);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, width, height, 20, 20);
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, width - 1, height - 1, 20, 20);
            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            g2.dispose();
        }
    }

    private static void openSettingsMenu(JFrame parentFrame) {
        JDialog settingsDialog = new JDialog(parentFrame, "Settings", true);
    settingsDialog.setLayout(new BoxLayout(settingsDialog.getContentPane(), BoxLayout.Y_AXIS));
    settingsDialog.setSize(200, 150);
    settingsDialog.setLocationRelativeTo(parentFrame);

    JLabel musicLabel = new JLabel("Background Music");

    // Create a panel for the toggle button and use BoxLayout with X_AXIS for center alignment
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

    // Add horizontal glue before the button to center-align it
    buttonPanel.add(Box.createHorizontalGlue());

    JToggleButton musicToggleButton = new JToggleButton("On");
    playClickSound(); // Play the click sound when the Easy button is clicked
    musicToggleButton.setSelected(musicOn);

    // Set the background and text colors for the button
    musicToggleButton.setBackground(Color.RED);
    musicToggleButton.setForeground(Color.BLACK);

    // Set the font and make the button appear 2D by removing the focus border
    musicToggleButton.setFont(musicToggleButton.getFont().deriveFont(Font.BOLD));
    musicToggleButton.setFocusPainted(false);

    // Set the margin to create space around the button text
    musicToggleButton.setMargin(new Insets(7, 10, 7, 10));

    
    // Add an empty border to create a margin for the musicLabel
    musicLabel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10)); // top, left, bottom, right
    
    
    musicToggleButton.addActionListener(e -> {
        musicOn = musicToggleButton.isSelected();
        musicToggleButton.setText(musicOn ? "On" : "Off");
        if (musicOn && !musicPlaying) { // Check if music is on and not already playing
            playClickSound(); // Play the click sound when the Easy button is clicked
            playBackgroundMusic();
        } else if (!musicOn && musicPlaying) { // Check if music is off and music is playing
            playClickSound(); // Play the click sound when the Easy button is clicked
            stopBackgroundMusic();
        }
    });

    buttonPanel.add(musicToggleButton);

    // Add horizontal glue after the button to center-align it
    buttonPanel.add(Box.createHorizontalGlue());

    settingsDialog.add(musicLabel);
    settingsDialog.add(buttonPanel);

    settingsDialog.setLocationRelativeTo(parentFrame);
    settingsDialog.setVisible(true);
}
    
    private static void openHelpFrame() {
        JFrame helpFrame = new JFrame("Help - Guess the Color Game");
        helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        helpFrame.setSize(400, 700);
        helpFrame.setResizable(false);

        JPanel helpPanel = new JPanel(new GridBagLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Paint the background image
                try {
                    Image backgroundImage = ImageIO.read(new File("help-bg.png"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        // Add a back button to return to the start frame
        GridBagConstraints backButtonConstraints = new GridBagConstraints();
        backButtonConstraints.gridx = 0;
        backButtonConstraints.gridy = 0;
        backButtonConstraints.anchor = GridBagConstraints.NORTHWEST; // Align to the top left
        backButtonConstraints.insets = new Insets(525, 10, 10, 10); // Padding
    
        RoundedButton backButton = new RoundedButton("Back", Color.WHITE, Color.LIGHT_GRAY, Color.BLUE, Color.BLACK);
        backButton.addActionListener(e -> {
            playClickSound(); // Play the click sound when the Easy button is clicked
            helpFrame.dispose(); // Close the help frame
            createStartFrame(); // Re-create the start frame
        });
        helpPanel.add(backButton, backButtonConstraints);

        helpFrame.add(helpPanel);
        helpFrame.setLocationRelativeTo(null);
        helpFrame.setVisible(true);
    }

    private static void playBackgroundMusic() {
        if (musicOn && !musicPlaying) { // Check if music is not already playing
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("bg-music.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
                backgroundMusicClip = clip;
                musicPlaying = true; // Set flag to true
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
             System.err.println("Error playing background music: " + e.getMessage());
            }
        }
    }
    
    private static void playClickSound() {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("clickeff.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
        System.err.println("Error playing click sound: " + e.getMessage());
    }
}

    private static void stopBackgroundMusic() {
        if (backgroundMusicClip != null) {
            backgroundMusicClip.stop();
            backgroundMusicClip.close();
            musicPlaying = false; // Set flag to false
        }
    }

    private static void chooseGameMode() {
        if (musicOn && !musicPlaying) {
        playBackgroundMusic();
    }
        
        JFrame modeFrame = new JFrame("Choose Game Mode");
        modeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        modeFrame.setSize(400, 700);
        modeFrame.setResizable(false);
        
        //Background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                try {
                    Image backgroundImage = ImageIO.read(new File("game-bg.png"));
                    // Draw the background image
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    modeFrame.setContentPane(backgroundPanel);
        
        modeFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(40, 10, -10, 10);

        RoundedButton easyButton = new RoundedButton("Easy", Color.WHITE, Color.LIGHT_GRAY, Color.GREEN, Color.BLACK);
        easyButton.addActionListener(e -> {
            startGameMode(15, 6); // Easy mode: 15 seconds, 6 lives
            modeFrame.dispose();
            playClickSound(); // Play the click sound when the Easy button is clicked
        });
        modeFrame.add(easyButton, gbc);

        // Add space between buttons
        gbc.gridy++;
        modeFrame.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        RoundedButton moderateButton = new RoundedButton("Moderate", Color.WHITE, Color.LIGHT_GRAY, Color.CYAN, Color.BLACK);
        moderateButton.addActionListener(e -> {
            startGameMode(10, 4); // Moderate mode: 10 seconds, 4 lives
            modeFrame.dispose();
            playClickSound(); // Play the click sound when the Easy button is clicked
        });
        modeFrame.add(moderateButton, gbc);

        // Add space between buttons
        gbc.gridy++;
        modeFrame.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        RoundedButton hardButton = new RoundedButton("Hard", Color.WHITE, Color.LIGHT_GRAY, Color.RED, Color.BLACK);
        hardButton.addActionListener(e -> {
            startGameMode(5, 2); // Hard mode: 5 seconds, 2 lives
            modeFrame.dispose();
            playClickSound(); // Play the click sound when the Easy button is clicked
        });
        modeFrame.add(hardButton, gbc);

        // Add space between buttons and exit button
        gbc.gridy++;
        gbc.insets = new Insets(200, 10, -100, 10); // Add margin between Hard button and Back button

        RoundedButton exitButton = new RoundedButton("Back", Color.WHITE, Color.LIGHT_GRAY, Color.YELLOW, Color.BLACK);
        exitButton.addActionListener(e -> {
            modeFrame.dispose();
            createStartFrame(); // Redirect to Start Game frame
            playClickSound(); // Play the click sound when the Easy button is clicked
        });
        modeFrame.add(exitButton, gbc);

        // Set the exit button at the bottom of the frame
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.PAGE_END;
        modeFrame.add(exitButton, gbc);

        modeFrame.setLocationRelativeTo(null);
        modeFrame.setVisible(true);
    }

    private static void startGameMode(int duration, int startingLives) {
        gameDuration = duration;
        timeRemaining = gameDuration;
        lives = startingLives;
        GuessTheColorGame.startingLives = startingLives; // Update the startingLives variable
        score = 0;
        gameInProgress = true;
        
        // Stop background music if it's playing
    if (musicPlaying) {
        stopBackgroundMusic();
        musicPlaying = false; // Update music playing flag
    }
        // Play background music for Easy mode only
        switch (duration) {
            case 15 -> // Easy mode: 15 seconds
                playEasyBackgroundMusic();
            case 10 -> // Moderate mode: 10 seconds
                playModerateBackgroundMusic();
            case 5 -> // Hard mode: 5 seconds
                playHardBackgroundMusic();
            default -> {
            }
        }
        
        SwingUtilities.invokeLater(() -> createAndShowGameFrame());
        startTimer();
    }
    
    private static void playEasyBackgroundMusic() {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("easy-bg.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        backgroundMusicClip = clip;
        musicPlaying = true; // Set flag to true
    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
        System.err.println("Error playing Easy background music: " + e.getMessage());
    }
}
    
    private static void playModerateBackgroundMusic() {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("moderate-bg.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        backgroundMusicClip = clip;
        musicPlaying = true; // Set flag to true
    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
        System.err.println("Error playing Moderate background music: " + e.getMessage());
    }
}

    private static void playHardBackgroundMusic() {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("hard-bg.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        backgroundMusicClip = clip;
        musicPlaying = true; // Set flag to true
    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
        System.err.println("Error playing Hard background music: " + e.getMessage());
    }
}
    private static void createAndShowGameFrame() {
        JFrame gameFrame = new JFrame("Guess the Color of the Text Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(400, 700);
        gameFrame.setResizable(false);
        
        // Set the background image directly on the gameFrame content pane
    JPanel backgroundPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                Image backgroundImage = ImageIO.read(new File("text-bg.png"));
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    backgroundPanel.setLayout(new BorderLayout()); // Set BorderLayout for panel
    gameFrame.setContentPane(backgroundPanel);

        JPanel topPanel = new JPanel(new BorderLayout());
        gameFrame.add(topPanel, BorderLayout.NORTH);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        topPanel.add(scorePanel, BorderLayout.WEST);

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scorePanel.add(scoreLabel);

        scorePanel.add(Box.createRigidArea(new Dimension(90, 0)));

        JPanel timePanel = new JPanel();
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.X_AXIS));
        topPanel.add(timePanel, BorderLayout.CENTER);

        JLabel timePlaceholderLeft = new JLabel("");
        timePanel.add(timePlaceholderLeft);

        timeLabel = new JLabel("Time: " + timeRemaining + "s");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timePanel.add(timeLabel);

        JLabel timePlaceholderRight = new JLabel("");
        timePanel.add(timePlaceholderRight);

        JPanel livesPanel = new JPanel();
        livesPanel.setLayout(new BoxLayout(livesPanel, BoxLayout.X_AXIS));
        topPanel.add(livesPanel, BorderLayout.EAST);

        livesLabel = new JLabel("Lives: " + lives);
        livesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        livesPanel.add(livesLabel);

        wordLabel = new JLabel();
        wordLabel.setFont(new Font("Arial", Font.BOLD, 36));
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameFrame.add(wordLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        colorButtons = new JButton[COLORS.length];
        for (int i = 0; i < COLORS.length; i++) {
            JButton button = new JButton(COLORS[i]);
            button.setPreferredSize(new Dimension(100, 40));
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setBackground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            button.addActionListener(new ColorButtonListener());
            buttonPanel.add(button);
            colorButtons[i] = button;
        }
        gameFrame.add(buttonPanel, BorderLayout.SOUTH);

        updateWordAndColor();  // Start the game by updating the word and color

        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    private static void startTimer() {
        int timerDelay;
        timerDelay = switch (gameDuration) {
            case 15 -> TIMER_INITIAL_DELAY_EASY;
            case 10 -> TIMER_INITIAL_DELAY_MODERATE;
            case 5 -> TIMER_INITIAL_DELAY_HARD;
            default -> TIMER_INITIAL_DELAY_EASY;
        }; // Easy mode
        // Moderate mode
        // Hard mode
        
        timer = new Timer(timerDelay, e -> {           
            timeRemaining--;
            timeLabel.setText("Time: " + timeRemaining + "s"); // Update time label text
            if (timeRemaining <= 0) {
                timer.stop();
                if (gameInProgress) { // Check if the game is in progress
                    gameInProgress = false; // Set game not in progress
                    JOptionPane.showMessageDialog(null, """
                            Time's up! Moving to the next round.
                             Game Over!""");
                    int currentScore = score; // Store the current score
                    lives = startingLives; // Reset lives count for next round
                    livesLabel.setText("Lives: " + lives);
                    score = 0; // Reset score
                    scoreLabel.setText("Score: " + score);
                    updateWordAndColor();

                    // Show a dialog to handle high score saving
                    handleHighScore(currentScore);

                    redirectToModesFrame(); // Redirect to mode selection
                }
            }
        });
        timer.start();
    }

    private static void handleHighScore(int currentScore) {
        stopBackgroundMusic(); // Stop the current background music
        playBackgroundMusic(); // Play the general background music
        
        if (currentScore > highScore) {
            highScore = currentScore;
            saveHighScore();
            JOptionPane.showMessageDialog(null, "Congratulations! New High Score: " + highScore);
        } else {
            JOptionPane.showMessageDialog(null, "Game Over! Your final score is: " + currentScore);
        }
    }

    private static void redirectToModesFrame() {
        // Create the frame for choosing game modes
        JFrame modeFrame = new JFrame("Choose Game Mode");
        modeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        modeFrame.setSize(400, 700);
        modeFrame.setResizable(false);
        
        //Background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                try {
                    Image backgroundImage = ImageIO.read(new File("game-bg.png"));
                    // Draw the background image
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    modeFrame.setContentPane(backgroundPanel);
        
        modeFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(40, 10, -10, 10);

        // Add buttons for Easy, Moderate, and Hard modes
        RoundedButton easyButton = new RoundedButton("Easy", Color.WHITE, Color.LIGHT_GRAY, Color.GREEN, Color.BLACK);
        easyButton.addActionListener(e -> {
            startGameMode(15, 6); // Easy mode: 15 seconds, 6 lives
            modeFrame.dispose();
            playClickSound(); // Play the click sound when the Easy button is clicked
        });
        modeFrame.add(easyButton, gbc);

        gbc.gridy++;
        modeFrame.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        RoundedButton moderateButton = new RoundedButton("Moderate", Color.WHITE, Color.LIGHT_GRAY, Color.BLUE, Color.BLACK);
        moderateButton.addActionListener(e -> {
            startGameMode(10, 4); // Moderate mode: 10 seconds, 4 lives
            modeFrame.dispose();
            playClickSound(); // Play the click sound when the Easy button is clicked
        });
        modeFrame.add(moderateButton, gbc);

        gbc.gridy++;
        modeFrame.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        RoundedButton hardButton = new RoundedButton("Hard", Color.WHITE, Color.LIGHT_GRAY, Color.RED, Color.BLACK);
        hardButton.addActionListener(e -> {
            startGameMode(5, 2); // Hard mode: 5 seconds, 2 lives
            modeFrame.dispose();
            playClickSound(); // Play the click sound when the Easy button is clicked
        });
        modeFrame.add(hardButton, gbc);

        // Add space between buttons and exit button
        gbc.gridy++;
        gbc.insets = new Insets(200, 10, -100, 10); // Add margin between Hard button and Back button

        RoundedButton exitButton = new RoundedButton("Back", Color.WHITE, Color.LIGHT_GRAY, Color.YELLOW, Color.BLACK);
        exitButton.addActionListener(e -> {
            modeFrame.dispose();
            createStartFrame(); // Redirect to Start Game frame
            playClickSound(); // Play the click sound when the Easy button is clicked
        });
        playClickSound(); // Play the click sound when the Easy button is clicked
        modeFrame.add(exitButton, gbc);

        // Set the exit button at the bottom of the frame
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.PAGE_END;
        modeFrame.add(exitButton, gbc);

        modeFrame.setLocationRelativeTo(null);
        modeFrame.setVisible(true);
        
    }

    private static void updateWordAndColor() {
        Random random = new Random();
        int index = random.nextInt(WORDS.length);

        String word = WORDS[index];
        String color = COLORS[random.nextInt(COLORS.length)];

        wordLabel.setText(word);
        wordLabel.setForeground(getColorFromString(color));

        timeRemaining = gameDuration; // Reset the timer for each round
        timeLabel.setText("Time: " + timeRemaining + "s"); // Update time label text
    
       //Speed up the timer
    if (score > 0 && score % TIMER_SPEEDUP_INTERVAL == 0) {
            int timerDelay = timer.getDelay();
            timer.setDelay(Math.max(timerDelay - TIMER_SPEEDUP_AMOUNT, 0));
    }
}

    private static Color getColorFromString(String color) {
        return switch (color) {
            case "red" -> Color.RED;
            case "green" -> Color.GREEN;
            case "blue" -> Color.BLUE;
            case "yellow" -> Color.YELLOW;
            case "orange" -> Color.ORANGE;
            case "purple" -> new Color(128, 0, 128);
            default -> Color.BLACK;
        }; // Purple
    }

    private static class ColorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gameInProgress) return; // Ignore input if the game is not in progress

            JButton clickedButton = (JButton) e.getSource();
            String guess = clickedButton.getText().toLowerCase();

            String actualColor = COLORS[wordLabel.getForeground().equals(Color.BLACK) ? 0 : findColorIndex(wordLabel.getForeground())];
            if (guess.equals(actualColor)) {
                score++;
                scoreLabel.setText("Score: " + score); // Update score label
            } else {
                score -= 2;
                if (score < 0); // Ensure score go negative if the pick answer is incorrect  
                scoreLabel.setText("Score: " + score); // Update score label
                
                lives--;
                livesLabel.setText("Lives: " + lives); // Update lives label
                if (lives <= 0) {
                    timer.stop(); // Stop the timer
                    gameInProgress = false; // Set game not in progress
                    JOptionPane.showMessageDialog(null, "Game Over! Your final score is: " + score +
                            "\nHigh Score: " + highScore);
                    if (score > highScore) {
                        highScore = score;
                        saveHighScore();
                    }
                    int choice = JOptionPane.showConfirmDialog(null, "Try Again?", "Game Over", JOptionPane.YES_NO_OPTION);
                    switch (choice) {
                        case JOptionPane.YES_OPTION -> {
                            resetGame(); // Reset the game
                            startGameMode(gameDuration, startingLives); // Restart the game with the same mode
                        }
                        case JOptionPane.NO_OPTION -> returnToModeSelection(); // Return to mode selection screen
                        default -> System.exit(0); // Exit the game
                        
                    }
                }
            }
            

            updateWordAndColor();
            playClickSound(); // Play the click sound
        }

        private int findColorIndex(Color color) {
            if (color == null) return -1; // Handle null case
            for (int i = 0; i < COLORS.length; i++) {
                if (getColorFromString(COLORS[i]).getRGB() == color.getRGB()) {
                    return i;
                }
            }
            return -1;
        }

        private void resetGame() {
            score = 0;
            lives = startingLives;
            timeRemaining = gameDuration;
            timer.stop(); // Stop the timer
            stopBackgroundMusic(); // Stop the current background music
            playBackgroundMusic(); // Play the general background music
            updateWordAndColor();
        }

        private void returnToModeSelection() {
            gameInProgress = false;
            stopBackgroundMusic(); // Stop the current background music
            playBackgroundMusic(); // Play the general background music
            SwingUtilities.invokeLater(() -> chooseGameMode());
        }
    }
}

