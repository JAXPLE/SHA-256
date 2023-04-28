import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

class Encryption {
    public String getHash(String inputPassWord) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        StringBuilder hexString = new StringBuilder();

        byte[] firstHash = inputPassWord.getBytes(StandardCharsets.UTF_8);
        printArray("원문 > 바이트", firstHash);

        byte[] hash = messageDigest.digest(firstHash);
        printArray("바이트 > 해쉬", hash);

        for (byte b : hash)
            hexString.append(String.format("%02X", b));

        return hexString.toString();
    }

    public void printArray(String tag, byte[] hexString) {
        for (int i = 0; i < hexString.length; i++) {
            System.out.printf("%02X\t", hexString[i]);
            if ((i + 1) % 5 == 0)
                System.out.println();
        }
        System.out.println("\n");
    }
}

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scan = new Scanner(System.in);
        Encryption encryption = new Encryption();
        boolean isRun = true;

        while (isRun) {
            System.out.print("input a = ");
            String firstName = scan.next();
            String encryptionFirstName = encryption.getHash(firstName);

            System.out.print("input b = ");
            String secondName = scan.next();
            String encryptionSecondName = encryption.getHash(secondName);

            System.out.println("encryptionFirstName  > " +encryptionFirstName);
            System.out.println("encryptionSecondName > " +encryptionSecondName);

            isRun = encryptionFirstName.equals(encryptionSecondName);
            System.out.println("\nresult > " + isRun);
        }
    }
}
