package edu.java.lab2;
/** 
 * @author ������*/

//����������� ����������� ���������
import java.awt.BorderLayout;
import java.awt.FileDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import java.awt.event.*;
import java.lang.Math;
import java.util.HashMap;
import java.io.*;

public class InterForm {
	//������ ����������
	/** 
	 * ����� ����������, ������������ 
	 * ��� ���������� ����� �� �����
	 * ��� ���������� ������
	 * */
	private class NotEnoughDiskSpace extends Exception
	{
		public NotEnoughDiskSpace()
		{
			super("������������ ����� �� ����� ��� �������� ������.");
		};
	};
	
	/**
	 * ����� ����������, ������������,
	 * ����� ��� �������� ���������� ������
	 * �� ������ ����� ��������������� ������.  
	 * */
	private class NotFound extends Exception
	{
		public NotFound()
		{
			super("�� ������� ������ �� �������� ����������.");
		};
	};	
	
	//���������� ����������� �����������
	private JFrame InterForm;
	private DefaultTableModel Model;
	private JButton Open;
	private JButton Save;
	private JButton New;
	private JButton Edit;
	private JButton Delete;
	private JToolBar ToolBar;
	private JScrollPane Scroll;
	private JTable Groups; 
	private JComboBox Year;
	private JTextField GroupName;
	private JButton Filter;
	private JButton Report;
	
	private void CheckDiskSpace() throws NotEnoughDiskSpace
	{
		//if(Math.random() < 0.5)
			///throw new NotEnoughDiskSpace();
	};
	
	private void FindByParams() throws NotFound
	{
		//if(Math.random() >= 0.5)
			//throw new NotFound();
	};
	
	//����� �������� ����
	public void Show()
	{
		InterForm = new JFrame("�������� ����������� �����");
		InterForm.setSize(500, 300);
		InterForm.setLocation(100, 100);
		InterForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�������� ������ � ������������ ������
		Open = new JButton(new ImageIcon("./img/open.png"));
		Save = new JButton(new ImageIcon("./img/save.png"));
		New = new JButton(new ImageIcon("./img/new.png"));
		Edit = new JButton(new ImageIcon("./img/edit.png"));
		Delete = new JButton(new ImageIcon("./img/delete.png"));
		Report = new JButton(new ImageIcon("./img/report.png"));
		
		//��������� ��������� ��� ������
		Open.setToolTipText("�������");
		Save.setToolTipText("���������");
		New.setToolTipText("����� ������");
		Edit.setToolTipText("������������� ������");
		Delete.setToolTipText("������� ������");
		Report.setToolTipText("������� �����");
		
		//���������� ������ �� ������ ������������
		ToolBar = new JToolBar("������ ������������");
		ToolBar.add(Open);
		ToolBar.add(Save);
		ToolBar.add(New);
		ToolBar.add(Edit);
		ToolBar.add(Delete);
		ToolBar.add(Report);
		
		//���������� ������ ������������
		InterForm.setLayout(new BorderLayout());
		InterForm.add(ToolBar, BorderLayout.NORTH);
		
		//�������� ������� � �������
		String[] Columns = {"������", "��� ���������", "��������� � ���-������"};
		String[][] Data = {{"Paul Van Dyk", "1994", "105"}, {"ATB", "1998", "54"}};
		Model = new DefaultTableModel(Data, Columns);
		Groups = new JTable(Model);
		Scroll = new JScrollPane(Groups);		
		
		//���������� ������� � �������
		InterForm.add(Scroll, BorderLayout.CENTER);
		
		//���������� ����������� ������
		Year = new JComboBox(new String[] {"���", "1994", "1998"});		
		GroupName = new JTextField("�������� ������");
		Filter = new JButton("�����");
		
		//���������� ����������� �� ������
		JPanel FilterPanel = new JPanel();
		FilterPanel.add(GroupName);
		FilterPanel.add(Year);
		FilterPanel.add(Filter);		
		
		//���������� ������ ������ ����� ����
		InterForm.add(FilterPanel, BorderLayout.SOUTH);
		
		//������������ �������� �����
		InterForm.setVisible(true);
		
		//���������� ����������
		New.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/**����� ��������� � ����� �� ������� ������ "����� ������". */
				String group = "Doom";
				String year = "1993";
				String chart = "12";
				Model.addRow(new String[] {group, year, chart});
			}});
		
		Filter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/**����� ��������� � ����� �� ������� ������ "�����". */
				try
				{
					FindByParams();
				}
				catch(NotFound ex)
				{
					JOptionPane.showMessageDialog(InterForm, "�� ������� ������ � ��������� ����������� ������.");
				};
				JOptionPane.showMessageDialog(InterForm, "�������� ������� �� ������ \"�����\".");
			}});
		
		Year.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/**����� ��������� � ����� �� ����� ������ � ������ "���". */
				JOptionPane.showMessageDialog(InterForm, "�������� ������ ������ ��������������� ������ \"���\".");
			}});
		
		Open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				//�������� ����������� ���� ������ ����� ��� �������� ������
				FileDialog open = new FileDialog(InterForm, "�������� ������", FileDialog.LOAD);
				open.setFile("*.xml");
				open.setVisible(true);
				
				//��������, ������� �� ����
				String FileName = open.getDirectory() + open.getFile();
				if(FileName == null)
					return;
				
				//������� �������
				int rows = Model.getRowCount();
				for(int i = 0; i < rows; i++)
					Model.removeRow(0);
				
				Document doc = null;
				
				//��������� ������
				try
				{
					//�������� ������� ���������
					DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					
					//������ ��������� �� �����
					doc = dBuilder.parse(new File(FileName));
					
					//������������ ���������
					doc.getDocumentElement().normalize();
				}
				
				catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				NodeList nlGames = doc.getElementsByTagName("game");
				
				//���������� ������ � �������
				for(int temp = 0; temp < nlGames.getLength(); temp++) {
					Node element = nlGames.item(temp);
					NamedNodeMap attrs = element.getAttributes();
					String name = attrs.getNamedItem("name").getNodeValue();
					String year = attrs.getNamedItem("year").getNodeValue();
					String chart = attrs.getNamedItem("chart").getNodeValue();
					
					Model.addRow(new String[] {name, year, chart});
					
				}
				
			}});
		
		Save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//�������� ����������� ���� ������ ����� ��� ���������� ������
				FileDialog open = new FileDialog(InterForm, "���������� ������", FileDialog.SAVE);
				open.setFile("*.xml");
				open.setVisible(true);
				
				//��������, ������� �� ����
				String FileName = open.getDirectory() + open.getFile();
				if(FileName == null)
					return;
				
				DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = null;
				try {
					builder = f.newDocumentBuilder();
				} catch (ParserConfigurationException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}				
				Document doc = null;
				try {
					doc = builder.parse(FileName);
				} catch (SAXException | IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				//��������� ������
				Node gamelist = doc.getDocumentElement();
				
				for(int i = 0; i < Model.getRowCount(); i++) {
					Element game = doc.createElement("game");
					gamelist.appendChild(game);
					game.setAttribute("name", (String)Model.getValueAt(i, 0));
					game.setAttribute("year", (String)Model.getValueAt(i, 1));
					game.setAttribute("chart", (String)Model.getValueAt(i, 2));
				}
				
				try {
					Transformer trans = TransformerFactory.newInstance().newTransformer();
					trans.setOutputProperty(OutputKeys.METHOD, "xml");
					trans.setOutputProperty(OutputKeys.INDENT, "yes");
					
					trans.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(FileName)));
				}
				
				catch(TransformerConfigurationException e1)	{ 
					e1.printStackTrace();
				} 
				
				catch (TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				
				
			}});
		
		//���������� �����
		Report.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				try {
					//�������� ����������� ���� ������ ����� ��� ���������� ������
					FileDialog open = new FileDialog(InterForm, "�������� ������", FileDialog.LOAD);
					open.setFile("*.xml");
					open.setVisible(true);
					
					//��������, ������� �� ����
					String FileName = open.getDirectory() + open.getFile();
					if(FileName == null)
						return;
					
					//�������� ��������� XML-������					
					//�������� ������� ���������
					DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					
					//������ ��������� �� �����					
					JRXmlDataSource ds = new JRXmlDataSource(FileName, "/gamelist/game");
					
					//�������� ������ �� ���� �������
					JasperReport jasperReport = JasperCompileManager.compileReport("./report/report2.jrxml");
					
					//���������� ������ �������
					JasperPrint print = JasperFillManager.fillReport(jasperReport, new HashMap(), ds);
					JRExporter exporterpdf = new JRPdfExporter();
					JRExporter exporterhtml = new JRHtmlExporter();
					
					//������� ����� ����� ��� �������� ������
					exporterpdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "./report/autoreport2.pdf");
					exporterhtml.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "./report/autoreport2.html");
					
					//����������� ������ � ������
					exporterpdf.setParameter(JRExporterParameter.JASPER_PRINT, print);
					exporterhtml.setParameter(JRExporterParameter.JASPER_PRINT, print);
					
					//�������� ������ � �������� �������
					exporterpdf.exportReport();
					exporterhtml.exportReport();
				}
			catch(JRException e)
					{e.printStackTrace();} 
				catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}			
		});
	};	
	
	public static void main(String[] args)
	{
		//�������� � ����������� �������� �����
		new InterForm().Show();
	};
};
