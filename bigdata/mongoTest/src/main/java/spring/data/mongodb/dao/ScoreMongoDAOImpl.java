package spring.data.mongodb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import spring.data.mongodb.dto.ScoreDTO;
//spring-data에서 지원되는 Repository와 MongoTemplate 클래스를 이용해서 mongodb에 엑세스하는 기능을 구현
import spring.data.mongodb.dto.ScoreRepository;

@Repository
public class ScoreMongoDAOImpl implements ScoreMongoDAO {
	// 페이징처리를 편하게 하기 위해 추가
	// CLRUD를 위한 기본 기능도 제공
	// (Spring-data의 common 라이브러리에서 지원하는 기능)
	@Autowired
	ScoreRepository scoreRepositoy;

	//
	// spring-data-mongodb라이브러리에서 제공
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<ScoreDTO> findAll() {
		List<ScoreDTO> mongolist = (List<ScoreDTO>) scoreRepositoy.findAll();
		return mongolist;
	}

	@Override
	public List<ScoreDTO> findAll(int pageNo) {
		// PagingAndSortingRepository에서 제공하는 findAll을 호출하면 spring-data 내부에서 페이징처리가 된 객체를
		// 전달받을 수 있다.
		// 페이징처리를 내부에서 할 수 있도록 피룡한 정보를 findAll 메소드에서 pabeable타입의 매개변수로 전달받는다.
		// pageable을 구현하고 있는 pageRequest객체를 넘겨준다.
		// 현재 페이지 번호(page), size(화면에 표실 게시글 갯수)
		// [리턴]
		// findAll이 처리되면 페이징된 객체를 Page타입으로 리턴
		Page<ScoreDTO> pageList = scoreRepositoy.findAll(new PageRequest(pageNo, 5));
		List<ScoreDTO> mongolist = pageList.getContent();
		return mongolist;
	}

	@Override
	public List<ScoreDTO> findCriteria(String key, String value) {
		Criteria criteria = new Criteria(key);
		// 2. 조건에 대한 설정 - value를 셋팅
		if (key.equals("java") || key.equals("servlet")) {
			try {
				criteria.is(Integer.parseInt(value));
			}catch (NumberFormatException e) {
				criteria.is(value);
			}
		} else {
			criteria.is(value);
		}
		// 3.
		Query query = new Query(criteria);
		List<ScoreDTO> document = mongoTemplate.find(query, ScoreDTO.class, "score");
		return document;
		/*
		 * Query query = new Queray();
		 * query.addCriteria(Criteria.where(data[0]).is(value));
		 * query.addCriteria(
		 * List<ScoreDTO> mongolist = mongoTemplate.find(query, ScoreDTO.class, "score");
		 * criteria.andOperator(Criteria.where(data[0]).is(value),Criteria.where("addr").is("인천");
		 */
	}

	// mongodb가 json으로 모든 작업을 처리하므로 key, value로 조건을 정의
	// spring-data-mongodb에서 이러한 조건을 처리하는 객체를 만들어서 제공
	// Criteria - 조건
	@Override
	public ScoreDTO findById(String key, String value) {
		// 1. 조건을 객체로 작성
		Criteria criteria = new Criteria(key);
		// 2. 조건에 대한 설정 - value를 셋팅
		criteria.is(value);
		// 3.
		Query query = new Query(criteria);
		ScoreDTO document = mongoTemplate.findOne(query, ScoreDTO.class, "score");
		return document;
	}

	@Override
	public void insertDocument(ScoreDTO doc) {
		scoreRepositoy.save(doc);
	}

	@Override
	public void insertAllDocument(List<ScoreDTO> docs) {
		scoreRepositoy.save(docs);
	}

	@Override
	public void update(ScoreDTO document) {
		//수정할 document에 조건을 적용 - rdbms의 where절
		Criteria criteria = new Criteria("id");
		criteria.is(document.getId());
		Query query = new Query(criteria);
		
		//수정될 데이터를 셋팅 - rdbms의 set절
		//Update객체에 수정할 데이터 셋팅
		Update update = new Update();
		update.set("addr", document.getAddr());
		update.set("dept", document.getDept());
		
		//mongoTemplate의 수정기능 호출
		//mongoDB에서 multi옵션을 주느냐 안주느냐에 따라 다른 메소드 선택
		mongoTemplate.updateMulti(query, update, "score");
	}

	@Override
	public void test1() {

	}

}
