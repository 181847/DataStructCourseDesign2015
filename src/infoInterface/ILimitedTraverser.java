package infoInterface;

import basicInterface.ILimitedable;

/**
 * 这个接口继承于IInfoTraverser和ILimitedable，
 * 描述了一个可以被限制的遍历者类型。
 */
public interface ILimitedTraverser extends IInfoTraverser, ILimitedable {

}
