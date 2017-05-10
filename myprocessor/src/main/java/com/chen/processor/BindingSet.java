package com.chen.processor;

import com.google.common.collect.ImmutableList;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.lang.model.element.TypeElement;

import static com.google.auto.common.MoreElements.getPackage;
import static javax.lang.model.element.Modifier.PUBLIC;

/**
 * A set of all the bindings requested by a single type.
 */
final class BindingSet {
    private static final ClassName VIEW = ClassName.get("android.view", "View");
    private static final ClassName UNBINDER = ClassName.get("com.kugou.mybind", "Mybinder");

    private final TypeName targetTypeName;
    private final ClassName bindingClassName;
    private final ImmutableList<ViewBinding> viewBindings;

    private BindingSet(TypeName targetTypeName, ClassName bindingClassName,
                       ImmutableList<ViewBinding> viewBindings) {
        this.targetTypeName = targetTypeName;
        this.bindingClassName = bindingClassName;
        this.viewBindings = viewBindings;
    }

    JavaFile brewJava() {
        return JavaFile.builder(bindingClassName.packageName(), createType())
                .addFileComment("自动生成的代码，不要手动修改")
                .build();
    }

    private TypeSpec createType() {
        TypeSpec.Builder result = TypeSpec.classBuilder(bindingClassName.simpleName())
                .addModifiers(PUBLIC);
        result.addSuperinterface(UNBINDER);
        result.addMethod(createBindingConstructor(targetTypeName));
        return result.build();
    }

    private MethodSpec createBindingConstructor(TypeName targetType) {
        MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
                .addModifiers(PUBLIC);
        constructor.addParameter(targetType, "target");
        constructor.addParameter(VIEW, "source");

        for (ViewBinding binding : viewBindings) {
            constructor.addStatement("target.$L = ($T)source.findViewById($L)", binding.getName(), binding.getType(), binding.getId());
        }

        return constructor.build();
    }


    static Builder newBuilder(TypeElement enclosingElement) {
        TypeName targetType = TypeName.get(enclosingElement.asType());

        String packageName = getPackage(enclosingElement).getQualifiedName().toString();
        String className = enclosingElement.getQualifiedName().toString().substring(
                packageName.length() + 1).replace('.', '$');
        ClassName bindingClassName = ClassName.get(packageName, className + "_ViewBding");

        return new Builder(targetType, bindingClassName);
    }

    static final class Builder {
        private final TypeName targetTypeName;
        private final ClassName bindingClassName;

        private final Map<Integer, ViewBinding> viewIdMap = new LinkedHashMap<>();

        private Builder(TypeName targetTypeName, ClassName bindingClassName) {
            this.targetTypeName = targetTypeName;
            this.bindingClassName = bindingClassName;
        }

        void addField(int id, String name, TypeName type) {
            ViewBinding viewId = viewIdMap.get(id);
            if (viewId == null) {
                viewId = new ViewBinding(id, name, type);
                viewIdMap.put(id, viewId);
            }
        }

        BindingSet build() {
            ImmutableList.Builder<ViewBinding> viewBindings = ImmutableList.builder();
            for (ViewBinding viewBinding : viewIdMap.values()) {
                viewBindings.add(viewBinding);
            }
            return new BindingSet(targetTypeName, bindingClassName, viewBindings.build());
        }
    }
}
