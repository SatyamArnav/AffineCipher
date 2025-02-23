import java.util.Scanner;

public class affinecipher {

    static int a = 17;
    static int b = 20;

    static String encryptMessage(char[] msg) {
        String cipher = "";
        for (int i = 0; i < msg.length; i++) {
            if (msg[i] != ' ') {
                if (msg[i] >= 'A' && msg[i] <= 'Z') {
                    cipher = cipher + (char) ((((a * (msg[i] - 'A')) + b) % 26 + 'A'));
                } else {
                    cipher += msg[i];
                }
            } else {
                cipher += msg[i];
            }
        }
        return cipher;
    }

    static String decryptCipher(String cipher) {
        String msg = "";
        int a_inv = 0;
        int flag = 0;

        for (int i = 0; i < 26; i++) {
            flag = (a * i) % 26;
            if (flag == 1) {
                a_inv = i;
                break;
            }
        }

        for (int i = 0; i < cipher.length(); i++) {
            if (cipher.charAt(i) != ' ') {
                if (cipher.charAt(i) >= 'A' && cipher.charAt(i) <= 'Z') {
                    msg = msg + (char) (((a_inv * (cipher.charAt(i) - 'A' - b + 26)) % 26) + 'A');
                } else {
                    msg += cipher.charAt(i);
                }
            } else {
                msg += cipher.charAt(i);
            }
        }
        return msg;
    }

    static boolean areCoprime(int a, int m) {
        int gcd = 1;
        for (int i = 1; i <= a && i <= m; i++) {
            if (a % i == 0 && m % i == 0) {
                gcd = i;
            }
        }
        return gcd == 1;
    }

    public static void main(String[] args) {
        Scanner abc = new Scanner(System.in);
        System.out.println("ENTER A MESSAGE IN CAPITAL LETTERS:");
        String msg = abc.nextLine();

        if (!areCoprime(a, 26)) {
            System.out.println("Error: 'a' and 26 must be coprime for the Affine Cipher to work.");
            return;
        }

        String cipherText = encryptMessage(msg.toCharArray());
        System.out.println("Encrypted Message: " + cipherText);

        System.out.println("Decrypted Message: " + decryptCipher(cipherText));
    }
}

