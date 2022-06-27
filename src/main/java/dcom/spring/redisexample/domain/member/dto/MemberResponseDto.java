package dcom.spring.redisexample.domain.member.dto;

import dcom.spring.redisexample.domain.member.Member;
import dcom.spring.redisexample.domain.team.Team;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private Integer id;
    private String name;
    private List<String> teams;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .teams(member.getTeam().stream().map(Team::getName).collect(Collectors.toList()))
                .build();
    }

    public static List<MemberResponseDto> of(List<Member> member) {
        return member.stream().map(MemberResponseDto::of).collect(Collectors.toList());
    }
}
