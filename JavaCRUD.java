import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaCRUD {

	private JFrame frame;
	private JTextField bookName;
	private JTextField txtEdition;
	private JTextField txtPrice;
	private JTable table;
	private JTable table_1;
	private JTextField txtBookId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCRUD window = new JavaCRUD();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCRUD() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	        	   ex.printStackTrace();
	        }

	    }
	 public void table_load()
	    {
	    	try 
	    	{
		    ps = con.prepareStatement("select * from book");
		    rs = ps.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 642, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Sitka Heading", Font.BOLD, 30));
		lblNewLabel.setBounds(244, 11, 174, 54);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 75, 344, 247);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name ");
		lblNewLabel_1.setFont(new Font("Sitka Heading", Font.BOLD, 21));
		lblNewLabel_1.setBounds(10, 53, 141, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Sitka Heading", Font.BOLD, 21));
		lblNewLabel_1_1.setBounds(10, 100, 131, 23);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Sitka Heading", Font.BOLD, 21));
		lblNewLabel_1_2.setBounds(10, 144, 131, 23);
		panel.add(lblNewLabel_1_2);
		
		bookName = new JTextField();
		bookName.setBounds(142, 55, 192, 20);
		panel.add(bookName);
		bookName.setColumns(10);
		
		txtEdition = new JTextField();
		txtEdition.setColumns(10);
		txtEdition.setBounds(142, 103, 192, 20);
		panel.add(txtEdition);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(142, 144, 192, 20);
		panel.add(txtPrice);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
				
				
				String bname,edition,price,bid;
				
				
				bname = bookName.getText();
				edition = txtEdition.getText();
				price = txtPrice.getText();
				bid  = txtBookId.getText();
				
				 try {
						ps= con.prepareStatement("update book set book_name= ?,edition=?,price=? where id =?");
						ps.setString(1, bname);
			            ps.setString(2, edition);
			            ps.setString(3, price);
			            ps.setString(4, bid);
			            ps.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
			            table_load();
			          
			            bookName.setText("");
			            txtEdition.setText("");
			            txtPrice.setText("");
			            bookName.requestFocus();
			}
			 
			            catch (SQLException e1) {
			e1.printStackTrace();
			}
			}


		});
		btnNewButton.setFont(new Font("Sitka Banner", Font.BOLD, 17));
		btnNewButton.setBounds(10, 199, 89, 43);
		panel.add(btnNewButton);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setFont(new Font("Sitka Banner", Font.BOLD, 17));
		btnExit.setBounds(124, 199, 89, 43);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setFont(new Font("Sitka Banner", Font.BOLD, 17));
		btnClear.setBounds(237, 199, 97, 43);
		panel.add(btnClear);
		
		table = new JTable();
		table.setBounds(593, 384, -190, -248);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 75, 262, 251);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "SEARCH", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 333, 334, 49);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Book ID");
		lblNewLabel_1_1_1.setBounds(22, 15, 79, 27);
		lblNewLabel_1_1_1.setFont(new Font("Sitka Heading", Font.BOLD, 21));
		panel_2.add(lblNewLabel_1_1_1);
		
		txtBookId = new JTextField();
		txtBookId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				
				 try {
			          
			            String id = txtBookId.getText();
			                ps = con.prepareStatement("select book_name,edition,price from book where id = ?");
			                ps.setString(1, id);
			                ResultSet rs = ps.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String name = rs.getString(1);
			                String edition = rs.getString(2);
			                String price = rs.getString(3);
			                
			                bookName.setText(name);
			                txtEdition.setText(edition);
			                txtPrice.setText(price);
			                
			                
			            }  
			            else
			            {
			             bookName.setText("");
			             txtEdition.setText("");
			                txtPrice.setText("");
			                
			            }
			 
			        }
			catch (SQLException ex) {
			          ex.printStackTrace();
			}

			}});
		txtBookId.setBounds(122, 15, 151, 20);
		txtBookId.setColumns(10);
		panel_2.add(txtBookId);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 15));
		btnUpdate.setBounds(361, 339, 97, 43);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 15));
		btnDelete.setBounds(501, 339, 97, 43);
		frame.getContentPane().add(btnDelete);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(22, 11, -3, 0);
		frame.getContentPane().add(panel_1);
	}
}
