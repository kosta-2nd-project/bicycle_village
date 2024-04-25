package group2.bicycle_village.dao;
/*
* #key=query
    query.select=select * from Electronics  order by writeday desc
    query.selectBymodelNum=select * from Electronics where model_num=?
    query.insert=insert into Electronics values(?,?,?,?,?,sysdate,0,?,?)
    query.delete=delete Electronics where model_num=? and password=?
    query.update=update Electronics set model_name=?,price=?,description=? where model_num=? and password=?
    query.updateReadnum=update Electronics set readnum=readnum+1 where model_num=?

    query.userlogin=select * from users where user_id=? and pwd=?

            #paging
    query.pagingSelect=select * from  (SELECT a.*, ROWNUM rnum FROM (SELECT * FROM Electronics ORDER BY writeday desc) a) where  rnum>=? and rnum <=?
    query.totalCount=select count(*) from Electronics

#replies
    query.replyByParentNum=select * from replies where parent_model_num=?

* */
public interface DBQuery {
    String boardSelect = "select * from Electronics order by writeday desc";

}
