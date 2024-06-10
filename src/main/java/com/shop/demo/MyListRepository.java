package com.shop.demo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MyListRepository extends JpaRepository<MyList, Integer> {
    public Iterable<MyList> findById(String id);

    public Iterable<MyList> findAllById(String id);
    
    //id와 일치하는 운동일지 데이터 가져오는 코드
    @Query(value = "select b.id, day1, event1, weight1, number1, set1, feedback1, list " +
                   "from MMMember a, My_List b where a.id = b.id and a.id = ?", nativeQuery = true)
    public List<Map<String, Object>> findByList(String id);
    
    //받은 id와 일치하는 id 만 가져오는 코드
    @Query(value = "select a.id " +
    		"from MMMember a where a.id = ?", nativeQuery = true)
    public String findByIdList(String id);
    
    //운동일지에 부여된 번호만 가져오는 코드
    Optional<MyList> findTopByOrderByListDesc();

    @Query(value = "select * from My_List a where a.list = ?", nativeQuery = true)
    public MyList findByList1(int list);
   
    @Transactional
    void deleteByList(int list);
}


