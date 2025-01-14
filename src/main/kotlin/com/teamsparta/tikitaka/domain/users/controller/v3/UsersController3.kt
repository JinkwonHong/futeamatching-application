package com.teamsparta.tikitaka.domain.users.controller.v3

import com.teamsparta.tikitaka.domain.common.util.RedisUtils
import com.teamsparta.tikitaka.domain.recruitment.dto.recruitmentapplication.RecruitmentApplicationResponse
import com.teamsparta.tikitaka.domain.recruitment.service.v2.recruitmentapplication.RecruitmentApplicationService
import com.teamsparta.tikitaka.domain.users.dto.CodeDto
import com.teamsparta.tikitaka.domain.users.dto.LoginRequest
import com.teamsparta.tikitaka.domain.users.dto.LoginResponse
import com.teamsparta.tikitaka.domain.users.dto.NameRequest
import com.teamsparta.tikitaka.domain.users.dto.NameResponse
import com.teamsparta.tikitaka.domain.users.dto.PasswordRequest
import com.teamsparta.tikitaka.domain.users.dto.PasswordResponse
import com.teamsparta.tikitaka.domain.users.dto.SignUpRequest
import com.teamsparta.tikitaka.domain.users.dto.TokenRefreshDto
import com.teamsparta.tikitaka.domain.users.dto.UserDto
import com.teamsparta.tikitaka.domain.users.service.v3.EmailService
import com.teamsparta.tikitaka.domain.users.service.v3.UsersService3
import com.teamsparta.tikitaka.infra.security.UserPrincipal
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v3/users")
@RestController
class UsersController3(
    private val userService: UsersService3,
    private val recruitmentApplicationService: RecruitmentApplicationService,
    private val emailService: EmailService,
    private val redisUtils: RedisUtils
) {
    @GetMapping("/create-code")
    fun createCode(
        @RequestParam email: String,
    ): ResponseEntity<String> {
        emailService.sendEmail(email)
        return ResponseEntity.ok("이메일을 확인하세요")
    }

    @PostMapping("/create-code/{email}")
    fun emailVerified(
        @PathVariable email: String,
        @RequestBody dto: CodeDto
    ): ResponseEntity<String> {
        return if (emailService.verificationEmail(email, dto.code)) {
            redisUtils.setVerifiedEmailWithExpiration(email)
            redisUtils.deleteData(email)
            ResponseEntity.ok("인증 성공. 회원가입을 진행하세요.")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("인증 코드가 유효하지 않습니다.")
        }
    }

    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody request: SignUpRequest
    ): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.signUp(request))
    }

    @PostMapping("/log-in")
    fun logIn(
        @RequestBody request: LoginRequest
    ): ResponseEntity<LoginResponse> {
        return ResponseEntity.ok(userService.logIn(request))
    }

    @PostMapping("/log-out")
    fun logout(request: HttpServletRequest): ResponseEntity<Unit> {
        val token = request.getAttribute("accessToken") as String?
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.logOut(token!!))
    }

    @PostMapping("/token/refresh")
    fun tokenRefresh(@RequestBody tokenRefreshDto: TokenRefreshDto): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.validateRefreshTokenAndCreateToken(tokenRefreshDto.refreshToken))
    }

    @PutMapping("/profile/name")
    fun updateName(
        @RequestBody request: NameRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<NameResponse> {
        return ResponseEntity.ok(userService.updateName(request, userPrincipal))
    }

    @PutMapping("/profile/password")
    fun updatePassword(
        @RequestBody request: PasswordRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<PasswordResponse> {
        return ResponseEntity.ok(userService.updatePassword(request, userPrincipal))
    }

    @GetMapping("my-recruitment-applications")
    fun getMyApplications(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<List<RecruitmentApplicationResponse>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(recruitmentApplicationService.getMyApplications(userPrincipal))
    }
}
