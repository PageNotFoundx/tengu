package org.laniakeamly.poseidon.framework.sql;

import lombok.Getter;
import lombok.Setter;
import org.laniakeamly.poseidon.framework.beans.BeansManager;
import org.laniakeamly.poseidon.framework.compiler.Precompiler;
import org.laniakeamly.poseidon.framework.container.Container;
import org.laniakeamly.poseidon.framework.sql.xml.build.PrecompiledClass;
import org.laniakeamly.poseidon.framework.sql.xml.build.PrecompiledMethod;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Create by 2BKeyboard on 2019/12/19 14:03
 */
public class SqlMapper {

    @Getter
    private String sql;

    @Getter
    private Object[] args;

    private static Container precompiled = BeansManager.getBean("precompiled");
    private static Precompiler precompiler = BeansManager.getBean("precompiler");

    public SqlMapper(){}

    public SqlMapper(String sql, Object[] args) {
        this.sql = sql;
        this.args = args;
    }

    /**
     * 构建一个sqlMapper
     * @param builderName   xml文件中定义builder标签name属性
     * @param mapperName    mapper标签name属性
     * @param parameters    参数
     * @return
     */
    static SqlMapper builderMapper(String builderName, String mapperName, Map<String, Object> parameters) {
        PrecompiledClass pc = precompiled.getValue(builderName);
        // 判断类是否已经加载到对象
        if (!pc.isLoad()) {
            precompiler.loaderClass(pc);
        }
        precompiler.compilerMethod(pc, mapperName, parameters);
        List args = new LinkedList();
        String sql = pc.getPrecompiledMethod("findUserByName").invoke(parameters,args);
        return new SqlMapper(sql,args.toArray());
    }

}
