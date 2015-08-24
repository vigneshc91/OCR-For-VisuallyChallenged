package sss;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.KeyStroke;

import net.sf.jcarrierpigeon.WindowPosition;
import net.sf.jtelegraph.Telegraph;
import net.sf.jtelegraph.TelegraphQueue;
import net.sf.jtelegraph.TelegraphType;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.gtranslate.Audio;
import com.gtranslate.Language;
import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;

import javax.swing.JEditorPane;
import javax.swing.JTextPane;

import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;


public class sample {

	private JFrame frmImageToText;
	private JFrame procFrame;
	
	Voice vo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					sample window = new sample();
					window.frmImageToText.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
		
	}

	/**
	 * Create the application.
	 */
	String output;
	public sample() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmImageToText = new JFrame();
		frmImageToText.setIconImage(Toolkit.getDefaultToolkit().getImage(sample.class.getResource("/sss/icon.png")));
		frmImageToText.setTitle("Image To Text");
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int taskht = dim.height - rec.height;
		frmImageToText.setSize(dim.width, dim.height-taskht);
		frmImageToText.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		procFrame = new JFrame();
		procFrame.setUndecorated(true);
		procFrame.setSize(180, 120);
		procFrame.setLocationRelativeTo(null);
		procFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JFileChooser save_file = new JFileChooser();
		FileFilter doc_files = new FileNameExtensionFilter("doc files","doc");
		save_file.addChoosableFileFilter(doc_files);
		
		final JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Calibri", Font.PLAIN, 14));
		textArea.setAutoscrolls(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBorder(new LineBorder(new Color(153, 180, 209), 2));
		JScrollPane scrollPane = new JScrollPane(textArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		SpellChecker.setUserDictionaryProvider(new FileUserDictionary());
		SpellChecker.registerDictionaries(null,"en");
		SpellChecker.register(textArea);
		
		final TextArea textArea_1 = new TextArea();
		textArea_1.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 16));
		//textArea_1.setLineWrap(true);
		//textArea_1.setWrapStyleWord(true);
		//textArea_1.setBorder(new LineBorder(SystemColor.activeCaption, 2));
		JScrollPane scrollPane_1 = new JScrollPane(textArea_1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		/*GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			String font_path = this.getClass().getResource("Braille.ttf").getPath(); 
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File(font_path)).deriveFont(14f);
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(font_path)));
			textArea_1.setFont(font);
			
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		ImageIcon proces = new ImageIcon(this.getClass().getResource("processing.gif"));
		final JLabel lblNewLabel = new JLabel(proces);
		lblNewLabel.setVisible(false);
		
		JLabel procFrame_label = new JLabel(proces);
		procFrame.getContentPane().add(procFrame_label);
		//procFrame.setAlwaysOnTop(true);
		
		BufferedImage bufimg = null;
		try {
			bufimg = ImageIO.read(this.getClass().getResource("File-preview.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final ImageIcon prev_img = new ImageIcon(bufimg);
		final JLabel lblNewLabel_1 = new JLabel(prev_img);
		JScrollPane scrollPane_2 = new JScrollPane(lblNewLabel_1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_2.setBorder(new LineBorder(SystemColor.activeCaption, 2));
		
		final JFileChooser get_file = new JFileChooser();
		
		GroupLayout groupLayout = new GroupLayout(frmImageToText.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 660, GroupLayout.PREFERRED_SIZE)
							.addGap(97))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 660, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 551, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(43)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 636, GroupLayout.PREFERRED_SIZE)
							.addGap(23))))
		);
		frmImageToText.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmImageToText.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFile);
		
		final JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setIcon(new ImageIcon(sample.class.getResource("/sss/import.png")));
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ret = get_file.showOpenDialog(mntmOpen);
				if(ret == get_file.APPROVE_OPTION){
					final File input = get_file.getSelectedFile();
					
					//JOptionPane.showMessageDialog(null, "Image "+input+" selected", "Input", JOptionPane.INFORMATION_MESSAGE);
					Thread th = new Thread(){
					public void run(){
						
						textArea.setText("");
						textArea_1.setText("");
						SpellChecker.unregister(textArea);
						//lblNewLabel.setVisible(true);
						frmImageToText.setEnabled(false);
						procFrame.setVisible(true);
						try {
							BufferedImage bufimg_1 = ImageIO.read(input);
							
							lblNewLabel_1.setIcon(new ImageIcon(bufimg_1));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						final long startTime = System.currentTimeMillis();
						output = conversion(input);
						final long endTime = System.currentTimeMillis();
						textArea.setText(output);
						SpellChecker.register(textArea);
						//textArea_1.setText(output);
						//lblNewLabel.setVisible(false);
						TxtBraille br = new TxtBraille();
						textArea_1.setText(br.getBraille(output));
						frmImageToText.setEnabled(true);
						procFrame.setVisible(false);
						System.out.println("recognized in : "+TimeUnit.MILLISECONDS.toSeconds((endTime - startTime))+" Sec");
						Telegraph tele = new Telegraph("Success", "Sucessfully converted to Braille...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
						
						}
					};
					th.start();
//					try {
//						
//						th.join();
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					
				}
			}
		});
		mntmOpen.setMnemonic(KeyEvent.VK_O);
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnFile.add(mntmOpen);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon(sample.class.getResource("/sss/exit.png")));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
		mntmExit.setMnemonic(KeyEvent.VK_X);
		mnFile.add(mntmExit);
		
		JMenu mnSave = new JMenu("Save");
		mnSave.setMnemonic(KeyEvent.VK_S);
		menuBar.add(mnSave);
		
		JMenuItem mnSaveText = new JMenuItem("Save Text");
		mnSaveText.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnSave.add(mnSaveText);
		mnSaveText.setIcon(new ImageIcon(sample.class.getResource("/sss/save1.png")));
		mnSaveText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String src =  textArea.getText();
				
				if(src.equals("")){
					
					Telegraph tele = new Telegraph("Warning", "The Document Is Empty!", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
				}
				else{
					
				int ret = save_file.showSaveDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION){
					
					File saved_file = save_file.getSelectedFile();
					String file_name = saved_file.toString();
					if(!file_name.toLowerCase().endsWith(".doc")){
						
						saved_file = new File(file_name+".doc");
					}
					try{
						FileWriter fw = new FileWriter(saved_file);
						fw.write(src);  
						fw.close();
						Telegraph tele = new Telegraph("Success", "File saved successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
						
					}
					catch(IOException e){
						//System.err.println(e);
						Telegraph tele = new Telegraph("Error", "Some problem occured", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
					}
				}
			 }
			}
		});
		
		JMenuItem mnSaveBraille = new JMenuItem("Save Braille");
		mnSaveBraille.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mnSave.add(mnSaveBraille);
		mnSaveBraille.setIcon(new ImageIcon(sample.class.getResource("/sss/save_as.png")));
		mnSaveBraille.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String src =  textArea_1.getText();
				
				if(src.equals("")){
					Telegraph tele = new Telegraph("Warning", "The Document Is Empty!", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
					
				}
				else{
					
				int ret = save_file.showSaveDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION){
					
					File saved_file = save_file.getSelectedFile();
					String file_name = saved_file.toString();
					if(!file_name.toLowerCase().endsWith(".doc")){
						saved_file = new File(file_name+".doc");
					}
					try{
						Writer fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saved_file), "UTF-8"));
						fw.write(src);  
						fw.close();
						Telegraph tele = new Telegraph("Success", "File saved successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
					}
					catch(IOException e){
						//System.err.println(e);
						Telegraph tele = new Telegraph("Error", "Some problem occured", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
					}
				}
			 }
			}
		});
		
		JMenu mnAudio = new JMenu("Audio");
		mnAudio.setMnemonic(KeyEvent.VK_A);
		menuBar.add(mnAudio);
		
		JMenuItem mntmPlay = new JMenuItem("Play");
		mntmPlay.setIcon(new ImageIcon(sample.class.getResource("/sss/play icon.png")));
		mntmPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String audio_src = textArea.getText();
				//String audio_text = audio_src.replaceAll("[\\t\\r\\n]", " ");
				//audio_text = audio_text.replaceAll("[#&]", "");
				
					 Thread th = new Thread(){
						public void run(){
						  if(!audio_src.equals("")){
							//frmImageToText.setEnabled(false);
							procFrame.setVisible(true);
							vo = new Voice("kevin16");
							vo.say(audio_src);
							//frmImageToText.setEnabled(true);
							procFrame.setVisible(false);
						  } else{
								Telegraph tele = new Telegraph("Warning", "There is nothing to play audio...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
								TelegraphQueue que = new TelegraphQueue();
								que.add(tele);
							}
						}
					};
					th.start();
					//playAudio(audio_text);
				
			}
		});
		mntmPlay.setMnemonic(KeyEvent.VK_P);
		mnAudio.add(mntmPlay);
		
		JMenuItem mntmStop = new JMenuItem("Stop");
		mntmStop.setIcon(new ImageIcon(sample.class.getResource("/sss/stop icon.png")));
		mntmStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				vo.dispose();
			
			}
		});
		mnAudio.add(mntmStop);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setIcon(new ImageIcon(sample.class.getResource("/sss/save to disk.png")));
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String audio_src = textArea.getText();
				//String audio_text = audio_src.replaceAll("[\\t\\r\\n]", " ");
				//audio_text = audio_text.replaceAll("[#]", "");
				Thread th = new Thread(){
					public void run(){
						if(!audio_src.equals("")){
							int ret = save_file.showSaveDialog(null);
								if(ret == JFileChooser.APPROVE_OPTION){
									frmImageToText.setEnabled(false);
									procFrame.setVisible(true);
									File saved_file = save_file.getSelectedFile();
									String file_name = saved_file.toString();
									if(file_name.toLowerCase().endsWith(".wav")){
										file_name = file_name.substring(0, file_name.length()-4);
									}
									Voice voi = new Voice("kevin16");
									voi.saveAudio(file_name,audio_src);
									frmImageToText.setEnabled(true);
									procFrame.setVisible(false);
									Telegraph tele = new Telegraph("Success", "Wrote synthesized speech to "+file_name+".wav", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);				
									TelegraphQueue que = new TelegraphQueue();
									que.add(tele);
								}
						} else{
							Telegraph tele = new Telegraph("Warning", "There is nothing to save audio...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
							TelegraphQueue que = new TelegraphQueue();
							que.add(tele);
						}
					}
		     };
		     th.start();
			}
		});
		mnAudio.add(mntmSave);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public String conversion(File inputfile){
		Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
		 // Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
		String result = null;
		try {
			//instance.setLanguage("tam");
			
			result = instance.doOCR(inputfile);
			Telegraph tele = new Telegraph("Success", "Recognition Completed Successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);				
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
			
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Telegraph tele = new Telegraph("Error", e.toString(), TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);				
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
		}
		
		return result;
		
	} 
	
  /*  public void playAudio(String text){
		Audio audio = Audio.getInstance();
		InputStream sound = null;
		try {
			 sound = audio.getAudio(text, Language.ENGLISH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Telegraph tele = new Telegraph("Error", "Some problem occured", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);				
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
		}
		try {
			audio.play(sound);
			Telegraph tele = new Telegraph("Success", "Playing audio...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);				
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Telegraph tele = new Telegraph("Error", "Some problem occured", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);				
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
		}
	} */
	
  /*  public void saveAudio(String text, String file_name){
		Audio audio = Audio.getInstance();
		InputStream sound = null;
		
		try {
			 sound = audio.getAudio(text, Language.ENGLISH);
			 Converter con = new Converter();
			 con.convert(sound, file_name, null, null);
			 Telegraph tele = new Telegraph("Success", "Audio saved successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);				
			 TelegraphQueue que = new TelegraphQueue();
			 que.add(tele);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Telegraph tele = new Telegraph("Error", "Some problem occured", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);				
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Telegraph tele = new Telegraph("Error", "Some problem occured", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);				
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
		}
	} */
}
