import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: mnikonova
 * Date: 5/10/12
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void main(String args[]) {

        List<String> listForFileNamesAfterSplitting;
        List<String> listForFileNamesAfterHandling = new ArrayList<String>();
        String fileNameToProcess = "";

        Reader testReader = new Reader();

        listForFileNamesAfterSplitting = testReader.readSplitWriteFirstStageToFileForThreads("file.txt", 2);
        ThreadHandler threadOwner = new ThreadHandler(fileNameToProcess, listForFileNamesAfterHandling);
        threadOwner.processFiles(listForFileNamesAfterSplitting);
        testReader.readMergeWriteThirdStageToResultFile(listForFileNamesAfterHandling);
    }
}
