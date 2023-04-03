# PageNation
## pageNation for search
已完成了基本的分页查询，接下来是搜索功能

## 分页功能和查询功能已完成
1. 思路：前端 ajax请求 
2. servlet:接受请求参数 
3. serviceImpl调用mybatis完成查询后返回servlet
4. 最后响应前端

## mybatis的工作方式
**orm工作原理**
1. Example创建criteria `example.createCriteria()`
2. criteria装载查询方法和参数 ` criteria.andBrandLike("%"+cupBrand+"%");`
3. service层调用dao层接口完成查询 `cupDap.selectByExample(example)`