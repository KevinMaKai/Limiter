package site.higgs.limiter.interceptor;

import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import site.higgs.limiter.source.LimitedResourceSource;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 切点抽象定义
 *
 */
abstract class LimitedResourceSourcePointcut extends StaticMethodMatcherPointcut implements Serializable {

    public LimitedResourceSourcePointcut() {
    }

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        LimitedResourceSource limitedResourceSource = this.getLimitedResourceSource();
        boolean matched = limitedResourceSource != null && !CollectionUtils.isEmpty(limitedResourceSource.getLimitedResource(aClass,method));
        if (matched == true) {
            return matched;
        }
        return matched;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof LimitedResourceSourcePointcut)) {
            return false;
        } else {
            LimitedResourceSourcePointcut otherPc = (LimitedResourceSourcePointcut) other;
            return ObjectUtils.nullSafeEquals(this.getLimitedResourceSource(), otherPc.getLimitedResourceSource());
        }
    }

    public int hashCode() {
        return LimitedResourceSourcePointcut.class.hashCode();
    }

    public String toString() {
        return this.getClass().getName() + ": " + this.getLimitedResourceSource();
    }

    protected abstract LimitedResourceSource getLimitedResourceSource();
}
