package digitalsignature;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
public class Dsa {
    public static SignatureObject GenerateSignature(String inputString)throws Exception
    {
        SignatureObject signatureObject = new SignatureObject();
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
        keyPairGen.initialize(2048);
        //Creating KeyPair generator object  
        //Initializing the key pair generator
        keyPairGen.initialize(2048);      
        //Generate the pair of keys
        KeyPair pair = keyPairGen.generateKeyPair();
        //Getting the privatekey from the key pair
        PrivateKey privKey = pair.getPrivate();
        //Creating a Signature object
        Signature sign = Signature.getInstance("SHA256withDSA");
        //Initializing the signature
        sign.initSign(privKey);
        byte[] bytes = inputString.getBytes();
        //Adding data to the signature
        sign.update(bytes);
        //Calculating the signature
        byte[] signature = sign.sign();      
        //Initializing the signature
        // sign.initVerify(pair.getPublic());
        // sign.update(bytes);
        // //Verifying the signature
        // boolean bool = sign.verify(signature);
        // if(bool) {
        //     System.out.println("Signature verified");   
        // } else {
        //     System.out.println("Signature failed");
        // }
        signatureObject.sign=signature;
        signatureObject.pair=pair;
        signatureObject.signature=sign;
        return signatureObject;

    }
    public static boolean VerifySignature(SignatureObject signatureObject,String inputString) throws Exception
    {
        byte[] sign = signatureObject.sign;
        Signature signature=signatureObject.signature;
        KeyPair pair=signatureObject.pair;
        byte[] bytes = inputString.getBytes();
        signature.initVerify(pair.getPublic());
        signature.update(bytes);
         boolean bool = signature.verify(sign);
        // if(bool) {
        //     System.out.println("Signature verified");   
        // } else {
        //     System.out.println("Signature failed");
        // }
        return bool;
    }
   public static void main(String args[]) throws Exception
   {
      String inputString ="lol";
      SignatureObject temp=GenerateSignature(inputString);
      boolean test=VerifySignature(temp,inputString);
      System.out.println(temp.pair);
      if(!test)
      System.out.println("Signature corrupted");
}
}