package com.ssafy.db.repository.Chatroom;

import com.ssafy.db.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 방송 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface ChatRoomRepository extends JpaRepository<Chatroom, Integer> {

}