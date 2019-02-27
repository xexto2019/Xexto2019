package Xexto2019;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.List;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;


public class XextoMain extends JFrame {
	
	private static Liga nireLiga = new Liga();
	private DefaultListModel<String> taldeModeloa;	
	private DefaultListModel<String> jokalariModeloa;	
	private DefaultListModel<String> ErabiltzaileakModeloa;
	private DefaultListModel<String> partidaModeloa;
	private DefaultListModel<String> sailkapenModeloa;
	private JPanel contentPane;
	private JPanel taldeaGehituPanela;
	private JPanel taldeakPanela;
	private JPanel taldeFitxaPanela;
	private JPanel jokalariaGehituPanela;
	private JPanel hasiSaioaPanela;
	private JPanel partidakPanela;
	private JPanel sailkapenaPanela;
	private JPanel sortuErabiltzaileakPanela;
	private JTextField Izenatxt;
	private JTextField Pasahitzatxt;
	private JButton gordeAldaketaErabiltzaile;
	private JButton btnAldatuErabiltzailea;
	private String erabiltzaileMota;
	private String autMaila = "Ikuslea";
	private boolean ligaMartxan = false;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XextoMain frame = new XextoMain();
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
	
	public XextoMain() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(XextoMain.class.getResource("/Irudiak/asdas.PNG")));
		setTitle("Xexto Liga");
		setResizable(false);
		sartuAdminErabiltzailea();
		sortuProbazkoDatuak();
		Taldea t = new Taldea();
		Erabiltzailea e = new Erabiltzailea();
		marraztuOinarria();	
		marraztuMenua();
		marraztuTaldeakPanela();
	}
	
	private static void sortuProbazkoDatuak() {	
		for (int i = 1; i <= 20 ; i++) {	
			Taldea t = new Taldea (i, "Saskibaloi Taldea "+String.format("%02d", i), "herria"+String.format("%02d", i), "email"+String.format("%02d", i), "zelaia"+String.format("%02d", i));
			for (int k = 1; k <= 15 ; k++) {
				int x=i*100+k;
				Jokalaria j = new Jokalaria ("nan_"+x, "izena_"+x, "abizena_"+x);
				t.gehituJokalaria(j);
			}
			nireLiga.gehituTaldea(t);
		}
		//nireLiga.inprimatuTaldeak();
	}
	
	private static void sartuAdminErabiltzailea() {	
			nireLiga.gehituErabiltzailea(new Erabiltzailea("Admin", "Admin", "Administratzailea"));
		}
	private void sortuTaldeModeloa() {
		taldeModeloa.removeAllElements();
		for (Taldea t :  nireLiga.getTaldeak()) {
			taldeModeloa.addElement(t.toString());
		}
	}
	
	private void sortuSailkapenModeloa(Map<String, String> sailkapena) {
		sailkapenModeloa.removeAllElements();
		for (String key : sailkapena.keySet() ) {
			//System.out.println(key + " | " + sailkapena.get(key));
			String katea = key.toString() + " || " + sailkapena.get(key).toString();
			sailkapenModeloa.addElement(katea);
		}
	}
	
	private void sortuJokalariModeloa(Taldea t) {
		jokalariModeloa.removeAllElements();
		for (Jokalaria j :  t.getJokalariak()) {
			//System.out.println(j);
			jokalariModeloa.addElement(j.toString());
		}
	}
	

	private void marraztuOinarria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
	}
	
	private void marraztuMenua() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnAdministrazioa = new JMenu("Administrazioa");
		menuBar.add(mnAdministrazioa);
		
		if(autMaila.equals("Ikuslea")) {
			JMenuItem mntmHasiSaioa = new JMenuItem("Hasi saioa");
			mnAdministrazioa.add(mntmHasiSaioa);
			mntmHasiSaioa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					contentPane.removeAll();
					marraztuHasiSaioaPanela();
				}
			});}
		else {
			JMenuItem mntmAmaituSaioa = new JMenuItem("Amaitu saioa");
			mntmAmaituSaioa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					contentPane.removeAll();
					marraztuHasiSaioaPanela();
					autMaila = "Ikuslea";
				}
			});
			mnAdministrazioa.add(mntmAmaituSaioa);
			
			}
		
		
		
		
		if(autMaila.equals("Administratzailea")) {
			JMenuItem mntmErabiltzaileakSortu = new JMenuItem("Sortu Erabiltzaileak");
			mntmErabiltzaileakSortu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					erabiltzaileakPanela();
					sortuErabiltzaileModeloa();
					revalidate();
					repaint();
				}
			});
			mnAdministrazioa.add(mntmErabiltzaileakSortu);
			
			
				JMenuItem mntmHasiLiga  = new JMenuItem("Hasi Liga");
				mntmHasiLiga.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!ligaMartxan) {
						mntmHasiLiga.setText("Amaitu Liga");
						ligaMartxan = !ligaMartxan;
						System.out.println(ligaMartxan);
						JOptionPane.showMessageDialog (null, "Liga hasi da!!!", "Liga hasi da!!!", JOptionPane.INFORMATION_MESSAGE);
						contentPane.removeAll();
						marraztuTaldeakPanela();
						revalidate();
						repaint();
						
						}
						else if(ligaMartxan == true) {
							mntmHasiLiga.setText("Hasi Liga");
							autMaila = "Administratzailea";
							ligaMartxan = !ligaMartxan;
							contentPane.removeAll();
							marraztuTaldeakPanela();
							revalidate();
							repaint();
						}
						
					}
					});
				mnAdministrazioa.add(mntmHasiLiga);			
	}

		JMenu mnLiga = new JMenu("Liga");
		menuBar.add(mnLiga);
		
		JMenuItem mntmTaldeak = new JMenuItem("Taldeak");
		mnLiga.add(mntmTaldeak);
		mntmTaldeak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				marraztuTaldeakPanela();
				revalidate();
				repaint();
			}
		});
		
		JMenuItem mntmPartiduak = new JMenuItem("Partidak");
		mnLiga.add(mntmPartiduak);
		mntmPartiduak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				marraztuPartidakPanela();
				revalidate();
				repaint();
			}
		});
		
		JMenuItem mntmSailkapena = new JMenuItem("Sailkapena");
		mnLiga.add(mntmSailkapena);
		
		mntmSailkapena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				marraztuSailkapenaPanela();
				revalidate();
				repaint();

			}
		});
		
		/* Hizkuntza aldaketaren ideia geneukan, baina denborarekin gatzakak euki ditugu.
		 * 
		 * for(int i = 0; i < 8; i++) {
			menuBar.add(Box.createHorizontalGlue());
		}
		
		JMenu mnEseu = new JMenu("es/eu");
		menuBar.add(mnEseu);
		
		JMenuItem mntmEspaol = new JMenuItem("Espa\u00F1ol");
		mnEseu.add(mntmEspaol);
		
		JMenuItem mntmEuskara = new JMenuItem("Euskara");
		mnEseu.add(mntmEuskara);*/
			
	}
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        ArrayList<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());
        Collections.reverse(list);
        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
	
	private void marraztuTaldeakPanela() {
		/*TALDEAK*/
		taldeakPanela = new JPanel();
		taldeakPanela.setBackground(Color.DARK_GRAY);
		taldeakPanela.setBounds(0, 0, 1008, 708);
		contentPane.add(taldeakPanela);
		taldeakPanela.setLayout(null);
		
		JLabel lblTaldeakIzenburua = new JLabel("Xexto Ligaren Taldeak");
		lblTaldeakIzenburua.setForeground(Color.WHITE);
		lblTaldeakIzenburua.setBounds(10, 0, 539, 47);
		lblTaldeakIzenburua.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 40));
		taldeakPanela.add(lblTaldeakIzenburua);
		
		JList<String> taldeakLista = new JList<String>();
		taldeakLista.setForeground(Color.WHITE);
		taldeakLista.setFont(new Font("Monospaced", Font.BOLD, 13));
		taldeakLista.setBackground(new Color(128, 128, 128));
		taldeakLista.setBounds(10, 100, 666, 597);
		taldeModeloa = new DefaultListModel<String>();
		taldeakLista.setModel(taldeModeloa);
		sortuTaldeModeloa();
		taldeakLista.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent evt) {   	  
	          int pos = taldeakLista.getSelectedIndex();
	          Taldea t = XextoMain.nireLiga.getTaldeak().get(pos);
	          taldeakPanela.setVisible(false);
	          marraztuTaldeFitxa(t);
	          //taldeFitxaPanela.setVisible(true);
	        }
	      });
		taldeakPanela.add(taldeakLista);
		
		JLabel irudiTalde = new JLabel("");
		irudiTalde.setIcon(new ImageIcon(XextoMain.class.getResource("/Irudiak/vector.png")));
		irudiTalde.setBounds(711, 424, 287, 192);
		taldeakPanela.add(irudiTalde);
		
		JLabel saskiTalde = new JLabel("");
		saskiTalde.setIcon(new ImageIcon(XextoMain.class.getResource("/Irudiak/taldeak.png")));
		saskiTalde.setBounds(711, 100, 287, 245);
		taldeakPanela.add(saskiTalde);
		if (autMaila.equals("Administratzailea") && ligaMartxan == false) {
			JButton btnGehituTaldea = new JButton("Gehitu Taldea");
			btnGehituTaldea.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					taldeakPanela.setVisible(false);
					marraztuTaldeaGehituPanela();
					taldeaGehituPanela.setVisible(true);
				}
			});
			btnGehituTaldea.setBounds(10, 66, 160, 23);
			btnGehituTaldea.setFont(new Font("Monospaced", Font.BOLD, 15));
			taldeakPanela.add(btnGehituTaldea);
		}
	}
	
	private void marraztuTaldeaGehituPanela() {
		/*TALDEA GEHITU*/
		taldeaGehituPanela = new JPanel();
		taldeaGehituPanela.setBounds(0, 0, 1008, 708);
		taldeaGehituPanela.setBackground(Color.DARK_GRAY);
		contentPane.add(taldeaGehituPanela);
		taldeaGehituPanela.setLayout(null);
		
		JLabel lblTaldeaEditatu = new JLabel("Sortu Taldea");
		lblTaldeaEditatu.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTaldeaEditatu.setForeground(Color.WHITE);
		lblTaldeaEditatu.setBounds(10, 11, 764, 72);
		taldeaGehituPanela.add(lblTaldeaEditatu);
		
		JLabel lblKodea = new JLabel("Kodea");
		lblKodea.setBounds(45, 120, 45, 15);
		lblKodea.setForeground(Color.WHITE);
		taldeaGehituPanela.add(lblKodea);
		
		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setBounds(45, 145, 45, 15);
		lblIzena.setForeground(Color.WHITE);
		taldeaGehituPanela.add(lblIzena);
		
		JLabel lblHerria = new JLabel("Herria");
		lblHerria.setBounds(45, 170, 45, 15);
		lblHerria.setForeground(Color.WHITE);
		taldeaGehituPanela.add(lblHerria);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(45, 195, 45, 15);
		lblEmail.setForeground(Color.WHITE);
		taldeaGehituPanela.add(lblEmail);
		
		JLabel lblZelaia = new JLabel("Zelaia");
		lblZelaia.setBounds(45, 220, 45, 15);
		lblZelaia.setForeground(Color.WHITE);
		taldeaGehituPanela.add(lblZelaia);
		
		JTextField txtKodea = new JTextField();
		txtKodea.setBounds(140, 120, 150, 20);
		taldeaGehituPanela.add(txtKodea);
		txtKodea.setColumns(10);
		
		JTextField txtIzena = new JTextField();
		txtIzena.setColumns(10);
		txtIzena.setBounds(140, 143, 150, 20);
		taldeaGehituPanela.add(txtIzena);
		
		JTextField txtHerria = new JTextField();
		txtHerria.setColumns(10);
		txtHerria.setBounds(140, 169, 150, 20);
		taldeaGehituPanela.add(txtHerria);
		
		JTextField txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(140, 194, 150, 20);
		taldeaGehituPanela.add(txtEmail);
		
		JTextField txtZelaia = new JTextField();
		txtZelaia.setColumns(10);
		txtZelaia.setBounds(140, 219, 150, 20);
		taldeaGehituPanela.add(txtZelaia);
		
		Action gordeTaldea = new AbstractAction() {
			 public void actionPerformed(ActionEvent e) {
				int kodea = Integer.parseInt(txtKodea.getText());
				String izena = txtIzena.getText();
				String herria = txtHerria.getText();
				String email = txtEmail.getText();
				String zelaia = txtZelaia.getText();
				Taldea t = new Taldea(kodea, izena, herria, email, zelaia);
				XextoMain.nireLiga.gehituTaldea(t);
				sortuTaldeModeloa();
				taldeaGehituPanela.setVisible(false);
				taldeakPanela.setVisible(true);
			 }
		};
		
		JButton btnGordeTaldea = new JButton("Gorde Taldea");
		btnGordeTaldea.setBounds(137, 263, 150, 23);
		btnGordeTaldea.setFont(new Font("Monospaced", Font.BOLD, 15));
		taldeaGehituPanela.add(btnGordeTaldea);
		if (autMaila.equals("Administratzailea") && ligaMartxan == false) {
			btnGordeTaldea.addActionListener(gordeTaldea);
		}
		txtZelaia.addActionListener(gordeTaldea);
		txtEmail.addActionListener(gordeTaldea);
		txtHerria.addActionListener(gordeTaldea);
		txtIzena.addActionListener(gordeTaldea);
		txtKodea.addActionListener(gordeTaldea);
	}
	
	private void marraztuTaldeFitxa(Taldea t) {
		/*--Hemen Taldearen atributuak aldatzeko eskuragarri eukiko duzu, eta taldearen jokalariak ikusi eta aldatu ere.--*/
		JPanel taldeFitxaPanela = new JPanel();
		taldeFitxaPanela.setBackground(Color.DARK_GRAY);
		contentPane.add(taldeFitxaPanela);
		taldeFitxaPanela.setBounds(0, 0, 1008, 708);
		taldeFitxaPanela.setLayout(null);
		
		JLabel jokalariakIrudia = new JLabel("");
		jokalariakIrudia.setIcon(new ImageIcon(XextoMain.class.getResource("/Irudiak/jokalariak.png")));
		jokalariakIrudia.setBounds(450, 30, 564, 120);
		taldeFitxaPanela.add(jokalariakIrudia);
		
		JLabel jokalariakIrudia2 = new JLabel ("");
		jokalariakIrudia2.setIcon(new ImageIcon(XextoMain.class.getResource("/Irudiak/saskibaloi.png")));
		jokalariakIrudia2.setBounds(690, 30, 287, 304);
		taldeFitxaPanela.add(jokalariakIrudia2);
		
		JLabel lblTaldeFitxa = new JLabel("Taldearen fitxa");
		lblTaldeFitxa.setFont(new Font("Monospaced", Font.PLAIN, 40));
		lblTaldeFitxa.setForeground(Color.WHITE);
		lblTaldeFitxa.setBounds(10, 5, 764, 72);
		taldeFitxaPanela.add(lblTaldeFitxa);
		
		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setForeground(Color.WHITE);
		lblIzena.setBounds(46, 146, 46, 14);
		taldeFitxaPanela.add(lblIzena);
		
		JLabel lblHerria = new JLabel("Herria");
		lblHerria.setForeground(Color.WHITE);
		lblHerria.setBounds(46, 172, 46, 14);
		taldeFitxaPanela.add(lblHerria);
		
		JLabel lblKodea = new JLabel("Kodea");
		lblKodea.setForeground(Color.WHITE);
		lblKodea.setBounds(46, 122, 46, 14);
		taldeFitxaPanela.add(lblKodea);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(46, 197, 46, 14);
		taldeFitxaPanela.add(lblEmail);
		
		JLabel lblZelaia = new JLabel("Zelaia");
		lblZelaia.setForeground(Color.WHITE);
		lblZelaia.setBounds(46, 222, 46, 14);
		taldeFitxaPanela.add(lblZelaia);

		JTextField taldeKodea = new JTextField();
		taldeKodea.setBounds(140, 119, 130, 20);
		taldeFitxaPanela.add(taldeKodea);
		taldeKodea.setText(String.valueOf(t.getKodea()));
		taldeKodea.setEditable(false);

		JTextField taldeIzena = new JTextField();
		taldeIzena.setBounds(140, 143, 130, 20);
		taldeFitxaPanela.add(taldeIzena);
		taldeIzena.setText(t.getIzena());
		taldeIzena.setEditable(false);
		
		JTextField taldeHerria = new JTextField();
		taldeHerria.setBounds(140, 169, 130, 20);
		taldeFitxaPanela.add(taldeHerria);
		taldeHerria.setText(t.getHerria());
		taldeHerria.setEditable(false);
		
		JTextField taldeEmail = new JTextField();
		taldeEmail.setBounds(140, 194, 130, 20);
		taldeFitxaPanela.add(taldeEmail);
		taldeEmail.setText(t.getEmail());
		taldeEmail.setEditable(false);
		
		JTextField taldeZelaia = new JTextField();
		taldeZelaia.setBounds(140, 219, 130, 20);
		taldeFitxaPanela.add(taldeZelaia);
		taldeZelaia.setText(t.getZelaia());
		taldeZelaia.setEditable(false);

		JList<String> jokalariLista = new JList<String>();
		jokalariLista.setForeground(Color.WHITE);
		jokalariLista.setFont(new Font("Monospaced", Font.BOLD, 13));
		jokalariLista.setBackground(new Color(128, 128, 128));
		jokalariLista.setBounds(10, 285, 666, 597);
		jokalariModeloa = new DefaultListModel<String>();
		jokalariLista.setModel(jokalariModeloa);
		sortuJokalariModeloa(t);
		taldeFitxaPanela.add(jokalariLista);
		
		/*----Jokalarien listan klikatzean, jokalari horren aldaketa panela aterako da----*/
		jokalariLista.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		          int pos = jokalariLista.getSelectedIndex();
		          Jokalaria j = t.getJokalariak().get(pos);	          
		          contentPane.removeAll();
		          marraztuJokalarienAldaketa(j, t);
		          revalidate();
		          repaint();
		        }
		      });
		
		/*---Gehitzeko jokalari bat balio du---*/
		if (autMaila.equals("Administratzailea") && ligaMartxan == false) {
			JButton btnGehituJokalaria = new JButton("Gehitu Jokalaria");
			btnGehituJokalaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				marraztuJokalariaGehituPanela(t);
				jokalariaGehituPanela.setVisible(true);
				revalidate();
				repaint();
			}
			});
		btnGehituJokalaria.setBounds(10, 70, 130, 23);
		taldeFitxaPanela.add(btnGehituJokalaria);
		}		
		
		/*---Honek, taldearen atributuak aldatzeko aukera emango dizu---*/
		if (autMaila.equals("Administratzailea") && ligaMartxan == false) {
			JButton btnAldatuTaldea = new JButton("Aldatu Taldea");
			btnAldatuTaldea.setBounds(150, 70, 130, 23);
			taldeFitxaPanela.add(btnAldatuTaldea);
			btnAldatuTaldea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("x");
				taldeIzena.setEditable(true);
				taldeHerria.setEditable(true);
				taldeEmail.setEditable(true);
				taldeZelaia.setEditable(true);
				
				//contentPane.removeAll();
				//marraztuJokalariaGehituPanela(t);
				//jokalariaGehituPanela.setVisible(true);
				//revalidate();
				//repaint();
			}
			});
	
		
		JButton btnEzabatuTaldea = new JButton("Ezabatu Taldea");
		btnEzabatuTaldea.setBounds(290, 70, 130, 23);
		taldeFitxaPanela.add(btnEzabatuTaldea);
		btnEzabatuTaldea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nireLiga.kenduTaldea(t);
				contentPane.removeAll();
				marraztuTaldeakPanela();
				revalidate();
				repaint();
			}
		});
	
		Action actGordeTaldea = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
			t.setIzena(taldeIzena.getText());
			t.setHerria(taldeHerria.getText());
			t.setEmail(taldeEmail.getText());
			t.setZelaia(taldeZelaia.getText());
			//sortuTaldeModeloa();
			contentPane.removeAll();
			marraztuTaldeakPanela();
			revalidate();
			repaint();
			}
		};
		
		/*---Taldearen atributuak aldatu eta gero, botoi honek gordkeo ditu balio hoiek---*/
		JButton btnGorde = new JButton("Gorde");
		btnGorde.addActionListener(actGordeTaldea);
		btnGorde.setBounds(140, 251, 130, 23);
		taldeFitxaPanela.add(btnGorde);
		
		/*--- textua guztiei enter botoia aplikatzen dugu---*/
		taldeKodea.addActionListener(actGordeTaldea);
		taldeIzena.addActionListener(actGordeTaldea);
		taldeHerria.addActionListener(actGordeTaldea);
		taldeEmail.addActionListener(actGordeTaldea);
		taldeZelaia.addActionListener(actGordeTaldea);		
		}
	}
	
	public void marraztuJokalariaGehituPanela(Taldea t) {
		/*JOKALARIA GEHITU*/
		
		jokalariaGehituPanela = new JPanel();
		contentPane.add(jokalariaGehituPanela);
		jokalariaGehituPanela.setBackground(Color.DARK_GRAY);
		jokalariaGehituPanela.setBounds(0, 0, 1008, 708);
		jokalariaGehituPanela.setLayout(null);
		
		JLabel lblJokalariakEditatu = new JLabel("Sortu Jokalaria");
		lblJokalariakEditatu.setBounds(83, 34, 473, 79);
		lblJokalariakEditatu.setForeground(Color.WHITE);
		lblJokalariakEditatu.setFont(new Font("Monospaced", Font.BOLD, 40));
		jokalariaGehituPanela.add(lblJokalariakEditatu);
		
		JLabel lblNan = new JLabel("NAN");
		lblNan.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblNan.setForeground(Color.WHITE);
		lblNan.setBounds(84, 126, 116, 27);
		jokalariaGehituPanela.add(lblNan);
		
		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblIzena.setBounds(84, 164, 116, 27);
		lblIzena.setForeground(Color.WHITE);
		jokalariaGehituPanela.add(lblIzena);
		
		JLabel lblAbizena = new JLabel("Abizena");
		lblAbizena.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblAbizena.setBounds(84, 202, 116, 27);
		lblAbizena.setForeground(Color.WHITE);
		jokalariaGehituPanela.add(lblAbizena);
		
		JTextField txtNAN = new JTextField();
		txtNAN.setBounds(210, 133, 122, 20);
		txtNAN.setColumns(10);
		jokalariaGehituPanela.add(txtNAN);
		
		JTextField txtIzena = new JTextField();
		txtIzena.setBounds(210, 171, 122, 20);
		jokalariaGehituPanela.add(txtIzena);
		txtIzena.setColumns(10);
		
		JTextField txtAbizena = new JTextField();
		txtAbizena.setBounds(210, 209, 122, 20);
		txtAbizena.setColumns(10);
		jokalariaGehituPanela.add(txtAbizena);
		
		Action gordeJokalaria = new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				String izena = txtIzena.getText();
				String abizena = txtAbizena.getText();
				String nan = txtNAN.getText();
				Jokalaria j = new Jokalaria(nan, izena, abizena);
				t.gehituJokalaria(j);
				sortuJokalariModeloa(t);
				contentPane.removeAll();
				marraztuTaldeFitxa(t);
				revalidate();
				repaint();
				//System.out.print(j);
			}
		};
		
		/*---Botoi honek gordetzen du jokalariaren objektua, jokalari ArrayListan---*/
		if (autMaila.equals("Administratzailea") && ligaMartxan == false) {
			JButton btnGorde = new JButton("Gorde");
			btnGorde.setFont(new Font("Monospaced", Font.BOLD, 13));
			btnGorde.setBounds(210, 255, 122, 20);
			btnGorde.addActionListener(gordeJokalaria);
			jokalariaGehituPanela.add(btnGorde);
		}
		
		/*---enter botoia sakatzeko ematen dituegu hemen---*/
		
		txtNAN.addActionListener(gordeJokalaria);
		txtIzena.addActionListener(gordeJokalaria);
		txtAbizena.addActionListener(gordeJokalaria);
		
		JLabel editatuIrudia = new JLabel("");
		editatuIrudia.setIcon(new ImageIcon(XextoMain.class.getResource("/Irudiak/abstract.png")));
		editatuIrudia.setBounds(427, 242, 561, 458);
		jokalariaGehituPanela.add(editatuIrudia);
	}
	
	private void marraztuPartidakPanela() {
		/*PARTIDAK*/
		if (nireLiga.getJardunaldiak().size() == 0) {
			nireLiga.sortuJardunaldiak();
		} 
		
		partidakPanela = new JPanel();
		partidakPanela.setBackground(Color.DARK_GRAY);
		partidakPanela.setBounds(0, 0, 1008, 708);
		contentPane.add(partidakPanela);
		partidakPanela.setLayout(null);
		
		JLabel lblTaldeakIzenburua = new JLabel("Partidak");
		lblTaldeakIzenburua.setForeground(Color.WHITE);
		lblTaldeakIzenburua.setBounds(5, 5, 200, 49);
		lblTaldeakIzenburua.setFont(new Font("Monospaced", Font.BOLD, 40));
		partidakPanela.add(lblTaldeakIzenburua);
		
		JLabel partidaIcon = new JLabel("");
		partidaIcon.setIcon(new ImageIcon(XextoMain.class.getResource("/Irudiak/pitch.png")));
		partidaIcon.setBounds(350, 0, 150, 104);
		partidakPanela.add(partidaIcon);
		
		JLabel pasadaIcon = new JLabel("");
		pasadaIcon.setIcon(new ImageIcon(XextoMain.class.getResource("/Irudiak/pasada.png")));
		pasadaIcon.setBounds(520, 90, 500, 550);
		partidakPanela.add(pasadaIcon);
		
		//jardunaldien izenak
		String[] jardunaldiKateak = new String[nireLiga.getJardunaldiak().size()];
		int i =0;
		for (Jardunaldia j : nireLiga.getJardunaldiak()) {
			jardunaldiKateak[i] =  j.getIzena();
			i++;
		}
		
		//Konboa
		JComboBox<String> jardunaldiCombo = new JComboBox<String>(jardunaldiKateak);
		jardunaldiCombo.setBounds(10, 70, 130, 20);
		partidakPanela.add(jardunaldiCombo);
		
			jardunaldiCombo.addActionListener(new ActionListener() {
				@Override
			    public void actionPerformed(ActionEvent event) {
			        String hautua = (String) jardunaldiCombo.getSelectedItem();
			        for (Jardunaldia j : nireLiga.getJardunaldiak()) {
			        	if (j.getIzena().equals(hautua)) {
			        		sortuPartidaModeloa(j);
			        	}
			        }
			    }
	
			});

		JList<String> partidakLista = new JList<String>();
		partidakLista.setFont(new Font("Monospaced", Font.BOLD, 13));
		partidakLista.setBackground(new Color(128, 128, 128));
		partidakLista.setForeground(Color.WHITE);
		partidakLista.setBounds(10, 110, 466, 397);
			
		partidaModeloa = new DefaultListModel<String>();
		partidakLista.setModel(partidaModeloa);
		partidakPanela.add(partidakLista);
		
		sortuPartidaModeloa(nireLiga.getJardunaldiak().get(0));
		
		//TODO emaitzak sartu
		
		if ((autMaila.equals("Administratzailea") || autMaila.equals("Erabiltzailea")) && ligaMartxan == true) {
			partidakLista.addListSelectionListener(new ListSelectionListener() {
			      public void valueChanged(ListSelectionEvent evt) {
			    	  //System.out.println(evt.getFirstIndex() + " | " + evt.getLastIndex() + " | " + evt.getValueIsAdjusting());
			    	  if ( !evt.getValueIsAdjusting()) {
			    		  int pos = partidakLista.getSelectedIndex();
			    		  if (pos>=0) {
				    		  //System.out.println("POS: " + pos);
					          String nirejardunaldia = (String) jardunaldiCombo.getSelectedItem();
					          Jardunaldia jardunaldiHau = new Jardunaldia();
						        for (Jardunaldia j : nireLiga.getJardunaldiak()) {
						        	if (j.getIzena().equals(nirejardunaldia)) {
						        		jardunaldiHau =j;
						        		break;
						        	}
						        }
						        //DIALOGOA
						        Partida p = jardunaldiHau.getPartidak().get(pos);
						        emaitzaDialogoa d = new emaitzaDialogoa(nireLiga, p);
						        d.setVisible(false);	
						        sortuPartidaModeloa(nireLiga.getJardunaldiak().get(pos));
					        }
			    	  }
			      }
			});
		}
		
		
		JButton btnGehituTaldea = new JButton("Gehitu Taldea");
		btnGehituTaldea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				partidakPanela.setVisible(false);
				marraztuTaldeaGehituPanela();
				//taldeaGehituPanela.setVisible(true);
			}
		});
		btnGehituTaldea.setBounds(10, 66, 130, 23);
		taldeakPanela.add(btnGehituTaldea);
	}
	
	private void marraztuSailkapenaPanela() {	
		Map<String, String> sailkapena = new HashMap<String, String>();
		
		if (nireLiga.getJardunaldiak().size()==0) {
			nireLiga.sortuJardunaldiak();
		}
		
		for (Jardunaldia j : nireLiga.getJardunaldiak()) {
			for (Partida p : j.getPartidak()) {
				int p1 = p.getPuntuak()[0];
				int p2 = p.getPuntuak()[1];
				int s1=0;
				int s2=0;
				if (sailkapena.containsKey(p.getTalde1().getIzena())){
					s1 = p1 + Integer.parseInt(sailkapena.get(p.getTalde1().getIzena())) ;
				}
				else {
					s1 = p1 ;
				}
				if (sailkapena.containsKey(p.getTalde2().getIzena())){
					s2 = p2 + Integer.parseInt(sailkapena.get(p.getTalde2().getIzena())) ;
				}
				else {
					s2 = p2 ;
				}
				sailkapena.put(p.getTalde1().getIzena(), String.valueOf((s1)));
				sailkapena.put(p.getTalde2().getIzena(), String.valueOf((s2)));
			}
		}
		
		sailkapena = sortByValue(sailkapena);
		
		sailkapenaPanela = new JPanel();
		sailkapenaPanela.setBackground(Color.DARK_GRAY);
		sailkapenaPanela.setBounds(0, 0, 1008, 708);
		contentPane.add(sailkapenaPanela);
		sailkapenaPanela.setLayout(null);
		
		JLabel lblSailkapenaIzenburua = new JLabel("Sailkapena");
		lblSailkapenaIzenburua.setForeground(Color.WHITE);
		lblSailkapenaIzenburua.setBounds(10, 5, 300, 49);
		lblSailkapenaIzenburua.setFont(new Font("Monospaced", Font.BOLD, 40));
		sailkapenaPanela.add(lblSailkapenaIzenburua);
		
		JList<String> sailkapenaLista = new JList<String>();
		sailkapenaLista.setFont(new Font("Monospaced", Font.BOLD, 15));
		sailkapenaLista.setBackground(new Color(128, 128, 128));
		sailkapenaLista.setForeground(Color.WHITE);
		sailkapenaLista.setBounds(10, 100, 400, 575);
		sailkapenModeloa = new DefaultListModel<String>();
		sailkapenaLista.setModel(sailkapenModeloa);
		sortuSailkapenModeloa(sailkapena);
			
		sailkapenaPanela.add(sailkapenaLista);
		
		JLabel sailkapenaLogoa = new JLabel("");
		sailkapenaLogoa.setIcon(new ImageIcon(XextoMain.class.getResource("/Irudiak/asdas.PNG")));
		sailkapenaLogoa.setBounds(600, 25, 500, 200);
		sailkapenaPanela.add(sailkapenaLogoa);		
		
		JLabel sailkapenaIrudia = new JLabel("");
		sailkapenaIrudia.setIcon(new ImageIcon(XextoMain.class.getResource("/Irudiak/Xexto.png")));
		sailkapenaIrudia.setBounds(440, 200, 500, 550);
		sailkapenaPanela.add(sailkapenaIrudia);
		
		
	}

	private void marraztuHasiSaioaPanela(){
		hasiSaioaPanela = new JPanel();
		contentPane.add(hasiSaioaPanela);
		hasiSaioaPanela.setBackground(Color.DARK_GRAY);
		hasiSaioaPanela.setBounds(0, 0, 1008, 708);	
		hasiSaioaPanela.setLayout(null);		
		
		//Erabiltzailearen Labela
		JLabel lblErabiltzailea = new JLabel("Erabiltzailea:");
		lblErabiltzailea.setFont(new Font("Monospaced", Font.BOLD, 40));
		lblErabiltzailea.setForeground(Color.WHITE);
		lblErabiltzailea.setBounds(96, 272, 336, 66);
		hasiSaioaPanela.add(lblErabiltzailea);
		
		//erabiltzailearen Textua
		JTextField erabiltzailea = new JTextField();
		erabiltzailea.setBounds(442, 296, 328, 40);
		hasiSaioaPanela.add(erabiltzailea);
		erabiltzailea.setColumns(10);
		
		//Pasahitzaren labela
		JLabel lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setFont(new Font("Monospaced", Font.BOLD, 40));
		lblPasahitza.setForeground(Color.WHITE);
		lblPasahitza.setBounds(96, 395, 240, 66);
		hasiSaioaPanela.add(lblPasahitza);
				
		//Pasahitzaren Textua
		JPasswordField pasahitza = new JPasswordField();
		pasahitza.setBounds(442, 415, 328, 40);
		hasiSaioaPanela.add(pasahitza);
		
		/*---Action honek balioztatzen du datuak, saioa hasteko, eta honela enter botoia sakatuz, saio hasiko zenuke---*/
		Action enterBotoia = new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	boolean Bilatu = false;
				
				for(int i = 0; nireLiga.getErabiltzaileak().size()>i && Bilatu != true; i++) {
					if (erabiltzailea.getText().equals(nireLiga.getErabiltzaileak().get(i).getIzena())) {
						if(pasahitza.getText().equals(nireLiga.getErabiltzaileak().get(i).getPasahitza())){
							Bilatu = true;
							if(nireLiga.getErabiltzaileak().get(i).getErabiltzaileMota().equals("Administratzailea")){
								autMaila = "Administratzailea";
								contentPane.removeAll();
								marraztuTaldeakPanela();
								marraztuMenua();
								contentPane.revalidate();
								contentPane.repaint();
							}
							else{
								//EmaitzakJarri.setVisible(true);
								autMaila = "Erabiltzailea";
								contentPane.removeAll();
								marraztuTaldeakPanela();
								marraztuMenua();
								contentPane.revalidate();
								contentPane.repaint();
							}
						}
						else {
							JOptionPane.showMessageDialog (null, "Saiatu Berriro", "Ez da egokia", JOptionPane.ERROR_MESSAGE);
						}
					}
					/*
					else {
						JOptionPane.showMessageDialog (null, "Saiatu Berriro", "Ez da egokia", JOptionPane.ERROR_MESSAGE);
					}
					*/
				}
			}
		};
		pasahitza.addActionListener(enterBotoia);
		erabiltzailea.addActionListener(enterBotoia);
		
		//Botoi honek balioztatzen du ia datu egokiak diren eta saioa hasiko zenuke.
		JButton btnBidali = new JButton("Bidali");
		btnBidali.addActionListener(enterBotoia);
		btnBidali.setForeground(Color.DARK_GRAY);
		btnBidali.setBackground(Color.ORANGE);
		btnBidali.setFont(new Font("Monospaced", Font.BOLD, 30));
		btnBidali.setBounds(442, 562, 162, 40);
		hasiSaioaPanela.add(btnBidali);
		
		//Label honek ongi etorria ematen dizu
		JLabel eslogana = new JLabel("Ongi Etorri Xexto Aplikaziora.\r\n");
		eslogana.setForeground(Color.WHITE);
		eslogana.setFont(new Font("Monospaced", Font.BOLD, 30));
		eslogana.setBounds(96, 47, 549, 66);
		hasiSaioaPanela.add(eslogana);
		
		//Label honek ongi etorria ematen dizu.
		JLabel eslogana2 = new JLabel("Identifikatu Zaitez!");
		eslogana2.setForeground(Color.WHITE);
		eslogana2.setFont(new Font("Monospaced", Font.BOLD, 30));
		eslogana2.setBounds(96, 103, 378, 66);
		hasiSaioaPanela.add(eslogana2);
		
		//Label honek gure logoaren irudia ateratzen du.
		JLabel logoa = new JLabel("");
		logoa.setIcon(new ImageIcon(XextoMain.class.getResource("/Irudiak/asdas.PNG")));
		logoa.setBounds(764, 11, 186, 219);
		hasiSaioaPanela.add(logoa);
		
		//Botoi honek taldeak panelera eramango dizu.
		JButton atzerabotoia = new JButton("");
		atzerabotoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				marraztuTaldeakPanela();
				taldeakPanela.setVisible(true);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		atzerabotoia.setIcon(new ImageIcon(XextoMain.class.getResource("/Irudiak/flecha4.gif")));
		atzerabotoia.setBackground(Color.DARK_GRAY);
		atzerabotoia.setBounds(96, 537, 129, 73);
		hasiSaioaPanela.add(atzerabotoia);
	}
	
	private void sortuErabiltzaileModeloa() {
		ErabiltzaileakModeloa.removeAllElements();
		for (Erabiltzailea e : nireLiga.getErabiltzaileak()) {
			ErabiltzaileakModeloa.addElement(e.toString());
		}
	}	
	
	private void sortuPartidaModeloa(Jardunaldia j) {
		partidaModeloa.removeAllElements();
			for (Partida p : j.getPartidak()) {
				partidaModeloa.addElement(p.toString());
		}
	}	

	private void erabiltzaileakPanela() {		
		sortuErabiltzaileakPanela = new JPanel();
		sortuErabiltzaileakPanela.setBackground(Color.DARK_GRAY);
		sortuErabiltzaileakPanela.setBounds(0, 0, 1008, 708);
		contentPane.add(sortuErabiltzaileakPanela);
		sortuErabiltzaileakPanela.setLayout(null);
		
		JButton btnSortuErabiltzailea = new JButton("Sortu Erabiltzailea");
		btnSortuErabiltzailea.setFont(new Font("Monospaced", Font.BOLD, 12));
		btnSortuErabiltzailea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				erabiltzaileakPanela();
				SortuErabiltzailea();
				sortuErabiltzaileModeloa();
				revalidate();
				repaint();
			}
		});
		btnSortuErabiltzailea.setBounds(31, 11, 189, 23);
		sortuErabiltzaileakPanela.add(btnSortuErabiltzailea);
		
		JList<String> erabiltzaileakLista = new JList<String>();
		erabiltzaileakLista.setFont(new Font("Monospaced", Font.BOLD, 15));
		erabiltzaileakLista.setBounds(31, 65, 364, 423);
		ErabiltzaileakModeloa = new DefaultListModel<String>();
		erabiltzaileakLista.setModel(ErabiltzaileakModeloa);
		erabiltzaileakLista.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		    	  contentPane.removeAll();
		          int pos = erabiltzaileakLista.getSelectedIndex();
		          Erabiltzailea e = nireLiga.getErabiltzaileak().get(pos);
		          erabiltzaileakPanela();
		          sortuErabiltzaileModeloa();
		    	  editatuErabiltzailea(e);
		    	  revalidate();
		    	  repaint();
		        }
		      });
		sortuErabiltzaileakPanela.add(erabiltzaileakLista);		
	}
	
	private void SortuErabiltzailea() {
		
		JLabel lblErabiltzailea = new JLabel("Erabiltzailea:");
		lblErabiltzailea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblErabiltzailea.setBounds(428, 66, 136, 23);
		lblErabiltzailea.setForeground(Color.WHITE);
		sortuErabiltzaileakPanela.add(lblErabiltzailea);
		
		JLabel lblIzena = new JLabel("Izena:");
		lblIzena.setFont(new Font("Monospaced", Font.BOLD, 18));
		lblIzena.setBounds(554, 168, 86, 20);
		lblIzena.setForeground(Color.WHITE);
		sortuErabiltzaileakPanela.add(lblIzena);
		
		Izenatxt = new JTextField();
		Izenatxt.setBounds(683, 170, 189, 23);
		sortuErabiltzaileakPanela.add(Izenatxt);
		Izenatxt.setColumns(10);
		
		JLabel lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setFont(new Font("Monospaced", Font.BOLD, 18));
		lblPasahitza.setBounds(556, 212, 117, 23);
		lblPasahitza.setForeground(Color.WHITE);
		sortuErabiltzaileakPanela.add(lblPasahitza);
		
		Pasahitzatxt = new JTextField();
		Pasahitzatxt.setBounds(683, 215, 189, 23);
		sortuErabiltzaileakPanela.add(Pasahitzatxt);
		Pasahitzatxt.setColumns(10);
		
		JLabel lblMota = new JLabel("Mota:");
		lblMota.setFont(new Font("Monospaced", Font.BOLD, 18));
		lblMota.setBounds(554, 113, 86, 23);
		lblMota.setForeground(Color.WHITE);
		sortuErabiltzaileakPanela.add(lblMota);
		
		String [] messageStrings = {"Erabiltzailea" , "Administratzailea"};
		JComboBox comboBox = new JComboBox(messageStrings);
		comboBox.setBounds(683, 117, 189, 23);
		sortuErabiltzaileakPanela.add(comboBox);
		
		Action gordeErabiltzaileak = new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				String izena = Izenatxt.getText();
				String pasahitza = Pasahitzatxt.getText();
				if(comboBox.getSelectedItem().equals("Erabiltzailea")) {
					erabiltzaileMota = "Erabiltzailea";
				}
				else if (comboBox.getSelectedItem().equals("Administratzailea")) {
					erabiltzaileMota = "Administratzailea";
				}
				System.out.println(erabiltzaileMota);
				Erabiltzailea e = new Erabiltzailea(izena, pasahitza, erabiltzaileMota);
				erabiltzaileakPanela();
				nireLiga.gehituErabiltzailea(e);
				sortuErabiltzaileModeloa();
				revalidate();
				repaint();
			}
		};
				
		JButton btnGordeErabiltzailea = new JButton("Gorde");
		btnGordeErabiltzailea.setBounds(683, 251, 189, 23);
		sortuErabiltzaileakPanela.add(btnGordeErabiltzailea);		
		
		btnGordeErabiltzailea.addActionListener(gordeErabiltzaileak);
		Pasahitzatxt.addActionListener(gordeErabiltzaileak);
		Izenatxt.addActionListener(gordeErabiltzaileak);
		
			
	}
	
	private void editatuErabiltzailea(Erabiltzailea e) {
		JLabel lblErabiltzaileakEditatu = new JLabel("Erabiltzaileak editatu:");
		lblErabiltzaileakEditatu.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblErabiltzaileakEditatu.setBounds(428, 66, 261, 23);
		lblErabiltzaileakEditatu.setForeground(Color.WHITE);
		sortuErabiltzaileakPanela.add(lblErabiltzaileakEditatu);
		
		JLabel EditatuIzenalbl = new JLabel("Izena");
		EditatuIzenalbl.setBounds(554, 168, 86, 20);
		EditatuIzenalbl.setFont(new Font("Monospaced", Font.BOLD, 18));
		EditatuIzenalbl.setForeground(Color.WHITE);
		sortuErabiltzaileakPanela.add(EditatuIzenalbl);
		
		JLabel EditatuPasahitzalbl = new JLabel("Pasahitza");
		EditatuPasahitzalbl.setBounds(556, 212, 117, 23);
		EditatuPasahitzalbl.setFont(new Font("Monospaced", Font.BOLD, 18));
		EditatuPasahitzalbl.setForeground(Color.WHITE);
		sortuErabiltzaileakPanela.add(EditatuPasahitzalbl);
		
		JTextField EditatuIzenatxt = new JTextField();
		EditatuIzenatxt.setEditable(false);
		EditatuIzenatxt.setBounds(683, 170, 160, 23);
		sortuErabiltzaileakPanela.add(EditatuIzenatxt);
		EditatuIzenatxt.setText(e.getIzena());
		EditatuIzenatxt.setColumns(10);
		
		JTextField EditatuPasahitzatxt = new JTextField();
		EditatuPasahitzatxt.setEditable(false);
		EditatuPasahitzatxt.setBounds(683, 215, 160, 23);
		sortuErabiltzaileakPanela.add(EditatuPasahitzatxt);
		EditatuPasahitzatxt.setText(e.getPasahitza());
		EditatuPasahitzatxt.setColumns(10);
						
		String [] messageStrings = {"Erabiltzailea" , "Administratzailea"};
		JComboBox comboBox = new JComboBox(messageStrings);
		comboBox.setBounds(683, 117, 160, 23);
		comboBox.setEnabled(false);
		sortuErabiltzaileakPanela.add(comboBox);
		
		btnAldatuErabiltzailea = new JButton("Aldatu Datuak");
		btnAldatuErabiltzailea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EditatuIzenatxt.setEditable(true);
				EditatuPasahitzatxt.setEditable(true);
				comboBox.setEnabled(true);
			}
		});
		btnAldatuErabiltzailea.setBounds(683, 62, 160, 23);
		sortuErabiltzaileakPanela.add(btnAldatuErabiltzailea);
		
		JButton btnEzabatuErabiltzailea = new JButton("Ezabatu Erabiltzailea");
		btnEzabatuErabiltzailea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				nireLiga.kenduErabiltzailea(e);
				contentPane.removeAll();
				erabiltzaileakPanela();
				sortuErabiltzaileModeloa();
				revalidate();
				repaint();
			}
		});
		btnEzabatuErabiltzailea.setBounds(850, 62, 160, 23);
		sortuErabiltzaileakPanela.add(btnEzabatuErabiltzailea);
		gordeAldaketaErabiltzaile = new JButton("Gorde");
		gordeAldaketaErabiltzaile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//contentPane.removeAll();
				e.setIzena(EditatuIzenatxt.getText());
				e.setPasahitza(EditatuPasahitzatxt.getText());
				if(comboBox.getSelectedItem().equals("Erabiltzailea")) {
					erabiltzaileMota = "Erabiltzailea";
				}
				else if (comboBox.getSelectedItem().equals("Administratzailea")) {
					erabiltzaileMota = "Administratzailea";
				}
				e.setErabiltzaileMota(erabiltzaileMota);
				contentPane.removeAll();
				erabiltzaileakPanela();
				sortuErabiltzaileModeloa();
				revalidate();
				repaint();
			}
		});
		gordeAldaketaErabiltzaile.setBounds(683, 251, 160, 23);
		sortuErabiltzaileakPanela.add(gordeAldaketaErabiltzaile);		
	}
	
	private void marraztuJokalarienAldaketa(Jokalaria j, Taldea t) {
				
		JPanel taldeFitxaPanela = new JPanel();
		contentPane.add(taldeFitxaPanela);
		taldeFitxaPanela.setBackground(Color.DARK_GRAY);
		taldeFitxaPanela.setBounds(0, 0, 1008, 708);
		taldeFitxaPanela.setLayout(null);
		
		JLabel lblTaldeFitxa = new JLabel("Editatu Jokalaria");
		lblTaldeFitxa.setBounds(59, 21, 424, 59);
		lblTaldeFitxa.setFont(new Font("Monospaced", Font.BOLD, 40));
		lblTaldeFitxa.setForeground(Color.WHITE);
		taldeFitxaPanela.add(lblTaldeFitxa);
		
		JLabel lblNan = new JLabel("NAN");
		lblNan.setBounds(133, 169, 95, 17);
		lblNan.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblNan.setForeground(Color.WHITE);
		taldeFitxaPanela.add(lblNan);
		
		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setBounds(133, 197, 95, 22);
		lblIzena.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblIzena.setForeground(Color.WHITE);
		taldeFitxaPanela.add(lblIzena);
		
		JLabel lblAbizena = new JLabel("Abizena");
		lblAbizena.setBounds(133, 230, 95, 20);
		lblAbizena.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblAbizena.setForeground(Color.WHITE);
		taldeFitxaPanela.add(lblAbizena);
		
		JTextField jokalariNan = new JTextField();
		jokalariNan.setBounds(238, 171, 169, 20);
		taldeFitxaPanela.add(jokalariNan);
		jokalariNan.setText(j.getNan());
		jokalariNan.setEditable(false);
		
		JTextField jokalariIzena = new JTextField();
		jokalariIzena.setBounds(238, 202, 169, 20);
		taldeFitxaPanela.add(jokalariIzena);
		jokalariIzena.setText(j.getIzena());
		jokalariIzena.setEditable(false);
		
		JTextField jokalariAbizena = new JTextField();
		jokalariAbizena.setBounds(238, 233, 169, 20);
		taldeFitxaPanela.add(jokalariAbizena);
		jokalariAbizena.setText(j.getAbizena());
		jokalariAbizena.setEditable(false);
		
		Action gordeJokalariaAldaketa = new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				j.setIzena(jokalariIzena.getText());
				j.setNan(jokalariNan.getText());
				j.setAbizena(jokalariAbizena.getText());
				contentPane.removeAll();
				marraztuTaldeFitxa(t);
				revalidate();
				repaint();
			}
		};


		if (autMaila.equals("Administratzailea") && ligaMartxan == false) {
			JButton btnAldatuJokalaria = new JButton("Aldatu Jokalaria");
			btnAldatuJokalaria.setFont(new Font("Monospaced", Font.PLAIN, 13));
			btnAldatuJokalaria.setBounds(238, 113, 169, 23);
			btnAldatuJokalaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("x");
				jokalariIzena.setEditable(true);
				jokalariNan.setEditable(true);
				jokalariAbizena.setEditable(true);				
			}
		});
		taldeFitxaPanela.add(btnAldatuJokalaria);
		
		
		
		JButton btnGorde = new JButton("Gorde");
		btnGorde.setBounds(238, 290, 169, 23);
		btnGorde.addActionListener(gordeJokalariaAldaketa);
			
		taldeFitxaPanela.add(btnGorde);
		
		JButton btnEzabatuJokalaria = new JButton("Ezabatu Jokalaria");
		btnEzabatuJokalaria.setFont(new Font("Monospaced", Font.PLAIN, 13));
		btnEzabatuJokalaria.setBounds(59, 113, 172, 23);
		btnEzabatuJokalaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				t.kenduJokalaria(j);
				contentPane.removeAll();
				marraztuTaldeFitxa(t);
				revalidate();
				repaint();
			}
		});
		taldeFitxaPanela.add(btnEzabatuJokalaria);
		
		/*---enter klik egiterakoan gordetzen ditu datuak---*/
		jokalariNan.addActionListener(gordeJokalariaAldaketa);
		jokalariIzena.addActionListener(gordeJokalariaAldaketa);
		jokalariAbizena.addActionListener(gordeJokalariaAldaketa);
		
		
		
		}
		
		JLabel monigoteIrudia = new JLabel("");
		monigoteIrudia.setIcon(new ImageIcon(XextoMain.class.getResource("/Irudiak/Monigot.png")));
		monigoteIrudia.setBounds(534, 34, 393, 438);
		taldeFitxaPanela.add(monigoteIrudia);
		
		JLabel femaleIrudia = new JLabel("");
		femaleIrudia.setIcon(new ImageIcon(XextoMain.class.getResource("/Irudiak/female.png")));
		femaleIrudia.setBounds(10, 337, 268, 363);
		taldeFitxaPanela.add(femaleIrudia);
	}

	
	/*private JFrame nineu() {
		return this;
	}*/
}

	