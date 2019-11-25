package com.elm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elm.bean.Foods;

public interface FoodsDao {
	@Insert("insert into tb_foods values(#{name},#{price},#{img},#{sale},#{intro},#{evaluate},#{psf},#{shop})")
	int insertFoods(Foods foods) throws SQLException;
	@Update("update tb_foods set name=#{name},price=#{price},img=#{img},sale=#{sale},intro={intro},evaluate=#{evaluate},psf=#{psf},shop=#{shop} where id=#{id}")
	int updateFoods(Foods foods) throws SQLException;
	@Delete("delete from tb_foods where id=#{id}")
	int deleteFoodsById(Integer id) throws SQLException;
	@Select("select * from tb_foods where id=#{id}")
	Foods selectFoodsById(Integer id) throws SQLException;
	@Select("select * from tb_foods")
	List<Foods> selectAllFoods() throws SQLException;
	@Select("select * from tb_foods where ")
	List<Foods> selectByCondition(Foods foods) throws SQLException;
	
	List<Foods> selectByShop(int shop) throws SQLException;

	List<Foods> selectFoodsByShunxu(int a) throws SQLException;

}
