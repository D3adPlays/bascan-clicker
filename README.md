# bascan-clicker

Besoins api format URL
http://lien/api/gtoken&hcapcha=<token>
return:
    - 400 erreur //si token hcapcha invalide
    - <token> // comportant l'ip du demandeur et Un uuid généré avec seed l'ip le tout encrypté avec une clef privé
    https://docs.hcaptcha.com pour l'api simple
```
	private static SecretKeySpec secretKey;
	private static final String ALGORITHM = "AES";
    	public static String decrypt(String strToDecrypt, String secret) {
		try {
			prepareSecreteKey(secret);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public static String encrypt(String strToEncrypt, String secret) {
		try {
			prepareSecreteKey(secret);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static void prepareSecreteKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes(StandardCharsets.UTF_8);
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
```
