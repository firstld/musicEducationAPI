package com.jingcheng.musicEducation.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author cx
 *
 */

@Repository
public interface CurdType {
   int insertEntity(Object entity);
   int updateEntity(Object entity);
   Object findObjById(@Param("id") Integer id);
   List<Object> findObjList(Map<String,Object> params);
   int deleteEntities(Integer[] ids);
}
