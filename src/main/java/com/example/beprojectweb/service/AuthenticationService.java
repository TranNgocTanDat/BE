package com.example.beprojectweb.service;

import com.example.beprojectweb.dto.request.AuthenticationRequest;
import com.example.beprojectweb.dto.request.IntrospectRequest;
import com.example.beprojectweb.dto.response.AuthenticationResponse;
import com.example.beprojectweb.dto.response.IntrospectResponse;
import com.example.beprojectweb.entity.User;
import com.example.beprojectweb.exception.AppException;
import com.example.beprojectweb.exception.ErrorCode;
import com.example.beprojectweb.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String signerKey;

    //check login
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        //check user
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

        //sử dụng matches để kiểm tra pass nhập vào đúng với pass trong db
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        //kiểm tra đăng nhập
        if(!authenticated)
            throw new AppException(ErrorCode.UNATHENTICATIED);
        //sucess
        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();

    }

    public String generateToken(User user){
        //Create Header
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        //create Payload
        //claim
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername()) //user login
                .issuer("beproject")
                .issueTime(new Date()) //thời gian tạo token
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                //tạo 1 claim chứa scope nhận biết user or admin
                //tạo 1 hàm build scope bên dưới
                .claim("scope", buildScope(user))
                .build();

        //Payload
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        //JWSObject
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        //ký token
        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {

            throw new RuntimeException(e);
        }
    }

    //verify token
    public IntrospectResponse introspectResponse(IntrospectRequest request) throws JOSEException, ParseException {
        //lấy token
        var token = request.getToken();

        // Sử dụng JWSVerifier
        JWSVerifier jwsVerifier =new MACVerifier(signerKey.getBytes());

        // Sử dụng signedJWT
        SignedJWT signedJWT = SignedJWT.parse(token);

        // Kiểm tra thời gian token
        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        // verify
        var verifier =signedJWT.verify(jwsVerifier);
        return IntrospectResponse.builder()
                .valid(verifier && expityTime.after(new Date()))
                .build();

    }

    //bui scope từ 1 user
    private String buildScope(User user){
        //scope đang là một 1 lisr , dùng StringJoiner để nối các scope bằng dấu " "
        StringJoiner stringJoiner = new StringJoiner(" "); // Oauth2 quy định scope phân cách nhau bằng " "

        //kiểm tra role
        if(!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(stringJoiner::add);
        return stringJoiner.toString();
    }
}
