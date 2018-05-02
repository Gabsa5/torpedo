package torpedo.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



import torpedo.game.Board;



public class albiGUI {
	private Control control;
	private final BoardPanel boardPanel;
	private final BoardPanel enemyPanel;
	private final JFrame gameFrame;
	
	private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(1300, 700);
	private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(600, 600);
	private final static Dimension CELL_PANEL_DIMENSION = new Dimension(10, 10);
	
	private Color lightCellColor = Color.decode("#58ACFA");
    private Color darkCellColor = Color.decode("#2E9AFE");
    
    private int CELLNUMBER = 10;
    //TODO ezt utkozben valtoztatni
    
    private Cursor customCursor;
	
	public albiGUI(Control control){
		this.gameFrame = new JFrame("Jchess");
		this.gameFrame.setLayout(new BorderLayout());
		final JMenuBar tableMenuBar = createTableMenuBar();
		this.gameFrame.setJMenuBar(tableMenuBar);
		this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
		this.gameFrame.setResizable(false);
		
		JPanel northPanel = new JPanel(new BorderLayout());      
	    northPanel.add(new JLabel("Ellenség pálya"), BorderLayout.WEST);
	    northPanel.add(new JLabel("Saját pálya"), BorderLayout.EAST);	    
		
		this.boardPanel = new BoardPanel();
		this.enemyPanel = new BoardPanel();
		
		JPanel centerPanel = new JPanel(new BorderLayout());
	    centerPanel.add(this.boardPanel, BorderLayout.WEST);
	    centerPanel.add(this.enemyPanel, BorderLayout.EAST);    
	    
	    JPanel southPanel = new JPanel(new BorderLayout());
	    southPanel.add(new JLabel("Ellenség hajói"), BorderLayout.WEST);
	    southPanel.add(new JLabel("Saját hajók"), BorderLayout.EAST);
		
		this.gameFrame.add(northPanel, BorderLayout.NORTH);		
		this.gameFrame.add(centerPanel, BorderLayout.CENTER);
		this.gameFrame.add(southPanel, BorderLayout.SOUTH);
		this. gameFrame.setVisible(true);
		
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image customimage = toolkit.getImage("x.jpg");
		this.customCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				customimage, new Point(0, 0), "customCursor");
		
	}

	private JMenuBar createTableMenuBar() {
		final JMenuBar tableMenuBar = new JMenuBar();
		tableMenuBar.add(createFileMenu());
		return tableMenuBar;
	}

	private JMenu createFileMenu() {
		final JMenu fileMenu = new JMenu("File");		
		final JMenuItem newGame = new JMenuItem("New Game");		
		
		
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("New Game");	
				// TODO: uj jatek kezdese
			}
		});		
		fileMenu.add(newGame);
		
		final JMenuItem saveGame = new JMenuItem("Save game");
		saveGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Game Saved");
				// TODO: jatekmentes
			}
		});		
		fileMenu.add(saveGame);
		
		final JMenuItem exitGame = new JMenuItem("Exit");
		exitGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				// TODO: kilepesi muveletek (mentes...)
			}
		});		
		fileMenu.add(exitGame);
		
		return fileMenu;
	}
	
	private class BoardPanel extends JPanel{
		final List<CellPanel> cellTiles;
		
		BoardPanel() {
			super(new GridLayout(CELLNUMBER, CELLNUMBER));
			this.cellTiles = new ArrayList<>();
			for (int i = 0; i < CELLNUMBER*CELLNUMBER; i++) {
				final CellPanel tilePanel = new CellPanel(this, i);
				this.cellTiles.add(tilePanel);
				tilePanel.setBorder(BorderFactory.createLineBorder(Color.black));
				add(tilePanel);
			}
			setPreferredSize(BOARD_PANEL_DIMENSION);
			validate();
		}
		
		public void drawBoard(final Board board) {
			removeAll();
			for (final CellPanel cellPanel : cellTiles) {
				cellPanel.drawCell(board);
				add(cellPanel);
			}
			validate();
			repaint();
		}
	}
	
	private class CellPanel extends JPanel {		
		private final int cellId;
		
		public CellPanel(final BoardPanel boardpanel, final int cellId) {
			super(new GridBagLayout());
			this.cellId = cellId;
			setPreferredSize(CELL_PANEL_DIMENSION);
			assignCellColor();
			
			
			addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(final MouseEvent e) {}
				
				@Override
				public void mousePressed(final MouseEvent e) {}
				
				@Override
				public void mouseExited(final MouseEvent e) {}
				
				@Override
				public void mouseEntered(final MouseEvent e) {}
				
				@Override
				public void mouseClicked(final MouseEvent e) {					
					System.out.println(cellId);
					setCursor(customCursor);
					
				}
			});
			validate();
		}
		
		public void drawCell(final Board board) {
			assignCellColor();
			validate();
			repaint();
		}
		

		private void assignCellColor() {
            if ((this.cellId / CELLNUMBER) % 2 != 0) {
                setBackground(this.cellId % 2 == 0 ? lightCellColor : darkCellColor);                
            } else {
                setBackground(this.cellId % 2 != 0 ? lightCellColor : darkCellColor);
            }
        }
	}
	

	
}


