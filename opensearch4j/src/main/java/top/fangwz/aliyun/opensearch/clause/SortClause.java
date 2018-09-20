/**
 * @author yuanwq, date: 2017年9月12日
 */
package top.fangwz.aliyun.opensearch.clause;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import top.fangwz.aliyun.opensearch.ISearchClause;

import java.util.Collection;
import java.util.Map;

/**
 * @author yuanwq
 */
public class SortClause implements ISearchClause {
  public static final String DEFAULT_SORT = "RANK";

  private final Map<String, Direction> sorts = Maps.newLinkedHashMap();

  public SortClause asc(String field) {
    sorts.put(field, Direction.ASC);
    return this;
  }

  public SortClause desc(String field) {
    sorts.put(field, Direction.DESC);
    return this;
  }

  public boolean isEmpty() {
    return sorts.isEmpty();
  }

  public Collection<Map.Entry<String, Direction>> getSorts() {
    return sorts.entrySet();
  }

  @Override
  public StringBuilder appendSearchParams(StringBuilder sb) {
    if (isEmpty()) {
      return sb;
    }
    sb.append("sort=");
    boolean first = true;
    for (Map.Entry<String, Direction> entry : sorts.entrySet()) {
      if (first) {
        first = false;
      } else {
        sb.append(";");
      }
      sb.append(entry.getValue().paramValue).append(entry.getKey());
    }
    return sb;
  }

  @Override
  public String toString() {
    return appendSearchParams(new StringBuilder()).toString();
  }

  @AllArgsConstructor
  public enum Direction {
    ASC("+"),
    DESC("-");

    private final String paramValue;
  }
}