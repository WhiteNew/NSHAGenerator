package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import static application.GenerateAllxml.*;
import static application.SingleXML.*;
import static application.Scenario.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;

import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TAGenController {
	@FXML
	private Button help_bt;
	@FXML
	private Button exit_bt;
	@FXML
	private Button input_sv;
	@FXML
	private Button input_selfego;
	@FXML
	private Button input_person;
	@FXML
	private Button input_weather;
	@FXML
	private Button input_road;
	@FXML
	private Button input_tl;
	@FXML
	private Button input_driver;
	@FXML
	private Button state_sv;
	@FXML
	private Button state_sf;
	@FXML
	private Button state_person;
	@FXML
	private Button state_wea;
	@FXML
	private Button state_road;
	@FXML
	private Button state_tl;
	@FXML
	private Button state_scene;
	@FXML
	private Button tree_gen;
	@FXML
	private Button tree_show;
	@FXML
	private Button model_sv;
	@FXML
	private Button model_sf;
	@FXML
	private Button model_person;
	@FXML
	private Button model_wea;
	@FXML
	private Button model_road;
	@FXML
	private Button model_tf;
	@FXML
	private Button model_driver;
	@FXML
	private Button TA_gen;
	@FXML
	private Button TA_monitor;
	@FXML
	private TextArea result_show;
	@FXML
	private TextField Intial_driverState;
	@FXML
	private Button Qdbutton;
	// Event Listener on Button[#input_sv].onAction
	@FXML
  // public StringBuffer inputpath=new StringBuffer();
	
//	public String svpath=;
//	public String sfpath;
//	public String personpath;
//	public String weapath;
//	public String roadpath;
//	public String traffpath;
//	public String driverpath;
	public String svpath="D:\\TAgendata\\input\\othercar.txt";
	public String sfpath="D:\\TAgendata\\input\\currentcar.txt";
	public String personpath="D:\\TAgendata\\input\\pedestrian.txt";
	public String weapath="D:\\TAgendata\\input\\weather.txt";
	public String roadpath="D:\\TAgendata\\input\\road.txt";
	public String traffpath="D:\\TAgendata\\input\\trafficlight.txt";
	public String driverpath="D:\\TAgendata\\input\\driver.txt";
	   String[] sv=new String[10];
	   String[] sf=new String[10];
	   String[] person=new String[10];
	   String[] weatherInfo=new String[10]; 
	   String[] road=new String[10];
	   String[] trafficl=new String[10];
	   int[] drive =new int[10]; 
	   String[] scene=new String[10];
	   String tempfile_sv;
	   String tempfile_sf;
	   String tempfile_person;
	   String tempfile_wea;
	   String tempfile_tl;
	   String tempfile_road;
	//*************************************************************************input data***********************************
	public void openInput_sv(ActionEvent event) {     //ѡ���ܱ߳�������
	//	System.out.println("Please open the trainging data file,format like .txt");
		Stage Filestage = null;
		FileChooser fileChooser = new FileChooser();
	//	fileChooser.setTitle("Open Training Data File");
	//	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(Filestage);
		if(fileChooser.equals(null))
		{ String trainfilepath=null; }
		 svpath = selectedFile.getPath();
		if(svpath==null)
			svpath="�����ȷ���ļ�·��.....";
		System.out.println(svpath);
		
	//	inputpath.append(trainfilepath);
		//result_show.setText(inputpath.toString());
		result_show.appendText(svpath+"\n");
	}
	public void openInput_sf(ActionEvent event) {   
		
		Stage Filestage = null;
		FileChooser fileChooser = new FileChooser();
	//	fileChooser.setTitle("Open Training Data File");
	//	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(Filestage);
		if (event.getSource().equals(input_sv)) {
			 sfpath = selectedFile.getPath();
			 sfpath = selectedFile.getPath();
				System.out.println(sfpath);
			//	inputpath.append(trainfilepath);
				result_show.appendText(sfpath+"\n");
	        } else if (event.getSource().equals(input_selfego)) {
	        	 personpath = selectedFile.getPath();
	     		System.out.println(personpath);
	     	//	inputpath.append(trainfilepath);
	     		result_show.appendText(personpath+"\n");
	        }
	        else if (event.getSource().equals(input_person)) {
	        	 personpath = selectedFile.getPath();
	     		System.out.println(personpath);
	     	//	inputpath.append(trainfilepath);
	     		result_show.appendText(personpath+"\n");
	        }
	
	}
	public void openInput_person(ActionEvent event) {   
		Stage Filestage = null;
		FileChooser fileChooser = new FileChooser();
	//	fileChooser.setTitle("Open Training Data File");
	//	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(Filestage);
		 personpath = selectedFile.getPath();
		System.out.println(personpath);
	//	inputpath.append(trainfilepath);
		result_show.appendText(personpath+"\n");
	}
	public void openInput_wea(ActionEvent event) {   
		Stage Filestage = null;
		FileChooser fileChooser = new FileChooser();
	//	fileChooser.setTitle("Open Training Data File");
	//	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(Filestage);
		 weapath = selectedFile.getPath();
		System.out.println(weapath);
	//	inputpath.append(trainfilepath);
		result_show.appendText(weapath+"\n");
	}
	public void openInput_road(ActionEvent event) {   
		Stage Filestage = null;
		FileChooser fileChooser = new FileChooser();
	//	fileChooser.setTitle("Open Training Data File");
	//	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(Filestage);
		 roadpath = selectedFile.getPath();
		System.out.println(roadpath);
	//	inputpath.append(trainfilepath);
		result_show.appendText(roadpath+"\n");
	}
	public void openInput_traffic(ActionEvent event) {   
		Stage Filestage = null;
		FileChooser fileChooser = new FileChooser();
	//	fileChooser.setTitle("Open Training Data File");
	//	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(Filestage);
		 traffpath = selectedFile.getPath();
		System.out.println(traffpath);
	//	inputpath.append(trainfilepath);
		result_show.appendText(traffpath+"\n");
	}
	public void openInput_driver(ActionEvent event) {   
		Stage Filestage = null;
		FileChooser fileChooser = new FileChooser();
	//	fileChooser.setTitle("Open Training Data File");
	//	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(Filestage);
		 driverpath = selectedFile.getPath();
		System.out.println(driverpath);
	//	inputpath.append(trainfilepath);
		result_show.appendText(driverpath+"\n");
	}
	
	//*************************************************************************��������������*******************************************************
	public void QdriverButton(ActionEvent event) {   
	
		String temp=Intial_driverState.getText();
		System.out.println(temp);
		int focuslevel=Integer.parseInt(temp);
		for(int i=0;i<10;i++)
		{
			drive[i]=focuslevel;
		}
		
	}
	public void treeGen(ActionEvent event) throws IOException {
		
		System.out.println(svpath);
		System.out.println(sfpath);
		System.out.println(personpath);
		System.out.println(weapath);
		System.out.println(roadpath);
		System.out.println(traffpath);
		readdata1(svpath);
		readdata2(sfpath);
		readdata3(personpath);
		readdata4(weapath);
		readdata5(roadpath);
		readdata6(traffpath);
	    String writefilepath="D:\\TAgendata\\input\\TreeSequence.txt";
		File file =new File(writefilepath);
		Writer out =new FileWriter(file);
		for(int i =0;i<10;i++)
		{
			scene[i]="<("+sv[i]+"),"+"("+sf[i]+"),"+"("+person[i]+"),"+"("+weatherInfo[i]+"),"+"("+road[i]+"),"+"("+trafficl[i]+"),"+"("+drive[i]+")"+">";
			out.write(scene[i]+"\n");
		}

		out.close();

		   result_show.clear();
		   result_show.setText("success");
	}
   public void showTree(ActionEvent event) {
	   
	   for(int i =0;i<10;i++)
		{
			result_show.appendText(scene[i]+"\n");
		}
		

   }
	public 	void   readdata1(String p) {
		
		String tempath=p;
		 FileInputStream fsv = null;
		  InputStreamReader isr = null;
		  BufferedReader br = null; //���ڰ�װInputStreamReader,��ߴ������ܡ���ΪBufferedReader�л���ģ���InputStreamReaderû�С�
		  try {
		   String str = " ";
		   String str1 = " ";

		   fsv = new FileInputStream(tempath);// FileInputStream
		   // ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
		    isr = new InputStreamReader(fsv);// InputStreamReader ���ֽ���ͨ���ַ���������,
		    br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new InputStreamReader�Ķ���
		    int i=0;
		   while ((str = br.readLine()) != null&&i<10) {
		    sv[i] = str;
		    i++;
		   }
		  
		 
		   result_show.clear();
		   result_show.setText(str1);
		  } catch (FileNotFoundException e) {
		   System.out.println("�Ҳ���ָ���ļ�");
		  } catch (IOException e) {
		   System.out.println("��ȡ�ļ�ʧ��");
		  } finally {
		   try {
		     br.close();
		     isr.close();
		     fsv.close();
		    // �رյ�ʱ����ð����Ⱥ�˳��ر���󿪵��ȹر������ȹ�s,�ٹ�n,����m
		   } catch (IOException e) {
		    e.printStackTrace();
		   }
		  }
	}
public 	void   readdata2(String p) {
		
		String tempath=p;
		 FileInputStream fsv = null;
		  InputStreamReader isr = null;
		  BufferedReader br = null; //���ڰ�װInputStreamReader,��ߴ������ܡ���ΪBufferedReader�л���ģ���InputStreamReaderû�С�
		  try {
		   String str = " ";
		   String str1 = " ";

		   fsv = new FileInputStream(tempath);// FileInputStream
		   // ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
		    isr = new InputStreamReader(fsv);// InputStreamReader ���ֽ���ͨ���ַ���������,
		    br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new InputStreamReader�Ķ���
		    int i=0;
		   while ((str = br.readLine()) != null&&i<10) {
		    sf[i] = str;
		    i++;
		   }
		  
		  
		   result_show.clear();
		//   result_show.setText(str1);
		  } catch (FileNotFoundException e) {
		   System.out.println("�Ҳ���ָ���ļ�");
		  } catch (IOException e) {
		   System.out.println("��ȡ�ļ�ʧ��");
		  } finally {
		   try {
		     br.close();
		     isr.close();
		     fsv.close();
		    // �رյ�ʱ����ð����Ⱥ�˳��ر���󿪵��ȹر������ȹ�s,�ٹ�n,����m
		   } catch (IOException e) {
		    e.printStackTrace();
		   }
		  }
	}
public 	void   readdata3(String p) {
	
	String path=p;
	 FileInputStream fsv = null;
	  InputStreamReader isr = null;
	  BufferedReader br = null; //���ڰ�װInputStreamReader,��ߴ������ܡ���ΪBufferedReader�л���ģ���InputStreamReaderû�С�
	  try {
	   String str = " ";
	   String str1 = " ";

	   fsv = new FileInputStream(path);// FileInputStream
	   // ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
	    isr = new InputStreamReader(fsv);// InputStreamReader ���ֽ���ͨ���ַ���������,
	    br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new InputStreamReader�Ķ���
	    int i=0;
	   while ((str = br.readLine()) != null&&i<10) {
	    person[i] = str;
	    i++;
	   }
	  
	   System.out.println(str1);// ��ӡ��str1
	   result_show.clear();
	//   result_show.setText(str1);
	  } catch (FileNotFoundException e) {
	   System.out.println("�Ҳ���ָ���ļ�");
	  } catch (IOException e) {
	   System.out.println("��ȡ�ļ�ʧ��");
	  } finally {
	   try {
	     br.close();
	     isr.close();
	     fsv.close();
	    // �رյ�ʱ����ð����Ⱥ�˳��ر���󿪵��ȹر������ȹ�s,�ٹ�n,����m
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	  }
}
public 	void   readdata4(String p) {
	
	String path=p;
	 FileInputStream fsv = null;
	  InputStreamReader isr = null;
	  BufferedReader br = null; //���ڰ�װInputStreamReader,��ߴ������ܡ���ΪBufferedReader�л���ģ���InputStreamReaderû�С�
	  try {
	   String str = " ";
	   String str1 = " ";

	   fsv = new FileInputStream(path);// FileInputStream
	   // ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
	    isr = new InputStreamReader(fsv);// InputStreamReader ���ֽ���ͨ���ַ���������,
	    br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new InputStreamReader�Ķ���
	    int i=0;
	   while ((str = br.readLine()) != null&&i<10) {
	    weatherInfo[i] = str;
	    i++;
	   }
	  
	   System.out.println(str1);// ��ӡ��str1
	   result_show.clear();
	//   result_show.setText(str1);
	  } catch (FileNotFoundException e) {
	   System.out.println("�Ҳ���ָ���ļ�");
	  } catch (IOException e) {
	   System.out.println("��ȡ�ļ�ʧ��");
	  } finally {
	   try {
	     br.close();
	     isr.close();
	     fsv.close();
	    // �رյ�ʱ����ð����Ⱥ�˳��ر���󿪵��ȹر������ȹ�s,�ٹ�n,����m
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	  }
}
public 	void   readdata5(String p) {
	
	String path=p;
	 FileInputStream fsv = null;
	  InputStreamReader isr = null;
	  BufferedReader br = null; //���ڰ�װInputStreamReader,��ߴ������ܡ���ΪBufferedReader�л���ģ���InputStreamReaderû�С�
	  try {
	   String str = " ";
	   String str1 = " ";

	   fsv = new FileInputStream(path);// FileInputStream
	   // ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
	    isr = new InputStreamReader(fsv);// InputStreamReader ���ֽ���ͨ���ַ���������,
	    br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new InputStreamReader�Ķ���
	    int i=0;
	   while ((str = br.readLine()) != null&&i<10) {
	    road[i] = str;
	    i++;
	   }
	  
	   System.out.println(str1);// ��ӡ��str1
	   result_show.clear();
	//   result_show.setText(str1);
	  } catch (FileNotFoundException e) {
	   System.out.println("�Ҳ���ָ���ļ�");
	  } catch (IOException e) {
	   System.out.println("��ȡ�ļ�ʧ��");
	  } finally {
	   try {
	     br.close();
	     isr.close();
	     fsv.close();
	    // �رյ�ʱ����ð����Ⱥ�˳��ر���󿪵��ȹر������ȹ�s,�ٹ�n,����m
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	  }
}
public 	void   readdata6(String p) {
	
	String path=p;
	 FileInputStream fsv = null;
	  InputStreamReader isr = null;
	  BufferedReader br = null; //���ڰ�װInputStreamReader,��ߴ������ܡ���ΪBufferedReader�л���ģ���InputStreamReaderû�С�
	  try {
	   String str = " ";
	   String str1 = " ";

	   fsv = new FileInputStream(path);// FileInputStream
	   // ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
	    isr = new InputStreamReader(fsv);// InputStreamReader ���ֽ���ͨ���ַ���������,
	    br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new InputStreamReader�Ķ���
	    int i=0;
	   while ((str = br.readLine()) != null&&i<10) {
	    trafficl[i] = str;
	    i++;
	   }
	  
	   System.out.println(str1);// ��ӡ��str1
	   result_show.clear();
	//   result_show.setText(str1);
	  } catch (FileNotFoundException e) {
	   System.out.println("�Ҳ���ָ���ļ�");
	  } catch (IOException e) {
	   System.out.println("��ȡ�ļ�ʧ��");
	  } finally {
	   try {
	     br.close();
	     isr.close();
	     fsv.close();
	    // �رյ�ʱ����ð����Ⱥ�˳��ر���󿪵��ȹر������ȹ�s,�ٹ�n,����m
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	  }
}

//*********************************************************************״̬status judgment*************************************************

public void generateSV_State(ActionEvent event) throws IOException {
	
	String tempfile=Scenario.speedStatus(new File(svpath), "SVstatus");
	System.out.println(tempfile);
	result_show.setText("SV状态判定成功，文件路径为"+tempfile);
}
public void generateSF_tate(ActionEvent event) throws IOException {
	
	String tempfile=Scenario.speedStatus(new File(sfpath), "SFstatus");
	System.out.println(tempfile);
	result_show.setText("SF状态判定成功，文件路径为"+tempfile);
}
public void generatePerson_State(ActionEvent event) throws IOException {
	
	String tempfile=Scenario.speedStatus(new File(personpath), "Personstatus");
	System.out.println(tempfile);
	result_show.setText("Person状态判定成功，文件路径为"+tempfile);
}
public void generateTL_State(ActionEvent event) throws IOException {
	
	String tfile=Scenario.trafficLightStatus(new File(traffpath), "TraffStatus");
	System.out.println(tfile);
	result_show.setText("Traffic light 状态判定成功，文件路径为"+tfile);
}

public void generateRoad_State(ActionEvent event) throws IOException {
	
	String tfile=Scenario.roadStatus(new File(roadpath), "RoadStatus");
	result_show.setText("Road 状态判定成功，文件路径为"+tfile);
}
public void generateWeather_State(ActionEvent event) throws IOException {
	
	String tfile=Scenario.weatherStatus(new File(weapath), "WeatherStatus");
	result_show.setText("Weather 状态判定成功，文件路径为"+tfile);
}
public void generateALLFile_State(ActionEvent event) throws IOException {
	
	 tempfile_sv=Scenario.speedStatus(new File(svpath), "SVstatus");
	 tempfile_sf=Scenario.speedStatus(new File(sfpath), "SFstatus");
	 tempfile_person=Scenario.speedStatus(new File(personpath), "Personstatus");
	 tempfile_tl=Scenario.trafficLightStatus(new File(traffpath), "TraffStatus");
	 tempfile_road=Scenario.roadStatus(new File(roadpath), "RoadStatus");
	 tempfile_wea=Scenario.weatherStatus(new File(weapath), "WeatherStatus");
	
}


//*********************************************************************XML generate*************************************************

public void generateXML(ActionEvent event) throws IOException {
	
	 if (event.getSource().equals(model_sv)) {
        String tempfile1=SingleXML.getSingleXML(tempfile_sv);
    //    System.out.println(tempfile1);
        result_show.setText("模型生成成功，文件路径"+tempfile1);
       }
	 else if (event.getSource().equals(model_sf)) {
		 String tempfile2=SingleXML.getSingleXML(tempfile_sf);
		 System.out.println(tempfile2);
		 result_show.setText("模型生成成功，文件路径"+tempfile2);
       }
	 else if (event.getSource().equals(model_person)) {
		 String tempfile3=SingleXML.getSingleXML(tempfile_person);
		 System.out.println(tempfile3);
		 result_show.setText("模型生成成功，文件路径"+tempfile3);
       }
	 else if (event.getSource().equals(model_road)) {
		 String tempfile4=SingleXML.getSingleXML(tempfile_road);
		 System.out.println(tempfile4);
		 result_show.setText("模型生成成功，文件路径"+tempfile4);
       }
	 else if (event.getSource().equals(model_tf)) {
		 String tempfile5=SingleXML.getSingleXML(tempfile_tl);
		 System.out.println(tempfile5);
		 result_show.setText("模型生成成功，文件路径"+tempfile5);
       }
	 else if (event.getSource().equals(model_wea)) {
		 String tempfile6=SingleXML.getSingleXML(tempfile_wea);
		 System.out.println(tempfile6);
		 result_show.setText("模型生成成功，文件路径"+tempfile6);
       }
	 else if (event.getSource().equals(model_driver)) {
		 String tempfile7=SingleXML.getDriverXML();
		 System.out.println(tempfile7);
		 result_show.setText("模型生成成功，文件路径"+tempfile7);
       }
}
public void generateMonitorXML(ActionEvent event)throws IOException {

	 String tempfileMonitor=SingleXML.getMonitorXML();
	 System.out.println(tempfileMonitor);
	 result_show.setText("模型生成成功，文件路径"+tempfileMonitor);
}
public void generateALLXML(ActionEvent event) throws IOException {
	
	GenerateAllxml.generateALLfile();
	 result_show.setText("总模型生成成功！");
}

//****************************************************************************help exit******************************
public void helpButton() {
	result_show.clear();
	result_show.appendText("帮助手册：\n");
	result_show.appendText("1.实现步骤\n"+
	"1.1打开文件目录选择文件信息\n"+"1.2生成及查看场景特征树\n"+"1.3生成场景对象状态迁移序列\n"+"1.4生成基于UPPAAL SMC的时间自动机模型XML文件格式\n");
	result_show.appendText("2.注意文件源目录的问题，由于编码问题，目录中尽量不含中文");
	
	
}
public void ExitButton() {
	exit_bt.getScene().getWindow().getOnCloseRequest();
	System.exit(0);
	
}


}