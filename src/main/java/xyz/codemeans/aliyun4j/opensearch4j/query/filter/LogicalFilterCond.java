package xyz.codemeans.aliyun4j.opensearch4j.query.filter;

import lombok.Data;

/**
 * author: yuanwq
 * date: 2018/9/21
 */
@Data
public class LogicalFilterCond extends AbstractFilterCond {
  private final IFilterCond left;
  private final IFilterCond.LogicalOp op;
  private final IFilterCond right;

  @Override
  public StringBuilder appendQueryParams(StringBuilder sb) {
    if (left.priorTo(op)) {
      left.appendQueryParams(sb);
    } else {
      sb.append("(");
      left.appendQueryParams(sb);
      sb.append(")");
    }
    sb.append(" ").append(op.name().toUpperCase()).append(" ");
    if (right.priorTo(op)) {
      right.appendQueryParams(sb);
    } else {
      sb.append("(");
      right.appendQueryParams(sb);
      sb.append(")");
    }
    return sb;
  }

  @Override
  public boolean priorTo(IFilterCond.LogicalOp op) {
    // 为方便理解，故只有相同的操作符可以级联
    return this.op == op;
  }

}

