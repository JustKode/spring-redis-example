package dcom.spring.redisexample.domain.member.controller;

import dcom.spring.redisexample.domain.member.dto.MemberRequestDto;
import dcom.spring.redisexample.domain.member.dto.MemberResponseDto;
import dcom.spring.redisexample.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/list")
    public ResponseEntity<List<MemberResponseDto>> getMemberList() {
        return ResponseEntity.ok(memberService.getMemberList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable Integer id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> putMember(@PathVariable Integer id, @RequestBody MemberRequestDto dto) {
        return ResponseEntity.accepted().body(memberService.modifyMember(id, dto));
    }

    @PostMapping("/")
    public ResponseEntity<Integer> postMember(@RequestBody MemberRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.postMember(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.accepted().build();
    }
}
