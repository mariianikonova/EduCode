import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mnikonova
 * Date: 5/10/12
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class ThreadHandler implements Runnable {

    private String fileNameToProcess;
    private List<String> listForFileNamesAfterHandling;

    public ThreadHandler(String fileNameToProcess, List<String> listForFileNamesAfterHandling) {
        this.fileNameToProcess = fileNameToProcess;
        this.listForFileNamesAfterHandling = listForFileNamesAfterHandling;
    }

    public void run() {
        String fileNameAfterHandle = "";
        Reader reader = new Reader();
        fileNameAfterHandle = reader.readSplitWriteSecondStageStringToSeparateWords(fileNameToProcess);
        listForFileNamesAfterHandling.add(fileNameAfterHandle);
        System.out.println(Thread.currentThread().getName()+": listForFileNamesAfterHandling was created ( " + listForFileNamesAfterHandling.size() + " )"); //DOESN`T WRITE FILENAMES FROM THIS ARRAY, WHY?
    }

    void processFiles(List<String> fileNames) {
        for (String fileName : fileNames) {
            new Thread(new ThreadHandler(fileName, listForFileNamesAfterHandling)).start();
        }

    }
}