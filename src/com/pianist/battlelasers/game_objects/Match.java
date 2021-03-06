package com.pianist.battlelasers.game_objects;

import java.util.LinkedList;

import com.pianist.battlelasers.game_objects.AI.AIDifficulty;

import android.content.Context;
import android.graphics.Point;

/**
 * The match class keeps track of the current match that is being played in the
 * activity. There are a set number of games that can be won, the first to win
 * more then half of them wins the match
 * 
 * @author Alex Szoke & Peter Gokhshteyn
 */
public class Match
{
	public boolean onePlayer;
	
	public String userName;
	
	public boolean isOnline;
	
	public String otherPlayerName;
	
	public int otherPlayerRating;
	
	public int playerNumberForOnline;
	
	public int onlineUserId;

	public int numGames;
	
	public boolean timerOn;

	public int numMirrors;

	public int turnLength;

	public int playerOneScore;

	public int playerTwoScore;

	public Layout currentLayout;

	public Layout[] layouts;

	public int gameNumber;

	public boolean nextGameStarted;

	public AIDifficulty computerDifficulty;
	
	public int onlineRating;
	
	public boolean matchStarted;
	
	public boolean endMatch;
	
	public boolean showDialogs;

	/**
	 * Resets the match back to its initial parameters after the game is over,
	 * or it is quit
	 */
	public void reset()
	{
		playerOneScore = 0;
		playerTwoScore = 0;
		currentLayout = null;
		gameNumber = 1;
		nextGameStarted = true;
		isOnline = false;
		matchStarted = false;
		showDialogs = false;
		endMatch = false;
	}
	
	/**
	 * Resets the match back to the intial parameters for an online game
	 */
	public void resetOnline()
	{
		reset();
		numGames = 1;
		playerNumberForOnline = 0;
		onePlayer = false;
		isOnline = true;
		numMirrors = 4;
		turnLength = 30;
		timerOn = true;
		otherPlayerName = "Anonymous";
		otherPlayerRating = 1000;
	}

	/**
	 * Creates a default match object
	 */
	public Match()
	{
		onePlayer = false;
		userName = "";
		isOnline = false;
		playerNumberForOnline = 0;
		numGames = 3;
		numMirrors = 4;
		turnLength = 30;
		playerOneScore = 0;
		playerTwoScore = 0;
		currentLayout = null;
		gameNumber = 1;
		nextGameStarted = true;
		computerDifficulty = AIDifficulty.Medium;
		timerOn = true;
		matchStarted = false;
		reset();

		// Create all the layouts in the game
		layouts = new Layout[10];

		// 6 Mirrors
		int[] rows = new int[] { 3, 4, 5, 6, 7, 8 };
		int[] cols = new int[] { 6, 5, 4, 3, 2, 1 };
		boolean[] isHorizonal = new boolean[] { true, false, true, true, false,
				true };
		layouts[0] = new Layout(rows, cols, isHorizonal);

		rows = new int[] { 5, 4, 5, 6, 7, 6 };
		cols = new int[] { 1, 2, 3, 4, 5, 6 };
		isHorizonal = new boolean[] { false, false, false, false, false, false };
		layouts[1] = new Layout(rows, cols, isHorizonal);

		rows = new int[] { 4, 5, 5, 6, 6, 7 };
		cols = new int[] { 2, 3, 4, 3, 4, 5 };
		isHorizonal = new boolean[] { true, false, false, false, false, true };
		layouts[2] = new Layout(rows, cols, isHorizonal);

		// 8 Mirrors
		rows = new int[] { 4, 4, 5, 5, 6, 6, 7, 7 };
		cols = new int[] { 3, 4, 2, 5, 2, 5, 3, 4 };
		isHorizonal = new boolean[] { false, false, true, true, true, true,
				false, false };
		layouts[3] = new Layout(rows, cols, isHorizonal);

		rows = new int[] { 4, 4, 5, 5, 6, 6, 7, 7 };
		cols = new int[] { 2, 5, 3, 4, 3, 4, 2, 5 };
		isHorizonal = new boolean[] { false, false, false, true, true, false,
				false, false };
		layouts[4] = new Layout(rows, cols, isHorizonal);

		rows = new int[] { 4, 4, 5, 5, 6, 6, 7, 7 };
		cols = new int[] { 2, 5, 3, 4, 3, 4, 2, 5 };
		isHorizonal = new boolean[] { false, false, false, false, false, false,
				false, false };
		layouts[5] = new Layout(rows, cols, isHorizonal);

		// 12 Mirrors
		rows = new int[] { 2, 2, 3, 3, 4, 4, 7, 7, 8, 8, 9, 9 };
		cols = new int[] { 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5 };
		isHorizonal = new boolean[] { true, true, true, true, true, true, true,
				true, true, true, true, true };
		layouts[6] = new Layout(rows, cols, isHorizonal);

		rows = new int[] { 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6 };
		cols = new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 };
		isHorizonal = new boolean[] { true, false, true, true, false, true,
				true, false, true, true, false, true };
		layouts[7] = new Layout(rows, cols, isHorizonal);

		rows = new int[] { 4, 5, 4, 4, 5, 4, 7, 6, 7, 7, 6, 7 };
		cols = new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 };
		isHorizonal = new boolean[] { true, false, true, true, false, true,
				true, false, true, true, false, true };
		layouts[8] = new Layout(rows, cols, isHorizonal);

		rows = new int[] { 3, 3, 3, 3, 4, 4, 7, 7, 8, 8, 8, 8 };
		cols = new int[] { 2, 3, 4, 5, 3, 4, 3, 4, 2, 3, 4, 5 };
		isHorizonal = new boolean[] { true, true, true, true, true, true, true,
				true, true, true, true, true };
		layouts[9] = new Layout(rows, cols, isHorizonal);
	}

	/**
	 * Increments the gameNumber so that the next layout can be played
	 */
	public void nextGame()
	{
		gameNumber++;
		nextGameStarted = true;
	}

	/**
	 * Chooses the next layout of the match
	 * 
	 * @return The chosen layout of the match
	 */
	public Layout getNextLayout()
	{
		// If the game was restarted, return the same layout
		if (!nextGameStarted)
		{
			currentLayout.generatePositions(false);
			return currentLayout;
		}

		nextGameStarted = false;

		// Return a random layout based on the number of mirrors
		if (numMirrors != 4)
		{
			while (true)
			{
				int layout = (int) (Math.random() * ((numMirrors == 3) ? 4 : 3))
						+ (numMirrors - 1) * 3;
				currentLayout = layouts[layout];
				currentLayout.generatePositions(false);
				return layouts[layout];
			}
		}
		else
		{
			while (true)
			{
				int layout = (int) (Math.random() * 10);
				currentLayout = layouts[layout];
				currentLayout.generatePositions(false);
				return layouts[layout];
			}
		}
	}
	
	public Layout getLayout(int id){
		return layouts[id];
	}
	
	/**
	 * Called when the user loses an online game, this method updates the user's rating using an elo formula
	 */
	public void loseOnlineGame() {
		matchStarted = false;
		endMatch = true;
		double expectedScore = 1 / (1 + Math.pow(10, (otherPlayerRating - onlineRating) / 400));
		onlineRating += Math.round(50 * (0 - expectedScore));
	}
	
	/**
	 * Called when the user wins an online game, this method updates the user's rating using an elo formula
	 */
	public void winOnlineGame() {
		matchStarted = false;
		endMatch = true;
		double expectedScore = 1 / (1 + Math.pow(10, (otherPlayerRating - onlineRating) / 400));
		onlineRating += Math.round(50 * (1 - expectedScore));
	}

	/**
	 * An inner class that keeps track of the different possible mirror layouts
	 * a game can have
	 * 
	 * @author Alex Szoke & Peter Gokhshteyn
	 * @version June 16, 2013
	 */
	public class Layout
	{
		private final int[] rows;

		private final int[] cols;

		private final boolean[] horizonal;

		private LinkedList<Boolean> isHorizonal;

		private LinkedList<Point> positions;

		/**
		 * Creates a new layout with the mirrors set up according to their row
		 * column and orientation in the 3 different arrays
		 * 
		 * @param rows
		 *            The row positions for the match
		 * @param cols
		 *            The column positions for the match
		 * @param isHorizonal
		 *            Whether the mirrors are horizontal or not
		 */
		public Layout(final int[] rows, final int[] cols,
				final boolean[] isHorizonal)
		{
			this.rows = rows;
			this.cols = cols;
			this.horizonal = isHorizonal;
		}

		/**
		 * Generates the positions of the mirrors by extracting them from the
		 * array of rows and columns as well as the list of orientations
		 */
		public void generatePositions(boolean flipY)
		{
			positions = new LinkedList<Point>();
			this.isHorizonal = new LinkedList<Boolean>();
			for (int mirror = 0; mirror < rows.length; mirror++)
			{
				if (flipY) {
					positions.add(new Point(11 - rows[mirror], cols[mirror]));
				} else {
					positions.add(new Point(rows[mirror], cols[mirror]));
				}
				this.isHorizonal.add(horizonal[mirror]);
			}
		}

		/**
		 * Returns the position of the next mirror in the layout
		 * 
		 * @return the position of the next mirror in the layout
		 */
		public Point getNextPosition()
		{
			if (positions.isEmpty())
				return null;
			return positions.removeLast();
		}

		/**
		 * Checks if the next mirror is horizontal or not
		 * 
		 * @return true if the next mirror is horizontal, false if it is
		 *         vertical
		 */
		public boolean isNextHorizontal()
		{
			return isHorizonal.removeLast();
		}
	}
}
