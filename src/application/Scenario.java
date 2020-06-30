package application;

import java.beans.Visibility;
import java.io.*;

public class Scenario {
    public static int[] time = new int[10000];
    public static int[] vSpeed = new int[10000];
    public static int[] hSpeed = new int[10000];
    public static Double[] flat = new Double[10000];
    public static Double[] temperature = new Double[10000];
    public static String[] status = new String[10000];
    public static String[] trafficLight = new String[10000];
    public static String[] weather = new String[10000];
    public static String[] visibility = new String[10000];
    public static Double[][] driver = new Double[100][100];

    public static File statusFile(String[] status, String filename) throws IOException {
        File filepath1 = new File("D:\\TAgendata\\status\\"+ filename +".txt");
        if(!filepath1.exists()){
            filepath1.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(filepath1.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        int count = 0;
        int temp = 1;
        while(status[count] != null){
            if((status[count+1] != null) &&status[count+1].equals(status[count])){
                temp++;
                count++;
                continue;
            } else {
                //System.out.println(status[count+1] == status[count]);
                bufferedWriter.append(status[count] + " " + temp);
                //System.out.println(status[count]+" "+status[count+1]);
                bufferedWriter.newLine();
                temp = 1;
                count++;
            }
        }
        bufferedWriter.close();
        return filepath1;
    }

    public static String speedStatus(File filepath, String outFileName) throws IOException {
        if(!filepath.exists()){
            filepath.createNewFile();
        }
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(filepath);
        InputStreamReader reader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String temp = "";
        String[] str;
        int count = 0;
        int cur = 0;
        while((temp = bufferedReader.readLine()) != null){
            //System.out.println(temp);
            str = temp.split(" ");
            //System.out.println(str[0]+" "+str[1]+" "+str[2]);
            time[count] = Integer.parseInt(str[0]);
            vSpeed[count] = Integer.parseInt(str[1]);
            hSpeed[count++] = Integer.parseInt(str[2]);
        }
        for(int i = 0; i < count; i++){
            if((vSpeed[i] == 0) && (hSpeed[i] == 0)){
                status[i] = "static";
            } else if(((vSpeed[i] > 0) && (hSpeed[i] == 0)) || ((vSpeed[i] == 0) && (hSpeed[i] > 0))){
                if(i == 0){
                    status[i] = "uniform_straight_speed";
                } else if(vSpeed[i] == 0){
                    if(hSpeed[i] - hSpeed[i-1] == 0){
                        status[i] = "uniform_straight_speed";
                    } else{
                        status[i] = "changed_straight_speed";
                    }
                } else if(hSpeed[i] == 0){
                    if(vSpeed[i] - vSpeed[i-1] == 0){
                        status[i] = "uniform_straight_speed";
                    } else {
                        status[i] = "changed_straight_speed";
                    }
                }
            } else if((vSpeed[i] > 0) && (hSpeed[i] > 0)){
                status[i] = "turning";
            }
        }
        File outfile = statusFile(status, outFileName);
        return outfile.getAbsolutePath();
    }

    public static String trafficLightStatus(File filepath, String outFileName) throws IOException {
        if(!filepath.exists()){
            filepath.createNewFile();
        }
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(filepath);
        InputStreamReader reader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String temp = "";
        String[] str;
        int count = 0;
        while((temp = bufferedReader.readLine()) != null){
            str = temp.split(" ");
            time[count] = Integer.parseInt(str[0]);
            trafficLight[count++] = str[1];
        }
        File outFile = statusFile(trafficLight, outFileName);
        return outFile.getAbsolutePath();
    }

    public static String roadStatus(File filepath, String outFileName) throws IOException {
        if(!filepath.exists()){
            filepath.createNewFile();
        }
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(filepath);
        InputStreamReader reader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String temp = "";
        String[] str;
        int count = 0;
        while((temp = bufferedReader.readLine()) != null){
            str = temp.split(" ");
            time[count] = Integer.parseInt(str[0]);
            flat[count++] = Double.parseDouble(str[1]);
        }
        for(int i = 0; i < count; i++){
            if(flat[i] >= 0.15d){
                status[i] = "not_flat";
            } else {
                status[i] = "flat";
            }
        }
        File outFile = statusFile(status, outFileName);

        return outFile.getAbsolutePath();
    }

    public static String weatherStatus(File filepath, String outFileName) throws IOException {
        if(!filepath.exists()){
            filepath.createNewFile();
        }
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(filepath);
        InputStreamReader reader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String temp = "";
        String[] str;
        int count = 0;
        while((temp = bufferedReader.readLine()) != null){
            str = temp.split(" ");
            time[count] = Integer.parseInt(str[0]);
            weather[count] = str[1];
            temperature[count] = Double.parseDouble(str[2]);
            visibility[count++] = str[3];
        }

        File outFile = statusFile(visibility, outFileName);

        return outFile.getAbsolutePath();
    }

    public static String driverstatus(File filepath, String outFileName) throws IOException {
        if(!filepath.exists()){
            filepath.createNewFile();
        }
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(filepath);
        InputStreamReader reader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String temp = "";
        String[] str;
        int count = 0;
        while((temp = bufferedReader.readLine()) != null){
            str = temp.split(" ");
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < str.length; j++){
                    driver[i][j] = Double.parseDouble(str[j]);
                }
            }
        }
        return null;
    }
}
