import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: mnikonova
 * Date: 5/21/12
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class Reader {

    //TODO This method will split file "fileName" to threadAmount parts.
    //TODO Returns List of file parts names


    public List<String> readSplitWriteFirstStageToFileForThreads(String fileName, int threadAmount) {

        List<String> tmpListInstance = new ArrayList<String>();
        File file = new File(fileName);
        BufferedReader buffRead = null;
        String str;
        List<String> lowFileNamesInstancesList = new ArrayList<String>();
        try {
            if (threadAmount != 0 | threadAmount > 0) {
                FileReader fileTempRead = new FileReader(file);
                buffRead = new BufferedReader(fileTempRead);

                while ((str = buffRead.readLine()) != null) {
                    tmpListInstance.add(str);
                }
                int stringAmount = tmpListInstance.size();
                System.out.println("tmpListInstance size is: " + stringAmount + "(" + tmpListInstance.toString() + ")");
                int lineCounter = 0;
                int t = stringAmount / threadAmount;
                List<String> arrayListInstanceForThreading = new ArrayList<String>();

                for (String stringToListInstanceForTreading : tmpListInstance) {

                    if (lineCounter < t) {
                        lineCounter++;
                        arrayListInstanceForThreading.add(stringToListInstanceForTreading);
                        System.out.println(stringToListInstanceForTreading);
                    } else {
                        List<String> arrayListInstanceForThreadingNew = new ArrayList<String>();
                        arrayListInstanceForThreadingNew.addAll(arrayListInstanceForThreading);
                        System.out.println("ArrayList : " + arrayListInstanceForThreadingNew.toString());

                        Writer writerInstanceToLowFiles = new Writer();
                        lowFileNamesInstancesList.add(writerInstanceToLowFiles.fileWriterFirstStageToLowFiles(arrayListInstanceForThreadingNew));

                        lineCounter = 0;
                        arrayListInstanceForThreading.clear();
                        arrayListInstanceForThreading.add(stringToListInstanceForTreading);
                        lineCounter++;
                        System.out.println("tmpListInstance is created; arrayListInstanceForThreading is flushed");
                    }
                }
                System.out.println("File is successfully split to: " + threadAmount + " parts;");
            } else {
                System.out.println(" Incorrect thread value ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        } catch (IOException c) {
            c.printStackTrace();
        } catch (NullPointerException d) {
            d.printStackTrace();
        } finally {
            closeReader(buffRead);
        }
        return lowFileNamesInstancesList;
    }


    public String readSplitWriteSecondStageStringToSeparateWords(String fileName) {

        File file = new File(fileName);
        BufferedReader buffRead = null;
        String str;
        Map<String, Integer> mapInstance = new TreeMap<String, Integer>();

        String strForFileName = "";
        try {
            FileReader fileTempRead = new FileReader(file);
            buffRead = new BufferedReader(fileTempRead);

            while ((str = buffRead.readLine()) != null) {
                String[] tmpSplitString = str.split(" ");
                int k = 1;
                for (int i = 0; i < mapInstance.size(); i++) {
                    if (mapInstance.containsKey(tmpSplitString[i])) {
                        mapInstance.put(tmpSplitString[i], ++k);
                    } else {
                        mapInstance.put(tmpSplitString[i], k);
                    }
                }
            }
            Writer writerInstanceToLowFiles = new Writer();
            strForFileName = writerInstanceToLowFiles.fileWriterSecondStageToFileAfterHandling(mapInstance);
            System.out.println(mapInstance.toString());

        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        } catch (IOException c) {
            c.printStackTrace();
        } catch (NullPointerException d) {
            d.printStackTrace();
        } finally {
            closeReader(buffRead);
        }
        return strForFileName;
    }


    public String readMergeWriteThirdStageToResultFile(List<String> listForFileNamesAfterHandling) {   //TODO Parse each file to hashMap
        Map<String, Integer> resultMapInstance = new HashMap<String, Integer>();
        Map<String, BufferedReader> fileNameBuffReaderMap = new HashMap<String, BufferedReader>();
        Map<String, Map.Entry<String, Integer>> stringEntryMap = new HashMap<String, Map.Entry<String, Integer>>();

        try {
            for (String fileName : listForFileNamesAfterHandling) {
                File file = new File(fileName);
                FileReader fileTempRead = new FileReader(file);
                BufferedReader buffRead = new BufferedReader(fileTempRead);
                fileNameBuffReaderMap.put(fileName, buffRead);
            }


//        String str = "";
//        Map<String, Integer> tmpMapInstance = new HashMap<String, Integer>();

//        try {
//            for (String fileName : listForFileNamesAfterHandling) {
//                File file = new File(fileName);
//                FileReader fileTempRead = new FileReader(file);
//                BufferedReader buffRead = new BufferedReader(fileTempRead);
//                while ((str = buffRead.readLine()) != null) {
//                    int index = str.indexOf(' ');
//                    String letter = "" + str.charAt(index - 1);
//                    int number = Integer.parseInt("" + str.charAt(index + 1));
//                    tmpMapInstance.put(letter, number);
//                }
//            }    //This cycle read just first line???
//            for (Map.Entry<String, Integer> inst : tmpMapInstance.entrySet()) {
//                if (resultMapInstance.containsKey(inst)) {
//                    resultMapInstance.put(); //how can amount be added to value???); )
//                } else {
//                    resultMapInstance.put(inst);   //what is happened here???
//                }
//            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        } catch (IOException c) {
            c.printStackTrace();
        } catch (NullPointerException d) {
            d.printStackTrace();
        } finally {
            closeReader(buffRead);          //    What closeReader can be done in this case
        }
        String strForFileName = "";
        Writer writerInstanceToLowFiles = new Writer();
        strForFileName = writerInstanceToLowFiles.fileWriterThirdStageToResultFile(resultMapInstance);
        System.out.println("Result File was written" + resultMapInstance.toString());
        return strForFileName;
    }


    private void closeReader(BufferedReader buffRead) {
        try {
            if (buffRead != null) {
                buffRead.close();
            }
        } catch (IOException ignored) {
        }
    }
}
