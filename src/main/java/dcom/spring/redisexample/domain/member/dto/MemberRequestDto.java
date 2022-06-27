package dcom.spring.redisexample.domain.member.dto;

import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {
    private String name;
}
