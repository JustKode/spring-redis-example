package dcom.spring.redisexample.domain.member.service;

import dcom.spring.redisexample.domain.member.Member;
import dcom.spring.redisexample.domain.member.dto.MemberRequestDto;
import dcom.spring.redisexample.domain.member.dto.MemberResponseDto;
import dcom.spring.redisexample.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    @Cacheable(value = "member_list")
    public List<MemberResponseDto> getMemberList() {
        return MemberResponseDto.of(memberRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "member", key = "#id")
    public MemberResponseDto getMemberById(Integer id) {
        return MemberResponseDto.of(memberRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")
        ));
    }

    @Transactional
    @CacheEvict(value = "member_list")
    public Integer postMember(MemberRequestDto dto) {
        Member member = Member.builder().name(dto.getName()).build();
        return memberRepository.save(member).getId();
    }

    @Transactional
    @CacheEvict(value = "member", key = "#id")
    public MemberResponseDto modifyMember(Integer id, MemberRequestDto dto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")
        );
        member.setName(dto.getName());
        return MemberResponseDto.of(memberRepository.save(member));
    }

    @Transactional
    @CacheEvict(value = "member", key = "#id")
    public void deleteMember(Integer id) {
        if (memberRepository.existsById(id))
            memberRepository.deleteById(id);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.");
    }
}
