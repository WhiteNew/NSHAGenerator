package application;

import java.io.*;
import java.util.ArrayList;

import static application.GenerateAllxml.*;

public class SingleXML {
    public static String getFileName(String path){
        System.out.println(path);
        String[] str = path.split("\\\\");
        System.out.println(str[str.length - 1]);
        String str1 = str[str.length - 1].substring(0,str[str.length - 1].length()-4);
        System.out.println(str1);
        return str1;
    }

    public static String getSingleXML(String filepath) throws IOException {
        for(int i = 0; i < poiX.length; i++){
            poiX[i] = (- 892) + Distance * (i+1);
            poiY[i] = (- 671) + Distance * (i+1);
        }

        //outStream
      //  System.out.println(filepath);
        String Filename = getFileName(filepath);
        File filepath1 = new File("D:\\TAgendata\\output\\"+ Filename + ".xml");
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
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(filepath);
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
            //System.out.println(s+" " +node[count] +" "+time[count]);
            count++;
        }
        bufferedReader.close();
        String name = Filename.substring(0, 1).toUpperCase() + Filename.substring(1, Filename.length());
        bufferedWriter.append(Addtemplate(name));
        bufferedWriter.newLine();
        bufferedWriter.append(Adddeclaration("clock clk1;\n" + "//初始化函数\n" +
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
        bufferedWriter.newLine();
        //create the node location
        bufferedWriter.append(AddlocationInit());
        bufferedWriter.newLine();
        for(int i = 0; i < count; i++){
            bufferedWriter.append(Addlocation(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.append(AddlocationFinal(count+1));
        bufferedWriter.newLine();
        bufferedWriter.append("\t<init ref = \"" + "id0" + "\"/>\n");
        bufferedWriter.newLine();

        // create node transition
        for(int i = 0; i < count; i++){
            if(Integer.parseInt(time2[i]) >= 2){
                bufferedWriter.append(Addtransition(i));
                bufferedWriter.newLine();
            }
        }
        int loop = 0;
        while(loop < count){
            if(loop == 0){
                bufferedWriter.append("\t<transition>\n" +
                        "\t\t<source ref = \"" + "id0" + "\"/>\n" +
                        "\t\t<target ref = \"" + node[0] + "\"/>\n" +
                        "\t\t<label kind=\"assignment\">initialState(),clk=0</label>\n" +
                        "\t</transition>");
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

        bufferedWriter.append("\t<transition>\n" +
                "\t\t<source ref = \"" + node[loop-1] + "\"/>\n" +
                "\t\t<target ref = \"id" + "final" + "\"/>\n" +
                "\t</transition>");
        bufferedWriter.newLine();

        bufferedWriter.append(Endtemplate());

        bufferedWriter.append("<system>\n");
        bufferedWriter.append("system ");
        bufferedWriter.append(name);
        bufferedWriter.append(";\n");
        bufferedWriter.append("</system>\n");
        bufferedWriter.append("<query>\n" +
                "</query>\n");
        bufferedWriter.append("</nta>\n");


        bufferedWriter.close();
        return filepath1.getAbsolutePath();
    }

    public static String getDriverXML() throws IOException {
        //outStream
        String Filename = "Driver";
        File filepath1 = new File("D:\\TAgendata\\output\\"+ Filename + ".xml");
        if(!filepath1.exists()){
            filepath1.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(filepath1.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String initial = AddInit();
        bufferedWriter.append(initial);

        String temp = "";
        String[] str;

        //System.out.println(str[0]+" "+str[1]);
        //

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

        bufferedWriter.append("<system>\n");
        bufferedWriter.append("system ");
        bufferedWriter.append(Filename);
        bufferedWriter.append(";\n");
        bufferedWriter.append("</system>\n");
        bufferedWriter.append("<query>\n" +
                "</query>\n");
        bufferedWriter.append("</nta>\n");


        bufferedWriter.close();
        return filepath1.getAbsolutePath();
    }
    public static String getMonitorXML() throws IOException {
        //outStream
        String Filename = "Monitor";
        File filepath1 = new File("D:\\TAgendata\\output\\"+ Filename + ".xml");
        if(!filepath1.exists()){
            filepath1.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(filepath1.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String initial = AddInit();
        bufferedWriter.append(initial);

        String temp = "";
        String[] str;

        //System.out.println(str[0]+" "+str[1]);
        //

        bufferedWriter.append("<template>\n" +
                "\t\t<name>Monitor</name>\n" +
                "\t\t<declaration>clock cot;\n" +
                "\n" +
                "int distance_x;\n" +
                "int distance_y;\n" +
                "\n" +
                "void updateDistance()\n" +
                "{\n" +
                "    //distance=(sx-cx)*(sx-cx)+(sy-cy)*(sy-cy);\n" +
                "    distance_x = abs(cx-sx);\n" +
                "    distance_y = abs(cy-sy);\n" +
                "    \n" +
                "}\n" +
                "void judgeSafe()\n" +
                "{\n" +
                "   if(distance_x&lt;5 &amp;&amp; distance_y&lt;=5)\n" +
                "       danger=true;\n" +
                " //if(focus&lt;=100)\n" +
                "   //   danger=true;\n" +
                "   \n" +
                "}</declaration>\n" +
                "\t\t<location id=\"id20\" x=\"-229\" y=\"0\">\n" +
                "\t\t\t<name x=\"-263\" y=\"9\">judge</name>\n" +
                "\t\t\t<committed/>\n" +
                "\t\t</location>\n" +
                "\t\t<location id=\"id21\" x=\"-229\" y=\"-102\">\n" +
                "\t\t\t<label kind=\"invariant\" x=\"-272\" y=\"-93\">cot&lt;=1</label>\n" +
                "\t\t</location>\n" +
                "\t\t<location id=\"id22\" x=\"17\" y=\"-102\">\n" +
                "\t\t\t<name x=\"7\" y=\"-136\">unsafe</name>\n" +
                "\t\t\t<label kind=\"exponentialrate\" x=\"26\" y=\"-85\">2</label>\n" +
                "\t\t</location>\n" +
                "\t\t<location id=\"id23\" x=\"-340\" y=\"-102\">\n" +
                "\t\t\t<name x=\"-350\" y=\"-136\">start</name>\n" +
                "\t\t\t<urgent/>\n" +
                "\t\t</location>\n" +
                "\t\t<init ref=\"id23\"/>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id20\"/>\n" +
                "\t\t\t<target ref=\"id21\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-195\" y=\"-34\">judgeSafe()</label>\n" +
                "\t\t\t<nail x=\"-195\" y=\"-51\"/>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id21\"/>\n" +
                "\t\t\t<target ref=\"id20\"/>\n" +
                "\t\t\t<label kind=\"guard\" x=\"-408\" y=\"-68\">cot==1&amp;&amp;danger==false</label>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-391\" y=\"-51\">updateDistance(),cot=0</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id21\"/>\n" +
                "\t\t\t<target ref=\"id22\"/>\n" +
                "\t\t\t<label kind=\"guard\" x=\"-195\" y=\"-119\">danger==true&amp;&amp;cot==1</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id23\"/>\n" +
                "\t\t\t<target ref=\"id21\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-306\" y=\"-119\">cot=0</label>\n" +
                "\t\t\t<nail x=\"-246\" y=\"-102\"/>\n" +
                "\t\t</transition>\n" +
                "\t</template>\n");

        bufferedWriter.append("<system>\n");
        bufferedWriter.append("system ");
        bufferedWriter.append(Filename);
        bufferedWriter.append(";\n");
        bufferedWriter.append("</system>\n");
        bufferedWriter.append("<query>\n" +
                "</query>\n");
        bufferedWriter.append("</nta>\n");


        bufferedWriter.close();
        return filepath1.getAbsolutePath();
    }

    public static String MonitorTemp(){
        String temp = "<template>\n" +
                "\t\t<name>Monitor</name>\n" +
                "\t\t<declaration>clock cot;\n" +
                "\n" +
                "int distance_x;\n" +
                "int distance_y;\n" +
                "\n" +
                "void updateDistance()\n" +
                "{\n" +
                "    //distance=(sx-cx)*(sx-cx)+(sy-cy)*(sy-cy);\n" +
                "    distance_x = abs(cx-sx);\n" +
                "    distance_y = abs(cy-sy);\n" +
                "    \n" +
                "}\n" +
                "void judgeSafe()\n" +
                "{\n" +
                "   if(distance_x&lt;5 &amp;&amp; distance_y&lt;=5)\n" +
                "       danger=true;\n" +
                " //if(focus&lt;=100)\n" +
                "   //   danger=true;\n" +
                "   \n" +
                "}</declaration>\n" +
                "\t\t<location id=\"id20\" x=\"-229\" y=\"0\">\n" +
                "\t\t\t<name x=\"-263\" y=\"9\">judge</name>\n" +
                "\t\t\t<committed/>\n" +
                "\t\t</location>\n" +
                "\t\t<location id=\"id21\" x=\"-229\" y=\"-102\">\n" +
                "\t\t\t<label kind=\"invariant\" x=\"-272\" y=\"-93\">cot&lt;=1</label>\n" +
                "\t\t</location>\n" +
                "\t\t<location id=\"id22\" x=\"17\" y=\"-102\">\n" +
                "\t\t\t<name x=\"7\" y=\"-136\">unsafe</name>\n" +
                "\t\t\t<label kind=\"exponentialrate\" x=\"26\" y=\"-85\">2</label>\n" +
                "\t\t</location>\n" +
                "\t\t<location id=\"id23\" x=\"-340\" y=\"-102\">\n" +
                "\t\t\t<name x=\"-350\" y=\"-136\">start</name>\n" +
                "\t\t\t<urgent/>\n" +
                "\t\t</location>\n" +
                "\t\t<init ref=\"id23\"/>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id20\"/>\n" +
                "\t\t\t<target ref=\"id21\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-195\" y=\"-34\">judgeSafe()</label>\n" +
                "\t\t\t<nail x=\"-195\" y=\"-51\"/>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id21\"/>\n" +
                "\t\t\t<target ref=\"id20\"/>\n" +
                "\t\t\t<label kind=\"guard\" x=\"-408\" y=\"-68\">cot==1&amp;&amp;danger==false</label>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-391\" y=\"-51\">updateDistance(),cot=0</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id21\"/>\n" +
                "\t\t\t<target ref=\"id22\"/>\n" +
                "\t\t\t<label kind=\"guard\" x=\"-195\" y=\"-119\">danger==true&amp;&amp;cot==1</label>\n" +
                "\t\t</transition>\n" +
                "\t\t<transition>\n" +
                "\t\t\t<source ref=\"id23\"/>\n" +
                "\t\t\t<target ref=\"id21\"/>\n" +
                "\t\t\t<label kind=\"assignment\" x=\"-306\" y=\"-119\">cot=0</label>\n" +
                "\t\t\t<nail x=\"-246\" y=\"-102\"/>\n" +
                "\t\t</transition>\n" +
                "\t</template>\n";
        return temp;
    }
}


