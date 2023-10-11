package org.adbs.vtlabs.lab2new.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.adbs.vtlabs.lab2new.components.ObjectMapperProvider;
import org.adbs.vtlabs.lab2new.exception.InvalidTokenException;
import org.adbs.vtlabs.lab2new.model.service.User;
import org.adbs.vtlabs.lab2new.security.EStorePrincipal;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

public class AuthorityService {
    // It must not change when users exist in the DB
    private static final String SECRET_KEY = "THIS-IS-SERVER-SECRET-KEY";

    private static AuthorityService instance;
    @SneakyThrows
    public static synchronized AuthorityService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AuthorityService();
        }
        return instance;
    }

    private final ObjectMapper objectMapper;
    private final MessageDigest messageDigest;
    private final Base64.Encoder base64Encoder;
    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    public AuthorityService() throws Exception {
        objectMapper = ObjectMapperProvider.getInstance();
        messageDigest = MessageDigest.getInstance("SHA-256");
        base64Encoder = Base64.getEncoder();
        publicKey = loadPublicKeyFromResource("rsa/public-key.pem");
        privateKey = loadPrivateKeyFromResource("rsa/private-key.pem");
    }

    public String generateUserHash(String username, String password) {
        return base64Encoder.encodeToString(
                messageDigest.digest(String.format("%s:%s:%s", username, SECRET_KEY, password).getBytes(StandardCharsets.UTF_8))
        );
    }

    public String generateUserJwt(User user) {
        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
        return JWT.create()
                .withClaim("sub", user.getUserId())
                .withClaim("username", user.getUsername())
                .withIssuer("estore")
                .withArrayClaim("authorities", new String[]{"USER"})
                .sign(algorithm);
    }

    public EStorePrincipal verifyUserJwt(String token) throws InvalidTokenException {
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("estore")
                    .build();
            DecodedJWT decodedJwt = verifier.verify(token);
            String jwtPayload = new String(Base64.getDecoder().decode(decodedJwt.getPayload()));
            System.out.println(jwtPayload);
            return objectMapper.readValue(jwtPayload, EStorePrincipal.class);
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    private static RSAPublicKey loadPublicKeyFromResource(String publicKeyResource) throws Exception {
        try (InputStream inputStream = AuthorityService.class.getClassLoader().getResourceAsStream(publicKeyResource);
             InputStreamReader reader = new InputStreamReader(inputStream);
             PemReader pemReader = new PemReader(reader)) {
            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(content);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        }
    }

    private static RSAPrivateKey loadPrivateKeyFromResource(String privateKeyResource) throws Exception {
        try (InputStream inputStream = AuthorityService.class.getClassLoader().getResourceAsStream(privateKeyResource);
             InputStreamReader reader = new InputStreamReader(inputStream);
             PemReader pemReader = new PemReader(reader)) {
            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(content);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        }
    }
}
