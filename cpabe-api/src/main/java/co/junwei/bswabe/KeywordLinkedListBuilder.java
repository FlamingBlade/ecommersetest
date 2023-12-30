package co.junwei.bswabe;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.List;

class SKE1 {
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_TRANSFORMATION = "AES/ECB/NoPadding";

    public static byte[] Gen(int keySize) {
        // Implementation for key generation
        byte[] key = new byte[keySize / 8];
        new java.security.SecureRandom().nextBytes(key);
        return key;
    }

    public static byte[] Enc(byte[] key, byte[] plaintext) {
        return performCryptoOperation(Cipher.ENCRYPT_MODE, key, plaintext);
    }

    public static byte[] Dec(byte[] key, byte[] ciphertext) {
        return performCryptoOperation(Cipher.DECRYPT_MODE, key, ciphertext);
    }

    private static byte[] performCryptoOperation(int mode, byte[] key, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_ALGORITHM);

            if (mode == Cipher.ENCRYPT_MODE) {
                cipher.init(mode, secretKeySpec);
            } else if (mode == Cipher.DECRYPT_MODE) {
                cipher.init(mode, secretKeySpec);
            }

            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error performing crypto operation: " + e.getMessage(), e);
        }
    }
}

class Node {
    byte[] encryptedDocId;
    byte[] nextNodeKey;
    String nextNodeAddress;

    public Node(byte[] encryptedDocId, byte[] nextNodeKey, String nextNodeAddress) {
        this.encryptedDocId = encryptedDocId;
        this.nextNodeKey = nextNodeKey;
        this.nextNodeAddress = nextNodeAddress;
    }
}

public class KeywordLinkedListBuilder {
    private static final int KAPPA = 128; // Replace with the appropriate key size

    private List<Node> linkedList;

    public KeywordLinkedListBuilder() {
        linkedList = new ArrayList<>();
    }

    public void buildLinkedList(String keyword, List<String> documents, byte[] initialKey) {
        int ctr = 1;

        byte[] previousKey = initialKey;
        byte[] currentKey;

        if (documents != null) {
            for (int j = 0; j < documents.size(); j++) {
                // Generate a key K i,j
                currentKey = SKE1.Gen(KAPPA);

                // Ensure the data length is exactly 16 bytes
                byte[] dataToEncrypt = padData(documents.get(j).getBytes());

                // Create a node N i,j
                Node node = new Node(
                        SKE1.Enc(previousKey, dataToEncrypt),
                        currentKey,
                        "Phi_k1(" + (ctr + 1) + ")"
                );

                // Encrypt and store the node in linkedList
                linkedList.add(node);

                // Set the next key for the next iteration
                previousKey = currentKey;

                // Increment counter
                ctr++;
            }
        }
    }

    public List<String> decodeLinkedList(byte[] initialKey) {
        if (initialKey == null) {
            throw new IllegalArgumentException("Initial key must be provided for decoding the linked list.");
        }

        List<String> decryptedDocuments = new ArrayList<>();

        byte[] currentKey = initialKey;

        for (Node node : linkedList) {
            byte[] decryptedDocId = SKE1.Dec(currentKey, node.encryptedDocId);
            decryptedDocuments.add(new String(decryptedDocId).trim()); // trim to remove trailing spaces

            // Set the next key for the next iteration
            currentKey = node.nextNodeKey;
        }

        return decryptedDocuments;
    }

    private static byte[] padData(byte[] data) {
        int blockSize = 16; // AES block size is 16 bytes

        if (data.length == blockSize) {
            return data; // No need to pad if the data length is already 16 bytes
        } else if (data.length < blockSize) {
            byte[] paddedData = new byte[blockSize];
            System.arraycopy(data, 0, paddedData, 0, data.length);
            return paddedData;
        } else {
            // If the data is longer than 16 bytes, truncate it
            byte[] truncatedData = new byte[blockSize];
            System.arraycopy(data, 0, truncatedData, 0, blockSize);
            return truncatedData;
        }
    }
}
