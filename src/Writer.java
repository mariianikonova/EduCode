import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import java.util.UUID;

import static java.util.UUID.*;

/**
 * Created by IntelliJ IDEA.
 * User: mnikonova
 * Date: 5/10/12
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class Writer {


    public String fileWriterFirstStageToLowFiles(List<String> arrayListInstanceForThreadingNew) {
        String filename = "";
        BufferedWriter buffWriter = null;

        try {
            FileWriter fileTempWrite = new FileWriter(filename = randomUUID().toString());
            buffWriter = new BufferedWriter(fileTempWrite);
            for (String tmpString : arrayListInstanceForThreadingNew) {
                buffWriter.write(tmpString);
                buffWriter.newLine();
                buffWriter.flush();
            }
            System.out.println("File is wrote ( " + filename + " ) ");

        } catch (IOException c) {
            c.printStackTrace();
        } finally {
            closeWriter(buffWriter);
        }
        return filename;
    }


    public String fileWriterSecondStageToFileAfterHandling(Map<String, Integer> tmpHashMapInstance) {
        String filename = "";

        BufferedWriter buffWriter = null;
        try {
            FileWriter fileTempWrite = new FileWriter(filename = randomUUID().toString());
            buffWriter = new BufferedWriter(fileTempWrite);

            for (Map.Entry<String, Integer> str : tmpHashMapInstance.entrySet()) {
                buffWriter.write(str.getKey());
                buffWriter.write(" ");
                buffWriter.write(str.getValue().toString());
                buffWriter.newLine();
                buffWriter.flush();
            }
            System.out.println("File is wrote");

        } catch (IOException c) {
            c.printStackTrace();
        } finally {
            closeWriter(buffWriter);
        }
        return filename;
    }


    public String fileWriterThirdStageToResultFile(Map<String, Integer> mapInstance) {
        String filename = "";

        BufferedWriter buffWriter = null;
        try {
            FileWriter fileTempWrite = new FileWriter(filename = randomUUID().toString());
            buffWriter = new BufferedWriter(fileTempWrite);
            for (Map.Entry<String, Integer> str : mapInstance.entrySet()) {
                buffWriter.write(str.getKey());
                buffWriter.write(" : ");
                buffWriter.write(str.getValue().toString());
                buffWriter.newLine();
                buffWriter.flush();
            }
            System.out.println("File is wrote");

        } catch (IOException c) {
            c.printStackTrace();
        } finally {
            closeWriter(buffWriter);
        }
        return filename;
    }


    private void closeWriter(BufferedWriter buffWriter) {
        try {
            if (buffWriter != null) {
                buffWriter.close();
            }
        } catch (IOException ignored) {
        }
    }
}