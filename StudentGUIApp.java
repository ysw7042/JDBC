package xyz.itwill.student; 

/********************************************************
��    ��   �� : StudentGUIApp.java
��         �� : �л� ���� ���α׷�
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
	 * ������ ����
	 *********************************************/
	public StudentGUIApp() throws Exception {
		setTitle("�ߡߡ� �л� ���� ���α׷� �ߡߡ�");
		setSize(800, 400);

		Dimension dim = getToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2
				- getHeight() / 2);

		JPanel left = new JPanel();
		left.setLayout(new GridLayout(5, 1));

		JPanel pno = new JPanel();
		pno.add(new JLabel("��  ȣ"));
		pno.add(noTF = new JTextField(10));

		JPanel pname = new JPanel();
		pname.add(new JLabel("��  ��"));
		pname.add(nameTF = new JTextField(10));
		
		JPanel pbirthday = new JPanel();
		pbirthday.add(new JLabel("��  ��"));
		pbirthday.add(birthdayTF = new JTextField(10));
		
		JPanel pphone = new JPanel();
		pphone.add(new JLabel("��  ȭ"));
		pphone.add(phoneTF = new JTextField(10));

		JPanel paddress = new JPanel();
		paddress.add(new JLabel("��  ��"));
		paddress.add(addressTF = new JTextField(10));

		left.add(pno);
		left.add(pname);
		left.add(pphone);
		left.add(paddress);
		left.add(pbirthday);
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1, 5));

		bottom.add(addB = new JButton("��  ��"));
		addB.addActionListener(this);

		bottom.add(deleteB = new JButton("��  ��"));
		deleteB.addActionListener(this);
		
		bottom.add(updateB = new JButton("��  ��"));
		updateB.addActionListener(this);

		bottom.add(searchB = new JButton("��  ��"));
		searchB.addActionListener(this);
		
		bottom.add(cancelB = new JButton("��  ��"));
		cancelB.addActionListener(this);

		Object[] title={"�й�","�̸�","��ȭ��ȣ","�ּ�","�������"};
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
			if (c == addB) {//�߰� ��ư�� ���� ���
				if (cmd != ADD) {//ADD ���°� �ƴ� ���
					setEnable(ADD);//ADD ���·� Ȱ��ȭ					
				} else {//ADD ������ ���
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
			System.out.println("���� �߻� : " + e);
		}		
	}
	
	//STUDENT ���̺� ����� ��� �л������� �˻��Ͽ� JTable ���۳�Ʈ�� ����ϴ� �޼ҵ�
	public void displayAllStudent() {
		//STUDENT ���̺� ����� ��� �л������� �˻��Ͽ� ��ȯ�ϴ� DAO Ŭ������ �޼ҵ� ȣ��
		List<StudentDTO> studentList=StudentDAO.getDAO().selectAllStudentList();
		
		if(studentList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "����� �л������� �����ϴ�.");
			return;
		}
		
		//JTable.getModel() : TableModel �ν��Ͻ��� ��ȯ�ϴ� �޼ҵ�
		// => DefaultTableModel Ŭ������ ����� ��ü ����ȯ�Ͽ� ���������� ����
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		
		//���� JTable ���۳�Ʈ�� �����ϴ� ��� ���� �ϳ��� ����
		// => DefaultTableModel �ν��Ͻ��� ����� ��� ���� �����Ͽ� �ʱ�ȭ
		//DefaultTableModel.getRowCount() : DefaultTableModel �ν��Ͻ��� �హ���� ��ȯ�ϴ� �޼ҵ�
		for(int i=model.getRowCount();i>0;i--) {
			//DefaultTableModel.removeRow(int rowIndex) : ���ȣ�� ���޹޾� DefaultTableModel
			//�ν��Ͻ��� �ش� ���� �����ϴ� �޼ҵ�
			model.removeRow(0);
		}
		
		//JTable ���۳�Ʈ�� ��ȯ���� �л������� ���
		// => �˻��� �л������� DefaultTableModel �ν��Ͻ��� �ϳ��� ������ �߰��Ͽ� ���
		for(StudentDTO student:studentList) {
			//Vector �ν��Ͻ��� �����Ͽ� StudentDTO �ν��Ͻ��� �ʵ尪�� ��ҷ� �߰�
			Vector<Object> rowData=new Vector<Object>();
			rowData.add(student.getNo());
			rowData.add(student.getName());
			rowData.add(student.getPhone());
			rowData.add(student.getAddress());
			rowData.add(student.getBirthday());
			//DefaultTableModel.addRow(Vector rowData) : Vector �ν��Ͻ��� ���޹޾�
			//DefaultTableModel �ν��Ͻ��� ������ �߰��ϴ� �޼ҵ�
			model.addRow(rowData);
		}
	}
	
	//JTextField ���۳�Ʈ�� �Էµ� �л������� �����޾� STUDENT ���̺� �����ϴ� �޼ҵ�
	public void addStudent() {
		String noTemp=noTF.getText();
		
		if(noTemp.equals("")) {//�Է°��� ���� ���
			JOptionPane.showMessageDialog(this, "�й��� �ݵ�� �Է��� �ּ���.");
			noTF.requestFocus();//�Է������� �̵��ϴ� �޼ҵ�
			return;
		}
		
		String noReg="\\d{4}";//����ǥ����
		if(!Pattern.matches(noReg, noTemp)) {//����ǥ���İ� �Է°��� ������ �ٸ� ���
			JOptionPane.showMessageDialog(this, "�й��� 4�ڸ��� ���ڷθ� �Է��� �ּ���.");
			noTF.requestFocus();
			return;
		}
		
		int no=Integer.parseInt(noTemp);
		
		//�й��� �����Ͽ� STUDENT ���̺� ����� �ش� �п��� �л������� �˻��Ͽ� ��ȯ�ϴ�
		//DAO Ŭ������ �޼ҵ� ȣ��
		// => NULL ��ȯ : �л����� �̰˻� - �ش� �й��� �л����� ������
		// => StudentDTO �ν��Ͻ� ��ȯ : �л����� �˻� - �ش� �й��� �л����� ����
		if(StudentDAO.getDAO().selectNoStudent(no)!=null) {
			JOptionPane.showMessageDialog(this, "�̹� ������� �й��Դϴ�. �ٽ� �Է��� �ּ���.");
			noTF.requestFocus();
			return;
		}
		
		String name=nameTF.getText();
		
		if(name.equals("")) {//�Է°��� ���� ���
			JOptionPane.showMessageDialog(this, "�̸��� �ݵ�� �Է��� �ּ���.");
			nameTF.requestFocus();//�Է������� �̵��ϴ� �޼ҵ�
			return;
		}
		
		String nameReg="[��-�R]{2,7}";//����ǥ����
		if(!Pattern.matches(nameReg, name)) {//����ǥ���İ� �Է°��� ������ �ٸ� ���
			JOptionPane.showMessageDialog(this, "�̸��� 2~5 ������ �ѱ۸� �Է��� �ּ���.");
			nameTF.requestFocus();
			return;
		}
		
		String phone=phoneTF.getText();
		
		if(phone.equals("")) {//�Է°��� ���� ���
			JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� �ݵ�� �Է��� �ּ���.");
			phoneTF.requestFocus();//�Է������� �̵��ϴ� �޼ҵ�
			return;
		}
		
		String phoneReg="(01[016789])-\\d{3,4}-\\d{4}";//����ǥ����
		if(!Pattern.matches(phoneReg, phone)) {//����ǥ���İ� �Է°��� ������ �ٸ� ���
			JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� ���Ŀ� �°� �Է��� �ּ���.");
			phoneTF.requestFocus();
			return;
		}
		
		String address=addressTF.getText();
		
		if(address.equals("")) {//�Է°��� ���� ���
			JOptionPane.showMessageDialog(this, "�ּҸ� �ݵ�� �Է��� �ּ���.");
			addressTF.requestFocus();//�Է������� �̵��ϴ� �޼ҵ�
			return;
		}
		
		String birthday=birthdayTF.getText();
		
		if(birthday.equals("")) {//�Է°��� ���� ���
			JOptionPane.showMessageDialog(this, "��������� �ݵ�� �Է��� �ּ���.");
			birthdayTF.requestFocus();//�Է������� �̵��ϴ� �޼ҵ�
			return;
		}
		
		String birthdayReg="(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";//����ǥ����
		if(!Pattern.matches(birthdayReg, birthday)) {//����ǥ���İ� �Է°��� ������ �ٸ� ���
			JOptionPane.showMessageDialog(this, "��������� ���Ŀ� �°� �Է��� �ּ���.");
			birthdayTF.requestFocus();
			return;
		}
		
		//DTO �ν��Ͻ��� �����ϰ� ���۳�Ʈ�� �Է°����� �ʵ尪 ����
		StudentDTO student=new StudentDTO();
		student.setNo(no);
		student.setName(name);
		student.setPhone(phone);
		student.setAddress(address);
		student.setBirthday(birthday);
		
		//�л������� �����Ͽ� STUDENT ���̺� �����ϴ� DAO Ŭ������ �޼ҵ� ȣ��
		int rows=StudentDAO.getDAO().insertStudent(student);
		
		JOptionPane.showMessageDialog(this, rows+"���� �л������� ���� �Ͽ����ϴ�.");
		
		displayAllStudent();
		initDisplay();
	}
	
	//JTextField ���۳�Ʈ�� �Էµ� �й��� �����޾� STUDENT ���̺� ����� �ش� �й���
	//�л������� �����ϴ� �޼ҵ�
	public void removeStudent() {
		String noTemp=noTF.getText();
		
		if(noTemp.equals("")) {//�Է°��� ���� ���
			JOptionPane.showMessageDialog(this, "�й��� �ݵ�� �Է��� �ּ���.");
			noTF.requestFocus();//�Է������� �̵��ϴ� �޼ҵ�
			return;
		}
		
		String noReg="\\d{4}";//����ǥ����
		if(!Pattern.matches(noReg, noTemp)) {//����ǥ���İ� �Է°��� ������ �ٸ� ���
			JOptionPane.showMessageDialog(this, "�й��� 4�ڸ��� ���ڷθ� �Է��� �ּ���.");
			noTF.requestFocus();
			return;
		}
		
		int no=Integer.parseInt(noTemp);
		
		//�й��� �����Ͽ� STUDENT ���̺��� �ش� �й��� �л������� �����ϴ� DAO Ŭ������ �޼ҵ� ȣ��
		int rows=StudentDAO.getDAO().deleteStudent(no);
		
		if(rows>0) {
			JOptionPane.showMessageDialog(this, rows+"���� �л������� ���� �Ͽ����ϴ�.");
			displayAllStudent();
		} else {
			JOptionPane.showMessageDialog(this, "�����ϰ��� �ϴ� �й��� �л������� �����ϴ�.");
		}

		initDisplay();
	}
}













