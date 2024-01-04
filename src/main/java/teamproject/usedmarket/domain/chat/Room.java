package teamproject.usedmarket.domain.chat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import teamproject.usedmarket.domain.member.Member;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * 채팅방 처음 개설
 */
@Data
@NoArgsConstructor
public class Room {

    private Long roomId;
    private Set<Member> members = new HashSet<>();
    private Long memberId; // 판매자 또는 구매자의 member_id
    private Long itemId; // 아이템의 item_id
    private String roomStatus;
    private LocalDateTime createdAt;



}
