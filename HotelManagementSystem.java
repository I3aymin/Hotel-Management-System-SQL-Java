import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class HotelManagementSystem {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

	private JFrame frame;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField address;
	private JTextField city;
	private JTextField state;
	private JComboBox roomStyle;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField checkIn;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField checkOut;
	private JComboBox roomType;
	private JButton clearButton;
	private JLabel billingStatement;
	
	private static String guestID;
	private static int omID;
	private static String transactionID;
	
	private static String first;
	private static String last;

	
	public static Statement statement;
	public static Connection connection;
	private JButton checkOutButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//evere//Documents//HotelMangamentSystem.accdb");// change this part
			statement = connection.createStatement();
		}
		
		catch (SQLException sqlException)
		{
			JOptionPane.showMessageDialog( null,
		            sqlException.getMessage(), "Database Error",
		            JOptionPane.ERROR_MESSAGE );
		         System.exit( 1 );
		}
		
		catch (ClassNotFoundException cnfex)
		{
			System.out.println("Problem in loading or "
	                  + "registering MS Access JDBC driver");
	          cnfex.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HotelManagementSystem window = new HotelManagementSystem();
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
	public HotelManagementSystem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 332);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(10, 11, 63, 14);
		frame.getContentPane().add(lblNewLabel);
		
		firstName = new JTextField();
		firstName.setBounds(160, 8, 89, 20);
		frame.getContentPane().add(firstName);
		firstName.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setBounds(10, 36, 89, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lastName = new JTextField();
		lastName.setBounds(160, 33, 89, 20);
		frame.getContentPane().add(lastName);
		lastName.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setBounds(0, 0, 0, 0);
		frame.getContentPane().add(lblNewLabel_2);
		
		address = new JTextField();
		address.setBounds(160, 58, 89, 20);
		frame.getContentPane().add(address);
		address.setColumns(10);
		
		lblNewLabel_3 = new JLabel("City");
		lblNewLabel_3.setBounds(0, 0, 0, 0);
		frame.getContentPane().add(lblNewLabel_3);
		
		city = new JTextField();
		city.setBounds(160, 83, 89, 20);
		frame.getContentPane().add(city);
		city.setColumns(10);
		
		lblNewLabel_4 = new JLabel("State");
		lblNewLabel_4.setBounds(0, 0, 0, 0);
		frame.getContentPane().add(lblNewLabel_4);
		
		state = new JTextField();
		state.setBounds(160, 108, 89, 20);
		frame.getContentPane().add(state);
		state.setColumns(10);
		
		lblNewLabel_6 = new JLabel("Check in Date");
		lblNewLabel_6.setBounds(10, 136, 73, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		checkIn = new JTextField();
		checkIn.setBounds(160, 133, 89, 20);
		frame.getContentPane().add(checkIn);
		checkIn.setColumns(10);
		
		lblNewLabel_7 = new JLabel("Check out Date");
		lblNewLabel_7.setBounds(10, 161, 89, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		checkOut = new JTextField();
		checkOut.setBounds(160, 158, 89, 20);
		frame.getContentPane().add(checkOut);
		checkOut.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Room Type");
		lblNewLabel_5.setBounds(10, 186, 89, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		roomStyle = new JComboBox();
		roomStyle.setBounds(160, 183, 89, 20);
		roomStyle.setModel(new DefaultComboBoxModel(new String[] {"Luxury", "Cottage"}));
		roomStyle.setToolTipText("");
		frame.getContentPane().add(roomStyle);
		
		roomType = new JComboBox();
		roomType.setBounds(160, 208, 89, 20);
		roomType.setModel(new DefaultComboBoxModel(new String[] {"1 Queen Bed", "2 Queen Bed", "Two Rooms", "Three Rooms", "Bridal"}));
		frame.getContentPane().add(roomType);
		
		billingStatement = new JLabel("Billing Statement: $");
		billingStatement.setBounds(10, 227, 183, 19);
		billingStatement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(billingStatement);
		
		JButton submitButton = new JButton("Check In");
		submitButton.setBounds(50, 257, 105, 23);
		submitButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				String[] input = new String[9];
				
				input[0] = firstName.getText();
				input[1] = lastName.getText();
				input[2] = address.getText();
				input[3] = city.getText();
				input[4] = state.getText();
				input[5] = checkIn.getText();
				input[6] = checkOut.getText();
				input[7] = roomStyle.getSelectedItem().toString();
				input[8] = roomType.getSelectedItem().toString();
				
				//Converts input to date objects
				Calendar checkIn = convertDate(input[5]);
				Calendar realCheckIn = convertDate(input[5]);;
				System.out.println(dateFormat.format(checkIn.getTime()));
				Calendar checkOut = convertDate(input[6]);
				System.out.println(dateFormat.format(checkOut.getTime()));
				
				boolean ifEmpty = false;
				for(int i=0; i<input.length; i++)
				{
					if(input[i].equals(""))
					{
						ifEmpty = true;
						break;
					}
				}
				
					try {
						if(ifEmpty == true)
							JOptionPane.showMessageDialog(null, "Please make sure all fields are filled!");
						if(compareDates(getRoomID(input[8], input[7]), checkIn, checkOut) != true)
							JOptionPane.showMessageDialog(null, "That date is already taken, fill out another time or type of room!");
						else
						{
							insertGuest(input[0], input[1], input[2], input[3], input[4]);
							System.out.println(dateFormat.format(realCheckIn.getTime()));
							insertStatus(input[7], input[8], dateFormat.format( convertDate(input[5]).getTime()), dateFormat.format( convertDate(input[6]).getTime()));
							try {
								billingStatement.setText("Billing Statement: $" + getBilling(getRoomID(input[8], input[7]), convertDate(input[5]),  convertDate(input[6])));
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Insertion Successful");
						}
					} catch (HeadlessException e1) {		
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
		});
		frame.getContentPane().add(submitButton);
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(160, 257, 89, 23);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstName.setText("");
				lastName.setText("");
				address.setText("");
				city.setText("");
				state.setText("");
				checkIn.setText("");
				checkOut.setText("");
				
			}
		});
		frame.getContentPane().add(clearButton);
		
		checkOutButton = new JButton("Check Out");
		checkOutButton.setBounds(254, 257, 105, 23);
		checkOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String[] input = new String[2];
				
				input[0] = firstName.getText();
				input[1] = lastName.getText();
				
				boolean ifEmpty = false;
				for(int i=0; i<2; i++)
				{
					if(input[i].equals(""))
					{
						ifEmpty = true;
						break;
					}
				}
				
				if(ifEmpty == true)
					JOptionPane.showMessageDialog(null, "Please make sure all fields are filled!");
				else {
					try {
						delete(input[0], input[1]);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Deletion Successful");
				}
			}
		});
		frame.getContentPane().add(checkOutButton);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(10, 111, 73, 14);
		frame.getContentPane().add(lblState);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(10, 86, 73, 14);
		frame.getContentPane().add(lblCity);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 61, 73, 14);
		frame.getContentPane().add(lblAddress);
	}
	
	public static void getGuestID(String firstName, String lastName)
	{
		try(Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//evere//Documents//HotelMangamentSystem.accdb")){
			try(PreparedStatement stmt = connection.prepareStatement("SELECT GuestID FROM Guest WHERE FirstName LIKE ? AND LastName LIKE ?"))
			{
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
				guestID = rs.getString(1);
			
			statement.close();
			}
		}
			
		catch (SQLException sqlException)
		{
			JOptionPane.showMessageDialog( null,
		            sqlException.getMessage(), "Database Error",
		            JOptionPane.ERROR_MESSAGE );
		         System.exit( 1 );
		}

	}
	
	public static int getRoomID(String roomType, String roomStyle) throws SQLException 
	{
		int roomID = 0;
		try(Connection connection3 = DriverManager.getConnection("jdbc:ucanaccess://C://Users//evere//Documents//HotelMangamentSystem.accdb")){
			try(PreparedStatement stmt = connection3.prepareStatement("SELECT RoomID FROM RoomList WHERE (RoomType LIKE ?) AND (RoomStyle LIKE ?)"))
			{
			stmt.setString(1, roomType);
			stmt.setString(2, roomStyle);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
				roomID = rs.getInt(1);
			
			statement.close();
			}
			System.out.println(roomID);
			return roomID;
		}
	}
	
	public void insertGuest(String firstName, String lastName, String address, String city, String state)
	{
		try(Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//evere//Documents//HotelMangamentSystem.accdb")){
			try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO Guest(FirstName,LastName, Address, City, State) "
					+ "values(?,?,?,?,?)"))
			{
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, address);
			stmt.setString(4, city);
			stmt.setString(5, state);
			
			stmt.executeUpdate();
			
			statement.close();
			}
			
			try(PreparedStatement stmt = connection.prepareStatement("SELECT GuestID FROM Guest\n"
					+ "WHERE (FirstName = ?) and (LastName = ?)"))
			{
				stmt.setString(1, firstName);
				stmt.setString(2, lastName);
				
				stmt.executeQuery();
				
				getGuestID(firstName, lastName);
		         
				statement.close();
			}
		}
		catch (SQLException sqlException)
		{
			JOptionPane.showMessageDialog( null,
		            sqlException.getMessage(), "Database Error",
		            JOptionPane.ERROR_MESSAGE );
		         System.exit( 1 );
		}
	}
	
	
	public static void insertStatus(String roomStyle, String roomType, String checkIn, String checkOut)
	{
		
		getGuestID(first, last);
		try(Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//evere//Documents//HotelMangamentSystem.accdb")){
			try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO Status(GuestID, RoomID, CheckInDate, CheckOutDate) values(?,?,?,?)"))
			{
				stmt.setString(1, guestID);
				stmt.setInt(2, getRoomID(roomType, roomStyle));
				stmt.setString(3, checkIn);
				stmt.setString(4, checkOut);

				stmt.executeUpdate();
			
				statement.close();
			}
		}
		catch (SQLException sqlException)
		{
			JOptionPane.showMessageDialog( null,
		            sqlException.getMessage(), "Database Error",
		            JOptionPane.ERROR_MESSAGE );
		         System.exit( 1 );
		}
	}
	
	public static void delete(String firstName, String lastName) throws SQLException 
	{
		try(Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//evere//Documents//HotelMangamentSystem.accdb"))
		{
			getGuestID(firstName, lastName);
			try(PreparedStatement stmt = connection.prepareStatement("DELETE FROM Status WHERE GuestID = ?"))
			{
				stmt.setString(1, guestID);

				stmt.executeUpdate();

				statement.close();
			}
			try(PreparedStatement stmt = connection.prepareStatement("DELETE FROM Guest WHERE GuestID = ?"))
			{
				stmt.setString(1, guestID);

				stmt.executeUpdate();

				statement.close();
			}
		}
		catch (SQLException sqlException)
		{
			JOptionPane.showMessageDialog( null,
		            sqlException.getMessage(), "Database Error",
		            
		            JOptionPane.ERROR_MESSAGE );
		         System.exit( 1 );
		}
	}
	
	//Converts input to date objects
	public static Calendar convertDate(String temp) {
		String[] stringSplit = temp.split("-");
		int[] dateArray = new int[stringSplit.length];

		for (int i = 0; i < stringSplit.length; i++) {
			String stringNum = stringSplit[i];
			dateArray[i] = Integer.parseInt(stringNum);
		}
		
		Calendar result = new GregorianCalendar(dateArray[2],(dateArray[0] - 1),dateArray[1]);
		return result;
	}
	
	//Compare the Dates already in the Database to the dates were trying to add
	public static boolean compareDates(int RoomID, Calendar CheckIn, Calendar CheckOut) throws SQLException {
		boolean check = true;
		List<Calendar> datesInAlready = combineDates(pullCheckInDates(RoomID), pullCheckOutDates(RoomID));
		List<Calendar> datesToCheck = new ArrayList<Calendar>();
		
		while(CheckIn.compareTo(CheckOut) != 0) {
			datesToCheck.add(CheckIn);
			CheckIn.add(Calendar.DAY_OF_MONTH, 1);
		}
		for(int i = 0; i < datesToCheck.size(); i++) {
			for(int j = 0; j < datesInAlready.size(); j++) {
				if(datesToCheck.get(i).getTime().compareTo(datesInAlready.get(j).getTime()) == 0) {
					check = false;
				}
			}
		}
		System.out.print(check);
		return check;
	}
	
	public static List<Calendar> combineDates(List<String> CheckInList, List<String> CheckOutList) {
		List<Calendar> ArrayIn = new ArrayList<>();
		for(int i = 0; i < CheckInList.size(); i++) {
			Calendar date1 = convertDate(CheckInList.get(i));
			Calendar date2 = convertDate(CheckOutList.get(i));
			while(date1.compareTo(date2) != 0) {
				ArrayIn.add(date1);
				date1.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		return ArrayIn;
	}
	
	public static List<String> pullCheckInDates(int RoomID) throws SQLException {
		List<String> ArrayIn = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//evere//Documents//HotelMangamentSystem.accdb"))
		{
			try(PreparedStatement stmt = connection.prepareStatement("SELECT CheckInDate FROM status List\n"
					+ "WHERE RoomId = ?"))
			{
				stmt.setInt(1, RoomID);

				ResultSet resultSet = stmt.executeQuery();
				ArrayIn = new ArrayList<>();
				while (resultSet.next()) {      
					ArrayIn.add(resultSet.getString("CheckInDate"));   
				}
			}
			return ArrayIn;
		}
	}
	
	public static List<String> pullCheckOutDates(int RoomID) throws SQLException {
		List<String> ArrayIn = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//evere//Documents//HotelMangamentSystem.accdb"))
		{
			try(PreparedStatement stmt = connection.prepareStatement("SELECT CheckOutDate FROM status List\n"
					+ "WHERE RoomId = ?"))
			{
				stmt.setInt(1, RoomID);

				ResultSet resultSet = stmt.executeQuery();
				while (resultSet.next()) {      
					ArrayIn.add(resultSet.getString("CheckOutDate"));   
			    }
			}
			return ArrayIn;
		}
	}
	
	public static double getBilling(int RoomID, Calendar date1, Calendar date2) throws SQLException {
		double result = 0.0;
		int count = 0;
		while(date1.compareTo(date2) != 0) {
			count++;
			date1.add(Calendar.DAY_OF_MONTH, 1);
		} 
		if(count == 1) {
			try(Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//evere//Documents//HotelMangamentSystem.accdb")){
				try(PreparedStatement stmt = connection.prepareStatement("SELECT Night1 from RoomList where RoomID = ?"))
				{
				stmt.setInt(1, RoomID);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next())
					result = rs.getDouble(1);
				
				statement.close();
				}
			}
		
		}
		else if(count == 2) {
			try(Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//evere//Documents//HotelMangamentSystem.accdb")){
				try(PreparedStatement stmt = connection.prepareStatement("SELECT Night2 from RoomList where RoomID = ?"))
				{
				stmt.setInt(1, RoomID);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next())
					result = rs.getDouble(1);
				
				statement.close();
				}
			}
		}
		else {
			double temp = 0;
			try(Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//evere//Documents//HotelMangamentSystem.accdb")){
				try(PreparedStatement stmt = connection.prepareStatement("SELECT Night2, Extra from RoomList where RoomID = ?"))
				{
				stmt.setInt(1, RoomID);
				
				
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
					temp = rs.getDouble(1);
				if(RoomID == 1 || RoomID == 2 || RoomID == 3 || RoomID == 10 || RoomID == 11 || RoomID == 12)
					result = temp  + ( 22 * (count - 2));
				if(RoomID == 4 || RoomID == 5 || RoomID == 13 || RoomID == 14)
					result = temp;
				else
					result = temp  + ( 30 * (count - 2));
				
				statement.close();
				}
			}
		}
		
		getGuestID(first, last);
		try(Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//evere//Documents//HotelMangamentSystem.accdb"))
		{
			try(PreparedStatement stmt = connection.prepareStatement("UPDATE Status SET bill = ? WHERE GuestID = ?"))
			{
				stmt.setDouble(1, result);
				stmt.setString(2, guestID);
			
				stmt.executeUpdate();
		
				statement.close();
			}
		
		System.out.println("This is result: " + result);
		return result;
	}
	
	}
}
