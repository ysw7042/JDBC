package xyz.itwill.student; 

/********************************************************
ÆÄ    ÀÏ   ¸í : StudentGUIApp.java
±â         ´É : ÇÐ»ý °ü¸® ÇÁ·Î±×·¥
*********************************************************/
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StudentGUIApp extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	public static final int NONE = 0;
	public static final int ADD = 1;
	public static final int DELETE = 2;
	public static final int UPDATE = 3;
	public static final int UPDATE_CHANGE = 4;
	public static final int SEARCH = 5;

	JTextField noTF, nameTF, phoneTF, addressTF, birthdayTF;
	JButton addB, deleteB, updateB, searchB, cancelB;
	
	JTable table;
	
	int cmd;
	/********************************************
	 * »ý¼ºÀÚ Á¤ÀÇ
	 *********************************************/
	public StudentGUIApp() throws Exception {
		setTitle("¡ß¡ß¡ß ÇÐ»ý °ü¸® ÇÁ·Î±×·¥ ¡ß¡ß¡ß");
		setSize(800, 400);

		Dimension dim = getToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2
				- getHeight() / 2);

		JPanel left = new JPanel();
		left.setLayout(new GridLayout(5, 1));

		JPanel pno = new JPanel();
		pno.add(new JLabel("¹ø  È£"));
		pno.add(noTF = new JTextField(10));

		JPanel pname = new JPanel();
		pname.add(new JLabel("ÀÌ  ¸§"));
		pname.add(nameTF = new JTextField(10));
		
		JPanel pbirthday = new JPanel();
		pbirthday.add(new JLabel("»ý  ÀÏ"));
		pbirthday.add(birthdayTF = new JTextField(10));
		
		JPanel pphone = new JPanel();
		pphone.add(new JLabel("Àü  È­"));
		pphone.add(phoneTF = new JTextField(10));

		JPanel paddress = new JPanel();
		paddress.add(new JLabel("ÁÖ  ¼Ò"));
		paddress.add(addressTF = new JTextField(10));

		left.add(pno);
		left.add(pname);
		left.add(pphone);
		left.add(paddress);
		left.add(pbirthday);
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1, 5));

		bottom.add(addB = new JButton("Ãß  °¡"));
		addB.addActionListener(this);

		bottom.add(deleteB = new JButton("»è  Á¦"));
		deleteB.addActionListener(this);
		
		bottom.add(updateB = new JButton("º¯  °æ"));
		updateB.addActionListener(this);

		bottom.add(searchB = new JButton("°Ë  »ö"));
		searchB.addActionListener(this);
		
		bottom.add(cancelB = new JButton("Ãë  ¼Ò"));
		cancelB.addActionListener(this);

		Object[] title={"ÇÐ¹ø","ÀÌ¸§","ÀüÈ­¹øÈ£","ÁÖ¼Ò","»ý³â¿ùÀÏ"};
		table=new JTable(new DefaultTableModel(title, 0));
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		JScrollPane sp=new JScrollPane(table);
		
		add(sp, "Center");
		add(left, "West");
		add(bottom, "South");
		cmd = NONE;
		initialize();

		displayAllStudent();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void initialize() {
		noTF.setEditable(false);
		nameTF.setEditable(false);
		phoneTF.setEditable(false);
		addressTF.setEditable(false);
		birthdayTF.setEditable(false);
	}

	public void setEditable(int n) {
		switch (n) {
		case ADD:
			noTF.setEditable(true);
			nameTF.setEditable(true);
			phoneTF.setEditable(true);
			addressTF.setEditable(true);
			birthdayTF.setEditable(true);
			break;
		case DELETE:
			noTF.setEditable(true);
			break;
		case UPDATE:
			noTF.setEditable(true);
			break;
		case UPDATE_CHANGE:
			noTF.setEditable(false);
			nameTF.setEditable(true);
			phoneTF.setEditable(true);
			addressTF.setEditable(true);
			birthdayTF.setEditable(true);
			break;
		case SEARCH:
			nameTF.setEditable(true);
			break;
		case NONE:
			noTF.setEditable(false);
			nameTF.setEditable(false);
			phoneTF.setEditable(false);
			addressTF.setEditable(false);
			birthdayTF.setEditable(false);
		}
	}

	public void setEnable(int n) {
		addB.setEnabled(false);
		deleteB.setEnabled(false);
		updateB.setEnabled(false);
		searchB.setEnabled(false);

		switch (n) {
		case ADD:
			addB.setEnabled(true);
			setEditable(ADD);
			cmd = ADD;
			break;
		case DELETE:
			deleteB.setEnabled(true);
			setEditable(DELETE);
			cmd = DELETE;
			break;
		case UPDATE:
			updateB.setEnabled(true);
			setEditable(UPDATE);
			cmd = UPDATE;
			break;			
		case UPDATE_CHANGE:
			updateB.setEnabled(true);
			setEditable(UPDATE_CHANGE);
			cmd = UPDATE_CHANGE;
			break;			
		case SEARCH:
			searchB.setEnabled(true);
			setEditable(SEARCH);
			cmd = SEARCH;
			break;
		case NONE:
			addB.setEnabled(true);
			deleteB.setEnabled(true);
			updateB.setEnabled(true);
			searchB.setEnabled(true);
		}
	}

	public void clear() {
		noTF.setText("");
		nameTF.setText("");
		phoneTF.setText("");
		addressTF.setText("");
		birthdayTF.setText("");
	}

	public void initDisplay() {
		setEnable(NONE);
		clear();
		cmd = NONE;
		initialize();		
	}

	public static void main(String args[]) throws Exception {
		new StudentGUIApp();
	}
	
	public void actionPerformed(ActionEvent ev) {
		Component c = (Component) ev.getSource();
		try {
			if (c == addB) {//Ãß°¡ ¹öÆ°À» ´©¸¥ °æ¿ì
				if (cmd != ADD) {//ADD »óÅÂ°¡ ¾Æ´Ñ °æ¿ì
					setEnable(ADD);//ADD »óÅÂ·Î È°¼ºÈ­					
				} else {//ADD »óÅÂÀÎ °æ¿ì
					addStudent();
				}
			} else if (c == deleteB) {
				if (cmd != DELETE) {
					setEnable(DELETE);
				} else {
					removeStudent();
				}
			} else if (c == updateB) {
				if (cmd != UPDATE && cmd != UPDATE_CHANGE) {
					setEnable(UPDATE);
				} else if (cmd != UPDATE_CHANGE) {
					setEnable(UPDATE_CHANGE);
				} else  {
					initDisplay();
				}
			} else if (c == searchB) {
				if (cmd != SEARCH) {
					setEnable(SEARCH);
				} else {
					initDisplay();
				}
			} else if (c == cancelB) {
				initDisplay();
			}
		} catch (Exception e) {
			System.out.println("¿¹¿Ü ¹ß»ý : " + e);
		}		
	}
	
	//STUDENT Å×ÀÌºí¿¡ ÀúÀåµÈ ¸ðµç ÇÐ»ýÁ¤º¸¸¦ °Ë»öÇÏ¿© JTable ÄÄÆÛ³ÍÆ®¿¡ Ãâ·ÂÇÏ´Â ¸Þ¼Òµå
	public void displayAllStudent() {
		//STUDENT Å×ÀÌºí¿¡ ÀúÀåµÈ ¸ðµç ÇÐ»ýÁ¤º¸¸¦ °Ë»öÇÏ¿© ¹ÝÈ¯ÇÏ´Â DAO Å¬·¡½ºÀÇ ¸Þ¼Òµå È£Ãâ
		List<StudentDTO> studentList=StudentDAO.getDAO().selectAllStudentList();
		
		if(studentList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "ÀúÀåµÈ ÇÐ»ýÁ¤º¸°¡ ¾ø½À´Ï´Ù.");
			return;
		}
		
		//JTable.getModel() : TableModel ÀÎ½ºÅÏ½º¸¦ ¹ÝÈ¯ÇÏ´Â ¸Þ¼Òµå
		// => DefaultTableModel Å¬·¡½º·Î ¸í½ÃÀû °´Ã¼ Çüº¯È¯ÇÏ¿© ÂüÁ¶º¯¼ö¿¡ ÀúÀå
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		
		//±âÁ¸ JTable ÄÄÆÛ³ÍÆ®¿¡ Á¸ÀçÇÏ´Â ¸ðµç ÇàÀ» ÇÏ³ª¾¿ Á¦°Å
		// => DefaultTableModel ÀÎ½ºÅÏ½º¿¡ ÀúÀåµÈ ¸ðµç ÇàÀ» Á¦°ÅÇÏ¿© ÃÊ±âÈ­
		//DefaultTableModel.getRowCount() : DefaultTableModel ÀÎ½ºÅÏ½ºÀÇ Çà°¹¼ö¸¦ ¹ÝÈ¯ÇÏ´Â ¸Þ¼Òµå
		for(int i=model.getRowCount();i>0;i--) {
			//DefaultTableModel.removeRow(int rowIndex) : Çà¹øÈ£¸¦ Àü´Þ¹Þ¾Æ DefaultTableModel
			//ÀÎ½ºÅÏ½ºÀÇ ÇØ´ç ÇàÀ» »èÁ¦ÇÏ´Â ¸Þ¼Òµå
			model.removeRow(0);
		}
		
		//JTable ÄÄÆÛ³ÍÆ®¿¡ ¹ÝÈ¯¹ÞÀº ÇÐ»ýÁ¤º¸¸¦ Ãâ·Â
		// => °Ë»öµÈ ÇÐ»ýÁ¤º¸¸¦ DefaultTableModel ÀÎ½ºÅÏ½º¿¡ ÇÏ³ª¾¿ ÇàÀ¸·Î Ãß°¡ÇÏ¿© Ãâ·Â
		for(StudentDTO student:studentList) {
			//Vector ÀÎ½ºÅÏ½º¸¦ »ý¼ºÇÏ¿© StudentDTO ÀÎ½ºÅÏ½ºÀÇ ÇÊµå°ªÀ» ¿ä¼Ò·Î Ãß°¡
			Vector<Object> rowData=new Vector<Object>();
			rowData.add(student.getNo());
			rowData.add(student.getName());
			rowData.add(student.getPhone());
			rowData.add(student.getAddress());
			rowData.add(student.getBirthday());
			//DefaultTableModel.addRow(Vector rowData) : Vector ÀÎ½ºÅÏ½º¸¦ Àü´Þ¹Þ¾Æ
			//DefaultTableModel ÀÎ½ºÅÏ½ºÀÇ ÇàÀ¸·Î Ãß°¡ÇÏ´Â ¸Þ¼Òµå
			model.addRow(rowData);
		}
	}
	
	//JTextField ÄÄÆÛ³ÍÆ®·Î ÀÔ·ÂµÈ ÇÐ»ýÁ¤º¸¸¦ Á¦°ø¹Þ¾Æ STUDENT Å×ÀÌºí¿¡ ÀúÀåÇÏ´Â ¸Þ¼Òµå
	public void addStudent() {
		String noTemp=noTF.getText();
		
		if(noTemp.equals("")) {//ÀÔ·Â°ªÀÌ ¾ø´Â °æ¿ì
			JOptionPane.showMessageDialog(this, "ÇÐ¹øÀ» ¹Ýµå½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			noTF.requestFocus();//ÀÔ·ÂÃÐÁ¡À» ÀÌµ¿ÇÏ´Â ¸Þ¼Òµå
			return;
		}
		
		String noReg="\\d{4}";//Á¤±ÔÇ¥Çö½Ä
		if(!Pattern.matches(noReg, noTemp)) {//Á¤±ÔÇ¥Çö½Ä°ú ÀÔ·Â°ªÀÇ Çü½ÄÀÌ ´Ù¸¥ °æ¿ì
			JOptionPane.showMessageDialog(this, "ÇÐ¹øÀº 4ÀÚ¸®ÀÇ ¼ýÀÚ·Î¸¸ ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			noTF.requestFocus();
			return;
		}
		
		int no=Integer.parseInt(noTemp);
		
		//ÇÐ¹øÀ» Àü´ÞÇÏ¿© STUDENT Å×ÀÌºí¿¡ ÀúÀåµÈ ÇØ´ç ÇÐ¿øÀÇ ÇÐ»ýÁ¤º¸¸¦ °Ë»öÇÏ¿© ¹ÝÈ¯ÇÏ´Â
		//DAO Å¬·¡½ºÀÇ ¸Þ¼Òµå È£Ãâ
		// => NULL ¹ÝÈ¯ : ÇÐ»ýÁ¤º¸ ¹Ì°Ë»ö - ÇØ´ç ÇÐ¹øÀÇ ÇÐ»ýÁ¤º¸ ¹ÌÁ¸Àç
		// => StudentDTO ÀÎ½ºÅÏ½º ¹ÝÈ¯ : ÇÐ»ýÁ¤º¸ °Ë»ö - ÇØ´ç ÇÐ¹øÀÇ ÇÐ»ýÁ¤º¸ Á¸Àç
		if(StudentDAO.getDAO().selectNoStudent(no)!=null) {
			JOptionPane.showMessageDialog(this, "ÀÌ¹Ì »ç¿ëÁßÀÎ ÇÐ¹øÀÔ´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			noTF.requestFocus();
			return;
		}
		
		String name=nameTF.getText();
		
		if(name.equals("")) {//ÀÔ·Â°ªÀÌ ¾ø´Â °æ¿ì
			JOptionPane.showMessageDialog(this, "ÀÌ¸§À» ¹Ýµå½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			nameTF.requestFocus();//ÀÔ·ÂÃÐÁ¡À» ÀÌµ¿ÇÏ´Â ¸Þ¼Òµå
			return;
		}
		
		String nameReg="[°¡-ÆR]{2,7}";//Á¤±ÔÇ¥Çö½Ä
		if(!Pattern.matches(nameReg, name)) {//Á¤±ÔÇ¥Çö½Ä°ú ÀÔ·Â°ªÀÇ Çü½ÄÀÌ ´Ù¸¥ °æ¿ì
			JOptionPane.showMessageDialog(this, "ÀÌ¸§Àº 2~5 ¹üÀ§ÀÇ ÇÑ±Û¸¸ ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			nameTF.requestFocus();
			return;
		}
		
		String phone=phoneTF.getText();
		
		if(phone.equals("")) {//ÀÔ·Â°ªÀÌ ¾ø´Â °æ¿ì
			JOptionPane.showMessageDialog(this, "ÀüÈ­¹øÈ£¸¦ ¹Ýµå½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			phoneTF.requestFocus();//ÀÔ·ÂÃÐÁ¡À» ÀÌµ¿ÇÏ´Â ¸Þ¼Òµå
			return;
		}
		
		String phoneReg="(01[016789])-\\d{3,4}-\\d{4}";//Á¤±ÔÇ¥Çö½Ä
		if(!Pattern.matches(phoneReg, phone)) {//Á¤±ÔÇ¥Çö½Ä°ú ÀÔ·Â°ªÀÇ Çü½ÄÀÌ ´Ù¸¥ °æ¿ì
			JOptionPane.showMessageDialog(this, "ÀüÈ­¹øÈ£¸¦ Çü½Ä¿¡ ¸Â°Ô ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			phoneTF.requestFocus();
			return;
		}
		
		String address=addressTF.getText();
		
		if(address.equals("")) {//ÀÔ·Â°ªÀÌ ¾ø´Â °æ¿ì
			JOptionPane.showMessageDialog(this, "ÁÖ¼Ò¸¦ ¹Ýµå½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			addressTF.requestFocus();//ÀÔ·ÂÃÐÁ¡À» ÀÌµ¿ÇÏ´Â ¸Þ¼Òµå
			return;
		}
		
		String birthday=birthdayTF.getText();
		
		if(birthday.equals("")) {//ÀÔ·Â°ªÀÌ ¾ø´Â °æ¿ì
			JOptionPane.showMessageDialog(this, "»ý³â¿ùÀÏÀ» ¹Ýµå½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			birthdayTF.requestFocus();//ÀÔ·ÂÃÐÁ¡À» ÀÌµ¿ÇÏ´Â ¸Þ¼Òµå
			return;
		}
		
		String birthdayReg="(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";//Á¤±ÔÇ¥Çö½Ä
		if(!Pattern.matches(birthdayReg, birthday)) {//Á¤±ÔÇ¥Çö½Ä°ú ÀÔ·Â°ªÀÇ Çü½ÄÀÌ ´Ù¸¥ °æ¿ì
			JOptionPane.showMessageDialog(this, "»ý³â¿ùÀÏÀ» Çü½Ä¿¡ ¸Â°Ô ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			birthdayTF.requestFocus();
			return;
		}
		
		//DTO ÀÎ½ºÅÏ½º¸¦ »ý¼ºÇÏ°í ÄÄÆÛ³ÍÆ®ÀÇ ÀÔ·Â°ªÀ¸·Î ÇÊµå°ª º¯°æ
		StudentDTO student=new StudentDTO();
		student.setNo(no);
		student.setName(name);
		student.setPhone(phone);
		student.setAddress(address);
		student.setBirthday(birthday);
		
		//ÇÐ»ýÁ¤º¸¸¦ Àü´ÞÇÏ¿© STUDENT Å×ÀÌºí¿¡ ÀúÀåÇÏ´Â DAO Å¬·¡½ºÀÇ ¸Þ¼Òµå È£Ãâ
		int rows=StudentDAO.getDAO().insertStudent(student);
		
		JOptionPane.showMessageDialog(this, rows+"¸íÀÇ ÇÐ»ýÁ¤º¸¸¦ ÀúÀå ÇÏ¿´½À´Ï´Ù.");
		
		displayAllStudent();
		initDisplay();
	}
	
	//JTextField ÄÄÆÛ³ÍÆ®·Î ÀÔ·ÂµÈ ÇÐ¹øÀ» Á¦°ø¹Þ¾Æ STUDENT Å×ÀÌºí¿¡ ÀúÀåµÈ ÇØ´ç ÇÐ¹øÀÇ
	//ÇÐ»ýÁ¤º¸¸¦ »èÁ¦ÇÏ´Â ¸Þ¼Òµå
	public void removeStudent() {
		String noTemp=noTF.getText();
		
		if(noTemp.equals("")) {//ÀÔ·Â°ªÀÌ ¾ø´Â °æ¿ì
			JOptionPane.showMessageDialog(this, "ÇÐ¹øÀ» ¹Ýµå½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			noTF.requestFocus();//ÀÔ·ÂÃÐÁ¡À» ÀÌµ¿ÇÏ´Â ¸Þ¼Òµå
			return;
		}
		
		String noReg="\\d{4}";//Á¤±ÔÇ¥Çö½Ä
		if(!Pattern.matches(noReg, noTemp)) {//Á¤±ÔÇ¥Çö½Ä°ú ÀÔ·Â°ªÀÇ Çü½ÄÀÌ ´Ù¸¥ °æ¿ì
			JOptionPane.showMessageDialog(this, "ÇÐ¹øÀº 4ÀÚ¸®ÀÇ ¼ýÀÚ·Î¸¸ ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			noTF.requestFocus();
			return;
		}
		
		int no=Integer.parseInt(noTemp);
		
		//ÇÐ¹øÀ» Àü´ÞÇÏ¿© STUDENT Å×ÀÌºí¿¡¼­ ÇØ´ç ÇÐ¹øÀÇ ÇÐ»ýÁ¤º¸¸¦ »èÁ¦ÇÏ´Â DAO Å¬·¡½ºÀÇ ¸Þ¼Òµå È£Ãâ
		int rows=StudentDAO.getDAO().deleteStudent(no);
		
		if(rows>0) {
			JOptionPane.showMessageDialog(this, rows+"¸íÀÇ ÇÐ»ýÁ¤º¸¸¦ »èÁ¦ ÇÏ¿´½À´Ï´Ù.");
			displayAllStudent();
		} else {
			JOptionPane.showMessageDialog(this, "»èÁ¦ÇÏ°íÀÚ ÇÏ´Â ÇÐ¹øÀÇ ÇÐ»ýÁ¤º¸°¡ ¾ø½À´Ï´Ù.");
		}

		initDisplay();
	}
}













