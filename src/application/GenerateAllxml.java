package application;

import javafx.scene.effect.Light;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GenerateAllxml {
    public static int Short = 45;
    public static int Distance = -100;
    public static int initX = -892;
    public static int initY = -671;

    static String[] node = new String[20];
    static String[] time2 = new String[20];
    static int[] poiX = new int[20];
    static int[] poiY = new int[20];

    public static void getAllFile(String path, ArrayList<String> filename, ArrayList<String> title){
        File file = new File(path);
        File [] files = file.listFiles();
        String [] names = file.list();
        if(names != null){
            String [] completNames = new String[names.length];
            for(int i=0;i<names.length;i++){
                completNames[i]=path+names[i];
            }
            filename.addAll(Arrays.asList(completNames));
            title.addAll(Arrays.asList(names));
        }
    }

    public static String AddInit(){
        String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<!DOCTYPE nta PUBLIC '-//Uppaal Team//DTD Flat System 1.1//EN' 'http://www.it.uu.se/research/group/darts/uppaal/flat-1_2.dtd'>\n" +
                "<nta>\n"+
                Adddeclaration("clock clk;\n" +
                        "int n = 0;  \n" +
                                "int focus=0;" +
                                "broadcast chan safe;\n" +
                                "broadcast chan unsafe;\n" +
                                "\n" +
                                "broadcast chan def[2];\n" +
                                "\n" +
                                "bool danger;" +
                        "int svx,svy; //# 自身车辆运动速度\n" +
                        "int cvx,cvy;  //周边车辆v\n" +
                        "int sx,sy; //自身车辆位置\n" +
                        "int cx,cy;  //周边车辆位置\n" +
                        "int ca;\n" +
                        "int sa;\n" +
                        "int distance;");
        return temp;
    }

    public static String Addlocation(int poi){
        String temp = "\t<location id = \"" + node[poi] + "\">\n" +
                "\t\t<name>" + node[poi] + "</name>\n" +
                "\t</location>\n";
        return temp;
    }

    public static String AddlocationInit(){
        String temp = "\t<location id = \"id0\">\n" +
                "\t\t<name>"+ "Initial" +"</name>\n" +
                "\t</location>\n";
        return temp;
    }

    public static String AddlocationFinal(int poi){
        String temp = "\t<location id = \"id" + "final" + "\">\n" +
                "\t\t<name>" + "final" + "</name>\n" +
                "\t</location>\n";
        return temp;
    }

    public static String Addtemplate(String name){
        String temp = "<template>\n" +
                "\t<name>"+ name + "</name>\n";
        return temp;
    }

    public static String Endtemplate(){
        return "</template>\n";
    }

    public static String AddtransitionSpeed(int poi){
        String temp = "\t<transition>\n" +
                "\t\t<source ref = \"" + node[poi] + "\"/>\n" +
                "\t\t<target ref = \"" + node[poi] + "\"/>\n" +
                "\t\t<label kind=\"guard\">clk&gt;" + Sum(time2, poi) + "&amp;&amp;clk1==" + 1 +"</label>\n" +
                "\t\t<label kind=\"assignment\">clk1=0,laneKeep()</label>\n" +
                "\t</transition>\n";
        return temp;
    }

    public static String Addtransition(int poi){
        String temp = "\t<transition>\n" +
                "\t\t<source ref = \"" + node[poi] + "\"/>\n" +
                "\t\t<target ref = \"" + node[poi] + "\"/>\n" +
                "\t\t<label kind=\"guard\">clk&gt;" + Sum(time2, poi) + "&amp;&amp;clk1==" + 1 +"</label>\n" +
                "\t\t<label kind=\"assignment\">clk1=0</label>\n" +
                "\t</transition>\n";
        return temp;
    }

    public static String Adddeclaration(String declaration){
        String temp = "<declaration>\n" +
                declaration +"\n" +
                "</declaration>\n";
        return temp;
    }

    public static int Sum(String[] array, int tag){
        int result = 0;
        for(int i = 0; i < tag; i++){
            result += Integer.parseInt(array[i]);
        }
        return result;
    }

    public static void createGenerate() throws IOException {
        Scenario scenario = new Scenario();
        String currentcarStatus = scenario.speedStatus(new File("/Users/xirui.yang/Documents/graduate/input/currentcar.txt"), "currentcar");
        String othercarStatus = scenario.speedStatus(new File("/Users/xirui.yang/Documents/graduate/input/othercar.txt"), "othercar");
        String pedestrianStatus = scenario.speedStatus(new File("/Users/xirui.yang/Documents/graduate/input/pedestrian.txt"), "pedestrian");
        String weatherStatus = scenario.weatherStatus(new File("/Users/xirui.yang/Documents/graduate/input/weather.txt"), "weather");
        String trafficlightStatus = scenario.trafficLightStatus(new File("/Users/xirui.yang/Documents/graduate/input/trafficlight.txt"), "trafficlight");
        String roadStatus = scenario.roadStatus(new File("/Users/xirui.yang/Documents/graduate/input/road.txt"), "road");
    }

    public static void createSingleFile(File pathname) throws IOException {
        SingleXML singleXML = new SingleXML();
        String temp = singleXML.getSingleXML(pathname.getAbsolutePath());
    }

    public static void generateALLfile( ) throws IOException {

        for(int i = 0; i < poiX.length; i++){
            poiX[i] = (- 892) + Distance * (i+1);
            poiY[i] = (- 671) + Distance * (i+1);
        }

        //outStream
        File filepath1 = new File("D:\\TAgendata\\output\\NTA.xml");
        if(!filepath1.exists()){
            filepath1.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(filepath1.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String initial = AddInit();
        bufferedWriter.append(initial);

	// read from file
        ArrayList<String> listFileName = new ArrayList<String>();
        ArrayList<String> title = new ArrayList<String>();
        int num = 0;
     //   createGenerate();
        SingleXML driverxx = new SingleXML();
        driverxx.getDriverXML();
        getAllFile("D:\\TAgendata\\status\\", listFileName, title);
        for(String s:listFileName){
            createSingleFile(new File(s));
            FileInputStream fileInputStream = null;
            fileInputStream = new FileInputStream(s);
            InputStreamReader reader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String temp = "";
            String[] str;

            //System.out.println(str[0]+" "+str[1]);
            //

            int count = 0;
            while((temp = bufferedReader.readLine())!= null){
                str = temp.split(" ");
                node[count] = str[0];
                time2[count] = str[1];
                System.out.println(s+" " +node[count] +" "+time2[count]);
                count++;
            }
            bufferedReader.close();

            String tempName = title.get(num).substring(0,1).toUpperCase()+title.get(num).substring(1, title.get(num).length()-4);
            bufferedWriter.append(Addtemplate(tempName));
            bufferedWriter.newLine();
            System.out.println("this is s = "+tempName);
            num++;
            if(tempName.equals("SFstatus") || tempName.equals("SVstatus") || tempName.equals("Personstatus")){
                bufferedWriter.append(Adddeclaration("clock clk1;\n" + "//初始化函数 " + "\n" +
                        "void initialState()  \n" +
                        "{ \n" +
                        "  danger=false;\n" +
                        "  sx=10;\n" +
                        "  sy=100;\n" +
                        "  svx=0;\n" +
                        "  svy=20;\n" +
                        "  sa=0;\n" +
                        "}\n" +
                        "void laneKeep()\n" +
                        "{\n" +
                        "  sx=10;\n" +
                        "  sy=sy+svy;\n" +
                        "  \n" +
                        "}\n"));
            } else {
                bufferedWriter.append(Adddeclaration("clock clk1;\n"));
            }
            bufferedWriter.newLine();
            //create the node location
            bufferedWriter.append(AddlocationInit());
            bufferedWriter.newLine();
            for(int i = 0; i < count; i++){
                bufferedWriter.append(Addlocation(i));
                bufferedWriter.newLine();
            }
            if(!tempName.equals("TraffStatus"))
                bufferedWriter.append(AddlocationFinal(count+1));
            bufferedWriter.newLine();
            bufferedWriter.append("\t<init ref = \"" + "id0" + "\"/>\n");
            bufferedWriter.newLine();

            // create node transition
            for(int i = 0; i < count; i++){
                if(Integer.parseInt(time2[i]) >= 2){
                	   if(tempName.equals("SFstatus") || tempName.equals("SVstatus") || tempName.equals("Personstatus"))
                        bufferedWriter.append(AddtransitionSpeed(i));
                    else {
                        bufferedWriter.append(Addtransition(i));
                    }
                    bufferedWriter.newLine();
                }
            }
            int loop = 0;
            while(loop < count){
                if(loop == 0){
                	   if(tempName.equals("SFstatus") || tempName.equals("SVstatus") || tempName.equals("Personstatus"))
                        bufferedWriter.append("\t<transition>\n" +
                            "\t\t<source ref = \"" + "id0" + "\"/>\n" +
                            "\t\t<target ref = \"" + node[0] + "\"/>\n" +
                            "\t\t<label kind=\"assignment\">initialState(),clk=0</label>\n" +
                            "\t</transition>");
                    else {
                        bufferedWriter.append("\t<transition>\n" +
                                "\t\t<source ref = \"" + "id0" + "\"/>\n" +
                                "\t\t<target ref = \"" + node[0] + "\"/>\n" +
                                "\t\t<label kind=\"assignment\">clk=0</label>\n" +
                                "\t</transition>");
                    }
                }
                if(node[loop+1] != null){
                    bufferedWriter.append("\t<transition>\n" +
                            "\t\t<source ref = \"" + node[loop] + "\"/>\n" +
                            "\t\t<target ref = \"" + node[loop+1] + "\"/>\n" +
                            "\t\t<label kind=\"guard\">clk==" + Sum(time2, loop) + "&amp;&amp;clk1==" + time2[loop] +"</label>\n" +
                            "\t</transition>");
                }
                bufferedWriter.newLine();
                loop++;
            }

            if(tempName.equals("TraffStatus")){
                bufferedWriter.append("\t<transition>\n" +
                        "\t\t<source ref = \"" + node[loop-1] + "\"/>\n" +
                        "\t\t<target ref = \"" + node[0] + "\"/>\n" +
                        "\t</transition>");
                bufferedWriter.newLine();
            } else {
                bufferedWriter.append("\t<transition>\n" +
                        "\t\t<source ref = \"" + node[loop-1] + "\"/>\n" +
                        "\t\t<target ref = \"id" + "final" + "\"/>\n" +
                        "\t</transition>");
                bufferedWriter.newLine();
            }

            bufferedWriter.append(Endtemplate());

            node = new String[20];
            time2 = new String[20];

        }
        bufferedWriter.append("\t<template>\n" +
                "\t\t<name>Driver</name>\n" +
                "\t\t<declaration>clock dt; </declaration>\n" +
                "\t\t<location id=\"id4\" x=\"-340\" y=\"187\">\n" +
                "\t\t\t<name x=\"-382\" y=\"187\">f50</name>\n" +
                "\t\t\t<label kind=\"invariant\" x=\"-350\" y=\"204\">dt&lt;=1</label>\n" +
                "\t\t</location>\n" +
                "\t\t<location id=\"id5\" x=\"51\" y=\"229\">\n" +
                "\t\t\t<name x=\"102\" y=\"246\">f25</name>\n" +
                "\t\t\t<label kind=\"invariant\" x=\"41\" y=\"246\">dt&lt;=1</label>\n" +
                "\t\t</location>\n" +
                "\t\t<location id=\"id6\" x=\"204\" y=\"34\">\n" +
                "\t\t\t<name x=\"221\" y=\"17\">f0</name>\n" +
                "\t\t\t<label kind=\"invariant\" x=\"194\" y=\"51\">dt&lt;=1</label>\n" +
                "\t\t</location>\n" +
                "\t\t<location id=\"id7\" x=\"-357\" y=\"-76\">\n" +
                "\t\t\t<name x=\"-400\" y=\"-85\">f75</name>\n" +
                "\t\t\t<label kind=\"invariant\" x=\"-367\" y=\"-59\">dt&lt;=1</label>\n" +
                "\t\t</location>\n" +
                "\t\t<location id=\"id8\" x=\"-127\" y=\"-161\">\n" +
                "\t\t\t<name x=\"-110\" y=\"-187\">f100</name>\n" +
                "\t\t\t<label kind=\"invariant\" x=\"-187\" y=\"-187\">dt&lt;=1</label>\n" +
                "\t\t</location>\n" +
                "\t\t<location id=\"id9\" x=\"-127\" y=\"-238\">\n" +
                "\t\t\t<name x=\"-137\" y=\"-272\">start</name>\n" +
                "\t\t\t<urgent/>\n" +
                "\t\t</location>\n" +
                "\t\t<branchpoint id=\"id10\" x=\"17\" y=\"110\">\n" +
                "\t\t</branchpoint>\n" +
                "\t\t<branchpoint id=\"id11\" x=\"-119\" y=\"221\">\n" +
                "\t\t</branchpoint>\n" +
                "\t\t<branchpoint id=\"id12\" x=\"93\" y=\"-59\">\n" +
                "\t\t</branchpoint>\n" +
                "\t\t<branchpoint id=\"id13\" x=\"-272\" y=\"17\">\n" +
                "\t\t</branchpoint>\n" +
                "\t\t<branchpoint id=\"id14\" x=\"-153\" y=\"-102\">\n" +
                "\t\t</branchpoint>\n" +
                "\t\t<init ref=\"id9\"/>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id12\"/>\n" +
                "\t\t\t<target ref=\"id7\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-347\" y=\"-38\">focus=75,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-229\" y=\"-51\">2</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id12\"/>\n" +
                "\t\t\t<target ref=\"id5\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"68\" y=\"97\">focus=25,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"68\" y=\"114\">2</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id12\"/>\n" +
                "\t\t\t<target ref=\"id4\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-178\" y=\"102\">focus=50,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-204\" y=\"145\">7</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id12\"/>\n" +
                "\t\t\t<target ref=\"id8\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-76\" y=\"-127\">focus=100,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-25\" y=\"-110\">3</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id12\"/>\n" +
                "\t\t\t<target ref=\"id6\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"102\" y=\"-34\">focus=0,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"170\" y=\"-34\">86</label>\n" +
                "\t\t\t<nail x=\"153\" y=\"-51\"/>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id10\"/>\n" +
                "\t\t\t<target ref=\"id5\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-24\" y=\"140\">focus=25,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"93\" y=\"187\">69</label>\n" +
                "\t\t\t<nail x=\"102\" y=\"170\"/>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id10\"/>\n" +
                "\t\t\t<target ref=\"id6\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-24\" y=\"72\">focus=0,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"8\" y=\"93\">2</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id10\"/>\n" +
                "\t\t\t<target ref=\"id4\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-204\" y=\"178\">focus=50,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-178\" y=\"170\">13</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id11\"/>\n" +
                "\t\t\t<target ref=\"id4\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-322\" y=\"187\">focus=50,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-297\" y=\"212\">87</label>\n" +
                "\t\t\t<nail x=\"-255\" y=\"221\"/>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id11\"/>\n" +
                "\t\t\t<target ref=\"id5\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-76\" y=\"204\">focus=25,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-59\" y=\"187\">2</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id11\"/>\n" +
                "\t\t\t<target ref=\"id6\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-178\" y=\"51\">focus=0,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-111\" y=\"59\">1</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id11\"/>\n" +
                "\t\t\t<target ref=\"id8\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-211\" y=\"-46\">focus=100,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-178\" y=\"-85\">1</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id13\"/>\n" +
                "\t\t\t<target ref=\"id4\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-365\" y=\"34\">focus=50,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-391\" y=\"42\">11</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id13\"/>\n" +
                "\t\t\t<target ref=\"id6\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-136\" y=\"8\">focus=0,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-153\" y=\"12\">18</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id13\"/>\n" +
                "\t\t\t<target ref=\"id7\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-433\" y=\"-8\">focus=75,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-347\" y=\"0\">71</label>\n" +
                "\t\t\t<nail x=\"-365\" y=\"8\"/>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id6\"/>\n" +
                "\t\t\t<target ref=\"id12\"/>\n" +
                "\t\t\t<label kind=\"guard\" x=\"111\" y=\"-46\">dt==1</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id5\"/>\n" +
                "\t\t\t<target ref=\"id10\"/>\n" +
                "\t\t\t<label kind=\"guard\" x=\"8\" y=\"153\">dt==1</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id4\"/>\n" +
                "\t\t\t<target ref=\"id11\"/>\n" +
                "\t\t\t<label kind=\"guard\" x=\"-246\" y=\"195\">dt==1</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id7\"/>\n" +
                "\t\t\t<target ref=\"id13\"/>\n" +
                "\t\t\t<label kind=\"guard\" x=\"-331\" y=\"-25\">dt==1</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id14\"/>\n" +
                "\t\t\t<target ref=\"id8\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-289\" y=\"-144\">focus=100,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-195\" y=\"-161\">96</label>\n" +
                "\t\t\t<nail x=\"-178\" y=\"-136\"/>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id14\"/>\n" +
                "\t\t\t<target ref=\"id6\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-101\" y=\"-59\">focus=0,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-76\" y=\"-93\">2</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id14\"/>\n" +
                "\t\t\t<target ref=\"id7\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-280\" y=\"-110\">focus=75,dt=0</label>\n" +
                "\t\t\t<label kind=\"probability\" x=\"-297\" y=\"-93\">1</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id8\"/>\n" +
                "\t\t\t<target ref=\"id14\"/>\n" +
                "\t\t\t<label kind=\"guard\" x=\"-144\" y=\"-136\">dt==1</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id9\"/>\n" +
                "\t\t\t<target ref=\"id8\"/>\n" +
                "\t\t\t<nail x=\"-127\" y=\"-187\"/>\n" +
                "\t\t</transition>\n" +
                "\t</template>\n");

        bufferedWriter.append(SingleXML.MonitorTemp());
        bufferedWriter.append("<system>\n");
        bufferedWriter.append("system ");
        for(int i = 0; i < title.size(); i++){
            bufferedWriter.append(title.get(i).substring(0,1).toUpperCase()+title.get(i).substring(1, title.get(i).length()-4));
            bufferedWriter.append(",");
        }
        bufferedWriter.append("Driver,");
        bufferedWriter.append("Monitor");
        bufferedWriter.append(";\n");
        bufferedWriter.append("</system>\n");
        for(String s: listFileName){
            bufferedWriter.append("<query>\n" +
                    "</query>\n");
        }
        bufferedWriter.append("<query>\n" +
                "</query>\n");
        bufferedWriter.append("<query>\n" +
                "</query>\n");
        bufferedWriter.append("</nta>\n");

        bufferedWriter.close();
    }
}
