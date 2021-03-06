/**
 * author yuanwq, date: 2017年9月12日
 */
package xyz.codemeans.aliyun4j.opensearch4j.query.query;

import lombok.Getter;
import xyz.codemeans.aliyun4j.opensearch4j.query.ISearchClause;

/**
 * author yuanwq
 */
@Getter
public class QueryClause implements ISearchClause {
  private IQueryCond cond;

  public QueryClause setCond(IQueryCond cond) {
    this.cond = cond;
    return this;
  }

  public boolean isEmpty() {
    return cond == null;
  }

  @Override
  public StringBuilder appendQueryParams(StringBuilder sb) {
    sb.append("query=");
    if (cond == null) {
      // 避免空query
      sb.append("''");
    } else {
      cond.appendQueryParams(sb);
    }
    return sb;
  }

  @Override
  public String toString() {
    return appendQueryParams(new StringBuilder()).toString();
  }

}
