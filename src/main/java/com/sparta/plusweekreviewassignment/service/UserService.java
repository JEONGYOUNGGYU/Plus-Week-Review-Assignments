package com.sparta.plusweekreviewassignment.service;

import com.sparta.plusweekreviewassignment.dto.CommonResponseDto;
import com.sparta.plusweekreviewassignment.dto.LoginRequestDto;
import com.sparta.plusweekreviewassignment.dto.SignupRequestDto;
import com.sparta.plusweekreviewassignment.entity.User;
import com.sparta.plusweekreviewassignment.entity.UserRoleEnum;
import com.sparta.plusweekreviewassignment.repository.UserRepository;
import com.sparta.plusweekreviewassignment.jwt.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class UserService {

  final UserRepository userRepository;
  final JwtUtil jwtUtil;
  public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
    this.userRepository = userRepository;
    this.jwtUtil = jwtUtil;
  }

  public User findById(Long id){
    return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }


  public ResponseEntity<?> signup(SignupRequestDto signupRequestDto) {
    String username = signupRequestDto.getUsername();
    String password = signupRequestDto.getPassword();
    UserRoleEnum role = UserRoleEnum.USER;

    User user = new User(username, password, role);

    if(password.contains(username)){
      return ResponseEntity.badRequest().body(new CommonResponseDto("비밀번호는 아이디와 일치하면 안됩니다", 401));
    }else{
      userRepository.save(user);
    }
    return ResponseEntity.ok().body(new CommonResponseDto("회원가입이 완료되었습니다.", 200));
  }

  public ResponseEntity<?> login(LoginRequestDto loginRequestDto) {
    // 아이디가 맞는지 확인
    User username = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(() ->
        new IllegalArgumentException("해당 아이디가 없습니다."));
    // jwt 만들기 위해 가져옴
    UserRoleEnum role = UserRoleEnum.USER;
    // 해당 아이디의 비밀번호가 맞는지 확인
    if(username.getUsername().equals(loginRequestDto.getPassword())) {

    }
    // 맞으면 회원가입과 동시에 jwt 생성
    return new ResponseEntity<>(jwtUtil.createToken(loginRequestDto.getUsername(), role), HttpStatus.OK);

  }
}
