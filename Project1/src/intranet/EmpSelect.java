package intranet;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yongsu
 */
public class EmpSelect extends javax.swing.JFrame {

    /**
     * Creates new form EmpSelect
     */
	EmpDAO eDao=new EmpDAO();
	DeptDAO dDao=new DeptDAO();
	int selectedDept, selectedEmp;
	int selectedDeptOrEmp;
	int selectedEmpId;
	String selectedEmpName;
	String selectedDeptName;
	ArrayList<EmpVO> empList=null; 
	ArrayList<DeptVO> deptList=null;
	Msg selected;

    public EmpSelect(Msg send) {
        initComponents();
        selected=send;
        
		// 부서테이블 가져와 보여주기
        deptList=dDao.listDept();
		showDeptTable(deptList,tabDeptList);
		
		// 사원테이블 가져와 보여주기
		empList=eDao.listEmp();
		showEmpTable(empList,tabEmpList);
		
		tabDeptList.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		// 1. 마우스로 누른 지점의 글번호를 알아내자.
        		selectedDept=tabDeptList.getSelectedRow();
        		selectedEmp=tabEmpList.getSelectedRow();
        	}
        });

    }
    
	public void reflashTable(int tabNumber) {
		switch(tabNumber) {
		case 0:
			deptList=dDao.listDept();
			showDeptTable(deptList,tabDeptList);
			break;
		case 1:
			empList=eDao.listEmp();
			showEmpTable(empList,tabEmpList);
			break;
		default:
		}
	}

    public void showDeptTable(ArrayList<DeptVO> arr, javax.swing.JTable tabList) {
    	// 5. DefaultTableModel 객체 생성해서 담기
    	// 6. table 모델 설정
    	Object[][] tableContents=new Object [arr.size()][5];
        for(int i=0;i<tableContents.length;i++) {
        	DeptVO msg=arr.get(i);
        	tableContents[i][0]=msg.getDeptNo();
        	tableContents[i][1]=msg.getdName();
		}
        String[] DeptTitle={ "부서번호", "부서명" };
		DefaultTableModel tableModel=new DefaultTableModel(tableContents,DeptTitle);
        
    	tabList.setModel(tableModel);
		tabList.setRowHeight(25);
    }
    
    public void showEmpTable(ArrayList<EmpVO> arr, javax.swing.JTable tabList) {
    	// 5. DefaultTableModel 객체 생성해서 담기
    	// 6. table 모델 설정
    	Object[][] tableContents=new Object [arr.size()][5];
        for(int i=0;i<tableContents.length;i++) {
        	EmpVO msg=arr.get(i);
        	tableContents[i][0]=msg.getdName();
        	tableContents[i][1]=msg.getRank();
        	tableContents[i][2]=msg.getEmpId();
        	tableContents[i][3]=msg.geteName();
        }
        String[] EmpTitle={ "부서명", "직급", "사원번호", "사원이름" };
		DefaultTableModel tableModel=new DefaultTableModel(tableContents,EmpTitle);
        
    	tabList.setModel(tableModel);
		tabList.setRowHeight(25);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mm_lbHeader = new javax.swing.JLabel();
        jComboBoxSel = new javax.swing.JComboBox<>();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jButtonSelect = new javax.swing.JButton();
        tbEmpList = new javax.swing.JTabbedPane();
        spDeptList = new javax.swing.JScrollPane();
        tabDeptList = new javax.swing.JTable();
        spEmpList = new javax.swing.JScrollPane();
        tabEmpList = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        mm_lbHeader.setFont(new java.awt.Font("굴림", 0, 18)); // NOI18N
        mm_lbHeader.setText("사원탐색");

        jComboBoxSel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "부서명", "사원번호", "사원이름" }));

        jButtonSearch.setText("검색");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jButtonSelect.setText("선택");
        jButtonSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBoxSel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSearch)
                        .addContainerGap(29, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(mm_lbHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mm_lbHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSelect, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxSel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabDeptList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "부서번호", "부서명"
            }
        ));
        spDeptList.setViewportView(tabDeptList);

        tbEmpList.addTab("부서 List", spDeptList);

        tabEmpList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "부서명", "직급", "이름"
            }
        ));
        spEmpList.setViewportView(tabEmpList);

        tbEmpList.addTab("사원 List", spEmpList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbEmpList, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbEmpList, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // ComboBox에서 선택된 항목 가져옴
    	int n=jComboBoxSel.getSelectedIndex();
    	
    	// 검색어 얻어오기
    	String keyword= jTextFieldSearch.getText();
    	// System.out.println(keyword);
    	// DB에서 서치결과 가져옴
    	ArrayList<EmpVO> arr=null;
    	int empid=0;
    	if(n==1) {
    		try {
    			empid=Integer.parseInt(keyword.trim());
    		} catch(NumberFormatException e) {
    			JOptionPane.showMessageDialog(this, "사원번호는 숫자를 입력하세요.");
    			return;
    		}
    		arr=eDao.findEmp(empid);
    	}
    	else arr=eDao.findEmp(n,keyword);
    	// EMPLIST테이블에 보여줌 (null 처리 추가), EMP Tab을 보여줌
    	// System.out.println(arr);
		if(arr.size()==0) {
			JOptionPane.showMessageDialog(this, "검색된 사원이 존재하지 않습니다.");
		} else {
			showEmpTable(arr,tabEmpList);
			tbEmpList.setSelectedIndex(1);
		}
		
    }                                             

    private void jButtonSelectActionPerformed(java.awt.event.ActionEvent evt) {                                              
		// 현재 선택된 탭(부서 or 사원) 확인하기
    	selectedDeptOrEmp = tbEmpList.getSelectedIndex();
//		// 선택한 글번호 가져오기
//		// 선택한 글번호의 부서명 또는 사원번호 가져오기
		int selectedRow;
		if (selectedDeptOrEmp == 0) {
			selectedRow = tabDeptList.getSelectedRow();
		} else {
			selectedRow = tabEmpList.getSelectedRow();
		}
		if(selectedRow==-1) {
			// 전체 사원 리스트를 보여주고 돌아감 
			JOptionPane.showMessageDialog(this, "검색할 내용을 입력하시고 검색 버튼을 누르세요.");
			return;
		}
		Object selectedValue;
		if (selectedDeptOrEmp == 0) {
			selectedValue = tabDeptList.getValueAt(selectedRow,1);
			selectedDeptName=(String) selectedValue;
			// System.out.println("Dept Name="+selectedDeptName);
			ArrayList<EmpVO> arr=eDao.findEmp(0, selectedDeptName);
			showEmpTable(arr,tabEmpList);
			tbEmpList.setSelectedIndex(1);
		} else {
			selectedValue = tabEmpList.getValueAt(selectedRow,2);
			selectedEmpId=(int) selectedValue;
			// System.out.println("Emp Id="+selectedEmpId);
			selected.selectedEmpId=selectedEmpId;
			String eName=(String)tabEmpList.getValueAt(selectedRow,3);
			selected.selectedEmpName=eName;
			selected.mw_tfReceiveId.setText(eName);
			// System.out.println("Emp Name="+eName);
			dispose();
			//System.exit(0);
		}
    }                                             

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmpSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmpSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmpSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmpSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new EmpSelect().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonSelect;
    private javax.swing.JComboBox<String> jComboBoxSel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JLabel mm_lbHeader;
    private javax.swing.JTabbedPane tbEmpList;
    private javax.swing.JScrollPane spDeptList;
    private javax.swing.JScrollPane spEmpList;
    private javax.swing.JTable tabDeptList;
    private javax.swing.JTable tabEmpList;
    // End of variables declaration                   
}
