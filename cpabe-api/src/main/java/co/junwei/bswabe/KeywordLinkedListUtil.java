package co.junwei.bswabe;
import java.util.HashMap;
import java.util.List;

public class KeywordLinkedListUtil {

    public static void main(String[] args) {
        // Sample dataset
        HashMap<String, List<String>> documentSet = new HashMap<>();
        documentSet.put("keyword1", List.of("doc1", "doc2", "doc3"));
        documentSet.put("keyword2", List.of("doc4", "doc5", "doc6"));
        documentSet.put("keyword3", List.of("doc7", "doc8", "doc9"));

        // Build and decode linked list for each keyword
        for (String keyword : documentSet.keySet()) {
            // Define the key for the first node
            byte[] initialKey = SKE1.Gen(128);

            // Build linked list
            KeywordLinkedListBuilder builder = new KeywordLinkedListBuilder();
            builder.buildLinkedList(keyword, documentSet.get(keyword), initialKey);

            // Decode and retrieve documents using the initial key
            List<String> decodedDocuments = builder.decodeLinkedList(initialKey);

            // Print or use the decoded documents
            System.out.println("Keyword: " + keyword);
            System.out.println("Decoded Documents: " + decodedDocuments);
            System.out.println("--------------------");
        }
    }
}
