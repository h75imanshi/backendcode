package com.eoffice.service;

import com.eoffice.dto.AuthDTO.ApiResponse;
import com.eoffice.dto.AuthDTO.LoginRequest;
import com.eoffice.dto.AuthDTO.SignupRequest;

import com.eoffice.model.User;
import com.eoffice.repository.LoginActivityRepository;
import com.eoffice.repository.UserRepository;
import com.eoffice.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.eoffice.model.LoginActivity;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final LoginActivityRepository loginActivityRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // ==================== SIGNUP ====================
    public ApiResponse signup(SignupRequest request) {

        String token = "";
        if (userRepository.existsByUsername(request.getUsername())) {
            return new ApiResponse(false, "Username already taken!", token);
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return new ApiResponse(false, "Email already registered!", token);
        }

        if (!request.getPassword().equals(request.getConfirmPassword()))
            return new ApiResponse(false, "Passwords do not match!", token);

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());

        // Encrypt Password
        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setIsActive(true);

        userRepository.save(user);

        return new ApiResponse(
                true,
                "Registration successful!",
                token);
    }

    // ==================== LOGIN ====================
   // public AuthResponse login(LoginRequest request, HttpServletRequest httpRequest) {

        // Find user by username
        //User user = userRepository.findByUsername(request.getUsername()).orElse(null);

        //String ipAddress = getClientIp(httpRequest);
        //String userAgent = httpRequest.getHeader("User-Agent");

        // User not found
       // if (user == null) {
            //saveLoginActivity(request.getUsername(), null, ipAddress, userAgent, "FAILED");
           // return new AuthResponse(false, "Invalid username or password!");
       // }

        // Verify BCrypt password
       // if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
          //  saveLoginActivity(request.getUsername(), user.getEmail(), ipAddress, userAgent, "FAILED");
          //  return new AuthResponse(false, "Invalid username or password!");
       // }

        // Check if account is active
     //   if (!user.getIsActive()) {
            //return new AuthResponse(false, "Account is deactivated. Contact admin.");
        //}

        // Save successful login activity
//saveLoginActivity(user.getUsername(), user.getEmail(), ipAddress, userAgent, "SUCCESS");

        // Generate JWT token
      //  String token = jwtUtil.generateToken(user.getUsername());

       // return new AuthResponse(token, user.getUsername(), user.getEmail());
  //  }

    // ==================== LOGIN ====================
    public ApiResponse login(LoginRequest request, HttpServletRequest httpRequest) {

        System.out.println("LOGIN API HIT");
        System.out.println("Username = " + request.getUsername());

        User user = userRepository.findByUsername(request.getUsername())
                .orElse(null);

        System.out.println("User Found = " + (user != null));

        if (user != null) {
            System.out.println("DB Password = " + user.getPassword());

            boolean match = passwordEncoder.matches(
                    request.getPassword(),
                    user.getPassword());

            System.out.println("Password Match = " + match);
        }

        if (user == null) {
            return new ApiResponse(
                    false,
                    "Invalid username or password",
                    ""
            );
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            return new ApiResponse(
                    false,
                    "Invalid username or password",
                    ""
            );
        }

// TOKEN GENERATE
        String token =
                jwtUtil.generateToken(
                        user.getUsername());

        LoginActivity activity = new LoginActivity();

        activity.setUsername(
                user.getUsername());

        activity.setEmail(
                user.getEmail());

        activity.setToken(token);

        loginActivityRepository.save(activity);

        return new ApiResponse(
                true,
                "Login successful!",
                token,
                user.getUsername(),
                user.getFullName(),
                user.getEmail()
        );
    }

    // ==================== Save Login Activity ====================
    private void saveLoginActivity(String username, String email,
                                    String ip, String userAgent, String status ,String token) {
        LoginActivity activity = new LoginActivity();
        activity.setUsername(username);
        activity.setEmail(email);
        activity.setIpAddress(ip);
        activity.setUserAgent(userAgent);
        activity.setLoginStatus(status);
        activity.setToken(token);
        loginActivityRepository.save(activity);
    }

    // ==================== Get Client IP ====================
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
