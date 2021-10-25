/* Copyright 2009-2019 David Hadka
 *
 * This file is part of the MOEA Framework.
 *
 * The MOEA Framework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * The MOEA Framework is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the MOEA Framework.  If not, see <http://www.gnu.org/licenses/>.
 */
package SS1Problem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class World {
	
	/**
	 * The x position of the ant.
	 */
	private int x;
	
	/**
	 * The y position of the ant.
	 */
	private int y;
	
	/**
	 * The width of the world.
	 */
	private int width;
	
	/**
	 * The height of the world.
	 */
	private int height;
	
	/**
	 * The state of each cell
	 */
	private State[][] map;
	
	/**
	 * The current direction the ant is facing.
	 */
	private Direction direction;
	
	/**
	 * The number of moves required by the ant
	 */
	private int numberOfMoves;
	
	/**
	 * The number of remaining moves
	 */
	private int remainingMoves;
	
	/**
	 * The maximum number of moves
	 */
	private int maxMoves;
	
	/**
	 * The amount of food eaten by the ant.
	 */
	private int foodEaten;
	
	/**
	 * The total amount of food available
	 */
	private int totalFood;
	
	/**
	 * Constructs a new world using the ant trail defined in the specified
	 * file.
	 */
	public World(File file, int maxMoves) throws FileNotFoundException,
	IOException {
		this(new FileReader(file), maxMoves);
	}
	
	/**
	 * Constructs a new world using the ant trail defined in the specified
	 * input stream.
	 * 
	 * @param stream the stream containing the ant trail
	 * @param maxMoves the maximum number of moves the ant can expend to find
	 *        food
	 * @throws IOException if an I/O error occurred
	 */
	public World(InputStream stream, int maxMoves) throws IOException {
		this(new InputStreamReader(stream), maxMoves);
	}
	
	/**
	 * Constructs a new world using the ant trail defined in the specified
	 * reader.
	 */
	public World(Reader reader, int maxMoves) throws IOException {
		super();
		this.maxMoves = maxMoves;
		
		load(reader);
		reset();
	}
	
	/**
	 * Loads the ant trail.
	 */
	protected void load(Reader reader) throws IOException {
		BufferedReader lineReader = null;
		
		try {
			lineReader = new BufferedReader(reader);
			
			//read out the world dimension
			String line = lineReader.readLine();
			
			if (line == null) {
				throw new IOException("trail missing header line");
			}
			
			String[] tokens = line.split("\\s+");
			width = Integer.parseInt(tokens[0]);
			height = Integer.parseInt(tokens[1]);
			map = new State[width][height];
			
			//read the world state
			int i = 0;
			int j = 0;
			
			while ((line = lineReader.readLine()) != null) {
				i = 0;
				
				while (i < line.length()) {
					char c = line.charAt(i);
						
					if (c == ' ') {
						map[i][j] = State.EMPTY;
					} else if (c == '#') {
						map[i][j] = State.FOOD;
						totalFood++;
					} else if (c == '.') {
						map[i][j] = State.TRAIL;
					} else {
						throw new IllegalStateException();
					}
					
					i++;
				}
				
				// fill any remaining columns
				while (i < width) {
					map[i][j] = State.EMPTY;
					i++;
				}
				
				j++;
			}
			
			// fill any remaining rows
			while (j < height) {
				i = 0;
				
				while (i < width) {
					map[i][j] = State.EMPTY;
					i++;
				}
				
				j++;
			}
		} finally {
			if (lineReader != null) {
				lineReader.close();
			}
		}
	}
	
	/**
	 * Resets this world, returning the ant to its starting position and
	 * resetting the state of all cells to their original states.
	 */
	public void reset() {
		x = 0;
		y = 0;
		direction = Direction.RIGHT;
		remainingMoves = maxMoves;
		foodEaten = 0;
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (map[i][j].equals(State.EATEN)) {
					map[i][j] = State.FOOD;
				}
			}
		}
	}
	
	/**
	 * Returns the maximum number of moves the ant can expend to find food.
	 */
	public int getMaxMoves() {
		return maxMoves;
	}
	
	/**
	 * Returns the number of remaining moves the ant can perform to find food
	 */
	public int getRemainingMoves() {
		return remainingMoves;
	}

	/**
	 * Returns the number of moves required by the ant to find and eat all the food
	 */
	public int getNumberOfMoves() {
		return numberOfMoves;
	}
	
	/**
	 * Returns the amount of food eaten by the ant.
	 */
	public int getFoodEaten() {
		return foodEaten;
	}
	
	/**
	 * Returns the total amount of food available
	 */
	public int getTotalFood() {
		return totalFood;
	}
	
	/**
	 * Returns the amount of food remaining
	 */
	public int getRemainingFood() {
		return totalFood - foodEaten;
	}

	/**
	 * Turns the ant right.
	 */
	public void turnRight() {
		switch (direction) {
		case UP:
			direction = Direction.RIGHT;
			break;
		case DOWN:
			direction = Direction.LEFT;
			break;
		case RIGHT:
			direction = Direction.DOWN;
			break;
		case LEFT:
			direction = Direction.UP;
			break;
		default:
			throw new IllegalStateException();
		}
		
		remainingMoves--;
	}
	
	/**
	 * Turns the ant left.
	 */
	public void turnLeft() {
		switch (direction) {
		case UP:
			direction = Direction.LEFT;
			break;
		case DOWN:
			direction = Direction.RIGHT;
			break;
		case RIGHT:
			direction = Direction.UP;
			break;
		case LEFT:
			direction = Direction.DOWN;
			break;
		default:
			throw new IllegalStateException();
		}
		
		remainingMoves--;
	}
	
	/**
	 * Moves the ant forward one position in the direction it is facing.  If
	 * the ant has already expended all available moves, the ant remains
	 * stationary.
	 */
	public void moveForward() {
		if (getRemainingMoves() <= 0) {
			return;
		}
		
		switch (direction) {
		case UP:
			y = (y - 1 + height) % height;
			break;
		case DOWN:
			y = (y + 1) % height;
			break;
		case RIGHT:
			x = (x + 1) % width;
			break;
		case LEFT:
			x = (x - 1 + width) % width;
			break;
		default:
			throw new IllegalStateException();
		}
		
		if (map[x][y].equals(State.FOOD)) {
			map[x][y] = State.EATEN;
			foodEaten++;
			numberOfMoves = maxMoves - remainingMoves;
		}
		
		remainingMoves--;
	}
	
	/**
	 * Returns true if food is located in the position directly ahead
	 * of the ant; false otherwise.
	 */
	public boolean isFoodAhead() {
		switch (direction) {
		case UP:
			return map[x][(y - 1 + height) % height].equals(State.FOOD);
		case DOWN:
			return map[x][(y + 1) % height].equals(State.FOOD);
		case RIGHT:
			return map[(x + 1) % width][y].equals(State.FOOD);
		case LEFT:
			return map[(x - 1 + width) % width][y].equals(State.FOOD);
		default:
			throw new IllegalStateException();
		}
	}
	
	/**
	 * Prints a visual representation of the world
	 */
	public void display() {
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				switch (map[i][j]) {
				case FOOD:
					System.out.print('F');
					break;
				case EMPTY:
					System.out.print(' ');
					break;
				case TRAIL:
					System.out.print('.');
					break;
				case EATEN:
					System.out.print('e');
					break;
				default:
					System.out.print('?');
					break;
				}
			}
			
			System.out.println();
		}
	}

}
